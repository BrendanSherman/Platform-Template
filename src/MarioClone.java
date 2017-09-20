import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.xml.internal.bind.v2.TODO;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;

import static java.lang.Thread.sleep;

public class MarioClone extends BasicGame
{
    // adds images and shit. this is garbage code, please don't look at it.
    public Image smallMarioSheet = new Image("resources/images/smallMarioSheet.png");
    public SpriteSheet marioSheet = new SpriteSheet(smallMarioSheet, 15, 16, 1);
    public Image marioRight;
    public Image marioLeft;
    public Image questionBlock1;
    private Image bg;
    private int marioX;
    private int marioY;
    private Rectangle box = new Rectangle(500, 980, 100, 100);
    private Line MarioFootLine;
    private Line MarioRightLine;
    private Music song = new Music("resources/music/song1.ogg");
    public String MarioCurrentDir = "right";
    private int jumpStage = 0;
    private boolean jump = false;
    private Sound jumpSound = new Sound("resources/music/smb_jump-small.wav");
    boolean onBox = false;

    public MarioClone(String gamename, int x, int y) throws SlickException {
        super(gamename);
        this.marioX = x;
        this.marioY = y;
    }

    public void init() throws SlickException{
        // loads sprites (inc. Mario, the background, and blocks.)
        marioRight = new Image("resources/images/marioFacingRight.png");
        marioLeft = new Image("resources/images/marioFacingLeft.png");
        bg = new Image("resources/images/background1.jpg");
        questionBlock1 = new Image("resources/images/blocks/questionMarkBlock1.png");
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        song.play();
        song.loop();
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        if (marioY < 930 && jumpStage == 0){ //gravity
            marioY+=4;
        }

        Input input = gc.getInput();
        if(input.isKeyDown(Input.KEY_RIGHT)){
            marioX += 2;
            MarioCurrentDir = "right";
        }

        if(input.isKeyDown(Input.KEY_LEFT)){
            marioX -= 2;
            MarioCurrentDir = "left";
        }

        if (input.isKeyDown(Input.KEY_SPACE) && jumpStage == 0){
            jumpStage++;
            jumpSound.play();
        }

        if(jumpStage > 0){
            if (jumpStage >= 1 && jumpStage < 60){
                marioY -= 4;
                jumpStage++;
            }

            else if (jumpStage >= 60 && jumpStage < 120){
                marioY += 4;
                jumpStage++;
            }
            if(jumpStage == 120){
                jumpStage = 0;
                marioY -=4;
            }
        }

        init();
        MarioRightLine = new Line(marioX + 128, marioY, marioX + 128, marioY + 128);
        MarioFootLine =  new Line(marioX, marioY+128, marioX+90, marioY+128);
        if(MarioRightLine.intersects(box)){
            marioX -=2;
        }
        if (MarioFootLine.intersects(box)){
            marioY = 830;
            onBox = true;
        }


    }

    public void keyPressed(GameContainer gc, int key, char c) throws SlickException{
        super.keyPressed(key, c);
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        init();
        //draws the background
        bg.draw(0,0);
        //draws the ground (i think)
        g.setColor(Color.green);
        g.fillRect(0, 1050, 1920, 30);
        //draws Mario
        if (MarioCurrentDir.equals("right")) {
            smallMarioSheet.getSubImage(1,1, 15, 16).draw(marioX,marioY);
        }
        questionBlock1.draw(500, 950, 100, 100);
    }

    public static void main(String[] args)
    {
        try // Creates a new AppContainer and sets resolution, update interval, fullscreen status, and target framerate.
        {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new MarioClone("Game", 30, 930));
            appgc.setDisplayMode(1920, 1080, false);
            appgc.setMinimumLogicUpdateInterval(5);
            appgc.setMaximumLogicUpdateInterval(5);
            appgc.setTargetFrameRate(60);
            appgc.start();

        }
        catch (SlickException ex) //Generates a logger if AppContainer fails to start
        {
            Logger.getLogger(MarioClone.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}