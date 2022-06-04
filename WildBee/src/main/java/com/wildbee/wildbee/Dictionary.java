package com.wildbee.wildbee;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

class Dictionary {
    static int numberOfWords = 29;
    static String turkishUpperCaseLetters = "ABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVYZ";
    static String turkishLowerCaseLetters = "abcçdefgğhıijklmnoöprsştuüvyz";

    static HashSet<String> strings = new HashSet<>();
    static HashSet<Word> dictionary = new HashSet<>();
    static HashMap<Long, HashSet<Word>> wordsMap = new HashMap<>();
    static HashMap<Long, Long> pointMap = new HashMap<>();
    static HashSet<Pangram> pangramsDictionary = new HashSet<>();
    static HashMap<Long, Pangram> pangramsWithHashCode = new HashMap<>();

    static void read() throws FileNotFoundException {

        File file = new File("sozlukv2.txt");

        Scanner in = null;
        try {
            in = new Scanner(file);
        }catch(IOException e) {
            e.printStackTrace();
        }
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

    static void processDictionary() {
        for (Word word : dictionary)
            if (word.isPangram) {
                ControllerDialog.processedPangrams++;
                for (int i = 0; i < 7; i++) {
                    Pangram pangram = new Pangram();
                    pangram.name = word.name;
                    pangram.letters = word.letters;
                    pangram.point = word.point;
                    pangram.isPangram = word.isPangram;
                    pangram.hashCode = word.hashCode;
                    pangram.pangramHashCode = 8 * word.hashCode + i;
                    pangram.centerLetter = pangram.letters.charAt(i);
                    pangram.generateSetOfWordsWithHash();
                    if (pangram.totalPoint >= 100 && pangram.totalPoint <= 400 &&
                            pangram.totalNumberOfWords >= 20 && pangram.totalNumberOfWords <= 80 &&
                            !pangramsWithHashCode.containsKey(pangram.pangramHashCode)) {
                        pangramsDictionary.add(pangram);
                        pangramsWithHashCode.put(pangram.pangramHashCode, pangram);
                    }
                }
            }
    }
}