package br.com.vipmania.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.vipmania.model.Address;

public class AddressValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Address.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "receiver", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "cep", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "street", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "number", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "district", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "city", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "state", "field.required");
	}

}