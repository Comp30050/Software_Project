package Core;

import javax.swing.*;

public class GUI extends JFrame{
        private JLabel background;
        private JButton exitButton;
        private MouseListen ml;

        public GUI(Core c) {
                super("COMP30050 - Software Engineering");

                background = new JLabel();
                exitButton = new JButton();
                ml = new MouseListen(c);

                this.drawBackground();
        }

        private void drawBackground() {
                background = new JLabel();
                background.setLocation(0, 0);
                background.setSize(700, 400);

                exitButton = new JButton("Exit");
                exitButton.setLocation(620,320);
                exitButton.setSize(75, 75);
                exitButton.addMouseListener(ml);
                background.add(exitButton);
        }
}