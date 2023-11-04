package services.Quiz;

import java.io.BufferedReader;

public class FillTheBlank extends Quiz {

    private final int numQuestions = 356;

    @Override
    public void setQuestion(String question) {
        this.question = "Fill in the blank with the correct answer: " + question;
    }

    public void initFillTheBlank() {
        int posQuestion = (int) (Math.random() * numQuestions);
        //BufferedReader reader = new BufferedReader();

    }
}
