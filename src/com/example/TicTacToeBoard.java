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
    int x = 0;
    int o = 0;
    int n = (int) Math.sqrt(board.length());

    if (n * n != board.length()) {
      throw new IllegalArgumentException();
    }

    int[] columnSum = columnSum(n);

    return Evaluation.UnreachableState;
  }

  public int[] columnSum(int n) {
    int[] cols = new int[n];

    for (int i = 0; i < board.length(); i++) {
      char current = board.charAt(i);
      if (current == 'X' || current == 'x') {
        cols[i % n]++;
      } else if (current == 'O' || current == 'o') {
        cols[i % n]--;
      }
    }

    return cols;
  }
}
