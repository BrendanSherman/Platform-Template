import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;

public class Mushroom extends Item{
    boolean bottomCollision = false;
    Image mushroom = items.getSubImage(0, 0);
    int x;
    int y;
    Line bottomline;
    Rectangle mushroomRect;
    String dir = "right";
    public Mushroom(Box box) throws SlickException{
        super(box);
        this.x = box.x;
        this.y = box.y - 100;
    }

    public void draw(){
        mushroom.draw(x, y);
    }
    public void drawLines(){
        bottomline = new myLine(x, y+100, x+100, y+100);
        mushroomRect = new myRectangle(x, y, 100, 100);
    }
    public void collision(Mario m) throws SlickException{
        mushroom.destroy();
    }

    public void move(){
        if(dir.equals("right")){
            x+=3;
        }
        else{
            x-=3;
        }
    }



}
