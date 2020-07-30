package firebase.sdk.dashboard.data;

import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

/**
 * This differs from VersionMetadata in the attributes it contains
 * and when the API will return this data. This data will be displayed
 * in the SDK Release page for a given release and represents the changes
 * to that SDK for that release. It is contains some extra data such as the 
 * additional info and what the previous version was but doesn't include 
 * the launch date of the version.  */
@AutoValue
@JsonDeserialize(builder = AutoValue_SDKReleaseMetadata.Builder.class)
public abstract class SDKReleaseMetadata {

  public static Builder newBuilder() {
    return new AutoValue_SDKReleaseMetadata.Builder();
  }

  @JsonProperty("libraryName")
  public abstract String libraryName();
   
  @JsonProperty("platform")
  public abstract Platform platform();
   
  @JsonProperty("releaseName")
  public abstract String releaseName();

  /* The email of the user that enrolled the sdk. */
  @JsonProperty("verifier")
  public abstract String verifier();

  @JsonProperty("releaseVersion")
  public abstract String releaseVersion();

  @JsonProperty("oldVersion")
  public abstract String oldVersion();

  /* This will contain optional information like links to CLs, ariane entries, etc.
   * Example: {"releaseNoteCL": "cl/318309324"} */
  @JsonProperty("additionalInfo")
  public abstract HashMap<String, String> additionalInfo();

  @AutoValue.Builder
  @JsonPOJOBuilder(withPrefix = "")
  public interface Builder {
    Builder libraryName(String libraryName);

    Builder platform(Platform platform);

    Builder releaseName(String releaseName);

    Builder verifier(String verifier);

    Builder releaseVersion(String releaseVersion);

    Builder oldVersion(String oldVersion);

    Builder additionalInfo(HashMap<String, String> additionalInfo);

    SDKReleaseMetadata build();
  }
}

