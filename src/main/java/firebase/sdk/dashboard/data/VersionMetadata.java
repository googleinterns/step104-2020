package firebase.sdk.dashboard.data;

import com.google.auto.value.AutoValue;
import java.time.Instant;

/**
 * This differs from SDKReleaseMEtadata in the attributes it contains
 * and when the API will return this data. This data will be displayed
 * in the SDK version history page for a given sdk and represents a 
 * row in the table. It is missing some data such as the additional info
 * and what the previous version was from the SDKReleaseMetadata object
 * and includes the launch date of the version. This will also be used to
 * represent upcomning versions and the launch date will be the projected 
 * launch date of the release.
 */
@AutoValue
public abstract class VersionMetadata {

  public static Builder newBuilder() {
    return new AutoValue_VersionMetadata.Builder();
  }

  public abstract String libraryName();

  public abstract Platform platform();

  public abstract String releaseName();

  public abstract String version();

  public abstract Instant launchDate();

  @AutoValue.Builder
  public interface Builder {
    Builder libraryName(String libraryName);

    Builder platform(Platform platform);

    Builder releaseName(String releaseName);

    Builder version(String version);

    Builder launchDate(Instant launchDate);

    VersionMetadata build();
  }
}

