package dictionary.Model;

import java.util.ArrayList;

public class NormalDictionary {
    private static ArrayList<Word> words = new ArrayList<Word>();

    /**
     * add new word to dictionary.
     * 
     * @param target  wordTarget of the word
     * @param meaning wordExplain of the word
     */
    public void addWord(String target, String meaning) {
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getWordTarget().equals(target)) {
                return;
            }
        }
        Word w = new Word(target, meaning);
        words.add(w);
    }

    /**
     * remove a word in dictionary.
     * 
     * @param target wordTarget of the word
     */
    public void removeWord(String target) {
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getWordTarget().equals(target)) {
                words.remove(i);
                return;
            }
        }
    }

    /**
     * update new meaning of the word target.
     * 
     * @param target  wordTarget of the word
     * @param meaning wordExplain of the word
     */
    public void updateWordExplain(String target, String meaning) {
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getWordTarget().equals(target)) {
                words.get(i).setWordExplain(meaning);
                return;
            }
        }
    }

    /**
     * Help show all word from dictionary
     * 
     * @return ArrayList words
     */
    public ArrayList<Word> showAllWords() {
        return words;
    }

    /**
     * get meaning of the word target.
     * 
     * @param target wordTarget of the word
     * @return a String is the meaning of the word target
     */
    public String lookUpWord(String target) {
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getWordTarget().equals(target)) {
                return words.get(i).getWordExplain();
            }
        }
        return "Error";
    }
}