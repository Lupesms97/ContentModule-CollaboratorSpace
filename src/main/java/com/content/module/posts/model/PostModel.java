package com.content.module.posts.model;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "content")
@EqualsAndHashCode(of = "id")
public class PostModel {

    @Id
    private String id;
    private String title;
    private String shortContent;
    private String content;
    private LocalDate date;
    private String imageURL;

    public static LocalDate convertDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return LocalDate.parse(date, formatter);

    }

    public static PostModel getPostModel(){
        return new PostModel();
    }
}
