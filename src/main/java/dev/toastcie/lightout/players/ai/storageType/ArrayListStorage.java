package dev.toastcie.lightout.players.ai.storageType;

import java.util.ArrayList;

public class ArrayListStorage extends StorageTemplate{

    private ArrayList<int[][]> history;
    public ArrayListStorage(){
        history = new ArrayList<>();
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
    @Override
    public void add(int[][] table) {
        history.add(copy(table));
    }

    @Override
    public boolean test(int[][] table) {
        for (int[][] ints : history) {
            if (compare(ints, table)) {
                return false;
            }
        }
        return true;
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

    @Override
    public void reset() {
        history.clear();
    }
}
