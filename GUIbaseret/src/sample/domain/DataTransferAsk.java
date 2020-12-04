package sample.domain;

import sample.presentation.ControllerAsk;

public class DataTransferAsk {
    private ControllerAsk controllerAsk = new ControllerAsk();

    public String getText() {
        return controllerAsk.getAskTxt();
    }

    public double[] getSliders() {
        return controllerAsk.getSliderArray();
    }

}
