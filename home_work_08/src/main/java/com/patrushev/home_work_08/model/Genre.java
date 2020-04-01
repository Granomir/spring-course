package com.patrushev.home_work_08.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
    @Id
    private long id;
    private String name;

    public Genre(String name) {
        this.name = name;
    }
}
