package firebase.sdk.dashboard.data;

import java.util.List;
import java.time.Instant;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
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
  public abstract long launchCalDeadline();

  /* codeFreezeTime is when they cut off a branch to create a release candidate
   * for enrolled SDKS which will later be tested by the SDK owners. Any code
   * that is merged into the master branch after the code freeze date is not
   * released ad part of that launch. */
  @JsonProperty("codeFreezeTime")
  public abstract long codeFreezeTime();

  /* launchDate is when the SDKs in the release are publically released. */
  @JsonProperty("launchDate")
  public abstract long launchDate();

  @AutoValue.Builder
  @JsonPOJOBuilder(withPrefix = "")
  public interface Builder {
    Builder platform(Platform platform);

    Builder releaseName(String releaseName);

    Builder releaseManager(String releaseManager);

    Builder buganizerHotlistLink(String buganizerHotlistLink);

    Builder launchCalDeadline(long launchCalDeadline);

    Builder codeFreezeTime(long codeFreezeTime);

    Builder launchDate(long launchDate);

    Release build();
  }
}

