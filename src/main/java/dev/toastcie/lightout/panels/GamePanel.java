package dev.toastcie.lightout.panels;

import dev.toastcie.lightout.Constants;
import dev.toastcie.lightout.game.Game;
import dev.toastcie.lightout.players.HumanClick;
import dev.toastcie.lightout.players.HumanCursor;
import dev.toastcie.lightout.players.PlayerObject;
import dev.toastcie.lightout.players.PlayerTemplate;
import dev.toastcie.lightout.tools.Vector2Int;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel {

    public Game game;
    int tileWidth = Constants.SCREEN_WIDTH/Constants.ARRAY_WIDTH;
    int tileHeight = Constants.SCREEN_HEIGHT/Constants.ARRAY_HEIGHT;
    public PlayerObject player = PlayerTemplate.classicCursor;

    public GamePanel(){
        game = new Game(Constants.ARRAY_WIDTH,Constants.ARRAY_HEIGHT);
        game.start(100);
        this.setFocusable(true);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int x = e.getX()/tileWidth;
                int y = e.getY()/tileHeight;

                player.mouseClicked(new Vector2Int(x,y));
            }
        });


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Constants.BACKGROUND_COLOR);
        g.fillRect(0,0,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);

        int tileWidth = Constants.SCREEN_WIDTH/Constants.ARRAY_WIDTH;
        int tileHeight = Constants.SCREEN_HEIGHT/Constants.ARRAY_HEIGHT;

        for(int i = 0; i < Constants.ARRAY_WIDTH;i ++){
            for(int j = 0; j < Constants.ARRAY_HEIGHT;j ++){
                if(game.getelt(i,j)){
                    g.setColor(Constants.TILE_ON);
                }else{
                    g.setColor(Constants.TILE_OFF);
                }
                g.fillRoundRect(i*tileWidth+Constants.TILE_OFFSET,j*tileHeight+Constants.TILE_OFFSET,tileWidth-Constants.TILE_OFFSET*2,tileHeight-Constants.TILE_OFFSET*2,Constants.BORDER_RADIUS,Constants.BORDER_RADIUS);
            }
        }

        player.addGraphics(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800,800);
    }

    public void gameLoop(){
        //repaint();
        Vector2Int cPos = player.getTouchPos();

        if(cPos.x != -1){
            game.play(cPos.x,cPos.y);

            if(game.isWin()){
                game.reset();
                game.start(100);
            }
        }

        repaint();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
