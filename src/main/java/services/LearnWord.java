package services;

import model.Word;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static ui.App.dictionary;


public class LearnWord {
    private int cntLearnerWords;
    // Lấy ngày hiện tại
    LocalDate currentDate = LocalDate.now();

    // Sử dụng ngày hiện tại để tạo một số ngẫu nhiên
    long seed = currentDate.toEpochDay(); // Lấy số ngày từ Epoch

    // Sử dụng số ngẫu nhiên để khởi tạo Random
    Random random = new Random(seed);
    private List<Word> wordsToLearn = new ArrayList<>();

    public LearnWord() {
        cntLearnerWords = 0;
    }

    public int getCntLearnerWords() {
        return cntLearnerWords;
    }

    public void setCntLearnerWords(int cntLearnerWords) {
        this.cntLearnerWords = cntLearnerWords;
    }

    public void initWords() {
        for(int i = 0; i < cntLearnerWords; i++) {
            int index =random.nextInt(dictionary.getListWords().size());
            wordsToLearn.add(dictionary.lookup(dictionary.getListWords().get(index)));
        }
    }

    public List<Word> getWordsToLearn() {
        return wordsToLearn;
    }

    public static void main(String[] args) {
        LearnWord learnWord = new LearnWord();
        learnWord.setCntLearnerWords(10);
        learnWord.initWords();
        for(Word word : learnWord.getWordsToLearn()) {
            System.out.println(word.getWordTarget());
            System.out.println(word.getWordType());
            System.out.println(word.getWordPronounce());
            System.out.println(word.getWordExplain());
            System.out.println(word.getWordSynonym());
            System.out.println(word.getWordAntonym());
        }
    }

}
