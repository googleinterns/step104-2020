package firebase.sdk.dashboard.api;

import java.util.List;
import java.util.HashMap;
import java.time.Instant;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("platforms")
@Produces(MediaType.APPLICATION_JSON)
public class PlatformResource {

  /**
   * Method handling HTTP GET requests.
   * Exposed at "v1/platforms", this endpoint returns a list
   * of the names of all platforms along with the timestamp representing
   * the closest release deadline.
   *
   * @return Response object containing an HTTP status code and a HashMap contaning
   * the names of all the supported platforms along with a timestamp as its value.
   */
  @GET
  public Response getPlatforms() {
 
      HashMap<String, String> platforms = new HashMap<String, String>();
      platforms.put("android", "ANDROID");
      platforms.put("ios", "iOS");
      platforms.put("web", "WEB");
      platforms.put("games", "GAMES");
      return ResponseHandler.createJsonResponse(Status.OK, platforms);
  }

  /**
   * Method handling HTTP GET requests.
   * Exposed at "v1/platforms/{platform}/releases", this endpoint returns
   * a ReleaseResource object which handles the functionality of all endpoints
   * starting with the endpoint path.
   *
   * @return A ReleaseResource that will handle all requests from this enpoint and
   * onwards.
   */
  @Path("/{platform}/releases")
  public ReleaseResource getReleaseResource(@PathParam("platform") String platform) {
    return new ReleaseResource(platform);
  }

  /**
   * Method handling HTTP GET requests.
   * Exposed at "v1/platforms/{platform}/sdks", this endpoint returns
   * an SDKResource object which handles the functionality of all endpoints
   * starting with the endpoint path.
   *
   * @return A SDKResource that will handle all requests from this enpoint and
   * onwards.
   */
  @Path("/{platform}/sdks")
  public SDKResource getSdKResource(@PathParam("platform") String platform) {
    return new SDKResource(platform);
  }
}

