package firebase.sdk.dashboard.data;

public enum Platform {
  ANDROID("Android"),
  IOS("iOS"),
  WEB("Web"),
  GAMES("Games");

  private String label;

  Platform(Sting enumName){
    this.label;
  }

  public String getName() {
    return label;
  }

}

