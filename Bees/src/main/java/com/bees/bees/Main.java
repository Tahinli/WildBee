package com.bees.bees;



import animatefx.animation.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicBoolean;



public class Main{
    //Object
    Game gameR = new Game();
    WordPicker wordPicker = new WordPicker();
    Loading loading = new Loading();
    public void menu(Stage stage) throws Exception {
        //Communication
        //ControllerDialog.func();
        System.out.printf("Menuye girdi %d pangram var Main 27\n", Dictionary.pangramsDictionary.size());
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

        //Starter
        btRandom.setOnAction(actionEvent -> {
            new FadeIn(btRandom).play();
            c.set(false);
            callBack(cLanguage.get(),stage,c.get());
            unVisible(stage);
        });
        btChosen.setOnAction(actionEvent -> {
            c.set(true);
            new FadeIn(btChosen).play();
            wordPicker.wp(cLanguage.get(),stage,new Stage(),c.get());
            unVisible(stage);
        });
        //Language Detection
        rEnglish.setSelected(true);
        rTurkish.setOnAction(actionEvent -> {
            cLanguage.set(true);
            System.out.println(cLanguage.toString());
            btRandom.setText("Hızlı Başla");
            btChosen.setText("Kelime Ver");
            lbWelcome.setText("Hoşgeldin");
            ControllerDialog.setLanguage(0);
        });
        rEnglish.setOnAction(actionEvent -> {
            cLanguage.set(false);
            System.out.println(cLanguage.toString());
            btRandom.setText("Quick Start");
            btChosen.setText("Give a Word");
            lbWelcome.setText("Welcome");
            ControllerDialog.setLanguage(1);
        });

        rTurkish.setToggleGroup(language);
        rEnglish.setToggleGroup(language);
        stage.setTitle("Wild Bees");
        stage.setScene(new Scene(pane,400,300));
        pane.getChildren().addAll(btRandom,btChosen,lbWelcome,rTurkish,rEnglish);
        stage.setResizable(false);
        stage.show();
        stage.toFront();

    }

    public void visible(Stage stage){
        stage.show();

    }
    public void unVisible(Stage stage){
        stage.hide();

    }
    public StringBuilder gWordReturner(){
        Pangram pangram = ControllerDialog.getBeeHiveLetters();
        StringBuilder gWord = new StringBuilder(pangram.letters);
        Character middle = pangram.centerLetter;
        System.out.println("GELEN KELİME İLK = "+ gWord);
        System.out.println("ORTADAKİ HARF = "+ middle);
        gWord.deleteCharAt(gWord.indexOf(middle.toString()));
        gWord.trimToSize();
        gWord.append(middle);
        gWord.trimToSize();
        System.out.println("GELEN KELİME FİNAL = "+ gWord);
        return gWord;
    }
    public void callBack(Boolean cLanguage,Stage stage, Boolean c){
        StringBuilder tempG = gWordReturner();
        Character tempM = tempG.charAt(tempG.length()-1);
        gameR.rGame(cLanguage,stage,new Stage(),tempG,c,tempM);
    }
}
