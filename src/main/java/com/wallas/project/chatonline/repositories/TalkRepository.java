package com.wallas.project.chatonline.repositories;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wallas.project.chatonline.models.Talk;

public interface TalkRepository extends MongoRepository<Talk, UUID> {}
