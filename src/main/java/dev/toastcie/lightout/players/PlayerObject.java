package dev.toastcie.lightout.players;

import dev.toastcie.lightout.game.Game;
import dev.toastcie.lightout.tools.*;

import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class PlayerObject {
    int width, height;

    public PlayerObject(int width,int height){
        this.width = width;
        this.height = height;
    }

    abstract public void mouseClicked(Vector2Int pos);
    abstract public void keyPressed(KeyEvent e);
    abstract public void keyReleased(KeyEvent e);
    abstract public Vector2Int getTouchPos(Game instance);

    abstract public void reset();

    abstract public void addGraphics(Graphics g);
}
