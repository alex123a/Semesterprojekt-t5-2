package sample.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import sample.data.SaveAsk;
import sample.domain.DataTransferAsk;


public class ControllerAsk {
    String askTxt;
    private static SaveAsk saveAsk = new SaveAsk();
    private static int sliderValue1;
    private int sliderValue2;
    private int sliderValue3;
    private static String Textfield;
    DataTransferAsk dataTransferAsk = new DataTransferAsk();

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
        dataTransferAsk.setSlider(qOneSlider.getValue(),qTwoSlider.getValue(),qThreeSlider.getValue());
        saveAsk.writeToFile();
        System.out.println(sliderValue1);
    }
}
