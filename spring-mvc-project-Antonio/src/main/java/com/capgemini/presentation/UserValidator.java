package com.capgemini.presentation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.capgemini.modelo.UserVO;

public class UserValidator implements Validator {

	
	public boolean supports(Class<?> clazz) {
		return UserVO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserVO user= (UserVO) target;
		
		ValidationUtils.rejectIfEmpty(errors, "login","user.login", "El campo login no puede estar vacío.");
		ValidationUtils.rejectIfEmpty(errors, "pwd","user.pwd", "El campo password no puede estar vacío.");

	}

}
