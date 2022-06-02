package com.bees.bees;

import java.util.Objects;

public class Word {

    public String name;
    public String letters;
    public int point;
    public boolean isPangram;
    public long hashCode;

    public boolean checkWord() {
        if(!isTurkish())
            return false;
        if(name.length() < 4 || countDistinctLetters() > 7)
            return false;
        calculatePoint();
        return true;
    }

    public boolean isTurkish() {
        for(int i = 0; i < name.length(); i++)
            if( Dictionary.turkishUpperCaseLetters.indexOf( name.charAt(i) ) == -1 &&
                    Dictionary.turkishLowerCaseLetters.indexOf( name.charAt(i) ) == -1)
                        return false;
        for(int i = 0; i < name.length(); i++)  {
            int a = Dictionary.turkishLowerCaseLetters.indexOf( name.charAt(i) );
            if ( a != -1)
                name = name.substring(0,i) + Dictionary.turkishUpperCaseLetters.charAt(a) + name.substring(i + 1);
        }
        return true;
    }

    public void calculatePoint() {
        point = name.length() - 3;
        if(countDistinctLetters() == 7) {
            isPangram = true;
            point += 7;
        }
    }

    public int countDistinctLetters() {
        int n = 0;
        boolean[] used = new boolean[Dictionary.numberOfWords];
        for(int i = 0; i < name.length(); i++) {
            int a = Dictionary.turkishUpperCaseLetters.indexOf(name.charAt(i));
            if(!used[a])
                used[a] = true;
        }
        letters = "";
        hashCode = 0;
        long x = 1;
        for(int i = 0; i < Dictionary.numberOfWords; i++, x *= 2)
            if(used[i]) {
                letters += Dictionary.turkishUpperCaseLetters.charAt(i);
                hashCode += x;
                n++;
            }
        return n;
    }

}


