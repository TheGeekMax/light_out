package dev.toastcie.lightout.players;

import dev.toastcie.lightout.Constants;
import dev.toastcie.lightout.game.Game;
import dev.toastcie.lightout.tools.KeyMovement;
import dev.toastcie.lightout.tools.Vector2Int;

import java.awt.*;
import java.awt.event.KeyEvent;

public class HumanCursor extends PlayerObject{

    int x = 0;
    int y = 0;

    KeyMovement keymap = new KeyMovement(KeyEvent.VK_Z,KeyEvent.VK_Q,KeyEvent.VK_S,KeyEvent.VK_D,KeyEvent.VK_ENTER);
    public HumanCursor(int width, int height) {
        super(width, height);
    }

    @Override
    public void mouseClicked(Vector2Int pos) {}

    @Override
    public void keyPressed(KeyEvent e) {
        keymap.keyPressed(e);

        //on s'occupe de bouger le cursor
        System.out.println("...");
        Vector2Int dv = keymap.getDirrection();
        x += dv.x;
        y += dv.y;
        x = Math.min(Math.max(x,0),width-1);
        y = Math.min(Math.max(y,0),height-1);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keymap.keyReleased(e);
    }

    @Override
    public Vector2Int getTouchPos(Game instance) {
        if(keymap.isEnterPressed()){
            return new Vector2Int(x,y);
        }
        return new Vector2Int(-1,-1);
    }

    @Override
    public void addGraphics(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.red);
        g2d.setStroke(new BasicStroke(5));
        int wid = Constants.SCREEN_WIDTH/Constants.ARRAY_WIDTH;
        int hei = Constants.SCREEN_HEIGHT/Constants.ARRAY_HEIGHT;
        g2d.drawRoundRect(x*wid,y*hei,wid,hei,Constants.BORDER_RADIUS,Constants.BORDER_RADIUS);
    }

    @Override
    public void reset() {
        x = 0;
        y = 0;
    }
}
