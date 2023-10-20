package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
public class DatabaseQuery {

    private static final String URL = "jdbc:mysql://localhost:3306/english";
    private static final String USER = "root";
    private static final String PASSWORD = "PhuongMan31102004@<3";
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    public static Connection connect() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi kết nối đến cơ sở dữ liệu", e);
        }
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi đóng kết nối đến cơ sở dữ liệu", e);
        }
    }

    public static void closeStatement() {
        try {
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi đóng câu lệnh SQL", e);
        }
    }

    public static void closeResultSet() {
        try {
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi đóng kết quả truy vấn", e);
        }
    }

    public static void close() {
        closeResultSet();
        closeStatement();
        closeConnection();
    }

    public static String lookUpWord(final String word) {
        String SQL_Query = "SELECT * FROM english WHERE word = '" + word + "'";
        Word result = new Word();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_Query);
            while (resultSet.next()) {
                result.setId( Integer.parseInt(resultSet.getString("id")));
                result.setWordTarget(resultSet.getString("word"));
                result.setWordType(resultSet.getString("type"));
                result.setWordPronounce(resultSet.getString("pronounce"));
                result.setWord_explain(resultSet.getString("meaning"));
                result.setWordExample(resultSet.getString("example"));
                result.setWordAntonym(resultSet.getString("antonym"));
                result.setWordSynonym(resultSet.getString("synonym"));
            }
            closeStatement();
            closeResultSet();
            return result.toString();
        } catch (Exception e) {
            System.out.println(e);
            return "Không tìm thấy từ này";
        }
    }

    public static void insertWord(final String word, final String type, final String pronounce, final String meaning, final String example, final String antonym, final String synonym) {
        String SQL_Query = "INSERT INTO english(word, type, pronounce, meaning, example, antonym, synonym) VALUES ('" + word + "', '" + type + "', '" + pronounce + "', '" + meaning + "', '" + example + "', '" + antonym + "', '" + synonym + "')";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(SQL_Query);
            closeStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
