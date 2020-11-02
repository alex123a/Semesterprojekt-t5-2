package worldofzuul.NPCer;

public abstract class NPC {
    private String name;

    public NPC(String name) {
        this.name = name;
    }

    public abstract String description(String input);

    public abstract String toString();

}
