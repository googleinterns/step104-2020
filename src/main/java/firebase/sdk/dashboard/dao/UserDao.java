// TODO: Not entirely sure how interacting with user data will be but created a boiler plate.
package firebase.sdk.dashboard.dao;

import firebase.sdk.dashboard.data.User;
import firebase.sdk.dashboard.data.SDK;
import java.util.Map;

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
   * Registers the changes in the User object to the database.
   *
   * @param user The user being update in the data source.
   */
  public void updateUser(User user);
}
