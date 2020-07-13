package firebase.sdk.dashboard.data;

import java.util.List;
import java.time.Instant;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Release {

  public static Builder newBuilder() {
    return new AutoValue_Release.Builder();
  }

  public abstract String platform();

  public abstract String releaseName();

  public abstract String releaseManager();

  public abstract String buganizerHotlistLink();

  /* launchCalDeadline is the date by which an Ariane launch entry should be approved
   * for minor/major versions. */
  public abstract Instant launchCalDeadline();

  /* codeFreezeTime is when they cut off a branch to create a release candidate
   * for enrolled SDKS which will later be tested by the SDK owners. Any code
   * that is merged into the master branch after the code freeze date is not
   * released ad part of that launch. */
  public abstract Instant codeFreezeTime();

  /* launchDate is when the SDKs in the release are publically released. */
  public abstract Instant launchDate();

  @AutoValue.Builder
  public interface Builder {
    Builder platform(String platform);

    Builder releaseName(String releaseName);

    Builder releaseManager(String releaseManager);

    Builder buganizerHotlistLink(String buganizerHotlistLink);

    Builder launchCalDeadline(Instant launchCalDeadline);

    Builder codeFreezeTime(Instant codeFreezeTime);

    Builder launchDate(Instant launchDate);

    Release build();
  }
}

