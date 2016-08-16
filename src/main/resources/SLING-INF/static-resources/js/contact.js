/**
 * Handles the contact form submission and includes the created resource's success view in the current page.
 */
$(function() {
    var handleContactSubmission = function(path) {
        return function() {
            var $form = $(this),
                formPath = path;
            $.ajax(formPath, {
                "method": "POST",
                "success": function(response) {
                    var $newForm = $(response);
                    $newForm.submit(handleContactSubmission(formPath));
                    $form.replaceWith($newForm);
                },
                "data": $form.serialize()
            });
            return false;
        }
    };

    $(".contact-form-placeholder").each(function() {
        var $placeholder = $(this),
            $formPath = $placeholder.attr("data-form-path");

        $.get($formPath, function(form) {
            var $form = $(form);
            $form.submit(handleContactSubmission($formPath));
            $placeholder.replaceWith($form);
        })
    });
});
