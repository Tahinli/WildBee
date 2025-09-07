package com.bees.bees;

import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;

public class Loading {
    ControllerDialog controllerDialog = new ControllerDialog();
    public void LoadingScreen(){
        //Definition
        Stage stage = new Stage();
        Pane pane = new Pane();
        ProgressBar progressBar = new ProgressBar();
        AtomicInteger oldValue = new AtomicInteger(-1);

        //Size
        progressBar.setMinSize(350,35);
        progressBar.setMaxSize(350,35);

        //Location
        progressBar.relocate(20,120);

        pane.getChildren().add(progressBar);
        stage.setScene(new Scene(pane,400,300));
        stage.setResizable(false);
        stage.show();

    }
}
