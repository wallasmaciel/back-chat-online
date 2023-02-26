package com.wallas.project.chatonline.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.wallas.project.chatonline.models.Message;
import com.wallas.project.chatonline.models.Talk;

public interface TalkRepository extends MongoRepository<Talk, UUID> {
	@Query("{participants:?0,participants:?1}")
	Optional<Talk> findByUsersTalk(UUID first_user_id, UUID second_user_id);
	@Query("{'messages.message_id':?0}")
	Optional<Message> findByMessageTalk(UUID message_id);
}
