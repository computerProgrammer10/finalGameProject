import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(600,600);
        MyPanel panel = new MyPanel();
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                panel.addKey(e.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Overrided
            public void keyReleased(KeyEvent e) {
                panel.removeKey(e.getKeyChar());
            }
        });
        frame.add(panel);
        frame.setVisible(true);
    }
}