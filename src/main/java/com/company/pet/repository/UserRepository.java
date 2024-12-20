package com.company.pet.repository;

import com.company.pet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUsersById(UUID id);

    List<User> findUsersByRole(String role);

    @Query("SELECT u FROM User u JOIN ClassRoomStudent cs ON u.id = cs.student.id WHERE cs.classRoom.id = :classroomId")
    List<User> findStudentByClassroomId(@Param("classroomId") UUID classroomId);
}
