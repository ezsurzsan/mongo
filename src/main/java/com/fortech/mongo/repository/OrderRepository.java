package com.fortech.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fortech.mongo.entity.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
	@Override
	Order findOne(String id);

	@Override
	void delete(Order deleted);

}
