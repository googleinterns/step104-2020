package firebase.sdk.dashboard;

import org.glassfish.jersey.jackson.JacksonFeature;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.internal.inject.AbstractBinder; 
import firebase.sdk.dashboard.dao.*;
import firebase.sdk.dashboard.api.SDKResource;
import firebase.sdk.dashboard.api.ReleaseResource;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

@ApplicationPath("v1")
public class Main extends ResourceConfig {

  public Main() {
    packages("firebase.sdk.dashboard.api");
    register(JacksonFeature.class);
    register(new ApplicationBinder());
  }
}

class ApplicationBinder extends AbstractBinder {
  @Override
  protected void configure() {
    bind(SDKDaoDatastore.class)
      .to(SDKDao.class);
    bind(PlatformReleaseDaoDatastore.class)
      .to(PlatformReleaseDao.class);
    bind(UserDaoDatastore.class)
      .to(UserDao.class);
    bind(ReleaseResource.class)
      .to(ReleaseResource.class);
    bind(SDKResource.class) 
      .to(SDKResource.class);
    bind(DatastoreServiceFactory.getDatastoreService())
      .to(DatastoreService.class);
  }
}
