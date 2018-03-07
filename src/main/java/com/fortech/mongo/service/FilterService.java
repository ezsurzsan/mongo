package com.fortech.mongo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.fortech.mongo.entity.Product;

public interface FilterService {
	/*
	 * filter products
	 */
	Page<Product> filterProductsByFilters(List<String> brands, List<String> osList, List<String> yearList, int pageNumber, int pageSize);

	Page<Product> filterProductsByPriceRange(Double lowerThan, Double higherThan);

	List<Product> filterProductByOs(List<String> osList, List<Product> currentProducts);

	Page<Product> fullFilter(List<String> brands, List<String> osList, String text, int pageNumber, int pageSize);
	
	Page<Product> searchByText(String text, int pageNumber, int pageSize);

}
