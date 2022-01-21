package com.example;

/**
 * Takes in and evaluates a string representing a tic tac toe board.
 */
public class TicTacToeBoard {

  private String board = "";

  /**
   * This method should load a string into your TicTacToeBoard class.
   *
   * @param board The string representing the board
   */
  public TicTacToeBoard(String board) {
    this.board = board;
  }

  /**
   * Checks the state of the board (unreachable, no winner, X wins, or O wins)
   *
   * @return an enum value corresponding to the board evaluation
   */
  public Evaluation evaluate() {
    int n = (int) Math.sqrt(board.length());

    if (n * n != board.length()) {
      throw new IllegalArgumentException();
    }

    boolean[] columnWinners = columnWinner(n);
    boolean[] rowWinners = rowWinner(n);
    int[] diagonalSum = sumDiagonals(n);
    boolean xWon = columnWinners[0] || rowWinners[0] || diagonalSum[0]==n || diagonalSum[1]==n;
    boolean oWon = columnWinners[1] || rowWinners[1] || diagonalSum[0]==-n || diagonalSum[1]==-n;
    boolean invalid = checkInvalid(n);

    if(invalid || xWon && oWon)
      return Evaluation.UnreachableState;
    else if(xWon)
      return Evaluation.Xwins;
    else if(oWon)
      return Evaluation.Owins;
    else
      return Evaluation.NoWinner;
  }

  public boolean checkInvalid(int n){
    int x = 0;
    int o = 0;

    for (int i = 0; i < board.length(); i++) {
      char current = board.charAt(i);
      if (current == 'X' || current == 'x') {
        x++;
      } else if (current == 'O' || current == 'o') {
        o++;
      }
    }

    return Math.abs(x-o)>1;
  }

  public int[] sumDiagonals(int n){
    int[] diagonalSums = new int[2];
    int rowNum = 0;

    for (int i = 0; i < board.length(); i++) {
      char current = board.charAt(i);
      if (i % n == 0) {
        rowNum++;
      }
      if (current == 'X' || current == 'x') {
        if (i % n == rowNum) {
          diagonalSums[0]++;
        } else if (i % n == n - rowNum - 1) {
          diagonalSums[1]++;
        }
      } else if (current == 'O' || current == 'o') {
        if (i % n == rowNum) {
          diagonalSums[0]--;
        } else if (i % n == n - rowNum - 1) {
          diagonalSums[1]--;
        }
      }
    }

    return diagonalSums;
  }

  public boolean[] rowWinner(int n){
    boolean[] winners = new boolean[2];
    int rowSum = 0;
    int rowNum = 0;

    for (int i = 0; i < board.length(); i++) {
      char current = board.charAt(i);
      if (i % n == 0) {
        rowNum++;
        if (rowSum == n) {
          winners[0] = true;
        } else if (rowSum == -1 * n) {
          winners[1] = true;
        }
        rowSum = 0;
      }
      if (current == 'X' || current == 'x') {
        rowSum++;
      } else if (current == 'O' || current == 'o') {
        rowSum--;
      }
    }

    return winners;
  }

  public boolean[] columnWinner(int n) {
    int[] columnSum = new int[n];
    boolean[] winners = new boolean[2];

    for (int i = 0; i < board.length(); i++) {
      char current = board.charAt(i);
      if (current == 'X' || current == 'x') {
        columnSum[i % n]++;
      } else if (current == 'O' || current == 'o') {
        columnSum[i % n]--;
      }
    }

    for (int i = 0; i < columnSum.length; i++) {
      if (columnSum[i] == n) {
        winners[0] = true;
      } else if (columnSum[i] == -1 * n) {
        winners[1] = true;
      }
    }

    return winners;
  }
}
