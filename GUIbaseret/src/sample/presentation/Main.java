package sample.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.domain.Game;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends Application {
    public static Game game;
    private String path = "src/sample/presentation/pictures/video/Introduction.mp4";
    //src/sample/presentation/pictures/video/Introduction.mp4
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Plastic Road");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("file:src/sample/presentation/pictures/TrafficCone.png"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        game = new Game();
        game.goRoom();
        Controller.roomExit = new ArrayList<>(Arrays.asList(Game.startDirections));
        launch(args);
    }

}
