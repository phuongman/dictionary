package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Mydatabase extends Database {

    /**
     * khởi tạo Mydatabase.
     */
    public Mydatabase() {
        URL = "jdbc:sqlite:src/main/resources/mydata.db";
    }

    /**
     * hàm lookup.
     */
    public Myword lookup(String word) {
        Myword myword = new Myword();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM mydata WHERE word = ?");
            preparedStatement.setString(1, word);
            resultSet = preparedStatement.executeQuery();
            myword.setWord(resultSet.getString("word"));
            myword.setMeaning(resultSet.getString("meaning"));
            closePreparedStatement();
            closeResultSet();
            System.out.println("Tra từ thành công");
        } catch (Exception e) {
            System.out.println("Lỗi truy vấn");
            throw new RuntimeException(e);
        }
        return myword;
    }

    /**
     * hàm search.
     */
    public ArrayList<String> search(String preMyWord) {
        ArrayList<String> mywords = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT Word FROM mydata WHERE word LIKE ?");
            preparedStatement.setString(1, preMyWord + "%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                mywords.add(resultSet.getString("word"));
            }
            closePreparedStatement();
            closeResultSet();
            System.out.println("Tìm kiếm thành công");
        } catch (Exception e) {
            System.out.println("Lỗi truy vấn");
            throw new RuntimeException(e);

        }
        return mywords;
    }

    /**
     * hàm isWordExist.
     */
    public boolean isWordExist(final String word) {
        boolean check = false;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM mydata WHERE word = ?");
            preparedStatement.setString(1, word);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                check = true;
            }
            closeResultSet();
            closePreparedStatement();
        } catch (SQLException e) {
            System.out.println("Lỗi truy vấn isWordExist");
            throw new RuntimeException(e);
        }
        return check;
    }

    /**
     * hàm insert.
     */
    public void insert(Myword myword) {
        try {
            if(!isWordExist(myword.getWord())) {
                preparedStatement = connection.prepareStatement("INSERT INTO mydata VALUES (?, ?)");
                preparedStatement.setString(1, myword.getWord());
                preparedStatement.setString(2, myword.getMeaning());
                preparedStatement.executeUpdate();
                closePreparedStatement();
                System.out.println("Thêm thành công");
            }
            else {
                System.out.println("Từ đã tồn tại");
            }
        } catch (Exception e) {
            System.out.println("Lỗi truy vấn");
            throw new RuntimeException(e);
        }
    }

    /**
     * hàm update.
     */
    public void update(Myword myword, String word) {
        try {
            preparedStatement = connection.prepareStatement("UPDATE mydata SET meaning = ?, word = ? WHERE word = ?");
            preparedStatement.setString(1, myword.getMeaning());
            preparedStatement.setString(2, myword.getWord());
            preparedStatement.setString(3, word);
            preparedStatement.executeUpdate();
            closePreparedStatement();
            System.out.println("Cập nhật thành công");
        } catch (Exception e) {
            System.out.println("Lỗi truy vấn");
            throw new RuntimeException(e);

        }
    }

    /**
     * hàm delete.
     */
    public void delete(String word) {
        try {
            if(!isWordExist(word)) {
                System.out.println("Từ không tồn tại");
                return;
            }
            else {
                preparedStatement = connection.prepareStatement("DELETE FROM mydata WHERE word = ?");
                preparedStatement.setString(1, word);
                preparedStatement.executeUpdate();
                closePreparedStatement();
                System.out.println("Xóa thành công");
            }
        } catch (Exception e) {
            System.out.println("Lỗi truy vấn");
            throw new RuntimeException(e);

        }
    }

    public ArrayList<String> showAllWords() {
        ArrayList<String> mywords = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT Word FROM mydata");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                mywords.add(resultSet.getString("word"));
            }
            closePreparedStatement();
            closeResultSet();
            System.out.println("truy vaans thành công");
        } catch (Exception e) {
            System.out.println("Lỗi truy vấn");
            throw new RuntimeException(e);

        }
        return mywords;
    }

}
