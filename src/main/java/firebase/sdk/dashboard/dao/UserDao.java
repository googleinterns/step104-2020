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
   * @return A User object containg data that represents the user and their favorite sdks.
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
   * @param user The user being updated in the data source.
   */
  public void updateUser(User user);
}
 

