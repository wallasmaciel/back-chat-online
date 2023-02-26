package com.wallas.project.chatonline.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.wallas.project.chatonline.models.User;

public interface UserRepository extends MongoRepository<User, UUID> {
	@Query("{email:'?0'}")
	Optional<User> findByEmail(String email);
	@Query("{user_id:{$ne:?0}}")
	List<User> findByOtherUsers(UUID user_id);
}
