package sample.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller {

    public static String background;

    @FXML
    private ImageView backgroundRoom;

    @FXML
    private Button changePic;

    public void changeBackground() {
        backgroundRoom.setImage(new Image("file:" + background));
        System.out.println("Hej");
    }

}
