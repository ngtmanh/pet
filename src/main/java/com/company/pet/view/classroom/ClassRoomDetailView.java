package com.company.pet.view.classroom;

import com.company.pet.entity.ClassRoom;
import com.company.pet.entity.User;
import com.company.pet.service.ClassRoomStudentService;
import com.company.pet.service.StudentService;
import com.company.pet.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.valuepicker.EntityPicker;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@Route(value = "classRooms/:id", layout = MainView.class)
@ViewController(id = "ClassRoom.detail")
@ViewDescriptor(path = "class-room-detail-view.xml")
@EditedEntityContainer("classRoomDc")
public class ClassRoomDetailView extends StandardDetailView<ClassRoom> {
    @Autowired
    private ClassRoomStudentService classRoomStudentService;

    @Autowired
    private StudentService studentService;

    @ViewComponent
    private DataGrid<User> studentsDataGrid;
    @ViewComponent
    private EntityPicker<User> usersPicker;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        UUID classRoomId = getEditedEntity().getId();
        List<User> listStudent = classRoomStudentService.findAllStudentByClassRoomId(classRoomId);
        studentsDataGrid.getColumnByKey("lastName").setHeader("Last Name");
        studentsDataGrid.getColumnByKey("firstName").setHeader("First Name");
        studentsDataGrid.getColumnByKey("email").setHeader("Email");
        studentsDataGrid.getColumnByKey("phone").setHeader("Phone");
        studentsDataGrid.getColumnByKey("address").setHeader("Address");
        studentsDataGrid.setItems(listStudent);
    }
}