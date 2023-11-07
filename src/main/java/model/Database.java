package model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Database {

    /**
     * Đường dẫn đến cơ sở dữ liệu.
     */
    protected String URL = "jdbc:sqlite:src/main/resources/engData.db";
    /**
     * Câu lệnh truy vấn.
     */
    protected Connection connection = null;
    /**
     * Câu lệnh truy vấn.
     */
    protected PreparedStatement preparedStatement = null;
    /**
     * Kết quả truy vấn.
     */
    protected ResultSet resultSet = null;


    /**
     * Kết nối đến cơ sở dữ liệu.
     */
    public void Connect() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("Sqlite JDBC Driver not found.");
            e.printStackTrace();
            return;
        }

        try {
            connection = DriverManager.getConnection(URL);
            System.out.println("Kết nối thành công đến cơ sở dữ liệu");
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối đến cơ sở dữ liệu");
            e.printStackTrace();
        }
    }

    /**
     * Đóng kết nối đến cơ sở dữ liệu.
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Lỗi đóng kết nối đến cơ sở dữ liệu");
        }
    }

    /**
     * Đóng câu lệnh truy vấn.
     */
    public void closePreparedStatement() {
        try {
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Lỗi đóng câu lệnh truy vấn");
        }
    }

    /**
     * Đóng kết quả truy vấn.
     */
    public void closeResultSet() {
        try {
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Lỗi đóng kết quả truy vấn");
        }
    }

    /**
     * Lấy danh sách từ word.
     */
    public final List<Word> getAllWords() {
        String SQL = "SELECT * FROM engviet";
        Word newWord;
        List<Word> dictionary = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SQL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                newWord = new Word();
                newWord.setWordTarget(resultSet.getString("Word"));
                newWord.setWordType(resultSet.getString("Type"));
                newWord.setWordPronounce(resultSet.getString("Pronunciation"));
                newWord.setWord_explain(resultSet.getString("Meaning"));
                newWord.setWordAntonym(resultSet.getString("Antonyms"));
                newWord.setWordSynonym(resultSet.getString("Synonym"));
                dictionary.add(newWord);

            }

            closeResultSet();
            closePreparedStatement();
        } catch (SQLException e) {
            System.out.println("Lỗi truy vấn getListWords");
            throw new RuntimeException(e);
        }
        return dictionary;

    }

}
