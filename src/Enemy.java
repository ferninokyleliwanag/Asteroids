import java.awt.*;

public class Enemy extends Sprite {

    Board board;

    public Enemy(Color color, int x, int y, int diameter){
        super(color, x, y, diameter, diameter);
    }

    @Override
    public void paint(Graphics g){
        g.setColor(color);
        g.fillOval(x, y, width, height);
    }

    @Override
    public void move(int boardWidth, int boardHeight){
        if(x > boardWidth - 100 || x < 0){
            dx *= -1;
        }

        if(y > boardHeight/4 || y < 0){
            dy *= -1;
        }

        x += dx;
        y += dy;


    }
}
