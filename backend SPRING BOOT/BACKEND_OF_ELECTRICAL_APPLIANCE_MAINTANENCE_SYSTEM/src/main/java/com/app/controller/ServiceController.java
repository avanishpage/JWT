package com.app.controller;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.IMAGE_GIF_VALUE;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ApiResponse;
import com.app.dto.ServiceByCategoryDto;
import com.app.dto.ServiceDto;
import com.app.enums.Category;
import com.app.service.ImageHandlingIF;
import com.app.service.ServiceServiceLayerIF;

@RestController
@RequestMapping("/service")
@CrossOrigin(origins = "http://localhost:3000")
public class ServiceController {
	
	@Autowired
	private ServiceServiceLayerIF serviceService;
	
	@Autowired
	private ImageHandlingIF imgServiceLayer;

	//create and add a service to vendor
	@PostMapping("/add/{vendorId}")
	public ApiResponse addServiceToVendor(@Valid @RequestBody ServiceDto serviceDto,@PathVariable Long vendorId) {
		
		
		serviceService.attachServiceToVendor(serviceDto,vendorId);
		
		return new ApiResponse("service added to vendor successfully");
		
		
	}
	
	@GetMapping("/category")
	public List<ServiceByCategoryDto> getServiceByCategory(@RequestParam Category category){
		
		return serviceService.getServicesByCategory(category);
	}
	
	@DeleteMapping("/{serviceId}/vendor/{vendorId}/delete/")

	public ApiResponse deleteServiceOfVendorId(@PathVariable Long vendorId,@PathVariable Long serviceId ) {
	
		return serviceService.deleteServiceUsingVendorId(vendorId, serviceId);
	}

	@GetMapping("/categories")
	public List<Category> getCategoriesOfServices(){
		
		return serviceService.getAllCategories();
		
	}
	
	@GetMapping("/{city}")
	public List<ServiceDto> getAllServicesByCity(@PathVariable String city){
		return serviceService.getServicesByCity(city);
	}
	
	//image handling
	
	// 6. Upload image
		
		@PostMapping(value = "/images", consumes = "multipart/form-data")
		public ResponseEntity<?> uploadImage(@RequestParam long serviceId, @RequestParam MultipartFile image)
				throws IOException {
			System.out.println("in upload image " + serviceId);
			return ResponseEntity.status(HttpStatus.CREATED).body(imgServiceLayer.uploadImageCustomer(serviceId, image));
		}

	//7. download image
		
		@GetMapping(value = "/images/{serviceId}", produces =  {IMAGE_GIF_VALUE, IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE})
		public ResponseEntity<?> downloadImage(@PathVariable long serviceId) throws IOException {
			System.out.println("in download image " + serviceId);
			return ResponseEntity.ok(imgServiceLayer.serveImageCustomer(serviceId));
		}


	
}
