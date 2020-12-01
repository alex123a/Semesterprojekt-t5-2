package sample.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import sample.domain.Game;
import sample.domain.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class Main extends Application {
    public static Game game;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        game = new Game();
        game.goRoom();
        Controller.roomExit = new ArrayList<>(Arrays.asList(Game.startDirections));
        launch(args);
    }



}
