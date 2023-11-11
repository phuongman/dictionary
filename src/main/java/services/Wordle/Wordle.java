package services.Wordle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

public class Wordle {
    /**
     * Khai báo các từ winning.
     */
    private List<String> winWords = new ArrayList<>();
    private List<String> listWords = new ArrayList<>();
    private String winningWord;
    private int[] stateLetter  = new int[26];
    int cnt = 0;

    public Wordle() {
        initWinWords();
        initListWords();
        for(int i = 0; i < 26; i++) {
            stateLetter[i] = -1;
        }
    }

    public void initWinWords() {
        String path = "src/main/resources/winningWords.txt";
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while(line != null) {
                winWords.add(line);
                line = br.readLine();
            }
        } catch (Exception e) {
            System.out.println("Lỗi đọc file winningWords.txt");
            e.printStackTrace();
        }
    }

    public void initListWords() {
        String path = "src/main/resources/wordleDictionary.txt";
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while(line != null) {
                listWords.add(line);
                line = br.readLine();
            }
        } catch (Exception e) {
            System.out.println("Lỗi đọc file wordleDictionary.txt");
            e.printStackTrace();
        }
    }

    public void setWinningWord() {
        Random ran = new Random();
        int index = ran.nextInt(winWords.size());
        winningWord = winWords.get(index);
    }
    public boolean checkWin(String word) {
        return word.equals(winningWord);
    }

    public boolean checkWord(String word) {
        return (listWords.contains(word) || winWords.contains(word));
    }

    public String getWinningWord() {
        return winningWord;
    }

    public void updateState(String word) {
        cnt++;
        for(int i = 0; i < word.length(); i++) {
            if(word.charAt(i) == winningWord.charAt(i)) {
                stateLetter[word.charAt(i) - 'a'] = 1;
            } else {
                boolean check = false;
                for(int j = 0; j < winningWord.length(); j++) {
                    if(word.charAt(i) == winningWord.charAt(j)) {
                        stateLetter[word.charAt(i) - 'a'] = 2;
                        check = true;
                        break;
                    }
                }
                if(!check) {
                    stateLetter[word.charAt(i) - 'a'] = 0;
                }
            }
        }
     }

    public boolean checkGameover() {
        if(cnt == 6) {
            return true;
        }
        return false;
    }

    public void reset() {
        cnt = 0;
        for(int i = 0; i < 26; i++) {
            stateLetter[i] = -1;
        }
        setWinningWord();


    }

    public int getStateLetter(char c) {
        return stateLetter[c - 'a'];
    }
}
