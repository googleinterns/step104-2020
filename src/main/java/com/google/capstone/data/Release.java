package com.google.capstone.data;

import java.util.HashMap;

public class Release {
  private String platform;
  private String releaseName;
  private HashMap<String, String> enrolledSDKs;

  public Release(String platform, String releaseName, HashMap<String, String> enrolledSDKs) {
    this.platform = platform;
    this.releaseName = releaseName;
    this.enrolledSDKs = enrolledSDKs;
  }

  // TODO: Add Getters and Setters
}

