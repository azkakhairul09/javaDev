package com.school.administration.app.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.school.administration.app.ui.io.entity.ProductsEntity;
import com.school.administration.app.ui.io.entity.RoleEntity;
import com.school.administration.app.ui.io.entity.UserEntity;
import com.school.administration.app.ScheduledTasks;
import com.school.administration.app.exceptions.UserServiceException;
import com.school.administration.app.io.repositories.ProductsRepository;
import com.school.administration.app.io.repositories.RoleRepository;
import com.school.administration.app.io.repositories.UserRepository;
import com.school.administration.app.shared.Utils;
import com.school.administration.app.service.UserService;
import com.school.administration.app.shared.dto.ProductsDto;
import com.school.administration.app.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	ProductsRepository productsRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	ProductsRepository productRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	ScheduledTasks scheduledTasks;
	
	@Autowired
	Utils utils;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findByUsername(username);
		
		if (userEntity == null) throw new UsernameNotFoundException(username+ " not found");
		
		return new User(userEntity.getUsername(), userEntity.getEncryptPassword(), 
				userEntity.getIsActive(),
				true, true,
				true, new ArrayList<>());
	}

	@Override
	public UserDto getUser(String username) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findByUsername(username);
		
		if (userEntity == null) throw new UserServiceException(username+ " not found");
		
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);
				
		return returnValue;
	}

	@Override
	public UserDto createUser(String roleId, UserDto user) {
		// TODO Auto-generated method stub
		//validation
		if (userRepository.findByUsername(user.getUsername()) != null 
			&& userRepository.findByEmail(user.getEmail()) != null 
			&&userRepository.findByFullName(user.getFullName()) != null)
		throw new RuntimeException("user is duplicate entry");
		if (roleRepository.findRoleIdByRoleId(roleId) == null) throw new RuntimeException("role id not found");
		
		if (user != null && roleRepository.findRoleIdByRoleId(roleId) != null) {
			
			UserDto userDto = new UserDto();
			userDto.setErrorCode("0");
			userDto.setStatus("success");
			
		}
		
		final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+7"));
		 
		Calendar currentTime = Calendar.getInstance();
		 
		String timeStr = formatter.format(currentTime.getTime());
		
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = modelMapper.map(user, UserEntity.class);
//		BeanUtils.copyProperties(user, userEntity);
		
		RoleEntity roleEntity = roleRepository.findRoleIdByRoleId(roleId);
		
		String publicUserId = utils.generateUserId(30);
		userEntity.setEncryptPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setUserId(publicUserId);
		userEntity.setIsActive(true);
		userEntity.setRoleId(roleEntity);
		userEntity.setRoleName(roleEntity.getRoleName());
		userEntity.setCreatedDate(timeStr);
		
		UserEntity storedUserDetails = userRepository.save(userEntity);
		
		UserDto returnValue = modelMapper.map(storedUserDetails, UserDto.class);
		
		return returnValue;
	}

	@Override
	public List<UserDto> getUsers(int page, int limit) {
		// TODO Auto-generated method stub
		List<UserDto> returnValue = new ArrayList<UserDto>();
		
		ModelMapper modelMapper = new ModelMapper();
		
		if (page>0) page = page-1;
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<UserEntity> usersPage = userRepository.findAll(pageableRequest);
		
		List<UserEntity> users = usersPage.getContent();
		
		for (UserEntity userEntity : users) {
			returnValue.add(modelMapper.map(userEntity, UserDto.class) );
		}
		
		return returnValue;
	}

	@Override
	public ProductsDto createProduct(ProductsDto product) {
		// TODO Auto-generated method stub
		if (productRepository.findByProductName(product.getProductName()) != null) throw new UserServiceException("product is duplicate entry");
		
		SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
		
		final String DATE_FORMAT = "dd/MM/yyyy";
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+7"));
		 
		Calendar currentTime = Calendar.getInstance();
		 
		String timeStr = formatter.format(currentTime.getTime());
		
		ModelMapper modelMapper = new ModelMapper();
		ProductsEntity productEntity = modelMapper.map(product, ProductsEntity.class);
		
		String productID = utils.generateProductId(5);
		
		productEntity.setProductId(productID);
		productEntity.setCreatedBy(authentication.getName());
		productEntity.setCreatedDate(timeStr);
		productEntity.setIsExpired(false);
		
		ProductsEntity productDetail = productRepository.save(productEntity);
		
		ProductsDto returnValue = modelMapper.map(productDetail, ProductsDto.class);
		return returnValue;
	}

	@Override
	public List<ProductsDto> getProducts(int page, int limit) {
		// TODO Auto-generated method stub
		List<ProductsDto> returnValue = new ArrayList<ProductsDto>();
		
		ModelMapper modelMapper = new ModelMapper();
		
		if (page>0) page = page-1;
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<ProductsEntity> productsPage = productsRepository.findAll(pageableRequest);
		
		List<ProductsEntity> products = productsPage.getContent();
		
		for (ProductsEntity productEntity : products) {
			returnValue.add(modelMapper.map(productEntity, ProductsDto.class) );
		}
		
		return returnValue;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		// TODO Auto-generated method stub
		UserDto returnValue = new UserDto();
		
		ModelMapper modelMapper = new ModelMapper();
		
		UserEntity userEntity = userRepository.findUserByUserId(userId);
		
		if (userEntity == null) throw new UserServiceException(
				"user not found");
		
		returnValue = modelMapper.map(userEntity, UserDto.class);
		
		return returnValue;
	}
}
