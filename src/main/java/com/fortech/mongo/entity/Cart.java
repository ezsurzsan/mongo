package com.fortech.mongo.entity;

import java.util.LinkedHashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.querydsl.core.annotations.QueryEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@QueryEntity
@Document(collection = "carts")
@NoArgsConstructor
public class Cart {

	@Id
	private String id;
	private LinkedHashMap<String, Object> products;
	private Double totalPrice;

}
