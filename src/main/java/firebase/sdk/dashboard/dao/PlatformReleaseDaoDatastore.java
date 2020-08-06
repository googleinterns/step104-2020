package firebase.sdk.dashboard.dao;

import javax.inject.Inject;
import firebase.sdk.dashboard.data.SDK;
import firebase.sdk.dashboard.data.SDKReleaseMetadata;
import firebase.sdk.dashboard.data.VersionMetadata;
import firebase.sdk.dashboard.data.Release;
import firebase.sdk.dashboard.data.Platform;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.time.Instant;
import java.io.IOException;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.QueryResultList;

/**
 * A class for implementing the platform dao.
 */
public class PlatformReleaseDaoDatastore implements PlatformReleaseDao {

  @Inject
  public DatastoreService DATASTORE;

  public PlatformReleaseDaoDatastore() {}

  // Get a list of platforms from the datastore
  public List<Platform> getPlatforms(){
    List<Platform> platforms = new ArrayList<>();

    Query query = new Query("Platform");
    PreparedQuery results = DATASTORE.prepare(query);
    FetchOptions fetchOptions = FetchOptions.Builder.withDefaults();
    QueryResultList<Entity> entities = results.asQueryResultList(fetchOptions);

    for (Entity result : entities) {
      String name = (String) result.getProperty("name");
      Platform platform = Platform.get(name);
      platforms.add(platform);
    }
    return platforms;  
  }

  // Get a list of releases for a platform from the Datastore
  public List<Release> getPlatformReleases(Platform platform) {
    FilterPredicate platformFilter =
      new FilterPredicate("platform", FilterOperator.EQUAL, platform.getLabel());
    List<Release> releases = new ArrayList<>();
    Query query = new Query("Release")
      .setFilter(platformFilter)
      .addSort("codeFreezeTime", Query.SortDirection.DESCENDING);

    PreparedQuery preparedQuery = DATASTORE.prepare(query);
    FetchOptions fetchOptions = FetchOptions.Builder.withDefaults();
    QueryResultList<Entity> releaseQuery = preparedQuery.asQueryResultList(fetchOptions);
    System.out.println(releaseQuery);

    for (Entity releaseEntity : releaseQuery) {
      String name = (String) releaseEntity.getProperty("platform");
      Platform releasePlatform = Platform.get(name);
      String releaseManager = (String) releaseEntity.getProperty("releaseManager");
      String releaseName = (String) releaseEntity.getProperty("releaseName");
      String buganizerHotlistLink = (String) releaseEntity.getProperty("buganizerHotlistLink");
      long launchDate = (long) releaseEntity.getProperty("launchDate");
      long launchCalDeadline =(long) releaseEntity.getProperty("launchCalDeadline");
      long codeFreezeTime = (long) releaseEntity.getProperty("codeFreezeTime");

      Release release = Release.newBuilder()
        .platform(releasePlatform)
        .releaseName(releaseName)
        .releaseManager(releaseManager)
        .buganizerHotlistLink(buganizerHotlistLink)
        .launchCalDeadline(launchCalDeadline)
        .codeFreezeTime(codeFreezeTime)
        .launchDate(launchDate).build();

      releases.add(release);  
    }   
    return releases;
  }

  public Release getRelease(Platform platform, String releaseName) {
    Key releaseKey = KeyFactory.createKey("Release", platform.getLabel() + "_" + releaseName);
    FilterPredicate keyFilter =
      new FilterPredicate("__key__", FilterOperator.EQUAL, releaseKey);
    Query query = new Query("Release").setFilter(keyFilter);

    PreparedQuery preparedQuery = DATASTORE.prepare(query);
    Entity releaseEntity = preparedQuery.asSingleEntity();

    Release release = Release.newBuilder()
      .platform(Platform.get((String) releaseEntity.getProperty("platform")))
      .releaseName((String) releaseEntity.getProperty("releaseManager"))
      .releaseManager((String) releaseEntity.getProperty("releaseName"))
      .buganizerHotlistLink((String) releaseEntity.getProperty("buganizerHotlistLink"))
      .launchDate((long) releaseEntity.getProperty("launchDate"))
      .launchCalDeadline((long) releaseEntity.getProperty("launchCalDeadline"))
      .codeFreezeTime((long) releaseEntity.getProperty("codeFreezeTime"))
      .build();

    return release;
  }

  // Add release to the Datastore
  public void addRelease(Platform platform, Release release){
    Key releaseKey = KeyFactory.createKey("Release", platform.getLabel() + "_" + release.releaseName());

    // Create a release entity
    Entity releaseEntity = new Entity(releaseKey);
    releaseEntity.setProperty("platform", platform.getLabel());
    releaseEntity.setProperty("releaseName", release.releaseName());
    releaseEntity.setProperty("releaseManager", release.releaseManager());
    releaseEntity.setProperty("launchDate", release.launchDate());
    releaseEntity.setProperty("buganizerHotlistLink", release.buganizerHotlistLink());
    releaseEntity.setProperty("launchCalDeadline", release.launchCalDeadline());
    releaseEntity.setProperty("codeFreezeTime", release.codeFreezeTime());

    DATASTORE.put(releaseEntity);
  }

  // Delete a release from the Datastore 
  public void deleteRelease(Platform platform, String releaseName){
    Key releaseKey = KeyFactory.createKey("Release", platform.getLabel() + "_" + releaseName);
    DATASTORE.delete(releaseKey);
  }           
}

