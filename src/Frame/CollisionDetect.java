package Frame;

import Entity.HitBoxArea;
import Entity.Obstacle;
import Entity.Player;

import java.util.List;


public class CollisionDetect {
    private Player player;
    private GamePanel gamePanel;
    private List<Obstacle> oList;
    private SetObstacles setObstacles;

    public CollisionDetect(Player player, GamePanel gamePanel, List<Obstacle> oList, SetObstacles setObstacles) {
        this.player = player;
        this.gamePanel = gamePanel;
        this.oList = oList;
        this.setObstacles = setObstacles;
    }

    public Boolean checkCollision() {

        switch (setObstacles.getSwitchCase()) {
            case 0 -> {
                HitBoxArea playersHitbox = player.getHitBoxArea();
                HitBoxArea obstacleHitbox = setObstacles.getCheckedObstacle().getHitBoxArea();
                HitBoxArea obstacleHitboxR = setObstacles.getCheckedObstacleR().getHitBoxArea();
                if (playersHitbox.intersects(obstacleHitbox)) {
                    return true;
                } else if (gamePanel.isStartRandom() && playersHitbox.intersects(obstacleHitboxR)) {
                    return true;
                } else {
                    return false;
                }

            }
            case 1 -> {
                HitBoxArea playersHitbox = player.getHitBoxArea();
                HitBoxArea obstacleHitbox = setObstacles.getCheckedObstacle().getHitBoxArea();
                HitBoxArea obstacleHitbox1 = setObstacles.getCheckedObstacle1().getHitBoxArea();
                HitBoxArea obstacleHitboxR = setObstacles.getCheckedObstacleR().getHitBoxArea();
                if (playersHitbox.intersects(obstacleHitbox) || playersHitbox.intersects(obstacleHitbox1)) {
                    return true;
                } else if (gamePanel.isStartRandom() && playersHitbox.intersects(obstacleHitboxR)) {
                    return true;
                } else {
                    return false;
                }
            }
            case 2 -> {
                HitBoxArea playersHitbox = player.getHitBoxArea();
                HitBoxArea obstacleHitbox = setObstacles.getCheckedObstacle().getHitBoxArea();
                HitBoxArea obstacleHitbox1 = setObstacles.getCheckedObstacle1().getHitBoxArea();
                HitBoxArea obstacleHitbox2 = setObstacles.getCheckedObstacle2().getHitBoxArea();
                HitBoxArea obstacleHitboxR = setObstacles.getCheckedObstacleR().getHitBoxArea();
                if (playersHitbox.intersects(obstacleHitbox) || playersHitbox.intersects(obstacleHitbox1) || playersHitbox.intersects(obstacleHitbox2)) {
                    return true;
                } else if (gamePanel.isStartRandom() && playersHitbox.intersects(obstacleHitboxR)) {
                    return true;
                } else {
                    return false;
                }

            }
        }
        return false;
    }


}