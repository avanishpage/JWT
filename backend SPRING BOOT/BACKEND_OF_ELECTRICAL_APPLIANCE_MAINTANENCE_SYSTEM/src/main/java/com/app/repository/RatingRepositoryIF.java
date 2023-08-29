package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Rating;

public interface RatingRepositoryIF extends JpaRepository<Rating, Long>{

}
