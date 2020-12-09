package sample.presentation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    public void saveInfo() {
        dataTransferAsk.setSlider(qOneSlider.getValue(), qTwoSlider.getValue(), qThreeSlider.getValue(),qFourText.getText());
        theStage.close();
    }

    public static void startAsk() {
        Main main = new Main();
        Stage primaryStage = new Stage();
        theStage = primaryStage;
        try {
            main.start(theStage);
            Parent askRoot = FXMLLoader.load(ControllerAsk.class.getResource("askSample.fxml"));
            Scene scene2 = new Scene(askRoot);
            primaryStage.setScene(scene2);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
