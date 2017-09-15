
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.*;

public class SimpleSlickGame extends BasicGame
{
    public SimpleSlickGame(String gamename)
    {
        super(gamename);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {}

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        GraphicString gs = new GraphicString(g, "Hello", 100, 300);
        Input input = gc.getInput();
        if(input.isMouseButtonDown(0)) {
            while(input.isMouseButtonDown(0) && gs.getX() < 300) {
                gs.setX(gs.getX() + 20);
                gs.setY(gs.getY());
            }
        }
        gs.draw();
    }

    public static void main(String[] args)
    {
        try
        {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new SimpleSlickGame("Hello"));
            appgc.setDisplayMode(640, 1000, false);
            appgc.start();
        }
        catch (SlickException ex)
        {
            Logger.getLogger(SimpleSlickGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class GraphicString {
    private String string;
    private int x;
    private int y;
    private Graphics g;

    public GraphicString(Graphics g, String string, int x, int y) {
        this.string = string;
        this.x = x;
        this.y = y;
        this.g = g;
    }

    public void draw() {

        g.drawString(this.string, this.x, this.y);
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}