package firebase.sdk.dashboard.dao;

import firebase.sdk.dashboard.data.SDK;
import firebase.sdk.dashboard.data.SDKReleaseMetadata;
import firebase.sdk.dashboard.data.Release;
import firebase.sdk.dashboard.data.Platform;
import java.util.List;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

/**
 * An interface for retrieving and manipulating data about SDKs.
 */
public class SDKDaoDatastore implements SDKDao {

  private static final DatastoreService DATASTORE = DatastoreServiceFactory.getDatastoreService();

  public SDK getSDK(Platform platform, String libraryName) {
    Query<Entity> query = Query.newEntityQueryBuilder().setKind("SDK")
      .setFilter(PropertyFilter.eq("__key__", platform + "_" + libraryName))
      .build();

    PreparedQuery preparedQuery = DATASTORE.prepare(query);
    QueryResultList<Entity> results = preparedQuery.asQueryResultList();

    Entity entity = results.get(0);
    Platform platform = (Platform) entity.getProperty("platform");
    String libraryName = (String) entity.getProperty("libraryName");
    String libraryGroup = (String) entity.getProperty("libraryGroup");
    String externalName = (String) entity.getProperty("externalName");
    String fireEscapeName = (String) entity.getProperty("fireEscapeName");
    String owner = (String) entity.getProperty("owner");
    List<VersionMetadata> versionHistory = getVersionHistoryFromEntity(entity.getProperty("versionHistory"));
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

  public List<String> getSDKsEnrolledInRelease(Platform platform, String releaseName);

  public SDKReleaseMetadata getSDKReleaseMetadata(Platform platform, String releaseName, String libraryName) {
    Query<Entity> query = Query.newEntityQueryBuilder().setKind("SDKReleaseMetadata")
      .setFilter(PropertyFilter.eq("__key__", platform + "_" + libraryName + "_" + releaseName))
      .build();

    PreparedQuery preparedQuery = DATASTORE.prepare(query);
    QueryResultList<Entity> results = preparedQuery.asQueryResultList();

    Entity entity = results.get(0);
    Platform platform = (Platform) entity.getProperty("platform");
    String libraryName = (String) entity.getProperty("libraryName");
    String releaseName = (String) entity.getProperty("releaseName");
    String releaseVersion = (String) entity.getProperty("releaseVersion");
    String oldVersion = (String) entity.getProperty("oldVersion");
    HashMap<String, String> additionalInfo = (HashMap<String, String>) entity.getProperty("additionalInfo");
    sDKReleaseMetadata sdkReleaseMetadata = SDKReleaseMetadata.newuBuilder()
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

  //TODO: Define exception
  public void addSDK(Platform platform, SDK sdk) throws IOException {
    DATASTORE.put(createSDKEntity(sdk));
  }

  //TODO: Define exception
  public void deleteSDK(SDK sdk) throws IOException {
    DATASTORE.delete(sdk.platform() + "_" + sdk.libraryName());
  }

  //TODO: Define exception
  public void addSDKRelease(SDKReleaseMetadata sdk) {
    DATASTORE.put(createSDKReleaseMetadataEntity(sdk));
  }


  //TODO: Define exception
  public void deleteSDKRelease(SDKReleaseMetadata sdk) {
    DATASTORE.delete(sdk.platform() + "_" sdk.libraryName() + "_" + sdk.releaseName());
  }

  /* TODO: P2 functionality
     public void updateSDKEnrolledInRelease(SDKRelease oldSDKRelease, SDKRelease newSDKRelease);*/

  private Entity createSDKEntity(SDK sdk) {
    Key sdkKey = DATASTORE.newKeyFactory()
      .addAncestors(PathElement.of("Platforms", sdk.platform()))
      .setKind("SDK")
      .newKey(sdk.platform() + "_" + sdk.libraryName());

    ArrayList<EmbeddedEntity> versionMetadatas = new ArrayList<>();
    ArrayList<VersionMetadata> versionHistory = sdk.versionHistory();

    for (int i = 0; i < versionHistory.size(); i++) {
      versionMetadatas.add(createVersionMetadataEmbeddedEntity(versionHistory.get(i)));
    }

    Entity sdkEntity = Entity.newBuilder(sdkKey)
      .set("platform", sdk.platform())
      .set("libraryName", sdk.libraryName())
      .set("libraryGroup", sdk.libraryGroup())
      .set("externalName", sdk.externalName())
      .set("fireEscapeName", sdk.fireEscapeName())
      .set("owner", sdk.owner())
      .set("versionHistory", versionMetadatas);

    return sdkEntity;
  }

  private EmbeddedEntity createVersionMetadataEmbeddedEntity(VersionMetadata version) {
    EmbeddedEntity metadata = new EmbeddedEntity();
    metadata.setKey(version.platform() + "_" + version.libraryName() + "_" + version.version());
    metadata.setProperty("platform", version.platform());
    metadata.setProperty("libraryName", version.libraryName());
    metadata.setProperty("releaseName", version.releaseName());
    metadata.setProperty("version", version.version());
    metadata.setProperty("launchDate", version.launchDate());
    return metadata;
  }

  private Entity createSDKReleaseMetadataEntity(SDKReleaseMetadata sdkReleaseMetadata) {
    Key sdkReleaseMetadataKey = DATASTORE.newKeyFactory()
      .addAncestors(PathElement.of("Platforms", sdkReleaseMetadata.platform()))
      .setKind("SDKReleaseMetadata")
      .newKey(sdkreleaseMetadata.platform() + "_" + sdkreleaseMetadata.libraryName() + "_" + sdkreleaseMetadata.releaseName());
    Entity sdkReleaseEntity = Entity.newBuilder(sdkReleaseMetadataKey)
      .set("platform", version.platform())
      .set("libraryName", version.libraryName())
      .set("releaseName", version.releaseName())
      .set("releaseVersion", version.version())
      .set("oldVersion", version.oldVersion())
      .set("additionInfo", version.additionInfo());

    return sdkReleaseEntity;
  }
}
