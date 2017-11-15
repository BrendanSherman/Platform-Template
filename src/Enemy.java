import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

abstract class Enemy{
    int x;
    int y;
    public Enemy(int x, int y) throws SlickException{
        this.x = x;
        this.y = y;
    }
    abstract void die();

    abstract void draw() throws SlickException;

    abstract void move();

    abstract public boolean checkTopCollision();
    abstract public boolean checkLeftCollision();
    abstract public boolean checkRightCollision();
    abstract public boolean checkBottomCollision();
}
