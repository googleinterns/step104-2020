// TODO: Not entirely sure how interacting with user data will be but created a boiler plate.
package firebase.sdk.dashboard.dao;

import firebase.sdk.dashboard.data.User;

/**
 * An interface for retrieving and manipulating data about Users.
 */
public interface UserDao {

  /** 
   * Retrieves a User object from a data source.
   *
   * @param id The unique id that identifies the user.
   * @return A User object containg data that represents the user and their preferences.
   */
  public User getUser(String id);

  /**
   * Adds a User to the data source.
   *
   * @param user The user being added to the data source.
   */
  public void addUser(User user);

  /**
   * Updates the user's settings and registers the change in the data source.
   *
   * @param user The user object that contains the new settings.
   * @return Whether or not the transaction was successful.
   */
  public boolean updateUserSettings(User user);
}
