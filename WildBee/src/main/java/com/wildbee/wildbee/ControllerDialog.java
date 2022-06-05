package com.wildbee.wildbee;

import javafx.print.PrintResolution;
import javafx.print.Printer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.spec.ECField;
import java.util.Random;

/**
 * This class is concerned with words, transmission of words and score.
 * @since 2022-06
 */
class ControllerDialog {

    static Pangram beeHiveLetters;
    static int numberOfWords;

    static String[] messages = {"Beginner", "Good Start", "Moving Up", "Good", "Solid", "Nice", "Great",
            "Amazing", "Genius", "Almost There", "Queen Bee", "Başlangıç", "İyi Başlangıç", "Devam et", "İyi Gidiyorsun", "Sağlam", "Güzel", "Muhteşem",
            "Harika", "Dahi", "Neredeyse Bitti", "Kraliçe Arı"};
    static int maxPoint;
    static int pointFromGame;

    static int language = 1;
    static int processedPangrams = 0;

    /**
     * The place where the dictionary is is written, the dictionary is read and processed. The word list and pangrams are then expressed in the terminal.
     * @throws FileNotFoundException the part that runs if the file is not found
     */
    static void func() throws FileNotFoundException {
        Dictionary.read();
        Dictionary.processDictionary();
    }

    /**
     * @param i Allows you to select the game language at the start of the game.
     */
    static void setLanguage(int i) {
        language = i;
    }

    /**
     * It is returned if the conditions are met for the letters to be written in the beehive.
     * @return bee hive returns letters.
     */
    static  Pangram getBeeHiveLetters() {
        Random rand = new Random();
        int a = rand.nextInt(Dictionary.pangramsDictionary.size());
        int i = 0;
        for(Pangram pangram: Dictionary.pangramsDictionary) {
            if (i == a) {
                beeHiveLetters = pangram;
                break;
            }
            i++;
        }
        maxPoint = beeHiveLetters.totalPoint;
        pointFromGame = 0;
        numberOfWords = 0;
        return ControllerDialog.beeHiveLetters;
    }

    /**
     * @param str The name of the words to be entered is not determined as str.
     * @return If The word is correct, it returns the word to the list of correct answers.
     */
    static boolean getPangram(String str) {
        Word word = new Word();
        word.name = str;
        if(!word.checkWord() || !word.isPangram || word.name.length()!=7)
            return false;
        Pangram pangram = new Pangram();
        pangram.name = word.name;
        pangram.letters = word.letters;
        pangram.point = word.point;
        pangram.isPangram = word.isPangram;
        pangram.hashCode = word.hashCode;
        pangram.centerLetter = pangram.name.charAt(0);
        pangram.pangramHashCode = 8 * pangram.hashCode + word.letters.indexOf(pangram.centerLetter);
        pangram.totalNumberOfWords = pangram.totalPoint = 0;

        boolean b = false;
        for(Pangram p: Dictionary.pangramsDictionary)
            if(pangram.pangramHashCode == p.pangramHashCode && pangram.centerLetter == p.centerLetter) {
                beeHiveLetters = p;
                b = true;
                break;
            }
        if(!b)
            return false;

        maxPoint = beeHiveLetters.totalPoint;
        pointFromGame = 0;

        return true;
    }

    /**
     * @param str The word entered in the game is expressed with str.
     * @return Returns the availability message.
     */
    static Message checkIfAvailableFromPangram(String str) {
        Message message = beeHiveLetters.checkIfAvailableFromPangram(str);
        if (message.point > 0) {
            beeHiveLetters.foundWords.add(str);
            pointFromGame += message.point;
            numberOfWords++;
        }

        return message;
    }

    /**
     * @throws Exception .
     */
    static void writeWordsAndPangrams() throws Exception   {
        PrintWriter writer = new PrintWriter("cheatSheet.txt", StandardCharsets.UTF_8);

        writer.printf("Sözcük listesi hashcodelarıyla\n");
        int i = 0;
        for(Word word: Dictionary.dictionary)  {
            writer.printf("########## %d %s %d sözcük listesi\n",i++, word.name, word.hashCode);
            int j = 0;
            for(Word word2: Dictionary.wordsMap.get(word.hashCode))
                writer.printf("--- %d %s %d\n", j++, word2.name, word2.hashCode);
        }


        writer.printf("\n\n\n\n\n\n\n\n\n\n\n\n Pangram ve setOfWordsları\n\n\n\n\n\n\n\n\n");
        i = 0;
        for(Pangram pangram: Dictionary.pangramsDictionary)  {
            writer.printf("%d %s %s %c %d %d %d pangramın set of word listesi\n", i++, pangram.name, pangram.letters,
                    pangram.centerLetter, pangram.pangramHashCode,
                    pangram.totalNumberOfWords, pangram.totalPoint);

            int j = 0;
            for(Word word: pangram.setOfWords)
                writer.printf("%d %s %d\n", j++, word.name, word.hashCode);

        }
    }

}

