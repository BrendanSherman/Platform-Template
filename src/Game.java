import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Game extends BasicGame {
    int lives = 3;
    boolean animationEligible =  true;
    private Box box1;
    private Box box2;
    private Box box3;
    private Box box4;
    private Box box5;
    private QuestionBox qBox1;
    private Pipe pipe1;
    private Mario mario;
    private Image bg;
    private Music song = new Music("resources/sounds/stage1.ogg");
    private Sound jumpSound = new Sound("resources/sounds/smb_jump-small.wav");
    private Sound bumpSound = new Sound("resources/sounds/smb_bump.wav");
    int jumpStage = 0;
    private Camera cam = new Camera();
    int groundLevel = 922;
    Box[] collidables = new Box[7];
    Font font;
    TrueTypeFont ttf;

    public Game(String gamename, int x, int y) throws SlickException {
        super(gamename);
    }

    @Override
    public void init(GameContainer gc) throws SlickException { //Implicity called at the start.
        song.play(0, 0.01f);
        song.loop();
        box1 = new Box("resources/images/blocks/brickBlock1.png", 500, 952);
        box2 = new Box("resources/images/blocks/brickBlock1.png", 700, 700);
        box3 = new Box("resources/images/blocks/brickBlock1.png", 800, 700);
        qBox1= new QuestionBox(900, 700, "shroom");
        box4 = new Box("resources/images/blocks/brickBlock1.png", 1000, 700);
        box5 = new Box("resources/images/blocks/brickBlock1.png", 1100, 700);
        pipe1 = new Pipe(1400, 796);
        bg = new Image("resources/images/background1Clean.png");
        mario = new Mario(80, groundLevel);
        font = new Font("Apple Chancery", Font.BOLD, 32);
        ttf = new TrueTypeFont(font, true);
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        collidables[0] = box1;
        collidables[1] = qBox1;
        collidables[2] = box2;
        collidables[3] = box3;
        collidables[4] = box4;
        collidables[5] = box5;
        collidables[6] = pipe1;

        for(int x = 0; x < collidables.length; x++){
            if(collidables[x] instanceof QuestionBox && jumpStage < 60){
                collidables[x].bottomCollision(mario);
            }
            //checks for collisions with all entities in the level dab
            for(int j = 0; j< collidables[x].getLines().size(); j++){
                if(mario.marioRightCollison((org.newdawn.slick.geom.Shape) collidables[x].lines.get(j)) && collidables[x].lines.get(j) == collidables[x].leftLine){
                    mario.setMarioX(mario.getMarioX() - 2);
                    mario.rightCollision = true;
                }
                if(mario.marioLeftCollison((org.newdawn.slick.geom.Shape)collidables[x].lines.get(j))&& collidables[x].lines.get(j) == collidables[x].rightLine){
                    mario.setMarioX(mario.getMarioX() + 2);
                    mario.leftCollision = true;
                }

                if(mario.marioFeetCollison((org.newdawn.slick.geom.Shape)collidables[x].lines.get(j))&& collidables[x].lines.get(j) == collidables[x].topRectangle){
                    mario.setMarioY(collidables[x].getY()-130);
                    mario.feetCollision = true;
                }
                if(mario.marioHeadCollision((org.newdawn.slick.geom.Shape)collidables[x].lines.get(j)) && collidables[x].lines.get(j) == collidables[x].bottomRectangle && (jumpStage < 60)){
                    jumpStage = 0;
                    mario.headCollision = true;
                    if(!((collidables[x].url).equals("resources/images/blocks/emptyQuestionBlock.png")) && animationEligible
                            && mario.getMarioX() < collidables[x].x + 70){ //checks for "bump" eligibility
                        bumpSound.play();
                        collidables[x].moveStage += 1;
                        animationEligible = false;
                    }
                }
            }
        }

        if (mario.getMarioY() < groundLevel && jumpStage == 0){ //gravity
            mario.setMarioY(mario.getMarioY() + 4);
        }

        if(mario.getMarioY() > groundLevel){ //Reverse gravity
            mario.setMarioY(groundLevel);
        }

        Input input = gc.getInput();
        // check for inputs
        if (input.isKeyDown(Input.KEY_RIGHT)) {
            mario.setMarioX(mario.getMarioX() + 2);
            mario.marioDir = "right";
            mario.Draw(mario.marioDir);
            if(mario.marioRightStage < 30) {
                mario.marioRightStage++;
            }
            else{
                mario.marioRightStage = 1;
            }
            mario.marioLeftStage = 0;
        }
        else if(input.isKeyDown(Input.KEY_LEFT)){
            mario.setMarioX(mario.getMarioX() - 2);
            mario.marioDir = "left";
            mario.Draw(mario.marioDir);
            if(mario.marioLeftStage < 30) {
                mario.marioLeftStage++;
            }
            else{
                mario.marioLeftStage = 1;
            }
            mario.marioRightStage = 0;
        }
        else {
            mario.marioLeftStage = 0;
            mario.marioRightStage = 0;
        }

        if (input.isKeyPressed(Input.KEY_UP) && (mario.getMarioY() == 922 || mario.feetCollision)){
            jumpStage = 0;
            jumpStage++;
            jumpSound.play();
            mario.marioState = "jump";
            mario.marioLeftStage = 0;
            mario.marioRightStage = 0;
        }
        else

        if (input.isKeyDown(Input.KEY_1)) {
            mario.marioCurrentSheet = mario.smallMarioSheet;
        }

        if (input.isKeyDown(Input.KEY_2)) {
            mario.marioCurrentSheet = mario.smallLuigiSheet;
        }

        //update jump animation
            if (jumpStage >= 1 && jumpStage < 60) {
                mario.setMarioY(mario.getMarioY() - 6);
                mario.Draw(mario.marioDir);
                jumpStage++;
                mario.marioState = "jump";
            } else if (jumpStage >= 60 && jumpStage < 120) {
                mario.setMarioY(mario.getMarioY() + 6);
                mario.Draw(mario.marioDir);
                jumpStage++;
                mario.marioState = "jump";

            }
            if (jumpStage  >= 120) {
                mario.marioState = "walk";
                jumpStage = 0;
                mario.setMarioY(mario.getMarioY() - 6);
                mario.Draw(mario.marioDir);
            }

        if (mario.feetCollision || mario.getMarioY() >= groundLevel) {
            mario.marioState = "walk";
            animationEligible = true;
        }

        for(int k = 0;k < collidables.length; k++){ //calls animate for boxes
            collidables[k].Animate();
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

       for(int i = 0; i < collidables.length; i++){
            if(collidables[i] instanceof Box)
                collidables[i].draw();
       }

        ttf.drawString(cam.camX + 10, 30, "Lives: " + lives, Color.red);

        mario.Draw(mario.marioDir);

        //draws the ground
        g.setColor(new Color(0, 150,0));
        g.fillRect(0, 1050, 10000, 30);


    }

    public static void main(String[] args) throws SlickException
    {
        Game g = new Game("Super Mario Bros.", 30, 930);
        try // Creates a new AppContainer and sets resolution, update interval, fullscreen status, and target framerate.
        {
            AppGameContainer appgc;
            appgc = new AppGameContainer(g);
            appgc.setDisplayMode(1920, 1080, false);
            appgc.setMinimumLogicUpdateInterval(5);
            appgc.setMaximumLogicUpdateInterval(5);
            appgc.setVSync(true);
            appgc.setTargetFrameRate(60);
            appgc.start();

        }
        catch (SlickException ex) //Generates a logger if AppContainer fails to start
        {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}