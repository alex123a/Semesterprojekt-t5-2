package sample.data;

import sample.domain.DataTransferAsk;
import sample.domain.Player;
import sample.presentation.ControllerAsk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SaveAsk {
    private PrintWriter printWriter;
    private double slider1;
    private double slider2;
    private double slider3;

    public void setSliders(double slider1, double slider2, double slider3){
        this.slider1 = slider1;
        this.slider2 = slider2;
        this.slider3 = slider3;
    }

    public void writeToFile() {
        try {
            printWriter = new PrintWriter(new File("src/sample/answers.txt"));
            //printWriter.println(player.getName());
            printWriter.println("Hvad var din viden omkring brugen af plastik i veje, før du prøvet spillet? (0 er ingen viden,  10 er ekspert viden)");
            printWriter.println(slider1);
            printWriter.println("Hvor større er dit kendskab til brugen af plastik til udlægges af ny vej?(0 er ingen forskel, 10 er stor forskel)");
            printWriter.println(slider2);
            printWriter.println("Hvor stort et investeringspotentiale mener du, at plastikveje har? (0 er ingen, 10 er stort)");
            printWriter.println(slider3);
            printWriter.println("Noget generel feedback til spillet som helhed?");
            //printWriter.println(dataTransferAsk.getText());
            printWriter.println("");
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}


