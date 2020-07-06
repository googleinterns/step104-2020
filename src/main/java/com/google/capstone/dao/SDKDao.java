package com.google.capstone.dao;

import com.google.capstone.data.SDK;
import com.google.capstone.data.SDKRelease;

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
  public SDKRelease getSDKRelease(String platform, String releaseName, String libraryName);

  /**
   * Removes the SDK from the data source.
   *
   * @param sdk The SDK being removed.
   * @return Whether or not the SDK object was successfully removed from the
   * data source.
   */
  public boolean deleteSDK(SDK sdk);
}
