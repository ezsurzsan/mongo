package com.fortech.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.fortech.mongo.entity.Cart;
@Repository
public interface CartRepository extends MongoRepository<Cart, String>, QueryDslPredicateExecutor<Cart> {
	@Override
	Cart findOne(String id);

	@Override
	void delete(Cart deleted);
	
	
//	List<Cart>  findByProducts(LinkedHashMap)
}
