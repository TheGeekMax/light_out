package dev.toastcie.lightout.panels;

import dev.toastcie.lightout.Constants;
import dev.toastcie.lightout.animation.GameAnimation;
import dev.toastcie.lightout.game.Game;
import dev.toastcie.lightout.players.PlayerObject;
import dev.toastcie.lightout.players.PlayerTemplate;
import dev.toastcie.lightout.tools.Vector2Int;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel {

    public Game game;
    int tileWidth = Constants.SCREEN_WIDTH/Constants.ARRAY_WIDTH;
    int tileHeight = Constants.SCREEN_HEIGHT/Constants.ARRAY_HEIGHT;
    public PlayerObject player = PlayerTemplate.classicCursor;

    //pour les animations
    private GameAnimation animPlateau;

    public GamePanel(){
        animPlateau = new GameAnimation(Constants.ARRAY_WIDTH,Constants.ARRAY_HEIGHT);
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

        //pour l'animation
        for(int i = 0; i < Constants.ARRAY_WIDTH;i ++){
            for(int j = 0; j < Constants.ARRAY_HEIGHT;j ++){
                if(animPlateau.isInAnimation(i,j)) {
                    if (game.getelt(i, j)) {
                        g.setColor(Constants.TILE_OFF);
                    } else {
                        g.setColor(Constants.TILE_ON);
                    }
                    int wi_offset = (int) GameAnimation.lerp(animPlateau.getStep(i,j),0,GameAnimation.MAX_STEP,Constants.TILE_OFFSET, (double) Constants.SCREEN_WIDTH /(Constants.ARRAY_WIDTH*2));
                    int he_offset = (int) GameAnimation.lerp(animPlateau.getStep(i,j),0,GameAnimation.MAX_STEP,Constants.TILE_OFFSET, (double) Constants.SCREEN_HEIGHT /(Constants.ARRAY_HEIGHT*2));
                    g.fillRoundRect(i * tileWidth + wi_offset, j * tileHeight + he_offset, tileWidth - wi_offset * 2, tileHeight - he_offset * 2, Constants.BORDER_RADIUS, Constants.BORDER_RADIUS);
                }
            }
        }
        animPlateau.step();

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
            animPlateau.play(cPos.x,cPos.y);

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
