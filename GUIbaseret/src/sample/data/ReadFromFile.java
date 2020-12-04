package sample.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFromFile {

    public String ReadFile(String path, int num) {
        String line = "";
        try {
            return Files.readAllLines(Paths.get(path)).get(num);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }
}
