import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Board extends JPanel implements ActionListener{

    boolean isPlay = true;
    int asteroidCount = 10;
    Timer timer;
    List<Sprite> actors = new ArrayList<>();



    private long lastMoment, currentMoment, aLastMoment, aCurrentMoment, eCurrentMoment, eLastMoment;





    public Board(){
        setPreferredSize(new Dimension(800,800));
        setBackground(Color.BLACK);
        timer = new Timer(1000/60, this);
        timer.start();
        lastMoment = System.currentTimeMillis();
        aLastMoment = System.currentTimeMillis();
        eLastMoment = System.currentTimeMillis();
    }

    public void setup() {

        actors.add(0, new Player(Color.yellow, getWidth() / 2, getHeight() / 2, 35, 35));

        actors.add(new Enemy(Color.blue, getWidth()/2 , 200, 100));


        while(isPlay){
            addAsteroid();
            enemyShoot();
        }



        while(isPlay){
            System.out.println(actors.size());
        }

    }

    public void setPposition(int x, int y){
        actors.get(0).setPosition(x, y);

    }

    public void shootBullet(){
        currentMoment = System.currentTimeMillis();
        if(currentMoment - lastMoment > 500) {
            actors.add(new Bullet(Color.yellow, actors.get(0).x + 7, actors.get(0).y - 50, 5, 30));
            lastMoment = System.currentTimeMillis();
        }
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i = 0; i < actors.size(); i++){
            actors.get(i).paint(g);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        for(int i = 1; i < actors.size(); i++){
            actors.get(i).move(getHeight(), getWidth());
        }

        repaint();

    }

    public void addAsteroid(){

        aCurrentMoment = System.currentTimeMillis();
        if(aCurrentMoment - aLastMoment > 2500) {
            for (int i = 0; i < asteroidCount; i++) {
                actors.add(new Asteroid(Color.yellow, (int) (Math.random() * getWidth()), (int) (Math.random() * -20), 25, 25));
                if (actors.get(i) instanceof Asteroid) {
                    if (actors.get(i).getY() > getHeight()) {
                        actors.remove(i);
                    }
                }
                aLastMoment = System.currentTimeMillis();
            }
        }
    }

    //add enemy shoot bullet
    public void enemyShoot(){
        eCurrentMoment = System.currentTimeMillis();
        if(eCurrentMoment - eLastMoment > 1000) {

        }
    }

}
