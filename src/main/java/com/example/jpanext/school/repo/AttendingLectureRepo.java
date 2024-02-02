package com.example.jpanext.school.repo;

import com.example.jpanext.school.entity.AttendingLectures;
import com.example.jpanext.school.entity.Lecture;
import com.example.jpanext.school.entity.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendingLectureRepo extends JpaRepository<AttendingLectures, Long> {
    List<AttendingLectures> findByStudent(Student student);
    List<AttendingLectures> findByLecture(Lecture lecture);
}
