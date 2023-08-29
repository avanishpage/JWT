package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Card;

public interface CardRepository extends JpaRepository<Card,String> {

}
