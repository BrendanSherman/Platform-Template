import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public class Pipe extends Box{
    String url = "resources/images/blocks/pipe.png";

    public Pipe(int x, int y)throws SlickException{
        super(x,y);
    }

    @Override
    public void draw() throws SlickException{
        Image pipe = new Image(url);
        pipe.draw(this.x, this.y);
    }

    @Override
    public void drawLines(){
        lines = new ArrayList(4);
        topRectangle = new myRectangle(x, y, 256, 4);
        leftLine = new myLine(x, y-10, x, y + 266);
        rightLine = new myLine(x + 256, y-10, x + 256, y + 266);
        bottomRectangle = new myRectangle(x+10, y+246, 256, 10);

        lines.add(topRectangle);
        lines.add(leftLine);
        lines.add(rightLine);
        lines.add(bottomRectangle);
    }

    @Override
    public void Animate(){

    }

}
