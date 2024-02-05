package com.example.jpanext.school.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String name;
    @Setter
    private String day;
    @Setter
    private Integer startTime;
    @Setter
    private Integer endTime;

    @Setter
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    // 상세설정은 mappedBy가 가르키는 속성에 따른다.
    @ManyToMany(mappedBy = "attending")
    private final List<Student> students = new ArrayList<>();

//    @ManyToMany(mappedBy = "completed")
//    private final List<Student> completedStudents = new ArrayList<>();
}
