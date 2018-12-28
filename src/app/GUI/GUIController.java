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
    private Label imageNameLabel;

    @FXML
    private ImageView defaultImageViewer;

    @FXML
    private ImageView channelImageViewer;

    @FXML
    private ImageView grayImageViewer;

    @FXML
    private ImageView blackAndWhiteImageViewer;

    @FXML
    private ImageView modyfingImageViewer;

    @FXML
    private Slider bwSlider;

    public void toDefaultImage(){
        thisImage.setNewModyfingImage(thisImage.getDefaultImage());
        modyfingImageViewer.setImage(thisImage.showModifyingImage());
    }

    public void saveModifiedImageAction(){
        FileChooser imageFileChooser = makeFileChooser();

        File myImageFile = imageFileChooser.showSaveDialog(null);

        if (myImageFile != null) {
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(thisImage.showModifyingImage(),
                        null), "png", myImageFile);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void makeImageDarker(){
        thisImage.setNewModyfingImage(thisImage.makeDarker());
        modyfingImageViewer.setImage(thisImage.showModifyingImage());
    }

    public void makeImageBrighter(){
        thisImage.setNewModyfingImage(thisImage.makeBrighter());
        modyfingImageViewer.setImage(thisImage.showModifyingImage());
    }

    public void onbwSliderChanged(){
        int newValueOfSlider = Math.round((float)bwSlider.getValue());
        bwSlider.setValue(newValueOfSlider);
        thisImage.setNewBWImage(newValueOfSlider);
        blackAndWhiteImageViewer.setImage(thisImage.showBlackAndWhiteImage());
    }

    private FileChooser makeFileChooser(){
        FileChooser imageFileChooser = new FileChooser();
        imageFileChooser.setTitle("Choose destination of saving image");
        imageFileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG","*.jpg"),
                new FileChooser.ExtensionFilter("PNG","*.png")
        );
        return imageFileChooser;
    }

    public void saveBWImageAction(ActionEvent event){
        FileChooser imageFileChooser = makeFileChooser();

        File myImageFile = imageFileChooser.showSaveDialog(null);

        if (myImageFile != null) {
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(thisImage.showBlackAndWhiteImage(),
                        null), "png", myImageFile);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void saveGrayscaleImageAction(ActionEvent event){
        FileChooser imageFileChooser = makeFileChooser();

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

    private void checkIfImageLoaded(){
        if(isImageLoaded){
            channelImageViewer.setImage(null);

            thisImage = null;
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

        checkIfImageLoaded();

        File myImageFile = imageFileChooser.showOpenDialog(null);
        if(myImageFile != null ){

            isImageLoaded = true;

            bwSlider.setValue(2.0);
            imageNameLabel.setContentDisplay(ContentDisplay.CENTER);
            imageNameLabel.setText(myImageFile.getName());

            thisImage = new MyImage(new Image(myImageFile.toURI().toString()));
            if(thisImage.isColoredImage())
                bwSlider.setDisable(false);
            else
                bwSlider.setDisable(true);
            defaultImageViewer.setImage(thisImage.showDefaultImage());
            grayImageViewer.setImage(thisImage.showGrayImage());
            blackAndWhiteImageViewer.setImage(thisImage.showBlackAndWhiteImage());
            modyfingImageViewer.setImage(thisImage.showModifyingImage());
        } else {
            imageNameLabel.setText("Something goes wrong...");
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
