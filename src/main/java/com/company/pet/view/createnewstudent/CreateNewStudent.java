package com.company.pet.view.createnewstudent;

import com.company.pet.entity.Score;
import com.company.pet.entity.User;
import com.company.pet.service.StudentService;
import com.company.pet.view.main.MainView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "user/new", layout = MainView.class)
@ViewController(id = "CreateNewStudent")
@ViewDescriptor(path = "create-new-student.xml")
public class CreateNewStudent extends StandardView {
    @Autowired
    private StudentService studentService;
    @ViewComponent
    private TextField usernameField;
    @ViewComponent
    private PasswordField passwordField;
    @ViewComponent
    private PasswordField confirmPasswordField;
    @ViewComponent
    private TextField firstNameField;
    @ViewComponent
    private TextField lastNameField;
    @ViewComponent
    private TextField emailField;
    @ViewComponent
    private ComboBox timeZoneField;
    @ViewComponent
    private TextField addressField;
    @ViewComponent
    private TextField phoneField;
    @ViewComponent
    private TextField scoreMath;
    @ViewComponent
    private TextField scoreEnglish;
    @ViewComponent
    private TextField scoreHistory;


    @Subscribe("createNewStudent")
    public void onCreateNewStudent(ActionPerformedEvent event){
        try {
            float mathScore = Float.parseFloat(scoreMath.getValue());
            float englishScore = Float.parseFloat(scoreEnglish.getValue());
            float historyScore = Float.parseFloat(scoreHistory.getValue());
            String username = usernameField.getValue();
            String password = passwordField.getValue();
            String confirmPassword = confirmPasswordField.getValue();
            if (!password.equals(confirmPassword)) {
                Notification.show("Password not match!", 5000, Notification.Position.TOP_CENTER);
                throw new RuntimeException("Passwords do not match");
            }
            String firstName = firstNameField.getValue();
            String lastName = lastNameField.getValue();
            String email = emailField.getValue();
            String address = addressField.getValue();
            String phone = phoneField.getValue();


            Score score = new Score(mathScore, englishScore, historyScore);

            User user = new User(username, password, firstName, lastName, email, address, phone, score);
            studentService.createStudent(user, score);

            UI.getCurrent().navigate("users");
            Notification.show("Student created successfully!", 5000, Notification.Position.TOP_CENTER);
        }catch (Exception e){
            Notification.show("Error: " + e.getMessage(), 5000, Notification.Position.TOP_CENTER);
        }

    }
}