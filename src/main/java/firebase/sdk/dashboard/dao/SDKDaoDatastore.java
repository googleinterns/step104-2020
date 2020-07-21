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
 * An interface for retrieving and manipulating data about SDKs.
 */
public class SDKDaoDatastore implements SDKDao {

  private static final DatastoreService DATASTORE = DatastoreServiceFactory.getDatastoreService();

  public SDK getSDK(Platform givenPlatform, String givenLibraryName) {
    FilterPredicate keyPropertyFilter = makePropertyFilter("__key__", givenPlatform + "_" + givenLibraryName);

    Query query = new Query("SDK")
      .setFilter(keyPropertyFilter);

    PreparedQuery preparedQuery = DATASTORE.prepare(query);
    Entity entity = preparedQuery.asSingleEntity();

    Platform platform = (Platform) entity.getProperty("platform");
    String libraryName = (String) entity.getProperty("libraryName");
    String libraryGroup = (String) entity.getProperty("libraryGroup");
    String externalName = (String) entity.getProperty("externalName");
    String fireEscapeName = (String) entity.getProperty("fireEscapeName");
    String owner = (String) entity.getProperty("owner");
    List<VersionMetadata> versionHistory = getVersionHistoryFromProperty(
        (List<EmbeddedEntity>) entity.getProperty("versionHistory"));

    SDK sdk = SDK.newBuilder()
      .platform(platform)
      .libraryName(libraryName)
      .libraryGroup(libraryGroup)
      .externalName(externalName)
      .fireEscapeName(fireEscapeName)
      .owner(owner)
      .versionHistory(versionHistory)
      .build();

    return sdk;
  }

  public List<String> getSDKs(Platform givenPlatform) {
    FilterPredicate platformPropertyFilter = makePropertyFilter("platform", givenPlatform);

    Query query = new Query("SDK")
      .setFilter(platformPropertyFilter);

    PreparedQuery preparedQuery = DATASTORE.prepare(query);
    FetchOptions fetchOptions = FetchOptions.Builder.withDefaults();
    QueryResultIterable<Entity> results = preparedQuery.asQueryResultIterable();

    ArrayList<String> sdksForPlatform = new ArrayList<>();
    for (Entity entity: results) {
      sdksForPlatform.add(
          (String) entity.getProperty("libraryName"));
    }

    return sdksForPlatform;
  }

  public List<String> getSDKsEnrolledInRelease(Platform platform, String releaseName) {
    FilterPredicate platformPropertyFilter = makePropertyFilter("platform", platform);
    FilterPredicate releasePropertyFilter = makePropertyFilter("releaseName", releaseName);
    CompositeFilter compositeFilter = new CompositeFilter(
        CompositeFilterOperator.AND, Arrays.asList(
          platformPropertyFilter,
          releasePropertyFilter));

    Query query = new Query("SDKReleaseMetadata")
      .setFilter(compositeFilter);

    PreparedQuery preparedQuery = DATASTORE.prepare(query);
    FetchOptions fetchOptions = FetchOptions.Builder.withDefaults();
    QueryResultIterable<Entity> results = preparedQuery.asQueryResultIterable(fetchOptions);

    ArrayList<String> enrolledSDKs = new ArrayList<>();
    for (Entity entity: results) {
      String libraryName = (String) entity.getProperty("libraryName");
      enrolledSDKs.add(libraryName);
    }

    return enrolledSDKs;
  }

  public SDKReleaseMetadata getSDKReleaseMetadata(Platform givenPlatform, String givenReleaseName, String givenLibraryName) {
    FilterPredicate keyPropertyFilter = makePropertyFilter("__key__", givenPlatform + "_" + givenLibraryName + "_" + givenReleaseName);

    Query query = new Query("SDKReleaseMetadata")
      .setFilter(keyPropertyFilter);

    PreparedQuery preparedQuery = DATASTORE.prepare(query);
    Entity entity = preparedQuery.asSingleEntity();

    Platform platform = (Platform) entity.getProperty("platform");
    String libraryName = (String) entity.getProperty("libraryName");
    String releaseName = (String) entity.getProperty("releaseName");
    String releaseVersion = (String) entity.getProperty("releaseVersion");
    String oldVersion = (String) entity.getProperty("oldVersion");
    HashMap<String, String> additionalInfo = (HashMap<String, String>) entity.getProperty("additionalInfo");

    SDKReleaseMetadata sdkReleaseMetadata = SDKReleaseMetadata.newBuilder()
      .platform(platform)
      .libraryName(libraryName)
      .releaseName(releaseName)
      .releaseVersion(releaseVersion)
      .oldVersion(oldVersion)
      .additionalInfo(additionalInfo)
      .build();

    return sdkReleaseMetadata;
  }

  //TODO: Define exception
  public void addSDK(SDK sdk) {
    DATASTORE.put(createSDKEntity(sdk));
  }

  //TODO: Define exception
  public void deleteSDK(SDK sdk) {
    Key sdkKey = KeyFactory.createKey("SDK", sdk.platform() + "_" + sdk.libraryName());
    DATASTORE.delete(sdkKey);
  }

  //TODO: Define exception
  public void addSDKRelease(SDKReleaseMetadata sdk) {
    DATASTORE.put(createSDKReleaseMetadataEntity(sdk));
  }

  public void addSDKVersion(VersionMetadata sdkVersion) {
    EmbeddedEntity versionEntity = createVersionMetadataEmbeddedEntity(sdkVersion);
    FilterPredicate keyPropertyFilter = makePropertyFilter("__key__", sdkVersion.platform() + "_" + sdkVersion.libraryName());

    Query query = new Query("SDK")
      .setFilter(keyPropertyFilter);

    PreparedQuery preparedQuery = DATASTORE.prepare(query);
    Entity entity = preparedQuery.asSingleEntity();

    ArrayList<EmbeddedEntity> versions = (ArrayList<EmbeddedEntity>) entity.getProperty("versions");
    versions.add(0, versionEntity);
    entity.setProperty("versionHistory", versions);
    DATASTORE.put(entity);
  }


  //TODO: Define exception
  public void deleteSDKRelease(SDKReleaseMetadata sdk) {
    Key sdkReleaseMetadataKey = KeyFactory.createKey("SDKReleaseMetadata", sdk.platform() + "_" + sdk.libraryName() + "_" + sdk.releaseName());
    DATASTORE.delete(sdkReleaseMetadataKey);
  }

  //TODO: Define exception
  public void deleteSDKVersion(VersionMetadata sdkVersion) {
    Key sdkVersionKey = KeyFactory.createKey("VersionMetadata", sdkVersion.platform() + "_" + sdkVersion.libraryName() + "_" + sdkVersion.version());
    DATASTORE.delete(sdkVersionKey);
  }

  /* TODO: P2 functionality
     public void updateSDKEnrolledInRelease(SDKRelease oldSDKRelease, SDKRelease newSDKRelease);*/

  private Entity createSDKEntity(SDK sdk) {
    Key sdkKey = KeyFactory.createKey("SDK", sdk.platform() + "_" + sdk.libraryName());

    ArrayList<EmbeddedEntity> versionMetadatas = new ArrayList<>();
    List<VersionMetadata> versionHistory = sdk.versionHistory();

    for (int i = 0; i < versionHistory.size(); i++) {
      versionMetadatas.add(createVersionMetadataEmbeddedEntity(versionHistory.get(i)));
    }

    Entity sdkEntity = new Entity(sdkKey);
    sdkEntity.setProperty("platform", sdk.platform());
    sdkEntity.setProperty("libraryName", sdk.libraryName());
    sdkEntity.setProperty("libraryGroup", sdk.libraryGroup());
    sdkEntity.setProperty("externalName", sdk.externalName());
    sdkEntity.setProperty("fireEscapeName", sdk.fireEscapeName());
    sdkEntity.setProperty("owner", sdk.owner());
    sdkEntity.setProperty("versionHistory", versionMetadatas);

    return sdkEntity;
  }

  private EmbeddedEntity createVersionMetadataEmbeddedEntity(VersionMetadata version) {
    EmbeddedEntity metadata = new EmbeddedEntity();
    Key versionKey = KeyFactory.createKey("VersionMetadata", version.platform() + "_" + version.libraryName() + "_" + version.version());
    metadata.setKey(versionKey);
    metadata.setProperty("platform", version.platform());
    metadata.setProperty("libraryName", version.libraryName());
    metadata.setProperty("releaseName", version.releaseName());
    metadata.setProperty("version", version.version());
    metadata.setProperty("launchDate", version.launchDate());
    return metadata;
  }

  private Entity createSDKReleaseMetadataEntity(SDKReleaseMetadata sdkReleaseMetadata) {
    Key sdkReleaseMetadataKey = KeyFactory.createKey("SDKReleaseMetadata", sdkReleaseMetadata.platform() + "_" + sdkReleaseMetadata.libraryName() + "_" + sdkReleaseMetadata.releaseName());

    Entity sdkReleaseEntity = new Entity(sdkReleaseMetadataKey);
    sdkReleaseEntity.setProperty("platform", sdkReleaseMetadata.platform());
    sdkReleaseEntity.setProperty("libraryName", sdkReleaseMetadata.libraryName());
    sdkReleaseEntity.setProperty("releaseName", sdkReleaseMetadata.releaseName());
    sdkReleaseEntity.setProperty("releaseVersion", sdkReleaseMetadata.releaseVersion());
    sdkReleaseEntity.setProperty("oldVersion", sdkReleaseMetadata.oldVersion());
    sdkReleaseEntity.setProperty("additionalInfo", sdkReleaseMetadata.additionalInfo());

    return sdkReleaseEntity;
  }

  private FilterPredicate makePropertyFilter(String property, Object value) {
    FilterPredicate propertyFilter =
        new FilterPredicate(property, FilterOperator.EQUAL, value);

    return propertyFilter;
  }

  private List<VersionMetadata> getVersionHistoryFromProperty(List<EmbeddedEntity> property) {
    ArrayList<VersionMetadata> versionHistory = new ArrayList<>();
    for(EmbeddedEntity entity: property) {
      Platform platform = (Platform) entity.getProperty("platform");
      String libraryName = (String) entity.getProperty("libraryName");
      String releaseName = (String) entity.getProperty("releaseName");
      String version = (String) entity.getProperty("version");
      Instant launchDate = (Instant) entity.getProperty("launchDate");

      VersionMetadata versionMetadata = VersionMetadata.newBuilder()
        .platform(platform)
        .libraryName(libraryName)
        .releaseName(releaseName)
        .version(version)
        .launchDate(launchDate)
        .build();

      versionHistory.add(versionMetadata);
    }

    return versionHistory;
  }
}
