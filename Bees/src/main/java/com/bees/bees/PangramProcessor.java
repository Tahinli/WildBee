package com.bees.bees;

import java.util.ArrayList;

public class PangramProcessor {
    public String name;
    public String letters;
    public char centerLetter;
    public int totalPoint;
    public int totalNumberOfWords;

    public ArrayList<Integer> generateSetOfWordsWithHash(String str, char ch) {
        int hc = 0;
        boolean b = false;
        int a = 0;
        totalPoint = totalNumberOfWords = 0;
        //System.out.printf("GSOWWH %s %c\n", str, ch);
        ArrayList<Integer> ret = new ArrayList<>();
        for(int i = 0; i < 128; i++) {
            hc = 0;
            b = false;
            for(int j = 0, x = 1; j < 7; j++, x *= 2)
                if ((i & x) > 0) {
                    hc += 1 << Dictionary.turkishUpperCaseLetters.indexOf(str.charAt(j));
                    /*
                    System.out.printf("---- %d %d %d %c %d %d\n", i, j, hc, str.charAt(j),
                            Dictionary.turkishUpperCaseLetters.indexOf(str.charAt(j)),
                            1 << Dictionary.turkishUpperCaseLetters.indexOf(str.charAt(j)));
                    */
                }
            if(Dictionary.wordsMap.containsKey((hc))) {

                //
                // System.out.printf("%s %c %d\n", str, ch, Dictionary.wordsMap.get(hc).size());
                /*
                for(int k = 0; k < Dictionary.wordsMap.get(hc).size(); k++)
                        System.out.printf("%s\n", Dictionary.dictionary.get(Dictionary.wordsMap.get(hc).get(k)).name);
                */
                for(int x = 0; x < Dictionary.wordsMap.get(hc).size(); x++) {
                    totalPoint += Dictionary.dictionary.get(Dictionary.wordsMap.get(hc).get(x)).point;
                    totalNumberOfWords++;

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

}
