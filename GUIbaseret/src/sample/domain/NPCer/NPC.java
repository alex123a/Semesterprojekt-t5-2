package sample.domain.NPCer;

public abstract class NPC {
    private String name;
    private boolean talking = false;
    private String image;

    public NPC(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract String description(String command);

    public abstract String toString();

    public void setTalking(boolean talking) {
        this.talking = talking;
    }

    public boolean getTalking() {
        return this.talking;
    }

    public String getImage() {
        return image;
    }
}
