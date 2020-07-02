package com.google.capstone.data;

import java.util.HashMap;

public class SDKRelease {
  private String libraryName;
  private String platform;
  private String release;
  private String newVersion;
  private String oldVersion;
  private boolean majorVersionBump;
  private HashMap<String, String> changes;

  public SDKRelease(String libraryName, String platform, String newVersion, String oldVersion, boolean majorVersionBump) {
    this.libraryName = libraryName;
    this.platform = platform;
    this.newVersion = newVersion;
    this.oldVersion = oldVersion;
    this.majorVersionBump = majorVersionBump;
  }

  // TODO: Add Getters and Setters
  public void setRelease(Release release) {
    // TODO: implement this function once getters and setters for variables in data package are set
    return;
  }
}

