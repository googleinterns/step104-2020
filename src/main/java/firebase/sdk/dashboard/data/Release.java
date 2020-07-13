package firebase.sdk.dashboard.data;

import java.util.List;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Release {

  public static Builder newBuilder() {
    return new AutoValue_Release.Builder();
  }

  public abstract String platform();
  public abstract String releaseName();
  public abstract String releaseManager();
  public abstract String buganizerHotlistLink();
  public abstract long launchDeadline();
  public abstract long codeFreezeDate();
  public abstract long launchDate();

  @AutoValue.Builder
  public interface Builder {
    Builder platform(String platform);

    Builder releaseName(String releaseName);

    Builder releaseManager(String releaseManager);

    Builder buganizerHotlistLink(String buganizerHotlistLink);

    Builder launchDeadline(long launchDeadline);

    Builder codeFreezeDate(long codeFreezeDate);

    Builder launchDate(long launchDate);

    Release build();
  }
}

