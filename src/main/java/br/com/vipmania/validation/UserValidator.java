package br.com.vipmania.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.vipmania.model.User;

public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "name", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "email", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "password", "field.required");
	}

}