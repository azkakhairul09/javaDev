package com.school.administration.app.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.school.administration.app.exceptions.UserServiceException;
import com.school.administration.app.io.repositories.AudienceAddressRepositories;
import com.school.administration.app.io.repositories.AudienceRepositories;
import com.school.administration.app.service.AudienceService;
import com.school.administration.app.shared.Utils;
import com.school.administration.app.shared.dto.AudienceAddressDto;
import com.school.administration.app.shared.dto.AudienceDto;
import com.school.administration.app.ui.io.entity.AudienceEntity;

@Service
public class AudienceServiceImpl implements AudienceService {

	@Autowired
	AudienceRepositories audienceRepositories;
	
	@Autowired
	AudienceAddressRepositories audienceAddressRepositories;
	
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AudienceDto createAudience(AudienceDto audience) {
		// TODO Auto-generated method stub
		if(audienceRepositories.findByAudienceName(audience.getAudienceName()) != null) throw new UserServiceException("audience is already exist!");
		
		AudienceDto returnValue = new AudienceDto();
		AudienceAddressDto address = audience.getAddress();
		
		address.setAddressId(utils.generateAddressId(6));
				
		ModelMapper model = new ModelMapper();
		AudienceEntity audienceEntity = model.map(audience, AudienceEntity.class);
		
		final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+7"));
		 
		Calendar currentTime = Calendar.getInstance();
		 
		String timeStr = formatter.format(currentTime.getTime());
		
		String audienceId = utils.generateUserId(30);
		audienceEntity.setAudienceId(audienceId);
		audienceEntity.setCreatedDate(timeStr);
		audienceEntity.setEncryptPassword(bCryptPasswordEncoder.encode(audience.getPassword()));
		audienceEntity.setIsActive(true);
		AudienceEntity audienceDetail = audienceRepositories.save(audienceEntity);
		
		returnValue = model.map(audienceDetail, AudienceDto.class);
		return returnValue;
	}

}

