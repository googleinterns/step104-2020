package firebase.sdk.dashboard.data;

import java.util.List;
import java.time.Instant;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

@AutoValue
@JsonDeserialize(builder = AutoValue_Release.Builder.class)
public abstract class Release {

  public static Builder newBuilder() {
    return new AutoValue_Release.Builder();
  }

  @JsonProperty("platform")
  public abstract Platform platform();

  @JsonProperty("releaseName")
  public abstract String releaseName();

  @JsonProperty("releaseManager")
  public abstract String releaseManager();

  @JsonProperty("buganizerHotlistLink")
  public abstract String buganizerHotlistLink();

  /* launchCalDeadline is the date by which an Ariane launch entry should be approved
   * for minor/major versions. */
  @JsonProperty("launchCalDeadline")
  public abstract Instant launchCalDeadline();

  /* codeFreezeTime is when they cut off a branch to create a release candidate
   * for enrolled SDKS which will later be tested by the SDK owners. Any code
   * that is merged into the master branch after the code freeze date is not
   * released ad part of that launch. */
  @JsonProperty("codeFreezeTime")
  public abstract Instant codeFreezeTime();

  /* launchDate is when the SDKs in the release are publically released. */
  @JsonProperty("launchDate")
  public abstract Instant launchDate();

  @AutoValue.Builder
  public interface Builder {
    @JsonProperty("platform")
    Builder platform(Platform platform);

    @JsonProperty("releaseName")
    Builder releaseName(String releaseName);

    @JsonProperty("releaseManager")
    Builder releaseManager(String releaseManager);

    @JsonProperty("buganizerHotlistLink")
    Builder buganizerHotlistLink(String buganizerHotlistLink);

    @JsonProperty("launchCalDeadline")
    Builder launchCalDeadline(Instant launchCalDeadline);

    @JsonProperty("codeFreezeTime")
    Builder codeFreezeTime(Instant codeFreezeTime);

    @JsonProperty("launchDate")
    Builder launchDate(Instant launchDate);

    Release build();
  }
}

