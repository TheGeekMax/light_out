package dev.toastcie.lightout.players.ai;

import dev.toastcie.lightout.tools.Vector2Int;
import java.util.ArrayList;

public class State {

    public ArrayList<Vector2Int> queuePlay;
    public int[][] currentState;

    public State(int[][] currentState){
        this.currentState = currentState;
        queuePlay = new ArrayList<>();
    }

    public void addPlay(State reference, Vector2Int pos){
        queuePlay.addAll(reference.queuePlay);
        queuePlay.add(pos);
    }
}
