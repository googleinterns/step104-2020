package firebase.sdk.dashboard.data;

import java.util.HashMap;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class SDKReleaseMetadata {

  public static Builder newBuilder() {
    return new AutoValue_SDKReleaseMetadata.Builder();
  }

  public abstract String libraryName();
  public abstract String platform();
  public abstract String release();
  public abstract String newVersion();
  public abstract String oldVersion();

  /* This will contain optional information like links to CLs, ariane entries, etc. */
  public abstract HashMap<String, String> additionalInfo();

  @AutoValue.Builder
  public interface Builder {
    Builder libraryName(String libraryName);

    Builder platform(String platform);

    Builder release(String release);

    Builder newVersion(String newVersion);

    Builder oldVersion(String oldVersion);

    Builder additionalInfo(HashMap<String, String> additionalInfo);

    SDKReleaseMetadata build();
  }
}

