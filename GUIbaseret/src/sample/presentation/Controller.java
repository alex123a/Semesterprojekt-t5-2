package sample.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class Controller {

    public static String background;

    @FXML
    private ImageView backgroundRoom;

    @FXML
    private Button changePic;

    @FXML
    private ImageView player;

    @FXML
    private Pane stackPane;

    public void changeBackground() {
        backgroundRoom.setImage(new Image("file:" + background));
        System.out.println("Hej");
    }

    public void movePlayer(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.W) {
            player.setTranslateY(player.getTranslateY() - 4);
            System.out.println(player.getTranslateY());
        } else if (keyEvent.getCode() == KeyCode.S) {
            player.setTranslateY(player.getTranslateY() + 4);
            System.out.println(player.getTranslateY());
        } else if (keyEvent.getCode() == KeyCode.A) {
            player.setTranslateX(player.getTranslateX() - 4);
            System.out.println(player.getTranslateX());
        } else if (keyEvent.getCode() == KeyCode.D) {
            player.setTranslateX(player.getTranslateX() + 4);
            System.out.println(player.getTranslateX());
        } else if (keyEvent.getCode() == KeyCode.SPACE) {
            
        }
    }
}
