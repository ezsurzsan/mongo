package com.fortech.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.querydsl.core.annotations.QueryEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@QueryEntity
@Document(collection = "products")
@NoArgsConstructor
public class Product {
	@Id
	private String id;
	private String prodName;
	private String prodDesc;
	private String brand;
	private Double prodPrice;
	private String prodImage;
	private int prodQty;
	private String os;

}
