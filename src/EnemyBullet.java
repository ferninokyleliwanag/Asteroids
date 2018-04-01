import java.awt.*;

public class EnemyBullet extends Sprite {

    Board board;
    private boolean isRemove;

    public EnemyBullet(Color color, int x, int y, int width, int height){

        super(color, x, y, width, height);
        isRemove = false;
    }

    @Override
    public void move(int boardHeight, int boardWidth){
        if( y < 0 || y > boardHeight){
            isRemove = true;
        }

        y += 20;

    }

    @Override
    public void paint(Graphics g){

        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    public boolean isRemove(){
        return isRemove;
    }


}
