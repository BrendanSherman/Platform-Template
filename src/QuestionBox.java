import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class QuestionBox extends Box{
    private String type = "";
    boolean used = false;
    String url = "resources/images/blocks/questionMarkBlock1.png";

    public QuestionBox(int x, int y, String type) throws SlickException { //takes constructor from box
        super(x,y);
    }

    @Override
    public void draw() throws SlickException {
        Image questionBox = new Image(url);
        questionBox.draw(this.x, this.y);
    }

    @Override
    public void bottomCollision (Mario mario) throws SlickException{
        if(mario.marioHeadCollision(this.bottomRectangle)){
            this.url = "resources/images/blocks/emptyQuestionBlock.png";
            used = true;

            if(type.equals("shroom")){
                //TODO spawn shroom
            }
            if(type.equals("fire")){
                //TODO spawn fire flower on top of box
            }
            if(type.equals("star")){
                //TODO spawn star
            }
        }
    }

}
