package com.fortech.mongo.entity;

import java.util.Date;
import java.util.LinkedHashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.querydsl.core.annotations.QueryEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@QueryEntity
@Document(collection = "orders")
@NoArgsConstructor
public class Order {
	@Id
	private String id;
	private Date orderDate;
	private Double totalPrice;
	private LinkedHashMap<String, Object> products;

}
