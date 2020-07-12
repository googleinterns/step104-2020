package firebase.sdk.dashboard.dao;

import firebase.sdk.dashboard.data.Release;
import firebase.sdk.dashboard.data.SDKRelease;
import firebase.sdk.dashboard.data.Platform;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.QueryResultList;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A class that interacts with Datastore to retreive and manipulate data regarding
 * Release and SDKRelease objects.
 */
public class PlatformReleaseDaoDatastore implements PlatformReleaseDao {

  private static final DatastoreService DATASTORE = DatastoreServiceFactory.getDatastoreService();

  @Override
  public ArrayList<Platform> getPlatforms() {
    //TODO: Implement this method.
    return new ArrayList<Platform>();
  }

  @Override
  public Release getRelease(String platform, String releaseName) {
    //TODO: Implement this method
/*    Query<Entity> query = Query.newEntityQueryBuilder()
    .setKind("Release")
    .setFilter(
        CompositeFilter.and(PropertyFilter.eq("platform", platform), PropertyFilter.eq("releaseName", releaseName)))
    .build().get();

    QueryResults<Entity> releaseEntity = DATASTORE.run(query);*/
    return null;
  }
  
  public boolean addRelease(Release release) {
    //TODO: Implement this method
    return true;
  }

  public boolean deleteRelease(Release release) {
    //TODO: Implement this method
    return true;
  }

  public boolean enrollSDKInRelease(Release release, SDKRelease sdk) {
    //TODO: Implement this method
    return true;
  }

  public boolean unenrollSDKInRelease(Release release, SDKRelease sdk) {
    //TODO: Implement this method
    return true;
  }

  /* TODO: P2 functionality
  public boolean updateSDKEnrolledInRelease(SDKRelease oldSDKRelease, SDKRelease newSDKRelease);*/
} 

