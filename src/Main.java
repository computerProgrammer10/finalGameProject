import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Main {
    public static void main(String[] args) {
        JFrame startFrame = new JFrame();
        startFrame.setSize(600,600);
        JPanel defaultPanel = new JPanel();
        JLabel startLabel = new JLabel("What game mode do you wanna play?", SwingConstants.CENTER);
        defaultPanel.setLayout(new BorderLayout());
        startLabel.setText("Which game mode do you want to play?");
        startLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));

        JButton button1 = new JButton(), button2 = new JButton();
        JPanel centerPanel = new JPanel();
        button1.setText("Levels");
        button2.setText("Infinite");
        defaultPanel.add(startLabel, BorderLayout.NORTH); centerPanel.add(button1); centerPanel.add(button2); defaultPanel.add(centerPanel, BorderLayout.CENTER);
        startFrame.add(defaultPanel); startFrame.setVisible(true);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startFrame.setVisible(false);
                JFrame frame = new JFrame();
                frame.setSize(600,600);
                MyPanel panel = new MyPanel(1);
                frame.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        panel.addKey(e.getKeyChar());
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }


                    public void keyReleased(KeyEvent e) {
                        panel.removeKey(e.getKeyChar());
                    }
                });
                frame.add(panel);
                frame.setVisible(true);
            }
        });
    }
}