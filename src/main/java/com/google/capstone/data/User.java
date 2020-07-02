package com.google.capstone.data;

import java.util.ArrayList;

public class User {
  private long id;
  private String email;
  private ArrayList<SDK> favorites;

  private User(long id, String email, ArrayList<SDK> favorites) {
    this.id = id;
    this.email = email;
    this.favorites = favorites;
  }

  public void addSDKToFavorites(SDK sdk) {
    if (favorites.contains(sdk)) {
      return;
    } else {
      favorites.add(sdk);
    }
  }

  public void removeSDKFromFavorites(SDK sdk) {
    if (favorites.contains(sdk)) {
      favorites.remove(sdk);
    }
  }

  // TODO: Add Getters and Setters
}
  
