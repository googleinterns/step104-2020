package firebase.sdk.dashboard.data;

import java.time.Instant;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_VersionMetadata extends VersionMetadata {

  private final String libraryName;

  private final Platform platform;

  private final String releaseName;

  private final String version;

  private final Instant launchDate;

  private AutoValue_VersionMetadata(
      String libraryName,
      Platform platform,
      String releaseName,
      String version,
      Instant launchDate) {
    this.libraryName = libraryName;
    this.platform = platform;
    this.releaseName = releaseName;
    this.version = version;
    this.launchDate = launchDate;
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
  public String version() {
    return version;
  }

  @Override
  public Instant launchDate() {
    return launchDate;
  }

  @Override
  public String toString() {
    return "VersionMetadata{"
        + "libraryName=" + libraryName + ", "
        + "platform=" + platform + ", "
        + "releaseName=" + releaseName + ", "
        + "version=" + version + ", "
        + "launchDate=" + launchDate
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof VersionMetadata) {
      VersionMetadata that = (VersionMetadata) o;
      return this.libraryName.equals(that.libraryName())
          && this.platform.equals(that.platform())
          && this.releaseName.equals(that.releaseName())
          && this.version.equals(that.version())
          && this.launchDate.equals(that.launchDate());
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
    h$ ^= version.hashCode();
    h$ *= 1000003;
    h$ ^= launchDate.hashCode();
    return h$;
  }

  static final class Builder implements VersionMetadata.Builder {
    private String libraryName;
    private Platform platform;
    private String releaseName;
    private String version;
    private Instant launchDate;
    Builder() {
    }
    @Override
    public VersionMetadata.Builder libraryName(String libraryName) {
      if (libraryName == null) {
        throw new NullPointerException("Null libraryName");
      }
      this.libraryName = libraryName;
      return this;
    }
    @Override
    public VersionMetadata.Builder platform(Platform platform) {
      if (platform == null) {
        throw new NullPointerException("Null platform");
      }
      this.platform = platform;
      return this;
    }
    @Override
    public VersionMetadata.Builder releaseName(String releaseName) {
      if (releaseName == null) {
        throw new NullPointerException("Null releaseName");
      }
      this.releaseName = releaseName;
      return this;
    }
    @Override
    public VersionMetadata.Builder version(String version) {
      if (version == null) {
        throw new NullPointerException("Null version");
      }
      this.version = version;
      return this;
    }
    @Override
    public VersionMetadata.Builder launchDate(Instant launchDate) {
      if (launchDate == null) {
        throw new NullPointerException("Null launchDate");
      }
      this.launchDate = launchDate;
      return this;
    }
    @Override
    public VersionMetadata build() {
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
      if (this.version == null) {
        missing += " version";
      }
      if (this.launchDate == null) {
        missing += " launchDate";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_VersionMetadata(
          this.libraryName,
          this.platform,
          this.releaseName,
          this.version,
          this.launchDate);
    }
  }

}
