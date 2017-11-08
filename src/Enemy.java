import org.newdawn.slick.SlickException;

abstract class Enemy{
//Basic enemy class should have walking jumping and collisions

    abstract void death();

    abstract void draw() throws SlickException;

    abstract public boolean checkTopCollision();
    abstract public boolean checkLeftCollision();
    abstract public boolean checkRightCollision();
    abstract public boolean checkBottomCollision();
}
