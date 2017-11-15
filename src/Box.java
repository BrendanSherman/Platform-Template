import org.newdawn.slick.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.geom.Rectangle;

import java.awt.*;
import java.awt.Graphics;
import java.util.ArrayList;

//Box class
public class Box {
    boolean used;
    String type = "";
    String url = "";
    protected int x;
    protected int y;
    int moveStage = 0;
    public myRectangle topRectangle;
    public myLine rightLine;
    public myLine leftLine;
    public myRectangle bottomRectangle;
    public ArrayList<Collisions> lines;
    boolean animationEligible;



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

    public void Animate() throws SlickException{ //Box animation
        int oldY = this.y;
        if(moveStage >= 1 && moveStage <= 10) {
            this.y -= 2.0;
            moveStage++;
        }
        else if(moveStage > 10 && moveStage <= 20){
            this.y+= 2.0;
            moveStage++;
        }
        else if(moveStage == 21) {
            moveStage = 0;
            this.y = oldY;
        }

    }
    public void drawLines() throws SlickException { //draws lines to check for collisions
        lines = new ArrayList(4);
        topRectangle = new myRectangle(x+10, y, 80, 0);
        leftLine = new myLine(x, y-10, x, y + 110);
        rightLine = new myLine(x + 100, y-10, x + 100, y + 110);
        bottomRectangle = new myRectangle(x+30, y+99, 40, 1);

        lines.add(topRectangle);
        lines.add(leftLine);
        lines.add(rightLine);
        lines.add(bottomRectangle);
    }

    public void bottomCollision(Mario mario) throws SlickException{
    }; //to be overrided

    
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

    public int getMoveStage(){
        return moveStage;
    }

    public ArrayList getLines(){
        return lines;
    }

}