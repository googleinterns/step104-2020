package com.google.capstone.dao;

import com.google.capstone.data.SDK;

public interface SDKDao {
  public SDK getSDK(String platform, String libraryName);
  public boolean deleteSDK(String platform, String libraryName);
}
