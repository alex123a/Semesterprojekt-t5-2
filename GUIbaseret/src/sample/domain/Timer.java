package sample.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Timer {
    private Date startTime;
    private Date endTime;
    private double time, time1, time2, time3;
    private String name;
    private String name1, name2, name3;
    final private File highScore = new File("src/sample/highScore.txt");

    public void setStartTime() {
        startTime = new Date();
    }

    public void setEndTime() {
        endTime = new Date();
    }

    public void timeScore() {
        long timeScore = endTime.getTime() - startTime.getTime();
        long seconds = timeScore / 1000;
        double secondsDisplay = seconds % 60;
        double minutesDisplay = seconds / 60;
        time = minutesDisplay + secondsDisplay / 100;
        System.out.println("Time: " + (int) minutesDisplay + " minutes and " + (int) secondsDisplay + " seconds");
    }

    public void readHighScore() {
        //Henter highscore listen
        Scanner reader;
        try {
            reader = new Scanner(highScore);
            time1 = reader.nextDouble();
            name1 = reader.next();
            time2 = reader.nextDouble();
            name2 = reader.next();
            time3 = reader.nextDouble();
            name3 = reader.next();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String[] setHighScore() {
        //Printer de tre højeste score ind på vores dokument
        readHighScore();
        timeScore();
        String[] highScores = {time + " " + name, time1 + " " + name1, time2 + " " + name2, time3 + " " + name3};
        Arrays.sort(highScores);
        String printTime1 = String.valueOf(highScores[0]);
        String printTime2 = String.valueOf(highScores[1]);
        String printTime3 = String.valueOf(highScores[2]);
        PrintWriter writer;
        try {
            writer = new PrintWriter(highScore);
            //ændrer '.' til ',' da reader ikke kan læse '.'
            writer.print(printTime1.replace(".", ",") + " ");
            writer.print(printTime2.replace(".", ",") + " ");
            writer.print(printTime3.replace(".", ",") + " ");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return highScores;
    }
}
