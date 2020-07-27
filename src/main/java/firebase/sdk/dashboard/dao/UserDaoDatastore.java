package firebase.sdk.dashboard.dao;

import firebase.sdk.dashboard.data.SDK;
import firebase.sdk.dashboard.data.SDKReleaseMetadata;
import firebase.sdk.dashboard.data.VersionMetadata;
import firebase.sdk.dashboard.data.Release;
import firebase.sdk.dashboard.data.Platform;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.time.Instant;
import java.io.IOException;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
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

/* Class to implement UserDao */

public class UserDaoDatastore implements UserDao {

    public static final DatastoreService DATASTORE = DatastoreServiceFactory.getDatastoreService();

    // Get a user from the datastore
    public User getUser(String id){
        Query query = new Query("User");
        PreparedQuery results = DATASTORE.prepare(query);
        QueryResultIterable<Entity> users = preparedQuery.asQueryResultIterable();

        for (Entity user: users){
            String iD = (String) user.getProperty("id");
            if (iD.equals(id)){
                return user;
            }
        }

    }

    // Add a user to the datastore
    public void addUser(User user){
       Key userKey = KeyFactory.createKey(user.uid());
       
        // Create a user entity
        Entity userEntity = new Entity(userKey);
        userEntity.setProperty("id",user.uid());
        userEntity.setProperty("email",user.email());
        userEntity.setProperty("favoriteSDKs",user.favoriteSDKs);

        DATASTORE.put(userEntity);
    }
 
    // Updates user favorite SDKs
    public void updateUser(User user){
         User outdated = getUser(user.uid());
         outdated.favoriteSDKs() = user.favoriteSDKs();
         addUser(User outdated);

    }

    private FilterPredicate makePropertyFilter(String property, Object value) {
    FilterPredicate propertyFilter = new FilterPredicate(property, FilterOperator.EQUAL, value);

    return propertyFilter;
  }
    










}