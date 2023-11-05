package com.content.module.events.services;

import com.content.module.events.model.EventModel;

import java.util.List;

public interface EventsService {

    public EventModel createEvent(EventModel eventModel);

    public EventModel getEventById(String id);

    public EventModel updateEvent(EventModel eventModel);

    public boolean deleteEventById(String id);

    public List<EventModel> getAllEvents();

    public  List<EventModel> saveMultipleEvents(List<EventModel> eventModelList);
}
