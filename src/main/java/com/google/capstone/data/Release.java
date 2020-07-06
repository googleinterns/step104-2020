package com.google.capstone.data;

import java.util.HashMap;

public class Release {
  private String platform;
  private String releaseNum;
  private HashMap<String, String> enrolledSDKs;

  public Release(String platform, String releaseNum, HashMap<String, String> enrolledSDKs) {
    this.platform = platform;
    this.releaseNum = releaseNum;
    this.enrolledSDKs = enrolledSDKs;
  }

  // TODO: Add Getters and Setters
}

