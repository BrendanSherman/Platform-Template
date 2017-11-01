import org.newdawn.slick.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.geom.Rectangle;

import java.awt.*;
import java.awt.Graphics;
import java.util.ArrayList;

//Box class
public class Box {
    private String url;
    protected int x;
    protected int y;
    public myRectangle topRectangle;
    public myLine rightLine;
    public myLine leftLine;
    public myRectangle bottomRectangle;
    public ArrayList<Collisions> lines;



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
        lines = new ArrayList(4);
        topRectangle = new myRectangle(x, y, 100, 1);
        leftLine = new myLine(x, y-10, x, y + 110);
        rightLine = new myLine(x + 100, y-10, x + 100, y + 110);
        bottomRectangle = new myRectangle(x+10, y+55, 80, 10);

        lines.add(topRectangle);
        lines.add(leftLine);
        lines.add(rightLine);
        lines.add(bottomRectangle);
    }

    public void bottomCollision(Mario mario) throws SlickException{}; //to be overrided

    
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

    public ArrayList getLines(){
        return lines;
    }

}