package com.company.pet.view.classroomstudent;

import com.company.pet.entity.ClassRoomStudent;
import com.company.pet.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "classRoomStudents", layout = MainView.class)
@ViewController(id = "ClassRoomStudent.list")
@ViewDescriptor(path = "class-room-student-list-view.xml")
@LookupComponent("classRoomStudentsDataGrid")
@DialogMode(width = "64em")
public class ClassRoomStudentListView extends StandardListView<ClassRoomStudent> {
}