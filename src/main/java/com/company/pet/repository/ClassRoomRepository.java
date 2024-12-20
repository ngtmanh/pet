package com.company.pet.repository;


import com.company.pet.entity.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository(value = "pet_ClassRoomRepository")
public interface ClassRoomRepository extends JpaRepository<ClassRoom, UUID> {
    @Override
    List<ClassRoom> findAll();

    List<ClassRoom> findAllByName(String name);

    ClassRoom findByName(String name);
}