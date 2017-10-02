import org.newdawn.slick.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.geom.Rectangle;

import java.awt.*;
import java.awt.Graphics;

//Box class
public class Box {
    private String url;
    private int x;
    private int y;
    public Line topLine;
    public Line rightLine;
    public Line leftLine;
    public Line bottomLine;
    public Box(String url, int x, int y) throws SlickException{ //Sets variables, calls draw method
       this.url = url;
       this.x = x;
       this.y = y;
       this.draw();
       this.drawLines();
    }

    public void draw() throws SlickException{ //creates new image class with url, and draws it at x and y
        Image boxImage = new Image(url);
        boxImage.draw(x, y);
    }

    public void drawLines() throws SlickException{ //draws lines to check for collisions
        topLine = new Line(x, y, x+ 128, y);
        leftLine = new Line(x, y, x, y+128);
        rightLine = new Line(x+128, y, x+128, y+128);
        bottomLine = new Line(x, y+128, x+128, y+128);
    }

    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}