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

    private Map<String, Platform> enumMapping = new HashMap<String, Platform>();
    enumMapping.put("iOS", IOS);
    enumMapping.put("Web", WEB);
    enumMapping.put("Games", GAMES);
    enumMapping.put("Android", ANDROID);

    public List<Platform> getPlatforms(){
        List<Platform> platforms = new ArrayList<>();
        

        Query<Entity> query = Query.newEntityQueryBuilder().setKind("Platform").build();
        
        PreparedQuery results = datastore.prepare(query);

        for (Entity result : results.asIterable()) {
            String name = result.getProperty("name");
            Platform platform = enumMapping.get(name);
            platforms.add(platform);
        }
        return platforms;  
    }

    public List<Release> getPlatformReleases(Platform platform) {
        List<Release> releases = new ArrayList<>();

        Query<Entity> query = Query.newEntityQueryBuilder().setKind("Release").build();
        PreparedQuery releaseQuery = datastore.prepare(query);

        for (Entity releaseEntity : releaseQuery.asIterable()) {

            /* setproperty filter*/
            String name = resultEntity.getProperty("platform");

            Platform platform = enumMapping.get(name);
            String releaseName = resultEntity.getProperty("releaseName");
            String releaseManager = resultEntity.getProperty("release-manger");
            String buganizerHotlistLink = resultEntity.getProperty("buganizerHotlistLink");
            String launchCalDeadline = resultEntity.getProperty("launchCalDeadline");
            String codeFreezeTime = resultEntity.getProperty("codeFreezeTime");
        }


    }

    public void addRelease(Platform platform, Release release){

    }

    public void deleteRelease(Platform platform, String releaseName){

    }



  public abstract Platform platform();

  public abstract String releaseName();

  public abstract String releaseManager();

  public abstract String buganizerHotlistLink();

  /* launchCalDeadline is the date by which an Ariane launch entry should be approved
   * for minor/major versions. */
  public abstract Instant launchCalDeadline();

  /* codeFreezeTime is when they cut off a branch to create a release candidate
   * for enrolled SDKS which will later be tested by the SDK owners. Any code
   * that is merged into the master branch after the code freeze date is not
   * released ad part of that launch. */
  public abstract Instant codeFreezeTime();



