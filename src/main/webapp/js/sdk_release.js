async function getSDKRelease(platform, releaseName, libraryName) {
  const response = await fetch(`v1/platforms/${platform}/releases/${releaseName}/sdks/${libraryName}`);
  const sdkReleaseMetadata = await response.json();

  console.log(sdkReleaseMetadata);
  for (const [key, value] of Object.entries(sdkReleaseMetadata)) {
    if (key === "platform" || key === "libraryName") {
      continue;
    } else if (key === "additionalInfo") {
      for (const [innerKey, innerValue] of Object.entries(sdkReleaseMetadata[key])) {
        addTextToElement(innerKey, innerValue);
      }
    }
    addTextToElement(key, value);
  }
}

function addTextToElement(elementId, text) {
  const element = document.getElementById(elementId);
  if (!element) {
    // TODO: Leaving here for now because useful when debugging once full integration begins.
    console.log(`Not adding ${elementId} and ${text} because element not defined.`);
    return;
  }
  const textNode = document.createTextNode(text);
  element.appendChild(textNode);
}

function goToSDK() {
  const params = new URLSearchParams(`platform=${platform}&sdkName=${libraryName}`);
  const page = "release_history.html";
  goToPage(page, params);
}

async function disenrollSDK() {
  const disenroll = confirm(`Are you sure you want to disenroll ${libraryName} from ${releaseName}?`);
  if (disenroll) {
    const response = await fetch(`v1/platforms/${platform}/releases/${releaseName}/sdks/${libraryName}`, {
      method: "DELETE"
    });
    console.log(response.json());
  }
}

const urlParams = new URLSearchParams(window.location.search);
const platform = urlParams.get('platform');
const releaseName = urlParams.get('releaseName');
const libraryName = urlParams.get('sdkName');
if (!platform || !releaseName || !libraryName) {
  window.location.href = window.location.origin;
} else {
  getSDKRelease(platform, releaseName, libraryName);
}
