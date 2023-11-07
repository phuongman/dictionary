package services.Quiz;

import java.sql.*;
import java.util.Random;

public class ChooseQuestion {
    /**
     * Tạo một số ngẫu nhiên để tránh việc lặp lại các câu hỏi.
     */
    private long seed = System.currentTimeMillis() + new Random().nextInt(1000); // Thêm một số ngẫu nhiên
    private Random random = new Random(seed);
    private final String URL = "jdbc:sqlite:src/main/resources/questions.db";
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final int numQuestions = 356;
    private static  final int numWordMean = 102527;
    private static final int numWordPro = 53614;

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
    public ChooseQuestion() {
        Connect();
    }

    public Quiz initFillTheBlank() {
        try {
            String questionTitle = "Choose the correct word to fill in the blank: ";
            int id = random.nextInt(numQuestions) + 1;
            preparedStatement = connection.prepareStatement("SELECT question, answer1, answer2, answer3, answer4, correct FROM questions where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Quiz quiz = new Quiz();
                quiz.setQuestion(resultSet.getString("question"), questionTitle);
                String[] choices = new String[4];
                choices[0] = resultSet.getString("answer1");
                choices[1] = resultSet.getString("answer2");
                choices[2] = resultSet.getString("answer3");
                choices[3] = resultSet.getString("answer4");
                quiz.setChoices(choices);
                quiz.setAnswer_correct(resultSet.getString("correct"));
                return quiz;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi truy vấn cơ sở dữ liệu FillTheBlank");
            e.printStackTrace();
        }
        return null;
    }

    public Quiz query(String SQL, String SQL2, int typeQuiz) {
        String labelcolunm = "";
        String labelcolunm2 = "";
        String questionTitle = "";
        int numQuestion = 0;
        switch (typeQuiz) {
            case 1:
                labelcolunm = "word";
                labelcolunm2 = "meaning";
                questionTitle = "Choose the correct meaning of the word: ";
                numQuestion = numWordMean;
                break;
            case 2:
                labelcolunm = "meaning";
                labelcolunm2 = "word";
                questionTitle = "Choose the correct word of the meaning: ";
                numQuestion = numWordMean;
                break;
            case 3:
                labelcolunm = "word";
                labelcolunm2 = "pronunciation";
                questionTitle = "Choose the correct pronunciation of the word: ";
                numQuestion = numWordPro;
                break;
            case 4:
                labelcolunm = "pronunciation";
                labelcolunm2 = "word";
                questionTitle = "Choose the correct word of the pronunciation: ";
                numQuestion = numWordPro;
                break;
        }
        try {
            Quiz quiz = new Quiz();
            String[] choices = new String[4];
            for(int i = 0; i < 4; i++) {
                int id = random.nextInt(numQuestion) + 1;
                if(i == 0) {
                    preparedStatement = connection.prepareStatement(SQL);
                    preparedStatement.setInt(1, id);
                    resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        quiz.setQuestion(resultSet.getString(labelcolunm), questionTitle);
                    }
                    closePreparedStatement();
                    closeResultSet();
                }
                preparedStatement = connection.prepareStatement(SQL2);
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    choices[i] = resultSet.getString(labelcolunm2);
                    if(i == 0) {
                        quiz.setAnswer_correct(choices[i]);
                    }
                }
                closePreparedStatement();
                closeResultSet();
            }
            quiz.setChoices(choices);
            return quiz;
        } catch (Exception e) {
            System.out.println("Lỗi truy vấn cơ sở dữ liệu WordMeaning");
            e.printStackTrace();
        }
        return null;
    }

    public Quiz initQuiz(int typeQuiz) {
        String SQL = "";
        String SQL2 = "";
        switch (typeQuiz) {
            case 1:
                SQL = "SELECT word FROM wordmean WHERE id = ?";
                SQL2 = "SELECT meaning FROM wordmean WHERE id = ?";
                break;
            case 2:
                SQL = "SELECT meaning FROM wordmean WHERE id = ?";
                SQL2 = "SELECT word FROM wordmean WHERE id = ?";
                break;
            case 3:
                SQL = "SELECT word FROM wordpro WHERE id = ?";
                SQL2 = "SELECT pronunciation FROM wordpro WHERE id = ?";
                break;
            case 4:
                SQL = "SELECT pronunciation FROM wordpro WHERE id = ?";
                SQL2 = "SELECT word FROM wordpro WHERE id = ?";
                break;
            default:
                break;
        }
        return query(SQL, SQL2, typeQuiz);
    }
}
