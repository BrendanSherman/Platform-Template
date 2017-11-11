import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public abstract class Item {
    String dir = "right";
    boolean bottomCollision;
    private Box box;
    Rectangle itemRectangle;
    Rectangle narrowItemRectangle;
    int x;
    int y;
    public SpriteSheet items = new SpriteSheet("resources/images/items/itemsSpriteSheet.png", 100, 100);

    public Item(Box box) throws SlickException {
        this.box = box;
        this.x = box.x;
        this.y = box.y - 100;
        drawRect();
    }

    public Item(int x, int y) throws SlickException {
        this.x = x;
        this.y = y;
    }

    public void drawRect(){
        itemRectangle = new Rectangle(this.x, this.y, 100, 100);
        narrowItemRectangle = new Rectangle(this.x, this.y+45, 100, 10);
    }

    public void move(){}

    public void collision() throws SlickException{};

    public void fall(){
        this.y+=2;
    }
}
