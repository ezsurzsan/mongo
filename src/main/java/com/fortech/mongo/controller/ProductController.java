package com.fortech.mongo.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fortech.mongo.entity.Product;
import com.fortech.mongo.repository.ProductRepository;
import com.fortech.mongo.service.CartService;
import com.fortech.mongo.service.FilterService;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Controller
public class ProductController {

	@Autowired
	ProductRepository productRepository;
	@Autowired
	CartService cartService;
	@Autowired
	private FilterService filterService;
	// private List<Product> theProductList;

	@RequestMapping("/product")
	public String product(Model model) {
//		String asdf =model.toString();
		model.addAttribute("products", productRepository.findAll());
		return "product";
	}

	// @RequestMapping("/product/filter")
	// public String productFilter(Model model, @RequestParam String brand2,
	// @RequestParam String osList2,
	// @RequestParam int pageSize, @RequestParam int pageNumber) {
	// Page<Product> productList;
	// String[] brandList = new String[] { brand2 };
	// String[] osList = new String[] { osList2 };
	//
	// productList = filterService.filterProductsByBrand(Arrays.asList(brandList),
	// Arrays.asList(osList), pageNumber,
	// pageSize);
	// // productList = filterService.filterProductByOs(Arrays.asList(osList),
	// // productList);
	//
	// model.addAttribute("products", productList);
	// return "product";
	// }

	@RequestMapping("/product/search")
	public String productSearch(Model model, @RequestParam String text) {
		Page<Product> productList;
		productList = filterService.searchByText(text, 1, 2);
		model.addAttribute("products", productList);
		return "product";
	}

	@RequestMapping("/create")
	public String create(Model model) {
		return "create";
	}

	@RequestMapping("/save")
	public String save(@RequestParam String prodName, @RequestParam String prodDesc, @RequestParam Double prodPrice,
			@RequestParam String prodImage, @RequestParam int prodQty, @RequestParam String brand) {
		Product product = new Product();
		product.setProdName(prodName);
		product.setProdDesc(prodDesc);
		product.setProdPrice(prodPrice);
		product.setProdImage(prodImage);
		product.setProdQty(prodQty);
		product.setBrand(brand);
		productRepository.save(product);

		return "redirect:/show/" + product.getId();
	}

	@RequestMapping("/show/{id}")
	public String show(@PathVariable String id, Model model) {
		model.addAttribute("product", productRepository.findOne(id));
		return "show";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam String id) {
		Product product = productRepository.findOne(id);
		productRepository.delete(product);

		return "redirect:/product";
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable String id, Model model) {
		model.addAttribute("product", productRepository.findOne(id));
		return "edit";
	}

	@RequestMapping("/update")
	public String update(@RequestParam String id, @RequestParam String prodName, @RequestParam String prodDesc,
			@RequestParam Double prodPrice, @RequestParam String prodImage, @RequestParam int prodQty,
			@RequestParam String brand) {
		Product product = productRepository.findOne(id);
		product.setProdName(prodName);
		product.setProdDesc(prodDesc);
		product.setProdPrice(prodPrice);
		product.setProdImage(prodImage);
		product.setProdQty(prodQty);
		product.setBrand(brand);
		productRepository.save(product);

		return "redirect:/show/" + product.getId();
	}

	@RequestMapping("/addToCart")
	public String addToCart(@RequestParam String id) {

		String returnValue = cartService.addItemToCart(id, 1);

		return returnValue;
	}

	@RequestMapping("/cart/deleteOne")
	public String deleteOne(@RequestParam String id) {
		cartService.deleteOneFromCart(id);
		return "redirect:/cartItem";
	}

	@RequestMapping("/cart/deleteAll")
	public String deleteAll(@RequestParam String id) {
		cartService.deleteAllFromCart(id);
		return "redirect:/cartItem";
	}

	@RequestMapping("/product/fullFilter")
	public String completeFilter(@ModelAttribute("brands") String[] brandList,
			@ModelAttribute("osList") String[] osList, @ModelAttribute("yearList") String[] yearList,
			@RequestParam int pageSize, @RequestParam int pageNumber, BindingResult result, ModelMap model) {
		model.addAttribute("brands", brandList); // send param as brands=Apple, Huawei
		model.addAttribute("osList", osList);
		model.addAttribute("yearList", yearList);
		Page<Product> productList = filterService.filterProductsByFilters(Arrays.asList(brandList),
				Arrays.asList(osList), Arrays.asList(yearList), pageNumber, pageSize);
		model.addAttribute("products", productList);
		return "product";

	}

}
