package com.fortech.mongo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fortech.mongo.entity.Product;
import com.fortech.mongo.entity.QProduct;
import com.fortech.mongo.repository.ProductRepository;
import com.fortech.mongo.service.FilterService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

@Service
public class FilterServiceImpl implements FilterService {

	@Autowired
	private ProductRepository productRepository;

	// @Override
	// public Page<Product> filterProductsByBrand(List<String> brands, List<String>
	// osList, int pageNumber, int pageSize) {
	// QProduct qProd = new QProduct("product");
	// BooleanBuilder builder = new BooleanBuilder();
	//
	// Sort prodSort = new Sort(Sort.Direction.DESC, "brand");
	// Pageable pageable = new PageRequest(pageNumber - 1, pageSize, prodSort);
	// if (brands != null && brands.size() != 0) {
	// for (String brand : brands) {
	// builder.or(qProd.brand.eq(brand));
	// }
	//
	// }
	// if (osList != null && osList.size() != 0) {
	// for (String os : osList) {
	// builder.or(qProd.os.eq(os));
	// }
	// }
	// Page<Product> productList = productRepository.findAll(builder, pageable);
	//
	// return productList;
	//
	// }

	@Override
	public Page<Product> filterProductsByPriceRange(Double lowerThan, Double higherThan) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> filterProductByOs(List<String> osList, List<Product> currentProducts) {
		QProduct qProd = new QProduct("product");
		BooleanBuilder builder = new BooleanBuilder();
		List<Product> productList = currentProducts;
		if (osList != null && osList.size() != 0) {
			for (String os : osList) {
				builder.or(qProd.os.eq(os));
			}
		}
		productList = (List<Product>) productRepository.findAll(builder);
		if (currentProducts != null && !currentProducts.isEmpty()) {
			productList.retainAll(currentProducts);
		}
		return productList;

	}

	@Override
	public Page<Product> fullFilter(List<String> brands, List<String> osList, String text, int pageNumber,
			int pageSize) {
		String[] words = text.split(" ");
		QProduct qProd = new QProduct("product");
		Sort prodSort = new Sort(Sort.Direction.DESC, "brand");
		Pageable pageable = new PageRequest(pageNumber - 1, pageSize, prodSort);
		BooleanBuilder builder = new BooleanBuilder();
		if (brands != null && brands.size() != 0) {
			for (String brand : brands) {
				builder.or(qProd.brand.eq(brand));
			}

		}
		if (osList != null && osList.size() != 0) {
			for (String os : osList) {
				builder.or(qProd.os.eq(os));
			}
		}
		for (String word : words) {
			builder.or(qProd.prodName.contains(word)).or(qProd.prodDesc.contains(word)).or(qProd.os.contains(word))
					.or(qProd.brand.contains(word));
		}
		Page<Product> productList = (Page<Product>) productRepository.findAll(builder, pageable);

		return productList;
	}

	@Override
	public Page<Product> searchByText(String text, int pageNumber, int pageSize) {
		String[] words = text.split(" ");
		QProduct qProd = new QProduct("product");
		Sort prodSort = new Sort(Sort.Direction.DESC, "brand");
		Pageable pageable = new PageRequest(pageNumber - 1, pageSize, prodSort);
		BooleanBuilder builder = new BooleanBuilder();
		for (String word : words) {
			builder.or(qProd.prodName.contains(word)).or(qProd.prodDesc.contains(word)).or(qProd.os.contains(word))
					.or(qProd.brand.contains(word));
		}
		Page<Product> productList = (Page<Product>) productRepository.findAll(builder, pageable);
		return productList;
	}

	@Override
	public Page<Product> filterProductsByFilters(List<String> brands, List<String> osList, List<String> yearList,
			int pageNumber, int pageSize) {
		QProduct qProd = new QProduct("product");
		BooleanBuilder builder1 = new BooleanBuilder();
		Sort prodSort = new Sort(Sort.Direction.DESC, "brand");
		Pageable pageable = new PageRequest(pageNumber - 1, pageSize, prodSort);
		if (!"NA".equals(brands.get(0)) && brands.size() != 0) {
			for (String brand : brands) {
				builder1.or(qProd.brand.eq(brand));
			}

		}
		BooleanBuilder builder2 = new BooleanBuilder();
		if (!"NA".equals(osList.get(0)) && osList.size() != 0) {
			for (String os : osList) {
				builder2.or(qProd.os.eq(os));
			}
		}
		builder1 = builder1.and(builder2);
		builder2 = new BooleanBuilder();
		if (!"NA".equals(yearList.get(0)) && yearList.size() != 0) {
			for (String string : yearList) {
				builder2.or(qProd.prodDesc.containsIgnoreCase(string));
			}
		}
		builder1 = builder1.and(builder2);
		Page<Product> productList = productRepository.findAll(builder1, pageable);

		return productList;
	}

}
