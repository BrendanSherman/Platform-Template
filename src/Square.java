import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Square extends BasicGame
{
    public Image img;
    private Image bg;
    private int x;
    private int y;
    private Rectangle box = new Rectangle(500, 980, 100, 100);
    private Rectangle mariocollision;
    private Music song = new Music("song.ogg");

    public Square(String gamename, int x, int y) throws SlickException {
        super(gamename);
        this.x = x;
        this.y = y;
    }

    public void init() throws SlickException{
        img = new Image("images.png");
        bg = new Image("maxresdefault.jpg");
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
        mariocollision = new Rectangle(x, y, 128, 128);
        if(mariocollision.intersects(box)){
            x-=5;
        }
        img.draw(x, y);

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
        img.draw(x, y);
        g.setColor(Color.orange);
        g.fillRect(500, 980, 100, 80);

    }

    public static void main(String[] args)
    {
        try
        {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Square("Game", 30, 930));
            appgc.setDisplayMode(1920, 1080, true);
            appgc.setMinimumLogicUpdateInterval(5);
            appgc.setMaximumLogicUpdateInterval(5);
            appgc.setTargetFrameRate(60);
            appgc.start();

        }
        catch (SlickException ex)
        {
            Logger.getLogger(SimpleSlickGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}