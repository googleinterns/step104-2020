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

