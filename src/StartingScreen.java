import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StartingScreen implements KeyListener {
    private JPanel StartingScreen;
    private JButton StartingButton;
    private JTextField seedField;
    private JTextField mapSizeField;
    private JLabel mapSize;
    private DungeonGUI dungeonGUI;
    private nothingToSeeHereGUI nothingGUI;
    private int seed;
    private JFrame frame;

    public StartingScreen() {
        frame = new JFrame("Journey for Monkey Totem");
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        mapSizeField.setText(Integer.toString(25));
        frame.setLocation(width / 3, height / 3);
        frame.setContentPane(StartingScreen);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.addKeyListener(this);
        StartingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            if(seedField.getText().equals("meme")) {
                                nothingGUI = new nothingToSeeHereGUI();
                                frame.getContentPane().removeAll();
                                frame.setSize(569, 600);
                                frame.setContentPane(nothingGUI.getNothing());
                                frame.setVisible(true);
                            }
                            else {
                                DungeonGUI.initializeMapSize(mapSizeField.getText());
                                dungeonGUI = new DungeonGUI(seedField.getText(), mapSizeField.getText());
                                frame.getContentPane().removeAll();
                                frame.setContentPane(dungeonGUI.getDungeon());
                                frame.pack();
                                frame.setVisible(true);
                                frame.setFocusable(true);
                                frame.addKeyListener(dungeonGUI);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });

            }
        });

    }

    public JPanel getPanel() {
        return StartingScreen;
    }

    public JButton getButton() {
        return StartingButton;
    }

    public int getSeed() {
        return seed;
    }

    public JPanel getStart() {
        return StartingScreen;
    }

    public JFrame getFrame(){return frame;}


    public static void main(String[] args) {
        new StartingScreen();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
