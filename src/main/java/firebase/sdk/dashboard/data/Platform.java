package firebase.sdk.dashboard.data;

import java.util.HashMap;
import java.util.Map;

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

  private static final Map<String, Platform> enumMapping = new HashMap<>();

  static{

    for(Platform plat : Platform.values()){
       enumMapping.put(plat.getLabel(), plat);
    }
  }
 
  public static Platform get(String enumName) {
    return enumMapping.get(enumName);
  }

}

