package firebase.sdk.dashboard.api;

import java.util.ArrayList;
import java.time.Instant;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import firebase.sdk.dashboard.dao.SDKDao;
import firebase.sdk.dashboard.dao.SDKDaoDatastore;
import firebase.sdk.dashboard.data.SDK;
import firebase.sdk.dashboard.data.Platform;
import firebase.sdk.dashboard.data.SDKReleaseMetadata;
import firebase.sdk.dashboard.data.VersionMetadata;

/**
 * SDK resource (exposed at "v1/platforms/{platform}/sdks" path). 
 * All returned objects will be sent to the client as "application/json" media type.
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SDKResource {

  private Platform platform;
  private SDKDao sdkDao = new SDKDaoDatastore();

  public SDKResource(String platform) {
    this.platform = Platform.get(platform);
  }

  /**
   * Method handling HTTP GET requests.
   * Exposed at "v1/platforms/{platform}/sdks", this endpoint returns a list
   * of the names of all the sdks enrolled in the given platform.
   *
   * @return Response object containing a list of sdk names in JSON.
   */
  @GET
  public Response getSDKs() {
    // TODO: implement this method
    ArrayList<String> sdks = sdkDao.getSDKs(platform);
    return ResponseHandler.createJsonResponse(Status.OK, sdks);
  }

  /**
   * Method handling HTTP GET requests.
   * Exposed at "v1/platforms/{platform}/sdks/{sdkName}", this endpoint returns 
   * an SDK object, which includes the version history of the sdk, as a json object.
   *
   * @return Response object containing a status code and an SDK object in JSON.
   */
  @GET
  @Path("/{sdkName}")
  public Response getSDK(@PathParam("sdkName") String sdkName) {
    //TODO: Implement this method.
    SDK sdk = sdkDao.getSDK(platform, sdkName);
    return ResponseHandler.createJsonResponse(Status.OK, sdk);
  }
}
