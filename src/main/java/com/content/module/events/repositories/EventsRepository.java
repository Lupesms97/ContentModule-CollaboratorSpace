package com.content.module.events.repositories;

import com.content.module.events.model.EventModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsRepository extends MongoRepository<EventModel, String> {
}
