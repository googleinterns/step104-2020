package firebase.sdk.dashboard.dao;

import javax.inject.Inject;
import firebase.sdk.dashboard.data.User;
import firebase.sdk.dashboard.data.SDK;
import firebase.sdk.dashboard.data.SDKReleaseMetadata;
import firebase.sdk.dashboard.data.VersionMetadata;
import firebase.sdk.dashboard.data.Release;
import firebase.sdk.dashboard.data.Platform;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.time.Instant;
import java.io.IOException;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.QueryResultIterable;

/* Class to implement UserDao to manipulate data about the users */
public class UserDaoDatastore implements UserDao {

  @Inject
  public DatastoreService DATASTORE;

  public UserDaoDatastore() {}

  // Get a user from the datastore
  public User getUser(String id){
    FilterPredicate userPropertyFilter = equalPropertyFilter("id", id);
    Query query = new Query("User").setFilter(userPropertyFilter);

    PreparedQuery preparedQuery = DATASTORE.prepare(query);
    FetchOptions fetchOptions = FetchOptions.Builder.withDefaults();
    Entity user = preparedQuery.asSingleEntity();
    if (user == null) {
      return null;
      //TODO: Throw user not found exception
    }
    User users = User.newBuilder()
      .setUid((String)user.getProperty("id"))
      .setEmail((String)user.getProperty("email"))
      .setFavoriteSDKs(getFavoritesFromProperty((EmbeddedEntity) user.getProperty("favoriteSDKs")))
      .build();
    return users;
  }
  // Add a user to the datastore
  public void addUser(User user){
    Key userKey = KeyFactory.createKey("User", user.uid());
    // Create a user entity
    Entity userEntity = new Entity(userKey);
    userEntity.setProperty("id", user.uid());
    userEntity.setProperty("email", user.email());
    userEntity.setProperty("favoriteSDKs", createFavoritesEmbeddedEntity(user.favoriteSDKs()));
    DATASTORE.put(userEntity);
  }

  // Updates user favorite SDKs
  public void updateUser(User user){
    FilterPredicate userPropertyFilter = equalPropertyFilter("id", user.uid());
    Query query = new Query("User").setFilter(userPropertyFilter);
    PreparedQuery preparedQuery = DATASTORE.prepare(query);
    FetchOptions fetchOptions = FetchOptions.Builder.withDefaults();
    Entity userEntity = preparedQuery.asSingleEntity();
    if (userEntity == null) {
      //TODO: Throw user not found exception
    }
    userEntity.setProperty("favoriteSDKs", createFavoritesEmbeddedEntity(user.favoriteSDKs()));
    DATASTORE.put(userEntity);
  }                                                              

  private FilterPredicate equalPropertyFilter(String property, Object value) {
    FilterPredicate propertyFilter = new FilterPredicate(property, FilterOperator.EQUAL, value);
    return propertyFilter;                                                              
  }

  private Map<String, List<String>> getFavoritesFromProperty(EmbeddedEntity property) {
    HashMap<String, List<String>> favorites = new HashMap<>();

    for (Map.Entry<String, Object> entry : property.getProperties().entrySet()) {
      favorites.put(entry.getKey(), (List<String>) entry.getValue());
    }
    return favorites;
  }

  private EmbeddedEntity createFavoritesEmbeddedEntity(Map<String, List<String>> favoriteSDKs) {
    EmbeddedEntity entity = new EmbeddedEntity();
    for (String key : favoriteSDKs.keySet()) {
      entity.setProperty(key, favoriteSDKs.get(key));
    }
    return entity;
  }
}    
