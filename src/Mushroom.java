import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;

public class Mushroom extends Item{
    Image mushroom = items.getSubImage(0, 0);

    public Mushroom(Box box) throws SlickException{
        super(box);
    }
    public Mushroom(int x, int y)throws SlickException{
        super(x,y);
    }

    public void draw(){
        mushroom.draw(x, y);
        drawRect();
    }

    public void collision() throws SlickException{
        mushroom.destroy();
    }

    public void move(){
        if(dir.equals("right")){
            x+=1;
        }
        else{
            x-=1;
        }
    }




}
