package com.google.capstone.api;

import java.util.List;
import java.util.Arrays;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.capstone.dao.PlatformReleaseDao;
import com.google.capstone.dao.PlatformReleaseDaoDatastore;
import com.google.capstone.dao.SDKDao;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/users")
public class UserResource {

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
  public List<String> getUsers() {
    // TODO: implement this method
    //try {
    List<String> users = Arrays.asList("Eriyeza", "Nnenna", "Timmothy");
    return users;
    /*} catch (Exception e) {
      return e;
    }*/
  }
}
