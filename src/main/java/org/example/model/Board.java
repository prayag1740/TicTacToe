package org.example.model;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public int size;
    public PlayingPiece[][] board;

    public Board(int size) {
        this.size = size;
        board = new PlayingPiece[size][size];
    }

    public boolean addPiece(int row, int col, PlayingPiece playingPiece) {
        if (board[row][col] != null) {
            return false;
        }
        board[row][col] = playingPiece;

        return true;


    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != null) {
                    System.out.print(board[i][j].piecetype.name() + "  ");
                } else {
                    System.out.print("  ");
                }
                System.out.print(" | ");
            }
            System.out.println();
        }
    }

    public List<Pair<Integer, Integer>> getFreeCells() {
        List<Pair<Integer, Integer>> freeCells = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) {
                    Pair<Integer, Integer> rowcol = Pair.of(i, j);
                    freeCells.add(rowcol);
                }
            }
        }

        return freeCells;
    }

    public boolean checkWinner(int row, int col, PlayingPiece playingPiece) {
        boolean rowMatch = true ;
        boolean colMatch = true ;
        boolean diagnolMatch = true ;
        boolean antiDiagnolMatch = true ;

        //row match
        for (int i=0 ; i < size ; i++) {
            if (board[row][i] == null || board[row][i] != playingPiece) {
                rowMatch = false ;
                break ;
            }
        }

        //column match
        for (int i=0 ; i < size; i++) {
            if (board[i][col] == null || board[i][col] != playingPiece) {
                colMatch = false ;
                break;
            }
        }

        //diagnol match
        for (int i=0, j=0 ; i<size; i++,j++) {
            if (board[i][j] ==  null || board[i][j] != playingPiece) {
                diagnolMatch = false;
                break;
            }
        }

        //antidiagnol match
        for (int i=0, j=size-1 ; i<size; i++,j--) {
            if (board[i][j] == null || board[i][j] != playingPiece) {
                antiDiagnolMatch = false;
                break;
            }
        }

       return rowMatch || colMatch || diagnolMatch || antiDiagnolMatch ;
    }
}

