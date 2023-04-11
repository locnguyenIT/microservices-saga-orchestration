package com.ntloc.ordeview;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderViewRepository extends MongoRepository<Order, String> {


}
