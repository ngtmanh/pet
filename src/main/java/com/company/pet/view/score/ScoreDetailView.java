package com.company.pet.view.score;

import com.company.pet.entity.Course;
import com.company.pet.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "scores/:id", layout = MainView.class)
@ViewController(id = "Score.detail")
@ViewDescriptor(path = "score-detail-view.xml")
@EditedEntityContainer("scoreDc")
public class ScoreDetailView extends StandardDetailView<Course> {
}