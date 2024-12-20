package com.company.pet.service;

import com.company.pet.entity.Course;

import java.util.UUID;

public interface CourseService {
    void updateCourse(UUID id, String name, float point);
    void createCourse(Course course);
}
