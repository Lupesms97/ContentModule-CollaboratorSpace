package com.content.module.events.services.impl;

import com.content.module.events.ExepectionsHandle.DBError;
import com.content.module.events.model.EventModel;
import com.content.module.events.repositories.EventsRepository;
import com.content.module.events.services.EventsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class EventsServiceImpl implements EventsService {

    private final EventsRepository eventsRepository;
    @Override
    public EventModel createEvent(EventModel eventModel) {

        try {
            return eventsRepository.save(eventModel);
        } catch (DBError e) {
            throw new DBError("Error to create event");
        }
    }

    @Override
    public EventModel getEventById(String id) {
        try {
            Optional<EventModel> event = eventsRepository.findById(id);
            return event.orElse(null);
        } catch (DBError e) {
            throw new DBError("Error to get event");
        }
    }

    @Override
    public EventModel updateEvent(EventModel eventModel) {
        try{
            EventModel updatableEvent;
            updatableEvent = eventsRepository.findById(eventModel.getId()).orElse(null);
            if(updatableEvent != null){
                updatableEvent.setTitle(eventModel.getTitle());
                updatableEvent.setStart(eventModel.getStart());
                updatableEvent.setEnd(eventModel.getEnd());
                updatableEvent.setColor(eventModel.getColor());
                eventsRepository.save(updatableEvent);
            }else {
                throw new DBError("Event not found");
            }
            return updatableEvent;
        } catch (DBError e) {
            throw new DBError("Error to update event");
        }
    }

    @Override
    public boolean deleteEventById(String id) {
        try{
            EventModel event = eventsRepository.findById(id).orElse(null);
            if(event != null){
                eventsRepository.deleteById(id);
                return true;
            }else {
                throw new DBError("Event not found");
            }
        } catch (DBError e) {
            throw new DBError("Error to delete event");
        }

    }

    @Override
    public List<EventModel> getAllEvents() {
        try{
            return eventsRepository.findAll();
        } catch (DBError e) {
            throw new DBError("Error to get all events");
        }
    }

    @Override
    public List<EventModel> saveMultipleEvents(List<EventModel> eventModelList) {
        try{
            return eventsRepository.saveAll(eventModelList);
        } catch (DBError e) {
            throw new DBError("Error to save multiple events");
        }
    }
}
