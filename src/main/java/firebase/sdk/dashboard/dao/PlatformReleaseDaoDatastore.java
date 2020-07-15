package firebase.sdk.dashboard.dao;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import firebase.sdk.dashboard.data.User;
import firebase.sdk.dashboard.data.Release;
import firebase.sdk.dashboard.data.SDKReleaseMetadata;
import firebase.sdk.dashboard.data.Platform;
import firebase.sdk.dashboard.data.Platform;
import java.util.List;

/**
 * An platform release dao implementation. 
 */
public class PlaformReleaseDaoDatastore implements PlaformReleaseDao {

    DatastoreService platformDatastore = DatastoreServiceFactory.getDatastoreService();

  
    public List<Platform> getPlatforms(){

        List<Platform> platforms = new ArrayList<>();
        Query<Entity> query = Query.newEntityQueryBuilder().setKind("Platform").build();
        PreparedQuery results = platformDatastore.prepare(query);

        for (Entity result : results.asIterable()) {
            Platform platformName = result.getProperty("platform");
            platforms.add(platformName);
        }
        return platforms;
    }
   

    public List<Release> getPlatformReleases(Platform platform){

    }

 
    public void addRelease(Platform platform, Release release){

    }

   
    public void deleteRelease(Platform platform, String releaseName){

    }
}


