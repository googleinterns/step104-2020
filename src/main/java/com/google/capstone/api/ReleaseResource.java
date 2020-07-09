package com.google.capstone.api;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.google.capstone.dao.PlatformReleaseDao;
import com.google.capstone.dao.PlatformReleaseDaoDatastore;
import com.google.capstone.dao.SDKDao;
import com.google.capstone.data.SDK;
import com.google.capstone.data.SDKRelease;
import com.google.capstone.data.Release;

/**
 * Root resource (exposed at "platforms/{platform}/releases" path)
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReleaseResource {

  private static final PlatformReleaseDao PRDAO = new PlatformReleaseDaoDatastore();
//  private static final SDKDao SDAO = new SDKDao();
  private String platform;
  private ArrayList<String> sdks = new ArrayList<>();

  public ReleaseResource(String platform) {
    this.platform = platform;
    // Dummy Code for API
    sdks.add("firebase-common");
    sdks.add("firebase-common-ktx");
    sdks.add("firebase-ml");
    sdks.add("firebase-database");
    sdks.add("firebase-auth");
    sdks.add("firebase-components");
  }

  /**
   * Method handling HTTP GET requests. The returned object will be sent
   * to the client as "text/plain" media type.
   *
   * @return String that will be returned as a text/plain response.
   */
  @GET
  public Response getReleases() {
    // TODO: implement this method
    List<String> releases = Arrays.asList("M78", "M77", "M76", "M75", "M74", "M73");
    return ResponseHandler.createJsonResponse(Status.OK, releases);
  }

  @POST
  public Response getReleases(Release release) {
    String message = String.format("Added %s release to the dashboard.", "M79"/*release.releaseName*/);
    return ResponseHandler.createJsonResponse(Status.OK, message);
  }

  @DELETE
  @Path("{release}")
  public Response deleteRelease(@PathParam("release") String release) {
    String message = String.format("Deleting %s release from %s platform", release, platform);
    return ResponseHandler.createJsonResponse(Status.OK, message);
  }

  @GET
  @Path("{release}/sdks")
  public Response getReleaseSDKs(@PathParam("release") String release) {
    // TODO: implement this method
    HashMap <String, ArrayList<String>> releaseSDKs = new HashMap<>();
    releaseSDKs.put(release, sdks);
    return ResponseHandler.createJsonResponse(Status.OK, releaseSDKs);
  }

  @POST
  @Path("{release}/sdks")
  public Response enrollSDKinRelease(SDKRelease sdk) {
    //TODO: enroll this sdk in the given release
    String message = String.format("Enrolled %s in %s", "firebase-common", "M78"/*sdk.libraryName, sdk.release*/);
    return ResponseHandler.createJsonResponse(Status.OK, message); 
  }

  @GET
  @Path("{release}/sdks/{sdkName}")
  public Response getReleaseSDK(@PathParam("release") String release, @PathParam("sdkName") String sdkName) {
    // TODO: implement this method
    String newVersion = String.format("%d.%d.%d", 19, 2, 9 );
    String oldVersion = String.format("%d.%d.%d", 19, 2, 9 - 1);
    SDKRelease version = new SDKRelease(sdkName, platform, release, newVersion, oldVersion, false);
    return ResponseHandler.createJsonResponse(Status.OK, version);
  }
  
  @DELETE
  @Path("{release}/sdks/{sdkName}")
  public Response deleteReleaseSDK(@PathParam("release") String release, @PathParam("sdkName") String sdkName) {
    // TODO: implement this method
    String message = String.format("Disenrolled %s from %s release", "firebase-common", "M78");
    return ResponseHandler.createJsonResponse(Status.OK, message);
  }

}
