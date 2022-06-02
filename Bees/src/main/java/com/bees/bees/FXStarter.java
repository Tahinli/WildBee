package com.bees.bees;


import javafx.application.Application;
import javafx.application.Preloader;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;

public class FXStarter extends Application {
    AtomicInteger a = new AtomicInteger(0);
    Stage stage1 = new Stage();
    Stage stageL = new Stage();
    Main menuItself = new Main();
    Loading loading = new Loading();

    @Override
    public void start(Stage stage) throws Exception {
        loading.loader(stageL);
        new loader().start();
    }
public class loader extends Service<Double>{
    @Override
    protected void succeeded() {
        a.set(1);
        try {
            System.out.println("EHEHE");
            loading.closer(stageL);
            menuItself.menu(stage1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void running() {
        a.set(-1);
    }
    @Override
    protected Task <Double> createTask() {
        return new Task<Double>() {
            @Override
            protected Double call() throws Exception {
                System.out.println("ÇALIŞIYORUM");
                running();
                ControllerDialog.func();
                succeeded();
                return 100.0;
            }
        };
    }
}
    public static void main(String[] args) {
        launch(args);
    }
}
