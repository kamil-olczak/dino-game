package Frame;

import javax.swing.*;

public class GameFrame extends JFrame {
    private GamePanel gamePanel;
    private Thread mainThread;

    public GameFrame() {

        gamePanel = new GamePanel(this);
        this.add(gamePanel);

        mainThread = new Thread(gamePanel);
        mainThread.start();


        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }


    public Thread getMainThread() {
        return mainThread;
    }

}