import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class Game extends BasicGame
{
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private Image uselessImage;
    private Box box1;
    private QuestionBox qBox1;
    private Mario mario;
    private Image bg;
    private Music song = new Music("resources/music/song1.ogg");
    private int jumpStage = 0;
    private Sound jumpSound = new Sound("resources/music/smb_jump-small.wav");
    private Image e;
    private Camera cam = new Camera();
    int groundLevel = 922;
    Box[] collidables = new Box[2];


    public Game(String gamename, int x, int y) throws SlickException {
        super(gamename);
    }

    @Override
    public void init(GameContainer gc) throws SlickException { //Implicity called at the start
        song.play();
        song.loop();
        box1 = new Box("resources/images/blocks/brickBlock1.png", 500, 952);
        qBox1= new QuestionBox(900, 700, "shroom");
        bg = new Image("resources/images/background1.png");
        mario = new Mario(80, groundLevel);
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {

        collidables[0] = box1;
        collidables[1] = qBox1;


        for(int x = 0; x < collidables.length; x++){
            if(collidables[x] instanceof QuestionBox){
                collidables[x].bottomCollision(mario);
            }
            //checks for collisions with all entities in the level dab
            for(int j = 0; j< collidables[x].getLines().length; j++){
                if(mario.marioRightCollison(collidables[x].lines[j]) && collidables[x].lines[j] == collidables[x].leftLine){
                    mario.setMarioX(mario.getMarioX() - 2);
                    mario.rightCollision = true;
                }
                if(mario.marioLeftCollison(collidables[x].lines[j])&& collidables[x].lines[j] == collidables[x].rightLine){
                    mario.setMarioX(mario.getMarioX() + 2);
                    mario.leftCollision = true;
                }

                if(mario.marioFeetCollison(collidables[x].lines[j])&& collidables[x].lines[j] == collidables[x].topLine){
                    mario.setMarioY(collidables[x].getY()-130);
                    mario.feetCollision = true;
                }
                if(mario.marioHeadCollision(collidables[x].lines[j]) && collidables[x].lines[j] == collidables[x].bottomLine){
                    jumpStage = 0;
                    mario.headCollision = true;
                }
            }
        }

        if (mario.getMarioY() < groundLevel && jumpStage == 0){ //gravity
            mario.setMarioY(mario.getMarioY() + 4);
        }




        Input input = gc.getInput();
        // check for inputs
        if(input.isKeyDown(Input.KEY_RIGHT)){
            mario.setMarioX(mario.getMarioX() + 2);
            mario.marioDir = "right";
            mario.Draw(mario.marioDir);
        }

        if(input.isKeyDown(Input.KEY_LEFT)){
            mario.setMarioX(mario.getMarioX() - 2);
            mario.marioDir = "left";
            mario.Draw(mario.marioDir);
        }

        if (input.isKeyDown(Input.KEY_SPACE) && (mario.getMarioY() == 922 || mario.feetCollision)){
            jumpStage++;
            jumpSound.play();
        }
        //update jump animation
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

        //update camera x and y
        cam.camX = mario.getMarioX() - cam.VIEWPORT_WIDTH / 2;
        cam.camY = mario.getMarioY() - cam.VIEWPORT_HEIGHT / 2;

        //Stop the camera from going off the map
        if (cam.camX > cam.offsetMaxX)
            cam.camX = cam.offsetMaxX;
        else if (cam.camX < cam.offsetMinX)
            cam.camX = cam.offsetMinX;
        if (cam.camY > cam.offsetMaxY)
            cam.camY = cam.offsetMaxY;
        else if (cam.camY < cam.offsetMinY)
            cam.camY = cam.offsetMinY;

        mario.feetCollision = false;
        mario.headCollision = false;
        mario.leftCollision = false;
        mario.rightCollision = false;
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        g.translate(-cam.camX, -cam.camY);
        //draws the background
        for(int i = 0; i <= 10000; i+=2184){
            bg.draw(i, 0);
        }

        box1.draw();
        box1.drawLines();

        qBox1.draw();
        qBox1.drawLines();

        mario.Draw(mario.marioDir);

        //draws the ground
        g.setColor(Color.green);
        g.fillRect(0, 1050, 10000, 30);


    }

    public Animation getAnimation (Image i, int SpritesX, int SpritesY, int spriteWidth, int SpriteHeight, int frames, int duration, int SpriteSpacing) {
        Animation a = new Animation(false);

        for(int y = 0; y < SpritesY; y++) {
            for (int x = 0; x < SpritesX; x++) {
                a.addFrame(i.getSubImage(x * spriteWidth + x * SpriteSpacing, y *SpriteHeight, spriteWidth, SpriteHeight), duration);
            }
        }
        return a;
    }

    public static void main(String[] args) throws SlickException
    {
        Game g = new Game("Game", 30, 930);
        try // Creates a new AppContainer and sets resolution, update interval, fullscreen status, and target framerate.
        {
            // iniitialize game
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