package model;

import java.util.ArrayList;

public class Mydictionary {

    /**
     * The database of this dictionary.
     */
    private Mydatabase mydatabase = new Mydatabase();

    /**
     * Constructor.
     */
    public Mydictionary() {
        mydatabase.Connect();
    }

    /**
     * Close the connection to the database.
     */
    public void closeMydictionary() {
        mydatabase.closeConnection();
    }

    /**
     * thêm từ vào từ điển.
     */
    public void add(Myword myword) {
        mydatabase.insert(myword);
    }

    /**
     * sửa từ trong từ điển.
     */
    public void edit(Myword myword, String word) {
        mydatabase.update(myword, word);
    }

    /**
     * xóa từ trong từ điển.
     */
    public void delete(String word) {
        mydatabase.delete(word);
    }

    /**
     * tìm kiếm từ trong từ điển.
     */
    public Myword lookup(String word) {
        return mydatabase.lookup(word);
    }

    /**
     * tìm kiếm từ trong từ điển.
     */
    public ArrayList<String> search(String preWord) {
        if (preWord == null) return mydatabase.showAllWords();
        return mydatabase.search(preWord);
    }

    public ArrayList<String> getAllWord() {
        return mydatabase.showAllWords();
    }

    public static void main(String[] args) {
        Mydictionary mydictionary = new Mydictionary();
        Myword myword = new Myword();
        myword.setWord("hello");
        myword.setMeaning("xin chào");
        mydictionary.add(myword);
        Myword newWord = new Myword("hello1", "xin chào1");
        mydictionary.edit(newWord, "hello");
        Myword res = mydictionary.lookup("hello1");
        System.out.println(res.getWord() + " " + res.getMeaning());
        mydictionary.closeMydictionary();
    }

}
