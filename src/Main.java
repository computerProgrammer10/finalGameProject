import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Opt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");
        JFrame frame = new JFrame();
        frame.setSize(500,500);
        MyPanel panel = new MyPanel();
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                panel.addKey(e.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                panel.removeKey(e.getKeyChar());
            }
        });
        frame.add(panel);
        frame.setVisible(true);
    }
}