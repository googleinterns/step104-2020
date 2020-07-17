package firebase.sdk.dashboard.data;

public enum Platform {
  ANDROID("Android"),
  IOS("iOS"),
  WEB("Web"),
  GAMES("Games")

  private String name;

  Platform(Sting enumName){
    this.name;
  }

  public String getName() {
      return name;
  }

}

