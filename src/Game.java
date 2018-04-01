import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Game extends JFrame {

    Board board;

    public Game(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setTitle("ASTEROIDS");
        board = new Board();
        add(board);
        pack();
        setLocationRelativeTo(null);


        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(GAMESTATES.PLAY) {
                    if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                        board.shootBullet();
                    }
                    if(e.getKeyCode() == KeyEvent.VK_P) {
                        GAMESTATES.pauseGame();
                    }
                } else if(GAMESTATES.PAUSE) {
                    if(e.getKeyCode() == KeyEvent.VK_P) {
                        GAMESTATES.resumeGame();
                    }
                } else if(GAMESTATES.MENU) {
                    if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                        GAMESTATES.startGame();
                    }
                } else if(GAMESTATES.END) {
                    if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        System.exit(0);
                    }
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                board.setPposition(e.getX(), e.getY());
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                setCursor(getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0,0), null));
            }
        });


    }

    public static void main(String[] args){
        Game game = new Game();
        game.board.setup();
    }


}
