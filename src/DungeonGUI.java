import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DungeonGUI extends JFrame implements KeyListener {
    private JPanel panel1;
    private JButton Forward;
    private DungeonView View;
    private JButton Right;
    private JButton Left;
    private JButton Attack;
    private JProgressBar healthpoints;
    private JButton Take;
    private JLabel Eq;
    private JButton Heal;
    private Core core;
    private static int mapsize;
    private EndGUI endGUI;

    public static void initializeMapSize(String size) {
        mapsize = Integer.parseInt(size);
    }

    public DungeonGUI(String seed, String mapsize) {
        super("DungeonGame");
        this.mapsize = Integer.parseInt(mapsize);
        core = new Core(seed, mapsize);
        getCore().getMovement().subscribe(View);
        View.setIcon(core.getMovement().SetImage());


        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;


        Forward.addActionListener(new ActionListener() {                    //Przyciski ruchu
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    core.moveForward();
                    View.setIcon(core.getMovement().SetImage());
                    core.Canwefight();
                } catch (Ending ending) {
                    //JFrame frame = new JFrame("YOU WON!");
                    //JOptionPane.showMessageDialog(frame, "You've found Monkey Totem! Good job, Champion!");
                    endGUI = new EndGUI();

                }
                Eq.setText(core.getText());
            }
        });

        Left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                core.moveLeft();

                View.setIcon(core.getMovement().SetImage());
                System.out.println(core.getMovement().getCheat());
                Eq.setText(core.getText());
            }
        });
        Right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                core.moveRight();

                View.setIcon(core.getMovement().SetImage());
                Eq.setText(core.getText());
            }
        });
        Attack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    core.Endoffight();
                    healthpoints.setValue(healthpoints.getValue() - core.Mortal());
                    Eq.setText(core.getText());
                } catch (Dead dead) {
                    JFrame frame = new JFrame("YOU DIED!");
                    JOptionPane.showMessageDialog(frame, "YOU DIED!");
                }
            }
        });
        Take.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                core.canITakeIt();
                Eq.setText(core.getText());

            }
        });

        Heal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                core.Heal();
                healthpoints.setValue(core.getChampion().getHealth());
                Eq.setText(core.getText());
            }
        });

        healthpoints.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

            }
        });


    }

    public JPanel getDungeon() {
        return panel1;
    }

    public Core getCore() {
        return core;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void setFocusable(boolean b) {
        super.setFocusable(b);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            try {
                core.moveForward();
                View.setIcon(core.getMovement().SetImage());
                core.Canwefight();
                Eq.setText(core.getText());
            } catch (Ending ending) {
                endGUI = new EndGUI();

            }
            Eq.setText(core.getText());
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            core.moveLeft();
            View.setIcon(core.getMovement().SetImage());
            Eq.setText(core.getText());
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            core.moveRight();
            View.setIcon(core.getMovement().SetImage());
            Eq.setText(core.getText());
        }
        if (e.getKeyCode() == KeyEvent.VK_G) {
            try {
                core.Endoffight();
                healthpoints.setValue(healthpoints.getValue() - core.Mortal());
                Eq.setText(core.getText());
            } catch (Dead dead) {
                JFrame frame = new JFrame("YOU DIED!");
                JOptionPane.showMessageDialog(frame, "YOU DIED!");
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            core.canITakeIt();
            Eq.setText(core.getText());
        }
        if (e.getKeyCode() == KeyEvent.VK_H){
            core.Heal();
            healthpoints.setValue(core.getChampion().getHealth());
            Eq.setText(core.getText());
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        View = new DungeonView(mapsize);
       // panel1.add(View, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

    }

}
