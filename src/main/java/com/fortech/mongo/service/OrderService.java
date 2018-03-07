package com.fortech.mongo.service;

import com.fortech.mongo.entity.Cart;
import com.fortech.mongo.entity.Order;

public interface OrderService {

	Order createOrder(Cart cart);
}
