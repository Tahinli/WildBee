package com.wildbee.wildbee;


import animatefx.animation.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class is the most detailed part of the game part.
 * @since 2022-06
 */
class Game {
    /**
     * It is the class where the events that will happen from the moment the game is opened are written. If we go into details, the game contains parts such as "delete" "clear" "shuffle" "enter" "reload" and warning texts during the game, and details about the colors, positions and fonts of these buttons and warnings are written.
     * @since 2022-06
     * @param l It is the parameter named l used in the operation of the game.
     * @param stage1 It is the parameter named stage1 used in the operation of the game.
     * @param rStage It is the parameter named rStage used in the operation of the game.
     * @param gWord The parameter of the query word.
     * @param c Represents the bool value named c.
     * @param middle It is the parameter that concerns the middle letter (yellow hexagon).
     */
    void rGame(boolean l,Stage stage1,Stage rStage,StringBuilder gWord, boolean c, Character middle) {
        //Object
        Main menu = new Main();
        WordPicker wordPicker = new WordPicker();
        Finish finish = new Finish();

        //Definitions
        gWord.deleteCharAt(gWord.indexOf(middle.toString()));
        gWord.trimToSize();
        ArrayList<String> givenW = new ArrayList<>();
        for (int i = 0; i <gWord.length() ; i++)
            givenW.add(String.valueOf(gWord.charAt(i)));
        StringBuilder text = new StringBuilder();


        Button btm = new Button(middle.toString());
        Button bt1 = new Button(String.valueOf(gWord.charAt(0)));
        Button bt2 = new Button(String.valueOf(gWord.charAt(1)));
        Button bt3 = new Button(String.valueOf(gWord.charAt(2)));
        Button bt4 = new Button(String.valueOf(gWord.charAt(3)));
        Button bt5 = new Button(String.valueOf(gWord.charAt(4)));
        Button bt6 = new Button(String.valueOf(gWord.charAt(5)));
        Button btDelete = new Button("Delete");
        Button btClear = new Button("Clear");
        Button btShuffle = new Button("Shuffle");
        Button btEnter = new Button("Enter");
        Button btReload = new Button("Reload");
        Button btMain = new Button("Main Menu");
        Label showError = new Label("Welcome To The Our Game");
        Label lbPoint = new Label("0");
        Label lbPText = new Label("Your Point");
        Label lbP = new Label("Beginner");
        if (l)
            lbP.setText("Başlangıç");
        TextField uTxt = new TextField();
        TextArea rTxt = new TextArea();
        ProgressBar pbPoint = new ProgressBar(0.0);



        //Customization
        lbPoint.setStyle("-fx-font-weight: Bold");lbPoint.setStyle("-fx-font-size: 25");lbPoint.setTextFill(Color.CRIMSON);
        lbPText.setStyle("-fx-font-weight: Bold");lbPText.setStyle("-fx-font-style: italic");lbPText.setTextFill(Color.DARKCYAN);lbPText.setStyle("-fx-font-size: 16");
        lbP.setStyle("-fx-font-weight: Bold");lbPText.setStyle("-fx-font-style: italic");lbPText.setTextFill(Color.DARKORCHID);lbPText.setStyle("-fx-font-size: 13");
        if (l) {
            showError.setText("Oyunumuza Hoş Geldin");
            lbPText.setText("Puanınız");
        }
        showError.setTextFill(Color.CORAL);showError.setStyle("-fx-font-weight: Bold");showError.setStyle("-fx-font-size: 25");
        new Tada(showError).setSpeed(0.25).setCycleCount(1).play();
        rTxt.setEditable(false);
        uTxt.clear();
        rTxt.clear();
        btm.setStyle("-fx-text-fill: WHITE");
        btm.setStyle("-fx-font-weight: Bold");
        bt1.setStyle("-fx-font-weight: Bold");
        bt2.setStyle("-fx-font-weight: Bold");
        bt3.setStyle("-fx-font-weight: Bold");
        bt4.setStyle("-fx-font-weight: Bold");
        bt5.setStyle("-fx-font-weight: Bold");
        bt6.setStyle("-fx-font-weight: Bold");
        btEnter.setStyle("-fx-font-weight: Bold");
        btDelete.setStyle("-fx-font-weight: Bold");
        btShuffle.setStyle("-fx-font-weight: Bold");
        btClear.setStyle("-fx-font-weight: Bold");
        btMain.setStyle("-fx-font-weight: Bold");
        btReload.setStyle("-fx-font-weight: Bold");
        btm.setStyle("-fx-background-color: YELLOW");

        //Shape
        Polygon hex = new Polygon();
        hex.getPoints().addAll(100.0, 50.0,
                300.0, 50.0,
                400.0, 78.86,
                300.0, 107.72,
                100.0, 107.72,
                0.0, 78.86);

        btm.setShape(hex);
        bt1.setShape(hex);
        bt2.setShape(hex);
        bt3.setShape(hex);
        bt4.setShape(hex);
        bt5.setShape(hex);
        bt6.setShape(hex);



        //Size
        btm.setMinSize(100,100);
        bt1.setMinSize(100,100);
        bt2.setMinSize(100,100);
        bt3.setMinSize(100,100);
        bt4.setMinSize(100,100);
        bt5.setMinSize(100,100);
        bt6.setMinSize(100,100);
        btDelete.setMinSize(70,50);
        btClear.setMinSize(55,40);
        btShuffle.setMinSize(70,50);
        btEnter.setMinSize(70,50);
        btReload.setMinSize(70,50);
        btMain.setMinSize(70,50);
        lbPoint.setMinSize(50,50);
        lbPoint.setMaxSize(50,50);
        lbPText.setMinSize(100,50);
        lbPText.setMaxSize(100,50);
        lbP.setMinSize(100,50);
        lbP.setMaxSize(100,50);
        showError.setMinSize(600,50);
        showError.setMaxSize(600,50);
        uTxt.setMinSize(200,50);
        uTxt.setMaxSize(200,50);
        rTxt.setMinSize(200,500);
        rTxt.setMaxSize(200,500);
        pbPoint.setMinSize(250,30);
        pbPoint.setMaxSize(250,30);

        //Location
        btm.relocate(200,270);
        bt1.relocate(200,170);
        bt2.relocate(278,220);
        bt3.relocate(278,320);
        bt4.relocate(200,370);
        bt5.relocate(122,320);
        bt6.relocate(122,220);
        btDelete.relocate(120,500);
        btClear.relocate(120,600);
        btShuffle.relocate(220,500);
        btEnter.relocate(320,500);
        btReload.relocate(650,500);
        btMain.relocate(650,600);
        lbPoint.relocate(900,100);
        lbPText.relocate(850,70);
        lbP.relocate(950,20);
        showError.relocate(350,100);
        uTxt.relocate(80,100);
        rTxt.relocate(1000,100);
        pbPoint.relocate(950,70);

        //Actions
        btEnter.setOnAction(actionEvent -> {
            new Jello(btEnter).setSpeed(1.5).play();
            cleaner(uTxt);
            if (middleChecker(uTxt,btm))
            {
                showError.setText(" ");
                text.append(uTxt.getText());
                Message message = ControllerDialog.checkIfAvailableFromPangram(uTxt.getText());
                if (message.point>0) {
                    pbPoint.setProgress((double)ControllerDialog.pointFromGame/ControllerDialog.maxPoint);
                    areaFiller(text, rTxt);
                    uTxt.clear();
                    text.setLength(0);
                    showError.setVisible(true);
                    showError.setStyle("-fx-font-weight: Bolder");
                    showError.setStyle("-fx-font-style: Italic");
                    showError.setTextFill(Color.DARKCYAN);
                    showError.setStyle("-fx-font-size: 25");
                    lbP.setText(message.game);
                    lbPoint.setText(String.valueOf(ControllerDialog.pointFromGame));
                    showError.setText(message.word+"   "+"+"+message.point);
                    if (ControllerDialog.pointFromGame == ControllerDialog.maxPoint)
                    {
                        finish.finisher(message.finish, l, btReload);
                        if (l)
                            lbP.setText("VAHŞİ ARI");
                        else
                            lbP.setText("WILD BEE");
                    }
                }
                else
                {

                    String prInfo = message.word;
                    showError.setVisible(true);
                    showError.setStyle("-fx-font-weight: Bolder");
                    showError.setStyle("-fx-font-style: Italic");
                    showError.setTextFill(Color.RED);
                    showError.setStyle("-fx-font-size: 20");
                    text.setLength(0);
                    showError.setText(prInfo);
                    uTxt.end();
                    new Pulse(showError).play();
                }

            }
            else
            {
                showError.setVisible(true);
                showError.setStyle("-fx-font-weight: Bolder");
                showError.setStyle("-fx-font-style: Italic");
                showError.setTextFill(Color.RED);
                showError.setStyle("-fx-font-size: 25");

                //Language
                if (l)
                    showError.setText("Ortadaki Harfi İçermek Zorunda");
                else
                    showError.setText("Word must contain middle letter !");
                uTxt.end();
                new Pulse(showError).play();
            }
        });
        btm.setOnAction(actionEvent -> pressedL(btm,uTxt));
        bt1.setOnAction(actionEvent -> pressedL(bt1,uTxt));
        bt2.setOnAction(actionEvent -> pressedL(bt2,uTxt));
        bt3.setOnAction(actionEvent -> pressedL(bt3,uTxt));
        bt4.setOnAction(actionEvent -> pressedL(bt4,uTxt));
        bt5.setOnAction(actionEvent -> pressedL(bt5,uTxt));
        bt6.setOnAction(actionEvent -> pressedL(bt6,uTxt));
        btDelete.setOnAction(actionEvent -> {
            new Flash(btDelete).setSpeed(4).play();
            uTxt.requestFocus();
            uTxt.selectEnd();
            uTxt.deletePreviousChar();
            uTxt.requestFocus();
        });
        btClear.setOnAction(actionEvent -> {
            new JackInTheBox(btClear).setSpeed(1.25).play();
            uTxt.clear();
            uTxt.requestFocus();
        });
        btShuffle.setOnAction(actionEvent -> {
            new RubberBand(btShuffle).setSpeed(2).play();
            Collections.shuffle(givenW);
            areaFiller(givenW,bt1,bt2,bt3,bt4,bt5,bt6);
        });
        btReload.setOnAction((actionEvent -> {
            new Flip(btReload).setSpeed(5).play();
            if (c)
            {
                wordPicker.wp(l,stage1,true);
                rStage.close();
            }
            else {
                rStage.close();
                menu.callBack(l, stage1, false);
            }
        }));
        btMain.setOnAction(actionEvent -> {
            menu.visible(stage1);
            rStage.close();
        });

        //Language
        if (l)
        {
            btClear.setText("Temizle");
            btDelete.setText("Sil");
            btReload.setText("Yenile");
            btShuffle.setText("karıştır");
            btEnter.setText("Onayla");
            btMain.setText("Ana Menü");
        }

        Pane pane = new Pane();
        pane.getChildren().addAll(btm,bt1,bt2,bt3,bt4,bt5,bt6,btDelete,btClear,btShuffle,btEnter,btReload,btMain,showError,lbPText,lbPoint,lbP,uTxt,rTxt,pbPoint);
        rStage.setTitle("Bees");
        rStage.setScene(new Scene(pane,1280,720));
        new FadeIn(pane).play();
        rStage.setResizable(false);

        //Key Detection
        pane.setOnKeyPressed(keyEvent -> {
            uTxt.requestFocus();
            StringBuilder key = new StringBuilder();
            key.append(keyEvent.getCode().toString());
            switch (key.toString()) {
                case "ENTER" -> btEnter.fire();
                case "BACK_SPACE" -> btDelete.fire();
                case "SPACE" -> btShuffle.fire();
                case "ALT" -> btReload.fire();
                case "CONTROL" -> btMain.fire();
                case "ESCAPE" -> btClear.fire();
            }
            key.setLength(0);
        });

        uTxt.setOnKeyPressed(keyEvent -> {
            cleaner(uTxt);
            StringBuilder key = new StringBuilder();
            key.append(keyEvent.getCode().toString());
            key.trimToSize();
            switch (key.toString()) {
                case "SPACE" -> btShuffle.fire();
                case "ESCAPE" -> btClear.fire();
            }
            key.setLength(0);
        });

        rStage.show();
        rStage.toFront();

        rStage.setOnCloseRequest(windowEvent -> menu.visible(stage1));
    }

    /**
     * @param stringBuilder The parameter that puts the string list into a condition.
     * @param textArea The parameter that puts the text area into a condition.
     */
    void areaFiller(StringBuilder stringBuilder ,TextArea textArea)
    {
        if (!stringBuilder.isEmpty())
            textArea.appendText((stringBuilder)+"\n");
    }

    /**
     * @param arrayList It is the parameter that examines the list of strings by button.
     * @param bt1 It is the parameter that concerns button 1.
     * @param bt2 It is the parameter that concerns button 2.
     * @param bt3 It is the parameter that concerns button 3.
     * @param bt4 It is the parameter that concerns button 4.
     * @param bt5 It is the parameter that concerns button 5.
     * @param bt6 It is the parameter that concerns button 6.
     */
    void areaFiller( ArrayList arrayList, Button bt1, Button bt2, Button bt3, Button bt4, Button bt5, Button bt6)
    {
        bt1.setText((String)arrayList.get(0));
        bt2.setText((String)arrayList.get(1));
        bt3.setText((String)arrayList.get(2));
        bt4.setText((String)arrayList.get(3));
        bt5.setText((String)arrayList.get(4));
        bt6.setText((String)arrayList.get(5));
    }

    /**
     * @param textField It is the parameter of the text field.
     * @param btm Button parameter.
     * @return Returns the text in the text field.
     */
    boolean middleChecker(TextField textField,Button btm)
    {
        return textField.getText().contains(btm.getText());
    }

    /**
     * @param btReload It is the parameter that concerns the reload part.
     */
    void btReloadFire(Button btReload){
        btReload.fire();
    }

    /**
     * @param uTxt It is the parameter of the cleaning process.
     */
    void cleaner(TextField uTxt){
        uTxt.setText(uTxt.getText().replaceAll("[^a-zA-z],[^şçğü]"," "));
        uTxt.setText(uTxt.getText().replaceAll("-"," "));
        uTxt.setText(uTxt.getText().replaceAll("_", " "));
        uTxt.setText(uTxt.getText().trim());
        if (uTxt.getText().contains("i"))
            uTxt.setText(uTxt.getText().replaceAll("i","İ"));
        uTxt.setText(uTxt.getText().toUpperCase());
        uTxt.end();
    }
    void pressedL(Button bt,TextField uTxt){
        new RubberBand(bt).play();
        uTxt.appendText(bt.getText());
        uTxt.requestFocus();
        uTxt.selectEnd();
    }
}
