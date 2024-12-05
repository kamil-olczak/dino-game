package Frame;

import Entity.Obstacle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SetObstacles implements Runnable{
    private int xPos, xPosR, yPos, yPos1, yPos2, yPosR, gamePanelWidth, switchCase, objectIndex, objectIndex1, objectIndex2, objectIndexR, allObjectWidth ,oneObjectWidth;
    private Obstacle checkedObstacle, checkedObstacle1, checkedObstacle2, checkedObstacleR;
    private List<Obstacle> oList = new ArrayList<>();
    private GamePanel gamePanel;


    public SetObstacles (GamePanel gamePanel, int gamePanelWidth){
        this.gamePanel = gamePanel;
        this.gamePanelWidth = gamePanelWidth;

        switchCase = 0;
        objectIndex = (int)(Math.random()*5);
        objectIndex1 = (int)(Math.random()*5);
        objectIndex2 = (int)(Math.random()*5);
        objectIndexR = (int)(Math.random()*3);
        xPos = gamePanelWidth + 70;
        xPosR = gamePanelWidth + 70;


        Obstacle obstacle0 = new Obstacle("resources/Obstacle.png", 2);
        Obstacle obstacle1 = new Obstacle("resources/Obstacle.png", 3);
        Obstacle obstacle3 = new Obstacle("resources/Obstacle0.png", 3);
        Obstacle obstacle2 = new Obstacle("resources/Obstacle.png", 4);
        Obstacle obstacle4 = new Obstacle("resources/Obstacle0.png", 4);
        oList.add(obstacle0);
        oList.add(obstacle1);
        oList.add(obstacle3);
        oList.add(obstacle2);
        oList.add(obstacle4);

        yPos = 244 - oList.get(objectIndex).getHeight() + (int)(Math.random()*4);
        yPosR = 244 - oList.get(objectIndexR).getHeight() + (int)(Math.random()*4);
        checkedObstacle = oList.get(objectIndex);
        checkedObstacle1 = oList.get(objectIndex1);
        checkedObstacle2 = oList.get(objectIndex2);
        checkedObstacleR = oList.get(objectIndexR);

    }


    public void draw(Graphics g) {
        switch (switchCase) {
            case 0 -> {
                allObjectWidth = oList.get(objectIndex).getWidth();
                checkedObstacle = oList.get(objectIndex);
                checkedObstacle.setHitBoxArea(xPos + (5 * oList.get(objectIndex).getPixelScaling()) , yPos +2, oList.get(objectIndex).getWidth() - (9 * oList.get(objectIndex).getPixelScaling()), (oList.get(objectIndex).getHeight()));
                g.drawImage(
                        oList.get(objectIndex).getObstacleImage(),
                        xPos,
                        yPos,
                        allObjectWidth,
                        oList.get(objectIndex).getHeight(),
                        gamePanel);

            }
            case 1 -> {
                allObjectWidth = oList.get(objectIndex).getWidth() + oList.get(objectIndex1).getWidth();
                checkedObstacle = oList.get(objectIndex);
                checkedObstacle.setHitBoxArea(xPos + (5 * checkedObstacle.getPixelScaling()) , yPos +2, checkedObstacle.getWidth() - (9 * checkedObstacle.getPixelScaling()), (checkedObstacle.getHeight()));
                checkedObstacle1 = oList.get(objectIndex1);
                checkedObstacle1.setHitBoxArea((xPos + checkedObstacle.getWidth()) + (5 * checkedObstacle1.getPixelScaling()), yPos1 +2, checkedObstacle1.getWidth() - (9 * checkedObstacle1.getPixelScaling()), (checkedObstacle1.getHeight()));

                g.drawImage(
                        oList.get(objectIndex).getObstacleImage(),
                        xPos,
                        yPos,
                        oList.get(objectIndex).getWidth(),
                        oList.get(objectIndex).getHeight(),
                        gamePanel);
                g.drawImage(
                        oList.get(objectIndex1).getObstacleImage(),
                        xPos + oList.get(objectIndex).getWidth(),
                        yPos1,
                        oList.get(objectIndex1).getWidth(),
                        oList.get(objectIndex1).getHeight(),
                        gamePanel);
            }
            case 2 -> {
                allObjectWidth = oList.get(objectIndex).getWidth() + oList.get(objectIndex1).getWidth() + oList.get(objectIndex2).getWidth() - 20;
                checkedObstacle = oList.get(objectIndex);
                checkedObstacle.setHitBoxArea(xPos + (5 * checkedObstacle.getPixelScaling()) , yPos +2, checkedObstacle.getWidth() - (9 * checkedObstacle.getPixelScaling()), (checkedObstacle.getHeight()));
                checkedObstacle1 = oList.get(objectIndex1);
                checkedObstacle1.setHitBoxArea((xPos + oList.get(objectIndex).getWidth() - 10) + (5 * checkedObstacle1.getPixelScaling()), yPos1 +2, checkedObstacle1.getWidth() - (9 * checkedObstacle1.getPixelScaling()), (checkedObstacle1.getHeight()));
                checkedObstacle2 = oList.get(objectIndex2);
                checkedObstacle2.setHitBoxArea((xPos + oList.get(objectIndex).getWidth() + oList.get(objectIndex1).getWidth() - 10) + (5 * checkedObstacle2.getPixelScaling()), yPos2 +2, checkedObstacle2.getWidth() - (9 * checkedObstacle2.getPixelScaling()), (checkedObstacle2.getHeight()));
                g.drawImage(
                        oList.get(objectIndex).getObstacleImage(),
                        xPos,
                        yPos,
                        oList.get(objectIndex).getWidth(),
                        oList.get(objectIndex).getHeight(),
                        gamePanel);
                g.drawImage(
                        oList.get(objectIndex1).getObstacleImage(),
                        xPos + oList.get(objectIndex).getWidth() - 10,
                        yPos1,
                        oList.get(objectIndex1).getWidth(),
                        oList.get(objectIndex1).getHeight(),
                        gamePanel);
                g.drawImage(
                        oList.get(objectIndex2).getObstacleImage(),
                        xPos + oList.get(objectIndex).getWidth() + oList.get(objectIndex1).getWidth() - 10,
                        yPos2,
                        oList.get(objectIndex2).getWidth(),
                        oList.get(objectIndex2).getHeight(),
                        gamePanel);


            }
        }
    }

    public void drawOne(Graphics g){
        oneObjectWidth = oList.get(objectIndexR).getWidth();
        checkedObstacleR = oList.get(objectIndexR);
        checkedObstacleR.setHitBoxArea(xPosR + (5 * checkedObstacleR.getPixelScaling()), yPosR +2, checkedObstacleR.getWidth() - (9 * checkedObstacleR.getPixelScaling()), checkedObstacleR.getHeight());
        g.drawImage(
                oList.get(objectIndexR).getObstacleImage(),
                xPosR,
                yPosR,
                oneObjectWidth,
                oList.get(objectIndexR).getHeight(),
                gamePanel);

    }
    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            if(gamePanel.getFpsCounter() > 150)
                updateO();

            if  (gamePanel.getFpsCounter() > 250 &&
                !gamePanel.isStartRandom() &&
                (int)(Math.random()*100) == 1 &&
                xPos < 1350 &&
                (xPosR - xPos > 450 && xPosR < xPos + 950)) {
                    gamePanel.setStartRandom(true);
            }
            if(gamePanel.isStartRandom())
                updateOne();
            try {
                Thread.sleep(gamePanel.getThreadSleepTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public void updateO(){
                xPos -= gamePanel.getDecrementedValue();
                if(xPos < -allObjectWidth){
                    xPos = gamePanelWidth + 70;
                    switchCase = (int)(Math.random()*3);
                    objectIndex = (int)(Math.random()*5);
                    objectIndex1 = (int)(Math.random()*5);
                    objectIndex2 = (int)(Math.random()*5);

                    if (switchCase == 2){
                        objectIndex = objectIndex - 1;
                        objectIndex2 = objectIndex2 - 2;
                        if (objectIndex < 0 && objectIndex2 < 0){
                            objectIndex = objectIndex * -1;
                            objectIndex2 = objectIndex2 * -1;
                        } else if (objectIndex < 0){
                            objectIndex = objectIndex * -1;
                        } else if (objectIndex2 < 0) {
                            objectIndex2 = objectIndex2 * -1;
                        }
                    }
                    yPos = 244 - oList.get(objectIndex).getHeight() + (int)(Math.random()*4);
                    yPos1 = 244 - oList.get(objectIndex1).getHeight() + (int)(Math.random()*4);
                    yPos2 = 244 - oList.get(objectIndex2).getHeight() + (int)(Math.random()*4);
                }
        }


//    }

    public void updateOne(){
            xPosR -= gamePanel.getDecrementedValue();
            if(xPosR < -oneObjectWidth){
                gamePanel.setStartRandom(false);
                xPosR = gamePanelWidth + 70;
                objectIndexR = (int)(Math.random()*3);
                yPosR = 244 - oList.get(objectIndexR).getHeight() + (int)(Math.random()*4);
            }
        }



    public int getxPos() {
         return xPos;
    }

    public int getxPosR() {
        return xPosR;
    }


    public int getAllObjectWidth() {
        return allObjectWidth;
    }

    public int getOneObjectWidth() {
        return oneObjectWidth;
    }

    public List<Obstacle> getoList() {
        return oList;
    }

    public int getSwitchCase() {
        return switchCase;
    }

    public int getyPos() {
        return yPos;
    }

    public Obstacle getCheckedObstacle() {
        return checkedObstacle;
    }

    public Obstacle getCheckedObstacle1() {
        return checkedObstacle1;
    }

    public Obstacle getCheckedObstacle2() {
        return checkedObstacle2;
    }

    public Obstacle getCheckedObstacleR() {
        return checkedObstacleR;
    }

    public void setStartVariables(){
        switchCase = 0;
        objectIndex = (int)(Math.random()*5);
        objectIndex1 = (int)(Math.random()*5);
        objectIndex2 = (int)(Math.random()*5);
        objectIndexR = (int)(Math.random()*3);

        oList = new ArrayList<>();

        Obstacle obstacle0 = new Obstacle("resources/Obstacle.png", 2);
        Obstacle obstacle1 = new Obstacle("resources/Obstacle.png", 3);
        Obstacle obstacle3 = new Obstacle("resources/Obstacle0.png", 3);
        Obstacle obstacle2 = new Obstacle("resources/Obstacle.png", 4);
        Obstacle obstacle4 = new Obstacle("resources/Obstacle0.png", 4);
        oList.add(obstacle0);
        oList.add(obstacle1);
        oList.add(obstacle3);
        oList.add(obstacle2);
        oList.add(obstacle4);

        xPos = gamePanelWidth + 70;
        xPosR = gamePanelWidth + 70;
        yPos = 244 - oList.get(objectIndex).getHeight() + (int)(Math.random()*4);
        yPos1 = 244 - oList.get(objectIndex1).getHeight() + (int)(Math.random()*4);
        yPos2 = 244 - oList.get(objectIndex2).getHeight() + (int)(Math.random()*4);
        yPosR = 244 - oList.get(objectIndexR).getHeight() + (int)(Math.random()*4);
        checkedObstacle = oList.get(objectIndex);
        checkedObstacle1 = oList.get(objectIndex1);
        checkedObstacle2 = oList.get(objectIndex2);
        checkedObstacleR = oList.get(objectIndexR);
    }


}

