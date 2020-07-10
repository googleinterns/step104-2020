package firebase.sdk.dashboard.dao;

import firebase.sdk.dashboard.data.SDK;
import firebase.sdk.dashboard.data.SDKReleaseMetadata;
import firebase.sdk.dashboard.data.Release;
import firebase.sdk.dashboard.data.Platform;

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
  public SDK getSDK(String platform, String libraryName);

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
  public SDKReleaseMetadata getSDKReleaseMetadata(String platform, String releaseName, String libraryName);

  /**
   * Adds the SDK from the data source.
   *
   * @param platform The platform the sdk is being added to.
   * @param sdk The SDK being added.
   * @return Whether or not the SDK object was successfully added to the
   * data source.
   */
  public boolean addSDK(Platform platform, SDK sdk);

  /**
   * Removes the SDK from the data source.
   *
   * @param sdk The SDK being removed.
   * @return Whether or not the SDK object was successfully removed from the
   * data source.
   */
  public boolean deleteSDK(SDK sdk);

  /**
   * Enrolls the given SDKRelease object into the given Release by adding it
   * to the Release objects enrolledSDKs HashMap and registers that
   * enrollment by updating the data source with the new data.
   *
   * @param release The Release object that the sdk is being enrolled in.
   * @param sdk The SDKReleaseMetadata object that is being enrolled.
   * @return Whether or not the SDKRelease was successfully enrolled in the 
   * Release.
   */
  public boolean enrollSDKInRelease(Release release, SDKReleaseMetadata sdk);

  /**
   * Unenrolls the given SDKReleaseMetadata object from the given Release object by 
   * removing it from the Release objects enrolledSDKs HashMap and
   * registers the unenrollment by updating the data source with the new data.
   *
   * @param release The Release obhect that the sdk is being unenrolled from.
   * @param sdk The SDKReleaseMetadata object that is being unenrolled.
   * @return Whether or not the SDKRelease was successfully unenrolled from the 
   * Release.
   */
  public boolean unenrollSDKInRelease(Release release, SDKReleaseMetadata sdk);

  /**
   * Updates the given SDKRelease object and registers the changes in the data 
   * source.
   *
   * @param oldSDKRelease The SDKRelease before the changes were made.
   * @param newSDKRelease The SDKRelease after the changes were made.
   * @return Whether or not the SDKRelease was successfully modified.
   */
  /* TODO: P2 functionality
  public boolean updateSDKEnrolledInRelease(SDKRelease oldSDKRelease, SDKRelease newSDKRelease);*/
}
