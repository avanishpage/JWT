package com.app.service;

import java.util.List;

import com.app.dto.RatingDto;
import com.app.entity.Rating;

public interface RatingServiceIF {

	public void addRating(RatingDto ratingdto, Long order_id);

	public Double calculateAverageRatingforVendor(Long vendorId);

	public RatingDto getRatingForOrder(Long orderId);
	
	public List<RatingDto> getVendorRating(Long vendorId);
	
}
