package com.company.pet.service;

import com.company.pet.entity.User;

import java.util.List;
import java.util.UUID;

public interface ClassRoomStudentService {
    List<User> findAllStudentByClassRoomId(UUID classRoomId);

    void addStudentToClassRoom(UUID classRoomId, UUID studentId);
}
