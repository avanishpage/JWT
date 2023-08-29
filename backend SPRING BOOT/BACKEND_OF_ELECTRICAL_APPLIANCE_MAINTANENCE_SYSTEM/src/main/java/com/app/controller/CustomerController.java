package com.app.controller;


import static org.springframework.http.MediaType.IMAGE_GIF_VALUE;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ApiResponse;
import com.app.dto.CustomerRegisterDto;
import com.app.dto.PersonDto;
import com.app.dto.PersonDtoWithRole;
import com.app.dto.PersonLoginDto;
import com.app.dto.PersonLoginOutDto;
import com.app.dto.PersonRegisterDto;
import com.app.dto.PersonUpdateDto;
import com.app.security.CustomUserDetailsServiceImpl;
import com.app.service.CustomerServiceLayerIF;
import com.app.service.ImageHandlingIF;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

	@Autowired
	private CustomerServiceLayerIF serviceLayer;
	@Autowired
	private ImageHandlingIF imgServiceLayer;
	@Autowired
	private CustomUserDetailsServiceImpl userService;

	// post method for adding a new customer entry
	@PostMapping(value="/add",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ApiResponse createCustomer(@ModelAttribute @Valid CustomerRegisterDto custDto) {

		serviceLayer.addCustomerAndCart(custDto);
		
		userService.saveCustomer(custDto.getEmail());
		
		return new ApiResponse("customer successfully created!");

	}

	// put method for updating existing customer's details
	@PutMapping("/update/{customerId}")
	public ApiResponse updateCustomer(@RequestBody PersonUpdateDto custDto, @PathVariable Long customerId) {

		serviceLayer.updateCust(custDto, customerId);
		return new ApiResponse("customer information successfully updated!");

	}

	// get method to get customer details
	@GetMapping("/{customerId}")
	@PreAuthorize("hasRole('CUSTOMER')")
	public PersonLoginOutDto getCustomerDetails(@PathVariable Long customerId) {
		return serviceLayer.getCustomerDetails(customerId);
	}

	// delete method to delete a customer permanently
	@DeleteMapping("/delete/{customerId}")
	public ApiResponse deleteCustomer(@PathVariable Long customerId) {
		serviceLayer.deleteCustomer(customerId);
		
		return new ApiResponse("customer successfully deleted!");
	}

	// customer login
	@PostMapping("/login")


	public PersonLoginOutDto loginCustomer(@RequestBody PersonLoginDto customerLoginDto) {



		return serviceLayer.verifyCustomer(customerLoginDto);
	}

	// image handling

	// 6. Upload image

	@PostMapping(value = "/images", consumes = "multipart/form-data")
	public ResponseEntity<?> uploadImage(@RequestParam long customerId, @RequestParam MultipartFile image)
			throws IOException {
		System.out.println("in upload image " + customerId);
		return ResponseEntity.status(HttpStatus.CREATED).body(imgServiceLayer.uploadImageCustomer(customerId, image));
	}

//7. download image

	@GetMapping(value = "/images/{customerId}", produces = { IMAGE_GIF_VALUE, IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE })
	public ResponseEntity<?> downloadImage(@PathVariable long customerId) throws IOException {
		System.out.println("in download image " + customerId);
		return ResponseEntity.ok(imgServiceLayer.serveImageCustomer(customerId));
	}

}
