package com.wildbee.wildbee;


import animatefx.animation.Wobble;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This class is a class where the word is entered and the word is selected.
 * @since 2022-06
 */
class WordPicker {
    void wp (boolean l, Stage stage1,boolean c)
    {
        //Object
        Main menu = new Main();
        Game gameNR = new Game();

        //Definition
        StringBuilder gWord = new StringBuilder();
        Stage stage = new Stage();
        Button btEnter = new Button("Enter");
        Label lbInfo = new Label("Give a Word");
        TextField uTxt = new TextField("");
        Pane pane = new Pane();
        lbInfo.setStyle("-fx-font-weight: Bolder");
        lbInfo.setStyle("-fx-font-style: Italic");
        lbInfo.setTextFill(Color.BLUEVIOLET);
        lbInfo.setStyle("-fx-font-size: 25");


        //Size
        btEnter.setMinSize(70,50);
        uTxt.setMinSize(200,50);
        lbInfo.setMinSize(70,50);

        //Location
        btEnter.relocate(100,150);
        uTxt.relocate(50,70);
        lbInfo.relocate(50,20);

        //Language
        if (l)
        {
            btEnter.setText("Giriş");
            lbInfo.setText("Kelime Giriniz");
        }

        btEnter.setOnAction(actionEvent -> {
            new Wobble(btEnter).play();
            if (!uTxt.getText().isEmpty())
            {
                gameNR.cleaner(uTxt);
                boolean b = ControllerDialog.getPangram(uTxt.getText());
                if (b)
                {
                    gWord.setLength(0);
                    gWord.append(uTxt.getText());
                    gameNR.rGame(l, stage1, new Stage(), gWord, c, gWord.charAt(0));
                    stage.close();
                }
            }
        });

        pane.getChildren().addAll(btEnter,uTxt,lbInfo);
        stage.setTitle("WordPicker");
        stage.setScene(new Scene(pane,300,200));
        stage.setResizable(false);
        uTxt.requestFocus();

        pane.setOnKeyPressed(keyEvent -> {
            uTxt.requestFocus();
            StringBuilder key = new StringBuilder();
            key.append(keyEvent.getCode().toString());
            switch (key.toString()) {
                case "ENTER" -> btEnter.fire();
                case "ESCAPE" -> uTxt.clear();
            }
            key.setLength(0);
        });
        uTxt.setOnKeyPressed(keyEvent -> {
            gameNR.cleaner(uTxt);
            StringBuilder key = new StringBuilder();
            key.append(keyEvent.getCode().toString());
            key.trimToSize();
            if (key.toString().equals("ESCAPE"))
                uTxt.clear();
            key.setLength(0);
        });
        stage.show();

        stage.setOnCloseRequest(windowEvent -> {
            try {
                menu.visible(stage1);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }
}