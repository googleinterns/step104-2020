async function getSDKRelease(platform, releaseName, libraryName) {
  const response = await fetch(`v1/platforms/${platform}/releases/${releaseName}/sdks/${libraryName}`);
  const sdkReleaseMetadata = await response.json();

  console.log(sdkReleaseMetadata);
}

getSDKRelease("android", "M78", "firebase-common");
