package com.google.capstone.data;

import java.util.ArrayList;

public class Release {
  private String platform;
  private String releaseName;
  private ArrayList<String> enrolledSDKs;

  public Release(String platform, String releaseName, ArrayList<String> enrolledSDKs) {
    this.platform = platform;
    this.releaseName = releaseName;
    this.enrolledSDKs = enrolledSDKs;
  }

  // TODO: Add Getters and Setters
}

