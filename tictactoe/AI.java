package tictactoe;

import java.util.*;

public class AI {

    public static void computerGoBrrrrr(String difficulty) {
        System.out.printf("Making Move level \"%s\" %c%n", difficulty, Main.findPlayer());
        //implement switch statement for "easy", "medium" & "hard"
        Random random = new Random();

        int cell;
        boolean turnAI = true;

        if (difficulty.equals("medium")) {
            System.out.println("medium");
            turnAI = checkForRow();
        } else if (difficulty.equals("hard")) {
            System.out.println("hard");
            turnAI = masterfulAI();
        }
        while (turnAI) {
            cell = random.nextInt(9);
            if (!Main.gameStatus().equals("Game not finished")) {
                break;
            } else if (Main.getGameState()[cell] == '_') {
                Main.getGameState()[cell] = Main.findPlayer();
                turnAI = false;
            }
        }

    }

    private static boolean checkForRow() {

        char player = Main.findPlayer();
        char opponent = player == 'X' ? 'O' : 'X';

        int[][] win_condition_array = Main.win_condition_array;

        for (int[] arr : win_condition_array) {
            int playerCounter = 0;
            int opponentCounter = 0;
            for (int i = 0; i < 3; i++) {
                if (Main.getGameState()[arr[i]] == player) {
                    playerCounter++;
                    if (playerCounter == 2) {
                        for (int j = 0; j < 3; j++) {
                            if (Main.getGameState()[arr[i]] == '_') {
                                Main.getGameState()[arr[i]] = player;
                                return false;
                            }
                        }
                    }
                }
                if (Main.getGameState()[arr[i]] == opponent) {
                    opponentCounter++;
                    if (opponentCounter == 2) {
                        for (int j = 0; j < 3; j++) {
                            if (Main.getGameState()[arr[i]] == '_') {
                                Main.getGameState()[arr[i]] = player;
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    private static boolean masterfulAI() {
        char player = Main.findPlayer();
        char[] board = Main.getGameState();
        int bestScore = Integer.MIN_VALUE;
        int move = 10;
        int leastDepth = Integer.MAX_VALUE;
        //Iterates through all the moves
        for (int i = 0; i < 9; i++){
            if (board[i] == '_') {
                board[i] = player;
                int score = minimax(board,true, 0);
                board[i] = '_';
                //move selection
                if (score >= bestScore) {
                    bestScore = score;
                    move = i;
                }
            }
        }
        board[move] = player;
        return false;
    }

    private static int minimax(char[] newBoard, boolean isMaximizing, int depth) {
        char player = Main.findPlayer();
        int bestScore;

        if (terminalState(newBoard)) {
            if (isMaximizing) {
                return 10;
            } else {
                return -10;
            }
        } else if (!String.valueOf(newBoard).contains("_")) {
            return 0;
        }

        if (isMaximizing) {
            bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if (newBoard[i] == '_') {
                 newBoard[i] = player;
                 int score = minimax(newBoard, false, depth+1);
                 newBoard[i] = '_';
                 bestScore = Integer.max(score, bestScore);
                }
            }
        } else {
            bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                if (newBoard[i] == '_') {
                    newBoard[i] = player;
                    int score = minimax(newBoard, true, depth+1);
                    newBoard[i] = '_';
                    bestScore = Integer.min(score, bestScore);
                }
            }
        }

        return bestScore;
    }


    private static boolean terminalState(char[] board) {
        int[][] win_condition_array = Main.win_condition_array;

        for (int[] i: win_condition_array) {
            int counter = 0;
            char character = board[i[0]];
            for (int j = 0; j < 3; j++) {
                if (board[i[j]] == character && character != '_') {
                    counter++;
                    if (counter == 3) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

