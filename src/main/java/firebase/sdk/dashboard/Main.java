package firebase.sdk.dashboard;

import org.glassfish.jersey.jackson.JacksonFeature;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("v1")
public class Main extends ResourceConfig {

  public Main() {
    register(JacksonFeature.class);
    packages("firebase.sdk.dashboard.api");
  }
}
