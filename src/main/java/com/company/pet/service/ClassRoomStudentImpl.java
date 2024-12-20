package com.company.pet.service;

import com.company.pet.entity.ClassRoom;
import com.company.pet.entity.ClassRoomStudent;
import com.company.pet.entity.User;
import com.company.pet.repository.ClassRoomStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("pet_ClassRoomStudentService")
public class ClassRoomStudentImpl implements ClassRoomStudentService {
    @Autowired
    private ClassRoomStudentRepository classRoomStudentRepository;
    @Autowired
    private ClassRoomService classRoomService;
    @Autowired
    private StudentService studentService;

    @Override
    public List<User> findAllStudentByClassRoomId(UUID classRoomId) {
        List<User> listStudentInClass = new ArrayList<>();
        List<ClassRoomStudent> classRoomStudents = classRoomStudentRepository.findClassRoomStudentByClassRoomId(classRoomId);
        for (ClassRoomStudent classRoomStudent : classRoomStudents) {
            listStudentInClass.add(classRoomStudent.getStudent());
        }
        return listStudentInClass;
    }

    @Override
    public void addStudentToClassRoom(UUID classRoomId, UUID studentId) {
        ClassRoom classRoom = classRoomService.findById(classRoomId);
        User student = studentService.findStudentById(studentId);
        ClassRoomStudent classRoomStudent = new ClassRoomStudent();
        classRoomStudent.setClassRoom(classRoom);
        classRoomStudent.setStudent(student);
        classRoomStudentRepository.save(classRoomStudent);
    }
}
