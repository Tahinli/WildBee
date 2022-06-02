package com.bees.bees;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Dictionary {
    public static int  numberOfWords = 32;

    //public static String turkishUpperCaseLetters = "AÂBCÇDEFGĞHIİÎJKLMNOÖPRSŞTUÛÜVYZ";
    //public static String turkishLowerCaseLetters = "aâbcçdefgğhıiîjklmnoöprsştuûüvyz";

    public static String turkishUpperCaseLetters = "ABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVYZ";
    public static String turkishLowerCaseLetters = "abcçdefgğhıijklmnoöprsştuüvyz";

    public static HashSet<String> strings = new HashSet<>();
    public static HashSet<Word> dictionary = new HashSet<>();
    public static HashMap<Long, HashSet<Word>> wordsMap = new HashMap<>();
    public static HashMap<Long, HashSet<Word>> pangramMap = new HashMap<>();
    public static HashMap<Long, Long> pointMap = new HashMap<>();
    public static HashSet<Pangram> pangramsDictionary = new HashSet<>();
    public static HashMap<Long, Pangram> pangramsWithHashCode = new HashMap<>();

    public static void read() throws FileNotFoundException {

        /*
        *
        *
        *Burayı Kısalt
        *
        *
        *
        *
        */


        File file = new File("target/sozlukv2.txt");
        //File file = new File("target/VPSOZLUK.txt");
        //File file = new File("target/EnglishDictionary.txt");
        //File file = new File("target/sozluk56K.txt");
        //File file = new File("target/Sozluk76186.txt");
        //File file = new File("target/Sozluk25861.txt");
        Scanner in = null;
        try {
            in = new Scanner(file);
        }catch(IOException e) {
            e.printStackTrace();
        }
        int a = 0;
        while(in.hasNextLine()) {
            Word word = new Word();
            word.name = in.nextLine();
            if(word.checkWord() && !dictionary.contains(word)) {
                dictionary.add(word);
                strings.add(word.name);
                HashSet<Word> hs = new HashSet<>();
                long po = 0;

                if (wordsMap.containsKey(word.hashCode)) {
                    hs = wordsMap.get(word.hashCode);
                    po = pointMap.get(word.hashCode);
                }

                hs.add(word);
                wordsMap.put(word.hashCode, hs);
                pointMap.put(word.hashCode, po + word.point);
            }
        }
        in.close();
    }

    public static void processDictionary() {
        int a = 0;
        for (Word word : dictionary)
            if (word.isPangram) {
                ControllerDialog.processedPangrams++;
                a++;
                for (int i = 0; i < 7; i++) {
                    //System.out.printf("%s %s %b %s Word Dictionary 63\n", word.name, word.letters, word.isPangram, word.letters);
                    Pangram pangram = new Pangram();
                    pangram.name = word.name;
                    pangram.letters = word.letters;
                    pangram.point = word.point;
                    pangram.isPangram = word.isPangram;
                    pangram.hashCode = word.hashCode;
                    pangram.pangramHashCode = 8 * word.hashCode + i;
                    pangram.centerLetter = pangram.letters.charAt(i);
                    pangram.generateSetOfWordsWithHash();
                    //System.out.printf("%d %s %c %d / %d %d %d Dictionary 74\n", a++, pangram.name, pangram.centerLetter, pangram.pangramHashCode,
                    //      pangram.setOfWords.size(), pangram.totalPoint, pangram.totalNumberOfWords);
                    if (pangram.totalPoint >= 100 && pangram.totalPoint <= 400 &&
                            pangram.totalNumberOfWords >= 20 && pangram.totalNumberOfWords <= 80 &&
                            !pangramsWithHashCode.containsKey(pangram.pangramHashCode)) {
                        pangramsDictionary.add(pangram);
                        pangramsWithHashCode.put(pangram.pangramHashCode, pangram);
                     }
                }
            }

            System.out.printf("There are %d Pangram",a);
            /*
            System.out.printf("\n#### %s %d\n", word.name, word.hashCode);
            for (Word word2 : wordsMap.get(word.hashCode))
                System.out.printf("%s %d\n", word2.name, word2.hashCode);

             */
    }

}
                /*
                if(wordsMap.get(word) == null) {
                    hw.add(word);
                    wordsMap.put(word.hashCode, hw);
                }
                else {
                    hw = wordsMap.get(word.hashCode);
                    hw.add(word);
                    wordsMap.replace(word.hashCode, hw);
                }
            }
        }
    }
    public static void processDictionary() {
        int i = 0;
        for(Word word: dictionary) {
            System.out.printf("\n#### %s %d\n", word.name, word.hashCode);
            for(Word word2: wordsMap.get(word.hashCode))
                System.out.printf("%s %d\n", word2.name, word2.hashCode);
        }
        /*
        if(word.countDistinctLetters() == 7) {
            Pangram pangram = (Pangram) word;
            for(int i = 0; i < 7; i++)  {
                pangram.centerLetter = pangram.letters.charAt(i);
                pangram.hashCode = 8 * word.hashCode + i;
                pangram.
                if(pangramList.c)
                    pangramsDictionary.add(pangram);
            }
        }
        */


