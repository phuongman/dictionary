package model;
import java.sql.*;

public class DatabaseQuery {

    /**
     * Đường dẫn đến cơ sở dữ liệu.
     */
    private static final String URL = "jdbc:sqlite:engData.db";
    /**
     * Câu lệnh truy vấn.
     */
    private static Connection connection = null;
    /**
     * Câu lệnh truy vấn.
     */
    private static PreparedStatement preparedStatement = null;
    /**
     * Kết quả truy vấn.
     */
    private static ResultSet resultSet = null;
    /**
     * Câu lệnh truy vấn.
     */
    private static final String[] SQL_Query = {
        "SELECT * FROM engviet WHERE word = ?",
        "INSERT INTO engviet(Word, Type, Pronunciation, Meaning, Antonyms, Synonym) VALUES (?, ?, ?, ?, ?, ?)",
        "UPDATE engviet SET Word = ?, Type = ?, Pronunciation = ?, Meaning = ?, Antonyms = ?, Synonym = ? WHERE Word = ?",
        "DELETE FROM engviet WHERE word = ? ",
        "SELECT word FROM engviet WHERE word LIKE ? || '%'"
    };

    /**
     * Kết nối đến cơ sở dữ liệu.
     */
    public static void Connect() {
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
    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Lỗi đóng kết nối đến cơ sở dữ liệu");
        }
    }

    /**
     * Đóng câu lệnh truy vấn.
     */
    public static void closePreparedStatement() {
        try {
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Lỗi đóng câu lệnh truy vấn");
        }
    }

    /**
     * Đóng kết quả truy vấn.
     */
    public static void closeResultSet() {
        try {
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Lỗi đóng kết quả truy vấn");
        }
    }

    /**
     * Kiểm tra từ có tồn tại trong cơ sở dữ liệu hay không.
     */
    public static boolean isWordExist(final String word) {
        boolean check = false;
        try {
            preparedStatement = connection.prepareStatement(SQL_Query[0]);
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
     * Tìm kiếm từ.
     */
    public static String searchWord(final String preWord) {
        StringBuilder result = new StringBuilder("");
        try {
            preparedStatement = connection.prepareStatement(SQL_Query[4]);
            preparedStatement.setString(1, preWord); // Đặt giá trị cho tham số đầu vào
            resultSet = preparedStatement.executeQuery(); // Sử dụng preparedStatement thay vì statement
            while (resultSet.next()) {
                result.append(resultSet.getString("word")).append("\n");
            }
            closeResultSet();
            closePreparedStatement();
        } catch (SQLException e) {
            System.out.println("Lỗi truy vấn search");
        }
        return result.toString();
    }

    /**
     * Tìm kiếm từ.
     */
    public static Word lookUpWord(final String word) {
        Word result = new Word();
        try {

            preparedStatement = connection.prepareStatement(SQL_Query[0]);
            preparedStatement.setString(1, word);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.setWordTarget(resultSet.getString("Word"));
                result.setWordType(resultSet.getString("Type"));
                result.setWordPronounce(resultSet.getString("Pronunciation"));
                result.setWord_explain(resultSet.getString("Meaning"));
                result.setWordAntonym(resultSet.getString("Antonyms"));
                result.setWordSynonym(resultSet.getString("Synonym"));
            }
            closePreparedStatement();
            closeResultSet();
        } catch (Exception e) {
            System.out.println("Lỗi truy vấn look up");
        }
        return result;
    }

    /**
     * Thêm từ mới vào cơ sở dữ liệu.
     */
    public static void insertWord(final String word, final String type, final String pronounce, final String meaning, final String antonym, final String synonym) {
        try {
            if(!isWordExist(word)) {
                preparedStatement = connection.prepareStatement(SQL_Query[1]);
                preparedStatement.setString(1, word);
                preparedStatement.setString(2, type);
                preparedStatement.setString(3, pronounce);
                preparedStatement.setString(4, meaning);
                preparedStatement.setString(5, antonym);
                preparedStatement.setString(6, synonym);
                preparedStatement.executeUpdate();
                closePreparedStatement();
                System.out.println("Thêm từ thành công");
            }
            else {
                System.out.println("Từ đã tồn tại");
            }
        } catch (Exception e) {
            System.out.println("Lỗi truy vấn insert");
            throw new RuntimeException(e);
        }
    }

    /**
     * Cập nhật từ.
     */
    public static void updateWord(final String word, final String type, final String pronounce, final String meaning, final String example, final String antonym, final String synonym) {
        try {
            if(!isWordExist(word)) {
                System.out.println("Từ không tồn tại");
                return;
            }
            else {
                preparedStatement = connection.prepareStatement(SQL_Query[2]);
                preparedStatement.setString(1, word);
                preparedStatement.setString(2, type);
                preparedStatement.setString(3, pronounce);
                preparedStatement.setString(4, meaning);
                preparedStatement.setString(5, antonym);
                preparedStatement.setString(6, synonym);
                preparedStatement.executeUpdate();
                closePreparedStatement();
                System.out.println("Cập nhật từ thành công");
            }
        } catch (Exception e) {
            System.out.println("Lỗi truy vấn update");
            throw new RuntimeException(e);
        }
    }

    /**
     * Xóa từ.
     */
    public static void deleteWord(final String word) {
        try {
            if(!isWordExist(word)) {
                System.out.println("Từ không tồn tại");
                return;
            } else {
                preparedStatement = connection.prepareStatement(SQL_Query[3]);
                preparedStatement.setString(1, word);
                preparedStatement.executeUpdate();
                closePreparedStatement();
                System.out.println("Xóa từ thành công");
            }
        }
        catch (Exception e) {
            System.out.println("Lỗi truy vấn delete");
            throw new RuntimeException(e);
        }
    }


}
