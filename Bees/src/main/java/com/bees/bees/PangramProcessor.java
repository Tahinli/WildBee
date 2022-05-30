package com.bees.bees;

import java.util.ArrayList;
import java.util.Random;

public class PangramProcessor {
    public String name;
    public String letters;
    public char centerLetter;
    public int totalPoint;
    public int totalNumberOfWords;

    public ArrayList<Integer> generateSetOfWordsWithHash(String str, char ch) {
        long hc = 0;
        boolean b = false;
        int a = 0;
        totalPoint = totalNumberOfWords = 0;
        //System.out.printf("GSOWWH %s %c\n", str, ch);
        ArrayList<Integer> ret = new ArrayList<>();
        for (int i = 0; i < 128; i++) {
            hc = 0;
            b = false;
            boolean b2 = false;
            for (int j = 0, x = 1; j < 7; j++, x *= 2) {
                if ((i & x) > 0) {
                    if (str.charAt(j) == ch)
                        b2 = true;
                    hc += (long) Math.pow(2, Dictionary.turkishUpperCaseLetters.indexOf(str.charAt(j)));

                    if(ControllerDialog.dict.wordsMap.containsKey((hc)))
                        for (int k = 0; k < ControllerDialog.dict.wordsMap.get(hc).size(); k++) {
                            int indis = ControllerDialog.dict.wordsMap.get(hc).get(k);
                            String strStr = ControllerDialog.dict.dictionaryStr.get(indis);
                            Message message = checkIfAvailableFromPangram2(strStr);
                        }
                }
                    /*
                    System.out.printf("---- %d %d %d %c %d %d\n", i, j, hc, str.charAt(j),
                            Dictionary.turkishUpperCaseLetters.indexOf(str.charAt(j)),
                            1 << Dictionary.turkishUpperCaseLetters.indexOf(str.charAt(j)));
                    *

                     */
            }

            if (!b2)
                continue;

            if (Dictionary.wordsMap.containsKey((hc))) {

                //
                // System.out.printf("%s %c %d\n", str, ch, Dictionary.wordsMap.get(hc).size());
                /*
                for(int k = 0; k < Dictionary.wordsMap.get(hc).size(); k++)
                        System.out.printf("%s\n", Dictionary.dictionary.get(Dictionary.wordsMap.get(hc).get(k)).name);
                */
                for (int x = 0; x < Dictionary.wordsMap.get(hc).size(); x++) {
                    totalPoint += Dictionary.dictionary.get(Dictionary.wordsMap.get(hc).get(x)).point;
                    totalNumberOfWords++;

                    if()
                    ret.add(Dictionary.wordsMap.get(hc).get(x));
                }
                //ret.addAll(Dictionary.wordsMap.get(hc));

                //System.out.printf("!!!!!! %s %c %d %d\n", str, ch, hc, Dictionary.map.get(hc).size());
            }
            //System.out.printf("%d %d\n", i, hc);
            /*
            ArrayList<Integer> al;
            if(Dictionary.map.get(hc) == null)
                al = new ArrayList<>();
            else
                al = Dictionary.map.get(hc);
            for(int j = 0; j < al.size(); j++) {
                a = al.get(j);
                String s = Dictionary.dictionaryStr.get(a);
                if(s.indexOf(ch) != -1) {
                    ret.add(a);
                    totalNumberOfWords++;
                    totalPoint += s.charAt(i) - 3;
                    if(Dictionary.dictionary.get(a).isPangram)
                        totalPoint += 7;
                }

            }

             */
        }


        return ret;
    }


    public Message checkIfAvailableFromPangram2(String str) {
        //System.out.printf("%s\n", word.name);
        Message message = new Message();
        StringProcessor strPro = new StringProcessor();
        strPro.name = str;
        strPro.checkWord();


            /*
        boolean b = false;
        for(int i = 0; i < ControllerDialog.dict.dictionary.size(); i++) {
            String str2 = ControllerDialog.dict.dictionary.get(i).name;

            //int ind = ControllerDialog.dict.reverseDictionary.get( str2);
            if(str2.equals(strPro.name)) {
                b = true;
                break;
            }
    */

        //System.out.printf("%d %s %s %b\n", i, str2, ControllerDialog.dict.dictionary.get(ind).name, ControllerDialog.dict.reverseDictionary.containsKey( str2 ));

    /*
        System.out.printf("%s %d gelen ahmetten\n", str, str.length());

        System.out.printf("%b %d\n", ControllerDialog.dict.reverseDictionary.containsKey( strPro.name ), ControllerDialog.dict.reverseDictionary.get( strPro.name ));

        String s1 = "AHMETUFUK";
        String s2 = "AHMETUFUK";
        ControllerDialog.dict.reverseDictionary.put(s1, 1000);
        System.out.printf("Var mı %b\n", ControllerDialog.dict.reverseDictionary.containsKey(s2));

        Integer indisIndis = ControllerDialog.dict.reverseDictionary.get(str);
        System.out.printf("%d Merhaba dünya\n", indisIndis);
*/
        if (strPro.name.length() < 4) {
            message.point = -3;
            message.word = str + " does not include at least four letters.";
        } else if (!ControllerDialog.dict.reverseDictionary.containsKey(strPro.name)) { ////Düzelte ####################
            System.out.printf("%s sözlükte yok ahmetufuk\n", str);
            message.point = -1;
            message.word = str + " is not in the dictionary.";
        } else if (checkLetterNotInBeehive(strPro.name)) {
            message.point = -2;
            message.word = str + " includes letters not in the beehive.";
        } else if (!checkIfCenterLetterIncluded(strPro.name)) {
            message.point = -4;
            message.word = str + " the word does not include center letter.";
        } /*else
            if (checkIfAlreadyFound(strPro.name)) { /// burası da hashMap
            message.point = -5;
            message.word = "The user has already found " + str;

        }*/
        else {
            message.point = strPro.name.length() - 3;
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

            this.totalPoint += message.point;
            this.totalNumberOfWords++;
        }
        return message;
    }


    public boolean checkLetterNotInBeehive(String str) {
        for (int i = 0; i < str.length(); i++)
            if (this.name.indexOf(str.charAt(i)) == -1)
                return true;
        return false;
    }

    public boolean checkIfCenterLetterIncluded(String str) {
        if (str.indexOf(this.centerLetter) == -1)
            return false;
        return true;
    }

}
    /*
    public boolean checkIfAlreadyFound(String str) {
        if (foundWordsHash.containsKey(str))
            return true;
        */

    /*
    public Pangram isInThePangramWords(String str, char ch) {
        System.out.printf("Girdi %s %c\n", str, ch);
        for (Pangram p : Dictionary.pangramWords) {
            if (p.hashCode == this.hashCode && p.centerLetter == this.centerLetter) {
                return p;
            }
        }
        return null;
    }
*/
