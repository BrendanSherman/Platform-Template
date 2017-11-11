import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class QuestionBox extends Box{
    String url = "resources/images/blocks/questionMarkBlock1.png";

    public QuestionBox(int x, int y, String type) throws SlickException { //takes constructor from box
        super(x,y);
        used = false;
        this.type = type;
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
        }
    }

}
