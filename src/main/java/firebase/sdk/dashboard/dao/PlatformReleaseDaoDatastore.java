package firebase.sdk.dashboard.dao;


import firebase.sdk.dashboard.data.Release;
import firebase.sdk.dashboard.data.SDKReleaseMetadata;
import firebase.sdk.dashboard.data.Platform;
import java.util.List;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;


/**
 * A class for implementing the platform dao.
 */
public class PlatformReleaseDaoDatastore implements PlatformReleaseDao {
    
    public static final platformDatastore = DatastoreServiceFactory.getDatastoreService();

    public List<Platform> getPlatforms(){

        List<Platform> platforms = new new ArrayList<>();
        Query<Entity> query = Query.newEntityQueryBuilder().setKind("Platform").build();
        
        PreparedQuery results = datastore.prepare(query);

        for (Entity result : results.asIterable()) {
            String  = result.getProperty("name");
        }
        return platforms;  
    }

    public List<Release> getPlatformReleases(Platform platform) {

    }

    public void addRelease(Platform platform, Release release){

    }

    public void deleteRelease(Platform platform, String releaseName){

    }
}
