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
   * Retrieves the SDK object from a data source.
   *
   * @param platform The sdk's operating platform (ie Android, iOS, etc,).
   * @param libraryName The sdk's name.
   * @return An SDK object containg all the information about the SDK.
   */
  public SDK getSDK(Platform platform, String libraryName);

  /**
   * Retrieves the names of all SDKs that are available for the given platform.
   *
   * @param platform The sdks' operating platform (ie Android, iOS, etc,).
   * @return A list of the names of all the sdks for the given platform.
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
   * Retrieves the SDKRelease object from a data source.
   *
   * @param platform The SDKRelease object's operating platform (ie Android, iOS, etc,).
   * @param releaseName The name of the release the SDKRelease object is enrolled 
   * in (ie M74, M75_hotfix, etc,).
   * @param libraryName The sdk's name.
   * @return An SDKReleaseMetadata object containing all the information about the SDKRelease.
   */
  public SDKReleaseMetadata getSDKReleaseMetadata(Platform platform, String releaseName, String libraryName);

  /**
   * Adds the SDK to the data source.
   *
   * @param sdk The SDK being added.
   */
  public void addSDK(SDK sdk);

  /**
   * Removes the SDK from the data source.
   *
   * @param sdk The SDK being removed.
   */
  public void deleteSDK(SDK sdk);

  /**
   * Adds the SDKReleaseMetadata object to the data source.
   *
   * @param sdkRelease The SDKReleaseMetadata object that is being added.
   */
  public void addSDKRelease(SDKReleaseMetadata sdkRelease);

  /**
   * Deletes the SDKReleaseMetadata object from the data source.
   *
   * @param sdkRelease The SDKReleaseMetadata object that is being deleted.
   */
  public void deleteSDKRelease(SDKReleaseMetadata sdkRelease);

  /**
   * Adds the VersionMetadata object to the data source.
   *
   * @param sdkVersion The VersionMetadata object that is being added.
   */
  public void addSDKVersion(VersionMetadata sdkVersion);

  /**
   * Deletes the VersionMetadata object from the data source.
   *
   * @param sdkVersion The VersionMetadata object that is being deleted.
   */
  public void deleteSDKVersion(VersionMetadata sdkVersion);

  /**
   * Updates the given SDKRelease object and registers the changes in the data 
   * source.
   *
   * @param sdkRelease The SDKRelease after the changes were made to its attributes (ie
   * more additionalInfo).
   */
  // TODO: P2 functionality
  public void updateSDKEnrolledInRelease(SDKReleaseMetadata sdkRelease);
}
