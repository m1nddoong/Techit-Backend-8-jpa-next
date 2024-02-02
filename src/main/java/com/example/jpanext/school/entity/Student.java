package com.example.jpanext.school.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
// 테이블의 이름을 설정하고 싶을 때 (그 외의 기능도 많음)
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Setter
//    private String firstName;
//    @Setter
//    // 컬럼의 이름을 설정하고 싶을 때 (그 외의 기능도 많음)
//    @Column(name = "last_name")
//    private String lastName;

    private String name;
    private Integer age;
    private String phone;
    private String email;

    @Setter
    @ManyToOne
    @JoinColumn(name = "advisor_id")
    private Instructor advisor;


    @ManyToMany
    // Join Table의 모습을 정의하고 싶을 때
    @JoinTable(
            name = "attending_lectures",
            // Join Table의 나를 가르키는 FK의 설정
            joinColumns = @JoinColumn(name = "student_id"),
            // Join Table의 관계를 맺는 상대방을 가르키는 FK의 설정
            inverseJoinColumns = @JoinColumn(name = "lecture_id")
    )
    private final List<Lecture> attending = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    private final List<AttendingLectures> attendingLectures = new ArrayList<>();

//    @ManyToMany
//    private final List<Lecture> completed = new ArrayList<>();
}