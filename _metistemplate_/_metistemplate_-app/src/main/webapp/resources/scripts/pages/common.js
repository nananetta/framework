var common_js=
    {
        initialRequiredField: function () {
            var i = 0;
            $('[required]').each(function () {
                i = i+1;
            var id = $(this).attr('id');
            var $label = $("label[for=" + id + "]");
            if ($label.length > 0) {
                $label.html($label.html().replace(":","<span style='color:red;'>*</span><span>:</span>"));
            }
                ;

           });
        },

        initForceLabelShowRequired: function () {
            var index = $("label.required").text().lastIndexOf(":");
            $("label.required").text().lastIndexOf(":").replaceWith("<span>*</span>&nbsp;:");
        },
     
        validatorSetDefault: function ($) {           
            var defaultOptions = {
                highlight: function (element, errorClass, validClass) {
                    if ($(element).parents('.input-sumgroup').length > 0) {
                        $(element).closest(".input-sumgroup").addClass('has-error has-feedback').removeClass('has-success');
                    } else {
                        $(element).closest(".form-group").addClass('has-error has-feedback').removeClass('has-success');
                    }
                },
                unhighlight: function (element, errorClass, validClass) {
                    if ($(element).parents('.input-sumgroup').length > 0) {
                        $(element).closest(".input-sumgroup").removeClass('has-error has-success');
                    } else {
                        $(element).closest(".form-group").removeClass('has-error has-success');
                    }
                },
                ignore: '',
                errorElement: 'span',
                errorClass: 'help-block' , 
                errorPlacement: function (error, element) {
                    if (element.prop("type") === "radio") {
                        error.appendTo(element.parent().parent());
                    } else if (element.prop("type") === "checkbox") {
                        error.appendTo(element.parents(".checkbox"));
                    } else if (element.is('.valid-maxtodate')) {
                        error.insertAfter(element.parent().parent());
                    } else if (element.parents('.datepicker').length) {
                        error.insertAfter(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                },
            };
            $.validator.setDefaults(defaultOptions);
            $.validator.addMethod("acceptimage", $.validator.methods.accept,"Resources.validAcceptImage");
            $.validator.addMethod("uploadFile", function (val, element) {
                var size = 0;
                if (element.files.length != 0) {
                    size = element.files[0].size;
                }
                if (size > 4194304)// checks the file more than 4 MB
                {
                    return false;
                } else {
                    return true;
                }
            }, "Resources.validUploadFile" + " 4MB.");

            jQuery.validator.addMethod("phone", function (phone_number, element) {
                phone_number = phone_number.replace(/\s+/g, "");
                return this.optional(element) || phone_number.length > 9 &&
                phone_number.match(/^((\+[1-9]{1,4}[ \-]*)|(\([0-9]{2,3}\)[ \-]*)|([0-9]{2,4})[ \-]*)*?[0-9]{3,4}?[ \-]*[0-9]{3,4}?$/);
            }, "Please specify a valid phone number");

            $.validator.addMethod("sumgroup", function (val, element, params) {
                var parent = $(element).parents(".input-sumgroup");
                var fields = $(parent).find("input[type=number]");
                var total = 0.00;
                fields.each(function (x, item) {
                    total += parseFloat($(this).val());
                });

                if (total > parseFloat(params) && total != 0) {
                    return false;
                } else {
                    return true;
                }
            }, "Resources.validInputSumgroup");

            $.validator.addMethod("minselect",
                $.validator.methods.min,
                "Resources.validSelectOne");

            $.validator.addMethod('require-one', function (value) {
                return $('.require-one:checked').size() > 0;
            }, "Resources.validRequireOne");

            $.validator.addMethod('integer', function (value, element, param) {
                return (value == parseInt(value, 10) || value == "");
            }, "Resources.validInteger");

            $.validator.addMethod('maxtodate', function (value, element) {
                if (this.optional(element)) {
                    return true;
                };
                var result = false;
                try {
                    var date = $.datepicker.parseDate('dd/mm/yy', value);
                    var todaysDate = new Date();
                    if (date > todaysDate) {
                        result = false;
                    } else {
                        result = true;
                    }
                } catch (err) {
                    result = true;
                }
                return result;
            }, "Resources.validMaxTodate");

            $.validator.addClassRules({

                "valid-maxtodate": {
                    date: true,
                    maxtodate: true
                },
                "valid-thisyear": {
                    max: function () {
                        var thisyear = new Date().getFullYear();
                        return thisyear;
                    }
                },
                "valid-image": {
                    acceptimage: "image/*",
                    uploadFile: true
                },
                "valid-tel": {
                    phone: true
                },

                "valid-test-02": {
                    required: true,
                    digits: true,
                    minlength: 2,
                    maxlength: 5
                }
            });

        },
        validate_duplicates_id: function () {
            $('[id]').each(function () { // iterate all id's on the page
                var elements_with_specified_id = $('[id=' + this.id + ']');
                if (elements_with_specified_id.length > 1) {
                    //elements_with_specified_id.addClass('error')
                    console.error('Multiple IDs #' + this.id);
                }
            });
        }

}
