package sample.data;

import sample.presentation.Controller;
import sample.presentation.Main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandler {
    private PrintWriter printWriter;

    // Reads lines from a txt file
    public String ReadFile(String path, int index) {
        String line = "";
        try {
            // Files loades the file, that is given from the path in the parameters of the method.
            // readallLines reads all the lines, and then .get(index) is used to get the specific line line in the text field you want.
            return Files.readAllLines(Paths.get(path)).get(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }
    // Method for writeing to a file
    // Parameters are the values from the silders and the text in the txt field
    public void writeToFile(double slider1, double slider2, double slider3, String text) {
        try {
            // Makes a printwriter from the file, we want to read to.
            // The append functions is so that it writes on a new line every time.
            printWriter = new PrintWriter(new FileWriter(new File("src/sample/answers.txt"), true));
            // Writes the player name
            printWriter.println(Main.game.getPlayerObject().getName());
            printWriter.println("Hvad var din viden omkring brugen af plastik i veje, før du prøvet spillet? (0 er ingen viden,  10 er ekspert viden)");
            printWriter.println(slider1);
            printWriter.println("Hvor større er dit kendskab til brugen af plastik til udlægges af ny vej?(0 er ingen forskel, 10 er stor forskel)");
            printWriter.println(slider2);
            printWriter.println("Hvor stort et investeringspotentiale mener du, at plastikveje har? (0 er ingen, 10 er stort)");
            printWriter.println(slider3);
            printWriter.println("Noget generel feedback til spillet som helhed?");
            printWriter.println(text);
            printWriter.println("");
            // closes the printwriter
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
