package com.content.module.posts.respositories;

import com.content.module.posts.model.PostModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends MongoRepository<PostModel, UUID> {
}
