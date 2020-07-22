package firebase.sdk.dashboard;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import java.util.*;
import java.time.Instant;
import firebase.sdk.dashboard.data.*;
import firebase.sdk.dashboard.dao.SDKDao;
import firebase.sdk.dashboard.dao.SDKDaoDatastore;

@ApplicationPath("v1")
public class Main extends ResourceConfig {

  private static SDKDao dao = new SDKDaoDatastore();

  public Main() {
    packages("firebase.sdk.dashboard.api");

    // TODO: Delete all of this testing code
    VersionMetadata version1 = VersionMetadata.newBuilder()
      .libraryName("firebase-common")
      .platform(Platform.ANDROID)
      .releaseName("M76")
      .version("19.3.0")
      .launchDate(Instant.now())
      .build();

    VersionMetadata version2 = VersionMetadata.newBuilder()
      .libraryName("firebase-common")
      .platform(Platform.ANDROID)
      .releaseName("M78")
      .version("19.3.1")
      .launchDate(Instant.now())
      .build();


    SDK sdk1 = SDK.newBuilder()
      .platform(Platform.ANDROID)
      .libraryName("firebase-common")
      .libraryGroup("firebase_common")
      .externalName("firebase-common")
      .fireEscapeName("firebase-common")
      .owner("ashwinraghav@")
      .versionHistory(Arrays.asList())
      .build();

    dao.addSDK(sdk1);
    System.out.printf("Added SDK %s to %s platform in the datastore.\n", sdk1.libraryName(), sdk1.platform());

    SDK sdk = SDK.newBuilder()
      .platform(Platform.ANDROID)
      .libraryName("firebase-common-ktx")
      .libraryGroup("firebase_common")
      .externalName("firebase-common-ktx")
      .fireEscapeName("firebase-common:ktx")
      .owner("ashwinraghav@")
      .versionHistory(Arrays.asList())
      .build();

    dao.addSDK(sdk);
    System.out.printf("Added SDK %s to %s platform in the datastore.\n", sdk.libraryName(), sdk.platform());

    HashMap<String, String> additionalInfo = new HashMap<>();
    additionalInfo.put("releaseNotesCL", "cl/12345678");

    SDKReleaseMetadata sdkRelease = SDKReleaseMetadata.newBuilder()
      .libraryName("firebase-common")
      .platform(Platform.ANDROID)
      .releaseName("M78")
      .releaseVersion("19.3.1")
      .oldVersion("19.3.0")
      .additionalInfo(new HashMap<>())
      .build();

    System.out.println(dao.getSDKs(Platform.ANDROID));
    System.out.println(dao.getSDK(Platform.ANDROID, "firebase-common"));
    dao.deleteSDK(sdk);
    System.out.println(dao.getSDKs(Platform.ANDROID));
    System.out.println(dao.getSDK(Platform.ANDROID, "firebase-common-ktx"));
    
    System.out.printf("Getting sdks enrolled in a release: %s\n", dao.getSDKsEnrolledInRelease(Platform.ANDROID, "M77"));
    dao.addSDKRelease(sdkRelease);
    dao.addSDKVersion(version1);
    dao.addSDKVersion(version2);

    System.out.printf("Getting sdks enrolled in a release: %s\n", dao.getSDKsEnrolledInRelease(Platform.ANDROID, "M78"));
    System.out.printf("Retrieving a specific sdk: %s\n", dao.getSDKReleaseMetadata(Platform.ANDROID, "M78", "firebase-common"));
    System.out.println(dao.getSDK(Platform.ANDROID, "firebase-common"));

    dao.deleteSDKVersion(version2);
    dao.deleteSDKRelease(sdkRelease);
    dao.deleteSDKVersion(version1);
    System.out.printf("Getting sdks enrolled in release after deletion: %s\n", dao.getSDKsEnrolledInRelease(Platform.ANDROID, "M78"));
    System.out.printf("Retrieving a specific sdk releasemetadata after deletion: %s\n", dao.getSDKReleaseMetadata(Platform.ANDROID, "M78", "firebase-common"));
    System.out.println(dao.getSDK(Platform.ANDROID, "firebase-common"));
  }
}
