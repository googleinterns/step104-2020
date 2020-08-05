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

getPlatforms();

