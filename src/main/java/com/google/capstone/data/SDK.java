package com.google.capstone.data;

import java.util.LinkedHashMap;

public class SDK {
  private String libraryName;
  private String libraryGroup;
  private String externalName;
  private String fireEscapeName;
  private String owner;
  private LinkedHashMap<String, SDKRelease> versionHistory;
  
  public SDK(String libraryName, String libraryGroup, String externalName) {
    this.libraryName = libraryName;
    this.libraryGroup = libraryGroup;
    this.externalName = externalName;
    this.versionHistory = new LinkedHashMap<String, SDKRelease>();
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public void setFireEscapeName(String fireEscapeName) {
    this.fireEscapeName = fireEscapeName;
  }

  public void addVersion(String version, SDKRelease sdkRelease) {
    this.versionHistory.put(version, sdkRelease);
  }

  // TODO: Add Getters and Setters
}

