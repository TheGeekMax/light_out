package dev.toastcie.lightout.animation;

public class GameAnimation {
    int width,height;
    public static final int MAX_STEP = 10;
    int[][] animStep;
    public GameAnimation(int width, int height){
        this.width = width;
        this.height = height;

        animStep = new int[width][height];
        for(int i =0; i < width;i ++){
            for(int j =0; j < height;j ++){
                animStep[i][j] = MAX_STEP;
            }
        }
    }

    public boolean isInAnimation(int x,int y){
        return animStep[x][y] < MAX_STEP;
    }

    public void step(){
        for(int i =0; i < width;i ++){
            for(int j =0; j < height;j ++){
                animStep[i][j] = (animStep[i][j] == MAX_STEP)?MAX_STEP:animStep[i][j]+1;
            }
        }
    }

    public int getStep(int x, int y){
        return animStep[x][y];
    }

    private void toggle(int x, int y){
        if(x < 0 || x >= width || y < 0 || y >= height) return;
        animStep[x][y]= 0;
    }
    public void play(int x, int y){
        toggle(x,y);
        toggle(x-1,y);
        toggle(x+1,y);
        toggle(x,y+1);
        toggle(x,y-1);
    }

    public static double lerp(double t, double min, double max, double a, double b) {
        if (t < min || t > max) {
            throw new IllegalArgumentException("t must be within the range [min, max]");
        }
        double normalizedT = (t - min) / (max - min);
        return (1 - normalizedT) * a + normalizedT * b;
    }
}
