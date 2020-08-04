package com.uvaan.sampleapi.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.nimbusds.jose.util.DateUtils;
import com.uvaan.sampleapi.constants.Constants;
import com.uvaan.sampleapi.service.UserService;
@Component
public class UserUtils {

	@Autowired
	UserService userService;
	
	public static UserDetails getLogedInUserDetails() {
		Authentication authentication = SecurityContextHolder.getContext()
			    .getAuthentication();		
			return  (UserDetails) authentication.getPrincipal();			
	}
	
	public com.uvaan.sampleapi.domain.User getLogedInUser() {
		Authentication authentication = SecurityContextHolder.getContext()
			    .getAuthentication();
		User  user = (User) authentication.getPrincipal();
		return userService.findByEmail(user.getUsername()) ;			
	}
	
	
	static Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

	private static final SimpleDateFormat sdf = new SimpleDateFormat(Constants.MONTH_YEAR);
	
	public static boolean isDateValid(String date) {
		try {
			if (CustomUtils.isEmpty(date)) {
				return false;
			}

			sdf.setLenient(false);
			sdf.parse(date);
			return true;
		} catch (ParseException e) {
			LOGGER.error("isDateValid() - Not able to pase the date:" + date);
			return false;
		}
	}
	
}
