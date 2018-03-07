package com.fortech.mongo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fortech.mongo.dto.CartItemDto;
import com.fortech.mongo.entity.Cart;
import com.fortech.mongo.entity.Order;
import com.fortech.mongo.service.CartService;
import com.fortech.mongo.service.OrderService;

@Controller
public class CartController {
	@Autowired
	CartService cartService;
	@Autowired
	OrderService orderService;

	@RequestMapping("/cartItem")
	public String product(Model model) {
		Cart cart = cartService.getCart();
		List<CartItemDto> cartItemList = cartService.getCartItems();
		model.addAttribute("cartItems", cartItemList);
		model.addAttribute("totalPrice", cart.getTotalPrice());
		return "cartItem";
	}

	@RequestMapping("/completeOrder")
	public String completeOrder() {
		Order order = orderService.createOrder(cartService.getCart());
		// model.addAttribute("order", order);
		return "redirect:/product";
	}

}
