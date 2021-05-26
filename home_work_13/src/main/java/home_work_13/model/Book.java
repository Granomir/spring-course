package home_work_13.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title")
    private String title;
    @ManyToOne
    @JoinColumn(name = "author")
    private Author author;
    @ManyToOne
    @JoinColumn(name = "genre")
    private Genre genre;
}
