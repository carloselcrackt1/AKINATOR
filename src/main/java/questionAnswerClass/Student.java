package questionAnswerClass;

/**
 *
 * @author Carlos Auqui
 */
public class Student{
    private String characterName;
   
    public Student(String nombrePersonaje) {
        this.characterName = nombrePersonaje;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }   
}
