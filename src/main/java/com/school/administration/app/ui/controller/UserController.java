package com.school.administration.app.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.administration.app.service.AudienceService;
import com.school.administration.app.service.InvoiceService;
import com.school.administration.app.service.UserService;
import com.school.administration.app.shared.dto.AudienceDto;
import com.school.administration.app.shared.dto.InvoiceDto;
import com.school.administration.app.shared.dto.ProductsDto;
import com.school.administration.app.shared.dto.UserDto;
import com.school.administration.app.ui.model.contentResponse.ContentAudience;
import com.school.administration.app.ui.model.contentResponse.ContentAudiences;
import com.school.administration.app.ui.model.contentResponse.ContentInvoice;
import com.school.administration.app.ui.model.contentResponse.ContentInvoices;
import com.school.administration.app.ui.model.contentResponse.ContentProduct;
import com.school.administration.app.ui.model.contentResponse.ContentProducts;
import com.school.administration.app.ui.model.contentResponse.ContentUser;
import com.school.administration.app.ui.model.contentResponse.ContentUsers;
import com.school.administration.app.ui.model.request.AudienceRequestModel;
import com.school.administration.app.ui.model.request.InvoiceRequestModel;
import com.school.administration.app.ui.model.request.ProductRequestModel;
import com.school.administration.app.ui.model.request.UserRequestModel;
import com.school.administration.app.ui.model.response.AudienceResponse;
import com.school.administration.app.ui.model.response.InvoiceResponse;
import com.school.administration.app.ui.model.response.UserResponse;
import com.school.administration.app.ui.model.response.ProductResponse;


@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	InvoiceService invoiceService;
	
	@Autowired
	AudienceService audienceService;

	
	@PostMapping(
		path = "/audience-registration",
		consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
		produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
		)
	public ContentAudience createAudience(
			@RequestParam String roleId,
			@RequestBody AudienceRequestModel audience) throws Exception {
		AudienceResponse returnValue = new AudienceResponse();
		
		ContentAudience result = new ContentAudience();
		
		ModelMapper modelMapper = new ModelMapper();
		AudienceDto audienceDto = modelMapper.map(audience, AudienceDto.class);
		
		AudienceDto createdAudience = audienceService.createAudience(roleId, audienceDto);
		returnValue = modelMapper.map(createdAudience, AudienceResponse.class);
		
		result.setContent(returnValue);
		result.setErrorCode("201");
		result.setErrorDesc("created");
		
		return result;
	}
	
	@GetMapping(
			path = "/get-all-audiences", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ContentAudiences getAudiences(@RequestParam(value = "page", defaultValue = "0") int page,
								   @RequestParam(value = "limit", defaultValue = "25") int limit) {
		List<AudienceResponse> returnValue = new ArrayList<>();
		
		ContentAudiences result = new ContentAudiences();
		
		List<AudienceDto> audiences = audienceService.getAudiences(page, limit);
		
		if (audiences != null && !audiences.isEmpty()) {
			java.lang.reflect.Type listType = new TypeToken<List<AudienceResponse>>() {
			}.getType();
			returnValue = new ModelMapper().map(audiences, listType);
		}
		
		result.setContent(returnValue);
		result.setErrorCode("0");
		result.setErrorDesc("success get audiences");
		
		return result;
	}
	
	@GetMapping(path = "/get-audience", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ContentAudience getAudienceDetail(@RequestParam(value = "audienceId") String audienceId) {

		AudienceResponse returnValue = new AudienceResponse();
		
		ContentAudience result = new ContentAudience();
		
		AudienceDto audienceDto = audienceService.getAudienceByAudienceId(audienceId);
		
		if (audienceDto != null) {
			java.lang.reflect.Type listType = new TypeToken<AudienceResponse>() {
			}.getType();
			returnValue = new ModelMapper().map(audienceDto, listType);
		}

		result.setContent(returnValue);
		result.setErrorCode("0");
		result.setErrorDesc("success get audience");
		
		return result;
	}
	
	@PostMapping(
		path = "/user-registration",
		consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
		produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
		)
	public ContentUser createUser(
			@RequestBody UserRequestModel user) throws Exception {
		UserResponse returnValue = new UserResponse();
		
		ContentUser result = new ContentUser();
		
		if (user.getUsername().isEmpty()) throw new NullPointerException("Username may not be null");
		if (user.getPassword().isEmpty()) throw new NullPointerException("Password may not be null");
		
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(user, UserDto.class);
		
		UserDto createdUser = userService.createUser(userDto);
		returnValue = modelMapper.map(createdUser, UserResponse.class);
		
		result.setContent(returnValue);
		result.setErrorCode("201");
		result.setErrorDesc("created");
		
		return result;
	}
	
	@GetMapping(
			path = "/get-all-users", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ContentUsers getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
								   @RequestParam(value = "limit", defaultValue = "25") int limit) {
		List<UserResponse> returnValue = new ArrayList<>();
		
		ContentUsers result = new ContentUsers();
		
		List<UserDto> users = userService.getUsers(page, limit);
		
		if (users != null && !users.isEmpty()) {
			java.lang.reflect.Type listType = new TypeToken<List<UserResponse>>() {
			}.getType();
			returnValue = new ModelMapper().map(users, listType);
		}
		
		result.setContent(returnValue);
		result.setErrorCode("0");
		result.setErrorDesc("success get users");
		
		return result;
	}
	
	@GetMapping(path = "/get-user", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ContentUser getUserDetail(@RequestParam(value = "userId") String userId) {

		UserResponse returnValue = new UserResponse();
		
		ContentUser result = new ContentUser();
		
		UserDto userDto = userService.getUserByUserId(userId);
		
		if (userDto != null) {
			java.lang.reflect.Type listType = new TypeToken<UserResponse>() {
			}.getType();
			returnValue = new ModelMapper().map(userDto, listType);
		}

		result.setContent(returnValue);
		result.setErrorCode("0");
		result.setErrorDesc("success get user");
		
		return result;
	}
	
	@PutMapping(path = "/disactivate-user",
		consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
		produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
		)
	public ContentUser disactivateUser(@RequestParam(value = "userId") String userId) {
		UserResponse returnValue = new UserResponse();
		
		ContentUser result = new ContentUser();
		
		UserDto userDto = new UserDto();
		
		UserDto updatedUser = userService.disactiveUser(userId, userDto);
		BeanUtils.copyProperties(updatedUser, returnValue);
		
		result.setContent(returnValue);
		result.setErrorCode("0");
		result.setErrorDesc("success disactivated");
		
		return result;	
	}
	
	@PostMapping(
		path = "/create-product",
		consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
		produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
		)
	public ContentProduct createProduct(
			@RequestBody ProductRequestModel product) throws Exception {
		ProductResponse returnValue = new ProductResponse();
		
		ContentProduct result = new ContentProduct();
		
		ModelMapper modelMapper = new ModelMapper();
		ProductsDto productDto = modelMapper.map(product, ProductsDto.class);
		
		ProductsDto createdProduct = userService.createProduct(productDto);
		returnValue = modelMapper.map(createdProduct, ProductResponse.class);
		
		result.setContent(returnValue);
		result.setErrorCode("201");
		result.setErrorDesc("created");
		
		return result;
	}
	
	@GetMapping(
			path = "/get-all-products", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ContentProducts getProducts(@RequestParam(value = "page", defaultValue = "0") int page,
								   @RequestParam(value = "limit", defaultValue = "25") int limit) {
		List<ProductResponse> returnValue = new ArrayList<>();
		
		ContentProducts result = new ContentProducts();
		
		List<ProductsDto> products = userService.getProducts(page, limit);
		
		if (products != null && !products.isEmpty()) {
			java.lang.reflect.Type listType = new TypeToken<List<ProductResponse>>() {
			}.getType();
			returnValue = new ModelMapper().map(products, listType);
		}
		
		result.setContent(returnValue);
		result.setErrorCode("0");
		result.setErrorDesc("success get products");
		
		return result;
	}
	
	@GetMapping(path = "/get-product", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ContentProduct getProductDetail(@RequestParam(value = "productId") String productId) {

		ProductResponse returnValue = new ProductResponse();
		
		ContentProduct result = new ContentProduct();
		
		ProductsDto productDto = userService.getProductByProductId(productId);
		
		if (productDto != null) {
			java.lang.reflect.Type listType = new TypeToken<ProductResponse>() {
			}.getType();
			returnValue = new ModelMapper().map(productDto, listType);
		}
		
		result.setContent(returnValue);
		result.setErrorCode("0");
		result.setErrorDesc("success get product");

		return result;
	}
	
	@PostMapping(
		path = "/create-invoice",
		consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
		produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
		)
	public InvoiceResponse createInvoice(
			InvoiceRequestModel invoice,
			@RequestParam String userId, String productId ) throws Exception {
		InvoiceResponse returnValue = new InvoiceResponse();
		
		ModelMapper modelMapper = new ModelMapper();
		InvoiceDto invoiceDto = modelMapper.map(invoice, InvoiceDto.class);
		
		InvoiceDto createdInvoice = invoiceService.createInvoice(userId, productId, invoiceDto);
		returnValue = modelMapper.map(createdInvoice, InvoiceResponse.class);
		
		return returnValue;
	}
	
	@GetMapping(
			path = "/get-all-invoices", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ContentInvoices getInvoices(@RequestParam(value = "page", defaultValue = "0") int page,
								   @RequestParam(value = "limit", defaultValue = "25") int limit) {
		List<InvoiceResponse> returnValue = new ArrayList<>();
		
		ContentInvoices result = new ContentInvoices();
		
		List<InvoiceDto> invoices = invoiceService.getInvoices(page, limit);
		
		if (invoices != null && !invoices.isEmpty()) {
			java.lang.reflect.Type listType = new TypeToken<List<InvoiceResponse>>() {
			}.getType();
			returnValue = new ModelMapper().map(invoices, listType);
		}
		
		result.setContent(returnValue);
		result.setErrorCode("0");
		result.setErrorDesc("success get invoices");
		
		return result;
	}
	
	@GetMapping(path = "/get-invoice", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ContentInvoice getInvoiceDetail(@RequestParam(value = "invoiceId") String invoiceId) {

		InvoiceResponse returnValue = new InvoiceResponse();
		
		ContentInvoice result = new ContentInvoice();
		
		InvoiceDto invoiceDto = invoiceService.getInvoiceByInvoiceId(invoiceId);
		
		if (invoiceDto != null) {
			java.lang.reflect.Type listType = new TypeToken<InvoiceResponse>() {
			}.getType();
			returnValue = new ModelMapper().map(invoiceDto, listType);
		}

		result.setContent(returnValue);
		result.setErrorCode("0");
		result.setErrorDesc("success get invoice");
		
		return result;
	}
//	
//	@GetMapping(path = "/get-address", produces = { MediaType.APPLICATION_XML_VALUE,
//			MediaType.APPLICATION_JSON_VALUE })
//	public ContentAddress getAddress(@RequestParam(value = "userId") String userId) {
//
//		AddressResponse returnValue = new AddressResponse();
//		
//		ContentAddress result = new ContentAddress();
//		
//		AddressDto addressDto = addressService.getAddressByAudienceId(userId);
//		
//		if (addressDto != null) {
//			java.lang.reflect.Type listType = new TypeToken<AddressResponse>() {
//			}.getType();
//			returnValue = new ModelMapper().map(addressDto, listType);
//		}
//
//		result.setContent(returnValue);
//		result.setErrorCode("0");
//		result.setErrorDesc("success get address");
//		
//		return result;
//	}
}
