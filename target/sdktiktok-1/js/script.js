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
