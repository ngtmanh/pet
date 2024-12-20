package com.company.pet.view.user;

import com.company.pet.entity.User;
import com.company.pet.entity.Enum;
import com.company.pet.service.StudentService;
import com.company.pet.view.main.MainView;
import com.vaadin.flow.component.BlurNotifier;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.propertyfilter.PropertyFilter;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.kit.component.dropdownbutton.DropdownButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.util.List;

@Route(value = "users", layout = MainView.class)
@ViewController(id = "User.list")
@ViewDescriptor(path = "user-list-view.xml")
@LookupComponent("usersDataGrid")
@DialogMode(width = "64em")
public class UserListView extends StandardListView<User> {

    @ViewComponent
    private PropertyFilter filterByRole;
    @ViewComponent
    private PropertyFilter filterByUserName;
    @ViewComponent
    private PropertyFilter filterByFirstName;
    @ViewComponent
    private PropertyFilter filterByLastName;
    @ViewComponent
    private PropertyFilter filterByEmail;
    @ViewComponent
    private JmixButton createButton;

    /**
     * Event triggered when the "Filter by Role"
     */
    @Subscribe("filterByRole")
    public void onFilterByRole(ActionPerformedEvent event) {
        filterByRole.setVisible(true);
        filterByUserName.setVisible(false);
        filterByFirstName.setVisible(false);
        filterByLastName.setVisible(false);
        filterByEmail.setVisible(false);
    }

    /**
     * Event triggered when the "Filter by UserName"
     */
    @Subscribe("filterByUserName")
    public void onFilterByUserName(ActionPerformedEvent event) {
        filterByRole.setVisible(false);
        filterByUserName.setVisible(true);
        filterByFirstName.setVisible(false);
        filterByLastName.setVisible(false);
        filterByEmail.setVisible(false);
    }

    /**
     * Event triggered when the "Filter by FirstName"
     */    @Subscribe("filterByFirstName")
    public void onFilterByFirstName(ActionPerformedEvent event) {
        filterByRole.setVisible(false);
        filterByUserName.setVisible(false);
        filterByFirstName.setVisible(true);
        filterByLastName.setVisible(false);
        filterByEmail.setVisible(false);
    }

    /**
     * Event triggered when the "Filter by LastName"
     */
    @Subscribe("filterByLastName")
    public void onFilterByLastName(ActionPerformedEvent event) {
        filterByRole.setVisible(false);
        filterByUserName.setVisible(false);
        filterByFirstName.setVisible(false);
        filterByLastName.setVisible(true);
        filterByEmail.setVisible(false);
    }

    /**
     * Event triggered when the "Filter by Email"
     */
    @Subscribe("filterByEmail")
    public void onFilterByEmail(ActionPerformedEvent event) {
        filterByRole.setVisible(false);
        filterByUserName.setVisible(false);
        filterByFirstName.setVisible(false);
        filterByLastName.setVisible(false);
        filterByEmail.setVisible(true);
    }
    @Subscribe("createNewStudent")
    public void onNegative(ActionPerformedEvent event){
        UI.getCurrent().navigate("user/new");
    }


}