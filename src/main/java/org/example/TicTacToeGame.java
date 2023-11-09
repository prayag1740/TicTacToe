package org.example ;

import org.apache.commons.lang3.tuple.Pair;
import org.example.model.Board;
import org.example.model.Player;
import org.example.model.PlayingPieceO;
import org.example.model.PlayingPieceX;
import sun.nio.ch.sctp.SctpNet;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {

    Deque<Player> players ;
    Board gameboard ;

    public void initializeGame() {
        players = new LinkedList<>() ;

        PlayingPieceX crossPiece = new PlayingPieceX();
        Player player1 = new Player("Player1", crossPiece) ;

        PlayingPieceO noughtPiece = new PlayingPieceO();
        Player player2 = new Player("Player2", noughtPiece) ;

        players.add(player1);
        players.add(player2);

        gameboard = new Board(3);
    }

    public String startGame() {
        boolean noWinner = true ;
        while (noWinner) {
            Player playerTurn = players.removeFirst();
            gameboard.printBoard();

            List<Pair<Integer, Integer>> freecells = gameboard.getFreeCells() ;
            if (freecells.isEmpty()) {
                noWinner = false;
                continue;
            }

            //Taking user input
            System.out.println("Player " + playerTurn.getName() + " Enter row and col :");
            Scanner inputScanner = new Scanner(System.in) ;
            String s = inputScanner.nextLine();
            String[] values = s.split(",");
            int inputRow = Integer.parseInt(values[0]);
            int inputCol = Integer.parseInt(values[1]) ;

            boolean pieceAddSuccess = gameboard.addPiece(inputRow, inputCol, playerTurn.playingPiece);
            if (! pieceAddSuccess) {
                System.out.println("Incorrect piece added !! Please try again");
                players.addFirst(playerTurn);
                continue;
            }
            players.addLast(playerTurn);

            boolean isWinner = gameboard.checkWinner(inputRow, inputCol, playerTurn.playingPiece);
            if (isWinner) {
                return "Winner is " + playerTurn.getName();
            }
        }
        return "Game is a tie";
    }
}

