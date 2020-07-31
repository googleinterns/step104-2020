package firebase.sdk.dashboard;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("v1")
public class Main extends ResourceConfig {

  public Main() {
    register(new JacksonJsonProvider());
    packages("firebase.sdk.dashboard.api");
  }
}
