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
import java.util.Map;
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

  public SDK getSDK(Platform platform, String sdkName) {
    Key sdkKey = KeyFactory.createKey("SDK", platform.getLabel() + "_" + sdkName);
    FilterPredicate keyPropertyFilter = makePropertyFilter("__key__", sdkKey);
    Query query = new Query("SDK")
      .setFilter(keyPropertyFilter);
    PreparedQuery preparedQuery = DATASTORE.prepare(query);
    Entity entity = preparedQuery.asSingleEntity();
    if (entity == null) {
      return null;
    }

    SDK sdk = SDK.newBuilder()
      .platform(Platform.get((String) entity.getProperty("platform")))
      .libraryName((String) entity.getProperty("libraryName"))
      .libraryGroup((String) entity.getProperty("libraryGroup"))
      .externalName((String) entity.getProperty("externalName"))
      .fireEscapeName((String) entity.getProperty("fireEscapeName"))
      .owner((String) entity.getProperty("owner"))
      .versionHistory(getVersionHistoryFromProperty((List<EmbeddedEntity>) entity.getProperty("versionHistory")))
      .build();

    return sdk;
  }

  public List<String> getSDKs(Platform platform) {
    FilterPredicate platformPropertyFilter = makePropertyFilter("platform", platform.getLabel());
    Query query = new Query("SDK")
      .setFilter(platformPropertyFilter);

    PreparedQuery preparedQuery = DATASTORE.prepare(query);
    FetchOptions fetchOptions = FetchOptions.Builder.withDefaults();
    QueryResultIterable<Entity> results = preparedQuery.asQueryResultIterable();
    if (results == null) {
      return Arrays.asList();
    }

    ArrayList<String> sdksForPlatform = new ArrayList<>();
    for (Entity entity : results) {
      sdksForPlatform.add(
          (String) entity.getProperty("libraryName"));
    }
    return sdksForPlatform;
  }

  public List<String> getSDKsEnrolledInRelease(Platform platform, String releaseName) {
    FilterPredicate platformPropertyFilter = makePropertyFilter("platform", platform.getLabel());
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
    if (results == null) {
      return Arrays.asList();
    }

    ArrayList<String> enrolledSDKs = new ArrayList<>();
    for (Entity entity : results) {
      String libraryName = (String) entity.getProperty("libraryName");
      enrolledSDKs.add(libraryName);
    }
    return enrolledSDKs;
  }

  public SDKReleaseMetadata getSDKReleaseMetadata(Platform platform, String releaseName, String sdkName) {
    Key sdkReleaseMetadataKey = KeyFactory.createKey("SDKReleaseMetadata", platform.getLabel() + "_" + sdkName + "_" + releaseName);
    FilterPredicate keyPropertyFilter = makePropertyFilter("__key__", sdkReleaseMetadataKey);

    Query query = new Query("SDKReleaseMetadata")
      .setFilter(keyPropertyFilter);

    PreparedQuery preparedQuery = DATASTORE.prepare(query);
    Entity entity = preparedQuery.asSingleEntity();
    if (entity == null) {
      return null;
    }

    SDKReleaseMetadata sdkReleaseMetadata = SDKReleaseMetadata.newBuilder()
      .platform(Platform.get((String) entity.getProperty("platform")))
      .libraryName((String) entity.getProperty("libraryName"))
      .releaseName((String) entity.getProperty("releaseName"))
      .releaseVersion((String) entity.getProperty("releaseVersion"))
      .oldVersion((String) entity.getProperty("oldVersion"))
      .additionalInfo(getAdditionalInfoFromProperty((EmbeddedEntity) entity.getProperty("additionalInfo")))
      .build();

    return sdkReleaseMetadata;
  }

  public void addSDK(SDK sdk) {
    DATASTORE.put(createSDKEntity(sdk));
  }

  public void deleteSDK(Platform platform, String sdkName) {
    Key sdkKey = KeyFactory.createKey("SDK", platform.getLabel() + "_" + sdkName);
    DATASTORE.delete(sdkKey);
  }

  public void addSDKRelease(SDKReleaseMetadata sdk) {
    DATASTORE.put(createSDKReleaseMetadataEntity(sdk));
  }

  public void addSDKVersion(VersionMetadata sdkVersion) {
    EmbeddedEntity versionEntity = createVersionMetadataEmbeddedEntity(sdkVersion);
    Key sdkKey = KeyFactory.createKey("SDK", sdkVersion.platform().getLabel() + "_" + sdkVersion.libraryName());
    FilterPredicate keyPropertyFilter = makePropertyFilter("__key__", sdkKey);

    Query query = new Query("SDK")
      .setFilter(keyPropertyFilter);

    PreparedQuery preparedQuery = DATASTORE.prepare(query);
    Entity entity = preparedQuery.asSingleEntity();
    if (entity == null) {
      //TODO: Throw entityNotFoundException
      return;
    }

    ArrayList<EmbeddedEntity> versions = (ArrayList) entity.getProperty("versionHistory");
    if (versions == null) {
      entity.setProperty("versionHistory", Arrays.asList(versionEntity));
    } else {
      versions.add(0, versionEntity);
      entity.setProperty("versionHistory", versions);
    }
    DATASTORE.put(entity);
  }


  public void deleteSDKRelease(SDKReleaseMetadata sdk) {
    Key sdkReleaseMetadataKey = KeyFactory.createKey("SDKReleaseMetadata", sdk.platform().getLabel() + "_" + sdk.libraryName() + "_" + sdk.releaseName());
    DATASTORE.delete(sdkReleaseMetadataKey);
  }

  public void deleteSDKVersion(VersionMetadata sdkVersion) {
    Key sdkKey = KeyFactory.createKey("SDK", sdkVersion.platform().getLabel() + "_" + sdkVersion.libraryName());
    FilterPredicate keyPropertyFilter = makePropertyFilter("__key__", sdkKey);
    Query query = new Query("SDK")
      .setFilter(keyPropertyFilter);
    PreparedQuery preparedQuery = DATASTORE.prepare(query);
    Entity entity = preparedQuery.asSingleEntity();

    if (entity == null) {
      //TODO: Throw entityNotFoundException
      return;
    }
    ArrayList<EmbeddedEntity> versions = (ArrayList) entity.getProperty("versionHistory");
    if (versions == null) {
      //TODO: Throw entityNotFoundException
      return;
    }
    List<VersionMetadata> versionHistory = getVersionHistoryFromProperty(versions);
    versionHistory.remove(sdkVersion);
    entity.setProperty("versionHistory", createEmbeddedEntityVersionList(versionHistory));
    DATASTORE.put(entity);
  }

  // TODO: P2 functionality
  public void updateSDKEnrolledInRelease(SDKReleaseMetadata sdkRelease) {
    return;
  }

  private List<EmbeddedEntity> createEmbeddedEntityVersionList(List<VersionMetadata> versionHistory) {
    ArrayList<EmbeddedEntity> versionMetadatas = new ArrayList<>();

    for (int i = 0; i < versionHistory.size(); i++) {
      versionMetadatas.add(createVersionMetadataEmbeddedEntity(versionHistory.get(i)));
    }
    return versionMetadatas;
  }

  private Entity createSDKEntity(SDK sdk) {
    Key sdkKey = KeyFactory.createKey("SDK", sdk.platform().getLabel() + "_" + sdk.libraryName());

    List<EmbeddedEntity> versionMetadatas = createEmbeddedEntityVersionList(sdk.versionHistory());

    Entity sdkEntity = new Entity(sdkKey);
    sdkEntity.setProperty("platform", sdk.platform().getLabel());
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
    metadata.setProperty("platform", version.platform().getLabel());
    metadata.setProperty("libraryName", version.libraryName());
    metadata.setProperty("releaseName", version.releaseName());
    metadata.setProperty("version", version.version());
    metadata.setProperty("launchDate", version.launchDate().toEpochMilli());

    return metadata;
  }

  private Entity createSDKReleaseMetadataEntity(SDKReleaseMetadata sdkReleaseMetadata) {
    Key sdkReleaseMetadataKey = KeyFactory.createKey("SDKReleaseMetadata", sdkReleaseMetadata.platform().getLabel() + "_" + sdkReleaseMetadata.libraryName() + "_" + sdkReleaseMetadata.releaseName());

    Entity sdkReleaseEntity = new Entity(sdkReleaseMetadataKey);
    sdkReleaseEntity.setProperty("platform", sdkReleaseMetadata.platform().getLabel());
    sdkReleaseEntity.setProperty("libraryName", sdkReleaseMetadata.libraryName());
    sdkReleaseEntity.setProperty("releaseName", sdkReleaseMetadata.releaseName());
    sdkReleaseEntity.setProperty("releaseVersion", sdkReleaseMetadata.releaseVersion());
    sdkReleaseEntity.setProperty("oldVersion", sdkReleaseMetadata.oldVersion());
    sdkReleaseEntity.setProperty("additionalInfo", createAdditionalInfoEmbeddedEntity(sdkReleaseMetadata.additionalInfo()));

    return sdkReleaseEntity;
  }

  private EmbeddedEntity createAdditionalInfoEmbeddedEntity(HashMap<String, String> additionalInfo) {
    EmbeddedEntity entity = new EmbeddedEntity();
    for (String key : additionalInfo.keySet()) { 
        entity.setProperty(key, additionalInfo.get(key));
    }
    return entity;
  }

  private FilterPredicate makePropertyFilter(String property, Object value) {
    FilterPredicate propertyFilter =
        new FilterPredicate(property, FilterOperator.EQUAL, value);

    return propertyFilter;
  }

  private List<VersionMetadata> getVersionHistoryFromProperty(List<EmbeddedEntity> property) {
    if (property == null) {
      return Arrays.asList();
    }

    ArrayList<VersionMetadata> versionHistory = new ArrayList<>();
    for (EmbeddedEntity entity : property) {
      VersionMetadata versionMetadata = VersionMetadata.newBuilder()
        .platform(Platform.get((String) entity.getProperty("platform")))
        .libraryName((String) entity.getProperty("libraryName"))
        .releaseName((String) entity.getProperty("releaseName"))
        .version((String) entity.getProperty("version"))
        .launchDate(Instant.ofEpochMilli((long) entity.getProperty("launchDate")))
        .build();

      versionHistory.add(versionMetadata);
    }
    return versionHistory;
  }

  private HashMap<String, String> getAdditionalInfoFromProperty(EmbeddedEntity property) {
    HashMap<String, String> additionalInfo = new HashMap<>();
    
    for (Map.Entry<String, Object> entry : property.getProperties().entrySet()) {
      additionalInfo.put(entry.getKey(), (String) entry.getValue());
    }
    return additionalInfo;
  }
}
