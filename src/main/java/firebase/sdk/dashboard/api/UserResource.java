package firebase.sdk.dashboard.api;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
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

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

  private ArrayList<String> favourites;
  public UserResource() {
    favourites = new ArrayList<String>(Arrays.asList("firebase-common", "firebase-common-ktx", "firebase-installations", "firebase-installations-interop"));
  }

  //private static final PlatformReleaseDao PRDAO = new PlatformReleaseDaoDatastore();
//  private static final SDKDao SDAO = new SDKDao();

  /**
   * Method handling HTTP GET requests. The returned object will be sent
   * to the client as "text/plain" media type.
   *
   * @return String that will be returned as a text/plain response.
   */
  @GET
  @Path("{uid}/sdks")
  public Response getUserFavorites(@PathParam("uid") String uid) {
    // TODO: implement this method
    return ResponseHandler.createJsonResponse(Status.OK, favourites);
  }

  /**
   * Method handling HTTP GET requests. The returned object will be sent
   * to the client as "text/plain" media type.
   *
   * @return String that will be returned as a text/plain response.
   */
  @POST
  @Path("{uid}/sdks")
  public Response addSdkToUserFavorites(@PathParam("uid") String uid) {
    // TODO: implement this method
    return ResponseHandler.createJsonResponse(Status.OK, favourites);
  }

  @DELETE
  @Path("{uid}/sdks/{sdkName}")
  public Response addSdkToUserFavorites(@PathParam("uid") String uid, @PathParam("sdkName") String sdkName) {
    // TODO: implement this method
    favourites.remove(sdkName);
    return ResponseHandler.createJsonResponse(Status.OK, favourites);
  }
}
