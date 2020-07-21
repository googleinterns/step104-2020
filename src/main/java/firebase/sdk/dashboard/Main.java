package firebase.sdk.dashboard;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import firebase.sdk.dashboard.data.Release;
import firebase.sdk.dashboard.data.Platform;
import java.time.Instant;
import firebase.sdk.dashboard.dao.PlatformReleaseDaoDatastore;
import java.util.List;

@ApplicationPath("v1")
public class Main extends ResourceConfig {

  public Main() {
    packages("firebase.sdk.dashboard.api");

    PlatformReleaseDaoDatastore platformsDatastore = new PlatformReleaseDaoDatastore();

    String name = "Android";
    Platform releasePlatform = Platform.get(name);
    String releaseManager = "Timothy Mazenge";
    String releaseName = "Ashwin Rhaghav";
    String buganizerHotlistLink = "www.hotlist.com";
    Instant launchDate = Instant.parse("2017-02-03T10:37:30.00Z");
    Instant launchCalDeadline = Instant.parse("2017-02-03T10:37:30.00Z");
    Instant codeFreezeTime = Instant.parse("2017-02-03T10:37:30.00Z");

    Release release = Release.newBuilder()
        .platform(releasePlatform)
        .releaseName(releaseName)
        .releaseManager(releaseManager)
        .buganizerHotlistLink(buganizerHotlistLink)
        .launchCalDeadline(launchCalDeadline)
        .codeFreezeTime(codeFreezeTime)
        .launchDate(launchDate).build();

    platformsDatastore.addRelease(releasePlatform, release);

    //List<Platform> platforms = platformsDatastore.getPlatforms();
   // System.out.println(platforms);
    }
}
