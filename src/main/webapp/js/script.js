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
  const versionNotes = document.getElementById(version).firstElementChild;
  if (versionNotes.style.display === "none") {
    versionNotes.style.display = "block";
  } else {
    versionNotes.style.display = "none";
  }
}

 $(document).ready(function () {
    var navListItems = $('div.setup-panel div a'),
        allWells = $('.setup-content'),
        allNextBtn = $('.nextBtn');

    allWells.hide();

    navListItems.click(function (e) {
        e.preventDefault();
        var $target = $($(this).attr('href')),
            $item = $(this);

        if (!$item.hasClass('disabled')) {
            navListItems.removeClass('btn-success').addClass('btn-default');
            $item.addClass('btn-success');
            allWells.hide();
            $target.show();
            $target.find('input:eq(0)').focus();
        }
    });

    allNextBtn.click(function () {
        var curStep = $(this).closest(".setup-content"),
            curStepBtn = curStep.attr("id"),
            nextStepWizard = $('div.setup-panel div a[href="#' + curStepBtn + '"]').parent().next().children("a"),
            curInputs = curStep.find("input[type='text'],input[type='url']"),
            isValid = true;

        $(".form-group").removeClass("has-error");
        for (var i = 0; i < curInputs.length; i++) {
            if (!curInputs[i].validity.valid) {
                isValid = false;
                $(curInputs[i]).closest(".form-group").addClass("has-error");
            }
        }

        if (isValid) nextStepWizard.removeAttr('disabled').trigger('click');
    });

    $('div.setup-panel div a.btn-success').trigger('click');
});

//Fetch data from API endpoint

//fetch for landing page
let doms = ["web", "android", "games", "ios"]

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

//fetch release pages
async function getReleases() {
   const response = await fetch('/v1/platforms/android/releases');
   const releases = await response.json();
   
    //Create text elements for each release detail
    for (i = 0; i < releases.length; i++) {
        let element = "#release-" + i;
        let release = releases[i];

        const divElement = document.querySelector(element);
        
        let name = release["releaseName"];
        const textNode0 = document.createTextNode(name); 
        divElement.appendChild(textNode0);
       
        divElement.appendChild(document.createElement("HR"));

        let deadline = release["launchDeadline"];
        const textNode1 = document.createTextNode("Launch Deadline: " + getDate(deadline)); 
        divElement.appendChild(textNode1);

        divElement.appendChild(document.createElement("HR"));

        let coldfreeze = release["codeFreezeDate"];
        const textNode3 = document.createTextNode("Cold Freeze Date: " + getDate(coldfreeze)); 
        divElement.appendChild(textNode3);

        divElement.appendChild(document.createElement("HR"));

        let launch = release["launchDate"];
        const textNode4 = document.createTextNode("Launch Date: " + getDate(launch)); 
        divElement.appendChild(textNode4); 
    }
}

//fetch release pages
async function getSDKs() {
   const response = await fetch('v1/platforms/android/releases/M78/sdks');
   const sdks = await response.json();
   
   const listSDKs = sdks["M78"];

  //Create header for release
   const headerElement = document.querySelector("#header");
   const headerText = document.createTextNode("M78");
   headerElement.appendChild(headerText);

   //Creating elements for all sdks
   console.log(listSDKs);
   for (i = 0; i < listSDKs.length; i++) {
        let element = "sdk" + i;
        console.log(element);
        console.log(listSDKs[i]);
        const divElement = document.getElementById(element);
        const textNode = document.createTextNode(listSDKs[i]); 
        divElement.appendChild(textNode);
   }

}

function getDate(time) {
    var date = new Date(time);
    return date;
}

getPlatforms();
getReleases();
getSDKs();
