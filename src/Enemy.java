import java.awt.*;

public class Enemy extends Sprite {

    Board board;

    private int health = 5;
    private long lastHit = 0, lastShoot = System.currentTimeMillis();
    public Enemy(Color color, int x, int y, int diameter){
        super(color, x, y, diameter, diameter);
    }

    @Override
    public void paint(Graphics g){
        g.setColor(color);
        g.fillOval(x, y, width, height);
        g.setColor(Color.white);
        printSimpleString("" + health, width, x, y+(height/2)+15, g);
    }

    public void bulletHit() {
        if (System.currentTimeMillis() - lastHit >= 500) {
            health--;
            lastHit = System.currentTimeMillis();
        }
    }

    public boolean isDead() {
        return health <= 0;
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

    private void printSimpleString(String s, int width, int XPos, int YPos, Graphics g2d){
        //returns the LENGTH of the STRING parameter to the variable stringLen
        int stringLen = (int)g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
        //determines the center of the WIDTH parameter and subtracts the center of the length
        //to determine the X value to start the string
        int start = width/2 - stringLen/2;
        //prints s at the desired X position with adjustment and the desired y.
        g2d.drawString(s, start + XPos, YPos);
    }

}
