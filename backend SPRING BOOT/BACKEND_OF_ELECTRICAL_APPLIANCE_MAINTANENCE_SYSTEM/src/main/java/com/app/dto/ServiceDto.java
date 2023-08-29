package com.app.dto;

import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.app.enums.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ServiceDto {

	private Long id;
	
	@NotBlank(message="name cannot be blank")
	private String name;
	
	@NotBlank(message="description cannot be blank")
	private String description;
	
	@NotNull(message="price cannot be null")
	private Long price;
	
	@NotNull(message="category cannot be null")
	private Category category;
	

}
