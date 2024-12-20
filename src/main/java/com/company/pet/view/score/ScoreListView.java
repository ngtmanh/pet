package com.company.pet.view.score;

import com.company.pet.entity.Course;
import com.company.pet.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "scores", layout = MainView.class)
@ViewController(id = "Score.list")
@ViewDescriptor(path = "score-list-view.xml")
@LookupComponent("scoresDataGrid")
@DialogMode(width = "64em")
public class ScoreListView extends StandardListView<Course> {
}