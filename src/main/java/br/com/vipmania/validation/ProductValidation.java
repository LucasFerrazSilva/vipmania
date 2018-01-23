package br.com.vipmania.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.vipmania.model.Product;

public class ProductValidation implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object arg0, Errors errors) {
		Product product = (Product) arg0;
		
		ValidationUtils.rejectIfEmpty(errors, "name", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "description", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "category", "field.required");
		
		if(product.hasInvalidValue())
			errors.rejectValue("value", "invalid.value");
	}

}
