package com.wildbee.wildbee;

import animatefx.animation.*;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


class Loading {
    ProgressBar progressBar = new ProgressBar();
    Stage stageG = new Stage();
    Button bt = new Button();

    Label lb = new Label("---> LOADING <---");

    void loader(Stage stage) {
        //Definition
        Pane pane = new Pane();

        //Size
        progressBar.setMinSize(350,35);
        progressBar.setMaxSize(350,35);
        bt.setMinSize(100,50);
        bt.setMaxSize(100,50);
        lb.setMinSize(300,50);
        lb.setMaxSize(300,50);


        //Location
        progressBar.relocate(20,120);
        bt.relocate(140,200);
        lb.relocate(100,55);


        //Action
        bt.setOnAction(actionEvent -> {
            try {
                closer(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        //Thread
        Thread th = new Thread(new pro());
        th.setDaemon(true);
        th.start();

        pane.getChildren().addAll(progressBar,bt,lb);
        stage.setScene(new Scene(pane,400,300));
        stage.setResizable(false);
        stage.setTitle("Loading...");


        stage.show();
        stage.toFront();

        //Customization
        bt.setText("Start");
        bt.setStyle("-fx-font-weight: Bold");
        lb.setStyle("-fx-font-weight: Bold");
        lb.setStyle("-fx-font-size: 20");
        lb.setTextFill(Color.CORAL);
        new FadeIn(lb).setCycleCount(-1).play();
        new Pulse(bt).setCycleCount(-1).play();
        bt.setOpacity(0.0);


    }
    void closer(Stage stage) {
        Main menu = new Main();
        menu.menu(stageG);
        stage.close();
    }

    class pro extends Task<Integer>{
        @Override
        protected Integer call() {
            while (ControllerDialog.processedPangrams != 11833)
                progressBar.setProgress(ControllerDialog.processedPangrams/11833.0);
            bt.setOpacity(100.0);
            lb.setTextFill(Color.CRIMSON);
            new RollOut(lb).play();
            return ControllerDialog.processedPangrams;
        }
    }
}

