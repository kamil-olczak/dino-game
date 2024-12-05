package Frame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Background {
    private BufferedImage backgroundImage;
    private int xPos;
    private GamePanel gamePanel;


    public Background(GamePanel gamePanel){
        File file = new File("resources/background.png");
        try{
            backgroundImage = ImageIO.read(file);
        } catch (IOException e){
            e.printStackTrace();
        }

        this.gamePanel = gamePanel;
        this.xPos = 0;
    }

    public void draw(Graphics g){
        g.drawImage(backgroundImage,xPos,0,gamePanel);
    }

    public void updateB(){
        xPos -= gamePanel.getDecrementedValue();
        xPos = xPos <= -backgroundImage.getWidth() + 1280 ? 0 : xPos;
    }

    public void setStartVariables(){
        xPos = 0;
    }

}
