package sample.data;

import sample.domain.DataTransferAsk;
import sample.domain.Player;
import sample.presentation.Controller;
import sample.presentation.ControllerAsk;

import java.io.*;
import java.util.Scanner;

public class WriteToFile {
    private PrintWriter printWriter;

    public void writeToFile(double slider1, double slider2, double slider3, String text) {
        try {
            printWriter = new PrintWriter(new FileWriter(new File("src/sample/answers.txt"),true));
            printWriter.println(Controller.playerObject.getName());
            printWriter.println("Hvad var din viden omkring brugen af plastik i veje, før du prøvet spillet? (0 er ingen viden,  10 er ekspert viden)");
            printWriter.println(slider1);
            printWriter.println("Hvor større er dit kendskab til brugen af plastik til udlægges af ny vej?(0 er ingen forskel, 10 er stor forskel)");
            printWriter.println(slider2);
            printWriter.println("Hvor stort et investeringspotentiale mener du, at plastikveje har? (0 er ingen, 10 er stort)");
            printWriter.println(slider3);
            printWriter.println("Noget generel feedback til spillet som helhed?");
            printWriter.println(text);
            printWriter.println("");
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


