package firebase.sdk.dashboard.dao;

import firebase.sdk.dashboard.data.Release;
import firebase.sdk.dashboard.data.SDKReleaseMetadata;
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
import java.util.List;
import java.util.Arrays;

/**
 * A class that interacts with Datastore to retreive and manipulate data regarding
 * Release and SDKRelease objects.
 */
public class PlatformReleaseDaoDatastore implements PlatformReleaseDao {

  private static final DatastoreService DATASTORE = DatastoreServiceFactory.getDatastoreService();

  @Override
  public List<Platform> getPlatforms() {
    //TODO: Implement this method.
    return Arrays.asList();
  }

  @Override
  public List<Release> getPlatformReleases(Platform platform) {
    // TODO: Implement this method
    return Arrays.asList();
  }
  
  @Override
  public boolean addRelease(Platform platform, Release release) {
    //TODO: Implement this method
    return true;
  }

  @Override
  public boolean deleteRelease(Platform platform, Release release) {
    //TODO: Implement this method
    return true;
  }

  /* TODO: P2 functionality
  public boolean updateSDKEnrolledInRelease(SDKRelease oldSDKRelease, SDKRelease newSDKRelease);*/
} 

