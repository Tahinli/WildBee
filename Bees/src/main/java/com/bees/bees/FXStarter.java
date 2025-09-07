package com.bees.bees;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicInteger;

public class FXStarter extends Application {
    AtomicInteger a = new AtomicInteger(0);
    Stage stage1 = new Stage();
    Stage stageL = new Stage();
    Main menuItself = new Main();
    Loading loading = new Loading();
    boolean b = false;
    Integer p = 0;
    Timer timer = new Timer();

    @Override
    public void start(Stage stage)throws Exception {
        loading.loader(stage1);
        Thread thread = new Thread(new starter());
        thread.setDaemon(true);
        thread.start();
        b = true;
    }

    public class starter extends Task<Integer>{
        @Override
        protected Integer call() throws Exception {
            ControllerDialog.func();
            return 11833;
        }
    }
}

