package com.content.module.events.controllers;

import com.content.module.events.dto.EventsDto;
import com.content.module.events.dto.EventsUpdateDTo;
import com.content.module.events.model.EventModel;
import com.content.module.events.services.EventsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Slf4j
public class EventsControllers {
    private final EventsService eventsService;

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public ResponseEntity<List<EventModel>> getAllEvents(){
        try {
            List<EventModel> eventModelList = eventsService.getAllEvents();
            if (eventModelList == null || eventModelList.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(eventModelList);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/event")
    public ResponseEntity<?> createEvent(@RequestBody EventsDto eventsDto)  {
        try {
            EventModel eventModel = EventModel.convertToEventModel(eventsDto);
            log.info(eventModel.getStart().toString());
            log.info(eventModel.getEnd().toString());
            EventModel EventModelReturn =  eventsService.createEvent(eventModel);
            log.info(EventModelReturn.getStart().toString());
            log.info(EventModelReturn.getEnd().toString());
            return ResponseEntity.ok(EventModelReturn);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("saveMultipleEvents")
    public ResponseEntity<?> saveMultipleEvents(@RequestBody  List<EventsDto> eventDtoList){
        List<EventModel> eventModelList = new ArrayList<>();
        for(EventsDto eventModelDto : eventDtoList){
            EventModel eventModel = EventModel.convertToEventModel(eventModelDto);
            eventModelList.add(eventModel);
        }
        List<EventModel> eventModelListReturn = eventsService.saveMultipleEvents(eventModelList);
        if (eventModelListReturn == null || eventModelListReturn.isEmpty()){
            return ResponseEntity.internalServerError().body("Error to save events");
        }
        return ResponseEntity.ok(eventModelListReturn);
    }

    @DeleteMapping("eventDeletion")
    public ResponseEntity<?> deleteEventById(@RequestParam String eventId){
        boolean response = eventsService.deleteEventById(eventId);
        if (response){
            return ResponseEntity.ok("Event deleted");
        }
        return ResponseEntity.internalServerError().body("Error to delete event");
    }

    @PutMapping("update")
    public ResponseEntity<?> updateEvent(@RequestBody EventsUpdateDTo eventsUpdateDTo){
        try{
                EventModel eventModel = EventModel.convertToEventModel(eventsUpdateDTo);

                EventModel eventReturn = eventsService.updateEvent(eventModel);
                return ResponseEntity.ok(eventReturn);

        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Error to update event");
        }

    }
}
