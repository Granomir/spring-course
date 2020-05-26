package com.patrushev.home_work_09.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "nickName")
    private String nickName;
    @Column(name = "body")
    private String body;
    @Column(name = "bookId")
    private long bookId;
}
