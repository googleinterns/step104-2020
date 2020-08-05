async function getVersionHistory(platform, sdkName) {
  const response = await fetch(`v1/platforms/${platform}/sdks/${sdkName}`);
  const versionHistory = await response.json();
  console.log(versionHistory);

  const name = versionHistory["libraryName"];
  const owner = versionHistory["owner"];
  const externalName = versionHistory["externalName"];
  const libraryGroup = versionHistory["libraryGroup"];
  // TODO: Fetch for the latest version from endpoint
  const latestVersion = "19.2.9";

  createNode(name, "#sdk-name");
  createNode("External Name: " + externalName, "#external");
  createNode("Latest Version: " + latestVersion, "#latest-version");
  createNode("Library Group: " + libraryGroup, "#library-group");
  createNode("Owner: " + owner, "#owner");
}

function createNode(tag, id) {
  const tagElement = document.querySelector(id);
  const tagText = document.createTextNode(tag);
  tagElement.appendChild(tagText);
}

function favouriteSDK() {
  const star = document.getElementById("star");
  if (star.classList.contains("favourite")) {
    star.classList.remove("favourite");
    console.log("You unfavourited this SDK.");
    window.alert("You removed this SDK from your favourites!");
  } else {
    star.classList.add("favourite");
    console.log("You favourited this SDK.");
    window.alert("You added this SDK to your favourites!");
  }
}

const urlParams = new URLSearchParams(window.location.search);
const platform = urlParams.get('platform');
const libraryName = urlParams.get('sdkName');
if (!platform || !libraryName) {
  window.location.href = window.location.origin;
} else {
  getVersionHistory(platform, libraryName);
}
