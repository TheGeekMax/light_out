package dev.toastcie.lightout.players.ai;

import dev.toastcie.lightout.players.ai.storageType.*;
import dev.toastcie.lightout.tools.Vector2Int;

import java.util.ArrayList;

public class Backtracking {

    StorageTemplate storage;
    ArrayList<State> queuePlay;

    ArrayList<Vector2Int> bestPlay;

    public Backtracking(int[][] initialTable){
        storage = new ArrayListStorage();
        queuePlay = new ArrayList<>();
        bestPlay = new ArrayList<>();

        // on ajoute le tableau initial
        storage.add(initialTable);
        queuePlay.add(new State(initialTable));
    }

    private boolean isWin(int[][] table){
        for(int i = 0; i < table.length; i++){
            for(int j = 0; j < table[i].length; j++){
                if(table[i][j] == 1){
                    return false;
                }
            }
        }
        return true;
    }

    public ArrayList<Vector2Int> getBestPlay(){
        return (ArrayList<Vector2Int>) bestPlay.clone();
    }

    private boolean compare(int[][] elt, int[][] table) {
        for(int i = 0; i < elt.length;i ++){
            for(int j = 0; j < elt[i].length;j ++){
                if(elt[i][j] != table[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    private int[][] copy(int[][] table){
        int[][] newTable = new int[table.length][table[0].length];
        for(int i = 0; i < table.length;i ++){
            for(int j = 0; j < table[i].length;j ++){
                newTable[i][j] = table[i][j];
            }
        }
        return newTable;
    }

    public void calculateQueueList(){
        int t =0;
        while(!queuePlay.isEmpty()){
            //System.out.println(queuePlay.size());
            State currentState = queuePlay.remove(0);
            if( t < currentState.queuePlay.size()){
                t = currentState.queuePlay.size();
                //System.out.println(t);
            }
            if(isWin(currentState.currentState)){
                //System.out.println("win");
                bestPlay = currentState.queuePlay;
                return;
            }
            for(int i = 0; i < currentState.currentState.length; i++){
                for(int j = 0; j < currentState.currentState[i].length; j++){
                    int[][] newTable = copy(currentState.currentState);
                    newTable[i][j] = (newTable[i][j] + 1) % 2;
                    if(i > 0){
                        newTable[i-1][j] = (newTable[i-1][j] + 1) % 2;
                    }
                    if(i < currentState.currentState.length-1){
                        newTable[i+1][j] = (newTable[i+1][j] + 1) % 2;
                    }
                    if(j > 0){
                        newTable[i][j-1] = (newTable[i][j-1] + 1) % 2;
                    }
                    if(j < currentState.currentState[i].length-1){
                        newTable[i][j+1] = (newTable[i][j+1] + 1) % 2;
                    }
                    //System.out.println(compare(newTable,currentState.currentState));
                    if(storage.test(newTable)){
                        storage.add(newTable);
                        State newState = new State(newTable);
                        newState.addPlay(currentState,new Vector2Int(i,j));
                        queuePlay.add(newState);
                    }else{
                        //System.out.println("already tested");
                    }
                }
            }
        }
    }
}
