package com.wildbee.wildbee;

import java.util.*;

class Pangram extends Word {
    long pangramHashCode;
    char centerLetter;
    int totalPoint;
    int totalNumberOfWords;
    HashSet<Word> setOfWords = new HashSet<>();
    HashSet<String> foundWords = new HashSet<>();

    void generateSetOfWordsWithHash() {
        long hc;
        boolean b;

        totalPoint = totalNumberOfWords = 0;
        setOfWords = new HashSet<>();
        for (int i = 0; i < 128; i++) {
            hc = 0;
            b = false;
            for (int j = 0, x = 1; j < 7; j++, x *= 2)
                if ((i & x) > 0) {
                    if (letters.charAt(j) == centerLetter)
                        b = true;
                    hc += (long) Math.pow(2, Dictionary.turkishUpperCaseLetters.indexOf(letters.charAt(j)));
                }
            if (b && Dictionary.wordsMap.containsKey(hc)) {
                setOfWords.addAll(Dictionary.wordsMap.get(hc));
                totalPoint += Dictionary.pointMap.get(hc);
                totalNumberOfWords += Dictionary.wordsMap.get(hc).size();
            }
        }
    }

    Message checkIfAvailableFromPangram(String str) {
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
        }
        return message;
    }

    boolean checkLetterNotInBeehive(String str) {
        for (int i = 0; i < str.length(); i++)
            if (name.indexOf(str.charAt(i)) == -1)
                return true;
        return false;
    }

    boolean checkIfCenterLetterIncluded(String str) {
        if (str.indexOf(centerLetter) == -1)
            return false;
        return true;
    }

}


