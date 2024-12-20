package com.company.pet.service;

import com.company.pet.entity.Course;
import com.company.pet.entity.User;
import java.util.List;
import java.util.UUID;

public interface StudentService {
    List<User> listStudentInClassRoom(UUID classRoomId);

    User findStudentById(UUID id);

    List<User> findUserByRole(String role);

    void createStudent(User user, Course score);
}
