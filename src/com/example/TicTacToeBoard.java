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

    return Evaluation.NoWinner;
  }
}
