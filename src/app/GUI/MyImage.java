package app.GUI;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MyImage {
    private Image myImage;
    private BufferedImage defaultImage;
    private BufferedImage toModifyImage;
    private Image redChannelImage;
    private Image blueChannelImage;
    private Image greenChannelImage;
    private Image grayImage;
    private Image blackAndWhiteImage;
    private int width;
    private int height;


    public MyImage(Image newImage){
        this.myImage = newImage;
        this.width = (int)this.myImage.getWidth();
        this.height = (int)this.myImage.getHeight();
        this.defaultImage = SwingFXUtils.fromFXImage(myImage, null);
        this.redChannelImage = redChannel();
        this.blueChannelImage = blueChannel();
        this.greenChannelImage = greenChannel();
        this.grayImage = gray();
        this.blackAndWhiteImage = blackandwhite();
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
    public Image showBlackAndWhiteImage(){
        return blackAndWhiteImage;
    }


    public Image blackandwhite(){
        this.toModifyImage = SwingFXUtils.fromFXImage(myImage, null);
        BufferedImage bwImage = new BufferedImage(
                toModifyImage.getWidth(),toModifyImage.getHeight(),BufferedImage.TYPE_BYTE_BINARY);

        Graphics2D graphic = bwImage.createGraphics();
        graphic.drawImage(this.toModifyImage,0,0,Color.WHITE,null);

        return  SwingFXUtils.toFXImage(toModifyImage, null);
    }

    public Image gray(){
        this.toModifyImage = SwingFXUtils.fromFXImage(myImage, null);
        for (int column = 0 ; column < this.width ; ++column ){
            for ( int row = 0 ; row < this.height ; ++row ){
               int pixel = this.defaultImage.getRGB(column,row);

                int alpha = (pixel>>24)&0xff;
                int red = (pixel>>16)&0xff;
                int green = (pixel>>8)&0xff;
                int blue = pixel&0xff;

                int avg = (red+green+blue)/3;

                pixel = (avg<<24) | (avg<<16) | (avg<<8) | avg;
                toModifyImage.setRGB( column, row,pixel );
            }
        }
        return  SwingFXUtils.toFXImage(toModifyImage, null);
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
