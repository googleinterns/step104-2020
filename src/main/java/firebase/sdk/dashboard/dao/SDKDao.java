package firebase.sdk.dashboard.dao;

import firebase.sdk.dashboard.data.SDK;
import firebase.sdk.dashboard.data.SDKReleaseMetadata;
import firebase.sdk.dashboard.data.VersionMetadata;
import firebase.sdk.dashboard.data.Release;
import firebase.sdk.dashboard.data.Platform;
import java.util.List;

/**
 * An interface for retrieving and manipulating data about SDKs.
 */
public interface SDKDao {

  /**
   * Retrieves the SDK object from a datasource.
   *
   * @param platform The sdk's operating platform (ie Android, iOS, etc,).
   * @param libraryName The sdk's name.
   * @return An SDK object containg all the information about the SDK from the 
   * data source.
   */
  public SDK getSDK(Platform platform, String libraryName);

  /**
   * Retrieves the names of all SDK objects in the given platform from a datasource.
   *
   * @param platform The sdks' operating platform (ie Android, iOS, etc,).
   * @return An SDK object containg all the information about the SDK from the 
   * data source.
   */
  public List<String> getSDKs(Platform platform);

  /**
   * Retrieves the names of all the sdks enrolled in the given release.
   *
   * @param platform The SDKRelease object's operating platform (ie Android, iOS, etc,).
   * @param releaseName The name of the release the SDKRelease object is enrolled 
   * in (ie M74, M75_hotfix, etc,).
   * @return A list of the names of all the sdks enrolled in the requested release.
   */
  public List<String> getSDKsEnrolledInRelease(Platform platform, String releaseName);

  /**
   * Retrieves the SDKRelease object from a datasource.
   *
   * @param platform The SDKRelease object's operating platform (ie Android, iOS, etc,).
   * @param releaseName The name of the release the SDKRelease object is enrolled 
   * in (ie M74, M75_hotfix, etc,).
   * @param libraryName The sdk's name.
   * @return An SDKRelease object containing all the information about the SDKRelease from
   * the data source.
   */
  public SDKReleaseMetadata getSDKReleaseMetadata(Platform platform, String releaseName, String libraryName);

  //TODO: Define exception
  /**
   * Adds the SDK from the data source.
   *
   * @param sdk The SDK being added.
   */
  public void addSDK(SDK sdk);

  //TODO: Define exception
  /**
   * Removes the SDK from the data source.
   *
   * @param sdk The SDK being removed.
   */
  public void deleteSDK(SDK sdk);

  //TODO: Define exception
  /**
   * Adds the SDKReleaseMetadata object to the datasource.
   *
   * @param sdk The SDKReleaseMetadata object that is being added.
   */
  public void addSDKRelease(SDKReleaseMetadata sdk);

  //TODO: Define exception
  /**
   * Deletes the SDKReleaseMetadata object from the datasource.
   *
   * @param sdk The SDKReleaseMetadata object that is being deleted from the 
   * data source.
   */
  public void deleteSDKRelease(SDKReleaseMetadata sdk);

  //TODO: Define exception
  /**
   * Adds the VersionMetadata object to the datasource.
   *
   * @param sdk The VersionMetadata object that is being added.
   */
  public void addSDKVersion(VersionMetadata sdkVersion);

  //TODO: Define exception
  /**
   * Deletes the VersionMetadata object from the datasource.
   *
   * @param sdk The VersionMetadata object that is being deleted from the 
   * data source.
   */
  public void deleteSDKVersion(VersionMetadata sdkVersion);

  /**
   * Updates the given SDKRelease object and registers the changes in the data 
   * source.
   *
   * @param oldSDKRelease The SDKRelease before the changes were made.
   * @param newSDKRelease The SDKRelease after the changes were made.
   * @return Whether or not the SDKRelease was successfully modified.
   */
  /* TODO: P2 functionality
  public void updateSDKEnrolledInRelease(SDKRelease oldSDKRelease, SDKRelease newSDKRelease);*/
}
