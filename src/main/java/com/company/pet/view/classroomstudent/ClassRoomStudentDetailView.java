package com.company.pet.view.classroomstudent;

import com.company.pet.entity.ClassRoomStudent;
import com.company.pet.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "classRoomStudents/:id", layout = MainView.class)
@ViewController(id = "ClassRoomStudent.detail")
@ViewDescriptor(path = "class-room-student-detail-view.xml")
@EditedEntityContainer("classRoomStudentDc")
public class ClassRoomStudentDetailView extends StandardDetailView<ClassRoomStudent> {
}