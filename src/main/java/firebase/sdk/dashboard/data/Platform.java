package firebase.sdk.dashboard.data;

import java.util.HashMap;
import java.util.Map;

public enum Platform {
  
  ANDROID("Android"),
  IOS("iOS"),
  WEB("Web"),
  GAMES("Games");

  private String label;

  Platform(String label){
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
  
  private static final Map<String, Platform> enumMapping = new HashMap<>();

  static {
    // Maps string labels to the enum names
    for(Platform platform : Platform.values()){
       enumMapping.put(platform.getLabel(), platform);
    }
  }
 
  // Gets enum based on the string label
  public static Platform get(String enumName) {
    return enumMapping.get(enumName);
  }
}

