package services;

import model.Word;

import java.util.ArrayList;
import java.util.List;
import static model.Test.*;
public class LearnWord {
    private int cntLearnerWords;
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
            int index = (int) (Math.random() * dictionary.getListWords().size());
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
