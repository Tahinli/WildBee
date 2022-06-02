package com.bees.bees;

import java.util.*;



public class Pangram extends Word {
    public long pangramHashCode;
    public char centerLetter;
    public int totalPoint;
    public int totalNumberOfWords;
    public HashSet<Word> setOfWords = new HashSet<>();
    public HashSet<String> foundWords = new HashSet<>();
    public ArrayList<String> wordsList = new ArrayList<>();
    //public ArrayList<Integer> foundWords = new ArrayList<>();
    public HashMap<String, Integer> foundWordsHash = new HashMap<>();

    public void generateSetOfWordsWithHash() {
        long hc = 0;
        boolean b = false;

        totalPoint = totalNumberOfWords = 0;
        setOfWords = new HashSet<>();
        //System.out.printf("\nBaşla %s %s %c\n", name, letters, centerLetter);
        for (int i = 0; i < 128; i++) {
            hc = 0;
            b = false;
            for (int j = 0, x = 1; j < 7; j++, x *= 2)
                if ((i & x) > 0) {
                    if (letters.charAt(j) == centerLetter)
                        b = true;
                    hc += (long) Math.pow(2, Dictionary.turkishUpperCaseLetters.indexOf(letters.charAt(j))  );
                }
            //System.out.printf("%s\n", Long.toBinaryString(hc));

            if (b && Dictionary.wordsMap.containsKey(hc))  {
                setOfWords.addAll(Dictionary.wordsMap.get(hc));
                totalPoint += Dictionary.pointMap.get(hc);
                totalNumberOfWords += Dictionary.wordsMap.get(hc).size();
            }
        }
    }
            /*
                for (Word word : Dictionary.wordsMap.get(hc)) {
                    if (!setOfWords.contains(word))  {
                        Message message = checkIfAvailableFromPangram(word.name);
                        //System.out.printf("%s %s %c %s %s %d %s is available from pangram 38\n", name, letters, centerLetter, word.name, word.letters, message.point, message.word);
                        if(message.point > 0) {
                            setOfWords.add(word);
                            totalPoint += word.point;
                            totalNumberOfWords++;
                        }
                        else
                            System.out.printf("%s %c %s %d girmedi 1 \n", name, centerLetter, word.name, message.point);
                    }
                    else
                        System.out.printf("%s %c %s Girmedi 2\n", name, centerLetter, word.name);
                }

             */


    public Message checkIfAvailableFromPangram(String str) {
        //System.out.printf("%s\n", word.name);
        Message message = new Message();
        Word word = new Word();
        word.name = str;
        word.checkWord();

        if (name.length() < 4) {
            message.point = -3;
            if(ControllerDialog.language == 0)
                message.word = str + " en az dört harf içermeli.";
            else
                message.word = str + " does not include at least four letters.";
        } else if(!Dictionary.strings.contains(str)) {
            message.point = -1;
            if(ControllerDialog.language == 0)
                message.word = str + " sözlükte yok.";
            else
                message.word = str + " is not in the dictionary.";
        } else if(checkLetterNotInBeehive(str) ) {
            message.point = -2;
            if(ControllerDialog.language == 0)
                message.word = str + " sözcüğü altıgenlerde olmayan karakter içeriyor";
            else
                message.word = str + " includes character not in the beehive.";
        } else if(!checkIfCenterLetterIncluded(str)) {
            message.point = -4;
            if(ControllerDialog.language == 0)
                message.word = str + " merkezdeki sarı harfi içermiyor.";
            else
                message.word = str + " the word does not include center letter.";
        }else if(foundWords.contains(str)) {
            message.point = -5;
            if(ControllerDialog.language == 0)
                message.word = str + " sözcüğünü daha önce bulmuştunuz.";
            else
            message.word = "The user has already found " + str;
        } else {
            message.point = str.length() - 3;
            if (word.countDistinctLetters() == 7) {
                message.word = "Pangram!";
                message.point += 7;
            } else {
                Random rand = new Random();
                int a = rand.nextInt(3);
                String[] messages = {"Good!", "Nice!", "Awesome!", "İyi!", "Harika!", "Muhteşem!"};
                if(ControllerDialog.language == 0)
                    a += 3;
                message.word = messages[a];
            }

            int a = (int) Math.ceil(10.0 * ControllerDialog.pointFromGame / ControllerDialog.maxPoint);
            if(ControllerDialog.language == 0)
                a += 11;
            message.game = ControllerDialog.messages[a];

            if (ControllerDialog.maxPoint == ControllerDialog.pointFromGame + message.point) {
                message.isFinished = true;
                message.game = ControllerDialog.messages[9];
                if(ControllerDialog.language == 0)
                    message.finish ="Tebrikler! KRALİÇE ARI!";
                else
                message.finish = "Congratulations! QUEEN BEE!";
            }
            System.out.printf("%s %c %s %d %d pangram 121\n", name, centerLetter, str,ControllerDialog.maxPoint, Dictionary.pangramsDictionary.size());
            System.out.printf("%s %c %d pangram 122\n", ControllerDialog.beeHiveLetters.name, ControllerDialog.beeHiveLetters.centerLetter, ControllerDialog.beeHiveLetters.setOfWords.size());
        }
        return message;
    }

    public boolean checkLetterNotInBeehive(String str) {
        for (int i = 0; i < str.length(); i++)
            if (name.indexOf(str.charAt(i)) == -1)
                return true;
        return false;
    }

    public boolean checkIfCenterLetterIncluded(String str) {
        if (str.indexOf(centerLetter) == -1)
            return false;
        return true;
    }

    public Pangram isInThePangramWords(String str) {
        Pangram pangram  = new Pangram();
        pangram.name = str;
        pangram.centerLetter = str.charAt(0);
        pangram.checkWord();
        pangram.pangramHashCode = 8 * pangram.hashCode + pangram.letters.indexOf(pangram.centerLetter);
        for (Pangram p : Dictionary.pangramsDictionary)
            if(p.centerLetter == pangram.centerLetter && p.pangramHashCode == pangram.pangramHashCode)
                return p;
        return null;
    }





}


