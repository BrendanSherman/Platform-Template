import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Shape;

public class Mario {
    private int marioX; //mario coordinates
    private int marioY;
    private myRectangle marioFeetRectangle;  //mario hitboxes
    private myRectangle marioRightRectangle;
    private myRectangle marioLeftRectangle;
    private myRectangle marioHeadRectangle;
    private Image marioRight;
    private Image marioLeft;
    private Image marioImage;
    public String marioState = "walk";
    public int marioLeftStage = 0;
    public int marioRightStage = 0;
    public boolean isJumping = false;
    public SpriteSheet smallMarioSheet = new SpriteSheet("resources/images/smallMarioSheet.png", 128, 128, 8);
    public SpriteSheet smallLuigiSheet = new SpriteSheet("resources/images/smallLuigiSheet.png", 128, 128, 8);
    public SpriteSheet marioCurrentSheet = smallMarioSheet;
    boolean feetCollision = false;  //used to check for mario collisions
    boolean headCollision = false;
    boolean leftCollision = false;
    boolean rightCollision = false;
    public String marioDir; //checks for mario's direction for animation

    public Mario(int x, int y) throws SlickException{ //sets variables
        this.marioX = x;
        this.marioY = y;
        marioDir = "right";
    }

    public void Draw(String marioDir){ //draws mario on screen depending on position
        if (marioDir.equals("right")){
            marioImage = getMarioImage(marioRightStage);
        }
        else if(marioDir.equals("left")){
            marioImage = getMarioImage(marioLeftStage).getFlippedCopy(true, false);
        }
        marioImage.draw(marioX, marioY);
        marioFeetRectangle = new myRectangle(marioX + 16, marioY + 118, 96, 10); //sets bottom hitbox
        marioRightRectangle = new myRectangle(marioX + 75, marioY + 32, 13, 60); //sets right hitbox
        marioLeftRectangle = new myRectangle(marioX + 32, marioY + 32, 16, 60); //sets left hitbox
        marioHeadRectangle = new myRectangle(marioX + 16, marioY, 96, 8);
    }
    // this method, given marioStage, will return the correct mario image.
    public Image getMarioImage(int marioStage) {
        // magic numbers are my magic
        Image image = marioCurrentSheet.getSubImage(4, 0);
        if (marioState == "walk") {
            if (marioStage == 0) {
                image = marioCurrentSheet.getSubImage(0, 0);
            }
            else if (marioStage >= 1 && marioStage <= 10) {
                image = marioCurrentSheet.getSubImage(1, 0);
            }
            else if (marioStage >= 1 && marioStage <= 20) {
                image = marioCurrentSheet.getSubImage(2, 0);
            }
            else if (marioStage >= 11 && marioStage <= 30) {
                image = marioCurrentSheet.getSubImage(3, 0);
            }
        }
        else if (marioState == "jump") {
            image = marioCurrentSheet.getSubImage(5, 0);
        }
        else if (marioState == "swim") {
            //todo implement swimming animation
        }
        return image;
    }

    public boolean marioFeetCollison(Shape s){   //checks for feet collision
         if (s.intersects((marioFeetRectangle)))
             return true;
         else
             return false;
    }
    public boolean marioHeadCollision(Shape s) { //checks for head collision with a specific line
        if(s.intersects(marioHeadRectangle))
            return true;
        else
            return false;

    }
  
    public boolean marioLeftCollison(Shape s){  //checks for left collision with a specific line
        if (s.intersects(marioLeftRectangle))
            return true;
        else
            return false;
    }


    public boolean marioRightCollison(Shape s){  //checks for right collision with a specific line
        if (marioRightRectangle.intersects(s))
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
