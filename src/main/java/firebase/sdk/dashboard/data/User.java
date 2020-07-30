package firebase.sdk.dashboard.data;

import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

@AutoValue
@JsonDeserialize(builder = AutoValue_User.Builder.class)
public abstract class User {

  public static Builder newBuilder() {
    return new AutoValue_User.Builder();
  }

  @Jsonproperty("uid")
  public abstract String uid();

  /* Example: {"Android": ["firebase-common", "firebase-common-ktx"]} */
  @Jsonproperty("favoriteSDKs")
  public abstract Map<Platform, List<String>> favoriteSDKs();

  @AutoValue.Builder
  @JsonPOJOBuilder(withPrefix = "")
  public interface Builder {
    Builder uid(String uid);

    /* The key is the platform and the value will be a list of the names of
     * the users favorite SDKs for that platform. */ 
    Builder favoriteSDKs(Map<Platform, List<String>> favoriteSDKs);

    User build();
  }
}

