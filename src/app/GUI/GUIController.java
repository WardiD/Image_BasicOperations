package app.GUI;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;


public class GUIController {

    
    @FXML
    private Button loadImageButton;

    @FXML
    private Label imageNameLabel;

    @FXML
    private ImageView imageDisplayer =  new ImageView();

    public void LoadImageButtonAction(ActionEvent event){

        FileChooser imageFileChooser = new FileChooser();
        imageFileChooser.setTitle("Choose image");

        imageFileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG","*.jpg"),
                new FileChooser.ExtensionFilter("PNG","*.png"),
                new FileChooser.ExtensionFilter("BMP","*.bmp")
            );

        File myImageFile = imageFileChooser.showOpenDialog(null);

        if(myImageFile != null ){
            imageNameLabel.setContentDisplay(ContentDisplay.CENTER);
            imageNameLabel.setText(myImageFile.getName());
            Image loadedImage = new Image(myImageFile.toURI().toString());
            //System.out.println(myImageFile.toURI().toString());
            imageDisplayer.setImage(loadedImage);
        } else {
            imageNameLabel.setText("Problem z wczytaniem pliku!!!");
        }


    }
}
