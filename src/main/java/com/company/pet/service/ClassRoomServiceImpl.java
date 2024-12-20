package com.company.pet.service;

import com.company.pet.entity.ClassRoom;
import com.company.pet.repository.ClassRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("pet_ClassRoomService")
public class ClassRoomServiceImpl implements ClassRoomService {
    @Autowired
    private ClassRoomRepository classRoomRepository;

    @Override
    public List<ClassRoom> findAll() {
        return classRoomRepository.findAll();
    }

    @Override
    public List<ClassRoom> findAllByName(String name) {
        return classRoomRepository.findAllByName(name);
    }

    @Override
    public ClassRoom findByName(String name) {
        return classRoomRepository.findByName(name);
    }

    @Override
    public ClassRoom findById(UUID id) {
        return classRoomRepository.findById(id).orElse(null);
    }
}
