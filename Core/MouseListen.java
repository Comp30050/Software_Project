package Core;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Gary on 11/04/2016.
 */
public class MouseListen implements MouseListener {
        private Core algorithmCore;

        public MouseListen(Core c) {
                this.algorithmCore = c;
        }

        public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)) {
                        algorithmCore.leftButtonClicked(e.getSource());
                }
        }

        public void mousePressed(MouseEvent e) {

        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }
}
