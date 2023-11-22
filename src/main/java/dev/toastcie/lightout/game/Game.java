package dev.toastcie.lightout.game;

import java.util.Random;

public class Game{
    int width, height;

    boolean[][] tiles;
    public Game(int width, int height){
        this.width = width;
        this.height = height;

        tiles = new boolean[width][height];
        reset();
    }

    private void toggle(int x, int y){
        if( x < 0 || x >= width || y < 0 || y >= height) return;
        tiles[x][y] = !tiles[x][y];
    }

    public void play(int x, int y){
        toggle(x,y);
        toggle(x+1,y);
        toggle(x-1,y);
        toggle(x,y+1);
        toggle(x,y-1);
    }

    public void reset(){
        for(int i = 0; i < width; i ++){
            for(int j = 0; j < height; j ++){
                tiles[i][j] = false;
            }
        }
    }

    public void start(int count){
        Random rn = new Random();
        for(int i = 0; i < count; i++){
            int x = rn.nextInt(width);
            int y = rn.nextInt(height);
            play(x,y);
        }
    }

    public boolean isWin(){
        for(int i = 0; i < width; i ++){
            for(int j = 0; j < height; j ++){
                if(tiles[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean getelt(int x, int y) {
        return tiles[x][y];
    }
}
