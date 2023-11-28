package dev.toastcie.lightout.tools;
import java.awt.event.KeyEvent;

public class KeyMovement{
    private boolean upPressed =false;
    private boolean leftPressed =false;
    private boolean downPressed =false;
    private boolean rightPressed =false;

    private boolean enterPressed = false;


    private int dx = 0;
    private int dy = 0;

    public int keyUp,keyLeft,keyDown,keyRight,keyEnter;

    public boolean usedUp,usedLeft,usedDown,usedRight,usedEnter;

    public KeyMovement(int keyUp,int keyLeft, int keyDown,int keyRight,int keyEnter){
        this.keyUp = keyUp;
        this.keyRight = keyRight;
        this.keyDown = keyDown;
        this.keyLeft = keyLeft;
        this.keyEnter = keyEnter;

        usedUp = false;
        usedDown = false;
        usedLeft = false;
        usedRight = false;
    }

    private void calculateDc(){
        dx = 0;
        dy = 0;

        if(upPressed && !usedUp){
            usedUp = true;
            dy --;
        }
        if(rightPressed && !usedRight){
            usedRight = true;
            dx ++;
        }
        if(downPressed && !usedDown){
            usedDown = true;
            dy ++;
        }
        if(leftPressed && !usedLeft){
            usedLeft = true;
            dx --;
        }
    }

    public boolean isEnterPressed(){
        if(enterPressed && !usedEnter){
            usedEnter = true;
            return true;
        }
        return false;
    }

    public Vector2Int getDirrection(){
        calculateDc();
        return new Vector2Int(dx,dy);
    }

    public void keyPressed(KeyEvent key){
        int k = key.getKeyCode();
        upPressed = k == keyUp || upPressed;
        rightPressed = k == keyRight || rightPressed;
        downPressed = k == keyDown || downPressed;
        leftPressed = k == keyLeft || leftPressed;
        enterPressed = k == keyEnter || enterPressed;
    }

    public void keyReleased(KeyEvent key){
        int k = key.getKeyCode();
        upPressed = k != keyUp && upPressed;
        rightPressed = k != keyRight && rightPressed;
        downPressed = k != keyDown && downPressed;
        leftPressed = k != keyLeft && leftPressed;
        enterPressed = k != keyEnter && enterPressed;

        usedUp = k != keyUp && usedUp;
        usedLeft = k != keyLeft && usedLeft;
        usedDown = k != keyDown && usedDown;
        usedRight = k != keyRight && usedRight;
        usedEnter = k != keyEnter && usedEnter;
    }
}