package projekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MineSweeperApp extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Mine.fxml"))));
        primaryStage.show();
    }

}
