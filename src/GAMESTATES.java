public class GAMESTATES {

    public static boolean MENU = true;
    public static boolean PLAY = false;
    public static boolean PAUSE = false;
    public static boolean END = false;
    public static boolean WIN = false;

    public static void setMenu(boolean menu) {
        MENU = menu;
    }

    public static void setPlay(boolean play) {
        PLAY = play;
    }

    public static void setPause(boolean pause) {
        PAUSE = pause;
    }

    public static void setEnd(boolean end) {
        END = end;
    }

    public static void setWin(boolean win) {
        WIN = win;
    }

    public static void startGame() {
        setMenu(false);
        setPlay(true);
        setPause(false);
        setEnd(false);
    }

    public static void pauseGame() {
        setMenu(false);
        setPlay(false);
        setPause(true);
        setEnd(false);
    }

    public static void resumeGame() {
        setMenu(false);
        setPlay(true);
        setPause(false);
        setEnd(false);
    }

    public static void endGame() {
        setMenu(false);
        setPlay(false);
        setPause(false);
        setEnd(true);
    }

    public static void menu() {
        setMenu(true);
        setPlay(false);
        setPause(false);
        setEnd(true);
    }

}
