import java.util.logging.Level;
import java.util.logging.Logger;
//TEST
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

public class MarioClone extends BasicGame
{
    public Image mario;
    private Image bg;
    private int x;
    private int y;
    private Rectangle box = new Rectangle(500, 980, 100, 100);
    private Rectangle boxHitBox;
    private Music song = new Music("resources/music/song1.ogg");

    public MarioClone(String gamename, int x, int y) throws SlickException {
        super(gamename);
        this.x = x;
        this.y = y;
    }

    public void init() throws SlickException{
        mario = new Image("resources/images/marioFacingRight.png");
        bg = new Image("resources/images/background1.jpg");
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
            x+= 2;
        }

        if(input.isKeyDown(Input.KEY_LEFT)){
            x-= 2;
        }


        if (input.isKeyDown(Input.KEY_UP)){
            y+=20;
        }

        init();
        if(y>=930)
            y = 930;
        boxHitBox = new Rectangle(x, y, 128, 128);
        if(boxHitBox.intersects(box)){
            x-=5;
        }
        mario.draw(x, y);

    }

    public void keyPressed(GameContainer gc, int key, char c) throws SlickException{

        super.keyPressed(key, c);
    }
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        init();
        bg.draw(0,0);
        g.setColor(Color.green);
        g.fillRect(0, 1050, 1920, 30);
        mario.draw(x, y);
        g.setColor(Color.orange);
        g.fillRect(500, 980, 100, 80);

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