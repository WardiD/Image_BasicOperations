package app.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        primaryStage.setTitle("Basic Operations on Images ");
        primaryStage.setScene(new Scene(root, 1600, 900));
        primaryStage.setResizable(false);



        //Slider bwSlider = new Slider(1,3,2);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
