package firebase.sdk.dashboard.data;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

@AutoValue
@JsonDeserialize(builder = AutoValue_SDK.Builder.class)
public abstract class SDK {

  public static Builder newBuilder() {
    return new AutoValue_SDK.Builder();
  }

  @JsonProperty("platform")
  public abstract Platform platform();

  @JsonProperty("libraryName")
  public abstract String libraryName();

  /* SDKs in g3 are broken up into library groups, which are SDKs that must 
   * be built together because they use obfuscated symbols in another library 
   * in the library group. Every SDK in a library group must be released together,
   * even if only one SDK has changes. */
  @JsonProperty("libraryGroup")
  public abstract String libraryGroup();

  @JsonProperty("externalName")
  public abstract String externalName();

  /* https://g3doc.corp.google.com/firebase/g3doc/acore/fireescape/index.md?cl=head. */
  @JsonProperty("fireEscapeName")
  public abstract String fireEscapeName();

  /* Internal username of the person managing the SDK. */
  @JsonProperty("owner")
  public abstract String owner();

  @JsonProperty("versionHistory")
  public abstract List<VersionMetadata> versionHistory();

  @AutoValue.Builder
  @JsonPOJOBuilder(withPrefix = "")
  public interface Builder {
    Builder platform(Platform platform);

    Builder libraryName(String libraryName);

    Builder libraryGroup(String libraryGroup);

    Builder externalName(String externalName);

    Builder fireEscapeName(String fireEscapeName);

    Builder owner(String owner);

    Builder versionHistory(List<VersionMetadata> versionHistory);

    SDK build();
  }
}

