package com.google.capstone.data;

import java.util.HashMap;

public class SDKRelease {
  private String libraryName;
  private String platform;
  private String release;
  private String newVersion;
  private String oldVersion;
  private boolean majorVersionBump;

  /* This will contain optional information like links to CLs, ariane entries, etc., */
  private HashMap<String, String> additionalInfo;

  public SDKRelease(String libraryName, String platform, String release, String newVersion, String oldVersion, boolean majorVersionBump) {
    this.libraryName = libraryName;
    this.platform = platform;
    this.release = release;
    this.newVersion = newVersion;
    this.oldVersion = oldVersion;
    this.majorVersionBump = majorVersionBump;
    this.additionalInfo = new HashMap<String, String>();;
  }

  public void addAdditionalInfo(String name, String value) {
    this.additionalInfo.put(name, value);
  }

  // TODO: Add Getters and Setters
}

