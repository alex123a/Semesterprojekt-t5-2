package sample.user.NPCer;

public abstract class NPC {
    private String name;
    private boolean talking = false;

    public NPC(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void description(String command);

    public abstract String toString();

    public void setTalking(boolean talking) {
        this.talking = talking;
    }

    public boolean getTalking() {
        return this.talking;
    }

}
