package Entity;

import java.awt.*;

public class HitBoxArea extends Rectangle {

    public HitBoxArea(int x, int y, int width, int height){
        super(x,y,width,height);
    }

    public int getXDiagonal(){
        return x + width;
    }

    public int getYDiagonal(){return y + height;
    }

    public void setHitBoxArea(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }




}
