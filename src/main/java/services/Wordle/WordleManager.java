package services.Wordle;

import java.util.Scanner;

public class WordleManager {
    /**
     * Khai báo wordle.
     */
    Wordle wordle;
    public WordleManager() {
        wordle = new Wordle();
    }
    public void setWinningWord() {
        wordle.setWinningWord();
    }

    public boolean checkWin(String word) {
        return wordle.checkWin(word);
    }

    public String getWinningWord() {
        return wordle.getWinningWord();
    }

    public static void main(String[] args) {
        WordleManager wm = new WordleManager();
        wm.setWinningWord();
        System.out.println(wm.getWinningWord());
        Scanner sc = new Scanner(System.in);
        for(int i = 1; i <= 6; i++) {
            String guess = sc.nextLine();
            if(wm.wordle.checkWord(guess)) {
                wm.wordle.updateState(guess);
                if(wm.checkWin(guess)) {
                    System.out.println("You win");
                    break;
                }
                else {
                    System.out.println(wm.wordle.checkGameover());
                    for(char c : guess.toCharArray()) {
                        System.out.print(c + " " + wm.wordle.getStateLetter(c) + "\n");
                    }
                    if(wm.wordle.checkGameover()) {
                        System.out.println("You lose");
                        break;
                    }
                }
            }
            else {
                System.out.println("Wrong word");
                i--;
            }
        }
    }
}
