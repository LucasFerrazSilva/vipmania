package br.com.vipmania.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.vipmania.model.NewUserPassword;

public class NewUserPasswordValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return NewUserPassword.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "newPassword", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "actualPassword", "field.required");

		NewUserPassword obj = (NewUserPassword) target;
		
		if(!obj.isActualPasswordValid())
			errors.rejectValue("actualPassword", "invalid.value");
	}

}