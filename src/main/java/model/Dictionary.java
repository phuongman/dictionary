package model;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {

    /**
     * khai báo cây Trie.
     */
    protected static Trie Trie;
    /**
     * khai báo database.
     */
    private static Database database = new Database();
    /**
     * khởi tạo từ điển.
     */
    public Dictionary() {
        database.Connect();
        Trie = new Trie();
        List<Word> words = database.getAllWords();
        for(Word word : words) {
            Trie.add(word);
        }
    }

    /**
     * Đóng từ điển.
     */
    public void closeDictionary() {
        database.closeConnection();
    }

    /**
     * Thêm từ vào trie.
     */
    public final Word lookup(String word) {
        return Trie.lookup(word);
    }

    /**
     * hàm search.
     */
    public final List<String> search(String preWord) {
        return Trie.search(preWord);
    }







}
