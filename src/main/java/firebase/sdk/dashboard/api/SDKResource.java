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

  private String platform;

  public SDKResource(String platform) {
    this.platform = platform;
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
  
    try {
      ArrayList<String> sdks = new ArrayList<>();
      sdks.add("firebase-common");
      sdks.add("firebase-common-ktx");
      sdks.add("firebase-ml");
      sdks.add("firebase-database");
      sdks.add("firebase-auth");
      sdks.add("firebase-messaging");
      
      return ResponseHandler.createJsonResponse(Status.OK, sdks);
    }
    } catch (Exception e) {
      // TODO: Implement method to catch errors 
      return e.toString();
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
    ArrayList<VersionMetadata> versionHistory = new ArrayList<>();
    int release = 78;
    for (int i = 0; i < 17; i++) {
      release -= i * 3;
      String newVersion = String.format("%d.%d.%d", 19, 2, 9 - i);
      VersionMetadata version = VersionMetadata.newBuilder()
        .libraryName("firebase-common")
        .platform(Platform.ANDROID)
        .releaseName( "M" + Integer.toString(release))
        .version(newVersion)
        .launchDate(Instant.now())
        .build();
      versionHistory.add(version);
    }
    SDK sdk = SDK.newBuilder()
      .platform(Platform.ANDROID)
      .libraryName(sdkName)
      .libraryGroup("firebase-common")
      .externalName("firebase-common")
      .owner("ashwin@")
      .fireEscapeName("firebase-common")
      .versionHistory(versionHistory)
      .build();
    return ResponseHandler.createJsonResponse(Status.OK, sdk);
  }
}
