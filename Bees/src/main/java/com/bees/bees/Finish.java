package com.bees.bees;

import animatefx.animation.FadeIn;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;

public class Finish {
    //Object
    Main menu = new Main();
    Game game = new Game();
    public void finisher(String message, Stage mStage, Boolean l,Button btR){
        //Definition
        Button btClose = new Button("Close");
        Button btReload = new Button("New Game");
        Label label = new Label(message);
        Stage stage = new Stage();
        Pane pane = new Pane();
        stage.setScene(new Scene(pane,400,300));
        stage.setResizable(false);

        //Size
        btClose.setMinSize(80,50);
        btReload.setMinSize(80,50);
        btClose.setMaxSize(80,50);
        btReload.setMaxSize(80,50);
        label.setMinSize(300,50);
        label.setMaxSize(300,50);

        //Location
        btClose.relocate(70,200);
        btReload.relocate(200,200);
        label.relocate(25,30);

        //Customization
        label.setStyle("-fx-font-weight: Bold");
        label.setStyle("-fx-font-size: 20");
        label.setTextFill(Color.CORAL);
        btClose.setStyle("-fx-background-color: LightBlue");
        btReload.setStyle("-fx-background-color: LightGreen");

        btClose.setOnAction(actionEvent -> {
            stage.close();
        });
        btReload.setOnAction(actionEvent -> {
            game.btReloadFire(btR);
            stage.close();
        });

        //Language
        if (l)
        {
            btReload.setText("Yeni Oyun");
            btClose.setText("Kapat");
        }

        pane.getChildren().addAll(btClose,btReload,label);
        new FadeIn(pane).play();
        stage.show();
    }
}
