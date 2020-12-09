package sample.domain;

import sample.data.FileHandler;

public class DataTransferAsk {
    private FileHandler saveAsk = new FileHandler();

    // This method passes down the values given from the sliders and the string from the txt field
    // Uses the method from the data layer to write to the field.
    public void setSlider(double slider1, double slider2, double slider3, String text) {
        saveAsk.writeToFile(slider1, slider2, slider3, text);
    }
}
