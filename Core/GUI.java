package Core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI{
        private JFrame frame;
        private JLabel label;
        private String fileName;
        private FrontEnd f;
        private boolean SAbuttonDrawn = false;
        private boolean GENbuttonDrawn = false;

        public GUI(FrontEnd f) {
                label = new JLabel();
                this.f = f;
                frame = new JFrame("COMP30050 - Software Engineering");

                this.getFile();
                this.buildFrame();
        }
        private void getFile() {
                JButton getFile = new JButton("Open File");
                getFile.setSize(120, 25);
                getFile.setLocation(20,5);
                frame.add(getFile, BorderLayout.PAGE_START);
                getFile.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e) {
                                JFileChooser fc = new JFileChooser();
                                fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

                                //Only progress to showing alg buttons if valid file passed
                                try {
                                        int returnVal = fc.showOpenDialog(getFile);
                                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                                                fileName = fc.getSelectedFile().getPath();
                                                f.initializePrefTable(fileName);
                                                frame.remove(label);
                                                label.setSize(200,30);
                                                label.setLocation(50,130);
                                                label.setText("File opened successfully :)");
                                                frame.add(label);
                                                label.setVisible(true);
                                                frame.repaint();
                                        }

                                        addAlgorithmButtons();  //Displays the algorithm buttons
                                } catch(Exception exp){
                                        frame.remove(label);
                                        label.setSize(200,30);
                                        label.setLocation(50,130);
                                        label.setText("Error, file couldn't be opened :(");
                                        frame.add(label);
                                        label.setVisible(true);
                                        frame.repaint();
                                }
                        }
                });
                getFile.setVisible(true);
        }

        //Nested buttons so that the next operation can only be carried out if prior required settings are set
        private void addAlgorithmButtons() {
                JOptionPane popup = new JOptionPane();
                JButton SAButton = new JButton("Run Simulated Annealing");
                final JButton SAacceptInput = new JButton("Accept SA & Run");
                final JButton GENacceptInput = new JButton("Accept Genetic & Run");

                SAButton.setSize(175,50);
                SAButton.setLocation(20,40);

                frame.add(SAButton);
                label.setSize(275,30);
                label.setLocation(50,130);

                SAButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                if(SAbuttonDrawn) {
                                        closeActionListener();  //stops "people" putting more buttons onto GUI, #hack
                                } else {
                                        SAacceptInput.setSize(175,25);
                                        SAacceptInput.setLocation(20, 100);
                                        SAacceptInput.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                        popup.showMessageDialog(null, "Running!");
                                                        f.initializeSearch(new StochasticAlgorithm(f.pt));
                                                        frame.remove(label);
                                                        label.setText("Done! "+getEnergyRating());
                                                        frame.add(label);
                                                        exitAndSaveButton();
                                                }
                                        });
                                        //Removes the other buttons accept button as they're overlayed, #hack
                                        frame.remove(GENacceptInput);
                                        frame.add(SAacceptInput);
                                        frame.repaint();
                                        label.setVisible(true);
                                        SAacceptInput.setVisible(true);
                                        SAbuttonDrawn = true;
                                        GENbuttonDrawn = false;
                                }
                        }

                        public void closeActionListener() {
                                SAButton.removeActionListener(this);
                        }
                });
                //Allows for two line buttons, #Hack
                String twoLineText = "Run Genetic algorithm\n(~40secs to run)";
                JButton GENButton = new JButton("<html>" + twoLineText.replaceAll("\\n", "<br>") + "</html>");
                GENButton.setSize(175, 50);
                GENButton.setLocation(200, 40);
                frame.add(GENButton);

                GENButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                if(GENbuttonDrawn) {
                                        closeActionListener();  //stops "people" putting more buttons onto GUI, #hack
                                } else {
                                        GENacceptInput.setSize(175,25);
                                        GENacceptInput.setLocation(20, 100);

                                        GENacceptInput.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                        popup.showMessageDialog(null, "Running... this might take a while :o");
                                                        f.initializeSearch(new GeneticAlgorithm(f.pt));
                                                        popup.showMessageDialog(null, "Phew.. Done! ");
                                                        frame.remove(label);
                                                        label.setText("Done! "+getEnergyRating());
                                                        frame.add(label);
                                                        exitAndSaveButton();
                                                }
                                        });
                                        //Removes the other buttons accept button as they're overlayed, #hack
                                        frame.remove(SAacceptInput);
                                        frame.add(GENacceptInput);
                                        frame.repaint();
                                        label.setVisible(true);
                                        GENacceptInput.setVisible(true);
                                        GENbuttonDrawn = true;
                                        SAbuttonDrawn = false;
                                }
                        }
                        public void closeActionListener() {
                                GENButton.removeActionListener(this);
                        }
                });
                GENButton.setVisible(true);
                SAButton.setVisible(true);
                frame.repaint();
        }
        private String getEnergyRating(){
                String energyRating;

                if(f.cs.getEnergy() <= 250) {
                        energyRating = "Excellent mapping generated..";
                } else if(f.cs.getEnergy() <= 300 ) {
                        energyRating = "Good mapping generated..";
                } else if(f.cs.getEnergy() <= 400) {
                        energyRating = "Adequate mapping generated..";
                } else {
                        energyRating = "Poor mapping generated..";
                }
                return energyRating;
        }

        private void exitAndSaveButton() {
                JButton exitButton = new JButton("Exit & Save");
                JOptionPane popup = new JOptionPane();
                exitButton.setLocation(265, 135);
                exitButton.setSize(120, 25);
                frame.add(exitButton);
                frame.repaint();
                exitButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                f.saveFileToOutput();
                                popup.showMessageDialog(null, "Solution exported to .tsv file :D");
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