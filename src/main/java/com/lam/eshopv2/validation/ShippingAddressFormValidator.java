package com.lam.eshopv2.validation;

/**
 * Created by a.lam.tuan on 23. 5. 2018.
 */

import com.lam.eshopv2.form.ShippingAddressForm;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ShippingAddressFormValidator implements Validator {

    private EmailValidator emailValidator = EmailValidator.getInstance();

    // Validator này chỉ dùng để kiểm tra đối với ShippingAddressForm.
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == ShippingAddressForm.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ShippingAddressForm custInfo = (ShippingAddressForm) target;

        // Kiểm tra các trường (field) của ShippingAddressForm.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.ShippingAddressForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.ShippingAddressForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.ShippingAddressForm.address");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "NotEmpty.ShippingAddressForm.phone");

        if (!emailValidator.isValid(custInfo.getEmail())) {
            errors.rejectValue("email", "Pattern.ShippingAddressForm.email");
        }
    }

}