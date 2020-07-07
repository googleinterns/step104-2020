package com.google.capstone.data;

import java.util.ArrayList;

public class Release {
  private String platform;
  private String releaseName;
  private String releaseManager;
  private long launchDeadline;
  private long codeFreezeDate;
  private long launchDate;
  private ArrayList<String> enrolledSDKs;

  public Release(String platform, String releaseName, String releaseManager) {
    this.platform = platform;
    this.releaseName = releaseName;
    this.releaseManager = releaseManager;
    this.enrolledSDKs = new ArrayList<String>();
  }

  public void addEnrolledSDK(String SDKName) {
    this.enrolledSDKs.add(SDKName);
  }

  public void setLaunchDeadline(long timestamp) {
    this.launchDeadline = timestamp;
  }

  public void setCodeFreezeDate(long timestamp) {
    this.codeFreezeDate = timestamp;
  }

  public void setLaunchDate(long timestamp) {
    this.launchDate = timestamp;
  }

  // TODO: Add Getters and Setters
}

