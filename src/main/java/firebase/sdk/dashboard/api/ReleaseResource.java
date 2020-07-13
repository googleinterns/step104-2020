package firebase.sdk.dashboard.api;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
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
import firebase.sdk.dashboard.dao.PlatformReleaseDao;
import firebase.sdk.dashboard.dao.PlatformReleaseDaoDatastore;
import firebase.sdk.dashboard.dao.SDKDao;
import firebase.sdk.dashboard.data.SDK;
import firebase.sdk.dashboard.data.SDKRelease;
import firebase.sdk.dashboard.data.Release;

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
   * Method handling HTTP GET requests.
   * Exposed at "v1/platforms/{platform}/releases", this endpoint returns a list
   * of Release objects representing all the sdks enrolled in the given platform.
   *
   * @return Response object containing a list of Releease objects in JSON.
   */
  @GET
  public Response getReleases() {
    // TODO: implement this method
    List<String> releaseManagers = Arrays.asList("ashwinraghav@", "rlazo@", "davidmotson@", "vkrtachko@", "vguthal@");
    ArrayList<HashMap<String, Object>> releases = new ArrayList<HashMap<String, Object>>();
    Date date = new Date();
    for (int i = 78; i > 0; i--) {
      HashMap<String, Object> release = new HashMap<String, Object>();
      release.put("releaseName", String.format("M%d", i));
      release.put("platform", "Android");
      release.put("releaseManager", releaseManagers.get(i % releaseManagers.size()));
      release.put("launchDeadline", date.getTime());
      release.put("codeFreezeDate", date.getTime());
      release.put("launchDate", date.getTime());
      releases.add(release);
    }

    return ResponseHandler.createJsonResponse(Status.OK, releases);
  }

  /**
   * Method handling HTTP POST requests.
   * Exposed at "v1/platforms/{platform}/releases", this endpoint consumes a  
   * Release object and adds it to the database. Only admins can do this.
   *
   * @return Response object containing a status code and a message to be 
   * displayed to the client.
   */
  @POST
  public Response addRelease(Release release) {
    String message = String.format("Added %s release to the dashboard.", "M79"/*release.releaseName*/);
    return ResponseHandler.createJsonResponse(Status.OK, message);
  }

  /**
   * Method handling HTTP DELETE requests.
   * Exposed at "v1/platforms/{platform}/releases/{release}", this endpoint
   * deletes the given Release object from the database. Only admins can do this.
   *
   * @return Response object containing a status code and a message to be 
   * displayed to the client.
   */
  @DELETE
  @Path("{release}")
  public Response deleteRelease(@PathParam("release") String release) {
    String message = String.format("Deleting %s release from %s platform", release, platform);
    return ResponseHandler.createJsonResponse(Status.OK, message);
  }

  /**
   * Method handling HTTP GET requests.
   * Exposed at "v1/platforms/{platform}/releases/{release}/sdks", this endpoint
   * returns a list of the names of all skds enrolled in the given release.
   *
   * @return Response object containing a status code and a list of Strings representing
   * the names of all the sdks enrolled in the given release.
   */
  @GET
  @Path("{release}/sdks")
  public Response getReleaseSDKs(@PathParam("release") String release) {
    // TODO: implement this method
    HashMap <String, ArrayList<String>> releaseSDKs = new HashMap<>();
    releaseSDKs.put(release, sdks);
    return ResponseHandler.createJsonResponse(Status.OK, releaseSDKs);
  }

  /**
   * Method handling HTTP POST requests.
   * Exposed at "v1/platforms/{platform}/releases/{release}/sdks", this endpoint 
   * consumes an SDKRelease object and enrolls it in the given release in the database.
   *
   * @return Response object containing a status code and a message to be displayed to
   * the client.
   */
  @POST
  @Path("{release}/sdks")
  public Response enrollSDKinRelease(SDKRelease sdk) {
    //TODO: enroll this sdk in the given release
    String message = String.format("Enrolled %s in %s", "firebase-common", "M78"/*sdk.libraryName, sdk.release*/);
    return ResponseHandler.createJsonResponse(Status.OK, message); 
  }

  /**
   * Method handling HTTP GET requests.
   * Exposed at "v1/platforms/{platform}/releases/{release}/sdks/{sdkName}", this endpoint
   * retrieves the SDKReleaseMetadata object for the given sdk and release.
   *
   * @return Response object containing a status code and an SDKReleaseMetadata object.
   */
  @GET
  @Path("{release}/sdks/{sdkName}")
  public Response getReleaseSDK(@PathParam("release") String release, @PathParam("sdkName") String sdkName) {
    // TODO: implement this method
    String newVersion = String.format("%d.%d.%d", 19, 2, 9 );
    String oldVersion = String.format("%d.%d.%d", 19, 2, 9 - 1);
    SDKRelease version = new SDKRelease(sdkName, platform, release, newVersion, oldVersion, false);
    return ResponseHandler.createJsonResponse(Status.OK, version);
  }
  
  /**
   * Method handling HTTP DELETE requests.
   * Exposed at "v1/platforms/{platform}/releases/{release}/sdks/{sdkName}", this endpoint
   * disenrolls the given sdk from the release it is in and deletes it from the database.
   *
   * @return Response object containing a status code and a message.
   */
  @DELETE
  @Path("{release}/sdks/{sdkName}")
  public Response deleteReleaseSDK(@PathParam("release") String release, @PathParam("sdkName") String sdkName) {
    // TODO: implement this method
    String message = String.format("Disenrolled %s from %s release", "firebase-common", "M78");
    return ResponseHandler.createJsonResponse(Status.OK, message);
  }

}
