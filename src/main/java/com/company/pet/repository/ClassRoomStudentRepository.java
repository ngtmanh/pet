package com.company.pet.repository;

import com.company.pet.entity.ClassRoomStudent;
import com.company.pet.entity.User;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;
import java.util.List;

public interface ClassRoomStudentRepository extends JpaRepository<ClassRoomStudent, UUID> {
    List<ClassRoomStudent> findClassRoomStudentByClassRoomId(UUID classRoomId);

    @Query("select cs.student from ClassRoomStudent cs where cs.classRoom.id = :classroomId")
    List<User> findUserByClassRoomId(@Param("classroomId") UUID classRoomId);
}
