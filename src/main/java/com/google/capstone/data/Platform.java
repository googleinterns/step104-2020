package com.google.capstone.data;

import java.util.LinkedHashMap;

public class Platform {
  private String name;
  
  /* LinkedHashMap contains the name of the release and the codefreeze timestamp in descending
   * order (newest first). */
  private LinkedHashMap<String, long> releases;

  public Platform(String platformName, LinkedHashMap<String, long> releases) {
    this.name = platformName;
    this.releases = releases;
  }

  // TODO: Add Getters and Setters
}

