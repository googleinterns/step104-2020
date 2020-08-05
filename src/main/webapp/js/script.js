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

// Fetch data from API endpoint

//fetch for landing page
const doms = ["web", "android", "games", "ios"]

async function getPlatforms() {
  const response = await fetch('/v1/platforms');
  const platforms = await response.json();

  count = 0;
  for (let plat in platforms){
    if (plat == doms[count]){
      const divElement = document.getElementById(plat); 
      const textNode = document.createTextNode(platforms[plat]); 
      divElement.appendChild(textNode);
    }
    count+=1;
  }
}

async function getReleases(platform) {
  const response = await fetch(`/v1/platforms/${platform}/releases`);
  const releases = await response.json();

  // Create text elements for each release detail
  for (i = 0; i < releases.length; i++) {
    let element = "#release-" + i;
    let release = releases[i + 8];

    const divElement = document.querySelector(element);

    const name = release["releaseName"];
    const textNode0 = document.createTextNode(name); 
    divElement.appendChild(textNode0);

    divElement.appendChild(document.createElement("HR"));

    const deadline = release["launchCalDeadline"];
    const textNode1 = document.createTextNode("Launch Cal Deadline: " + getDate(deadline).toDateString()); 
    divElement.appendChild(textNode1);

    divElement.appendChild(document.createElement("HR"));

    const codefreeze = release["codeFreezeTime"];
    const textNode3 = document.createTextNode("Code Freeze Date: " + getDate(codefreeze).toDateString()); 
    divElement.appendChild(textNode3);

    divElement.appendChild(document.createElement("HR"));

    const launch = release["launchDate"];
    const textNode4 = document.createTextNode("Launch Date: " + getDate(launch).toDateString()); 
    divElement.appendChild(textNode4); 
  }
}

async function getReleaseSDKs(platform, releaseName) {
  const response = await fetch(`v1/platforms/${platform}/releases/${releaseName}/sdks`);
  const sdks = await response.json();
  
  // TODO: Remove console log
  console.log(sdks);

  // Create header for release
  const headerElement = document.querySelector("#header");
  const headerText = document.createTextNode(releaseName);
  headerElement.appendChild(headerText);

  // Creating elements for all sdks
  
  for (i = 0; i < sdks.length; i++) {
    let element = "sdk" + i;
    const divElement = document.getElementById(element);
    const textNode = document.createTextNode(sdks[i]); 
    divElement.appendChild(textNode);
  }
}

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

// Fetch favorite SDKS
async function getFavoriteSdks(userID) {
  const response = await fetch(`v1/users/${userID}/sdks`);
  const favorites = await response.json();
  // Build the links for favorite sdks
  const favoriteSDK = document.getElementById('favorites');
  console.log(favoriteSDK);
  for (i = 0; i < favorites.length; i++) {
    favoriteSDK.appendChild(createListElement(favorites[i]));
    favoriteSDK.appendChild(document.createElement("HR"));
    console.log(favorites[i]);
  }
}

// Create link for favorite sdk
function createListElement(text) {
  const sdkElement = document.createElement('a');
  sdkElement.innerText = text;
  sdkElement.setAttribute('class','user-favorite');
  sdkElement.setAttribute('href','product_release.html');
  return sdkElement;
}

function getDate(time) {
  var date = new Date(time * 1000);
  return date;
}


function createNode(tag, id) {
  const tagElement = document.querySelector(id);
  const tagText = document.createTextNode(tag);
  tagElement.appendChild(tagText);
}

// Sign user out of google sign-in
function googleSignOut() {
    firebase.auth().signOut().then(function() {
        Console.info("Sign out Successful");
        }).catch(function(error) {
            Console.info("Sign out Unsuccessful");
        });
}

getPlatforms();
getReleases("android");
getReleaseSDKs("android", "M76");
getVersionHistory("android", "firebase-common");
