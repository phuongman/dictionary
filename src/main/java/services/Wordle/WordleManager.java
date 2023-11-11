package services.Wordle;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class WordleManager {
    /**
     * Khai báo wordle.
     */
    Wordle wordle;
    String guessWord;
    Timer timer;
    int time = 300;
    private int timeElapsed;
    boolean isPlaying;
    ArrayList<Integer> timeElapsedList = new ArrayList<>();
    public WordleManager() {
        timer = new Timer();
        wordle = new Wordle();
        timeElapsed = 0;
        isPlaying = false;
    }

    public void setGuessWord(String guessWord) {
        this.guessWord = guessWord;
    }

    public String getGuessWord() {
        return guessWord;
    }

    public int getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(int timeElapsed) {
        this.timeElapsed = timeElapsed;
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

    public void startTimer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (isPlaying) {
                    timeElapsed++; // Tăng thời gian đã trôi qua sau mỗi giây nếu game đang chạy và không bị tạm dừng
                }
            }
        }, 1000, 1000); // Lặp lại mỗi giây (1000 mili giây)
    }

    public void startGame() {
        isPlaying = true; // Bắt đầu game
    }

    public void stopGame() {
        isPlaying = false; // Dừng game
        timeElapsedList.add(timeElapsed); // Lưu thời gian đã trôi qua vào danh sách
        timeElapsed = 0; // Đặt lại thời gian
    }


    public boolean checkGameOver() {
        return wordle.checkGameover() || timeElapsed >= time;
    }

    public void play() {
        startGame();
        startTimer();
    }
    public void reset() {
        wordle.reset();
        timeElapsed = 0;
        isPlaying = false;
    }

    public void rePlay() {
        reset();
        play();
    }

    public void exitGame() {
        reset();
    }
    public void updateState(String word) {
        wordle.updateState(word);
    }

    public static void main(String[] args) {
        WordleManager wm = new WordleManager();
        wm.setWinningWord();
        Scanner sc = new Scanner(System.in);
        boolean ok = false;
        while(!ok) {
            System.out.println("ban co muon thoat game ko:");
            String exit = sc.nextLine();
            if(exit.equals("false")) {
                wm.exitGame();
                return;
            }
            wm.play();
            System.out.println(wm.getWinningWord());
            for(int i = 1; i <= 6; i++) {
                System.out.println(wm.wordle.checkGameover() + "PP");
                System.out.print("nhap tu doan:");
                String guess = sc.nextLine();
                if(wm.wordle.checkWord(guess)) {
                    wm.wordle.updateState(guess);
                    if(wm.checkWin(guess)) {
                        System.out.println(wm.timeElapsed);
                        wm.stopGame();
                        System.out.println("You win");
                        break;
                    }
                    else {
                        System.out.println(wm.wordle.checkGameover() + "PPPP");
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
                    System.out.println(guess + "HHH");
                    System.out.println(i);
                    System.out.println("Wrong word" + "LLL");
                    i--;
                }
            }
            System.out.println("ban co muon choi lai:");
            String replay = sc.nextLine();
            if(replay.equals("true")) {
                wm.rePlay();
            }
            else {
                wm.reset();
                ok = true;
                return;
            }
        }
    }
}
