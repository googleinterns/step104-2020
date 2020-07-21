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
 * User resource (exposed at "users" path)
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

  private ArrayList<String> favourites;
  public UserResource() {
    favourites = new ArrayList<String>(Arrays.asList(
          "firebase-common", 
          "firebase-common-ktx", 
          "firebase-installations", 
          "firebase-installations-interop"));
  }

  /**
   * Method handling HTTP GET requests. 
   * Returns a list of the names of the given users favorite sdks. 
   *
   * @return A Response object containing an HTTP status code and a list of string
   * in its body.
   */
  @GET
  @Path("{uid}/sdks")
  public Response getUserFavorites(@PathParam("uid") String uid) {
    // TODO: implement this method
    return ResponseHandler.createJsonResponse(Status.OK, favourites);
  }

  /**
   * Method handling HTTP POST requests.
   * Adds the given sdk to the users favorites.
   *
   * @return A Response object that contains an HTTP status code.
   */
  @POST
  @Path("{uid}/sdks")
  public Response addSdkToUserFavorites(@PathParam("uid") String uid, String sdkKey) {
    // TODO: implement this method
    return ResponseHandler.createJsonResponse(Status.OK, null);
  }

  /**
   * Method handling HTTP DELETE requests.
   * Removes the given sdk from the users favorites.
   *
   * @return A Response object that contains an HTTP status code.
   */
  @DELETE
  @Path("{uid}/sdks/{sdkName}")
  public Response deleteSdkToUserFavorites(@PathParam("uid") String uid, @PathParam("sdkName") String sdkName) {
    // TODO: implement this method
    favourites.remove(sdkName);
    return ResponseHandler.createJsonResponse(Status.OK, null);
  }
}
