package com.school.administration.app.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.school.administration.app.ui.model.request.AudienceRequestModel;
import com.school.administration.app.ui.model.request.InvoiceRequestModel;
import com.school.administration.app.ui.model.request.ProductReqModel;
import com.school.administration.app.ui.model.request.UserRequestModel;
import com.school.administration.app.ui.model.response.AudienceResponse;
import com.school.administration.app.ui.model.response.InvoiceResponse;
import com.school.administration.app.ui.model.response.UserRest;
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
		path = "/user-registration",
		consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
		produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
		)
	public UserRest createUser(
			@RequestParam String roleId,
			@RequestBody UserRequestModel user) throws Exception {
		UserRest returnValue = new UserRest();
		
		if (user.getUsername().isEmpty()) throw new NullPointerException("Username may not be null");
		if (user.getPassword().isEmpty()) throw new NullPointerException("Password may not be null");
		
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(user, UserDto.class);
		
		UserDto createdUser = userService.createUser(roleId, userDto);
		returnValue = modelMapper.map(createdUser, UserRest.class);
		
		return returnValue;
	}
	
	@PostMapping(
		path = "/audience-registration",
		consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
		produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
		)
	public ResponseEntity<AudienceResponse> createAudience(
			@RequestBody AudienceRequestModel audience) throws Exception {
		AudienceResponse returnValue = new AudienceResponse();
		
		ModelMapper modelMapper = new ModelMapper();
		AudienceDto audienceDto = modelMapper.map(audience, AudienceDto.class);
		
		AudienceDto createdAudience = audienceService.createAudience(audienceDto);
		returnValue = modelMapper.map(createdAudience, AudienceResponse.class);
		
		return new ResponseEntity<AudienceResponse>(returnValue, HttpStatus.OK);
	}
	
	@GetMapping(
			path = "/get-all-users", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public List<UserRest> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
								   @RequestParam(value = "limit", defaultValue = "25") int limit) {
		List<UserRest> returnValue = new ArrayList<>();
		
		List<UserDto> users = userService.getUsers(page, limit);
		
		if (users != null && !users.isEmpty()) {
			java.lang.reflect.Type listType = new TypeToken<List<UserRest>>() {
			}.getType();
			returnValue = new ModelMapper().map(users, listType);
		}
		
		return returnValue;
	}
	
	@GetMapping(path = "/get-user", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public UserRest getUserDetail(@RequestParam(value = "userId") String userId) {

		UserRest returnValue = new UserRest();
		
		UserDto userDto = userService.getUserByUserId(userId);
		
		if (userDto != null) {
			java.lang.reflect.Type listType = new TypeToken<UserRest>() {
			}.getType();
			returnValue = new ModelMapper().map(userDto, listType);
		}

		return returnValue;
	}
	
	@PostMapping(
		path = "/create-product",
		consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
		produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
		)
	public ProductResponse createProduct(
			@RequestBody ProductReqModel product) throws Exception {
		ProductResponse returnValue = new ProductResponse();
		
		ModelMapper modelMapper = new ModelMapper();
		ProductsDto productDto = modelMapper.map(product, ProductsDto.class);
		
		ProductsDto createdProduct = userService.createProduct(productDto);
		returnValue = modelMapper.map(createdProduct, ProductResponse.class);
		
		return returnValue;
	}
	
	@GetMapping(
			path = "/get-all-products", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public List<ProductResponse> getProducts(@RequestParam(value = "page", defaultValue = "0") int page,
								   @RequestParam(value = "limit", defaultValue = "25") int limit) {
		List<ProductResponse> returnValue = new ArrayList<>();
		
		List<ProductsDto> products = userService.getProducts(page, limit);
		
		if (products != null && !products.isEmpty()) {
			java.lang.reflect.Type listType = new TypeToken<List<ProductResponse>>() {
			}.getType();
			returnValue = new ModelMapper().map(products, listType);
		}
		
		return returnValue;
	}
	
	@GetMapping(path = "/get-product", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ProductResponse getProductDetail(@RequestParam(value = "productId") String productId) {

		ProductResponse returnValue = new ProductResponse();
		
		ProductsDto productDto = userService.getProductByProductId(productId);
		
		if (productDto != null) {
			java.lang.reflect.Type listType = new TypeToken<ProductResponse>() {
			}.getType();
			returnValue = new ModelMapper().map(productDto, listType);
		}

		return returnValue;
	}
	
	@PostMapping(
		path = "/create-invoice",
		consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
		produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
		)
	public InvoiceResponse createInvoice(
			InvoiceRequestModel invoice,
			@RequestParam String audienceId, String productId ) throws Exception {
		InvoiceResponse returnValue = new InvoiceResponse();
		
		ModelMapper modelMapper = new ModelMapper();
		InvoiceDto invoiceDto = modelMapper.map(invoice, InvoiceDto.class);
		
		InvoiceDto createdInvoice = invoiceService.createInvoice(audienceId, productId, invoiceDto);
		returnValue = modelMapper.map(createdInvoice, InvoiceResponse.class);
		
		return returnValue;
	}
	
	@GetMapping(
			path = "/get-all-invoices", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public List<InvoiceResponse> getInvoices(@RequestParam(value = "page", defaultValue = "0") int page,
								   @RequestParam(value = "limit", defaultValue = "25") int limit) {
		List<InvoiceResponse> returnValue = new ArrayList<>();
		
		List<InvoiceDto> invoices = invoiceService.getInvoices(page, limit);
		
		if (invoices != null && !invoices.isEmpty()) {
			java.lang.reflect.Type listType = new TypeToken<List<InvoiceResponse>>() {
			}.getType();
			returnValue = new ModelMapper().map(invoices, listType);
		}
		
		return returnValue;
	}
	
	@GetMapping(path = "/get-invoice", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public InvoiceResponse getInvoiceDetail(@RequestParam(value = "invoiceId") String invoiceId) {

		InvoiceResponse returnValue = new InvoiceResponse();
		
		InvoiceDto invoiceDto = invoiceService.getInvoiceByInvoiceId(invoiceId);
		
		if (invoiceDto != null) {
			java.lang.reflect.Type listType = new TypeToken<InvoiceResponse>() {
			}.getType();
			returnValue = new ModelMapper().map(invoiceDto, listType);
		}

		return returnValue;
	}
}
