package com.company.pet.service;

import com.company.pet.entity.Score;
import com.company.pet.entity.User;
import com.company.pet.entity.Enum;
import com.company.pet.repository.ScoreRepository;
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
    private ScoreRepository scoreRepository;

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
    public void createStudent(User user, Score score) {
        Score studentScore = new Score();
        studentScore.setEnglish(score.getEnglish());
        studentScore.setMath(score.getMath());
        studentScore.setHistory(score.getHistory());
        scoreRepository.save(studentScore);

        User student = new User();
        student.setUsername(user.getUsername());
        student.setFirstName(user.getFirstName());
        student.setLastName(user.getLastName());
        student.setEmail(user.getEmail());
        student.setAddress(user.getAddress());
        student.setRole(Enum.Role.STUDENT.toString());
        student.setScore(studentScore);
        student.setPassword(user.getPassword());
        student.setPhone(user.getPhone());
        userRepository.save(student);
    }
}
