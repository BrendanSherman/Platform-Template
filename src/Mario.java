import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;

public class Mario {
    private int marioX;
    private int marioY;
    private Rectangle marioFeetRectangle;
    private Rectangle marioRightRectangle;
    private Image marioRight;
    private Image marioLeft;
    public String marioDir;

    public Mario(int x, int y, Image marioRight, Image marioLeft) throws SlickException{
        this.marioRight = marioRight;
        this.marioLeft = marioLeft;
        this.marioX = x;
        this.marioY = y;
        marioDir = "right";
    }

    public void Draw(String marioDir){
        if (marioDir.equals("right")){
            marioRight.draw(marioX, marioY);
        }

        else if(marioDir.equals("left")){
            marioLeft.draw(marioX, marioY);
        }

        marioFeetRectangle = new Rectangle(marioX + 16, marioY + 118, 196, 10);
        marioRightRectangle = new Rectangle(marioX + 75, marioY + 32, 13, 128);
    }

    public boolean checkRightCollision(Line otherThing){
        Draw(marioDir);
        if(otherThing.intersects(marioRightRectangle)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkBottomCollision(Line otherThing){
        Draw(marioDir);
        if(otherThing.intersects(marioFeetRectangle)){
            return true;
        }
        else{
            return false;
        }
    }

    public int getMarioX() {
        return marioX;
    }

    public void setMarioX(int marioX) {
        this.marioX = marioX;
    }

    public int getMarioY() {
        return marioY;
    }

    public void setMarioY(int marioY) {
        this.marioY = marioY;
    }
}
