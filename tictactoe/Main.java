package tictactoe;

import java.util.Scanner;

public class Main {
    private static char[] gameState /*= {'O','O','_','X','_','X','O','X','O'}*/;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // write your code here
        initialGameState();
        start();
        printCells();
    }

    public static char[] getGameState() {
        return gameState;
    }

    public static void initialGameState() {
        gameState = new char[9];
        for (int i = 0; i < 9; i++) {
            gameState[i] = '_';
        }
    }

    public static void printCells() {
        System.out.println("---------");
        for (int j = 3; j > 0; j--) {
            System.out.print("|");
            for (int i = 1; i < 4; i++) {
                int[] coordinates = {i, j};
                System.out.print(" " + getCellValue(coordinates));
            }
            System.out.println(" |");
        }
        System.out.println("---------");
    }

    private static char getCellValue(int[] coordinates) {
        return gameState[getCellPositionInGameState(coordinates)];
    }

    private static int getCellPositionInGameState(int[] coordinates) {
        int posInGameState = 0;

        switch (coordinates[1]) {
            case 1: {
                posInGameState = 6;
                break;
            }
            case 2: {
                posInGameState = 3;
                break;
            }
            case 3: {
                posInGameState = 0;
                break;
            }
        }
        posInGameState += coordinates[0] - 1;
        return posInGameState;
    }

    public static int[] readUserCoordinates() {
        boolean coordinatesAreCorrect = false;
        int x = 0;
        int y = 0;

        while (!coordinatesAreCorrect) {
            System.out.println("Enter the coordinates:");
            String buff = scanner.nextLine().trim();
            if (buff.equals("exit")){System.exit(1);}
            String buf[] = buff.split("\\s");
            if (buf.length != 2) {
                System.out.println("You should enter numbers!");
                continue;
            }
            if (!buf[0].matches("\\d+") || !buf[1].matches("\\d+")) {
                System.out.println("You should enter numbers!");
                continue;
            }
            x = Integer.parseInt(buf[0]);
            y = Integer.parseInt(buf[1]);
            if (x < 0 || x > 3 || y < 0 || y > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            int[] coordinates = {x, y};
            if (getCellValue(coordinates) != '_') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            coordinatesAreCorrect = true;
        }
        return new int[]{x, y};
    }

    public static char findPlayer() {
        int xCounter = 0;
        int oCounter = 0;
        for (char c : gameState) {
            switch (c) {
                case 'X': {
                    xCounter++;
                    break;
                }
                case 'O': {
                    oCounter++;
                    break;
                }
            }
        }
        return (xCounter <= oCounter) ? 'X' : 'O';
    }

    public static void makeMove(int[] coordinates, char player) {
        gameState[getCellPositionInGameState(coordinates)] = player;
    }

    public static String gameStatus() {
        //Possible states:
        //
        //"Game not finished" - when no side has a three in a row but the field has empty cells;
        //"Draw" - when no side has a three in a row and the field has no empty cells;
        //"X wins" - when the field has three X in a row;
        //"O wins" - when the field has three O in a row;

        for (int[] arr : win_condition_array) {
            if (gameState[arr[0]] == 'X' && gameState[arr[1]] == 'X' && gameState[arr[2]] == 'X') {
                return "X wins";
            }
            if (gameState[arr[0]] == 'O' && gameState[arr[1]] == 'O' && gameState[arr[2]] == 'O') {
                return "O wins";
            }
        }
        if (String.valueOf(gameState).contains("_")) {
            return "Game not finished";
        }
        return "Draw";
    }

    public static void start() {
        System.out.print("Input command: ");
        String command = scanner.next();
        String modCommand = command.replaceAll("\\s+", "").toLowerCase();

        switch (modCommand) {
            case "start":
                String player1 = scanner.next().toLowerCase();
                String player2 = scanner.next().toLowerCase();
                do {
                    turn(player1);
                    turn(player2);
                } while (gameStatus().equals("Game not finished"));
                System.out.println(gameStatus());
            break;
            case "exit":
                System.exit(1);
                break;
            default:
                System.out.println("Bad first parameter!(start/exit)");
                start();
                break;
        }
    }

    public static void turn(String player) {
        if ((gameStatus().equals("Game not finished"))) {
        switch (player) {
            case "user":
                int[] coordp1 = readUserCoordinates();
                makeMove(coordp1, findPlayer());
                printCells();
                break;
            case "easy":
                AI.computerGoBrrrrr("easy");
                printCells();
                break;
            case "medium":
                AI.computerGoBrrrrr("medium");
                printCells();
                break;
            case "hard":
                AI.computerGoBrrrrr("hard");
                printCells();
                break;
            case "exit":
                System.exit(1);
                break;
            default:
                System.out.println("Bad parameters!(user/easy/medium)");
                start();
                break;
        }
    }
    }
    public static final int[][] win_condition_array =
            {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

}



