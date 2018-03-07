package com.fortech.mongo.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortech.mongo.dto.CartItemDto;
import com.fortech.mongo.entity.Cart;
import com.fortech.mongo.entity.Product;
import com.fortech.mongo.repository.CartRepository;
import com.fortech.mongo.repository.ProductRepository;
import com.fortech.mongo.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private ProductRepository productRepository;


	@Override
	public List<CartItemDto> getCartItems() {
		List<CartItemDto> cartItemList = new ArrayList<>();
		LinkedHashMap<String, Object> products = getCart().getProducts();

		for (Entry<String, Object> entry : products.entrySet()) {
			Product product = productRepository.findOne(entry.getKey());
			CartItemDto dto = new CartItemDto();
			dto.setProductId(entry.getKey());
			dto.setProductName(product.getProdName());
			dto.setProductPrice(product.getProdPrice());
			dto.setProductQty((int) entry.getValue());
			cartItemList.add(dto);
		}

		return cartItemList;
	}

	@Override
	public Cart getCart() {
		Cart cart = new Cart();
		if (cartRepository.findAll().size() != 0) {
			cart = cartRepository.findAll().iterator().next();
		}

		return cart;
	}

	@Override
	public String addItemToCart(String productId, int qty) {
		Cart cart = getCart();
		Product product = productRepository.findOne(productId);
		if (product.getProdQty() - qty < 0) {
			return "redirect:/product";

		}
		LinkedHashMap<String, Object> productList = new LinkedHashMap<>();
		if (cart.getProducts() != null) {
			productList = cart.getProducts();
		}
		if (productList != null && productList.containsKey(productId)) {
			productList.put(productId, (int) productList.get(productId) + qty);

		} else {
			productList.put(productId, qty);
		}
		DecimalFormat df = new DecimalFormat("#.##");
		cart.setTotalPrice(Double.valueOf(df.format(cart.getTotalPrice() + product.getProdPrice() * qty)));
		product.setProdQty(product.getProdQty() - qty);
		cart.setProducts(productList);
		cartRepository.save(cart);
		productRepository.save(product);
		return "redirect:/cartItem";

	}

	@Override
	public String deleteOneFromCart(String productId) {
		Product product = productRepository.findOne(productId);
		Cart cart = getCart();
		LinkedHashMap<String, Object> productList = cart.getProducts();
		if (productList != null && productList.containsKey(productId)) {
			if ((int) productList.get(productId) >= 1) {
				productList.put(productId, (int) productList.get(productId) - 1);
				product.setProdQty(product.getProdQty() + 1);
				cart.setTotalPrice(cart.getTotalPrice() - product.getProdPrice());
			}
			if ((int) productList.get(productId) == 0) {
				productList.remove(productId);
			}
		}
		cart.setProducts(productList);
		productRepository.save(product);
		cartRepository.save(cart);
		return "redirect:/cartItem";

	}

	@Override
	public String deleteAllFromCart(String productId) {
	
		
		Product product = productRepository.findOne(productId);
		Cart cart = getCart();
		LinkedHashMap<String, Object> productList = cart.getProducts();
		if (productList != null && productList.containsKey(productId)) {
			product.setProdQty(product.getProdQty() + (int) productList.get(productId));
			cart.setTotalPrice(cart.getTotalPrice() - product.getProdPrice() * (int) productList.get(productId));
			productList.remove(productId);
		}
		cart.setProducts(productList);
		productRepository.save(product);
		cartRepository.save(cart);
		return "redirect:/cartItem";
	}

}
