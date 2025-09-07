package com.bees.bees;

import java.util.Objects;

public class StringProcessor {
    String name;
    String letters;
    int point;
    boolean isPangram;
    long hashCode;

    /*
    public void clearAccents() {
        for(int i = 0; i < name.length(); i++) {
            int a = Dictionary.charactersWithAccent.indexOf(name.charAt(i));
            if(a != -1)
                name = name.substring(0,i) + Dictionary.equivalentOfAccents.charAt(a) + name.substring(i + 1);
        }
    }
     */

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
        //System.out.printf("\n%s girdi\n", name);
        boolean[] used = new boolean[32];
        for(int i = 0; i < name.length(); i++) {
            int a = Dictionary.turkishUpperCaseLetters.indexOf(name.charAt(i));
            used[a] = true;
        }
        int n = 0;
        letters = "";
        hashCode = 0;
        for(int i = 0, a = 1; i < 32; i++, a *= 2)
            if(used[i]) {
                hashCode += a;
                letters += Dictionary.turkishUpperCaseLetters.charAt(i);
                n++;
            }
        //System.out.printf("%d\n", hashCode);
        return n ;
    }

    /*
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (obj.getClass() != this.getClass())
            return false;

        final StringProcessor other = (StringProcessor) obj;
        if (!Objects.equals(this.str, other.str))
            return false;

        return true;
    }
    */
}
