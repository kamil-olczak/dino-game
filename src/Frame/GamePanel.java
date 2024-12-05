package Frame;

import Entity.Player;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class GamePanel extends JPanel implements Runnable{
    private final int screenWidth = 1280, screenHeight = 300, decrementedValue = 7;
    private int score, highscore = 0, fps = 120;
    private int threadSleepTime;
    private long fpsCounter;
    private boolean startRandom = false, stopGame = false, threadStarted = false;


    private Input input = new Input(this);
    private Player player = new Player(this, input);
    private SetObstacles setObstacles = new SetObstacles(this, screenWidth);
    private Thread playerThread = new Thread(player);
    private Thread setObstaclesThread = new Thread(setObstacles);

    private Background background = new Background(this);
    private CollisionDetect collisionDetect = new CollisionDetect(player, this, setObstacles.getoList(), setObstacles);
    private GameFrame gameFrame;
    private StringBuilder stringBuilder, highscoreStringBuilder;




    public GamePanel(GameFrame gameFrame){

        this.addKeyListener(input);
        this.gameFrame = gameFrame;

        fpsCounter = 0;
        stringBuilder = new StringBuilder();
        getHighscoreFromFile();
        threadSleepTime = 1000/fps;
        highscoreStringBuilder = new StringBuilder();
        highscoreStringBuilder.append(highscore);

        playerThread.start();

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));

    }

    @Override
    public void run() {
            while (!Thread.currentThread().isInterrupted()) {

                updateAll();

                repaint();

                if(fpsCounter % 10 == 0){
                    score++;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(score);
                }
                if (fpsCounter == 750 || fpsCounter == 1200 || fpsCounter == 1500 || fpsCounter == 2200 || fpsCounter == 3000){
                    fps += 10;
                    threadSleepTime = 1000/fps;
                    if(fpsCounter != 3000)
                        player.setPlayerJumpSpeedInc(player.getPlayerJumpSpeedInc() - 0.05f);
                    else
                        player.setPlayerJumpSpeedInc(player.getPlayerJumpSpeedInc() - 0.04f);
                }

                if(!threadStarted && fpsCounter == 150) {
                    setObstaclesThread.start();
                    threadStarted = true;
                }

                fpsCounter++;
                stopGame = collisionDetect.checkCollision();
                checkIfLose();

                try {
                    Thread.sleep(threadSleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }



    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        background.draw(g);
        player.draw(g);

        g.drawString("Score: " + stringBuilder.toString(), 10, 20);
        g.drawString("Highscore: " + highscoreStringBuilder.toString(), 150 , 20);
        if(fpsCounter < 400){
            g.drawString("Press SPACE to JUMP", 700 , 20);
            g.drawString("Press and HOLD SPACE to LONGJUMP", 700, 40);
        }


        if (fpsCounter > 150 && setObstacles.getxPos() > -setObstacles.getAllObjectWidth()){
            setObstacles.draw(g);
        }
        if (startRandom && setObstacles.getxPosR() > -setObstacles.getOneObjectWidth()){
            setObstacles.drawOne(g);
        }

        if(stopGame){
            g.drawString("Game Over", screenWidth/2 -55, 120);
            if(score > highscore){
                g.drawString("You have set a new highscore!", screenWidth/2 -120, 140);
            }
            g.drawString("Press SPACE to restart", screenWidth/2 -90, 160);
        }
    }

    public void updateAll() {

        player.update();
        background.updateB();
        collisionDetect.checkCollision();
    }




    public void getHighscoreFromFile(){

        try {
            FileReader fr = new FileReader("resources/highscore.txt");
            BufferedReader br = new BufferedReader(fr);
            highscore = Integer.parseInt(br.readLine());
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveHighscore(){

        try {
            FileWriter fileWriter = new FileWriter("resources/highscore.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkIfLose(){
        while (stopGame){
            if (score > highscore) {
                saveHighscore();
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(!playerThread.isAlive()) {
                playerThread.interrupt();
            }
            if(!setObstaclesThread.isAlive()){
                setObstaclesThread.interrupt();
            }
            if(!gameFrame.getMainThread().isAlive()){
                gameFrame.getMainThread().interrupt();
            }


            if(input.isJumpKeyPressed()) {
                stopGame = false;

                fpsCounter = 0;
                fps = 120;
                threadSleepTime = 1000/fps;
                score = 0;
                stringBuilder = new StringBuilder();
                getHighscoreFromFile();
                threadSleepTime = 1000/fps;
                highscoreStringBuilder = new StringBuilder();
                highscoreStringBuilder.append(highscore);
                startRandom = false;

                setObstacles.setStartVariables();
                player.setStartVariables();
                background.setStartVariables();

            }
        }
    }

    public int getThreadSleepTime() {
        return threadSleepTime;
    }

    public long getFpsCounter() {
        return fpsCounter;
    }

    public void setStartRandom(boolean startRandom) {
        this.startRandom = startRandom;
    }

    public boolean isStartRandom() {
        return startRandom;
    }

    public int getDecrementedValue() {
        return decrementedValue;
    }

    public SetObstacles getSetObstacles() {
        return setObstacles;
    }
}




