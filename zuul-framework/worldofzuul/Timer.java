package worldofzuul;

import java.util.Date;

public class Timer {
    private static Date startTime;
    private static Date endTime;

    public static void setStartTime() {
        startTime = new Date();
    }

    public static void setEndTime() {
        endTime = new Date();
    }

    public static void timeScore() {
        long timeScore = endTime.getTime()-startTime.getTime();
        System.out.println(timeScore);
    }
}