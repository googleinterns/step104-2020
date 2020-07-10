package firebase.sdk.dashboard.data;

import java.util.List;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class SDK {

  public static Builder newBuilder() {
    return new AutoValue_SDK.Builder();
  }

  public abstract String libraryName();
  public abstract String libraryGroup();
  public abstract String externalName();
  public abstract String fireEscapeName();
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

