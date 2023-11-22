package dev.toastcie.lightout.tools;

public class Vector2Int {
    public int x,y;

    public Vector2Int(int x, int y){
        this.x = x;
        this.y = y;

    }

    public int moduleSquared(){
        return x*x + y*y;
    }
}
