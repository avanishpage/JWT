package com.app.controller;

import static org.springframework.http.MediaType.IMAGE_GIF_VALUE;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.app.dto.PersonDtoWithRole;
import com.app.dto.PersonLoginDto;
import com.app.dto.PersonLoginOutDto;
import com.app.dto.PersonRegisterDto;
import com.app.dto.PersonUpdateDto;
import com.app.dto.ServiceDto;
import com.app.security.CustomUserDetailsServiceImpl;
import com.app.service.ImageHandlingIF;
import com.app.service.ServiceServiceLayerIF;
import com.app.service.VendorServiceLayerIF;

@RestController
@RequestMapping("/vendor")
@CrossOrigin(origins = "http://localhost:3000")
public class VendorController {

	@Autowired
	private VendorServiceLayerIF vendorServiceLayer;

	@Autowired
	private ServiceServiceLayerIF serviceService;
	
	@Autowired
	private ImageHandlingIF imgServiceLayer;
	@Autowired
	private CustomUserDetailsServiceImpl userService;

	// create vendor
	@PostMapping(value="/add",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ApiResponse createVendor(@ModelAttribute @Valid PersonRegisterDto vendorDto) {

		
		
		vendorServiceLayer.addVendor(vendorDto);
		
		userService.saveVendor(vendorDto.getEmail());
		
		return new ApiResponse("Vendor is added successfully");

	}

	// put method for updating vendor information
	@PutMapping("/update/{id}")
	public ApiResponse updateVendor(@RequestBody PersonUpdateDto vendorDto, @PathVariable Long id) {

		vendorServiceLayer.updateVendor(vendorDto, id);
		return new ApiResponse("Vendor Updating");
	}

	// get method for getting vendor information
	@GetMapping("/{vendorId}")
	public PersonLoginOutDto getVendorDetails(@PathVariable Long vendorId) {
		//System.out.println("in get customer " + vendorId);
		return vendorServiceLayer.getVendorDetails(vendorId);
	}

	// delete method for deleting vendor
	@DeleteMapping("/delete/{vendorId}")
	public ApiResponse deleteVendor(@PathVariable Long vendorId) {
		vendorServiceLayer.deleteVendor(vendorId);
		return new ApiResponse("Vendor deleted SIUUUU");
	}

	// post method for login
	@PostMapping("/login")
	public PersonDtoWithRole loginVendor(@RequestBody PersonLoginDto vendorLoginDto) {
		return vendorServiceLayer.verifyVendor(vendorLoginDto);
	}

	@GetMapping("/services/{vendorId}")
	public List<ServiceDto> getServicesOfVendor(@PathVariable Long vendorId) {

		return vendorServiceLayer.getAllServicesOf(vendorId);
	}

	
	@PutMapping("/update/{vendorid}/service/{serviceId}")
	public ApiResponse updateServiceforVendor(@RequestBody ServiceDto servicedto, @PathVariable Long vendorid,@PathVariable Long serviceId) {

		vendorServiceLayer.updateServiceofVendor(servicedto, vendorid, serviceId);
		return new ApiResponse("Service"+ serviceId + "vendor" + vendorid + "updated successfully");
	} 
		
	//image handling
	// Upload image
		
		@PostMapping(value = "/images", consumes = "multipart/form-data")
		public ResponseEntity<?> uploadImage(@RequestParam long vendorId, @RequestParam MultipartFile image)
				throws IOException {
			System.out.println("in upload image " + vendorId);
			return ResponseEntity.status(HttpStatus.CREATED).body(imgServiceLayer.uploadImageVendor(vendorId, image));
		}

	// download image
		
		@GetMapping(value = "/images/{vendorId}", produces =  {IMAGE_GIF_VALUE, IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE})
		public ResponseEntity<?> downloadImage(@PathVariable long vendorId) throws IOException {
			System.out.println("in download image " + vendorId);
			return ResponseEntity.ok(imgServiceLayer.serveImageCustomer(vendorId));
		}

		
		@GetMapping("/service/{serviceId}")
		public ServiceDto getSingleServiceById(@PathVariable Long serviceId) {
			
			return vendorServiceLayer.getSingleService(serviceId);
			
		}
	
}
