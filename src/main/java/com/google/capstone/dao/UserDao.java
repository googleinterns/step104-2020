package com.google.capstone.dao;

import com.google.capstone.data.User;

public interface UserDao {
  public User getUser(String id);
  public void addUser(User user);
  public boolean updateUserSettings(User user);
}
