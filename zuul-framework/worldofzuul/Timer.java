package worldofzuul;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Timer {
    private static Date startTime;
    private static Date endTime;
    private static double time, time1, time2, time3;
    final private static File highScore = Paths.get(new File("worldofzuul/textfiles/highScore.txt").getAbsolutePath()).toFile();

    public static void setStartTime() {
        startTime = new Date();
    }

    public static void setEndTime() {
        endTime = new Date();
    }

    public static void timeScore() {
        long timeScore = endTime.getTime()-startTime.getTime();
        long seconds = timeScore / 1000;
        double secondsDisplay = seconds % 60;
        double minutesDisplay = seconds / 60;
        time = minutesDisplay + secondsDisplay/100;
        System.out.println("Time: " + (int)minutesDisplay + " minutes and " + (int)secondsDisplay + " seconds");
    }

    public static void readHighScore() {
        Scanner reader;
        try {
            reader = new Scanner(highScore);
            time1 = reader.nextDouble();
            time2 = reader.nextDouble();
            time3 = reader.nextDouble();
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find the file");
            e.printStackTrace();
        }
    }

    public static void setHighScore() {
        readHighScore();
        double [] highScores = {time, time1, time2, time3};
        Arrays.sort(highScores);
        try {
            PrintWriter writer = new PrintWriter(highScore);
            writer.print(highScores[0] + " ");
            writer.print(highScores[1] + " ");
            writer.print(highScores[2] + " ");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(time1 + "" + time2 + "" + time3);

    }

}
