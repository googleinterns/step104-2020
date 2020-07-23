package firebase.sdk.dashboard.data;

import com.google.auto.value.AutoValue;
import java.util.List;
import java.util.Map;

@AutoValue
public abstract class User {

  public static Builder newBuilder() {
    return new AutoValue_User.Builder();
  }

  public abstract String uid();

  /* Example: {"Android": ["firebase-common", "firebase-common-ktx"]} */
  public abstract Map<Platform, List<String>> favoriteSDKs();

  @AutoValue.Builder
  public interface Builder {
    Builder uid(String uid);

    /* The key is the platform and the value will be a list of the names of
     * the users favorite SDKs for that platform. */ 
    Builder favoriteSDKs(Map<Platform, List<String>> favoriteSDKs);

    User build();
  }
}

