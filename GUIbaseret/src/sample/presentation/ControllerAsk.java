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
    //Passes down the values to the domain layer
    public void saveInfo() {
        dataTransferAsk.setSlider(qOneSlider.getValue(), qTwoSlider.getValue(), qThreeSlider.getValue(),qFourText.getText());
        theStage.close();
    }
    // New stage for the survey
    public static void startAsk() {
        // Need a new main to open a new stage. 2 mains running at the same time, so that 2 stages run at the same time.
        Main main = new Main();
        // new stage
        Stage primaryStage = new Stage();
        // Sets the stage
        theStage = primaryStage;
        try {
            // readdies the stage
            main.start(theStage);
            // Defines new root and the fxml document for the root
            Parent askRoot = FXMLLoader.load(ControllerAsk.class.getResource("askSample.fxml"));
            // Creates a scene from the root
            Scene scene2 = new Scene(askRoot);
            // Puts the scene in the stage
            primaryStage.setScene(scene2);
            // Loads the stage
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
