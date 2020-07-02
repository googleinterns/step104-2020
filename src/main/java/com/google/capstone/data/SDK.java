package com.google.capstone.data;

import java.util.HashMap;
import java.util.Map;

public class SDK {
  private String libraryName;
  private String libraryGroup;
  private String externalName;
  private String fireEscapeName;
  private String owner;
  private HashMap<String, SDKRelease> versionHistory;
  
  public SDK(String libraryName, String libraryGroup, String externalName, String fireEscapeName, String owner, HashMap<String, SDKRelease> versionHistory) {
    this.libraryName = libraryName;
    this.libraryGroup = libraryGroup;
    this.externalName = externalName;
    this.fireEscapeName = fireEscapeName;
    this.owner = owner;
    this.versionHistory = versionHistory;
  }

  // TODO: Add Getters and Setters
}

