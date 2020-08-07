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
    getFavoriteSDKs(user.uid);
  }
});
