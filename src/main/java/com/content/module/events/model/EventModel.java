package com.content.module.events.model;

//      "id": "a9f9ce94-9d67-48ed-8695-742d1407e0e3",
//              "title": "Vermelho: Eventos ou reuniões obrigatórias",
//              "start": "2023/10/28",
//              "end": "2023/10/30",
//              "color": "red"

import com.content.module.events.dto.EventsUpdateDTo;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "events")
public class EventModel {
    @Id
    private String id;
    private String title;
    private LocalDate start;
    private LocalDate end;
    private String color;

    public static LocalDate convertDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);

    }

    public static EventModel convertToEventModel(EventsUpdateDTo eventsUpdateDTo){
        return new EventModel(eventsUpdateDTo.id(),eventsUpdateDTo.title(),convertDate(eventsUpdateDTo.start()),convertDate(eventsUpdateDTo.end()),eventsUpdateDTo.color());
    }
}
