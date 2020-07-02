package com.google.capstone.dao;

import com.google.capstone.data.Release;
import com.google.capstone.data.SDKRelease;

public interface ReleaseDao {
  public Release getRelease(String platform, String releaseNum);
  public boolean addRelease(String platform, String releaseNum);
  public boolean deleteRelease(String platform, String releaseNum);
  public boolean enrollSDKInRelease(Release release, SDKRelease sdk);
  public boolean unenrollSDKInRelease(Release release, SDKRelease sdk);
  public boolean updateSDKEnrolledInRelease(Release release, SDKRelease oldSDKRelease, SDKRelease newSDKRelease);
}

