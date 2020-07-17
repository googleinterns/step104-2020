package firebase.sdk.dashboard.dao;


import firebase.sdk.dashboard.data.Release;
import firebase.sdk.dashboard.data.SDKReleaseMetadata;
import firebase.sdk.dashboard.data.Platform;
import java.util.*;
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
    
    public static final dashboardDatastore = DatastoreServiceFactory.getDatastoreService();

    private Map<String, Platform> enumMapping = new HashMap<String, Platform>();
    enumMapping.put("iOS", IOS);
    enumMapping.put("Web", WEB);
    enumMapping.put("Games", GAMES);
    enumMapping.put("Android", ANDROID);

    public List<Platform> getPlatforms(){
        List<Platform> platforms = new ArrayList<>();
        
        Query<Entity> query = Query.newEntityQueryBuilder().setKind("Platform").build();
        
        PreparedQuery results = dashboardDatastore.prepare(query);

        for (Entity result : results.asIterable()) {
            String name = result.getProperty("name");
            Platform platform = enumMapping.get(name);
            platforms.add(platform);
        }
        return platforms;  
    }

    public List<Release> getPlatformReleases(Platform platform) {
        List<Release> releases = new ArrayList<>();

        Query<Entity> query = Query.newEntityQueryBuilder().setKind("Release").setFilter(PropertyFilter.eq("platform", platform))build();
        PreparedQuery releaseQuery = dashboardDatastore.prepare(query);

        for (Entity releaseEntity : releaseQuery.asIterable()) {

            /* setproperty filter*/
            String name = resultEntity.getProperty("platform");
            Platform platform = enumMapping.get(name);

            String releaseName = resultEntity.getProperty("releaseName");
            String launchDate = resultEntity.getProperty("launchDate");
            String launchCalDeadline = resultEntity.getProperty("launchCalDeadline");
            String codeFreezeTime = resultEntity.getProperty("codeFreezeTime");
            /*how to create a release o*/

            Release release = Release.newBuilder()
                .releaseName(releaseName)
                .launchCalDeadline(launchCalDeadline)
                .codeFreezeTime(codeFreezeTime)
                .launchDate(launchDate).build();
            releases.add(release);
        }
        return release;
    }

    public void addRelease(Platform platform, Release release){
        String platformString;

         for (String key : enumMapping.keySet()) {
            Platform value = enumMapping.get(key);
            if (value == release.platform()) {
                platformString = key;
            }
        }

        Key releaseKey = dashboardDatastore.newKeyFactory()
            .setKind("Release")
            .newKey(release.releaseName() + platformString);
        
        Entity release = Entity.newBuilder(releaseKey)
            .set("platform", platformString)
            .set("releaseName", release.releaseName())
            .set("releaseManager", release.releaseManager())
            .set("launchDate", release.launchDate())
            .set("buganizerHotlistLink", release.buganizerHotlistLink())
            .set("launchCalDeadline", release.launchCalDeadline())
            .set("codeFreezeTime", release.codeFreezeTime())
            .build();

        dashboardDatastore.put(release);
    }

    public void deleteRelease(Platform platform, String releaseName){
        String platformString;

        for (String key : enumMapping.keySet()) {
            Platform value = enumMapping.get(key);
            if (value == release.platform()) {
                platformString = key;
            }
        }
        
        dashboardDatastore.delete(releaseName + platformString);
    }



