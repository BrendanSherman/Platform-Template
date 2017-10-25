import org.newdawn.slick.geom.Rectangle;

public abstract class Item {
    private Box box;
    private Rectangle itemRectangle;

    public Item(Box box){
        this.box = box;
    }

    public Item(){

    }

    public void drawRect(){
        itemRectangle = new Rectangle(box.getX(), box.getY(), 100, 100);
    }

    public void movement(){

    }




}
