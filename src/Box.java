import org.newdawn.slick.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.*;

import java.awt.*;
import java.awt.Graphics;

//Box class
public class Box {
    private String url;
    protected int x;
    protected int y;
    public Line topLine;
    public Line rightLine;
    public Line leftLine;
    public Line bottomLine;
    public Line[] lines;


    public Box(String url, int x, int y) throws SlickException{ //Sets variables, calls draw method
       this.url = url;
       this.x = x;
       this.y = y;
       this.draw();
       this.drawLines();
    }

    public Box(int x, int y) throws SlickException{
        this.x = x;
        this.y = y;
        this.drawLines();
    }

    public void draw() throws SlickException{ //creates new image class with url, and draws it at x and y
        Image boxImage = new Image(url);
        boxImage.draw(x, y);
    }

    public void drawLines() throws SlickException { //draws lines to check for collisions
        lines = new Line[4];
        topLine = new Line(x, y, x + 128, y);
        leftLine = new Line(x, y, x, y + 128);
        rightLine = new Line(x + 128, y, x + 128, y + 128);
        bottomLine = new Line(x, y + 90, x + 100, y + 90);
        lines[0] = topLine;
        lines[1] = leftLine;
        lines[2] = rightLine;
        lines[3] = bottomLine;
    }

    public void bottomCollision(Mario mario){};

    
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

    public Line[] getLines(){
        return lines;
    }

}