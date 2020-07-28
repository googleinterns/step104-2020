
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

// Enroll Form Validation using bootstrapValidator Framework

$(document).ready(function(){
  $(".enrollForm").bootstrapValidator({

    message: "This value is not valid",
    feedbackIcons: {
      valid: 'glyphicon glyphicon-ok',
      invalid: 'glyphicon glyphicon-remove',
      validating: 'glyphicon glyphicon-refresh'
    },

    // Validation of fields on the Enrollment wizard form
    fields: {
      currentVersion: {
        message: 'The username is not valid',
        validators: {
          notEmpty: {
            message: 'The current version is required and cannot be empty'
          },

          // Regular Expression to check the format of semantic version
          regexp: {
            regexp: /^(\d+\.)?(\d+\.)?(\*|\d)$/,
            message: 'Incorrect format: Version should contain a major, minor and patch version numbers separated by a dot (.)'
          }
        }  
      },

      newReleaseVersion: {
        message: 'The username is not valid',
        validators: {
          notEmpty: {
            message: 'The current version is required and cannot be empty'
          },

          // Regular Expression to check the format of semantic version
          regexp: {
            regexp: /^(\d+\.)?(\d+\.)?(\*|\d)$/,
            message: 'Incorrect format: Version should contain a major, minor and patch version numbers separated by a dot (.)'
          }
        }  
      },
    }
  });
});
