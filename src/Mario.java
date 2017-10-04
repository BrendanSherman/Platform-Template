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
    SpriteSheet smallMarioSheetMovement = new SpriteSheet("resources/images/smallMarioSheetMovement.png", 120, 128, 8);
    SpriteSheet smallMarioSheetMovement2 = new SpriteSheet("resources/images/smallMarioSheetMovement2.png", 120, 128, 8);

    public Mario(int x, int y, Image marioRight, Image marioLeft) throws SlickException{ //sets variables
        this.marioRight = marioRight;
        this.marioLeft = marioLeft;
        this.marioX = x;
        this.marioY = y;
        marioDir = "right";
    }

    public void Draw(String marioDir){ //draws mario on screen depending on position
        if (marioDir.equals("right")){
            smallMarioSheetMovement.getSubImage(0, 0).draw(marioX, marioY);
        }

        else if(marioDir.equals("left")){
            smallMarioSheetMovement.getSubImage(0, 0).getFlippedCopy(true, false).draw(marioX, marioY);
        }

        marioFeetRectangle = new Rectangle(marioX + 16, marioY + 118, 196, 10); //sets bottom hitbox
        marioRightRectangle = new Rectangle(marioX + 75, marioY + 32, 13, 128); //sets right hitbox
    }

    public boolean checkRightCollision(Line otherThing){ //checks for a right collision
        Draw(marioDir);
        if(otherThing.intersects(marioRightRectangle)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkBottomCollision(Line otherThing){ //checks for a bottom collision
        Draw(marioDir);
        if(otherThing.intersects(marioFeetRectangle)){
            return true;
        }
        else{
            return false;
        }
    }

    //getters and setters for X and Y

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
