package firebase.sdk.dashboard.data;

public enum Platform {
  UNKNOWN(0),
  ANDROID(1),
  IOS(2),
  WEB(3),
  GAMES(4);

  private int value;
  private final Platform[] platformValues = Platform.values();

  private Platform(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public static Platform fromInteger(int x) {
    return platformValues[x];
  }
}

