package com.wildbee.wildbee;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Tester {
        public static int numberOfWords = 32;
        public static ArrayList<Character> TurkishUpperCaseLetters = new ArrayList<>(List.of('A','Â', 'B', 'C', 'Ç', 'D', 'E', 'F', 'G', 'Ğ', 'H', 'I', 'İ','Î', 'J', 'K', 'L', 'M', 'N', 'O', 'Ö', 'P', 'R', 'S', 'Ş', 'T', 'U','Û', 'Ü', 'V', 'Y', 'Z'));
        public static ArrayList<Character> TurkishLowerCaseLetters = new ArrayList<>(List.of('a','â', 'b', 'c', 'ç', 'd', 'e', 'f', 'g', 'ğ', 'h', 'ı', 'i','î', 'j', 'k', 'l', 'm', 'n', 'o', 'ö', 'p', 'r', 's', 'ş', 't', 'u','û', 'ü', 'v', 'y', 'z'));
        public static HashSet<Long> createHashSet = new HashSet<>();

        public static void main(String[] args) throws FileNotFoundException {

            System.out.print("\nThe program will start in a few seconds and all the words will be printed on the terminal (you can review the words on the terminal)!\n");

            File file = new File("sozlukv2.txt"); //Update the dictionary location for the program to work correctly!
            Scanner in = new Scanner(file);
            ArrayList<String> al = new ArrayList<>();

            while (in.hasNext()) {
                String str = in.next();

                if(!isTurkish(str))
                    continue;
                str = makeUpperCase(str);
                boolean b = true;
                for (String s : al)
                    if (s.equals(str)) {
                        b = false;
                        break;
                    }
                if(b)
                    al.add(str);
            }

            for(int i = 0; i < al.size(); i++) {
                System.out.printf("%d %s %d\n", i, al.get(i), i);
            }
            System.out.print("\n\nCalculating the number of games and the number of pangrams, please wait!\n\n");
            int n = 0, m = 0;
            for(int i = 0; i < al.size(); i++) {
                String letters = isPangram(al.get(i));
                if (letters.length() == 7) {
                    m++;
                    for (int k = 0; k < 7; k++) {
                        int c = 0;
                        int point = 0;
                        for (String s : al) {
                            int p = isAvailable(al.get(i), letters.charAt(k), s);
                            if (p > 0) {
                                point += p;
                                c++;
                            }
                        }
                        if (point >= 100 && point <= 400 && c >= 20 && c <= 80) {
                            long hc = hashCodeOfPangram(letters, k);
                            if(!createHashSet.contains(hc)) {
                                createHashSet.add(hc);
                                n++;
                            }
                        }
                    }
                }
            }
            System.out.print("Results:\n");
            System.out.printf("As a result of the program, %d games and %d pangram words were found!\n", n, m);
        }

        public static String isPangram(String str) {
            boolean[] used = new boolean[numberOfWords];
            for(int i = 0; i < str.length(); i++) {
                int a = TurkishUpperCaseLetters.indexOf(str.charAt(i));
                if(!used[a])
                    used[a] = true;
            }
            StringBuilder letters = new StringBuilder();
            long x = 1;
            for(int i = 0; i < numberOfWords; i++, x *= 2)
                if(used[i]) {
                    letters.append(TurkishUpperCaseLetters.get(i));
                }
            return letters.toString();
        }

        public static int isAvailable(String pan, char cl, String str)  {
            if(str.indexOf(cl) == -1 || str.length() < 4)
                return -1;
            for(int i = 0; i < str.length(); i++)
                if(pan.indexOf(str.charAt(i)) == -1)
                    return -1;
            int p = str.length() - 3;
            if(isPangram(str).length() == 7)
                p += 7;
            return p;
        }

        public static String makeUpperCase(String str)  {
            for(int i = 0; i < str.length(); i++)  {
                int a = TurkishLowerCaseLetters.indexOf(str.charAt(i));
                if(a != -1)
                    str = str.substring(0,i) + TurkishUpperCaseLetters.get(a) + str.substring(i+1);
            }
            return str;
        }
        public static boolean isTurkish(String str)  {
            for(int i = 0; i < str.length(); i++)
                if(!TurkishLowerCaseLetters.contains(str.charAt(i)) &&
                        !TurkishUpperCaseLetters.contains(str.charAt(i)))
                    return false;
            return true;
        }
        public static long hashCodeOfPangram(String letters, int a)  {
            boolean[] used = new boolean[32];
            for(int i = 0; i < 7; i++)
                used[TurkishUpperCaseLetters.indexOf(letters.charAt(i))] = true;
            long hc = 0;
            long x = 1;
            for(int i = 0; i < 32; i++, x *= 2)
                if(used[i])
                    hc += x;
            return hc * 8 + a;
        }
    }
