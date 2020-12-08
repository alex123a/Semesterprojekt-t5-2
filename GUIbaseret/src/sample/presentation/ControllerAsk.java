package sample.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sample.domain.DataTransferAsk;


public class ControllerAsk {
    public static Stage theStage;
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
        dataTransferAsk.setSlider(qOneSlider.getValue(), qTwoSlider.getValue(), qThreeSlider.getValue(),qFourText.getText());
        theStage.close();
    }
}
