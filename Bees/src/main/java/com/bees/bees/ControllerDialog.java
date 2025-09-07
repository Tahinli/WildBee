package com.bees.bees;



import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ControllerDialog {

    public static Pangram beeHiveLetters;
    public static int numberOfWords;
    public static boolean isAllFound;

    public static String[] messages = {"Beginner", "Good Start", "Moving Up", "Good", "Solid", "Nice",  "Great",
            "Amazing", "Genius", "Queen Bee"};
    public static int maxPoint;

    public static int pointFromWord;
    public static String wordMessage;

    public static int pointFromGame;

    public static String gameMessage;

    public static void func() throws FileNotFoundException {
        Dictionary.read();
        Dictionary.processDictionary();
        Dictionary.findPossiblePangramWords(); //HERE ########################################
        //Dictionary.printAllSetOfWordsOfPangram();


        /*
        for(int i = 0; i < Math.pow(2, 30); i++)  {
            if(Dictionary.map.containsKey(i))  {

                System.out.printf("Başlangıç %s\n", Pangram.getWordsFromHashCode(i));
                for(Word word: Dictionary.map.get(i))
                    System.out.printf("%s CD 38\n", word.word);
                System.out.printf("End\n\n");

            }
        }
         */

        /*
        int i = 0;
        for(Pangram pangram: Dictionary.pangramWords) {
            System.out.printf("\n%d %s %c %d CD line 45\n", i++, pangram.name, pangram.centerLetter, pangram.setOfWords.size());
            int j = 0;
            for(Word word: pangram.setOfWords)
                System.out.printf("%d %s %s\n", j++, pangram.name, word.name);
            System.out.printf("\n");
        }
        */



        /*
        int a = 0;
        for(Word word: Dictionary.dictionary) {
            System.out.printf("%d %s %s %d %b %d\n", a++, word.word, String.valueOf(word.letters), word.point, word.isPangram, word.hashCode);
        }
        System.out.printf("%d\n", Dictionary.dictionary.size());

        System.out.printf("%d %b\n", Dictionary.pangramWords.size(), Dictionary.pangramWords.isEmpty());

        for(Pangram pangram: Dictionary.pangramWords) {
            for (Word word2 : pangram.setOfWords)
                System.out.printf("%d %s created\n", word2.hashCode, word2.word);
            System.out.println();
        }

         */
        /*
        int a = 0;
        for (; a < dict.pangramWords.size(); a++) {
            System.out.printf("%d %s %c %d %d pangram words properties\n", a++, dict.pangramWords.get(a).word, dict.pangramWords.get(a).centerLetter, dict.pangramWords.get(a).totalNumberOfWords, dict.pangramWords.get(a).totalPoint);
        }
        */

        //System.out.println("########################################################");
        //System.out.printf("%d %b\n", Dictionary.pangramWords.size(), Dictionary.pangramWords.isEmpty());
        //dict.printAllPangramWords();

        /*
        beeHiveLetters = Dictionary.gameLetters();

        System.out.println("BeeHiveLetters' setOfWords are: CD 88");
        for(Word word: beeHiveLetters.setOfWords)
            System.out.printf("%s\n", word.name);
        */
        /*
        System.out.printf("all possible words %s %c\n", beeHiveLetters.word, beeHiveLetters.centerLetter);
        int i = 0;
        for(Word word: beeHiveLetters.setOfWords) {
            System.out.printf("%d %s %d %s\n", i++, word.word, word.point, word.message);
        }
        */

        //System.out.printf("There are %d pangram words.\n", Dictionary.pangramWords.size());
        //System.out.printf("Hello World\n");

        /*
        for(int a = 0; a < 29; a++)
            System.out.printf("%d ", Dictionary.turkishUpperCaseLetters.indexOf(Dictionary.turkishUpperCaseLetters.charAt(a)) );
        Word w = new Word();
        w.name = "DEDE";
        w.calculatePoint();
        System.out.printf("\n%d\n", w.hashCode);
        */
    }

    public static Pangram getBeeHiveLetters() {
        beeHiveLetters = Dictionary.gameLetters();
        for(int i = 0; i < beeHiveLetters.wordsList.size(); i++)
            System.out.printf("%s\n", beeHiveLetters.wordsList.get(i)) ;
        return beeHiveLetters;
    }

    public static boolean getPangram(String str) {
        Word word = new Word();
        word.name = str;
        if(word.checkWord() && word.countDistinctLetters() == 7) {
            Pangram pangram = new Pangram();
            long hc = (long) 8 * word.hashCode;
            if(!Dictionary.pangramWordsHashMap.containsKey(hc))
                return true;
        }
        return false;
    }


    /*
    public Word checkWord(Word word) {
        word.checkWord();
        if(word.point > 0) {

            totalPoint += word.point;
            numberOfWords++;
            if(numberOfWords == beeHiveLetters.totalNumberOfWords)
                isAllFound = true;
        }
        return word;
    }
    */

    /*
    public Word gameStatus() {
        Word word = new Word();
        word.point = totalPoint;
        if(word.point == maxPoint)
            word.message = messages[9];
        else {
            int a = word.point;
            if (a >= 90)
                a -= 10;
            word.message = messages[totalPoint / 10];
        }
        message = word.message;
        return word;
    }
    */

    public static Message checkIfAvailableFromPangram(String str) {
        System.out.println(str);
        Message message = beeHiveLetters.checkIfAvailableFromPangram(str);
        if(message.point > 0) {
            int ind = Dictionary.reverseDictionary.get(str);
            beeHiveLetters.foundWordsHash.put(str, ind);
            beeHiveLetters.foundWords.add(ind);
            numberOfWords++;
            pointFromGame += pointFromWord;
        }
        return message;
    }


    /*
    public ControllerDialog checkIfAvailableFromPangram(ControllerDialog cd, Word w) {
        ControllerDialog ret = beeHiveLetters.checkIfAvailableFromPangram(cd, w);
        return ret;
    }
    */

    /*
    public ControllerDialog copyOfCD() {
        ControllerDialog ret = new ControllerDialog();
        ret.beeHiveLetters = beeHiveLetters;
        ret.numberOfWords = numberOfWords;
        ret.isAllFound = isAllFound;
        ret.messages = messages;
        ret.maxPoint = maxPoint;
        ret.pointFromWord = pointFromWord;
        ret.wordMessage = wordMessage;
        ret.pointFromGame = pointFromGame;
        ret.gameMessage = gameMessage;
        return ret;
    }
    */
}
