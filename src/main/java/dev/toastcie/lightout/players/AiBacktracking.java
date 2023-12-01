package dev.toastcie.lightout.players;

import dev.toastcie.lightout.game.Game;
import dev.toastcie.lightout.players.ai.Backtracking;
import dev.toastcie.lightout.tools.Vector2Int;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class AiBacktracking extends PlayerObject{
    int currentFrame = 0;
    ArrayList<Vector2Int> queuePlay;
    public AiBacktracking(int width, int height) {
        super(width, height);
        queuePlay = new ArrayList<>();
    }

    @Override
    public void mouseClicked(Vector2Int pos) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public Vector2Int getTouchPos(Game instance) {
        if(currentFrame++ < 30){
            return new Vector2Int(-1,-1);
        }
        currentFrame = 0;
        if(queuePlay.isEmpty()){
            Backtracking nouveau = new Backtracking(instance.getArray());
            nouveau.calculateQueueList();
            queuePlay = nouveau.getBestPlay();
        }
        return queuePlay.remove(0);
    }

    @Override
    public void reset() {
        queuePlay.clear();
        currentFrame = 0;
    }

    @Override
    public void addGraphics(Graphics g) {

    }
}
