package sample.domain;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class testAfFiler {
    public static void main(String[] args) {
        String file = Paths.get(new File("worldofzuul\\FarmerenText.txt").getAbsolutePath()).toString();
        try {
            String line = Files.readAllLines(Paths.get(file)).get(1);
            System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
