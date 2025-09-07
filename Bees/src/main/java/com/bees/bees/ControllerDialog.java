package com.bees.bees;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class ControllerDialog {

    public static Pangram beeHiveLetters;
    public static Dictionary dict;
    public static int numberOfWords;
    public static boolean isAllFound;

    public static String[] messages = {"Beginner", "Good Start", "Moving Up", "Good", "Solid", "Nice", "Great",
            "Amazing", "Genius", "Almost There", "Queen Bee", "Başlangıç", "İyi Başlangıç", "Devam et", "İyi Gidiyorsun", "Sağlam", "Güzel", "Muhteşem",
            "Harika", "Dahi", "Neredeyse Bitti", "Kraliçe Arı"};
    public static int maxPoint;

    public static int pointFromWord;
    public static String wordMessage;

    public static int pointFromGame;

    public static String gameMessage;
    public static int language = 1;
    public static int processedPangrams = 0;

    public static void func() throws FileNotFoundException, UnsupportedEncodingException {
        /*
        dict = new Dictionary();
        dict.read();
        dict.processDictionary();


         */
        Dictionary.read();
        Dictionary.processDictionary();

        PrintWriter writer = new PrintWriter("kelimeler.txt", "UTF-8");


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

    public static void setLanguage(int i) {
        language = i;
    }

    public static  Pangram getBeeHiveLetters() {
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
        for(Word word :beeHiveLetters.setOfWords)
            System.out.printf("%s Kopya\n", word.name);
        return ControllerDialog.beeHiveLetters;
    }


    public static boolean getPangram(String str) {
        Word word = new Word();
        word.name = str;
        System.out.printf("Kaandan gelen %s %s --- \n", str, word.name);
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



        System.out.printf("1 Word is valid %s %c %d\n", pangram.name, pangram.centerLetter, pangram.pangramHashCode);

        System.out.printf("2 Word is valid %s\n", pangram.name);
        /*
        for(Word word2 :beeHiveLetters.setOfWords)
            System.out.printf("%s Kopya\n", word2.name);
        */

        boolean b = false;
        for(Pangram p: Dictionary.pangramsDictionary)
            if(pangram.pangramHashCode == p.pangramHashCode && pangram.centerLetter == p.centerLetter) {
                beeHiveLetters = p;
                b = true;
                break;
            }
        if(!b)
            return false;

        /*
        if(!Dictionary.pangramsDictionary.contains(pangram))
            return false;
        */
        //Pangram p = pangram.isInThePangramWords(pangram);

        /*
        System.out.printf("3 Word is valid %s\n", pangram.name);
        if(p == null)
            return false;
        */

        //beeHiveLetters = pangram;
        maxPoint = beeHiveLetters.totalPoint;
        pointFromGame = 0;
        System.out.printf("4 Word is valid %s\n", pangram.name);

        for(Word word2 :beeHiveLetters.setOfWords)
            System.out.printf("%s Kopya\n", word2.name);


        return true;
    }

    public static Message checkIfAvailableFromPangram(String str) {
        Message message = beeHiveLetters.checkIfAvailableFromPangram(str);
        if(message.point > 0) {
            beeHiveLetters.foundWords.add(str);
            pointFromGame += message.point;
            numberOfWords++;
        }

        return message;
    }


}

