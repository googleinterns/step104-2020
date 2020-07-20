package firebase.sdk.dashboard.data;

import java.util.HashMap;
import com.google.auto.value.AutoValue;

/**
 * This differs from VersionMetadata in the attributes it contains
 * and when the API will return this data. This data will be displayed
 * in the SDK Release page for a given release and represents the changes
 * to that SDK for that release. It is contains some extra data such as the 
 * additional info and what the previous version was but doesn't include 
 * the launch date of the version.  */
@AutoValue
public abstract class SDKReleaseMetadata {

  public static Builder newBuilder() {
    return new AutoValue_SDKReleaseMetadata.Builder();
  }

  public abstract String libraryName();
   
  public abstract Platform platform();
   
  public abstract String releaseName();

  public abstract String releaseVersion();

  public abstract String oldVersion();

  /* This will contain optional information like links to CLs, ariane entries, etc.
   * Example: {"releaseNoteCL": "cl/318309324"} */
  public abstract HashMap<String, String> additionalInfo();

  @AutoValue.Builder
  public interface Builder {
    Builder libraryName(String libraryName);

    Builder platform(Platform platform);

    Builder releaseName(String releaseName);

    Builder releaseVersion(String releaseVersion);

    Builder oldVersion(String oldVersion);

    Builder additionalInfo(HashMap<String, String> additionalInfo);

    SDKReleaseMetadata build();
  }
}

