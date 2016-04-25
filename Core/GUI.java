package Core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI{
        private JFrame frame;
        private JLabel background;
        private String fileName;
        private FrontEnd f;

        public GUI(FrontEnd f) {
                this.f = f;
                frame = new JFrame("COMP30050 - Software Enginnering");
                background = new JLabel();

                this.drawBackground();
                this.getFile();

                this.buildFrame();
        }

        private void drawBackground() {
                background.setLayout(new FlowLayout());
                background.setLocation(0, 0);
                background.setSize(400, 200);
        }

        private void getFile() {
                JButton getFile = new JButton("Open File");
                getFile.setSize(120, 25);
                getFile.setLocation(5,5);
                frame.add(getFile, BorderLayout.PAGE_START);
                getFile.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e) {
                                JFileChooser fc = new JFileChooser();
                                fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

                                int returnVal = fc.showOpenDialog(getFile);

                                if (returnVal == JFileChooser.APPROVE_OPTION) {
                                        fileName = fc.getSelectedFile().getName();
                                        f.initializePrefTable(fileName);
                                }

                                addAlgorithmButtons();

                        }
                });
                getFile.setVisible(true);
        }

        private void addAlgorithmButtons() {
                JPanel algPane = new JPanel();
                algPane.setLayout(new FlowLayout());
                algPane.setSize(350,30);
                algPane.setLocation(50,50);

                JButton SAButton = new JButton("Simulated Annealing");
                SAButton.setSize(150,25);
                algPane.add(SAButton);
                JPanel algOptions = new JPanel(new FlowLayout());

                SAButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                algOptions.removeAll();

                                JButton acceptInput = new JButton("Accept & Run");
                                algOptions.setLocation(50,50);
                                acceptInput.setSize(125,25);
                                acceptInput.setLocation(50, 85);
                                algOptions.setSize(400,150);

                                acceptInput.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                                f.initializeSearch(new StochasticSearch(f.pt));
                                                exitAndSaveButton();
                                                frame.repaint();
                                        }
                                });
                                frame.add(acceptInput);
                                frame.repaint();
                                acceptInput.setVisible(true);
                        }
                });

                JButton GENButton = new JButton("Genetic");
                GENButton.setSize(150,25);
                GENButton.setLocation(160, 0);
                algPane.add(GENButton);
                GENButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                algOptions.removeAll();
                                JButton acceptInput = new JButton("Accept & Run");
                                algOptions.setLocation(50,50);
                                acceptInput.setSize(125,25);
                                acceptInput.setLocation(50, 85);
                                algOptions.setSize(400,30);

                                acceptInput.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                                f.initializeSearch(new GeneticSearch(f.pt));
                                                exitAndSaveButton();
                                                frame.repaint();
                                        }
                                });
                                frame.add(GENButton);
                                frame.repaint();
                                acceptInput.setVisible(true);
                        }
                });
                GENButton.setVisible(true);
                algPane.setVisible(true);
                frame.add(algPane);
                frame.repaint();
        }

        private void exitAndSaveButton() {
                JButton exitButton = new JButton("Exit & Save");
                exitButton.setLocation(265, 135);
                exitButton.setSize(120, 25);
                frame.add(exitButton, BorderLayout.SOUTH);
                frame.repaint();
                exitButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                f.saveFileToOutput();
                                frame.dispose();
                        }
                });
                exitButton.setVisible(true);
        }

        public void buildFrame() {
                frame.setLayout(new BorderLayout());
                frame.setSize(400, 200);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                frame.setVisible(true);
        }
}