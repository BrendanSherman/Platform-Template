import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public abstract class Item {
    private Box box;
    private Rectangle itemRectangle;
    public SpriteSheet items = new SpriteSheet("resources/images/items/itemsSpriteSheet.png", 100, 100);
    /*
    hi brendan
    to access fireflower, do *imagename* = items.getSubImage(0, 0);
     */
    public Item(Box box) throws SlickException {
        this.box = box;
    }

    public Item() throws SlickException {

    }

    public void drawRect(){}

    public void movement(){}




}
