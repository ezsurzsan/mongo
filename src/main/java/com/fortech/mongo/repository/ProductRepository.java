package com.fortech.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.fortech.mongo.entity.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String>, QueryDslPredicateExecutor<Product> {

	@Override
	Product findOne(String id);

	@Override
	void delete(Product deleted);

}
