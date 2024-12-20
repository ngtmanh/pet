package com.company.pet.view.user;

import com.company.pet.entity.Course;
import com.company.pet.entity.User;
import com.company.pet.service.CourseService;
import com.company.pet.view.main.MainView;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.Route;
import io.jmix.core.EntityStates;
import io.jmix.core.MessageTools;
import io.jmix.flowui.Dialogs;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.app.inputdialog.DialogActions;
import io.jmix.flowui.app.inputdialog.DialogOutcome;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.model.DataLoader;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Objects;
import java.util.TimeZone;
import java.util.UUID;

import static io.jmix.flowui.app.inputdialog.InputParameter.intParameter;
import static io.jmix.flowui.app.inputdialog.InputParameter.stringParameter;

@Route(value = "users/:id", layout = MainView.class)
@ViewController(id = "User.detail")
@ViewDescriptor(path = "user-detail-view.xml")
@EditedEntityContainer("userDc")
public class UserDetailView extends StandardDetailView<User> {

    @ViewComponent
    private TypedTextField<String> usernameField;
    @ViewComponent
    private PasswordField passwordField;
    @ViewComponent
    private PasswordField confirmPasswordField;
    @ViewComponent
    private ComboBox<String> timeZoneField;
    @ViewComponent
    private MessageBundle messageBundle;
    @Autowired
    private MessageTools messageTools;
    @Autowired
    private Notifications notifications;

    @Autowired
    private EntityStates entityStates;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @ViewComponent
    private DataLoader courseDl;
    @ViewComponent
    private DataGrid<Course> course;

    @Autowired
    private Dialogs dialogs;
    @Autowired
    private CourseService courseService;

//    @Subscribe
//    public void onInit(final InitEvent event) {
//        timeZoneField.setItems(List.of(TimeZone.getAvailableIDs()));
//    }

    @Subscribe
    public void onInitEntity(final InitEntityEvent<User> event) {
        usernameField.setReadOnly(false);
        passwordField.setVisible(true);
        confirmPasswordField.setVisible(true);
    }

    @Subscribe
    public void onReady(final ReadyEvent event) {
        if (entityStates.isNew(getEditedEntity())) {
            usernameField.focus();
        }
    }

    @Subscribe
    public void onValidation(final ValidationEvent event) {
        if (entityStates.isNew(getEditedEntity())
                && !Objects.equals(passwordField.getValue(), confirmPasswordField.getValue())) {
            event.getErrors().add(messageBundle.getMessage("passwordsDoNotMatch"));
        }
    }

    @Subscribe
    public void onBeforeSave(final BeforeSaveEvent event) {
        if (entityStates.isNew(getEditedEntity())) {
            getEditedEntity().setPassword(passwordEncoder.encode(passwordField.getValue()));

            String entityCaption = messageTools.getEntityCaption(getEditedEntityContainer().getEntityMetaClass());
            notifications.create(messageBundle.formatMessage("noAssignedRolesNotification", entityCaption))
                    .withType(Notifications.Type.WARNING)
                    .withPosition(Notification.Position.TOP_END)
                    .show();
        }
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        User editedEntity = getEditedEntity();
        if (editedEntity != null && editedEntity.getId() != null) {
            courseDl.setParameter("id", editedEntity.getId());
            courseDl.load();
        }
    }
    @Subscribe
    public void onInit(InitEvent event) {
        course.addItemClickListener(clickEvent -> {
            Course selectedCourse = clickEvent.getItem();
            openCourseDetailsDialog(selectedCourse);
        });
    }
    private void openCourseDetailsDialog(Course selectedCourse) {
        dialogs.createInputDialog(this)
                .withHeader("Edit Course Details")
                .withParameters(
                        stringParameter("name").withLabel("Course Name").withRequired(true).withDefaultValue(selectedCourse.getName()),
                        intParameter("point").withLabel("Point").withDefaultValue(selectedCourse.getPoint())
                )
                .withActions(DialogActions.OK_CANCEL)
                .withCloseListener(closeEvent -> {
                    if (closeEvent.closedWith(DialogOutcome.OK)) {
                        String name = closeEvent.getValue("name");
                        int point = closeEvent.getValue("point");
                        Float pointFloat = Float.valueOf(point);

                        courseService.updateCourse(selectedCourse.getId(), name, pointFloat);
                        selectedCourse.setName(name);
                        selectedCourse.setPoint(pointFloat);
                        courseDl.load();
                    }
                })
                .open();
    }
    @Subscribe("createCourse")
    private void addMoreCourse(ActionPerformedEvent event) {
        dialogs.createInputDialog(this)
                .withHeader("New Course Details")
                .withParameters(
                        stringParameter("name").withLabel("Course Name").withRequired(true),
                        intParameter("point").withLabel("Point")
                )
                .withActions(DialogActions.OK_CANCEL)
                .withCloseListener(closeEvent -> {
                    if (closeEvent.closedWith(DialogOutcome.OK)) {
                        String name = closeEvent.getValue("name");
                        int point = closeEvent.getValue("point");
                        Float pointFloat = Float.valueOf(point);

                        Course course = new Course();
                        course.setName(name);
                        course.setPoint(pointFloat);
                        course.setUser(getEditedEntity());

                        courseService.createCourse(course);
                        courseDl.load();
                    }
                })
                .open();
    }


}