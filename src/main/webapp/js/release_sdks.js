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
    divElement.addEventListener("click", () => {
      const params = new URLSearchParams(
        `platform=${platform}&releaseName=${releaseName}&sdkName=${sdks[i]}}`);
      const page = "product_release.html";
      goToPage(page, params);
    });
  }
}

const urlParams = new URLSearchParams(window.location.search);
const platform = urlParams.get('platform');
const releaseName = urlParams.get('releaseName');

getReleaseSDKs(platform, releaseName);
