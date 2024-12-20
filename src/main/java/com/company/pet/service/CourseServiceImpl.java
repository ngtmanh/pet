package com.company.pet.service;

import com.company.pet.entity.Course;
import com.company.pet.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("pet_CourseService")
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void updateCourse(UUID id, String name, float point) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course != null) {
            course.setName(name);
            course.setPoint(point);
            courseRepository.save(course);
        }else{
            throw new RuntimeException("Course not found");
        }
    }

    @Override
    public void createCourse(Course course) {
        Course newCousrse = new Course();
        newCousrse.setName(course.getName());
        newCousrse.setPoint(course.getPoint());
        newCousrse.setUser(course.getUser());
        courseRepository.save(newCousrse);
    }
}
