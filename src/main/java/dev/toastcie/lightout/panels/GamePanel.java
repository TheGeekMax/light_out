package dev.toastcie.lightout.panels;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.pink);
        g.fillRect(0,0,800,800);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800,800);
    }
}
