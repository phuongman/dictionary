package dictionary.Model;

import java.util.ArrayList;
import dictionary.Inputer;

public class NormalDictionary {
    private static ArrayList<Word> words = new ArrayList<Word>();

    /**
     * add new word to dictionary.
     * 
     * @param target  wordTarget of the word
     * @param meaning wordMeaning of the word
     * @return true if 'target' isn't in the dictionary (can add), otherwise false
     */
    public boolean addWord(String target, String meaning) {
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getWordTarget().equals(target)) {
                return false;
            }
        }
        Word w = new Word(target, meaning);
        words.add(w);
        return true;
    }

    /**
     * remove a word in dictionary.
     * 
     * @param target wordTarget of the word
     * @return true if 'target' is already in the dictionary (can remove), otherwise
     *         false
     */
    public boolean removeWord(String target) {
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getWordTarget().equals(target)) {
                words.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * update new meaning of the word target.
     * 
     * @param target  wordTarget of the word
     * @param meaning wordMeaning of the word
     * @return true if 'target' is already in the dictionary (can update), otherwise
     *         false
     */
    public boolean updatewordMeaning(String target, String meaning) {
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getWordTarget().equals(target)) {
                words.get(i).setwordMeaning(meaning);
                return true;
            }
        }
        return false;
    }

    /**
     * Help show all word from dictionary.
     * 
     * @return String contains all words
     */
    public String showAllWords() {
        if (words.size() == 0) {
            return "Error";
        }
        int noSize = 2;
        int englishSize = 7;
        int vietnameseSize = 10;
        for (int i = 0; i < words.size(); i++) {
            englishSize = Math.max(englishSize, words.get(i).getWordTarget().length());
            vietnameseSize = Math.max(vietnameseSize, words.get(i).getwordMeaning().length());
        }
        noSize = Math.max(noSize, String.valueOf(words.size()).length());
        String result = "";
        result += Inputer.compressWordplusSpace("No", noSize) + " | ";
        result += Inputer.compressWordplusSpace("English", englishSize) + " | Vietnamese\n";
        for (int i = 0; i < words.size(); i++) {
            result += Inputer.compressWordplusSpace(String.valueOf(i + 1), noSize) + " | ";
            result += Inputer.compressWordplusSpace(words.get(i).getWordTarget(), englishSize) + " | ";
            result += words.get(i).getwordMeaning() + "\n";
        }
        return result;
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
                return words.get(i).getwordMeaning();
            }
        }
        return "Error";
    }
}