package sample.domain;

import sample.data.SaveAsk;

public class DataTransferAsk {
    private SaveAsk saveAsk = new SaveAsk();


    public void setSlider(double slider1, double slider2, double slider3) {
        saveAsk.setSliders(slider1,slider2,slider3);
    }


}
