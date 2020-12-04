package sample.data;

import sample.domain.DataTransferAsk;
import sample.domain.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Paths;

public class SaveAsk {
    //private File questionFile = new File("src/sample/data/answers.txt");
    final private static File questionFile = Paths.get(new File("src/sample/data/textfiles/gameDescriptions/answers.txt").getAbsolutePath()).toFile();
    private DataTransferAsk dataTransferAsk = new DataTransferAsk();
    private Player player = new Player();

    public void writeToFile() {
        try {
            PrintWriter printWriter = new PrintWriter(questionFile);
            printWriter.println(player.getName());
            printWriter.println("Hvad var din viden omkring brugen af plastik i veje, før du prøvet spillet? (0 er ingen viden,  10 er ekspert viden)");
            printWriter.println(dataTransferAsk.getSliders()[0]);
            printWriter.println("Hvor større er dit kendskab til brugen af plastik til udlægges af ny vej?(0 er ingen forskel, 10 er stor forskel)");
            printWriter.println(dataTransferAsk.getSliders()[1]);
            printWriter.println("Hvor stort et investeringspotentiale mener du, at plastikveje har? (0 er ingen, 10 er stort)");
            printWriter.println(dataTransferAsk.getSliders()[2]);
            printWriter.println("Noget generel feedback til spillet som helhed?");
            printWriter.println(dataTransferAsk.getText());
            printWriter.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
