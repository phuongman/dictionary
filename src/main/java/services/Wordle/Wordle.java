package services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Wordle {
    /**
     * Khai báo các từ winning.
     */

    // Lấy ngày hiện tại
    Date currentDate = new Date();

    // Sử dụng ngày để tạo một số ngẫu nhiên
    long randomSeed = currentDate.getTime();

    // Sử dụng số ngẫu nhiên để chọn từ
    Random random = new Random(randomSeed);
    private List<String> winWords = new ArrayList<>();
    private List<String> listWords = new ArrayList<>();
    private String winningWord;

    public Wordle() {
        initWinWords();
        initListWords();
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
        int index = random.nextInt(winWords.size());
        winningWord = winWords.get(index);
    }
    public boolean checkWin(String word) {
        return word.equals(winningWord);
    }

    public boolean binarySearch(List<String> list, String word) {
        int d = 0, c = list.size() - 1;
        while (d <= c) {
            int g = (d + c) >> 1;
            if(list.get(g).equals(word)) return true;
            if(list.get(g).compareTo(word) < 0) d = g + 1;
            else c = g - 1;
        }
        return false;
    }
    public boolean checkWord(String word) {
        return (binarySearch(listWords, word) || binarySearch(winWords, word));
    }

    public String getWinningWord() {
        return winningWord;
    }

    public void reset() {
        setWinningWord();
    }

    public void rePlay() {
        setWinningWord();

    }
}
