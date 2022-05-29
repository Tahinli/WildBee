package com.bees.bees;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Dictionary {
    public static ControllerDialog game = new ControllerDialog();
    public static ArrayList<String> rawDictionaryStr = new ArrayList<String>();
    public static ArrayList<Word> rawDictionary = new ArrayList<Word>();
    public static ArrayList<String> dictionaryStr = new ArrayList<>();
    public static ArrayList<Word> dictionary = new ArrayList<Word>();
    public static HashMap<String, Integer> reverseDictionary = new HashMap<>();
    public static HashMap<Integer, ArrayList<Integer>> wordsMap = new HashMap<>();
    public static ArrayList<Integer> pangramWordsStr = new ArrayList<>();
    public static ArrayList<Character> pangramWordsChr = new ArrayList<>();
    public static ArrayList<Pangram> pangramWords = new ArrayList<Pangram>();
    public static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    public static HashMap<Long, ArrayList<Integer>> pangramWordsHashMap = new HashMap<>();
    public static String turkishUpperCaseLetters = "ABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVYZ";
    public static String turkishLowerCaseLetters = "abcçdefgğhıijklmnoöprsştuüvyz";


    //dosya okuma sıkıntı çıkarabilir.
    public static void read() throws FileNotFoundException {
        File file = new File("target/VPSOZLUK.txt");
        //File file = new File("/Users/admin/IdeaProjects/VirtualProgrammingProject/target/EnglishDictionary.txt");
        //File file = new File("/Users/admin/IdeaProjects/VirtualProgrammingProject/target/sozluk56K.txt");
        //File file = new File("/Users/admin/IdeaProjects/VirtualProgrammingProject/target/Sozluk76186.txt");
        //File file = new File("/Users/admin/IdeaProjects/VirtualProgrammingProject/target/Sozluk25861.txt");
        Scanner in = null; //YANLIŞ ADRES VERİLSE NE YAPILACAK
        try {
            in = new Scanner(file);
        }catch(IOException e) {
            e.printStackTrace();
        }
        int a = 0;
        while(in.hasNextLine()) {
            Word word = new Word();
            word.name = in.nextLine();
            rawDictionaryStr.add(word.name);
            rawDictionary.add(word);
          //  System.out.printf("%d %s\n", a++, word.name);
        }
        /*
        a = 0;
        for(int i = 0; i < rawDictionaryStr.size(); i++)
            System.out.printf("%d %s\n", a++, rawDictionaryStr.get(i));
        */
        in.close();
    }

    public static void processDictionary() {
        int i = 0;
        int j = 0;
        for (String str : rawDictionaryStr) {
            StringProcessor strPro = new StringProcessor();
            strPro.name = str;
            // System.out.printf("ĞĞĞĞĞĞĞĞĞ %s\n", strPro.name);
            if (strPro.checkWord()) {
                //System.out.printf("HHHHHHHHHH %s %s\n", strPro.name, strPro.letters);
                dictionaryStr.add(strPro.name);
                Word word = new Word();
                word.name = strPro.name;
                word.letters = strPro.letters;
                word.point = strPro.point;
                word.isPangram = strPro.isPangram;
                word.hashCode = strPro.hashCode;

                dictionary.add(word);
                reverseDictionary.put(strPro.name, j);

                ArrayList<Integer> al = wordsMap.get(strPro.hashCode);
                if (al == null)
                    al = new ArrayList<Integer>();
                al.add(j);
                wordsMap.put(strPro.hashCode, al);


                /*
                System.out.printf("### %d %s %d\n", j, str, wordsMap.get(strPro.hashCode).size());
                for(int k = 0; k < wordsMap.get(strPro.hashCode).size(); k++)
                    System.out.printf("--- %s\n", dictionaryStr.get(wordsMap.get(strPro.hashCode).get(k)));
                */

                j++;
                /*
                for(Word word2: al)
                   System.out.printf("%d %s created\n", word2.hashCode, word2.word);
                System.out.println();
                */
            }
            i++;
        }
  //      for(i = 0; i < dictionaryStr.size(); i++)
//            System.out.printf("%d %s\n", i, dictionaryStr.get(i));
    }

    public static void findPossiblePangramWords() {
        /*int a = 0;
        for (Word word : dictionary) {
            a++;
            if (a % 100 == 0)
                System.out.printf("%d %s\n", a, word.word);
            if (word.isPangram) {
            */


            int a = 0;
            int x = 0;
            int y = 0;
            int z = 0;
            for(int i = 0; i < dictionaryStr.size(); i++) {
                String str = dictionaryStr.get(i);
                StringProcessor strPro = new StringProcessor();
                strPro.name = str;
                strPro.checkWord();
                if (strPro.isTurkish() && strPro.countDistinctLetters() == 7)  {
                    x++;
                    for (int j = 0; j < 7; j++) {
                        PangramProcessor panPro = new PangramProcessor();
                        panPro.name = strPro.name;
                        panPro.centerLetter = strPro.letters.charAt(j);
                        panPro.letters = strPro.letters;

                        //System.out.printf("*** %d %s %c %s\n", a++, panPro.name, panPro.centerLetter, panPro.letters);


                        Pangram pangram = new Pangram();
                        pangram.name = str;
                        pangram.letters = panPro.letters;
                        pangram.centerLetter = str.charAt(j);
                        pangram.pangramHashCode =(long) 8 * strPro.hashCode + j;
                        pangram.setOfWords = panPro.generateSetOfWordsWithHash(panPro.name, panPro.centerLetter);
                        pangram.totalPoint = panPro.totalPoint;
                        pangram.totalNumberOfWords = panPro.totalNumberOfWords;


                        for(int k = 0; k < pangram.setOfWords.size(); k++)
                            pangram.wordsList.add(Dictionary.dictionaryStr.get(pangram.setOfWords.get(k)));


                        //System.out.printf("+++ %d %s %c %d %d %d\n", a, pangram.name, pangram.centerLetter, pangram.setOfWords.size(), pangram.totalPoint, pangram.totalNumberOfWords);

                        //pangram.generateSetOfWords();  //HERE
                        //pangram.generateSetOfWordsWithHash();


                        //       //      //System.out.printf("\n%s %c %d %d is a pangram:\n", pangram.word, pangram.centerLetter, pangram.totalPoint, pangram.totalNumberOfWords);
                        /*ArrayList<Word> set = Dictionary.map.get(pangram.hashCode);
                        if (set != null)
                            for (Word word2 : set) {
                                System.out.println(word2.word);
                            }
                         */

                        //System.out.printf("%s %c %d %d dictionary 85 find possible\n", pangram.word, pangram.centerLetter,
                        //       pangram.totalNumberOfWords, pangram.totalPoint);

                        if (pangram.totalNumberOfWords >= 20 && pangram.totalNumberOfWords <= 80 &&
                                pangram.totalPoint >= 100 && pangram.totalPoint <= 400) {
                            y++;
                            if( !Dictionary.pangramWordsHashMap.containsKey(pangram.pangramHashCode) ) {
                                pangramWords.add(pangram);
                                pangramWordsStr.add(i);
                                pangramWordsChr.add(panPro.centerLetter);
                                z++;
                                Dictionary.pangramWordsHashMap.put(pangram.pangramHashCode, pangram.setOfWords);
                            }

                            a++;
                            if(a % 1000 == 0)
                                System.out.printf("%d %s %c %d\n", a, pangram.name, pangram.centerLetter, pangram.setOfWords.size());

                            //System.out.printf("%d %s %c\n", a, pangram.name, pangram.centerLetter);
                        }

                        //Pangram png = pangramWords.get(pangramWords.size() - 1);
                        //System.out.printf("%s %c %d %d is added pangram list\n", png.word, png.centerLetter, png.totalNumberOfWords, png.totalPoint);
                        //System.out.printf("%s %c %d %d is added pangram list\n", pangram.word, pangram.centerLetter, pangram.totalNumberOfWords, pangram.totalPoint);

                    }
                }
            }
            System.out.printf("%d pangram var %d pangram kombinasyonu var. %d pangram kombinasyonu var.", x, y, z);
        //    }
        //}
        /*
        for(Pangram png: pangramWords)  {
            System.out.printf("%s %c %d %d subsets of the pangram are:\n", png.word, png.centerLetter, png.totalNumberOfWords, map.get(png.hashCode).size());
            ArrayList<Word>  arr = map.get(png.hashCode);
            for(Word word: arr)
                System.out.printf("%s %c %d\n", word.word, word.centerLetter,word.hashCode);
            System.out.println("That's it");
        }
        System.out.println("Hello World");

         */
    }
        //Ahmet was here
    public static Pangram gameLetters()
    {
        int n = pangramWordsHashMap.size(); //returns 0 pangramwords
        System.out.printf("#######%d pangram word\n", n);
        Random rand = new Random();
        int a = rand.nextInt(n);

        /*
        for(Word word: pangramWords.get(a).setOfWords)
            System.out.println(word.name);
        */


        return pangramWords.get(a);
    }

    /*
    public static void printAllPangramWords() {
        System.out.println("Pangram words");
        int i = 0;
        for (Pangram pangram: pangramWords) {
            System.out.printf("%d %s %c %d %d\n", i++, pangram.name, pangram.centerLetter, pangram.totalNumberOfWords, pangram.totalPoint);
        }
        System.out.println("HELLO WORLD 2!");
    }
    */
    /*
    public static void printAllSetOfWordsOfPangram() {
        int i = 0;
        for(Pangram pangram: pangramWords) {
            System.out.printf("\n%d %s %c %d %d %d set of words dictionary 135\n", i++, pangram.name, pangram.centerLetter, pangram.setOfWords.size(), pangram.totalPoint, pangram.totalNumberOfWords);
            int j = 0;
            for(Word word: pangram.setOfWords) {
                System.out.printf("%d %s %d\n", j++, word.name, word.point);
            }
        }
    }
    */

}
