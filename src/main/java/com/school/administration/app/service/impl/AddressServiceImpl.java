package com.school.administration.app.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.school.administration.app.exceptions.UserServiceException;
import com.school.administration.app.io.repositories.AddressRepositories;
import com.school.administration.app.service.AddressService;
import com.school.administration.app.shared.dto.AddressDto;
import com.school.administration.app.ui.io.entity.AddressEntity;

public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepositories audienceAdressRepositories;
	
	@Override
	public AddressDto getDetailAddress(String addressId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AddressDto getAddressByAudienceId(String audienceId) {
		// TODO Auto-generated method stub
		AddressDto returnValue = new AddressDto();
		
		ModelMapper modelMapper = new ModelMapper();
		
		AddressEntity addressEntity = audienceAdressRepositories.findAddressByAudienceId(audienceId);
		
		if (addressEntity == null) throw new UserServiceException("audience not found");
		
		returnValue = modelMapper.map(addressEntity, AddressDto.class);
		
		return returnValue;
	}
	
}
