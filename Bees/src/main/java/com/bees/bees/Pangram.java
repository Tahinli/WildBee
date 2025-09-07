package com.bees.bees;

import java.util.*;

public class Pangram extends Word {
    public int indis;
    public long pangramHashCode;
    public char centerLetter;
    public int totalPoint;
    public int totalNumberOfWords;
    public ArrayList<Integer> setOfWords = new ArrayList<>();
    public ArrayList<String> wordsList = new ArrayList<>();
    public ArrayList<Integer> foundWords = new ArrayList<>();
    public HashMap<String, Integer> foundWordsHash = new HashMap<>();


    public Message checkIfAvailableFromPangram(String str) {
        //System.out.printf("%s\n", word.name);
        System.out.println("CAB"+str);
        Message message = new Message();

        if(str.length() < 4) {
            message.point = -3;
            message.word = str + " does not include at least four letters.";
        }
        else if( !Dictionary.reverseDictionary.containsKey(str) ) { ////Düzelte ####################
            message.point = -1;
            message.word  = str + " is not in the dictionary.";
        }
        else if(checkLetterNotInBeehive(str)) {
            message.point = -2;
            message.word = str + " includes letters not in the beehive.";
        }
        else if(!checkIfCenterLetterIncluded( str)) {
            message.point = -4;
            message.word = str + " the word does not include center letter.";
        }
        else if(checkIfAlreadyFound(str)) { /// burası da hashMap
            message.point = -5;
            message.word = "The user has already found " + str + " and is not allowed to score it twice.";
        }
        else {
            message.point = str.length() - 3;
            StringProcessor strPro = new StringProcessor();
            strPro.name = str;
            if (strPro.countDistinctLetters() == 7) {
                message.word = "Pangram!";
                message.point += 7;
            } else {
                Random rand = new Random();
                int a = rand.nextInt(3);
                String[] messages = {"Good!", "Nice!", "Awesome!"};
                message.game = messages[a];
            }
            //Dictionary.game.numberOfWords++;
            //Dictionary.game.pointFromGame += message.point;


            int ind = (int) Math.ceil(1.0 * Dictionary.game.pointFromWord / Dictionary.game.maxPoint);
            message.finish = Dictionary.game.messages[ind];
            if(Dictionary.game.maxPoint == Dictionary.game.pointFromGame) {
                message.isFinished = true;
                message.finish = "Congratulations. You have found all words. You are the QUEEN BEE!";
            }
        }

        /*
        System.out.printf("\nLine 265 Pangram\n");
        System.out.printf("#1 %s %c %s\n", this.word, this.centerLetter, word.word);
        //System.out.printf("#2 %s %s\n", this.message, this);
        System.out.printf("#2 %d %d\n", this.totalPoint, this.totalNumberOfWords);
        */

        return message;
    }

    public boolean checkLetterNotInBeehive(String str) {
        for(int i = 0; i < str.length(); i++)
            if(this.name.indexOf( str.charAt(i) ) == -1 )
                return true;
        return false;
    }

    public boolean checkIfCenterLetterIncluded(String str) {
        if(str.indexOf( this.centerLetter )  == -1)
            return false;
        return true;
    }

    public boolean checkIfAlreadyFound(String str) {
        if(foundWordsHash.containsKey(str))
            return true;
        return false;
    }

    public Pangram isInThePangramWords(String str, char ch) {
        for(Pangram p : Dictionary.pangramWords) {
            if (p.hashCode == this.hashCode && p.centerLetter == this.centerLetter)
                return p;
        }
        return null;
    }


    /*
    public void generateSetOfWords() {
        for(Word word: Dictionary.dictionary) {
            ControllerDialog result = this.checkIfAvailableFromPangram(new ControllerDialog(), word);
            if(result.pointFromWord > 0) {
                //çirkin kod tek satıra indirilmeli
                setOfWords.add(word);
                totalNumberOfWords++;
                totalPoint += word.point;
                //System.out.printf("%s %c\n", this.word, this.centerLetter);
                //System.out.printf("%s %d %s\n", ret.word,  ret.point, ret.message);
                //System.out.printf("%s %d %s\n\n", setOfWords.get(setOfWords.size() - 1).word, setOfWords.get(setOfWords.size() - 1).point, setOfWords.get(setOfWords.size() - 1).message);
            }
        }
    }
    */


    /*
    public void generateSetOfWordsWithHash() {
        boolean[] used = new boolean[7];
        dfs( 0, used);
        /*
        ArrayList<Word> set = Dictionary.map.get(hashCode);
        if(set != null)
            for(Word word: set) {
                setOfWords.add(word);
                totalNumberOfWords++;
                totalPoint += word.point;
            }
        return;
    }
    */

    /*
    public void dfs( int index, boolean[] used) {
        if(index == 7) {
            int hc = getHashCode(used);

            /*
            System.out.printf("\n########### %s %c\n", name, centerLetter);
            System.out.printf("%s %d\n", getWordsFromHashCode(hc), hc);
            for(int i = 0; i < 7; i++)
                if(used[i])
                    System.out.printf("%c", letters[i]);
            System.out.printf(" %d\n", hc);
            */

            /*
            System.out.printf("Pangram Line 45\n");
            System.out.printf("%s ", word);
            for(int i = 0; i < 7; i++)
                System.out.printf("%d", used[i] ? 1: 0);
            System.out.printf(" ");
            for(int i = 0; i < 7; i++)
                if(used[i])
                    System.out.printf("%c", letters[i]);
            System.out.printf("\n%s %d %b\n", this.word, hc, Dictionary.map.containsKey(hc));
            if(Dictionary.map.containsKey(hc))
                System.out.printf("%s\n", Dictionary.map.get(hc).get(0).word);
            */

            /*
            if(Dictionary.map.containsKey(hc)) {
                ArrayList<Word> sow = Dictionary.map.get(hc);
                //System.out.printf("\n%d %d %d pangram 73###\n", hc, Dictionary.map.get(hc).size(), sow.size());
                for (Word word : Dictionary.map.get(hc)) {
                    //System.out.printf("### %s %c %s pangram 75\n", this.name, this.centerLetter, word.name);
                    ControllerDialog cd = checkIfAvailableFromPangram(new ControllerDialog(), word);

                    //Word ret = word.copyOf();
                    //ControllerDialog result = new ControllerDialog().checkIfAvailableFromPangram(this, ret);


                    /*
                    System.out.printf("%s %s %d %s %d dfs pangram\n", this.word, ret.word, ret.point, ret.message, setOfWords.size());
                    System.out.printf("%s %s\n", String.valueOf(this.letters), String.valueOf(ret.letters));
                    System.out.printf("%s\n%s\n\n", Integer.toBinaryString(this.hashCode), Integer.toBinaryString(ret.hashCode));

                    */

                    /*
                    System.out.printf("\n %s %c %s %s Pangram 77\n", this.word, this.centerLetter, word.word, result.wordMessage);
                    if(this.numberOfWords > 0) {
                        System.out.printf("#1 %s %s\n", result.beeHiveLetters.word, word.word);
                        System.out.printf("#2 %s %s\n", result.pointFromWord, result.wordMessage);
                        System.out.printf("#3 %d %d\n", result.beeHiveLetters.totalPoint, result.beeHiveLetters.totalNumberOfWords);
                        System.out.printf("#4 %d %d\n\n", result.beeHiveLetters.setOfWords.size(), result.beeHiveLetters.setOfWords.size());
                    }
                    */
                    /*
                    for(Word word2: result.beeHiveLetters.setOfWords)  {
                        System.out.println(word2);
                    }
                    */
    /*
                    if(cd.pointFromWord > 0 && !setOfWords.contains(word)) {

                        setOfWords.add(word);
                        totalNumberOfWords++;
                        totalPoint += word.point;

                        //System.out.printf("%s %c %s %d %d %d is available pangram 101\n", this.name, this.centerLetter, word.name, this.hashCode, word.hashCode, hc);

                        /*
                        System.out.printf("%s\n", Integer.toBinaryString(this.hashCode));
                        System.out.printf("%s\n", Integer.toBinaryString(word.hashCode));
                        System.out.printf("\n");


                    }
                }
            }
            */
            //else {

                /*
                ArrayList<Word> sow = new ArrayList<Word>();
                Pangram png = this.copyOfPangram();
                Word w = word.copyOf();
                ControllerDialog result = new ControllerDialog().checkIfAvailableFromPangram(new ControllerDialog(), png, w);
                */
            //}
/*
            return;
        }
        used[index] = false;
        dfs(index + 1, used);
        used[index] = true;
        dfs(index + 1, used);
    }
    */


    /*
    public int count(boolean[] used) {
        int n = 0;
        for(int i = 0; i < 7; i++)
            n += used[i] ? 1: 0;
        return n;
    }
    */

    /*
    public int getHashCode(boolean[] used) {
        int hc = 0;
        /*
        for(int i = 0; i < 7; i++)
            System.out.printf("%d", used[i] ? 1: 0);
        System.out.printf("\n");
        */

        /*
        System.out.printf("start\n");
        System.out.printf("%s\n", this.word);
        for(int i = 0; i< 7; i++)
            System.out.printf("%d", used[i] ? 1: 0);
        System.out.printf(" %s pangram getHashCode 127\n", word);
        */

        /*
        System.out.printf("GetHashCode Pangram 152\n");
        System.out.printf("%s\n", this.word);
        for(int i = 0; i< 7; i++)
            System.out.printf("%c", letters[i]);
        System.out.printf("\n");
        for(int i = 0; i < 7; i++)
            System.out.printf("%d", used[i] ? 1: 0);
        System.out.printf("\n");


        for(int i = 0; i < 7; i++)
            if(used[i]) {
                int a = (int) Math.pow(2, Dictionary.turkishUpperCaseLetters.indexOf(letters[i]));
                hc += a;
               // System.out.printf("%c %d %d %d\n", letters[i], Dictionary.turkishUpperCaseLetters.indexOf(letters[i]), a, hc);
                //System.out.printf("%c", letters[i]);
                //System.out.printf("%s %c %s %c %d %d getHashCode used\n", word, centerLetter, String.valueOf(letters),
                //        letters[i], Dictionary.turkishUpperCaseLetters.indexOf(letters[i]), a);
            }
        //System.out.printf("\n");
        //System.out.printf(" %d\nEnd\n\n", hc);
        /*
        if(Dictionary.map.containsKey(hc))
            System.out.printf("%d %b %d\n", hc, Dictionary.map.containsKey(hc), Dictionary.map.get(hc).size());
        System.out.printf("\n");

        return hc;
    }
    */

    /*
    public static String getWordsFromHashCode(int hc) {
        String str = "";
        char[] letters = new char[29];
        int n = 0;
        for(int i = 28, a = (int) Math.pow(2,28); i >= 0; i--, a /= 2)
            if(hc >= a) {
                char ch = Dictionary.turkishUpperCaseLetters.charAt(i);
                letters[n++] = ch;
                hc -= a;
                //System.out.printf("%c %d pangram 171\n ", ch, hc);
            }

        return String.valueOf(letters);
    }
    */

    /*
    public Pangram copyOfPangram() {
        Pangram pangram = new Pangram();
        pangram.centerLetter = centerLetter;
        pangram.totalPoint = totalPoint;
        pangram.totalNumberOfWords = totalNumberOfWords;
        pangram.setOfWords = setOfWords;
        pangram.foundWords = foundWords;
        pangram.name = name;
        pangram.letters = letters;
        pangram.point = point;
        pangram.isPangram = isPangram;
        pangram.hashCode = hashCode;
        return pangram;
    }

    */

    /*
    *
    * Mesajları düzenle
    *
     */



}
