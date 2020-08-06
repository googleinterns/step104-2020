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

function starSDK() {
  const star = document.getElementById("star");
  Promise.resolve(checkSDKInFavorites(platform, libraryName)).then(function(inUserFavorites) {
    if (inUserFavorites) {
      const response = unfavoriteSDK(platform, libraryName);
      console.log(response.json());
      if (star.classList.contains("favorite")) {
        star.classList.remove("favorite");
        console.log("You unfavorited this SDK.");
        window.alert("You removed this SDK from your favorites!");
      } 
    } else {
      const response = favoriteSDK(platform, libraryName);
      console.log(response);
      if (!star.classList.contains("favorite")) {
        star.classList.add("favorite");
        console.log("You favorited this SDK.");
        window.alert("You added this SDK to your favorites!");
      }
    }
  });
}

    const urlParams = new URLSearchParams(window.location.search);
    const platform = urlParams.get('platform');
    const libraryName = urlParams.get('sdkName');
    if (!platform || !libraryName) {
      window.location.href = window.location.origin;
    } else {
      getVersionHistory(platform, libraryName);
      firebase.auth().onAuthStateChanged(function(user) {
        if (user) {
          Promise.resolve(checkSDKInFavorites(platform, libraryName)).then(function(inUserFavorites) {
            if (inUserFavorites) {
              if (!star.classList.contains("favorite")) {
                star.classList.add("favorite");
                console.log("This sdk is in the user favorites.");
              }
            } else {
              star.classList.remove("favorite");
              console.log("This sdk is not in the user favorites.");
            } 
          });
        };
      });
    }
