abstract class Enemy{
//Basic enemy class should have walking jumping and collisions

    abstract void death();

    abstract void draw();

    abstract public boolean checkTopCollision();
    abstract public boolean checkLeftCollision();
    abstract public boolean checkRightCollision();
}
