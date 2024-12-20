package com.company.pet.view.classroom;

import com.company.pet.entity.ClassRoom;
import com.company.pet.service.ClassRoomService;
import com.company.pet.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;

@Route(value = "classRooms", layout = MainView.class)
@ViewController(id = "ClassRoom.list")
@ViewDescriptor(path = "class-room-list-view.xml")
@LookupComponent("classRoomsDataGrid")
@DialogMode(width = "64em")
public class ClassRoomListView extends StandardListView<ClassRoom> {
    @Autowired
    private ClassRoomService classRoomService;

    @ViewComponent
    private TextField searchField;

    @ViewComponent
    private DataGrid<ClassRoom> classRoomsDataGrid;

    @Subscribe("searchAction")
    public void onSearchAction(ActionPerformedEvent event) {
        String searchTerm = searchField.getValue();
        List<ClassRoom> result = classRoomService.findAll();
        if (searchTerm != null || !searchTerm.isEmpty()) {
            result = classRoomService.findAllByName(searchTerm);
        }
        classRoomsDataGrid.setItems(result);
    }
    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        classRoomsDataGrid.getColumnByKey("name").setHeader("Class Name");
        classRoomsDataGrid.getColumnByKey("teacher.lastName").setHeader("Teacher Last Name");
        classRoomsDataGrid.getColumnByKey("school.name").setHeader("School Name");
    }
}
