package com.fortech.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fortech.mongo.entity.Cart;
@Repository
public interface MCartRepository extends MongoRepository<Cart, String> {
	@Override
	Cart findOne(String id);

	@Override
	void delete(Cart deleted);
	
	
//	List<Cart>  findByProducts(LinkedHashMap)
}
