package worldofzuul.NPCer;

import java.io.File;

public abstract class NPC {
    private String name;

    public NPC(String name) {
        this.name = name;
    }

    public abstract File description();

    public abstract String toString();

}
