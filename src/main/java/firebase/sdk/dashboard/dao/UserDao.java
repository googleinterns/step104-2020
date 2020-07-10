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
   * Adds the given sdk to the users favorites. 
   *
   * @param user The user making the request.
   * @param sdk The sdk the user wants to add to their favorites.
   * @return Whether or not the transaction was successful.
   */
  public boolean addSDKToFavorites(User user, SDK sdk);

  /**
   * Removes the given sdk to the users favorites. 
   *
   * @param user The user making the request.
   * @param sdk The sdk the user wants to remove from their favorites.
   * @return Whether or not the transaction was successful.
   */
  public boolean removeSDKFromFavorites(User user, SDK sdk);

  /**
   * Get the SDK names that the user marked as their favorite.
   *
   * @param user The user requesting to see their favourites
   * @return A map containing only strings with the keys being platforms
   * and the values being the names of the sdks.
   */
  public Map<String, String> getUserFavorites(User user);
}
