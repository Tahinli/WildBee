package com.bees.bees;


import javafx.application.Application;
import javafx.stage.Stage;

public class FXStarter extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        Loading loading = new Loading();
        loading.LoadingScreen();
        Stage stage1 = new Stage();
        Main menuItself = new Main();
        menuItself.menu(stage1);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
