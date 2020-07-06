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

  public SDKRelease(String libraryName, String platform, String release, String newVersion, String oldVersion, boolean majorVersionBump,
      HashMap<String, String> changes) {
    this.libraryName = libraryName;
    this.platform = platform;
    this.release = release;
    this.newVersion = newVersion;
    this.oldVersion = oldVersion;
    this.majorVersionBump = majorVersionBump;
    this.changes = changes;
  }

  // TODO: Add Getters and Setters
  }
}

