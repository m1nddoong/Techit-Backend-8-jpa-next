package com.example.jpanext.school.repo;

import com.example.jpanext.school.entity.Instructor;
import com.example.jpanext.school.entity.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository
        extends JpaRepository<Student, Long> {

    List<Student> findAllByAdvisorId(Long id);

    List<Student> findAllByAdvisor(Instructor instructor);

    // 지도교수가 null 인 학생을 반환
    @Query("SELECT s FROM Student s " +
           "WHERE s.advisor IS NULL ")
    List<Student> noAdvisorStudents();

    @Modifying
    @Query("UPDATE Student s " +
            "SET s.advisor = :advisor " +
            "WHERE s.advisor IS NULL")
    Integer setAdvisorForStudent(
            @Param("advisor") Instructor instructor
    );


    // 객체를 받아서 where 조건에 받는다?
    @Query("SELECT s FROM Student s WHERE s.advisor = :advisor")
    List<Student> findByAdvisor(
            @Param("advisor")
            Instructor instructor);
}
