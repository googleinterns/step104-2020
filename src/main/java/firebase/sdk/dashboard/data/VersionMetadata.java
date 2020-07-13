package firebase.sdk.dashboard.data;

import com.google.auto.value.AutoValue;
import java.time.Instant;

@AutoValue
public abstract class VersionMetadata {

  public static Builder newBuilder() {
    return new AutoValue_VersionMetadata.Builder();
  }

  public abstract String libraryName();

  public abstract String platform();

  public abstract String releaseName();

  public abstract String version();

  public abstract Instant launchDate();

  @AutoValue.Builder
  public interface Builder {
    Builder libraryName(String libraryName);

    Builder platform(String platform);

    Builder releaseName(String releaseName);

    Builder version(String version);

    Builder launchDate(Instant launchDate);

    VersionMetadata build();
  }
}

