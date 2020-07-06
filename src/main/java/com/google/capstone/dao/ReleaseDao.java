package com.google.capstone.dao;

import com.google.capstone.data.Release;
import com.google.capstone.data.SDKRelease;

/**
 * An interface for retrieving and manipulating data about SDK releases. 
 */
public interface ReleaseDao {

  /**
   * Retrieves a release object from a data source.
   *
   * @param platform The release objects operating platform (ie Android, iOS, etc,).
   * @param releaseName The release number of the release object (ie M74, M75, etc,).
   * @return A Release data object representing all the information about the release
   * from the data source.
   */
  public Release getRelease(String platform, String releaseName);
  
  /**
   * Adds the given release object to a data source. Only admins should
   * be able to add release objects to data sources.
   * 
   * @param release The release object that is to be stored in the data source.
   * @return Whether or not the release object was succesfully added to the 
   * data source.
   */
  public boolean addRelease(Release release);

  /**
   * Removes the requested release object from a data source. Only admins should
   * be able to delete release objects from data sources.
   *
   * @param release The release object that is to be deleted from the data source.
   * @return Whether or not the release object was succesfully removed from the data
   * source.
   */
  public boolean deleteRelease(Release release);

  /**
   * Enrolls the given SDKRelease object into the given Release by adding it
   * to the Release objects enrolledSDKs HashMap and registers that
   * enrollment by updating the data source with the new data.
   *
   * @param release The Release object that the sdk is being enrolled in.
   * @param sdk The SDKRelease object that is being enrolled.
   * @return Whether or not the SDKRelease was successfully enrolled in the 
   * Release.
   */
  public boolean enrollSDKInRelease(Release release, SDKRelease sdk);

  /**
   * Unenrolls the given SDKRelease object from the given Release object by 
   * removing it from the Release objects enrolledSDKs HashMap and
   * registers the unenrollment by updating the data source with the new data.
   *
   * @param release The Release obhect that the sdk is being unenrolled from.
   * @param sdk The SDKRelease object that is being unenrolled.
   * @return Whether or not the SDKRelease was successfully unenrolled from the 
   * Release.
   */
  public boolean unenrollSDKInRelease(Release release, SDKRelease sdk);

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

