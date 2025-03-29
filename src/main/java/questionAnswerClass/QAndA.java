package questionAnswerClass;

public class QAndA implements java.io.Serializable{
    
    private String characterName;
    private String icon;
    private boolean character;

    public QAndA(String characterName, String icon, boolean character) {
        this.characterName = characterName;
        this.icon = icon;
        this.character = character;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isCharacter() {
        return character;
    }

    public void setCharacter(boolean character) {
        this.character = character;
    }
    
}
