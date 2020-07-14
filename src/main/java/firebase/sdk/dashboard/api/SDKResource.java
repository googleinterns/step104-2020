package firebase.sdk.dashboard.api;

import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

/**
 * Root resource (exposed at "myresource" path)
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SDKResource {

  private String platform;
  private static final PlatformReleaseDao PRDAO = new PlatformReleaseDaoDatastore();
//  private static final SDKDao SDAO = new SDKDao();

  public SDKResource(String platform) {
    this.platform = platform;
  }

  /**
   * Method handling HTTP GET requests. The returned object will be sent
   * to the client as "text/plain" media type.
   *
   * @return String that will be returned as a text/plain response.
   */
  @GET
  public Response getSDKs() {
    // TODO: implement this method
    //try {
      ArrayList<String> sdks = new ArrayList<>();
      sdks.add("firebase-common");
      sdks.add("firebase-common-ktx");
      sdks.add("firebase-ml");
      sdks.add("firebase-database");
      sdks.add("firebase-auth");
      sdks.add("firebase-messaging");
      
      return ResponseHandler.createJsonResponse(Status.OK, sdks);
    /*} catch (Exception e) {
      return e.toString();
    }*/
  }

  @GET
  @Path("/{sdkName}")
  public Response getSDK(@PathParam("sdkName") String sdkName) {
    SDK sdk = new SDK(sdkName, "firebase-common", "firebase-common");
    sdk.setOwner("ashwin@");
    int release = 78;
    for (int i = 0; i < 17; i++) {
      release -= i * 3;
      String newVersion = String.format("%d.%d.%d", 19, 2, 9 - i);
      String oldVersion = String.format("%d.%d.%d", 19, 2, 9 - 1 - i);
      SDKRelease version = new SDKRelease("firebase-common", "platform", "M" + Integer.toString(release), newVersion, oldVersion, false);
      sdk.addVersion(newVersion, version);
    }
    return ResponseHandler.createJsonResponse(Status.OK, sdk);
  }
}
