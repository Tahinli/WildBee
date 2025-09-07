package com.bees.bees;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;

public class Loading {
    public void loader(Stage stage){
        //Definition
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
        stage.setTitle("Loading...");
        stage.show();
    }
    public void closer(Stage stage){
        stage.close();
    }


}
