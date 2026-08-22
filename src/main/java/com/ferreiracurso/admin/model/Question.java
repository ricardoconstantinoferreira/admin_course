package com.ferreiracurso.admin.model;

import com.ferreiracurso.admin.model.enums.TypeQuestion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "type_question")
    private TypeQuestion typeQuestion;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @Column(name = "points")
    private int points;
}
