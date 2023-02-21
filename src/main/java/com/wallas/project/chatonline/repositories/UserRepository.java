package com.wallas.project.chatonline.repositories;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.wallas.project.chatonline.models.User;

public interface UserRepository extends MongoRepository<User, UUID> {
	@Query("{email:'?0'}")
	User findByEmail(String email);
}
