package com.wallas.project.chatonline.repositories;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.wallas.project.chatonline.models.Talk;

public interface TalkRepository extends MongoRepository<Talk, UUID> {
	@Query("{participants:?0,participants:?1}")
	Talk findByUsersTalk(UUID first_user_id, UUID second_user_id);
}
