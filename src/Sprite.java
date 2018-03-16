import java.awt.*;

public class Sprite implements Move, Paint {

    Color color;
    int x, y, width, height;
    double dx = 10, dy = 5;

    public Sprite(Color color, int x, int y, int width, int height){

        this.color = color;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }




    @Override
    public void move(int boardHeight, int boardWidth) {

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle( x, y, width, height);
    }

    @Override
    public void paint(Graphics g) {

    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public int getY(){
        return y;
    }
}
