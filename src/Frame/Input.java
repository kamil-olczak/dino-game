package Frame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {

    private boolean jumpKeyPressed = false, debugKeyPressed = false;
    private GamePanel gamePanel;

    public Input(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 32) {
            jumpKeyPressed = true;
        }
        if(e.getKeyCode() == 17){
            debugKeyPressed = true;
            System.out.println("xPos: " + gamePanel.getSetObstacles().getxPos() + "\txPosR: " + gamePanel.getSetObstacles().getxPosR());
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        jumpKeyPressed = false;
        debugKeyPressed = false;
    }

    public boolean isJumpKeyPressed() {
        return jumpKeyPressed;
    }

    public boolean isDebugKeyPressed() {
        return debugKeyPressed;
    }
}
