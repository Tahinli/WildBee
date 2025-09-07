package com.wildbee.wildbee;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.stage.Stage;

public class FXStarter extends Application {
    Stage stage1 = new Stage();
    Loading loading = new Loading();
    boolean b = false;

    @Override
    public void start(Stage stage) {
        loading.loader(stage1);
        Thread thread = new Thread(new starter());
        thread.setDaemon(true);
        thread.start();
        b = true;
    }

    static class starter extends Task<Integer>{
        @Override
        protected Integer call() throws Exception {
            ControllerDialog.func();
            return 11833;
        }
    }
}

