package dev.toastcie.lightout;

import dev.toastcie.lightout.panels.GamePanel;

import javax.swing.*;
import java.awt.*;

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

        //mainLoop();
    }

    public void mainLoop(){
        gamePanel.gameLoop();
    }
    public static void main(String[] args) {
        new MyFrame();
    }
}