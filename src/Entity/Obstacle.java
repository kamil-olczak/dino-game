package Entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Obstacle {
    private HitBoxArea hitBoxArea = new HitBoxArea(0,0,0,0);
    private BufferedImage obstacleImage;
    private int height, width, pixelScaling;


    public Obstacle(String path, int pixelScaling){
        File file = new File(path);
        try{
            obstacleImage = ImageIO.read(file);
        } catch (IOException e){
            e.printStackTrace();
        }
        this.pixelScaling = pixelScaling;


        height = obstacleImage.getHeight() * pixelScaling;
        width = obstacleImage.getWidth() * pixelScaling;

    }

    public BufferedImage getObstacleImage() {
        return obstacleImage;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public HitBoxArea getHitBoxArea() {
        return hitBoxArea;
    }

    public void setHitBoxArea(int x, int y, int width, int height) {
        hitBoxArea.setHitBoxArea(x,y,width,height);
    }

    public int getPixelScaling() {
        return pixelScaling;
    }
}
