package sample.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import sample.data.SaveAsk;


public class ControllerAsk {
    String askTxt;
    double[] sliderArray;
    private SaveAsk saveAsk = new SaveAsk();

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
    public void SaveInfo() {
        this.askTxt = qFourText.getText();
        System.out.println(qFourText.getText());
        sliderArray = new double[] {qOneSlider.getValue(), qTwoSlider.getValue(), qThreeSlider.getValue()};
        saveAsk.writeToFile();
    }

    public String getAskTxt(){
        return askTxt;
    }

    public double[] getSliderArray() {
        return sliderArray;
    }
}
