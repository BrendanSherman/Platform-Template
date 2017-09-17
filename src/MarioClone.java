import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

public class MarioClone extends BasicGame
{
    public Image mario;
    public Image questionBlock1;
    private Image bg;
    private int marioX;
    private int marioY;
    private Rectangle box = new Rectangle(500, 980, 100, 100);
    private Rectangle boxHitBox;
    private Music song = new Music("resources/music/song1.ogg");

    public MarioClone(String gamename, int x, int y) throws SlickException {
        super(gamename);
        this.marioX = x;
        this.marioY = y;
    }

    public void init() throws SlickException{
        mario = new Image("resources/images/marioFacingRight.png");
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
        Input input = gc.getInput();
        if(input.isKeyDown(Input.KEY_RIGHT)){
            marioX += 2;
        }

        if(input.isKeyDown(Input.KEY_LEFT)){
            marioX -= 2;
        }


        if (input.isKeyDown(Input.KEY_UP)){
            marioY +=20;
        }

        init();
        if(marioY >=930)
            marioY = 930;
        boxHitBox = new Rectangle(marioX, marioY, 128, 128);
        if(boxHitBox.intersects(box)){
            marioX -=5;
        }
        mario.draw(marioX, marioY);

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
        mario.draw(marioX, marioY);
        g.setColor(Color.orange); //draws a box
        g.fillRect(500, 980, 100, 100);
        questionBlock1.draw(500, 980);
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