$(document).ready(function() {

    $("#ai-image-div").hide();

    // Function to show full-page loader
    function showLoader() {
        if ($("#fullPageLoader").length === 0) {
            $("body").append('<div id="fullPageLoader"><div class="loader"></div></div>');
        }
    }

    // Function to hide the loader
    function hideLoader() {
        $("#fullPageLoader").remove();
    }

    // Attach click event handler
    $("#btn-generate-answer").click(function() {
        var prompt = $("#prompt").val().trim();

        if (prompt === "") {
            alert("Please enter a valid text.");
            return;
        }

        $.ajax({
            url: "/prompt?message=" + encodeURIComponent(prompt),
            type: "GET",
            beforeSend: function() {
                showLoader();
            },
            success: function(result) {
                hideLoader();
                $("#response").html(result);
            },
            error: function(xhr, status, error) {
                hideLoader();
                $("#response").html("<span style='color:red;'>Error: " + xhr.responseText + "</span>");
            }
        });
    });


    $("#btn-generate-recipe").click(function() {
            var formData = $("#recipe-form").serializeArray();
                    var jsonData = {};

                    // Convert array to JSON object
                    $.each(formData, function(_, field) {
                        jsonData[field.name] = field.value.trim();
                    });

                    // Ensure at least one field is filled
                    if (!jsonData.ingredient || !jsonData.cuisine || !jsonData.dietaryRestriction) {
                        alert("Please fill out all fields before generating a recipe.");
                        return;
                    }

            $.ajax({
                        url: "/create-recipe",
                        type: "POST",
                        contentType: "application/json",
                        data: JSON.stringify(jsonData),
                        beforeSend: function() {
                            showLoader();
                        },
                        success: function(result) {
                            hideLoader();
                            $("#response").html(result);
                        },
                        error: function(xhr, status, error) {
                            hideLoader();
                            $("#response").html("<span style='color:red;'>Error: " + xhr.responseText + "</span>");
                        }
                    });
        });



      $("#btn-suggest-diet").click(function() {
                  var formData = $("#diet-form").serializeArray();
                          var jsonData = {};

                          // Convert array to JSON object
                          $.each(formData, function(_, field) {
                              jsonData[field.name] = field.value.trim();
                          });

                          // Ensure at least one field is filled
                          if (!jsonData.height || !jsonData.weight || !jsonData.age || !jsonData.dietGoal || !jsonData.gender
                               || !jsonData.activityLevel || !jsonData.dietaryRestrictions) {
                              alert("Please fill out all fields before generating a diet recipe.");
                              return;
                          }

                  $.ajax({
                              url: "/suggest-diet-recipe",
                              type: "POST",
                              contentType: "application/json",
                              data: JSON.stringify(jsonData),
                              beforeSend: function() {
                                  showLoader();
                              },
                              success: function(result) {
                                  hideLoader();
                                  $("#response").html(result);
                              },
                              error: function(xhr, status, error) {
                                  hideLoader();
                                  $("#response").html("<span style='color:red;'>Error: " + xhr.responseText + "</span>");
                              }
                          });
              });


             $("#btn-generate-image").click(function() {
                     var prompt = $("#image-prompt").val().trim();

                     if (prompt === "") {
                         alert("Please enter a valid text.");
                         return;
                     }

                     $.ajax({
                         url: "/image/"+encodeURIComponent(prompt) ,
                         type: "GET",
                         beforeSend: function() {
                             showLoader();
                         },
                         success: function(result) {
                             hideLoader();
                             $("#ai-image-div").show();
                             $("#ai-image").attr("src", result);
                         },
                         error: function(xhr, status, error) {
                             hideLoader();
                             alert(xhr.responseText)
                         }
                     });
                 });

});
