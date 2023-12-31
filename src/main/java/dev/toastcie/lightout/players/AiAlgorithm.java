package dev.toastcie.lightout.players;

import dev.toastcie.lightout.Constants;
import dev.toastcie.lightout.game.Game;
import dev.toastcie.lightout.tools.Vector2Int;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class AiAlgorithm extends PlayerObject{

    ArrayList<Vector2Int> queuePlay;

    Vector2Int lastPlay;

    boolean state = true; //false -> falling down, true -> addon
    int currentHeight = 0;

    int currentFrame = 0;
    public AiAlgorithm(int width, int height) {
        super(width, height);
        queuePlay = new ArrayList<>();
        lastPlay = new Vector2Int(-1,-1);
    }

    @Override
    public void mouseClicked(Vector2Int pos) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public Vector2Int getTouchPos(Game instance) {
        //on verif que c'est une bonne frame
        if(currentFrame++ < 30){
            return new Vector2Int(-1,-1);
        }
        currentFrame = 0;

        //on change l'etat
        if(queuePlay.isEmpty()){
            if(state){
                //passage en mode falling down
                currentHeight = 0;
                state = false;
                calculateFalling(instance);
            }else if(currentHeight == height-1){
                //passage en mode addon
                state = true;
                calculateAddon(instance);
            }else{
                //calcul nouvelle ligne
                calculateFalling(instance);
            }
        }

        if(queuePlay.isEmpty()){
            return new Vector2Int(-1,-1);
        }
        lastPlay = queuePlay.remove(0);

        return lastPlay;
    }

    private void calculateFalling(Game instance){
        for(int i = 0; i < width; i ++){
            if(instance.getelt(i,currentHeight)){
                queuePlay.add(new Vector2Int(i,currentHeight+1));
            }
        }
        currentHeight++;
    }

    private void calculateAddon(Game instance){
        int currentCode = getLineCode(instance);

        if(currentCode == 17){
            queuePlay.add(new Vector2Int(0,0));
            queuePlay.add(new Vector2Int(1,0));
        }else if(currentCode == 10){
            queuePlay.add(new Vector2Int(0,0));
            queuePlay.add(new Vector2Int(3,0));
        }else if(currentCode == 7){
            queuePlay.add(new Vector2Int(1,0));
        }else if(currentCode == 28){
            queuePlay.add(new Vector2Int(3,0));
        }else if(currentCode == 13){
            queuePlay.add(new Vector2Int(4,0));
        }else if(currentCode == 22){
            queuePlay.add(new Vector2Int(0,0));
        }else if(currentCode == 27){
            queuePlay.add(new Vector2Int(2,0));
        }
    }

    private int getLineCode(Game instance){
        int lcode = 0;
        for(int i = width-1; i >=0; i --){
            lcode *=2;
            lcode += (instance.getelt(i,height-1))?1:0;
        }
        return lcode;
    }

    @Override
    public void reset() {
        queuePlay = new ArrayList<>();
        currentFrame = 0;
        currentHeight = 0;
    }

    @Override
    public void addGraphics(Graphics g) {
        if(lastPlay.x == -1) return;

        Graphics2D g2d = (Graphics2D) g;

        //on imprime le coup

        int wi = Constants.SCREEN_WIDTH/Constants.ARRAY_WIDTH;
        int he = Constants.SCREEN_HEIGHT/Constants.ARRAY_HEIGHT;

        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(Color.decode("#751522"));
        g2d.drawRoundRect(lastPlay.x*wi, lastPlay.y*he,wi,he,Constants.BORDER_RADIUS,Constants.BORDER_RADIUS);
    }
}
