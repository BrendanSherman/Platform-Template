import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public class Mario {
    private int marioX;
    private int marioY;
    private String url;
    private Rectangle marioFeetRectangle;
    private Rectangle marioRightRectangle;
    private SpriteSheet smallMarioSheet = new SpriteSheet("resources/images/smallMarioSheet.png", 120, 128, 8);

    public Mario(int x, int y, String url) throws SlickException{
        this.marioX = x;
        this.marioY = y;
        this.url = url;

    }

    public void Draw(String marioDir){
        if (marioDir.equals("right")){
            smallMarioSheet.getSubImage(0, 0, 128, 128).draw(marioX, marioY);
        }

        else if(marioDir.equals("left")){
            smallMarioSheet.getSubImage(0, 0, 128, 128).getFlippedCopy(true, false).draw(marioX, marioY);
        }

        marioFeetRectangle = new Rectangle(marioX + 16, marioY + 118, 196, 10);
        marioRightRectangle = new Rectangle(marioX + 75, marioY + 32, 13, 128);
    }

    public boolean checkRightCollision(Rectangle otherThing){
        if(marioRightRectangle.intersects(otherThing)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkBottomCollision(Rectangle otherThing){
        if(marioFeetRectangle.intersects(otherThing)){
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
