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
    private Rectangle marioLeftRectangle;
    private Rectangle marioHeadRectangle;
    private Image marioRight;
    private Image marioLeft;
    boolean feetCollision = false;
    boolean headCollision = false;
    boolean leftCollision = false;
    boolean rightCollision = false;
    public String marioDir;
    SpriteSheet smallMarioSheetMovement = new SpriteSheet("resources/images/smallMarioSheetMovement.png", 120, 128, 8);
    SpriteSheet smallMarioSheetMovement2 = new SpriteSheet("resources/images/smallMarioSheetMovement2.png", 120, 128, 8);

    public Mario(int x, int y) throws SlickException{ //sets variables
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

        marioFeetRectangle = new Rectangle(marioX, marioY + 118, 128, 10); //sets bottom hitbox
        marioRightRectangle = new Rectangle(marioX + 75, marioY + 32, 13, 128); //sets right hitbox
        marioLeftRectangle = new Rectangle(marioX + 32, marioY + 32, 16, 128); //sets left hitbox
        marioHeadRectangle = new Rectangle(marioX + 32, marioY, 128, 8);
    }
   public boolean marioHeadCollision(Line l) {
        if(marioHeadRectangle.intersects(l))
            return true;
        else
            return false;
   }
   public boolean marioFeetCollison(Line l){   //checks for feet collision
        if (marioFeetRectangle.intersects(l))
            return true;
        else
            return false;
   }

    public boolean marioLeftCollison(Line l){  //checks for left collision
        if (marioLeftRectangle.intersects(l))
            return true;
        else
            return false;
    }

    public boolean marioRightCollison(Line l){  //checks for right collision
        if (marioRightRectangle.intersects(l))
            return true;
        else
            return false;
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
