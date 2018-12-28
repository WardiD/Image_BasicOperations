package app.GUI;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MyImage {
    private Image myImage;
    private BufferedImage defaultImage;
    private BufferedImage toModifyImage;
    private Image modyfingImage;
    private Image redChannelImage;
    private Image blueChannelImage;
    private Image greenChannelImage;
    private Image grayImage;
    private Image blackAndWhiteImage;
    private int width;
    private int height;

    public void setNewBWImage(int slider){
        this.blackAndWhiteImage = blackandwhite(slider);
    }
    public void setNewModyfingImage(Image newImage){ this.modyfingImage = newImage; }

    public Image getDefaultImage(){
        return this.myImage;
    }

    public MyImage(Image newImage){
        this.myImage = newImage;
        this.width = (int)this.myImage.getWidth();
        this.height = (int)this.myImage.getHeight();
        this.defaultImage = SwingFXUtils.fromFXImage(myImage, null);
        this.redChannelImage = redChannel();
        this.blueChannelImage = blueChannel();
        this.greenChannelImage = greenChannel();
        this.grayImage = gray();
        if(  isColored(this.defaultImage)){
            this.blackAndWhiteImage = blackandwhite(2);
        } else {
            this.blackAndWhiteImage = myImage;
        }
        this.modyfingImage = newImage;
        //System.out.println("width = "+this.width+", height = "+this.height);
    }

    public Image showDefaultImage(){
        return myImage;
    }
    public Image showRedImage(){
        return redChannelImage;
    }
    public Image showBlueImage(){
        return blueChannelImage;
    }
    public Image showGreenImage(){
        return greenChannelImage;
    }
    public Image showGrayImage(){
        return grayImage;
    }
    public Image showBlackAndWhiteImage(){ return blackAndWhiteImage; }
    public Image showModifyingImage(){
        return modyfingImage;
    }


    public Image makeDarker(){
        this.toModifyImage = SwingFXUtils.fromFXImage(modyfingImage, null);

        for (int column = 0 ; column < this.width ; ++column ) {
            for (int row = 0; row < this.height; ++row) {
               Color darkerColor = new Color(this.toModifyImage.getRGB(column, row));
                darkerColor = darkerColor.darker();
               this.toModifyImage.setRGB(column,row, darkerColor.getRGB());
            }
        }

        return  SwingFXUtils.toFXImage(toModifyImage, null);
    }

    public Image makeBrighter(){
        this.toModifyImage = SwingFXUtils.fromFXImage(modyfingImage, null);

        for (int column = 0 ; column < this.width ; ++column ) {
            for (int row = 0; row < this.height; ++row) {
                Color brighterColor = new Color(this.toModifyImage.getRGB(column, row));
                brighterColor = brighterColor.brighter();
                this.toModifyImage.setRGB(column,row, brighterColor.getRGB());
            }
        }

        return  SwingFXUtils.toFXImage(toModifyImage, null);
    }
    public boolean isColoredImage(){
        return isColored(this.defaultImage);
    }
    private boolean isColored(BufferedImage image){
        long whiteColor = Color.WHITE.getRGB();
        long blackColor = Color.BLACK.getRGB();

        for (int column = 1 ; column < image.getWidth() ; ++column ) {
            for (int row = 1; row < image.getHeight() ; ++row) {
                long thisColor = image.getRGB(column,row);

                if( thisColor != whiteColor ){
                    if(thisColor != blackColor) {
                        return true;
                    }
                }
            }
        }
        return false;

    }


    public Image blackandwhite(int slider){
        Color colorPointer;
        switch (slider){
            case 1:
                colorPointer = new Color(Color.LIGHT_GRAY.getRGB());
                break;
            case 3:
                colorPointer = new Color(Color.DARK_GRAY.getRGB());
                break;
            default : //2
                colorPointer = new Color(Color.GRAY.getRGB());
                break;
        }
        this.toModifyImage = SwingFXUtils.fromFXImage(myImage, null);
        this.toModifyImage = toGray(toModifyImage);
        for (int column = 0; column < this.width; ++column) {
            for (int row = 0; row < this.height; ++row) {
                if (this.toModifyImage.getRGB(column, row) < colorPointer.getRGB()) {
                    toModifyImage.setRGB(column, row, Color.WHITE.getRGB());
                } else {
                    toModifyImage.setRGB(column, row, Color.BLACK.getRGB());
                }
            }
        }
        return SwingFXUtils.toFXImage(toModifyImage, null);

    }


    public BufferedImage toGray(BufferedImage image){
        for (int column = 0 ; column < this.width ; ++column ){
            for ( int row = 0 ; row < this.height ; ++row ){
                int pixel = image.getRGB(column,row);

                int alpha = (pixel>>24)&0xff;
                int red = (pixel>>16)&0xff;
                int green = (pixel>>8)&0xff;
                int blue = pixel&0xff;

                int avg = (red+green+blue)/3;

                pixel = (avg<<24) | (avg<<16) | (avg<<8) | avg;
                image.setRGB( column, row,pixel );
            }
        }
        return image;
    }

    public Image gray(){
        this.toModifyImage = SwingFXUtils.fromFXImage(myImage, null);
        return  SwingFXUtils.toFXImage(toGray(this.toModifyImage), null);
    }


    public Image redChannel(){
        this.toModifyImage = SwingFXUtils.fromFXImage(myImage, null);
        for (int column = 0 ; column < this.width ; ++column ){
            for ( int row = 0 ; row < this.height ; ++row ){
                Color color = new Color(this.defaultImage.getRGB(column, row));
                int red = color.getRed();
                toModifyImage.setRGB(column, row,new Color(red,0,0).getRGB() );
            }
        }
        return  SwingFXUtils.toFXImage(toModifyImage, null);
    }

    public Image blueChannel(){
        this.toModifyImage = SwingFXUtils.fromFXImage(myImage, null);
        for (int column = 0 ; column < this.width ; ++column ){
            for ( int row = 0 ; row < this.height ; ++row ){
                Color color = new Color(this.defaultImage.getRGB(column, row));
                int blue = color.getBlue();
                toModifyImage.setRGB(column, row,new Color(0,0,blue).getRGB() );
            }
        }
        return  SwingFXUtils.toFXImage(toModifyImage, null);
    }

    public Image greenChannel(){
        this.toModifyImage = SwingFXUtils.fromFXImage(myImage, null);
        for (int column = 0 ; column < this.width ; ++column )
            for (int row = 0; row < this.height; ++row) {
                Color color = new Color(this.defaultImage.getRGB(column, row));
                int green = color.getGreen();
                toModifyImage.setRGB(column, row, new Color(0, green, 0).getRGB());
            }
        return  SwingFXUtils.toFXImage(toModifyImage, null);
    }
}
