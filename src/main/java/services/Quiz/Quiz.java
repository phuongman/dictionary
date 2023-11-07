package services.Quiz;

public  class Quiz {
    /**
     * Quiz.
     */
    protected String question;
    protected String[] choices = new String[4];
    protected String answer_correct;

    public String getQuestion() {
        return this.question;
    };

    public String[] getChoices(){
        return this.choices;
    }

    public String getAnswer_correct() {
        return this.answer_correct;
    }

    public void setQuestion(String question, String questionTitle) {
        this.question = questionTitle + question;
    }

    public void setChoices(String[] choices) {
        this.choices = choices;
    }

    public void setAnswer_correct(String answer_correct) {
        this.answer_correct = answer_correct;
    }


}
