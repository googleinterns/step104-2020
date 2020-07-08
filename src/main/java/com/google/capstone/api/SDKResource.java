package com.google.capstone.api;

import java.util.ArrayList;
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
public class SDKResource {

  private static final PlatformReleaseDao PRDAO = new PlatformReleaseDaoDatastore();
//  private static final SDKDao SDAO = new SDKDao();

  /**
   * Method handling HTTP GET requests. The returned object will be sent
   * to the client as "text/plain" media type.
   *
   * @return String that will be returned as a text/plain response.
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public ArrayList<String> getSDKs() {
    // TODO: implement this method
    //try {
      ArrayList<String> sdks = new ArrayList<>();
      sdks.add("firebase-common");
      sdks.add("firebase-common-ktx");
      sdks.add("firebase-ml");
      sdks.add("firebase-database");
      sdks.add("firebase-auth");
      sdks.add("firebase-components");
      return sdks;
    /*} catch (Exception e) {
      return e.toString();
    }*/
  }
}
