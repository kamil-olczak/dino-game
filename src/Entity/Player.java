package Entity;

import Frame.GamePanel;
import Frame.Input;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player implements Runnable{


    private Input input;
    private GamePanel gamePanel;
    private int playerStartPosX, playerStartPosY,  counter;
    private float playerJumpSpeedInc;
    private final int  pixelScaling = 2;
    private boolean jumpMax;
    private BufferedImage renderedImage, dino1, dino0;
    private HitBoxArea hitBoxArea;



    public Player( GamePanel gamePanel, Input input){
        File file = new File("resources/dino0.png");
        File file1 = new File("resources/dino1.png");
        try{
            dino0 = ImageIO.read(file);
            dino1 = ImageIO.read(file1);
        } catch (IOException e){
            e.printStackTrace();
        }

        this.gamePanel = gamePanel;
        this.input = input;
        playerStartPosX = 120;
        playerStartPosY = 180;
        playerJumpSpeedInc = 6.2f;
        counter = 0;
        renderedImage = dino0;
        hitBoxArea = new HitBoxArea(playerStartPosX + pixelScaling*8, playerStartPosY, pixelScaling*7, pixelScaling*32);

    }

    @Override
    public void run() {

        while(!Thread.currentThread().isInterrupted()) {

            if (counter == 25)
                renderedImage = dino0;
            else if(counter == 50){
                renderedImage = dino1;
                counter = 0;
            }

            this.setHitBoxArea(playerStartPosX + pixelScaling*8, playerStartPosY, pixelScaling*7, pixelScaling*31);
            counter++;
            try {
                Thread.sleep(gamePanel.getThreadSleepTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void draw(Graphics g){
        g.drawImage(
                renderedImage,
                playerStartPosX,
                playerStartPosY,
                pixelScaling * renderedImage.getWidth(),
                pixelScaling * renderedImage.getHeight(),
                gamePanel);

    }


    public void update(){
        if (input.isJumpKeyPressed()) {
            if (playerStartPosY > 20 && !jumpMax) {
                if(playerStartPosY < 60 && playerStartPosY > 40)
                    playerStartPosY -= (playerJumpSpeedInc - 3.65);
                else if (playerStartPosY <= 40)
                    playerStartPosY -= (playerJumpSpeedInc - 4.55);
                else
                    playerStartPosY -= playerJumpSpeedInc;
            } else if (playerStartPosY <= 180) {
                jumpMax = true;
                if(playerStartPosY < 60 && playerStartPosY > 40)
                    playerStartPosY += (playerJumpSpeedInc - 3.1);
                else if (playerStartPosY <= 40)
                    playerStartPosY += (playerJumpSpeedInc - 4.1);
                else
                    playerStartPosY += playerJumpSpeedInc;
            } else {
                playerStartPosY = 200;
                jumpMax = false;
            }
        } else if (playerStartPosY != 180) {
            if (playerStartPosY > 40 && !jumpMax) {
                if(playerStartPosY < 70 && playerStartPosY > 50)
                    playerStartPosY -= (playerJumpSpeedInc - 3.05);
                else if (playerStartPosY <= 50)
                    playerStartPosY -= (playerJumpSpeedInc - 4.05);
                else
                    playerStartPosY -= playerJumpSpeedInc;
            } else if (playerStartPosY <= 180) {
                jumpMax = true;
                if(playerStartPosY < 70 && playerStartPosY > 50)
                    playerStartPosY += (playerJumpSpeedInc - 3.05);
                else if (playerStartPosY <= 50)
                    playerStartPosY += (playerJumpSpeedInc - 4.05);
                else
                    playerStartPosY += playerJumpSpeedInc;
            } else {
                playerStartPosY = 180;
                jumpMax = false;
            }
        } else{
            jumpMax = false;
        }
    }

    public HitBoxArea getHitBoxArea() {
        return hitBoxArea;
    }


    public void setHitBoxArea(int x, int y, int width, int height) {
        hitBoxArea.setHitBoxArea(x,y,width,height);
    }

    public void setPlayerJumpSpeedInc(float playerJumpSpeedInc) {
        this.playerJumpSpeedInc = playerJumpSpeedInc;
    }

    public float getPlayerJumpSpeedInc() {
        return playerJumpSpeedInc;
    }

    public void setStartVariables(){
        playerStartPosX = 120;
        playerStartPosY = 180;
        playerJumpSpeedInc = 6.2f;
        counter = 0;
        renderedImage = dino0;
        hitBoxArea.setHitBoxArea(playerStartPosX + pixelScaling*8, playerStartPosY, pixelScaling*7, pixelScaling*32);
    }
}
