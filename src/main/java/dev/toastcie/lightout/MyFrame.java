package dev.toastcie.lightout;

import dev.toastcie.lightout.panels.GamePanel;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    public MyFrame(){
        this.setTitle("Light Out");
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new GamePanel());
        this.pack();
    }
    public static void main(String[] args) {
        new MyFrame();
    }
}