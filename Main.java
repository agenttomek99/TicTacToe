/**
 * @author Tomasz Kulnianin
 * before use please read instructions!
 */
package tictactoepackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainScreenTransparent.fxml"));
        primaryStage.setTitle("Unbeatable Tic Tac Toe");
        primaryStage.setScene(new Scene(root,625,400));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
