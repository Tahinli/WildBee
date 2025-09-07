package com.wildbee.wildbee;

/**
 * It is a word check and score calculation class.
 * @since 2022-06
 */
public class Word {

    String name;
    String letters;
    int point;
    boolean isPangram;
    long hashCode;

    /**
     * @return The word is checked according to the entered conditions and the result is returned as true or false.
     */
    boolean checkWord() {
        if(!isTurkish())
            return false;
        if(name.length() < 4 || countDistinctLetters() > 7)
            return false;
        calculatePoint();
        return true;
    }

    /**
     * @return If the word is Turkish, the result is returned.
     */
    boolean isTurkish() {
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

    /**
     * The score is the calculated part, in case of a pangram, the score is added.
     */
    void calculatePoint() {
        point = name.length() - 3;
        if(countDistinctLetters() == 7) {
            isPangram = true;
            point += 7;
        }
    }

    /**
     * @return It is the part that counts the different letters and returns the result.
     */
    int countDistinctLetters() {
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


