package dev.toastcie.lightout.players;

import dev.toastcie.lightout.tools.*;

import java.awt.event.KeyEvent;

public abstract class PlayerObject {

    abstract void mouseClicked(Vector2Int pos);
    abstract void keyPressed(KeyEvent e);
    abstract void keyReleased(KeyEvent e);
    abstract Vector2Int getTouchPos();
}
