package model;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    protected static Trie Trie;
    private static Database database = new Database();
    public Dictionary() {
        database.Connect();
        Trie = new Trie();
        List<Word> words = database.getAllWords();
        for(Word word : words) {
            Trie.add(word);
        }
    }

    public void closeDictionary() {
        database.closeConnection();
    }
    public final Word lookup(String word) {
        return Trie.lookup(word);
    }

    public final List<String> search(String preWord) {
        return Trie.search(preWord);
    }







}
