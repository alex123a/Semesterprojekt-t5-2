package sample.domain;

import sample.data.FileHandler;

public class DataTransferAsk {
    private FileHandler saveAsk = new FileHandler();


    public void setSlider(double slider1, double slider2, double slider3, String text) {
        saveAsk.writeToFile(slider1,slider2,slider3,text);
    }
}
