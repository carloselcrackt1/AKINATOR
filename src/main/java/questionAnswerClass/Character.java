package questionAnswerClass;

/**
 *
 * @author Carlos Auqui
 */
public class Character implements Comparable{
    private String characterName;
    private Boolean position;

    public Character(String nombrePersonaje, Boolean posicion) {
        this.characterName = nombrePersonaje;
        this.position = posicion;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public Boolean getPosition() {
        return position;
    }

    public void setPosition(Boolean position) {
        this.position = position;
    }

    @Override
    public int compareTo(Object o) {
        Character ch = (Character) o;
        if(this.position== true && ch.position == false)
            return 1;
        else
          return 0;
    }

    
}
