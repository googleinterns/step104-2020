package firebase.sdk.dashboard.data;

import java.util.HashMap;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_SDKReleaseMetadata extends SDKReleaseMetadata {

  private final String libraryName;

  private final Platform platform;

  private final String releaseName;

  private final String releaseVersion;

  private final String oldVersion;

  private final HashMap<String, String> additionalInfo;

  private AutoValue_SDKReleaseMetadata(
      String libraryName,
      Platform platform,
      String releaseName,
      String releaseVersion,
      String oldVersion,
      HashMap<String, String> additionalInfo) {
    this.libraryName = libraryName;
    this.platform = platform;
    this.releaseName = releaseName;
    this.releaseVersion = releaseVersion;
    this.oldVersion = oldVersion;
    this.additionalInfo = additionalInfo;
  }

  @Override
  public String libraryName() {
    return libraryName;
  }

  @Override
  public Platform platform() {
    return platform;
  }

  @Override
  public String releaseName() {
    return releaseName;
  }

  @Override
  public String releaseVersion() {
    return releaseVersion;
  }

  @Override
  public String oldVersion() {
    return oldVersion;
  }

  @Override
  public HashMap<String, String> additionalInfo() {
    return additionalInfo;
  }

  @Override
  public String toString() {
    return "SDKReleaseMetadata{"
        + "libraryName=" + libraryName + ", "
        + "platform=" + platform + ", "
        + "releaseName=" + releaseName + ", "
        + "releaseVersion=" + releaseVersion + ", "
        + "oldVersion=" + oldVersion + ", "
        + "additionalInfo=" + additionalInfo
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof SDKReleaseMetadata) {
      SDKReleaseMetadata that = (SDKReleaseMetadata) o;
      return this.libraryName.equals(that.libraryName())
          && this.platform.equals(that.platform())
          && this.releaseName.equals(that.releaseName())
          && this.releaseVersion.equals(that.releaseVersion())
          && this.oldVersion.equals(that.oldVersion())
          && this.additionalInfo.equals(that.additionalInfo());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= libraryName.hashCode();
    h$ *= 1000003;
    h$ ^= platform.hashCode();
    h$ *= 1000003;
    h$ ^= releaseName.hashCode();
    h$ *= 1000003;
    h$ ^= releaseVersion.hashCode();
    h$ *= 1000003;
    h$ ^= oldVersion.hashCode();
    h$ *= 1000003;
    h$ ^= additionalInfo.hashCode();
    return h$;
  }

  static final class Builder implements SDKReleaseMetadata.Builder {
    private String libraryName;
    private Platform platform;
    private String releaseName;
    private String releaseVersion;
    private String oldVersion;
    private HashMap<String, String> additionalInfo;
    Builder() {
    }
    @Override
    public SDKReleaseMetadata.Builder libraryName(String libraryName) {
      if (libraryName == null) {
        throw new NullPointerException("Null libraryName");
      }
      this.libraryName = libraryName;
      return this;
    }
    @Override
    public SDKReleaseMetadata.Builder platform(Platform platform) {
      if (platform == null) {
        throw new NullPointerException("Null platform");
      }
      this.platform = platform;
      return this;
    }
    @Override
    public SDKReleaseMetadata.Builder releaseName(String releaseName) {
      if (releaseName == null) {
        throw new NullPointerException("Null releaseName");
      }
      this.releaseName = releaseName;
      return this;
    }
    @Override
    public SDKReleaseMetadata.Builder releaseVersion(String releaseVersion) {
      if (releaseVersion == null) {
        throw new NullPointerException("Null releaseVersion");
      }
      this.releaseVersion = releaseVersion;
      return this;
    }
    @Override
    public SDKReleaseMetadata.Builder oldVersion(String oldVersion) {
      if (oldVersion == null) {
        throw new NullPointerException("Null oldVersion");
      }
      this.oldVersion = oldVersion;
      return this;
    }
    @Override
    public SDKReleaseMetadata.Builder additionalInfo(HashMap<String, String> additionalInfo) {
      if (additionalInfo == null) {
        throw new NullPointerException("Null additionalInfo");
      }
      this.additionalInfo = additionalInfo;
      return this;
    }
    @Override
    public SDKReleaseMetadata build() {
      String missing = "";
      if (this.libraryName == null) {
        missing += " libraryName";
      }
      if (this.platform == null) {
        missing += " platform";
      }
      if (this.releaseName == null) {
        missing += " releaseName";
      }
      if (this.releaseVersion == null) {
        missing += " releaseVersion";
      }
      if (this.oldVersion == null) {
        missing += " oldVersion";
      }
      if (this.additionalInfo == null) {
        missing += " additionalInfo";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_SDKReleaseMetadata(
          this.libraryName,
          this.platform,
          this.releaseName,
          this.releaseVersion,
          this.oldVersion,
          this.additionalInfo);
    }
  }

}
