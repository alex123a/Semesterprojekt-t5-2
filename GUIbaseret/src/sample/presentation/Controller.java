package sample.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller {

    public static String background = "file:src/sample/presentation/pictures/RoadBuild.png";

    @FXML
    private ImageView backgroundRoom;

    @FXML
    private Button changePic;

    public void changeBackground(ActionEvent event) {
        backgroundRoom.setImage(new Image(background));
        System.out.println("Hej");
    }

}
