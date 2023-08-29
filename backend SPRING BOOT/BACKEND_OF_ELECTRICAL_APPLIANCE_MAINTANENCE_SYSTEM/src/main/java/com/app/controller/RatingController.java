package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.RatingDto;
import com.app.service.RatingServiceIF;
import com.app.service.VendorServiceLayerIF;

@RestController
@RequestMapping("/rating")
@CrossOrigin(origins = "http://localhost:3000")
public class RatingController {
	
	@Autowired
	private RatingServiceIF ratingService;
	
	@Autowired
	private VendorServiceLayerIF vendorServiceLayer;
	
	@PostMapping("/add/{order_id}")
	public ApiResponse giveRating(@RequestBody RatingDto ratingdto,@PathVariable Long order_id) {

		ratingService.addRating(ratingdto, order_id);
		
		return new ApiResponse("Rating added for customer");
		
	}
	

	@GetMapping("/average/{vendor_id}")
	public Double getAverageRating(@PathVariable Long vendor_id) {
		
	//	System.out.println(vendor_id);
	return ratingService.calculateAverageRatingforVendor(vendor_id);
	}
	
	@GetMapping("/order/{order_id}")
	public RatingDto getRatingForOrder(@PathVariable Long order_id) {
		
		return ratingService.getRatingForOrder(order_id);	
	}
	

	@GetMapping("/{vendorId}")
	public List<RatingDto> getRatingOfVendor(@PathVariable Long vendorId)
	{
		return ratingService.getVendorRating(vendorId);
	}


}
