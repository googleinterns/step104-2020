async function getReleases(platform) {
  const response = await fetch(`/v1/platforms/${platform}/releases`);
  const releases = await response.json();

  // Create text elements for each release detail
  for (i = 0; i < 6; i++) {
    let element = "#release-" + i;
    let release = releases[i + 8];

    const divElement = document.querySelector(element);

    divElement.addEventListener("click", () => {
      const params = new URLSearchParams(`platform=${platform}&releaseName=${release['releaseName']}`);
      const page = "release_sdks.html";
      goToPage(page, params);
    });

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

function getDate(time) {
  var date = new Date(time * 1000);
  return date;
}

const urlParams = new URLSearchParams(window.location.search);
const platform = urlParams.get('platform');


getReleases(platform)
