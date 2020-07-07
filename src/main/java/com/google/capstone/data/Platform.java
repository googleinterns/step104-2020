package com.google.capstone.data;

import java.util.LinkedHashMap;
import java.util.HashMap;

public class Platform {
  private String name;
  
  /* LinkedHashMap with the key being a release name and the value being a HashMap
   * containing the name of the release manager, the code freeze date as a timestamp,
   * the launch deadline as a timestamp and the launch date as a timestamp. It will be sorted
   * in descending order (newest launch timestamp first). */
  private LinkedHashMap<String, HashMap<String, Object>> releases;

  public Platform(String platformName, LinkedHashMap<String, HashMap<String, Object>> releases) {
    this.name = platformName;
    this.releases = releases;
  }

  // TODO: Add Getters and Setters
}

