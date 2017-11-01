import org.newdawn.slick.geom.Rectangle; //Allows the collisions method to be applicable for rectangles and lines

public interface Collisions {
    public boolean newIntersects(Rectangle r);
}
