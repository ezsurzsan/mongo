package com.fortech.mongo.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortech.mongo.entity.Cart;
import com.fortech.mongo.entity.Order;
import com.fortech.mongo.repository.CartRepository;
import com.fortech.mongo.repository.OrderRepository;
import com.fortech.mongo.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	CartRepository cartRepository;

	@Override
	public Order createOrder(Cart cart) {
		Order order = new Order();
		order.setOrderDate(new Date());
		order.setProducts(cart.getProducts());
		cartRepository.delete(cart);
		orderRepository.save(order);
		return order;

	}

}
