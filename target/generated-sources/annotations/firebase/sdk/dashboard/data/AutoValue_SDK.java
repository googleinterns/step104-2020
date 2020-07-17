package firebase.sdk.dashboard.data;

import java.util.List;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_SDK extends SDK {

  private final Platform platform;

  private final String libraryName;

  private final String libraryGroup;

  private final String externalName;

  private final String fireEscapeName;

  private final String owner;

  private final List<VersionMetadata> versionHistory;

  private AutoValue_SDK(
      Platform platform,
      String libraryName,
      String libraryGroup,
      String externalName,
      String fireEscapeName,
      String owner,
      List<VersionMetadata> versionHistory) {
    this.platform = platform;
    this.libraryName = libraryName;
    this.libraryGroup = libraryGroup;
    this.externalName = externalName;
    this.fireEscapeName = fireEscapeName;
    this.owner = owner;
    this.versionHistory = versionHistory;
  }

  @Override
  public Platform platform() {
    return platform;
  }

  @Override
  public String libraryName() {
    return libraryName;
  }

  @Override
  public String libraryGroup() {
    return libraryGroup;
  }

  @Override
  public String externalName() {
    return externalName;
  }

  @Override
  public String fireEscapeName() {
    return fireEscapeName;
  }

  @Override
  public String owner() {
    return owner;
  }

  @Override
  public List<VersionMetadata> versionHistory() {
    return versionHistory;
  }

  @Override
  public String toString() {
    return "SDK{"
        + "platform=" + platform + ", "
        + "libraryName=" + libraryName + ", "
        + "libraryGroup=" + libraryGroup + ", "
        + "externalName=" + externalName + ", "
        + "fireEscapeName=" + fireEscapeName + ", "
        + "owner=" + owner + ", "
        + "versionHistory=" + versionHistory
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof SDK) {
      SDK that = (SDK) o;
      return this.platform.equals(that.platform())
          && this.libraryName.equals(that.libraryName())
          && this.libraryGroup.equals(that.libraryGroup())
          && this.externalName.equals(that.externalName())
          && this.fireEscapeName.equals(that.fireEscapeName())
          && this.owner.equals(that.owner())
          && this.versionHistory.equals(that.versionHistory());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= platform.hashCode();
    h$ *= 1000003;
    h$ ^= libraryName.hashCode();
    h$ *= 1000003;
    h$ ^= libraryGroup.hashCode();
    h$ *= 1000003;
    h$ ^= externalName.hashCode();
    h$ *= 1000003;
    h$ ^= fireEscapeName.hashCode();
    h$ *= 1000003;
    h$ ^= owner.hashCode();
    h$ *= 1000003;
    h$ ^= versionHistory.hashCode();
    return h$;
  }

  static final class Builder implements SDK.Builder {
    private Platform platform;
    private String libraryName;
    private String libraryGroup;
    private String externalName;
    private String fireEscapeName;
    private String owner;
    private List<VersionMetadata> versionHistory;
    Builder() {
    }
    @Override
    public SDK.Builder platform(Platform platform) {
      if (platform == null) {
        throw new NullPointerException("Null platform");
      }
      this.platform = platform;
      return this;
    }
    @Override
    public SDK.Builder libraryName(String libraryName) {
      if (libraryName == null) {
        throw new NullPointerException("Null libraryName");
      }
      this.libraryName = libraryName;
      return this;
    }
    @Override
    public SDK.Builder libraryGroup(String libraryGroup) {
      if (libraryGroup == null) {
        throw new NullPointerException("Null libraryGroup");
      }
      this.libraryGroup = libraryGroup;
      return this;
    }
    @Override
    public SDK.Builder externalName(String externalName) {
      if (externalName == null) {
        throw new NullPointerException("Null externalName");
      }
      this.externalName = externalName;
      return this;
    }
    @Override
    public SDK.Builder fireEscapeName(String fireEscapeName) {
      if (fireEscapeName == null) {
        throw new NullPointerException("Null fireEscapeName");
      }
      this.fireEscapeName = fireEscapeName;
      return this;
    }
    @Override
    public SDK.Builder owner(String owner) {
      if (owner == null) {
        throw new NullPointerException("Null owner");
      }
      this.owner = owner;
      return this;
    }
    @Override
    public SDK.Builder versionHistory(List<VersionMetadata> versionHistory) {
      if (versionHistory == null) {
        throw new NullPointerException("Null versionHistory");
      }
      this.versionHistory = versionHistory;
      return this;
    }
    @Override
    public SDK build() {
      String missing = "";
      if (this.platform == null) {
        missing += " platform";
      }
      if (this.libraryName == null) {
        missing += " libraryName";
      }
      if (this.libraryGroup == null) {
        missing += " libraryGroup";
      }
      if (this.externalName == null) {
        missing += " externalName";
      }
      if (this.fireEscapeName == null) {
        missing += " fireEscapeName";
      }
      if (this.owner == null) {
        missing += " owner";
      }
      if (this.versionHistory == null) {
        missing += " versionHistory";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_SDK(
          this.platform,
          this.libraryName,
          this.libraryGroup,
          this.externalName,
          this.fireEscapeName,
          this.owner,
          this.versionHistory);
    }
  }

}
