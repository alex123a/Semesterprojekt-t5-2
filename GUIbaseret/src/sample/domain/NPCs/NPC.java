package sample.domain.NPCs;

public abstract class NPC {
    private String name;
    private boolean talking = false;
    private String image;

    /**
     * @param name  is used to assign a name to each NPC
     * @param image is used to find the image path
     */
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