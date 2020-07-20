package firebase.sdk.dashboard.dao;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.QueryResultList;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.KeyFactory.Builder;
import firebase.sdk.dashboard.data.Release;
import firebase.sdk.dashboard.data.SDKReleaseMetadata;
import firebase.sdk.dashboard.data.Platform;
import java.util.*;
import java.io.IOException;


/**
 * A class for implementing the platform dao.
 */
public class PlatformReleaseDaoDatastore implements PlatformReleaseDao {

    HashMap<String, Platform> enumMapping = new HashMap<>();
    for(Platform plat : Platform.values()){
        enumMapping.put(plat.getLabel(), plat);
    }

    DatastoreService dashboardDatastore = DatastoreServiceFactory.getDatastoreService();

    public List<Platform> getPlatforms(){
        List<Platform> platforms = new ArrayList<>();
        
        Query<Entity> query = Query.newEntityQueryBuilder().setKind("Platform").build();
        QueryResultList<Entity> results = dashboardDatastore.run(query);

        for (Entity result : results.asIterable()) {
            String name = result.getProperty("name");
            Platform platform = enumMapping.get(name);
            platforms.add(platform);
        }
        return platforms;  
    }

    public List<Release> getPlatformReleases(Platform platform) {
        List<Release> releases = new ArrayList<>();

        Query<Entity> query = Query.newEntityQueryBuilder().setKind("Release").setFilter(PropertyFilter.eq("platform", platform.getLabel())).build();
        QueryResultList<Entity> releaseQuery = dashboardDatastore.run(query);

        for (Entity releaseEntity : releaseQuery.asIterable()) {

            /*get clarification*/
            String name = (String) releaseEntity.getProperty("platform");
            Platform platform = enumMapping.get(name);
            String releaseManager = (String) releaseEntity.getProperty("releaseManager");
            String releaseName = (String) releaseEntity.getProperty("releaseName");
            String buganizerHotlistLink = (String) releaseEntity.getProperty("buganizerHotlistLink");
            Instant launchDate = releaseEntity.getProperty("launchDate");
            Instat launchCalDeadline = releaseEntity.getProperty("launchCalDeadline");
            String codeFreezeTime = releaseEntity.getProperty("codeFreezeTime");
           
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
        return releases;
    }

    public void addRelease(Platform platform, Release release){
        
        Key releaseKey = dashboardDatastore.newKeyFactory()
            .setKind("Release")
            .newKey(release.releaseName() + platform.getLabel());
        
        Entity relEntity = Entity.newBuilder(releaseKey)
            .set("platform", platform.getName())
            .set("releaseName", release.releaseName())
            .set("releaseManager", release.releaseManager())
            .set("launchDate", release.launchDate())
            .set("buganizerHotlistLink", release.buganizerHotlistLink())
            .set("launchCalDeadline", release.launchCalDeadline())
            .set("codeFreezeTime", release.codeFreezeTime())
            .build();

        dashboardDatastore.put(relEntity);
    }

    public void deleteRelease(Platform platform, String releaseName){
        String platformString = platform.getLabel();
        dashboardDatastore.delete(releaseName + platformString);
    }           
}



