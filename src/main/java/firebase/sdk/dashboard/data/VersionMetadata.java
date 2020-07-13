package firebase.sdk.dashboard.data;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class VersionMetadata {

  public static Builder newBuilder() {
    return new AutoValue_VersionMetadata.Builder();
  }

  public abstract String libraryName();
  public abstract String platform();
  public abstract String release();
  public abstract String newVersion();
  public abstract long launchDate();

  @AutoValue.Builder
  public interface Builder {
    Builder libraryName(String libraryName);

    Builder platform(String platform);

    Builder release(String release);

    Builder newVersion(String newVersion);

    Builder launchDate(long launchDate);

    VersionMetadata build();
  }
}

