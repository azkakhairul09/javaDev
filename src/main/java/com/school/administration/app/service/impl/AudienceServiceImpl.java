package com.school.administration.app.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.school.administration.app.exceptions.UserServiceException;
import com.school.administration.app.io.repositories.AddressRepositories;
import com.school.administration.app.io.repositories.AudienceRepositories;
import com.school.administration.app.io.repositories.RoleRepository;
import com.school.administration.app.io.repositories.UserRoleRepositories;
import com.school.administration.app.service.AudienceService;
import com.school.administration.app.shared.Utils;
import com.school.administration.app.shared.dto.AddressDto;
import com.school.administration.app.shared.dto.AudienceDto;
import com.school.administration.app.ui.io.entity.AudienceEntity;
import com.school.administration.app.ui.io.entity.UserRoleEntity;

@Service
public class AudienceServiceImpl implements AudienceService {

	@Autowired
	AudienceRepositories audienceRepositories;
	
	@Autowired
	UserRoleRepositories userRoleRepositories;
	
	@Autowired
	AddressRepositories addressRepositories;
	
	@Autowired
	RoleRepository roleRepository;
	
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
	public AudienceDto createAudience(String roleId, AudienceDto audience) {
		// TODO Auto-generated method stub
		if(audienceRepositories.findByAudienceName(audience.getAudienceName()) != null) throw new UserServiceException("audience is duplicate entry");
		
		AudienceDto returnValue = new AudienceDto();
		AddressDto address = audience.getAddress();
		
		address.setAddressId(utils.generateAddressId(6));
				
		ModelMapper model = new ModelMapper();
		AudienceEntity audienceEntity = model.map(audience, AudienceEntity.class);
		
		final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+7"));
		 
		Calendar currentTime = Calendar.getInstance();
		 
		String timeStr = formatter.format(currentTime.getTime());
		
		String audienceId = utils.generateUserId(30);
		audienceEntity.setAudienceId(audienceId);
		audienceEntity.setRoleId(roleId);
		audienceEntity.setCreatedDate(timeStr);
		audienceEntity.setEncryptPassword(bCryptPasswordEncoder.encode(audience.getPassword()));
		audienceEntity.setIsActive(true);
		
		UserRoleEntity userRoleEntity = new UserRoleEntity();
		
		userRoleEntity.setAudienceId(audienceId);
		userRoleEntity.setRoleId(roleId);
		
		userRoleRepositories.save(userRoleEntity);
		
		AudienceEntity audienceDetail = audienceRepositories.save(audienceEntity);
		
		returnValue = model.map(audienceDetail, AudienceDto.class);
		
		return returnValue;
	}

	@Override
	public List<AudienceDto> getAudiences(int page, int limit) {
		// TODO Auto-generated method stub
		List<AudienceDto> returnValue = new ArrayList<AudienceDto>();
		
		ModelMapper modelMapper = new ModelMapper();
		
		if (page>0) page = page-1;
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<AudienceEntity> audiencePage = audienceRepositories.findAll(pageableRequest);
		
		List<AudienceEntity> audiences = audiencePage.getContent();
		
		for (AudienceEntity audienceEntity : audiences) {
			returnValue.add(modelMapper.map(audienceEntity, AudienceDto.class) );
		}
		
		return returnValue;
	}

	@Override
	public AudienceDto getAudienceByAudienceId(String audienceId) {
		// TODO Auto-generated method stub
		AudienceDto returnValue = new AudienceDto();
		
		ModelMapper modelMapper = new ModelMapper();
		
		AudienceEntity audienceEntity = audienceRepositories.findAudienceByAudienceId(audienceId);
		
		if (audienceEntity == null) throw new UserServiceException(
				"audience not found");
		
		returnValue = modelMapper.map(audienceEntity, AudienceDto.class);
		
		return returnValue;	
	}
}

