package firebase.sdk.dashboard.data;

import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_User extends User {

  private final String uid;

  private final Map<Platform, List<String>> favoriteSDKs;

  private AutoValue_User(
      String uid,
      Map<Platform, List<String>> favoriteSDKs) {
    this.uid = uid;
    this.favoriteSDKs = favoriteSDKs;
  }

  @Override
  public String uid() {
    return uid;
  }

  @Override
  public Map<Platform, List<String>> favoriteSDKs() {
    return favoriteSDKs;
  }

  @Override
  public String toString() {
    return "User{"
        + "uid=" + uid + ", "
        + "favoriteSDKs=" + favoriteSDKs
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof User) {
      User that = (User) o;
      return this.uid.equals(that.uid())
          && this.favoriteSDKs.equals(that.favoriteSDKs());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= uid.hashCode();
    h$ *= 1000003;
    h$ ^= favoriteSDKs.hashCode();
    return h$;
  }

  static final class Builder implements User.Builder {
    private String uid;
    private Map<Platform, List<String>> favoriteSDKs;
    Builder() {
    }
    @Override
    public User.Builder uid(String uid) {
      if (uid == null) {
        throw new NullPointerException("Null uid");
      }
      this.uid = uid;
      return this;
    }
    @Override
    public User.Builder favoriteSDKs(Map<Platform, List<String>> favoriteSDKs) {
      if (favoriteSDKs == null) {
        throw new NullPointerException("Null favoriteSDKs");
      }
      this.favoriteSDKs = favoriteSDKs;
      return this;
    }
    @Override
    public User build() {
      String missing = "";
      if (this.uid == null) {
        missing += " uid";
      }
      if (this.favoriteSDKs == null) {
        missing += " favoriteSDKs";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_User(
          this.uid,
          this.favoriteSDKs);
    }
  }

}
