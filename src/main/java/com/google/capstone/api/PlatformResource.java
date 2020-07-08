package com.google.capstone.api;

import java.util.List;
import java.util.HashMap;
import java.util.Date;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.MediaType;
import com.google.capstone.dao.PlatformReleaseDao;
import com.google.capstone.dao.PlatformReleaseDaoDatastore;
import com.google.capstone.dao.SDKDao;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("platforms")
public class PlatformResource {

  //private static final PlatformReleaseDao PRDAO = new PlatformReleaseDaoDatastore();
//  private static final SDKDao SDAO = new SDKDao();

  /**
   * Method handling HTTP GET requests. The returned object will be sent
   * to the client as "text/plain" media type.
   *
   * @return String that will be returned as a text/plain response.
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  //public List<Platform> getPlatforms() {
  public Response getPlatforms() {
    // TODO: implement this method
//    try {
      //return prDao.getPlatforms();
      Date date = new Date();
      HashMap<String, Long> platforms = new HashMap<String, Long>();
      platforms.put("Android", date.getTime());
      platforms.put("iOS", date.getTime());
      platforms.put("Web", date.getTime());
      platforms.put("Games", date.getTime());
      return ResponseHandler.createJsonResponse(Status.OK, platforms);
 /*   } catch (Exception e) {
      return e;
    }*/
  }

  @Path("/{platform}/releases")
  public ReleaseResource getReleaseResource() {
    return new ReleaseResource();
    }

  @Path("/{release}/sdks")
  public SDKResource getSdKResource() {
    return new SDKResource();
  }
}
