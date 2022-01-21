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
    // the length of the board (the board is a boardLength by boardLength square)
    int boardLength = (int) Math.sqrt(board.length());

    // if the area of the board isn't the rounded down length, then we have an invalid board,
    // as the board area must be a square
    if (boardLength * boardLength != board.length()) {
      throw new IllegalArgumentException();
    }

    // 1st element true if x won, 2nd element true if o won by column completion
    boolean[] columnWinners = columnWinner(boardLength);
    // 1st element true if x won, 2nd element true if o won by row completion
    boolean[] rowWinners = rowWinner(boardLength);
    // 1st element sum of values on diagonal from top left to bottom right, 2nd element sum from
    // top right to bottom left diagonal where x values mean 1 is added, o values mean -1 is added,
    // and any other value means 0 is added
    int[] diagonalSum = sumDiagonals(boardLength);
    // stores if x won, can win with row, column, or if every diagonal value is a 1 (we read x) on
    // either diagonal
    boolean xWon = columnWinners[0] || rowWinners[0] || diagonalSum[0] == boardLength
        || diagonalSum[1] == boardLength;
    // stores if o won, can win with row, column, or if every diagonal value is a -1 (we read o) on
    // either diagonal
    boolean oWon =
        columnWinners[1] || rowWinners[1] || diagonalSum[0] == -boardLength
            || diagonalSum[1] == -boardLength;
    // stores if board is invalid
    boolean invalidBoard = checkInvalid(boardLength);

    // if both sides win, or board is invalid we have an unreachable state
    if (invalidBoard || xWon && oWon) {
      return Evaluation.UnreachableState;
    }
    // otherwise if x won we report it, same if o won
    else if (xWon) {
      return Evaluation.Xwins;
    } else if (oWon) {
      return Evaluation.Owins;
    }
    // if nothing else is satisfied nobody won
    else {
      return Evaluation.NoWinner;
    }
  }

  /**
   * Checks if the board is invalid by seeing if the absolute difference between the x and o marks
   * is greater than 1.
   *
   * @param boardLength the length of the board the game is played on
   * @return boolean true if the board is invalid, false otherwise
   */
  public boolean checkInvalid(int boardLength) {
    int numX = 0; // number of x marks
    int numO = 0; // number of o marks

    for (int i = 0; i < board.length(); i++) {
      char current = board.charAt(i);
      if (current == 'X' || current == 'x') { // we found an x mark
        numX++;
      } else if (current == 'O' || current == 'o') { // we found an o mark
        numO++;
      }
    }

    // return whether the absolute difference of the x and o marks is greater than 1
    return Math.abs(numX - numO) > 1;
  }

  /**
   * Sums up the values on both diagonals, assigning the value of the diagonal starting top left to
   * the first element and diagonal starting top right to the second element of the returned array.
   * When summing up values, we consider reading in an x to be adding 1 to the total, and reading an
   * o to be adding -1 to the total for a diagonal.
   *
   * @param boardLength the length of the board the game is played on
   * @return int[] array of size 2 where the first value is the sum of the diagonal starting top
   * left and the second value is the sum of the diagonal starting top right
   */
  public int[] sumDiagonals(int boardLength) {
    // store the sums of both diagonals
    int[] diagonalSums = new int[2];
    // the row we are evaluating, this value gets immediately incremented in the
    // loop and we want to start at 0 so we initialize it as -1
    int rowNum = -1;

    for (int i = 0; i < board.length(); i++) {
      char current = board.charAt(i);
      // if we are starting a new row (reading a multiple of the board length) increment row number
      if (i % boardLength == 0) {
        rowNum++;
      }
      if (current == 'X' || current == 'x') {
        // for diagonal starting top left, row number matches position in the row
        if (i % boardLength == rowNum) {
          diagonalSums[0]++;
        }
        // for diagonal starting top right, row number matches position in the row starting from end
        if (i % boardLength == boardLength - rowNum - 1) {
          diagonalSums[1]++;
        }
      } else if (current == 'O' || current == 'o') { // repeat logic with decrement if we read an o
        if (i % boardLength == rowNum) {
          diagonalSums[0]--;
        }
        if (i % boardLength == boardLength - rowNum - 1) {
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
