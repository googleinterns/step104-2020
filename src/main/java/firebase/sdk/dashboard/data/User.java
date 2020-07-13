package firebase.sdk.dashboard.data;

import com.google.auto.value.AutoValue;
import java.util.List;

@AutoValue
public abstract class User {

  public static Builder newBuilder() {
    return new AutoValue_User.Builder();
  }

  public abstract String uid();

  public abstract String email();

  public abstract List<String> favoriteSDKs();

  @AutoValue.Builder
  public interface Builder {
    Builder uid(String uid);

    Builder email(String email);

    Builder favoriteSDKs(List<String> favoriteSDKs);

    User build();
  }
}

