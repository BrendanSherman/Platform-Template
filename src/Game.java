import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

import static java.lang.Thread.sleep;

public class Game extends BasicGame
{
    private Image marioRight;
    private Image marioLeft;
    private Box box1;
    private Mario mario;
    private Image bg;
    private Music song = new Music("resources/music/song1.ogg");
    private int jumpStage = 0;
    private Sound jumpSound = new Sound("resources/music/smb_jump-small.wav");
    private Image e;

    public Game(String gamename, int x, int y) throws SlickException {
        super(gamename);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        song.play();
        song.loop();
        box1 = new Box("resources/images/blocks/questionMarkBlock1.png", 500, 952);
        bg = new Image("resources/images/background1.jpg");
        marioLeft = new Image("resources/images/marioFacingLeft.png");
        marioRight = new Image("resources/images/marioFacingRight.png");
        mario = new Mario(80, 930, marioRight, marioLeft);
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        if (mario.getMarioY() < 930 && jumpStage == 0){ //gravity
            mario.setMarioY(mario.getMarioY() + 4);
        }

        if(mario.checkRightCollision(box1.leftLine)){
            mario.setMarioX(mario.getMarioX() - 5);
        }

        if(mario.checkBottomCollision(box1.topLine)){
            mario.setMarioY(box1.getY() - 150);
        }


        Input input = gc.getInput();
        if(input.isKeyDown(Input.KEY_RIGHT)){
            mario.setMarioX(mario.getMarioX() + 2);
            System.out.println(mario.getMarioX());
            mario.marioDir = "right";
            mario.Draw(mario.marioDir);
        }

        if(input.isKeyDown(Input.KEY_LEFT)){
            mario.setMarioX(mario.getMarioX() - 2);
            mario.marioDir = "left";
            mario.Draw(mario.marioDir);
        }

        if (input.isKeyDown(Input.KEY_SPACE) && jumpStage == 0){
            jumpStage++;
            jumpSound.play();
        }

        if(jumpStage > 0) {
            if (jumpStage >= 1 && jumpStage < 60) {
                mario.setMarioY(mario.getMarioY() - 4);
                mario.Draw(mario.marioDir);
                jumpStage++;
            } else if (jumpStage >= 60 && jumpStage < 120) {
                mario.setMarioY(mario.getMarioY() + 4);
                mario.Draw(mario.marioDir);
                jumpStage++;
            }
            if (jumpStage == 120) {
                jumpStage = 0;
                mario.setMarioY(mario.getMarioY() - 4);
                mario.Draw(mario.marioDir);
            }
        }



    }

    public void keyPressed(GameContainer gc, int key, char c) throws SlickException{
        super.keyPressed(key, c);
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        //draws the background
        bg.draw(0,0);
        mario.Draw(mario.marioDir);

        //draws the ground (i think)
        g.setColor(Color.green);
        g.fillRect(0, 1050, 1920, 30);


        box1.draw();
        box1.drawLines();
        // Draws a question mark block.

    }

    public static void main(String[] args) throws SlickException
    {
        Game g = new Game("Game", 30, 930);
        try // Creates a new AppContainer and sets resolution, update interval, fullscreen status, and target framerate.
        {
            AppGameContainer appgc;
            appgc = new AppGameContainer(g);
            appgc.setDisplayMode(1920, 1080, false);
            appgc.setMinimumLogicUpdateInterval(5);
            appgc.setMaximumLogicUpdateInterval(5);
            appgc.setTargetFrameRate(60);
            appgc.start();

        }
        catch (SlickException ex) //Generates a logger if AppContainer fails to start
        {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}