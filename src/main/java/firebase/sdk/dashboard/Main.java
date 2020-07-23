package firebase.sdk.dashboard;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("v1")
public class Main extends ResourceConfig {

  public Main() {
    packages("firebase.sdk.dashboard.api");
    }
}
