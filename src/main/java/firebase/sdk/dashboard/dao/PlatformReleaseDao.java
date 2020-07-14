package firebase.sdk.dashboard.dao;

import firebase.sdk.dashboard.data.Release;
import firebase.sdk.dashboard.data.SDKReleaseMetadata;
import firebase.sdk.dashboard.data.Platform;
import java.util.List;

/**
 * An interface for retrieving and manipulating data about platforms and SDK releases. 
 */
public interface PlatformReleaseDao {

  /**
   * Retrieves a list of platform objects from a data source.
   *
   * @return A list of objects representing all the platforms.
   */
  public List<Platform> getPlatforms();

  /**
   * Retrieves a list of the releases for the given platform.
   *
   * @param platform The platform the releases are a part of.
   * @return A list of Release objects representing all the releases in that platform
   * in descending order.
   */
  public List<Release> getPlatformReleases(Platform platform);

  /**
   * Adds the given release object to given platform in a data source. Only admins should
   * be able to add release objects to data sources.
   * 
   * @param platform The platform the release is being added to.
   * @param release The release object that is to be stored in the data source.
   * @return Whether or not the release object was succesfully added to the 
   * data source.
   */
  public boolean addRelease(Platform platform, Release release);

  /**
   * Removes the requested release object from a data source. Only admins should
   * be able to delete release objects from data sources.
   *
   * @param platform The platform the release is being removed from.
   * @param releaseName The name of the release object that is to be deleted from the data source.
   * @return Whether or not the release object was succesfully removed from the data
   * source.
   */
  public boolean deleteRelease(Platform platform, String releaseName);
} 

