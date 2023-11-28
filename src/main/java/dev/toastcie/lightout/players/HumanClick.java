package dev.toastcie.lightout.players;

import dev.toastcie.lightout.tools.Vector2Int;

import java.awt.*;
import java.awt.event.KeyEvent;

public class HumanClick extends PlayerObject{
    boolean[][] clickGrid;
    public HumanClick(int width, int height) {
        super(width, height);
        clickGrid = new boolean[width][height];

        for(int i = 0 ; i < width; i ++){
            for(int j = 0 ; j < width; j ++){
                clickGrid[i][j] = false;
            }
        }
    }

    @Override
    public void mouseClicked(Vector2Int pos) {
        clickGrid[pos.x][pos.y] = true;
    }

    @Override
    public Vector2Int getTouchPos() {
        for(int i = 0 ; i < width; i ++){
            for(int j = 0 ; j < height; j ++){
                if(clickGrid[i][j]){
                    clickGrid[i][j] = false;
                    return new Vector2Int(i,j);
                }
            }
        }
        return new Vector2Int(-1,-1);
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e){}

    @Override
    public void addGraphics(Graphics g) {}
}
