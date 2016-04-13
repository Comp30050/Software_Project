package Core;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener{
        private JLabel background;
        private JButton exitButton;
        private MouseListen ml;
        private JTextArea consoleOutput;
        private String algorithm;

        public GUI(Core c) {
                super("Comp30050 - Software Enginnering (Map Students to projects)");
                consoleOutput = new JTextArea();
                background = new JLabel();
                exitButton = new JButton();
                ml = new MouseListen(c);

                this.drawBackground();
                this.addComboBox();
                this.makeConsoleDisplay();

                this.buildFrame();

                //updateConsoleDisplay("Hi Gary");
        }

        private void drawBackground() {
                background.setLocation(0, 0);
                background.setSize(700, 400);

                exitButton = new JButton("Exit");
                exitButton.setLocation(630, 335);
                exitButton.setSize(55, 25);
                exitButton.addMouseListener(ml);
                background.add(exitButton);
                exitButton.setVisible(true);
        }

        private void addComboBox() {

                //create ComboBox and set default algorithm to be Simulated Annealing
                JComboBox algorithmsBox = new JComboBox(new String[]{"Simulated Annealing", "Genetic"});
                algorithmsBox.setSelectedIndex(0);
                algorithm = (String) algorithmsBox.getItemAt(0);        //Set default algorithm
                algorithmsBox.setSize(150,25);
                algorithmsBox.addActionListener(this);
                algorithmsBox.setLocation(50,50);

                background.add(algorithmsBox);
                algorithmsBox.setVisible(true);
        }

        private void makeConsoleDisplay() {
                consoleOutput.setLocation(50, 90);
                consoleOutput.setSize(300, 100);
                consoleOutput.setEditable(false);
                background.add(consoleOutput);
                exitButton.setVisible(true);
        }

        public void updateConsoleDisplay(String toDisplay) {
                consoleOutput.append(toDisplay);
                background.add(consoleOutput);
                consoleOutput.setVisible(true);
        }

        public void buildFrame() {
                this.setSize(700, 400);
                this.setResizable(false);
                this.setLocationRelativeTo(null);
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                this.add(background);
                this.setVisible(true);
        }

        public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                String alg = (String)cb.getSelectedItem();

                algorithm = alg;
        }

        public String getAlgorithm() {
                return this.algorithm;
        }

        public JButton getExitButton() {
                return exitButton;
        }
}