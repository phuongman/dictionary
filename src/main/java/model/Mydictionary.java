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
        return mydatabase.search(preWord);
    }



}
