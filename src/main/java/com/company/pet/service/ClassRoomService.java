package com.company.pet.service;

import com.company.pet.entity.ClassRoom;

import java.util.List;
import java.util.UUID;

public interface ClassRoomService {

    List<ClassRoom> findAll();

    List<ClassRoom> findAllByName(String name);

    ClassRoom findByName(String name);

    ClassRoom findById(UUID id);
}
