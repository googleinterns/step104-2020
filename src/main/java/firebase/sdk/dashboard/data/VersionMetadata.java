package firebase.sdk.dashboard.data;

import java.time.Instant;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

/**
 * This differs from SDKReleaseMetadata in the attributes it contains
 * and when the API will return this data. This data will be displayed
 * in the SDK version history page for a given sdk and represents a 
 * row in the table. It is missing some data such as the additional info
 * and what the previous version was from the SDKReleaseMetadata object
 * and includes the launch date of the version. This will also be used to
 * represent upcoming versions and the launch date will be the projected 
 * launch date of the release.
 */
@AutoValue
@JsonDeserialize(builder = AutoValue_VersionMetadata.Builder.class)
public abstract class VersionMetadata {

  public static Builder newBuilder() {
    return new AutoValue_VersionMetadata.Builder();
  }

  @JsonProperty("libraryName")
  public abstract String libraryName();

  @JsonProperty("platform")
  public abstract Platform platform();

  @JsonProperty("releaseName")
  public abstract String releaseName();

  @JsonProperty("version")
  public abstract String version();

  @JsonProperty("launchDate")
  public abstract Instant launchDate();

  @AutoValue.Builder
  @JsonPOJOBuilder(withPrefix = "")
  public interface Builder {
    Builder libraryName(String libraryName);

    Builder platform(Platform platform);

    Builder releaseName(String releaseName);

    Builder version(String version);

    Builder launchDate(Instant launchDate);

    VersionMetadata build();
  }
}

