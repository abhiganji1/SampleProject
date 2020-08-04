package com.uvaan.sampleapi.validation;

import java.util.Date;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.Validator;

import com.uvaan.sampleapi.constants.Constants;
import com.uvaan.sampleapi.param.UserParam;
import com.uvaan.sampleapi.utils.CustomUtils;
import com.uvaan.sampleapi.utils.UserUtils;

public class UserValidation implements Validator {

	private static final Pattern EMAIL_PATTERN = Pattern.compile(Constants.EMAIL_PATTERN);
	private static final Pattern PASSWORD_PATTERN = Pattern.compile(Constants.PASSWORD_PATTERN);

	@Autowired
	UserUtils userUtils;

	@Override
	public boolean supports(Class<?> clazz) {
		return UserValidation.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors error) {
		UserParam params = (UserParam) target;

		addDefalts(params);

		if (isFieldValidFormat(params.getEmail(), EMAIL_PATTERN)) {
			error.rejectValue("mobile", Constants.BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		}

		if (isFieldValidFormat(params.getPassword(), PASSWORD_PATTERN)) {
			error.rejectValue("password", Constants.BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		}

		
		
	}

	public void validateupdate(Object target, Errors error) {

		validate(target, error);
		UserParam params = (UserParam) target;

		addDefalts(params);

		if (null == params.getId() || params.getId() <= 0) {
			error.rejectValue("id", Constants.BAD_REQUEST_ERROR_CD, "please provide valid entity id");
		}

	}

	private void addDefalts(UserParam params) {
		if (null == params.getId() || params.getId() <= 0) {
			//params.setCreatedBy(userUtils.getLogedInUser().getId());
			params.setCreatedDate(new Date());
		//	params.setUpdatedBy(userUtils.getLogedInUser().getId());
			params.setUpdaateddate(new Date());
			
		} else {
			//params.setUpdatedBy(userUtils.getLogedInUser().getId());
			params.setUpdaateddate(new Date());
			
		}

	}

	private static boolean isFieldValidFormat(String candidate, Pattern pattenrs) {
		return candidate == null ? true : !pattenrs.matcher(candidate).matches();
	}


	public void validateParameters(UserParam userParams, MapBindingResult err) {
		UserParam params = (UserParam) userParams;
		if (0 == (params.getCreatedby())) {
			params.setCreatedby(Constants.STARTING_OFFSET);
		}
		if (0 == (params.getUpdatedby())) {
			params.setUpdatedby(Constants.MAX_RESULTS_PER_PAGE);
		}
	}
}
