package com.school.administration.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.school.administration.app.io.repositories.AddressRepositories;
import com.school.administration.app.service.AddressService;
import com.school.administration.app.shared.dto.AddressDto;

public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepositories addressRepositories;
	
	@Override
	public AddressDto getDetailAddress(String addressId) {
		// TODO Auto-generated method stub
		return null;
	}	
}
