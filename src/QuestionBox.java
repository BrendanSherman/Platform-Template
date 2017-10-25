import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class QuestionBox extends Box{
    private String type = "";
    String url = "resources/images/blocks/questionMarkBlock1.png";

    public QuestionBox(int x, int y, String type) throws SlickException {
        super(x,y);
    }

    @Override
    public void draw() throws SlickException {
        Image questionBox = new Image(url);
        questionBox.draw(this.x, this.y);
    }

    @Override
    public void bottomCollision(Mario mario){
        if(mario.marioHeadCollision(this.bottomLine)){
            this.url = "resources/images/blocks/emptyQuestionBlock.png";

            if(type.equals("shroom")){
                //TODO spawn a mushroom that moves
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
