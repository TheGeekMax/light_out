package dev.toastcie.lightout.players;

import dev.toastcie.lightout.Constants;

public class PlayerTemplate {
    public static PlayerObject classicCursor = new HumanCursor(Constants.ARRAY_WIDTH,Constants.ARRAY_HEIGHT);
    public static PlayerObject classicClick = new HumanClick(Constants.ARRAY_WIDTH,Constants.ARRAY_HEIGHT);
    public static PlayerObject classicAiAlgorithm = new AiAlgorithm(Constants.ARRAY_WIDTH,Constants.ARRAY_HEIGHT);
}
