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
    int boardLength = (int) Math.sqrt(board.length());

    if (boardLength * boardLength != board.length()) {
      throw new IllegalArgumentException();
    }

    boolean[] columnWinners = columnWinner(boardLength);
    boolean[] rowWinners = rowWinner(boardLength);
    int[] diagonalSum = sumDiagonals(boardLength);
    boolean xWon = columnWinners[0] || rowWinners[0] || diagonalSum[0] == boardLength
        || diagonalSum[1] == boardLength;
    boolean oWon =
        columnWinners[1] || rowWinners[1] || diagonalSum[0] == -boardLength
            || diagonalSum[1] == -boardLength;
    boolean invalidBoard = checkInvalid(boardLength);

    if (invalidBoard || xWon && oWon) {
      return Evaluation.UnreachableState;
    } else if (xWon) {
      return Evaluation.Xwins;
    } else if (oWon) {
      return Evaluation.Owins;
    } else {
      return Evaluation.NoWinner;
    }
  }

  public boolean checkInvalid(int boardLength) {
    int numX = 0;
    int numO = 0;

    for (int i = 0; i < board.length(); i++) {
      char current = board.charAt(i);
      if (current == 'X' || current == 'x') {
        numX++;
      } else if (current == 'O' || current == 'o') {
        numO++;
      }
    }

    return Math.abs(numX - numO) > 1;
  }

  public int[] sumDiagonals(int boardLength) {
    int[] diagonalSums = new int[2];
    int rowNum = -1;

    for (int i = 0; i < board.length(); i++) {
      char current = board.charAt(i);
      if (i % boardLength == 0) {
        rowNum++;
      }
      if (current == 'X' || current == 'x') {
        if (i % boardLength == rowNum) {
          diagonalSums[0]++;
        } else if (i % boardLength == boardLength - rowNum - 1) {
          diagonalSums[1]++;
        }
      } else if (current == 'O' || current == 'o') {
        if (i % boardLength == rowNum) {
          diagonalSums[0]--;
        } else if (i % boardLength == boardLength - rowNum - 1) {
          diagonalSums[1]--;
        }
      }
    }

    return diagonalSums;
  }

  public boolean[] rowWinner(int boardLength) {
    boolean[] winners = new boolean[2];
    int rowSum = 0;

    for (int i = 0; i < board.length(); i++) {
      char current = board.charAt(i);
      if (i % boardLength == 0) {
        if (rowSum == boardLength) {
          winners[0] = true;
        } else if (rowSum == -1 * boardLength) {
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

    if (rowSum == boardLength) {
      winners[0] = true;
    } else if (rowSum == -1 * boardLength) {
      winners[1] = true;
    }

    return winners;
  }

  public boolean[] columnWinner(int boardLength) {
    int[] columnSum = new int[boardLength];
    boolean[] winners = new boolean[2];

    for (int i = 0; i < board.length(); i++) {
      char current = board.charAt(i);
      if (current == 'X' || current == 'x') {
        columnSum[i % boardLength]++;
      } else if (current == 'O' || current == 'o') {
        columnSum[i % boardLength]--;
      }
    }

    for (int i = 0; i < columnSum.length; i++) {
      if (columnSum[i] == boardLength) {
        winners[0] = true;
      } else if (columnSum[i] == -1 * boardLength) {
        winners[1] = true;
      }
    }

    return winners;
  }
}
