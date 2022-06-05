package com.wildbee.wildbee;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.stage.Stage;

/**
 * It is the class of Java Fx, in other words Game launching.
 * @since 2022-06
 */


/*
*
* AnimateFX Kütüphanesi Hata Alması Dahilinde lib klasöründeki Kütüphanenin WildBee
Projesine Eklenmesiyle Çözülecektir. Maven Üzerinden Eklemek için

<dependency>
  <groupId>io.github.typhon0</groupId>
  <artifactId>AnimateFX</artifactId>
  <version>1.2.2</version>
  <type>pom</type>
</dependency>


Kullanılabilir.

*/
public class FXStarter extends Application {
    Stage stage1 = new Stage();
    Loading loading = new Loading();
    boolean b = false;

    /**
     * @param stage stage parameter.
     */
    @Override
    public void start(Stage stage) {
        loading.loader(stage1);
        Thread thread = new Thread(new starter());
        thread.setDaemon(true);
        thread.start();
        b = true;
    }

    /**
     * This is the part that concerns the control dialog class.
     */
    static class starter extends Task<Integer>{
        @Override
        protected Integer call() throws Exception {
            ControllerDialog.func();
            return 11833;
        }
    }
}

