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

    public List<Platform> getPlatforms(){
        List<Platform> platforms = new ArrayList<>();
        
        Query<Entity> query = Query.newEntityQueryBuilder().setKind("Platform").build();
        
        QueryResults<Entity> results = dashboardDatastore.run(query);

        for (Entity result : results.asIterable()) {
            String name = result.getProperty("name");
            Platform platform = Platform(name);
            platforms.add(platform);
        }
        return platforms;  
    }

    public List<Release> getPlatformReleases(Platform platform) {
        List<Release> releases = new ArrayList<>();

        Query<Entity> query = Query.newEntityQueryBuilder().setKind("Release").setFilter(PropertyFilter.eq("platform", platform)).build();
        QueryResults<Entity> releaseQuery = dashboardDatastore.run(query);

        for (Entity releaseEntity : releaseQuery.asIterable()) {

            /*get clarification*/
            String name = resultEntity.getProperty("platform");
            Platform platform = Platform(name);

            String releaseManager = releaseEntity.getProperty("releaseManager");
            String releaseName = resultEntity.getProperty("releaseName");
            String buganizerHotlistLink = resultEntity.getProperty("buganizerHotlistLink");
            String launchDate = resultEntity.getProperty("launchDate");
            String launchCalDeadline = resultEntity.getProperty("launchCalDeadline");
            String codeFreezeTime = resultEntity.getProperty("codeFreezeTime");
           
            Release release = Release.newBuilder()
                .platform(platform)
                .releaseName(releaseName)
                .releaseManager(releaseManager)
                .buganizerHotlistLink(buganizerHotlistLink)
                .launchCalDeadline(launchCalDeadline)
                .codeFreezeTime(codeFreezeTime)
                .launchDate(launchDate).build();
            releases.add(release);
        }
        return release;
    }

    public void addRelease(Platform platform, Release release){
        
        Key releaseKey = dashboardDatastore.newKeyFactory()
            .setKind("Release")
            .newKey(release.releaseName() + platform.getName());
        
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
        String platformString = platform.getName();
        dashboardDatastore.delete(releaseName + platformString);
    }
        
        
}



