package com.company.pet.service;

import com.company.pet.entity.Course;
import com.company.pet.entity.User;
import com.company.pet.repository.CourseRepository;
import com.company.pet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service("pet_StudentService")
public class StudentImpl implements StudentService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository scoreRepository;

    @Override
    public List<User> listStudentInClassRoom(UUID classRoomId) {
        return userRepository.findStudentByClassroomId(classRoomId);
    }

    @Override
    public User findStudentById(UUID id) {
        return userRepository.findUsersById(id);
    }

    @Override
    public List<User> findUserByRole(String role) {
        return userRepository.findUsersByRole(role);
    }

    @Transactional()
    @Override
    public void createStudent(User user, Course score) {
    }
}
