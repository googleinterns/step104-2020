package firebase.sdk.dashboard.data;

import java.time.Instant;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_Release extends Release {

  private final Platform platform;

  private final String releaseName;

  private final String releaseManager;

  private final String buganizerHotlistLink;

  private final Instant launchCalDeadline;

  private final Instant codeFreezeTime;

  private final Instant launchDate;

  private AutoValue_Release(
      Platform platform,
      String releaseName,
      String releaseManager,
      String buganizerHotlistLink,
      Instant launchCalDeadline,
      Instant codeFreezeTime,
      Instant launchDate) {
    this.platform = platform;
    this.releaseName = releaseName;
    this.releaseManager = releaseManager;
    this.buganizerHotlistLink = buganizerHotlistLink;
    this.launchCalDeadline = launchCalDeadline;
    this.codeFreezeTime = codeFreezeTime;
    this.launchDate = launchDate;
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
  public String releaseManager() {
    return releaseManager;
  }

  @Override
  public String buganizerHotlistLink() {
    return buganizerHotlistLink;
  }

  @Override
  public Instant launchCalDeadline() {
    return launchCalDeadline;
  }

  @Override
  public Instant codeFreezeTime() {
    return codeFreezeTime;
  }

  @Override
  public Instant launchDate() {
    return launchDate;
  }

  @Override
  public String toString() {
    return "Release{"
        + "platform=" + platform + ", "
        + "releaseName=" + releaseName + ", "
        + "releaseManager=" + releaseManager + ", "
        + "buganizerHotlistLink=" + buganizerHotlistLink + ", "
        + "launchCalDeadline=" + launchCalDeadline + ", "
        + "codeFreezeTime=" + codeFreezeTime + ", "
        + "launchDate=" + launchDate
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof Release) {
      Release that = (Release) o;
      return this.platform.equals(that.platform())
          && this.releaseName.equals(that.releaseName())
          && this.releaseManager.equals(that.releaseManager())
          && this.buganizerHotlistLink.equals(that.buganizerHotlistLink())
          && this.launchCalDeadline.equals(that.launchCalDeadline())
          && this.codeFreezeTime.equals(that.codeFreezeTime())
          && this.launchDate.equals(that.launchDate());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= platform.hashCode();
    h$ *= 1000003;
    h$ ^= releaseName.hashCode();
    h$ *= 1000003;
    h$ ^= releaseManager.hashCode();
    h$ *= 1000003;
    h$ ^= buganizerHotlistLink.hashCode();
    h$ *= 1000003;
    h$ ^= launchCalDeadline.hashCode();
    h$ *= 1000003;
    h$ ^= codeFreezeTime.hashCode();
    h$ *= 1000003;
    h$ ^= launchDate.hashCode();
    return h$;
  }

  static final class Builder implements Release.Builder {
    private Platform platform;
    private String releaseName;
    private String releaseManager;
    private String buganizerHotlistLink;
    private Instant launchCalDeadline;
    private Instant codeFreezeTime;
    private Instant launchDate;
    Builder() {
    }
    @Override
    public Release.Builder platform(Platform platform) {
      if (platform == null) {
        throw new NullPointerException("Null platform");
      }
      this.platform = platform;
      return this;
    }
    @Override
    public Release.Builder releaseName(String releaseName) {
      if (releaseName == null) {
        throw new NullPointerException("Null releaseName");
      }
      this.releaseName = releaseName;
      return this;
    }
    @Override
    public Release.Builder releaseManager(String releaseManager) {
      if (releaseManager == null) {
        throw new NullPointerException("Null releaseManager");
      }
      this.releaseManager = releaseManager;
      return this;
    }
    @Override
    public Release.Builder buganizerHotlistLink(String buganizerHotlistLink) {
      if (buganizerHotlistLink == null) {
        throw new NullPointerException("Null buganizerHotlistLink");
      }
      this.buganizerHotlistLink = buganizerHotlistLink;
      return this;
    }
    @Override
    public Release.Builder launchCalDeadline(Instant launchCalDeadline) {
      if (launchCalDeadline == null) {
        throw new NullPointerException("Null launchCalDeadline");
      }
      this.launchCalDeadline = launchCalDeadline;
      return this;
    }
    @Override
    public Release.Builder codeFreezeTime(Instant codeFreezeTime) {
      if (codeFreezeTime == null) {
        throw new NullPointerException("Null codeFreezeTime");
      }
      this.codeFreezeTime = codeFreezeTime;
      return this;
    }
    @Override
    public Release.Builder launchDate(Instant launchDate) {
      if (launchDate == null) {
        throw new NullPointerException("Null launchDate");
      }
      this.launchDate = launchDate;
      return this;
    }
    @Override
    public Release build() {
      String missing = "";
      if (this.platform == null) {
        missing += " platform";
      }
      if (this.releaseName == null) {
        missing += " releaseName";
      }
      if (this.releaseManager == null) {
        missing += " releaseManager";
      }
      if (this.buganizerHotlistLink == null) {
        missing += " buganizerHotlistLink";
      }
      if (this.launchCalDeadline == null) {
        missing += " launchCalDeadline";
      }
      if (this.codeFreezeTime == null) {
        missing += " codeFreezeTime";
      }
      if (this.launchDate == null) {
        missing += " launchDate";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_Release(
          this.platform,
          this.releaseName,
          this.releaseManager,
          this.buganizerHotlistLink,
          this.launchCalDeadline,
          this.codeFreezeTime,
          this.launchDate);
    }
  }

}
