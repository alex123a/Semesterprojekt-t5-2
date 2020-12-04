package sample.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;


public class ControllerAsk {
    String askTxt;

    @FXML
    private Slider qOneSlider;

    @FXML
    private Slider qTwoSlider;

    @FXML
    private Slider qThreeSlider;

    @FXML
    private TextArea qFourText;

    @FXML
    private Button submitBut;

    @FXML
    public double[] SaveInfo(ActionEvent event) {
        this.askTxt = qFourText.getText();
        double sliderArray[] = {qOneSlider.getValue(),qTwoSlider.getValue(),qThreeSlider.getValue()};
        return sliderArray;
    }

    public String getAskTxt(){
        return askTxt;
    }
}
