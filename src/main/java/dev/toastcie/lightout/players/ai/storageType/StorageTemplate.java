package dev.toastcie.lightout.players.ai.storageType;

public abstract class StorageTemplate {
    public abstract void add(int[][] table);
    public abstract boolean test(int[][] table);
    public abstract void reset();
}
