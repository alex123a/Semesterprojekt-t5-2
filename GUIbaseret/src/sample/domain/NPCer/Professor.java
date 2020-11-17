package sample.domain.NPCer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Professor extends NPC {
    //Insert path into config file.
    private String file = new File("src/sample/domain/NPCer/NPC-descriptions/ProfessorText.txt").toString();

    public Professor(String name) {
        super(name);
    }

    @Override
    public void description(String command) {
        if (!super.getTalking()){
            try {
                setTalking(true);
                String line;
                line = Files.readAllLines(Paths.get(this.file)).get(0);
                System.out.println(line);
                line = Files.readAllLines(Paths.get(this.file)).get(1);
                System.out.println(line);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Could not find text file");
            }
        } else if (command.equals("bye") && super.getTalking()){
            try {
                String line;
                line = Files.readAllLines(Paths.get(this.file)).get(2);
                System.out.println(line);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Could not find text file");
            }
        } else {
            try {
                String line;
                line = Files.readAllLines(Paths.get(this.file)).get(3);
                System.out.println(line);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Could not find text file");
            }
        }
    }

    @Override
    public String toString() {
        return super.getName();
    }
}
