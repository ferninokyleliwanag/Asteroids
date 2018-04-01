import java.awt.*;

public class Asteroid extends Sprite{

    public Asteroid(Color color, int x, int y, int width, int height) {
        super(color, x, y, width, height);
    }

    @Override
    public void move(int boardHeight, int boardWidth){
        y += 1;
    }

    @Override
    public void paint(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

}
