package questionAnswerClass;

/**
 *
 * @author Carlos Auqui
 */
public class Question implements Comparable {
    private String question;
    private Boolean position;

    public Question(String question, Boolean position) {
        this.question = question;
        this.position = position;
    }

    public Boolean getPosition() {
        return position;
    }

    public void setPosition(Boolean position) {
        this.position = position;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public int compareTo(Object o) {
         Question q = (Question) o;
        if(this.position== true && q.position == false)
            return 1;
        else
          return 0;
    }
    
    
}
