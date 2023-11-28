package dev.toastcie.lightout;

import dev.toastcie.lightout.panels.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyFrame extends JFrame {

    GamePanel gamePanel;
    public MyFrame(){
        gamePanel = new GamePanel();

        this.setTitle("Light Out");
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(gamePanel);
        this.pack();

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                gamePanel.player.keyPressed(e);
                gamePanel.repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                gamePanel.player.keyReleased(e);
                gamePanel.repaint();
            }
        });

        mainLoop();
    }

    public void mainLoop(){
        while(true) {
            gamePanel.gameLoop();
        }
    }
    public static void main(String[] args) {
        new MyFrame();
    }
}