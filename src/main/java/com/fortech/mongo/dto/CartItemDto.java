package com.fortech.mongo.dto;

import lombok.Data;

@Data
public class CartItemDto {
	private String productId;
	private String productName;
	private Double productPrice;
	private int productQty;
}
