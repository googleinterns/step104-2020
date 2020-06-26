let globalCount = 0;
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

function loadFiveEarlierVersions() {
  console.log("Showing 5 more versions.");
  const releaseList = document.getElementById("release-history");
  for (let i = globalCount ; i < globalCount + 5; ++i) {
    const listNode = document.createElement("LI");
    const versionNum = (1926 - i).toString();
    const textNode = document.createTextNode(versionNum.substring(0, 2) + "." + versionNum.substring(2, 3) + "." +versionNum.substring(3, 4));
    listNode.appendChild(textNode);
    releaseList.appendChild(listNode);
  }
  globalCount += 5;
}

function showOrHideNotes(version) {
  console.log("Showing or hiding the notes of " + version);
  const versionNotes = document.getElementById(version).firstElementChild
  if (versionNotes.style.display === "none") {
    versionNotes.style.display = "block";
  } else {
    versionNotes.style.display = "none";
  }
}
