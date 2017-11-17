import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

public class Game extends BasicGame {
    int lives = 3;
    boolean animationEligible =  true;
    ArrayList<Item> items = new ArrayList();
    ArrayList<Enemy> enemies = new ArrayList();
    private Mushroom m;
    //private Flower f;
    //private Star s;
    private Box box1;
    private Box box2;
    private Box box3;
    private Box box4;
    private Box box5;
    private Box box6;
    private QuestionBox qBox1;
    private Pipe pipe1;
    private Mario mario;
    private Image bg;
    private Music song = new Music("resources/sounds/stage1.ogg");
    private Sound jumpSound = new Sound("resources/sounds/smb_jump-small.wav");
    private Sound bumpSound = new Sound("resources/sounds/smb_bump.wav");
    private Sound itemSpawn = new Sound("resources/sounds/smb_powerup_appears.wav");
    private Sound powerUp = new Sound("resources/sounds/smb_powerup.wav");
    private Sound smash = new Sound("resources/sounds/smb_breakblock.wav");
    int jumpStage = 0;
    private Camera cam = new Camera();
    public int groundLevel = 922;
    public int bigGroundLevel = 790;
    Box[] collidables = new Box[8];
    Font font;
    TrueTypeFont ttf;
    Rectangle ground = new Rectangle(0, 1050, 10000, 30);

    public Game(String gamename, int x, int y) throws SlickException {
        super(gamename);
    }

    @Override
    public void init(GameContainer gc) throws SlickException { //Implicity called at the start.
        song.loop(1, 0.3f);
        box1 = new Box("resources/images/blocks/brickBlock1.png", 500, 952);
        box2 = new Box("resources/images/blocks/brickBlock1.png", 700, 600);
        box3 = new Box("resources/images/blocks/brickBlock1.png", 800, 600);
        qBox1= new QuestionBox(900, 600, "mushroom");
        box4 = new Box("resources/images/blocks/brickBlock1.png", 1000, 600);
        box5 = new Box("resources/images/blocks/brickBlock1.png", 1100, 600);
        pipe1 = new Pipe(1500, 796);
        box6 = new Box("resources/images/blocks/brickBlock1.png", 1900, 500);
        bg = new Image("resources/images/background1Clean.png");
        mario = new Mario(80, groundLevel);
        font = new Font("Apple Chancery", Font.BOLD, 32);
        ttf = new TrueTypeFont(font, true);
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        mario.drawLines();

        collidables[0] = box1;
        collidables[1] = qBox1;
        collidables[2] = box2;
        collidables[3] = box3;
        collidables[4] = box4;
        collidables[5] = box5;
        collidables[6] = pipe1;
        collidables[7] = box6;

        for(int x = 0; x < collidables.length; x++){
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
                    if(!mario.isBig)
                        mario.setMarioY(collidables[x].getY()-130);
                    else
                        mario.setMarioY(collidables[x].getY()-260);
                    mario.feetCollision = true;
                }
                if(mario.marioHeadCollision((org.newdawn.slick.geom.Shape)collidables[x].lines.get(j)) && collidables[x].lines.get(j) == collidables[x].bottomRectangle && (jumpStage < 60)){
                    jumpStage = 0;
                    mario.headCollision = true;
                    if( collidables[x].used == false && animationEligible){ //checks for "bump" eligibility
                        bumpSound.play();
                        if(mario.isBig){
                            collidables[x].smash();
                            smash.play();
                        }
                        else
                            collidables[x].moveStage += 1;
                        animationEligible = false;
                    }

                    if(collidables[x] instanceof QuestionBox && !(collidables[x].used)){ //If mario hits an open qbox
                        if(collidables[x].type.equals("mushroom")) { //sets up a mushroom
                            itemSpawn.play();
                            m = new Mushroom(collidables[x]);
                            m.drawRect();
                            items.add(m);
                        }
                    }

                    if(collidables[x] instanceof QuestionBox){
                        collidables[x].bottomCollision(mario);
                    }

                }
            }
        }

        for(i = 0; i < items.size(); i++){
            for(int l = 0; l < collidables.length; l++){
                if((items.get(i).itemRectangle.intersects(collidables[l].topRectangle))){
                    items.get(i).bottomCollision = true;
                }
                if(items.get(i).narrowItemRectangle.intersects(collidables[l].leftLine) || items.get(i).narrowItemRectangle.intersects(collidables[l].rightLine)){
                    if(items.get(i).dir.equals("right")) { //Switch directions on collision, stuck proof
                        items.get(i).dir = ("left");
                        items.get(i).x-=3;
                    }
                    else if(items.get(i).dir.equals("left")) {
                        items.get(i).dir = ("right");
                        items.get(i).x+=3;
                    }
                }
            }

            if(!(items.get(i).bottomCollision) && items.get(i).y < groundLevel+35)
                items.get(i).fall();
            items.get(i).move();


            items.get(i).bottomCollision = false;

            if(mario.marioRightCollison(items.get(i).itemRectangle)){
                items.get(i).collision();

                if(items.get(i) instanceof Mushroom) {
                    m = null;
                    mario.getBig();
                }
                //else if(items.get(i) instanceof Flower)
                //else if(items.get(i) instance of Star)
                items.remove(i);
                powerUp.play();
            }
        }

        if(mario.marioFeetCollison(ground)){
            if(mario.isBig){
                mario.setMarioY(bigGroundLevel);
            }
            else
                mario.setMarioY(groundLevel);
            mario.feetCollision = true;
        }

        if (mario.getMarioY() < groundLevel && jumpStage == 0){ //gravity
            mario.setMarioY(mario.getMarioY() + 4);
        }

        if(mario.getMarioY() > groundLevel){ //Reverse gravity.
            if(mario.isBig)
                mario.setMarioY(bigGroundLevel);
            else
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

        if (input.isKeyPressed(Input.KEY_UP) && mario.feetCollision){
            jumpStage = 0;
            jumpStage++;
            jumpSound.play();
            mario.marioState = "jump";
            mario.marioLeftStage = 0;
            mario.marioRightStage = 0;
        }
        else

        if (input.isKeyDown(Input.KEY_1)) {
            mario.currentChar = "mario";
            mario.updateSheet();
        }

        if (input.isKeyDown(Input.KEY_2)) {
            mario.currentChar = "luigi";
            mario.updateSheet();
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

       Goomba gomb = new Goomba(300,groundLevel+28);
       gomb.draw();

       ttf.drawString(cam.camX + 10, 30, "Lives: " + lives, Color.red);

       mario.Draw(mario.marioDir);

        //draws the ground
        g.setColor(new Color(0, 150,0));
        g.fillRect(0, 1050, 10000, 30);
        ground = new Rectangle(0, 1050, 100000, 30);


        if(m != null) {
            m.draw();
        }

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