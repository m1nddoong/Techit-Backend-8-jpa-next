package com.example.jpanext.school.repo;

import com.example.jpanext.school.dto.ILCountDto;
import com.example.jpanext.school.dto.ILCountProjection;
import com.example.jpanext.school.entity.Instructor;
import jakarta.persistence.MapsId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface InstructorRepository
        extends JpaRepository<Instructor, Long> {

    // 지도 학생을 데리고 있지 않은 강사를 삭제
    @Modifying
    @Query("DELETE FROM Instructor i " +
            // size()는 JPQL이 제공하는 기능
            "WHERE size(i.advisingStudents) = 0"
    )
    Integer sackInstructorsNotAdvising();


    // 강의를 하고있지 않은 강사를 삭제
    @Modifying
    @Query("DELETE FROM Instructor i " +
            "WHERE i.id NOT IN" +
            "(SELECT DISTINCT l.instructor.id FROM Lecture l)")
    Integer sackInstructorsNotTeaching();

    @Query("SELECT l.instructor, COUNT(*) FROM Lecture  l " +
            "GROUP BY l.instructor")
    List<Object[]> selectIlCountObject();

    @Query("SELECT new com.example.jpanext.school.dto.ILCountDto(l.instructor, COUNT(*)) " +
            "FROM Lecture l " +
            "GROUP BY l.instructor")
    List<ILCountDto> selectILCountDto();

    @Query("SELECT l.instructor AS instructor, COUNT(*) as lectureCount " +
            "FROM Lecture l " +
            "GROUP BY l.instructor")
    List<ILCountProjection> selectILCountProj();
}
