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
    int moveStage = 0;
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

    public void Animate(){ //Box animation
        if(moveStage >= 1 && moveStage <= 10) {
            this.y -= 1;
            moveStage++;
        }
        else if(moveStage > 10 && moveStage <= 20){
            this.y+= 1;
            moveStage++;
        }
        else if(moveStage == 21){
            moveStage = 0;
        }

    }
    public void drawLines() throws SlickException { //draws lines to check for collisions
        lines = new ArrayList(4);
        topRectangle = new myRectangle(x, y, 100, 4);
        leftLine = new myLine(x, y-10, x, y + 110);
        rightLine = new myLine(x + 100, y-10, x + 100, y + 110);
        bottomRectangle = new myRectangle(x+10, y+55, 80, 10);

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