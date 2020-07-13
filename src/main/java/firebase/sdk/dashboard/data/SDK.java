package firebase.sdk.dashboard.data;

import java.util.List;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class SDK {

  public static Builder newBuilder() {
    return new AutoValue_SDK.Builder();
  }

  public abstract String libraryName();

  /* SDKs in g3 are broken up into library groups, which are SDKs that must 
   * be built together because they use obfuscated symbols in another library 
   * in the library group. Every SDK in a library group must be released together,
   * even if only one SDK has changes. */
  public abstract String libraryGroup();

  public abstract String externalName();

  /* https://g3doc.corp.google.com/firebase/g3doc/acore/fireescape/index.md?cl=head. */
  public abstract String fireEscapeName();

  /* Internal username of the person managing the SDK. */
  public abstract String owner();

  public abstract List<VersionMetadata> versionHistory();

  @AutoValue.Builder
  public interface Builder {
    Builder libraryName(String libraryName);

    Builder libraryGroup(String libraryGroup);

    Builder externalName(String externalName);

    Builder fireEscapeName(String fireEscapeName);

    Builder owner(String owner);

    Builder versionHistory(List<VersionMetadata> versionHistory);

    SDK build();
  }
}

