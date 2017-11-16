import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;

public class Goomba extends Enemy{
    public myLine topLine;
    public myLine bottomLine;
    public Rectangle sidesRectangle;
    public SpriteSheet goombaSheet = new SpriteSheet("resources/images/goombaSpriteSheet.png", 100, 100);

    public Goomba(int x, int y) throws SlickException{
        super(x, y);
    }
    public void die(){

    }

    public void draw() throws SlickException{
        Image goomba = goombaSheet.getSubImage(0, 0);
        goomba.draw(x, y);
    }

    public void move(){
        if(dir.equals("right")){
            this.x++;
        }
        else if(dir.equals("left")){
            this.x--;
        }
    }



    public boolean checkTopCollision(){
        return true;
    }
    public boolean checkLeftCollision(){
        return true;
    }
    public boolean checkRightCollision(){
        return true;
    }
    public boolean checkBottomCollision(){
        return true;
    }
}

