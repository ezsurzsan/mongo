package com.fortech.mongo.service;

import java.util.List;

import com.fortech.mongo.dto.CartItemDto;
import com.fortech.mongo.entity.Cart;

public interface CartService {

	List<CartItemDto> getCartItems(); // add string cartId

	Cart getCart();

	String addItemToCart(String productId, int qty);

	String deleteOneFromCart(String productId);

	String deleteAllFromCart(String productId);
}
