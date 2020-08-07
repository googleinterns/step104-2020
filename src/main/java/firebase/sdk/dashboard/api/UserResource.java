package firebase.sdk.dashboard.api;

import javax.inject.Inject;
import firebase.sdk.dashboard.dao.UserDao;
import firebase.sdk.dashboard.dao.UserDaoDatastore;
import java.util.Map;
import java.util.HashMap;
import firebase.sdk.dashboard.data.User;
import firebase.sdk.dashboard.data.Platform;
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

  @Inject
  public UserDao USER_DAO;

  /**
   * Method handling HTTP POST requests.
   * Adds the user to the database if it does not exist.
   *
   * @return A Response object that contains an HTTP status code.
   */
  @POST
  public Response addUser(User user) {
    if (USER_DAO.getUser(user.uid()) == null) {
      USER_DAO.addUser(user);
      return ResponseHandler.createJsonResponse(Status.CREATED, null);
    } else {
      return ResponseHandler.createJsonResponse(Status.OK, null);
    }
  }

  /**
   * Method handling HTTP GET requests. 
   * Returns a list of the names of the given users favorite sdks. 
   *
   * @return A Response object containing an HTTP status code and a list of strings
   * in its body.
   */
  @GET
  @Path("{uid}/sdks")
  public Response getUserFavorites(@PathParam("uid") String uid) {
    User user = USER_DAO.getUser(uid);
    if (user != null) {
      Map<String, List<String>> favourites = user.favoriteSDKs();
      return ResponseHandler.createJsonResponse(Status.OK, favourites);
    } else {
     return ResponseHandler.createJsonResponse(Status.NOT_FOUND, Arrays.asList()); 
    }
  }

  /**
   * Method handling HTTP POST requests.
   * Adds the given sdk to the users favorites.
   *
   * @return A Response object that contains an HTTP status code.
   */
  @POST
  @Path("{uid}/{platform}/sdks/{sdkName}")
  public Response addSdkToUserFavorites(@PathParam("uid") String uid, @PathParam("platform") String platformName, @PathParam("sdkName") String sdkName) {
    User user = USER_DAO.getUser(uid);
    Platform platform = Platform.get(platformName);
    Map<String, List<String>> favourites = user.favoriteSDKs();
    List<String> platformFavourites = favourites.get(platform.toString());
    if (platformFavourites == null) {
      platformFavourites = new ArrayList<String>();
    }

    platformFavourites.add(sdkName);
    favourites.put(platform.toString(), platformFavourites);
    User newUser = user.toBuilder()
      .setFavoriteSDKs(favourites)
      .build();
    USER_DAO.updateUser(newUser);
    return ResponseHandler.createJsonResponse(Status.OK, null);
  }

  /**
   * Method handling HTTP DELETE requests.
   * Removes the given sdk from the users favorites.
   *
   * @return A Response object that contains an HTTP status code.
   */
  @DELETE
  @Path("{uid}/{platform}/sdks/{sdkName}")
  public Response deleteSdkFromUserFavorites(@PathParam("uid") String uid, @PathParam("platform") String platformName, @PathParam("sdkName") String sdkName) {
    User user = USER_DAO.getUser(uid);
    Platform platform = Platform.get(platformName);
    Map<String, List<String>> favourites = user.favoriteSDKs();
    List<String> platformFavourites = favourites.get(platform.toString());
    platformFavourites.remove(sdkName);
    favourites.put(platform.toString(), platformFavourites);
    User newUser = user.toBuilder()
      .setFavoriteSDKs(favourites)
      .build();
    USER_DAO.updateUser(newUser);
    return ResponseHandler.createJsonResponse(Status.OK, null);
  }
}
