package firebase.sdk.dashboard.data;

import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_User extends User {

  private final String uid;

  private final String email;

  private final Map<String, List<String>> favoriteSDKs;

  private AutoValue_User(
      String uid,
      String email,
      Map<String, List<String>> favoriteSDKs) {
    this.uid = uid;
    this.email = email;
    this.favoriteSDKs = favoriteSDKs;
  }

  @Override
  public String uid() {
    return uid;
  }

  @Override
  public String email() {
    return email;
  }

  @Override
  public Map<String, List<String>> favoriteSDKs() {
    return favoriteSDKs;
  }

  @Override
  public String toString() {
    return "User{"
        + "uid=" + uid + ", "
        + "email=" + email + ", "
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
          && this.email.equals(that.email())
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
    h$ ^= email.hashCode();
    h$ *= 1000003;
    h$ ^= favoriteSDKs.hashCode();
    return h$;
  }

  static final class Builder implements User.Builder {
    private String uid;
    private String email;
    private Map<String, List<String>> favoriteSDKs;
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
    public User.Builder email(String email) {
      if (email == null) {
        throw new NullPointerException("Null email");
      }
      this.email = email;
      return this;
    }
    @Override
    public User.Builder favoriteSDKs(Map<String, List<String>> favoriteSDKs) {
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
      if (this.email == null) {
        missing += " email";
      }
      if (this.favoriteSDKs == null) {
        missing += " favoriteSDKs";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_User(
          this.uid,
          this.email,
          this.favoriteSDKs);
    }
  }

}
