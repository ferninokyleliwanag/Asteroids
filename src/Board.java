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
        if(GAMESTATES.PLAY) {
            for(int i = 0; i < actors.size(); i++) {
                actors.get(i).paint(g);
            }
        } else if(GAMESTATES.PAUSE) {
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 48));
            g.setColor(Color.white);
            printSimpleString("PAUSE", getWidth(), 0, getHeight()/2, g);
            g.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
            printSimpleString("Press P to resume", getWidth(), 0, getHeight()/2 + 50, g);
        } else if(GAMESTATES.MENU) {
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 48));
            g.setColor(Color.white);
            printSimpleString("Asteroids", getWidth(), 0, getHeight()/2, g);
            g.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
            printSimpleString("Use MOUSE to move and SPACE to shoot", getWidth(), 0, getHeight()/2 + 50, g);
            printSimpleString("Press P to pause during gameplay", getWidth(), 0, getHeight()/2 + 100, g);
            printSimpleString("Press ENTER to start", getWidth(), 0, getHeight()/2 + 150, g);
        } else if(GAMESTATES.END) {
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 48));
            g.setColor(Color.white);
            if(GAMESTATES.WIN) {
                printSimpleString("You win!", getWidth(), 0, getHeight()/2, g);
            } else {
                printSimpleString("You lose!", getWidth(), 0, getHeight()/2, g);
            }
            g.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
            printSimpleString("Thanks for playing!", getWidth(), 0, getHeight()/2 + 50, g);
            printSimpleString("Press ESC to quit", getWidth(), 0, getHeight()/2 + 100, g);
        }
    }

    public void enemyShoot() {
        if (System.currentTimeMillis() - eLastMoment >= 750) {
            actors.add(new EnemyBullet(Color.red, actors.get(1).x, actors.get(1).y + actors.get(1).height + 7, 5, 30));
            eLastMoment = System.currentTimeMillis();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(GAMESTATES.PLAY) {
            Sprite remove1 = null;
            Sprite remove2 = null;
            for(Sprite actor1 : actors) {
                actor1.move(getHeight(), getWidth());
                if(actor1 instanceof Enemy) {
                    if(((Enemy) actor1).isDead()) {
                        GAMESTATES.endGame();
                        GAMESTATES.setWin(true);
                    }
                }
                for(Sprite actor2 : actors) {
                    if(actor1 != actor2 && actor1.getBounds().intersects(actor2.getBounds())) {
                        if(actor1 instanceof Bullet && actor2 instanceof Asteroid) {
                            remove1 = actor2;
                            remove2 = actor1;
                        } else if(actor1 instanceof Asteroid && actor2 instanceof Bullet) {
                            remove1 = actor1;
                            remove2 = actor2;
                        }
                        if(actor1 instanceof Player && (actor2 instanceof Asteroid || actor2 instanceof EnemyBullet)) {
                            GAMESTATES.endGame();
                        } else if((actor1 instanceof Asteroid || actor1 instanceof EnemyBullet) && actor2 instanceof Player) {
                            GAMESTATES.endGame();
                        }
                        if(actor1 instanceof Enemy && actor2 instanceof Bullet) {
                            ((Enemy) actor1).bulletHit();
                            remove1 = actor2;
                        } else if(actor1 instanceof Bullet && actor2 instanceof Enemy) {
                            ((Enemy) actor2).bulletHit();
                            remove1 = actor1;
                        }
                    }
                }
            }
            actors.remove(remove1);
            actors.remove(remove2);
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
