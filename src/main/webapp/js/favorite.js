// Fetch favorite SDKS
async function getFavoriteSDKs(userID) {
  const response = await fetch(`v1/users/${userID}/sdks`);
  const favorites = await response.json();
  console.log(favorites);
  // Build the links for favorite sdks
  const favoriteSDK = document.getElementById('favorites');
  console.log(favoriteSDK);
  for (i = 0; i < favorites.length; i++) {
    favoriteSDK.appendChild(createListElement(favorites[i]));
    favoriteSDK.appendChild(document.createElement("HR"));
    console.log(favorites[i]);
  }
  return favorites;
}

async function favoriteSDK(platform, sdkName) {
  const response = await fetch(`v1/users/${getCurrentUser().uid}/${platform}/sdks/${sdkName}`, {
    method: "POST",
    headers: {
      'Content-Type': 'application/json'
    }
  });
  console.log(response.json());
}

async function unfavoriteSDK(platform, sdkName) {
  const response = await fetch(`v1/users/${getCurrentUser().uid}/${platform}/sdks/${sdkName}`, {
    method: "DELETE",
    headers: {
      'Content-Type': 'application/json'
    }
  });
  console.log(response.json());
}

// Check if this sdk is in the users favorites
async function checkSDKInFavorites(platform, libraryName) {
  const favorites = getFavoriteSDKs(getCurrentUser().uid);
  if (favorites[platform.toUpperCase()]) {
    return favorites[platform.toUpperCase()].includes(libraryName);
  } else {
    return false;
  }
}
