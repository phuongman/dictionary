package services.Quiz;

public abstract class Quiz {
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

    public abstract void setQuestion(String question);

    public void setChoices(String[] choices) {
        this.choices = choices;
    }

    public void setAnswer_correct(String answer_correct) {
        this.answer_correct = answer_correct;
    }


}
