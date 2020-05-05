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
public class Comment {
    @Id
    private long id;
    private String nickName;
    private String body;
    private long bookId;
}
