package firebase.sdk.dashboard.dao;

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
import com.google.appengine.api.datastore.DatastoreServiceFactory;
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
import com.google.appengine.api.datastore.QueryResultIterable;

/**
 * A class for implementing the platform dao.
 */
public class PlatformReleaseDaoDatastore implements PlatformReleaseDao {

    public static final DatastoreService DATASTORE = DatastoreServiceFactory.getDatastoreService();

    // Get a list of platforms from the datastore
    public List<Platform> getPlatforms(){
        List<Platform> platforms = new ArrayList<>();
        
        Query query = new Query("Platform");
        PreparedQuery results = DATASTORE.prepare(query);
        QueryResultIterable<Entity> entities = results.asQueryResultIterable();

        for (Entity result : entities) {
            String name = (String) result.getProperty("name");
            Platform platform = Platform.get(name);
            platforms.add(platform);
        }
        return platforms;  
    }

    // Get a list of releases for a platform from the Datastore
    public List<Release> getPlatformReleases(Platform platform) {
        List<Release> releases = new ArrayList<>();
        Query query = new Query("Release");
    
        PreparedQuery preparedQuery = DATASTORE.prepare(query);
        QueryResultIterable<Entity> releaseQuery = preparedQuery.asQueryResultIterable();

        for (Entity releaseEntity : releaseQuery) {
            String name = (String) releaseEntity.getProperty("platform");
            Platform releasePlatform = Platform.get(name);
            String releaseManager = (String) releaseEntity.getProperty("releaseManager");
            String releaseName = (String) releaseEntity.getProperty("releaseName");
            String buganizerHotlistLink = (String) releaseEntity.getProperty("buganizerHotlistLink");
            Instant launchDate = Instant.ofEpochMilli((Long) releaseEntity.getProperty("launchDate"));
            Instant launchCalDeadline = Instant.ofEpochMilli((Long) releaseEntity.getProperty("launchCalDeadline"));
            Instant codeFreezeTime = Instant.ofEpochMilli((Long) releaseEntity.getProperty("codeFreezeTime"));
           
            Release release = Release.newBuilder()
                .platform(releasePlatform)
                .releaseName(releaseName)
                .releaseManager(releaseManager)
                .buganizerHotlistLink(buganizerHotlistLink)
                .launchCalDeadline(launchCalDeadline)
                .codeFreezeTime(codeFreezeTime)
                .launchDate(launchDate).build();
            if (releasePlatform == platform) {
                releases.add(release);
            } 
        }   
        return releases;
    }

    // Add release to the Datastore
    public void addRelease(Platform platform, Release release){
        Key releaseKey = KeyFactory.createKey("Release", platform.getLabel() + "_" + release.releaseName());

        // Create a release entity
        Entity releaseEntity = new Entity(releaseKey);
        releaseEntity.setProperty("platform", platform.getLabel());
        releaseEntity.setProperty("releaseName", release.releaseName());
        releaseEntity.setProperty("releaseManager", release.releaseManager());
        releaseEntity.setProperty("launchDate", release.launchDate().toEpochMilli());
        releaseEntity.setProperty("buganizerHotlistLink", release.buganizerHotlistLink());
        releaseEntity.setProperty("launchCalDeadline", release.launchCalDeadline().toEpochMilli());
        releaseEntity.setProperty("codeFreezeTime", release.codeFreezeTime().toEpochMilli());
        
        DATASTORE.put(releaseEntity);
    }

    // Delete release from the Datastore 
    public void deleteRelease(Release release){
        Key releaseKey = KeyFactory.createKey("Release", release.platform().getLabel() + "_" + release.releaseName());
        DATASTORE.delete(releaseKey);
    }           
}



