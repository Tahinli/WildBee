package com.wildbee.wildbee;

import animatefx.animation.*;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicBoolean;


/**
 * It is the class that concerns the main parts of the game.
 * @since 2022-06
 */
class Main{
    //Object
    Game gameR = new Game();
    WordPicker wordPicker = new WordPicker();

    /**
     * @param stage What concerns the scene part is the parameter with its settings.
     */
    void menu(Stage stage) {
        //Definition
        AtomicBoolean c = new AtomicBoolean(false);
        Pane pane = new Pane();
        Button btRandom = new Button("Quick Start");
        Button btChosen = new Button("Give a Word");
        Label lbWelcome = new Label("Welcome");
        RadioButton rTurkish = new RadioButton("Türkçe");
        RadioButton rEnglish = new RadioButton("English");
        ToggleGroup language = new ToggleGroup();
        AtomicBoolean cLanguage = new AtomicBoolean(false);

        //Size
        btRandom.setMinSize(100,50);
        btChosen.setMinSize(100,50);
        btRandom.setMaxSize(100,50);
        btChosen.setMaxSize(100,50);
        lbWelcome.setMinSize(100,50);
        rTurkish.setMinSize(100,50);
        rTurkish.setMaxSize(100,50);
        rEnglish.setMinSize(100,50);
        rEnglish.setMaxSize(100,50);

        //Location
        btRandom.relocate(120,120);
        btChosen.relocate(120,180);
        lbWelcome.relocate(120,30);
        rTurkish.relocate(300,200);
        rEnglish.relocate(300,230);

        //VFX
        lbWelcome.setStyle("-fx-font-weight: Bolder");
        lbWelcome.setStyle("-fx-font-size: 25");
        new Wobble(lbWelcome).setCycleCount(-1).play();
        new GlowText(lbWelcome, Color.CORAL,Color.GREEN).setCycleCount(-1).play();

        //Action
        btRandom.setOnAction(actionEvent -> {
            new FadeIn(btRandom).play();
            c.set(false);
            callBack(cLanguage.get(),stage,c.get());
            unVisible(stage);
        });
        btChosen.setOnAction(actionEvent -> {
            c.set(true);
            new FadeIn(btChosen).play();
            wordPicker.wp(cLanguage.get(),stage,c.get());
            unVisible(stage);
        });
        //Language Detection
        rEnglish.setSelected(true);
        rTurkish.setOnAction(actionEvent -> {
            cLanguage.set(true);
            btRandom.setText("Hızlı Başla");
            btChosen.setText("Kelime Ver");
            lbWelcome.setText("Hoşgeldin");
            ControllerDialog.setLanguage(0);
        });
        rEnglish.setOnAction(actionEvent -> {
            cLanguage.set(false);
            btRandom.setText("Quick Start");
            btChosen.setText("Give a Word");
            lbWelcome.setText("Welcome");
            ControllerDialog.setLanguage(1);
        });

        //Thread
        Thread thread = new Thread(new writer());
        thread.setDaemon(true);
        thread.start();

        rTurkish.setToggleGroup(language);
        rEnglish.setToggleGroup(language);
        stage.setTitle("Wild Bees");
        stage.setScene(new Scene(pane,400,300));
        pane.getChildren().addAll(btRandom,btChosen,lbWelcome,rTurkish,rEnglish);
        stage.setResizable(false);
        stage.show();
        stage.toFront();

    }

    /**
     * This is the part about writer.
     */
    static class writer extends Task<Integer>{
        @Override
        protected Integer call() throws Exception {
            ControllerDialog.writeWordsAndPangrams();
            return 1;
        }
    }

    /**
     * @param stage is the visible scene parameter.
     */
    void visible(Stage stage){
        stage.show();
        stage.toFront();

    }

    /**
     * @param stage is the unvisible scene parameter.
     */
    void unVisible(Stage stage){
        stage.hide();

    }

    /**
     * @return It is the part where the incoming word is returned.
     */
    StringBuilder gWordReturner(){
        Pangram pangram = ControllerDialog.getBeeHiveLetters();
        StringBuilder gWord = new StringBuilder(pangram.letters);
        Character middle = pangram.centerLetter;
        gWord.deleteCharAt(gWord.indexOf(middle.toString()));
        gWord.trimToSize();
        gWord.append(middle);
        gWord.trimToSize();
        return gWord;
    }

    /**
     * @param cLanguage It is the parameter set according to the language.
     * @param stage is the parameter of the scene part called back.
     * @param c is the parameter of the scene part called back.
     */
    void callBack(Boolean cLanguage,Stage stage, Boolean c){
        StringBuilder tempG = gWordReturner();
        Character tempM = tempG.charAt(tempG.length()-1);
        gameR.rGame(cLanguage,stage,new Stage(),tempG,c,tempM);
    }
}
