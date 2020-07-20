package firebase.sdk.dashboard.data;

public enum Platform {
  ANDROID("Android"),
  IOS("iOS"),
  WEB("Web"),
  GAMES("Games");

  private String label;

  Platform(String enumName){
    this.label = enumName;
  }

  public String getLabel() {
    return label;
  }

}

