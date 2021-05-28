//package tictactoe;
//
//public class old {
//    int[] availSpots = emptyFields(newBoard);
//    char AI = Main.findPlayer();
//    char opponent = AI == 'O' ? 'X' : 'O';
//    int score = 0;
//
//        if (winning(newBoard, opponent)){
//        return -10;
//    }
//        else if (winning(newBoard, AI)){
//        return 10;
//    }
//        else if (emptyFields(newBoard).length == 0){
//        return 0;
//    }
//
//    //Move[][0 = index, 1 = score]
//    int[][] Move = new int[availSpots.length][2];
//
//        for (int i = 0; i < availSpots.length; i++) {
//        Move[i][0] = availSpots[i];
//
//        System.out.print("iteration: " + i);
//
//        newBoard[emptyFields(newBoard)[i]] = AI;
//        for (int j : availSpots) {
//            System.out.println(j);
//        }
//        System.out.println();
//        for (int h = 0, j = 0; h < 3; h++) {
//            System.out.println();
//            for (int t = 0; t < 3; t++, j++) {
//                System.out.print(newBoard[j]);
//            }
//        }
//        System.out.println();
//
//        if (isMaximizing) {
//            Move[i][1] = score + minimax(newBoard, false);
//        } else {
//            Move[i][1] = score + minimax(newBoard, true);
//        }
//
//        newBoard[availSpots[i]] = '_';
//
//    }
//
//
//    int bestMove;
//        if (isMaximizing) {
//        bestMove = Integer.MIN_VALUE;
//        for (int i = 0; i < availSpots.length; i++) {
//            if (Move[i][1] > bestMove) {
//                bestMove = Move[i][0];
//            }
//        }
//    } else {
//        bestMove = Integer.MAX_VALUE;
//        for (int i = 0; i < availSpots.length; i++) {
//            if (Move[i][1] < bestMove)  {
//                bestMove = Move[i][0];
//            }
//        }
//    }
//
//        return Move[bestMove][0];
//}
//}
