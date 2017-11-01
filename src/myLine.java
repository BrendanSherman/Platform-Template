import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;

public class myLine extends Line implements Collisions{
    public myLine(float x, float y, float x2, float y2){
        super(x, y, x2, y2);
    }

    public boolean newIntersects(Rectangle r){
        if(this.intersects(r)){
            return true;
        }
        return false;
    }
}
