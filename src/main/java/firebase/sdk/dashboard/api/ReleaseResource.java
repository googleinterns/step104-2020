package firebase.sdk.dashboard.api;

import javax.inject.Inject;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.Instant;
import java.util.Date;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import firebase.sdk.dashboard.data.SDK;
import firebase.sdk.dashboard.data.SDKReleaseMetadata;
import firebase.sdk.dashboard.data.VersionMetadata;
import firebase.sdk.dashboard.data.Release;
import firebase.sdk.dashboard.data.Platform;
import firebase.sdk.dashboard.dao.PlatformReleaseDao;
import firebase.sdk.dashboard.dao.PlatformReleaseDaoDatastore;
import firebase.sdk.dashboard.dao.SDKDao;
import firebase.sdk.dashboard.dao.SDKDaoDatastore;

/**
 * Root resource (exposed at "platforms/{platform}/releases" path)
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReleaseResource {

  private Platform platform;

  @Inject
  public PlatformReleaseDao RELEASEDAO;

  @Inject
  public SDKDao SDKDAO;

  public ReleaseResource() {}

  /**
   * Method handling HTTP GET requests.
   * Exposed at "v1/platforms/{platform}/releases", this endpoint returns a list
   * of Release objects representing all the release for the given platform.
   *
   * @return Response object containing a status code and a list of Strings in JSON.
   */
  @GET
  public Response getReleases() {
    // TODO: Catch exceptions.
    List<Release> releases = RELEASEDAO.getPlatformReleases(platform);
    return ResponseHandler.createJsonResponse(Status.OK, releases);
  }

  /**
   * Method handling HTTP POST requests.
   * Exposed at "v1/platforms/{platform}/releases", this endpoint consumes a  
   * Release object and adds it to the database. Only admins can do this.
   *
   * @return Response object containing a status code.
   */
  // TODO: Check membership in Firebase core team for allowing only admin access.
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addRelease(Release release) {
    // TODO: Catch exceptions.
    RELEASEDAO.addRelease(platform, release);
    return ResponseHandler.createJsonResponse(Status.OK, null);
  }

  /**
   * Method handling HTTP DELETE requests.
   * Exposed at "v1/platforms/{platform}/releases/{release}", this endpoint
   * deletes the given Release object from the database. Only admins can do this.
   *
   * @return Response object containing a status code.
   */
  // TODO: Check membership in Firebase core team for allowing only admin access.
  @DELETE
  @Path("{releaseName}")
  public Response deleteRelease(@PathParam("releaseName") String releaseName) {
    // TODO: Catch exceptions.
    RELEASEDAO.deleteRelease(platform, releaseName);
    return ResponseHandler.createJsonResponse(Status.OK, null);
  }

  /**
   * Method handling HTTP GET requests.
   * Exposed at "v1/platforms/{platform}/releases/{release}/sdks", this endpoint
   * returns a list of the names of all skds enrolled in the given release.
   *
   * @return Response object containing a status code and a list of strings representing 
   * the names of all the sdks enrolled in the given release.
   */
  @GET
  @Path("{releaseName}/sdks")
  public Response getReleaseSDKs(@PathParam("releaseName") String releaseName) {
    // TODO: Catch exceptions.
    List<String> sdksEnrolledInRelease = SDKDAO.getSDKsEnrolledInRelease(platform, releaseName);
    return ResponseHandler.createJsonResponse(Status.OK, sdksEnrolledInRelease);
  }

  /**
   * Method handling HTTP POST requests.
   * Exposed at "v1/platforms/{platform}/releases/{release}/sdks", this endpoint 
   * consumes an SDKRelease object and enrolls it in the given release.
   *
   * @return Response object containing a status code.
   */
  @POST
  @Path("{releaseName}/sdks")
  public Response enrollSDKinRelease(@PathParam("releaseName") String releaseName, SDKReleaseMetadata sdk) {
    // TODO: Catch exceptions.
    SDKReleaseMetadata alreadyEnrolled = SDKDAO.getSDKReleaseMetadata(platform, releaseName, sdk.libraryName());

    if (alreadyEnrolled != null) {
      return ResponseHandler.createJsonResponse(Status.CONFLICT, null);
    }

    Release release = RELEASEDAO.getRelease(platform, releaseName);
    // TODO: Add a suffix to time variables across the back end to clarify the attributes units.
    // TODO: Use https://docs.oracle.com/javase/8/docs/api/java/time/Instant.html#now-java.time.Clock-
    // to make code testable.
    long currentTime = System.currentTimeMillis() / 1000;
    // Check if the release is old and if so then dont allow enrollment.
    if (currentTime > release.codeFreezeTime()) {
      return ResponseHandler.createJsonResponse(Status.FORBIDDEN, null);
    }

    VersionMetadata version = VersionMetadata.newBuilder()
      .libraryName(sdk.libraryName())
      .platform(sdk.platform())
      .releaseName(releaseName)
      .version(sdk.releaseVersion())
      .launchDate(release.launchDate())
      .build();
    
    SDKDAO.addSDKRelease(sdk);
    SDKDAO.addSDKVersion(version);
    return ResponseHandler.createJsonResponse(Status.OK, null); 
  }

  /**
   * Method handling HTTP GET requests.
   * Exposed at "v1/platforms/{platform}/releases/{release}/sdks/{sdkName}", this endpoint
   * retrieves the SDKReleaseMetadata object for the given sdk and release.
   *
   * @return Response object containing a status code and an SDKReleaseMetadata object.
   */
  @GET
  @Path("{releaseName}/sdks/{sdkName}")
  public Response getReleaseSDK(@PathParam("releaseName") String releaseName, @PathParam("sdkName") String sdkName) {
    // TODO: Catch exceptions.
    SDKReleaseMetadata version = SDKDAO.getSDKReleaseMetadata(platform, releaseName, sdkName);
    return ResponseHandler.createJsonResponse(Status.OK, version);
  }
  
  /**
   * Method handling HTTP DELETE requests.
   * Exposed at "v1/platforms/{platform}/releases/{release}/sdks/{sdkName}", this endpoint
   * disenrolls the given sdk from the given release, if it is a part of it, and deletes the 
   * entries from the database.
   *
   * @return Response object containing a status code.
   */
  @DELETE
  @Path("{releaseName}/sdks/{sdkName}")
  public Response disenrollSDKinRelease(@PathParam("releaseName") String releaseName, @PathParam("sdkName") String sdkName) {
    // TODO: Catch exceptions.
    SDKReleaseMetadata sdkRelease = SDKDAO.getSDKReleaseMetadata(platform, releaseName, sdkName);
    VersionMetadata sdkVersion = SDKDAO.getSDKVersionMetadata(platform, sdkRelease.releaseVersion(), sdkName);
    SDKDAO.deleteSDKRelease(sdkRelease);
    SDKDAO.deleteSDKVersion(sdkVersion);
    return ResponseHandler.createJsonResponse(Status.OK, null);
  }

  public void setPlatform(String platformName) {
    this.platform = platform.get(platformName);
  }
}
