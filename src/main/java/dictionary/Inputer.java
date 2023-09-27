package dictionary;

import java.util.Scanner;

public class Inputer {
    private static Scanner input = new Scanner(System.in);

    /**
     * read int from user.
     * 
     * @return int from user
     */
    public static int InputNextInt() {
        return input.nextInt();
    }

    /**
     * read line from user.
     * 
     * @return line from user.
     */
    public static String InputNextLine() {
        return input.nextLine();
    }

    /** asked user to press enter to continue. */
    public static void pressEnterToContinue() {
        System.out.print("Nhan Enter de tiep tuc bro...");
        try {
            input.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * create a String contain many space charactors.
     * 
     * @param n number of space charactors
     * @return a String contain many space charactors
     */
    public static String compressManySpace(int n) {
        String result = "";
        for (int i = 0; i < n; i++) {
            result += " ";
        }
        return result;
    }

    /**
     * create a String contain word + spaces
     * 
     * @param w word
     * @param n number of space charactors
     * @return a String contain word + spaces
     */
    public static String compressWordplusSpace(String w, int n) {
        String result = w;
        n -= w.length();
        result += compressManySpace(n);
        return result;
    }
}
