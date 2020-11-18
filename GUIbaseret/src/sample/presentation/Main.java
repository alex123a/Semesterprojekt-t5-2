package sample.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.domain.Game;

import java.util.ArrayList;
import java.util.Arrays;

public class Main extends Application {
    public static Game game;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        game = new Game();
        game.goRoom();
        Controller.roomExit = new ArrayList<>(Arrays.asList(Game.startDirections));
        launch(args);
    }
}
