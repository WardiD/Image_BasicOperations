package app.GUI;


import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class GUIController {
    private boolean isImageLoaded = false;
    public MyImage thisImage;


    @FXML
    private Button loadImageButton = new Button();

    @FXML
    private Label imageNameLabel = new Label();

    @FXML
    private ImageView defaultImageViewer =  new ImageView();

    @FXML
    private ImageView channelImageViewer =  new ImageView();

    @FXML
    private ImageView grayImageViewer =  new ImageView();

    @FXML
    private ImageView blackAndWhiteImageViewer =  new ImageView();

    @FXML
    private Button redChannelButton = new Button();

    @FXML
    private Button greenChannelButton = new Button();

    @FXML
    private Button blueChannelButton = new Button();

    @FXML
    private Button saveGrayImageButton = new Button();


    public void SaveGrayscaleImageAction(ActionEvent event){
        FileChooser imageFileChooser = new FileChooser();
        imageFileChooser.setTitle("Choose destination of saving image");
        imageFileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG","*.jpg"),
                new FileChooser.ExtensionFilter("PNG","*.png")
        );


        File myImageFile = imageFileChooser.showSaveDialog(null);

        if (myImageFile != null) {
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(thisImage.showGrayImage(),
                                null), "png", myImageFile);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }


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
            isImageLoaded = true;
            imageNameLabel.setContentDisplay(ContentDisplay.CENTER);
            imageNameLabel.setText(myImageFile.getName());

            thisImage = new MyImage(new Image(myImageFile.toURI().toString()));
            defaultImageViewer.setImage(thisImage.showDefaultImage());
            grayImageViewer.setImage(thisImage.showGrayImage());
            blackAndWhiteImageViewer.setImage(thisImage.showBlackAndWhiteImage());
        } else {
            imageNameLabel.setText("Problem z wczytaniem pliku!!!");
        }
    }
    private void showAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setContentText("Please, load an image!!!");
        alert.showAndWait();
    }
    public void redChannelButtonAction(ActionEvent event){
        if(isImageLoaded == false ){
            showAlert();
        } else {
            channelImageViewer.setImage(thisImage.showRedImage());
        }

    }

    public void greenChannelButtonAction(ActionEvent event){
        if(isImageLoaded == false ){
            showAlert();
        } else {
            channelImageViewer.setImage(thisImage.showGreenImage());
        }

    }

    public void blueChannelButtonAction(ActionEvent event){
        if(isImageLoaded == false ){
            showAlert();
        } else {
            channelImageViewer.setImage(thisImage.showBlueImage());
        }

    }


}
