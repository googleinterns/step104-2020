package firebase.sdk.dashboard.api;

import com.google.gson.Gson;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * A class for creating json responses for the API.
 */
abstract class ResponseHandler {

  private static final Gson GSON = new Gson();

  /**
   * A method that creates a Response object from a status code and 
   * Plain Old Java Object (POJO).
   *
   * @param status The HTTP response code.
   * @param obj The object being converted into JSON to be the body of the response.
   * @return An Response object that will be sent to the client.
   */
  public static Response createJsonResponse(Status status, Object obj) {
    String json = GSON.toJson(obj);
    return Response.status(status).entity(json).build();
  }
}
