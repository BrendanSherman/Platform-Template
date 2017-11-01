import org.newdawn.slick.geom.Rectangle;

public class myRectangle extends Rectangle implements Collisions{
    public myRectangle(float x, float y, float width, float height){
        super(x, y, width, height);
    }

    public boolean newIntersects(Rectangle r){
        if(this.intersects(r)){
            return true;
        }
        return false;
    }
}
