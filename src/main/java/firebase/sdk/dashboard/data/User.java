package firebase.sdk.dashboard.data;

import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

@AutoValue
@JsonDeserialize(builder = AutoValue_User.Builder.class)
public abstract class User {

  public static Builder newBuilder() {
    return new AutoValue_User.Builder();
  }

  public abstract Builder toBuilder();

  @JsonProperty("uid")
  public abstract String uid();

  public abstract String email();

  /* Example: {"Android": ["firebase-common", "firebase-common-ktx"]} */
  @JsonProperty("favoriteSDKs")
  public abstract Map<Platform, List<String>> favoriteSDKs();

  @AutoValue.Builder
  @JsonPOJOBuilder(withPrefix = "")
  public interface Builder {
    Builder setUid(String uid);

    Builder setEmail(String email);

    /* The key is the platform and the value will be a list of the names of
     * the users favorite SDKs for that platform. */ 
    Builder setFavoriteSDKs(Map<Platform, List<String>> favoriteSDKs);

    User build();
  }
}

