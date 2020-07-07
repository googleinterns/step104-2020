package com.google.capstone.data;

import java.util.ArrayList;

public class Platform {
  private String name;
  private long closestDeadline;
  
  private ArrayList<Release> releases;

  public Platform(String platformName) {
    this.name = platformName;
    this.releases = new ArrayList<Release>();
  }

  public void setClosestDeadline(long timestamp) {
    this.closestDeadline = timestamp;
  }

  public void addRelease(Release release) {
    releases.add(release);
  }

  // TODO: Add Getters and Setters
}

