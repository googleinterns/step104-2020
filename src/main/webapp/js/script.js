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

// Create link for favorite sdk
function createListElement(text) {
  const sdkElement = document.createElement('div');
  sdkElement.innerText = text;
  sdkElement.setAttribute('class','user-favorite');
  return sdkElement;
}

getPlatforms();

firebase.auth().onAuthStateChanged(async function(user) {
  if (user) {
    const response = await fetch(`v1/users/${user.uid}/sdks`);
    const favorites = await response.json();
    console.log(favorites);
    // Build the links for favorite sdks
    const favoriteSDK = document.getElementById('favorites');
    console.log(favoriteSDK);
    for (i = 0; i < favorites['ANDROID'].length; i++) {
      const sdkName = favorites['ANDROID'][i];
      favoriteSDK.appendChild(createListElement(favorites['ANDROID'][i]));
      favoriteSDK.appendChild(document.createElement("HR"));
      favoriteSDK.addEventListener("click", () => {
        const params = new URLSearchParams(`platform=android&sdkName=${sdkName}`);
        const page = "release_history.html";
        goToPage(page, params);
      });

      console.log(favorites[i]);
    }
  }
});
