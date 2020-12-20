import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

/*
String representation of the board.

PC's read left to right.
so it will always be 00 - 02 left to right, then 10 - 12, then 20-22
I have mapped the board differently so that A1

|A1|B1|C1|
|A2|B2|C2|
|A3|B3|C3|

00|01|02
10|11|12
20|21|22

*/

public class BoardGame {

    private int[][] board;
    private int currentPlayerMark;
    private final int humanPlayer;
    private final int computerPlayer;



    public BoardGame(int i) {
        board = new int[i][i];
        currentPlayerMark = 1;
        humanPlayer = 1;
        computerPlayer = 2;
        initializeBoard();
    }

    public int[][] getBoard(){
      return board;
    }

    public int getBoardXDimensions() {
        return board[0].length;
    }

    public int getBoardYDimensions() {
        return board[1].length;
    }

    public int getCurrentPlayer() {
        return currentPlayerMark;
    }

    public int getHumanPlayer() {
        return humanPlayer;
    }

    public int getcomputerPlayer() {
        return computerPlayer;
    }

    // Set/Reset the board back to all empty values.
    public void initializeBoard() {
        currentPlayerMark = 1;
        for (int i = 0; i < getBoardXDimensions(); i++) {
            for (int j = 0; j < getBoardYDimensions(); j++) {
                board[i][j] = 0;
            }
        }
    }

    public void printBoard() {
      System.out.println("");
      for(int i = 0;i<getBoardXDimensions();i++){
        if (i<10){
        System.out.print("  "+(i) + " ");
        }
        else{
          System.out.print(" "+(i) + " ");
        }
      }
      System.out.println("");
      if(getBoardXDimensions()<=4){
        System.out.println("-------------");
      }
      else{
        for(int x = 0;x<(getBoardXDimensions()/3);x++)
        System.out.print("-------------");
        System.out.println("");
      }
      for (int i = 0; i < getBoardXDimensions(); i++) {
          System.out.print("| ");
          for (int j = 0; j < getBoardYDimensions(); j++) {
              System.out.print(board[i][j] + " | ");
              if (j==getBoardYDimensions()-1){
                  System.out.print(i);
            }
          }
          System.out.println();
          if(getBoardXDimensions()<=4){
            System.out.println("-------------");
          }
          else{
            for(int h = 0;h<(getBoardXDimensions()/3);h++)
            System.out.print("-------------");
            System.out.println("");
          }
      }
      System.out.println();
    }


    // Loop through all cells of the board and if one is found to be empty (contains char '-') then return false.
    // Otherwise the board is full.
    public boolean isBoardFull() {
        boolean isFull = true;

        for (int i = 0; i < getBoardXDimensions(); i++) {
            for (int j = 0; j < getBoardYDimensions(); j++) {
                if (board[i][j] == 0) {
                    isFull = false;
                }
            }
        }

        return isFull;
    }


    // Returns true if there is a win, false otherwise.
    // This calls our other win check functions to check the entire board.
    public boolean checkWinPlayer1(){
      //checking Every direction to see if there is a win.
      int[][] directions = {{1,0}, {1,-1}, {1,1}, {0,1}};
      ArrayList<Integer> winningLine = new ArrayList<>();
      for (int[] d : directions) {
          int di = d[0];
          int dj = d[1];
          for (int i = 0; i < getBoardXDimensions(); i++) {
              for (int j = 0; j < getBoardYDimensions(); j++) {
                  int maxXDimensions = i + 4*di;
                  int maxYDimensions = j + 4*dj;
                  if (0 <= maxXDimensions && maxXDimensions < getBoardXDimensions()
                  && 0 <= maxYDimensions && maxYDimensions < getBoardYDimensions()) {
                      winningLine.add(getBoardToken(i,j));
                      winningLine.add(getBoardToken(i+di,j+dj));
                      winningLine.add(getBoardToken(i+2*di,j+2*dj));
                      winningLine.add(getBoardToken(i+3*di,j+3*dj));
                      winningLine.add(getBoardToken(maxXDimensions,maxYDimensions));
                      int sum = 0;
                      for(int k = 0;k<winningLine.size();k++){
                        if(winningLine.get(k)==1){
                          sum += winningLine.get(k);
                        }
                      }
                      if(sum==5){
                        return true;
                      }
                      else{
                        winningLine.clear();
                      }
                  }
              }
          }
      }
      return false;
    }

    public boolean checkWinPlayer2(){
      //checking Every direction to see if there is a win.
      int[][] directions = {{1,0}, {1,-1}, {1,1}, {0,1}};
      ArrayList<Integer> winningLine = new ArrayList<>();
      for (int[] d : directions) {
          int di = d[0];
          int dj = d[1];
          for (int i = 0; i < getBoardXDimensions(); i++) {
              for (int j = 0; j < getBoardYDimensions(); j++) {
                  int maxXDimensions = i + 4*di;
                  int maxYDimensions = j + 4*dj;
                  if (0 <= maxXDimensions && maxXDimensions < getBoardXDimensions()
                  && 0 <= maxYDimensions && maxYDimensions < getBoardYDimensions()) {
                      winningLine.add(getBoardToken(i,j));
                      winningLine.add(getBoardToken(i+di,j+dj));
                      winningLine.add(getBoardToken(i+2*di,j+2*dj));
                      winningLine.add(getBoardToken(i+3*di,j+3*dj));
                      winningLine.add(getBoardToken(maxXDimensions,maxYDimensions));
                      int sum = 0;
                      for(int k = 0;k<winningLine.size();k++){
                        if(winningLine.get(k)==2){
                          sum += winningLine.get(k);
                        }
                      }
                      if(sum==10){
                        return true;
                      }
                      else{
                        winningLine.clear();
                      }
                  }
              }
          }
      }
      return false;
    }
    // Change player marks back and forth.
    public void changePlayer() {
        if (currentPlayerMark == 1) {
            currentPlayerMark = 2;
        }
        else {
            currentPlayerMark = 1;
        }
    }

    // Places a mark at the cell specified by row and col with the mark of the current player.
    public boolean placeMark(String buttonName, int player) {
        // Make sure that row and column are in bounds of the board.
          if(buttonName.equals("A1")&& player==1){
            this.board[0][0] = 1;

            return true;
          }
          else if(buttonName.equals("B1")&& player==1){
            this.board[0][1] = 1;

            return true;
          }
          else if(buttonName.equals("C1")&& player==1){
            this.board[0][2] = 1;

            return true;
          }
          else if(buttonName.equals("D1")&& player==1){
            this.board[0][3] = 1;

            return true;
          }
          else if(buttonName.equals("E1")&& player==1){
            this.board[0][4] = 1;

            return true;
          }
          else if(buttonName.equals("F1")&& player==1){
            this.board[0][5] = 1;

            return true;
          }
          else if(buttonName.equals("G1")&& player==1){
            this.board[0][6] = 1;

            return true;
          }
          else if(buttonName.equals("H1")&& player==1){
            this.board[0][7] = 1;

            return true;
          }
          else if(buttonName.equals("I1")&& player==1){
            this.board[0][8] = 1;

            return true;
          }
          else if(buttonName.equals("J1")&& player==1){
            this.board[0][9] = 1;

            return true;
          }
          else if(buttonName.equals("K1")&& player==1){
            this.board[0][10] = 1;

            return true;
          }
          else if(buttonName.equals("L1")&& player==1){
            this.board[0][11] = 1;

            return true;
          }
          else if(buttonName.equals("M1")&& player==1){
            this.board[0][12] = 1;

            return true;
          }
          else if(buttonName.equals("N1")&& player==1){
            this.board[0][13] = 1;

            return true;
          }
          else if(buttonName.equals("O1")&& player==1){
            this.board[0][14] = 1;
            System.out.println(this.board[0][14]);

            return true;
          }
          else if(buttonName.equals("A2")&& player==1){
            this.board[1][0] = 1;

            return true;
          }
          else if(buttonName.equals("B2")&& player==1){
            this.board[1][1] = 1;

            return true;
          }
          else if(buttonName.equals("C2")&& player==1){
            this.board[1][2] = 1;

            return true;
          }
          else if(buttonName.equals("D2")&& player==1){
            this.board[1][3] = 1;

            return true;
          }
          else if(buttonName.equals("E2")&& player==1){
            this.board[1][4] = 1;

            return true;
          }
          else if(buttonName.equals("F2")&& player==1){
            this.board[1][5] = 1;

            return true;
          }
          else if(buttonName.equals("G2")&& player==1){
            this.board[1][6] = 1;

            return true;
          }
          else if(buttonName.equals("H2")&& player==1){
            this.board[1][7] = 1;

            return true;
          }
          else if(buttonName.equals("I2")&& player==1){
            this.board[1][8] = 1;

            return true;
          }
          else if(buttonName.equals("J2")&& player==1){
            this.board[1][9] = 1;

            return true;
          }
          else if(buttonName.equals("K2")&& player==1){
            this.board[1][10] = 1;

            return true;
          }
          else if(buttonName.equals("L2")&& player==1){
            this.board[1][11] = 1;

            return true;
          }
          else if(buttonName.equals("M2")&& player==1){
            this.board[1][12] = 1;

            return true;
          }
          else if(buttonName.equals("N2")&& player==1){
            this.board[1][13] = 1;

            return true;
          }
          else if(buttonName.equals("O2")&& player==1){
            this.board[1][14] = 1;

            return true;
          }
          else if(buttonName.equals("A3")&& player==1){
            this.board[2][0] = 1;

            return true;
          }
          else if(buttonName.equals("B3")&& player==1){
            this.board[2][1] = 1;

            return true;
          }
          else if(buttonName.equals("C3")&& player==1){
            this.board[2][2] = 1;

            return true;
          }
          else if(buttonName.equals("D3")&& player==1){
            this.board[2][3] = 1;

            return true;
          }
          else if(buttonName.equals("E3")&& player==1){
            this.board[2][4] = 1;

            return true;
          }
          else if(buttonName.equals("F3")&& player==1){
            this.board[2][5] = 1;

            return true;
          }
          else if(buttonName.equals("G3")&& player==1){
            this.board[2][6] = 1;

            return true;
          }
          else if(buttonName.equals("H3")&& player==1){
            this.board[2][7] = 1;

            return true;
          }
          else if(buttonName.equals("I3")&& player==1){
            this.board[2][8] = 1;

            return true;
          }
          else if(buttonName.equals("J3")&& player==1){
            this.board[2][9] = 1;

            return true;
          }
          else if(buttonName.equals("K3")&& player==1){
            this.board[2][10] = 1;

            return true;
          }
          else if(buttonName.equals("L3")&& player==1){
            this.board[2][11] = 1;

            return true;
          }
          else if(buttonName.equals("M3")&& player==1){
            this.board[2][12] = 1;

            return true;
          }
          else if(buttonName.equals("N3")&& player==1){
            this.board[2][13] = 1;

            return true;
          }
          else if(buttonName.equals("O3")&& player==1){
            this.board[2][14] = 1;

            return true;
          }
          else if(buttonName.equals("A4")&& player==1){
            this.board[3][0] = 1;

            return true;
          }
          else if(buttonName.equals("B4")&& player==1){
            this.board[3][1] = 1;

            return true;
          }
          else if(buttonName.equals("C4")&& player==1){
            this.board[3][2] = 1;

            return true;
          }
          else if(buttonName.equals("D4")&& player==1){
            this.board[3][3] = 1;

            return true;
          }
          else if(buttonName.equals("E4")&& player==1){
            this.board[3][4] = 1;

            return true;
          }
          else if(buttonName.equals("F4")&& player==1){
            this.board[3][5] = 1;

            return true;
          }
          else if(buttonName.equals("G4")&& player==1){
            this.board[3][6] = 1;

            return true;
          }
          else if(buttonName.equals("H4")&& player==1){
            this.board[3][7] = 1;

            return true;
          }
          else if(buttonName.equals("I4")&& player==1){
            this.board[3][8] = 1;

            return true;
          }
          else if(buttonName.equals("J4")&& player==1){
            this.board[3][9] = 1;

            return true;
          }
          else if(buttonName.equals("K4")&& player==1){
            this.board[3][10] = 1;

            return true;
          }
          else if(buttonName.equals("L4")&& player==1){
            this.board[3][11] = 1;

            return true;
          }
          else if(buttonName.equals("M4")&& player==1){
            this.board[3][12] = 1;

            return true;
          }
          else if(buttonName.equals("N4")&& player==1){
            this.board[3][13] = 1;

            return true;
          }
          else if(buttonName.equals("O4")&& player==1){
            this.board[3][14] = 1;

            return true;
          }
          else if(buttonName.equals("A5")&& player==1){
            this.board[4][0] = 1;

            return true;
          }
          else if(buttonName.equals("B5")&& player==1){
            this.board[4][1] = 1;

            return true;
          }
          else if(buttonName.equals("C5")&& player==1){
            this.board[4][2] = 1;

            return true;
          }
          else if(buttonName.equals("D5")&& player==1){
            this.board[4][3] = 1;

            return true;
          }
          else if(buttonName.equals("E5")&& player==1){
            this.board[4][4] = 1;

            return true;
          }
          else if(buttonName.equals("F5")&& player==1){
            this.board[4][5] = 1;

            return true;
          }
          else if(buttonName.equals("G5")&& player==1){
            this.board[4][6] = 1;

            return true;
          }
          else if(buttonName.equals("H5")&& player==1){
            this.board[4][7] = 1;

            return true;
          }
          else if(buttonName.equals("I5")&& player==1){
            this.board[4][8] = 1;

            return true;
          }
          else if(buttonName.equals("J5")&& player==1){
            this.board[4][9] = 1;

            return true;
          }
          else if(buttonName.equals("K5")&& player==1){
            this.board[4][10] = 1;

            return true;
          }
          else if(buttonName.equals("L5")&& player==1){
            this.board[4][11] = 1;

            return true;
          }
          else if(buttonName.equals("M5")&& player==1){
            this.board[4][12] = 1;

            return true;
          }
          else if(buttonName.equals("N5")&& player==1){
            this.board[4][13] = 1;

            return true;
          }
          else if(buttonName.equals("O5")&& player==1){
            this.board[4][14] = 1;

            return true;
          }
          else if(buttonName.equals("A6")&& player==1){
            this.board[5][0] = 1;

            return true;
          }
          else if(buttonName.equals("B6")&& player==1){
            this.board[5][1] = 1;

            return true;
          }
          else if(buttonName.equals("C6")&& player==1){
            this.board[5][2] = 1;

            return true;
          }
          else if(buttonName.equals("D6")&& player==1){
            this.board[5][3] = 1;

            return true;
          }
          else if(buttonName.equals("E6")&& player==1){
            this.board[5][4] = 1;

            return true;
          }
          else if(buttonName.equals("F6")&& player==1){
            this.board[5][5] = 1;

            return true;
          }
          else if(buttonName.equals("G6")&& player==1){
            this.board[5][6] = 1;

            return true;
          }
          else if(buttonName.equals("H6")&& player==1){
            this.board[5][7] = 1;

            return true;
          }
          else if(buttonName.equals("I6")&& player==1){
            this.board[5][8] = 1;

            return true;
          }
          else if(buttonName.equals("J6")&& player==1){
            this.board[5][9] = 1;

            return true;
          }
          else if(buttonName.equals("K6")&& player==1){
            this.board[5][10] = 1;

            return true;
          }
          else if(buttonName.equals("L6")&& player==1){
            this.board[5][11] = 1;

            return true;
          }
          else if(buttonName.equals("M6")&& player==1){
            this.board[5][12] = 1;

            return true;
          }
          else if(buttonName.equals("N6")&& player==1){
            this.board[5][13] = 1;

            return true;
          }
          else if(buttonName.equals("O6")&& player==1){
            this.board[5][14] = 1;

            return true;
          }
          else if(buttonName.equals("A7")&& player==1){
            this.board[6][0] = 1;

            return true;
          }
          else if(buttonName.equals("B7")&& player==1){
            this.board[6][1] = 1;

            return true;
          }
          else if(buttonName.equals("C7")&& player==1){
            this.board[6][2] = 1;

            return true;
          }
          else if(buttonName.equals("D7")&& player==1){
            this.board[6][3] = 1;

            return true;
          }
          else if(buttonName.equals("E7")&& player==1){
            this.board[6][4] = 1;

            return true;
          }
          else if(buttonName.equals("F7")&& player==1){
            this.board[6][5] = 1;

            return true;
          }
          else if(buttonName.equals("G7")&& player==1){
            this.board[6][6] = 1;

            return true;
          }
          else if(buttonName.equals("H7")&& player==1){
            this.board[6][7] = 1;

            return true;
          }
          else if(buttonName.equals("I7")&& player==1){
            this.board[6][8] = 1;

            return true;
          }
          else if(buttonName.equals("J7")&& player==1){
            this.board[6][9] = 1;

            return true;
          }
          else if(buttonName.equals("K7")&& player==1){
            this.board[6][10] = 1;

            return true;
          }
          else if(buttonName.equals("L7")&& player==1){
            this.board[6][11] = 1;

            return true;
          }
          else if(buttonName.equals("M7")&& player==1){
            this.board[6][12] = 1;

            return true;
          }
          else if(buttonName.equals("N7")&& player==1){
            this.board[6][13] = 1;

            return true;
          }
          else if(buttonName.equals("N7")&& player==1){
            this.board[6][14] = 1;

            return true;
          }
          else if(buttonName.equals("A8")&& player==1){
            this.board[7][0] = 1;

            return true;
          }
          else if(buttonName.equals("B8")&& player==1){
            this.board[7][1] = 1;

            return true;
          }
          else if(buttonName.equals("C8")&& player==1){
            this.board[7][2] = 1;

            return true;
          }
          else if(buttonName.equals("D8")&& player==1){
            this.board[7][3] = 1;

            return true;
          }
          else if(buttonName.equals("E8")&& player==1){
            this.board[7][4] = 1;

            return true;
          }
          else if(buttonName.equals("F8")&& player==1){
            this.board[7][5] = 1;

            return true;
          }
          else if(buttonName.equals("G8")&& player==1){
            this.board[7][6] = 1;

            return true;
          }
          else if(buttonName.equals("H8")&& player==1){
            this.board[7][7] = 1;

            return true;
          }
          else if(buttonName.equals("I8")&& player==1){
            this.board[7][8] = 1;

            return true;
          }
          else if(buttonName.equals("J8")&& player==1){
            this.board[7][9] = 1;

            return true;
          }
          else if(buttonName.equals("K8")&& player==1){
            this.board[7][10] = 1;

            return true;
          }
          else if(buttonName.equals("L8")&& player==1){
            this.board[7][11] = 1;

            return true;
          }
          else if(buttonName.equals("M8")&& player==1){
            this.board[7][12] = 1;

            return true;
          }
          else if(buttonName.equals("N8")&& player==1){
            this.board[7][13] = 1;

            return true;
          }
          else if(buttonName.equals("O8")&& player==1){
            this.board[7][14] = 1;

            return true;
          }
          else if(buttonName.equals("A9")&& player==1){
            this.board[8][0] = 1;

            return true;
          }
          else if(buttonName.equals("B9")&& player==1){
            this.board[8][1] = 1;

            return true;
          }
          else if(buttonName.equals("C9")&& player==1){
            this.board[8][2] = 1;

            return true;
          }
          else if(buttonName.equals("D9")&& player==1){
            this.board[8][3] = 1;

            return true;
          }
          else if(buttonName.equals("E9")&& player==1){
            this.board[8][4] = 1;

            return true;
          }
          else if(buttonName.equals("F9")&& player==1){
            this.board[8][5] = 1;

            return true;
          }
          else if(buttonName.equals("G9")&& player==1){
            this.board[8][6] = 1;

            return true;
          }
          else if(buttonName.equals("H9")&& player==1){
            this.board[8][7] = 1;

            return true;
          }
          else if(buttonName.equals("I9")&& player==1){
            this.board[8][8] = 1;

            return true;
          }
          else if(buttonName.equals("J9")&& player==1){
            this.board[8][9] = 1;

            return true;
          }
          else if(buttonName.equals("K9")&& player==1){
            this.board[8][10] = 1;

            return true;
          }
          else if(buttonName.equals("L9")&& player==1){
            this.board[8][11] = 1;

            return true;
          }
          else if(buttonName.equals("M9")&& player==1){
            this.board[8][12] = 1;

            return true;
          }
          else if(buttonName.equals("N9")&& player==1){
            this.board[8][13] = 1;

            return true;
          }
          else if(buttonName.equals("O9")&& player==1){
            this.board[8][14] = 1;

            return true;
          }
          else if(buttonName.equals("A10")&& player==1){
            this.board[9][0] = 1;

            return true;
          }
          else if(buttonName.equals("B10")&& player==1){
            this.board[9][1] = 1;

            return true;
          }
          else if(buttonName.equals("C10")&& player==1){
            this.board[9][2] = 1;

            return true;
          }
          else if(buttonName.equals("D10")&& player==1){
            this.board[9][3] = 1;

            return true;
          }
          else if(buttonName.equals("E10")&& player==1){
            this.board[9][4] = 1;

            return true;
          }
          else if(buttonName.equals("F10")&& player==1){
            this.board[9][5] = 1;

            return true;
          }
          else if(buttonName.equals("G10")&& player==1){
            this.board[9][6] = 1;

            return true;
          }
          else if(buttonName.equals("H10")&& player==1){
            this.board[9][7] = 1;

            return true;
          }
          else if(buttonName.equals("I10")&& player==1){
            this.board[9][8] = 1;

            return true;
          }
          else if(buttonName.equals("J10")&& player==1){
            this.board[9][9] = 1;

            return true;
          }
          else if(buttonName.equals("K10")&& player==1){
            this.board[9][10] = 1;

            return true;
          }
          else if(buttonName.equals("L10")&& player==1){
            this.board[9][11] = 1;

            return true;
          }
          else if(buttonName.equals("M10")&& player==1){
            this.board[9][12] = 1;

            return true;
          }
          else if(buttonName.equals("N10")&& player==1){
            this.board[9][13] = 1;

            return true;
          }
          else if(buttonName.equals("O10")&& player==1){
            this.board[9][14] = 1;

            return true;
          }
          else if(buttonName.equals("A11")&& player==1){
            this.board[10][0] = 1;

            return true;
          }
          else if(buttonName.equals("B11")&& player==1){
            this.board[10][1] = 1;

            return true;
          }
          else if(buttonName.equals("C11")&& player==1){
            this.board[10][2] = 1;

            return true;
          }
          else if(buttonName.equals("D11")&& player==1){
            this.board[10][3] = 1;

            return true;
          }
          else if(buttonName.equals("E11")&& player==1){
            this.board[10][4] = 1;

            return true;
          }
          else if(buttonName.equals("F11")&& player==1){
            this.board[10][5] = 1;

            return true;
          }
          else if(buttonName.equals("G11")&& player==1){
            this.board[10][6] = 1;

            return true;
          }
          else if(buttonName.equals("H11")&& player==1){
            this.board[10][7] = 1;

            return true;
          }
          else if(buttonName.equals("I11")&& player==1){
            this.board[10][8] = 1;

            return true;
          }
          else if(buttonName.equals("J11")&& player==1){
            this.board[10][9] = 1;

            return true;
          }
          else if(buttonName.equals("K11")&& player==1){
            this.board[10][10] = 1;

            return true;
          }
          else if(buttonName.equals("L11")&& player==1){
            this.board[10][11] = 1;

            return true;
          }
          else if(buttonName.equals("M11")&& player==1){
            this.board[10][12] = 1;

            return true;
          }
          else if(buttonName.equals("N11")&& player==1){
            this.board[10][13] = 1;

            return true;
          }
          else if(buttonName.equals("O11")&& player==1){
            this.board[10][14] = 1;

            return true;
          }
          else if(buttonName.equals("A12")&& player==1){
            this.board[11][0] = 1;

            return true;
          }
          else if(buttonName.equals("B12")&& player==1){
            this.board[11][1] = 1;

            return true;
          }
          else if(buttonName.equals("C12")&& player==1){
            this.board[11][2] = 1;

            return true;
          }
          else if(buttonName.equals("D12")&& player==1){
            this.board[11][3] = 1;

            return true;
          }
          else if(buttonName.equals("E12")&& player==1){
            this.board[11][4] = 1;

            return true;
          }
          else if(buttonName.equals("F12")&& player==1){
            this.board[11][5] = 1;

            return true;
          }
          else if(buttonName.equals("G12")&& player==1){
            this.board[11][6] = 1;

            return true;
          }
          else if(buttonName.equals("H12")&& player==1){
            this.board[11][7] = 1;

            return true;
          }
          else if(buttonName.equals("I12")&& player==1){
            this.board[11][8] = 1;

            return true;
          }
          else if(buttonName.equals("J12")&& player==1){
            this.board[11][9] = 1;

            return true;
          }
          else if(buttonName.equals("K12")&& player==1){
            this.board[11][10] = 1;

            return true;
          }
          else if(buttonName.equals("L12")&& player==1){
            this.board[11][11] = 1;

            return true;
          }
          else if(buttonName.equals("M12")&& player==1){
            this.board[11][12] = 1;

            return true;
          }
          else if(buttonName.equals("N12")&& player==1){
            this.board[11][13] = 1;

            return true;
          }
          else if(buttonName.equals("O12")&& player==1){
            this.board[11][14] = 1;

            return true;
          }
          else if(buttonName.equals("A13")&& player==1){
            this.board[12][0] = 1;

            return true;
          }
          else if(buttonName.equals("B13")&& player==1){
            this.board[12][1] = 1;

            return true;
          }
          else if(buttonName.equals("C13")&& player==1){
            this.board[12][2] = 1;

            return true;
          }
          else if(buttonName.equals("D13")&& player==1){
            this.board[12][3] = 1;

            return true;
          }
          else if(buttonName.equals("E13")&& player==1){
            this.board[12][4] = 1;

            return true;
          }
          else if(buttonName.equals("F13")&& player==1){
            this.board[12][5] = 1;

            return true;
          }
          else if(buttonName.equals("G13")&& player==1){
            this.board[12][6] = 1;

            return true;
          }
          else if(buttonName.equals("H13")&& player==1){
            this.board[12][7] = 1;

            return true;
          }
          else if(buttonName.equals("I13")&& player==1){
            this.board[12][8] = 1;

            return true;
          }
          else if(buttonName.equals("J13")&& player==1){
            this.board[12][9] = 1;

            return true;
          }
          else if(buttonName.equals("K13")&& player==1){
            this.board[12][10] = 1;

            return true;
          }
          else if(buttonName.equals("L13")&& player==1){
            this.board[12][11] = 1;

            return true;
          }
          else if(buttonName.equals("M13")&& player==1){
            this.board[12][12] = 1;

            return true;
          }
          else if(buttonName.equals("N13")&& player==1){
            this.board[12][13] = 1;

            return true;
          }
          else if(buttonName.equals("O13")&& player==1){
            this.board[12][14] = 1;

            return true;
          }
          else if(buttonName.equals("A14")&& player==1){
            this.board[13][0] = 1;

            return true;
          }
          else if(buttonName.equals("B14")&& player==1){
            this.board[13][1] = 1;

            return true;
          }
          else if(buttonName.equals("C14")&& player==1){
            this.board[13][2] = 1;

            return true;
          }
          else if(buttonName.equals("D14")&& player==1){
            this.board[13][3] = 1;

            return true;
          }
          else if(buttonName.equals("E14")&& player==1){
            this.board[13][4] = 1;

            return true;
          }
          else if(buttonName.equals("F14")&& player==1){
            this.board[13][5] = 1;

            return true;
          }
          else if(buttonName.equals("G14")&& player==1){
            this.board[13][6] = 1;

            return true;
          }
          else if(buttonName.equals("H14")&& player==1){
            this.board[13][7] = 1;

            return true;
          }
          else if(buttonName.equals("I14")&& player==1){
            this.board[13][8] = 1;

            return true;
          }
          else if(buttonName.equals("J14")&& player==1){
            this.board[13][9] = 1;

            return true;
          }
          else if(buttonName.equals("K14")&& player==1){
            this.board[13][10] = 1;

            return true;
          }
          else if(buttonName.equals("L14")&& player==1){
            this.board[13][11] = 1;

            return true;
          }
          else if(buttonName.equals("M14")&& player==1){
            this.board[13][12] = 1;

            return true;
          }
          else if(buttonName.equals("N14")&& player==1){
            this.board[13][13] = 1;

            return true;
          }
          else if(buttonName.equals("O14")&& player==1){
            this.board[13][14] = 1;

            return true;
          }
          else if(buttonName.equals("A15")&& player==1){
            this.board[14][0] = 1;

            return true;
          }
          else if(buttonName.equals("B15")&& player==1){
            this.board[14][1] = 1;

            return true;
          }
          else if(buttonName.equals("C15")&& player==1){
            this.board[14][2] = 1;

            return true;
          }
          else if(buttonName.equals("D15")&& player==1){
            this.board[14][3] = 1;

            return true;
          }
          else if(buttonName.equals("E15")&& player==1){
            this.board[14][4] = 1;

            return true;
          }
          else if(buttonName.equals("F15")&& player==1){
            this.board[14][5] = 1;

            return true;
          }
          else if(buttonName.equals("G15")&& player==1){
            this.board[14][6] = 1;

            return true;
          }
          else if(buttonName.equals("H15")&& player==1){
            this.board[14][7] = 1;

            return true;
          }
          else if(buttonName.equals("I15")&& player==1){
            this.board[14][8] = 1;

            return true;
          }
          else if(buttonName.equals("J15")&& player==1){
            this.board[14][9] = 1;

            return true;
          }
          else if(buttonName.equals("K15")&& player==1){
            this.board[14][10] = 1;

            return true;
          }
          else if(buttonName.equals("L15")&& player==1){
            this.board[14][11] = 1;

            return true;
          }
          else if(buttonName.equals("M15")&& player==1){
            this.board[14][12] = 1;

            return true;
          }
          else if(buttonName.equals("N15")&& player==1){
            this.board[14][13] = 1;

            return true;
          }
          else if(buttonName.equals("O15")&& player==1){
            this.board[14][14] = 1;

            return true;
          }
          else if(buttonName.equals("A1")&& player==2){
            this.board[0][0] = 2;

            return true;
          }
          else if(buttonName.equals("B1")&& player==2){
            this.board[0][1] = 2;

            return true;
          }
          else if(buttonName.equals("C1")&& player==2){
            this.board[0][2] = 2;

            return true;
          }
          else if(buttonName.equals("D1")&& player==2){
            this.board[0][3] = 2;

            return true;
          }
          else if(buttonName.equals("E1")&& player==2){
            this.board[0][4] = 2;

            return true;
          }
          else if(buttonName.equals("F1")&& player==2){
            this.board[0][5] = 2;

            return true;
          }
          else if(buttonName.equals("G1")&& player==2){
            this.board[0][6] = 2;

            return true;
          }
          else if(buttonName.equals("H1")&& player==2){
            this.board[0][7] = 2;

            return true;
          }
          else if(buttonName.equals("I1")&& player==2){
            this.board[0][8] = 2;

            return true;
          }
          else if(buttonName.equals("J1")&& player==2){
            this.board[0][9] = 2;

            return true;
          }
          else if(buttonName.equals("K1")&& player==2){
            this.board[0][10] = 2;

            return true;
          }
          else if(buttonName.equals("L1")&& player==2){
            this.board[0][11] = 2;

            return true;
          }
          else if(buttonName.equals("M1")&& player==2){
            this.board[0][12] = 2;

            return true;
          }
          else if(buttonName.equals("N1")&& player==2){
            this.board[0][13] = 2;

            return true;
          }
          else if(buttonName.equals("O1")&& player==2){
            this.board[0][14] = 2;

            return true;
          }
          else if(buttonName.equals("A2")&& player==2){
            this.board[1][0] = 2;

            return true;
          }
          else if(buttonName.equals("B2")&& player==2){
            this.board[1][1] = 2;

            return true;
          }
          else if(buttonName.equals("C2")&& player==2){
            this.board[1][2] = 2;

            return true;
          }
          else if(buttonName.equals("D2")&& player==2){
            this.board[1][3] = 2;

            return true;
          }
          else if(buttonName.equals("E2")&& player==2){
            this.board[1][4] = 2;

            return true;
          }
          else if(buttonName.equals("F2")&& player==2){
            this.board[1][5] = 2;

            return true;
          }
          else if(buttonName.equals("G2")&& player==2){
            this.board[1][6] = 2;

            return true;
          }
          else if(buttonName.equals("H2")&& player==2){
            this.board[1][7] = 2;

            return true;
          }
          else if(buttonName.equals("I2")&& player==2){
            this.board[1][8] = 2;

            return true;
          }
          else if(buttonName.equals("J2")&& player==2){
            this.board[1][9] = 2;

            return true;
          }
          else if(buttonName.equals("K2")&& player==2){
            this.board[1][10] = 2;

            return true;
          }
          else if(buttonName.equals("L2")&& player==2){
            this.board[1][11] = 2;

            return true;
          }
          else if(buttonName.equals("M2")&& player==2){
            this.board[1][12] = 2;

            return true;
          }
          else if(buttonName.equals("N2")&& player==2){
            this.board[1][13] = 2;

            return true;
          }
          else if(buttonName.equals("O2")&& player==2){
            this.board[1][14] = 2;

            return true;
          }
          else if(buttonName.equals("A3")&& player==2){
            this.board[2][0] = 2;

            return true;
          }
          else if(buttonName.equals("B3")&& player==2){
            this.board[2][1] = 2;

            return true;
          }
          else if(buttonName.equals("C3")&& player==2){
            this.board[2][2] = 2;

            return true;
          }
          else if(buttonName.equals("D3")&& player==2){
            this.board[2][3] = 2;

            return true;
          }
          else if(buttonName.equals("E3")&& player==2){
            this.board[2][4] = 2;

            return true;
          }
          else if(buttonName.equals("F3")&& player==2){
            this.board[2][5] = 2;

            return true;
          }
          else if(buttonName.equals("G3")&& player==2){
            this.board[2][6] = 2;

            return true;
          }
          else if(buttonName.equals("H3")&& player==2){
            this.board[2][7] = 2;

            return true;
          }
          else if(buttonName.equals("I3")&& player==2){
            this.board[2][8] = 2;

            return true;
          }
          else if(buttonName.equals("J3")&& player==2){
            this.board[2][9] = 2;

            return true;
          }
          else if(buttonName.equals("K3")&& player==2){
            this.board[2][10] = 2;

            return true;
          }
          else if(buttonName.equals("L3")&& player==2){
            this.board[2][11] = 2;

            return true;
          }
          else if(buttonName.equals("M3")&& player==2){
            this.board[2][12] = 2;

            return true;
          }
          else if(buttonName.equals("N3")&& player==2){
            this.board[2][13] = 2;

            return true;
          }
          else if(buttonName.equals("O3")&& player==2){
            this.board[2][14] = 2;

            return true;
          }
          else if(buttonName.equals("A4")&& player==2){
            this.board[3][0] = 2;

            return true;
          }
          else if(buttonName.equals("B4")&& player==2){
            this.board[3][1] = 2;

            return true;
          }
          else if(buttonName.equals("C4")&& player==2){
            this.board[3][2] = 2;

            return true;
          }
          else if(buttonName.equals("D4")&& player==2){
            this.board[3][3] = 2;

            return true;
          }
          else if(buttonName.equals("E4")&& player==2){
            this.board[3][4] = 2;

            return true;
          }
          else if(buttonName.equals("F4")&& player==2){
            this.board[3][5] = 2;

            return true;
          }
          else if(buttonName.equals("G4")&& player==2){
            this.board[3][6] = 2;

            return true;
          }
          else if(buttonName.equals("H4")&& player==2){
            this.board[3][7] = 2;

            return true;
          }
          else if(buttonName.equals("I4")&& player==2){
            this.board[3][8] = 2;

            return true;
          }
          else if(buttonName.equals("J4")&& player==2){
            this.board[3][9] = 2;

            return true;
          }
          else if(buttonName.equals("K4")&& player==2){
            this.board[3][10] = 2;

            return true;
          }
          else if(buttonName.equals("L4")&& player==2){
            this.board[3][11] = 2;

            return true;
          }
          else if(buttonName.equals("M4")&& player==2){
            this.board[3][12] = 2;

            return true;
          }
          else if(buttonName.equals("N4")&& player==2){
            this.board[3][13] = 2;

            return true;
          }
          else if(buttonName.equals("O4")&& player==2){
            this.board[3][14] = 2;

            return true;
          }
          else if(buttonName.equals("A5")&& player==2){
            this.board[4][0] = 2;

            return true;
          }
          else if(buttonName.equals("B5")&& player==2){
            this.board[4][1] = 2;

            return true;
          }
          else if(buttonName.equals("C5")&& player==2){
            this.board[4][2] = 2;

            return true;
          }
          else if(buttonName.equals("D5")&& player==2){
            this.board[4][3] = 2;

            return true;
          }
          else if(buttonName.equals("E5")&& player==2){
            this.board[4][4] = 2;

            return true;
          }
          else if(buttonName.equals("F5")&& player==2){
            this.board[4][5] = 2;

            return true;
          }
          else if(buttonName.equals("G5")&& player==2){
            this.board[4][6] = 2;

            return true;
          }
          else if(buttonName.equals("H5")&& player==2){
            this.board[4][7] = 2;

            return true;
          }
          else if(buttonName.equals("I5")&& player==2){
            this.board[4][8] = 2;

            return true;
          }
          else if(buttonName.equals("J5")&& player==2){
            this.board[4][9] = 2;

            return true;
          }
          else if(buttonName.equals("K5")&& player==2){
            this.board[4][10] = 2;

            return true;
          }
          else if(buttonName.equals("L5")&& player==2){
            this.board[4][11] = 2;

            return true;
          }
          else if(buttonName.equals("M5")&& player==2){
            this.board[4][12] = 2;

            return true;
          }
          else if(buttonName.equals("N5")&& player==2){
            this.board[4][13] = 2;

            return true;
          }
          else if(buttonName.equals("O5")&& player==2){
            this.board[4][14] = 2;

            return true;
          }
          else if(buttonName.equals("A6")&& player==2){
            this.board[5][0] = 2;

            return true;
          }
          else if(buttonName.equals("B6")&& player==2){
            this.board[5][1] = 2;

            return true;
          }
          else if(buttonName.equals("C6")&& player==2){
            this.board[5][2] = 2;

            return true;
          }
          else if(buttonName.equals("D6")&& player==2){
            this.board[5][3] = 2;

            return true;
          }
          else if(buttonName.equals("E6")&& player==2){
            this.board[5][4] = 2;

            return true;
          }
          else if(buttonName.equals("F6")&& player==2){
            this.board[5][5] = 2;

            return true;
          }
          else if(buttonName.equals("G6")&& player==2){
            this.board[5][6] = 2;

            return true;
          }
          else if(buttonName.equals("H6")&& player==2){
            this.board[5][7] = 2;

            return true;
          }
          else if(buttonName.equals("I6")&& player==2){
            this.board[5][8] = 2;

            return true;
          }
          else if(buttonName.equals("J6")&& player==2){
            this.board[5][9] = 2;

            return true;
          }
          else if(buttonName.equals("K6")&& player==2){
            this.board[5][10] = 2;

            return true;
          }
          else if(buttonName.equals("L6")&& player==2){
            this.board[5][11] = 2;

            return true;
          }
          else if(buttonName.equals("M6")&& player==2){
            this.board[5][12] = 2;

            return true;
          }
          else if(buttonName.equals("N6")&& player==2){
            this.board[5][13] = 2;

            return true;
          }
          else if(buttonName.equals("O6")&& player==2){
            this.board[5][14] = 2;

            return true;
          }
          else if(buttonName.equals("A7")&& player==2){
            this.board[6][0] = 2;

            return true;
          }
          else if(buttonName.equals("B7")&& player==2){
            this.board[6][1] = 2;

            return true;
          }
          else if(buttonName.equals("C7")&& player==2){
            this.board[6][2] = 2;

            return true;
          }
          else if(buttonName.equals("D7")&& player==2){
            this.board[6][3] = 2;

            return true;
          }
          else if(buttonName.equals("E7")&& player==2){
            this.board[6][4] = 2;

            return true;
          }
          else if(buttonName.equals("F7")&& player==2){
            this.board[6][5] = 2;

            return true;
          }
          else if(buttonName.equals("G7")&& player==2){
            this.board[6][6] = 2;

            return true;
          }
          else if(buttonName.equals("H7")&& player==2){
            this.board[6][7] = 2;

            return true;
          }
          else if(buttonName.equals("I7")&& player==2){
            this.board[6][8] = 2;

            return true;
          }
          else if(buttonName.equals("J7")&& player==2){
            this.board[6][9] = 2;

            return true;
          }
          else if(buttonName.equals("K7")&& player==2){
            this.board[6][10] = 2;

            return true;
          }
          else if(buttonName.equals("L7")&& player==2){
            this.board[6][11] = 2;

            return true;
          }
          else if(buttonName.equals("M7")&& player==2){
            this.board[6][12] = 2;

            return true;
          }
          else if(buttonName.equals("N7")&& player==2){
            this.board[6][13] = 2;

            return true;
          }
          else if(buttonName.equals("O7")&& player==2){
            this.board[6][14] = 2;

            return true;
          }
          else if(buttonName.equals("A8")&& player==2){
            this.board[7][0] = 2;

            return true;
          }
          else if(buttonName.equals("B8")&& player==2){
            this.board[7][1] = 2;

            return true;
          }
          else if(buttonName.equals("C8")&& player==2){
            this.board[7][2] = 2;

            return true;
          }
          else if(buttonName.equals("D8")&& player==2){
            this.board[7][3] = 2;

            return true;
          }
          else if(buttonName.equals("E8")&& player==2){
            this.board[7][4] = 2;

            return true;
          }
          else if(buttonName.equals("F8")&& player==2){
            this.board[7][5] = 2;

            return true;
          }
          else if(buttonName.equals("G8")&& player==2){
            this.board[7][6] = 2;

            return true;
          }
          else if(buttonName.equals("H8")&& player==2){
            this.board[7][7] = 2;

            return true;
          }
          else if(buttonName.equals("I8")&& player==2){
            this.board[7][8] = 2;

            return true;
          }
          else if(buttonName.equals("J8")&& player==2){
            this.board[7][9] = 2;

            return true;
          }
          else if(buttonName.equals("K8")&& player==2){
            this.board[7][10] = 2;

            return true;
          }
          else if(buttonName.equals("L8")&& player==2){
            this.board[7][11] = 2;

            return true;
          }
          else if(buttonName.equals("M8")&& player==2){
            this.board[7][12] = 2;

            return true;
          }
          else if(buttonName.equals("N8")&& player==2){
            this.board[7][13] = 2;

            return true;
          }
          else if(buttonName.equals("O8")&& player==2){
            this.board[7][14] = 2;

            return true;
          }
          else if(buttonName.equals("A9")&& player==2){
            this.board[8][0] = 2;

            return true;
          }
          else if(buttonName.equals("B9")&& player==2){
            this.board[8][1] = 2;

            return true;
          }
          else if(buttonName.equals("C9")&& player==2){
            this.board[8][2] = 2;

            return true;
          }
          else if(buttonName.equals("D9")&& player==2){
            this.board[8][3] = 2;

            return true;
          }
          else if(buttonName.equals("E9")&& player==2){
            this.board[8][4] = 2;

            return true;
          }
          else if(buttonName.equals("F9")&& player==2){
            this.board[8][5] = 2;

            return true;
          }
          else if(buttonName.equals("G9")&& player==2){
            this.board[8][6] = 2;

            return true;
          }
          else if(buttonName.equals("H9")&& player==2){
            this.board[8][7] = 2;

            return true;
          }
          else if(buttonName.equals("I9")&& player==2){
            this.board[8][8] = 2;

            return true;
          }
          else if(buttonName.equals("J9")&& player==2){
            this.board[8][9] = 2;

            return true;
          }
          else if(buttonName.equals("K9")&& player==2){
            this.board[8][10] = 2;

            return true;
          }
          else if(buttonName.equals("L9")&& player==2){
            this.board[8][11] = 2;

            return true;
          }
          else if(buttonName.equals("M9")&& player==2){
            this.board[8][12] = 2;

            return true;
          }
          else if(buttonName.equals("N9")&& player==2){
            this.board[8][13] = 2;

            return true;
          }
          else if(buttonName.equals("O9")&& player==2){
            this.board[8][14] = 2;

            return true;
          }
          else if(buttonName.equals("A10")&& player==2){
            this.board[9][0] = 2;

            return true;
          }
          else if(buttonName.equals("B10")&& player==2){
            this.board[9][1] = 2;

            return true;
          }
          else if(buttonName.equals("C10")&& player==2){
            this.board[9][2] = 2;

            return true;
          }
          else if(buttonName.equals("D10")&& player==2){
            this.board[9][3] = 2;

            return true;
          }
          else if(buttonName.equals("E10")&& player==2){
            this.board[9][4] = 2;

            return true;
          }
          else if(buttonName.equals("F10")&& player==2){
            this.board[9][5] = 2;

            return true;
          }
          else if(buttonName.equals("G10")&& player==2){
            this.board[9][6] = 2;

            return true;
          }
          else if(buttonName.equals("H10")&& player==2){
            this.board[9][7] = 2;

            return true;
          }
          else if(buttonName.equals("I10")&& player==2){
            this.board[9][8] = 2;

            return true;
          }
          else if(buttonName.equals("J10")&& player==2){
            this.board[9][9] = 2;

            return true;
          }
          else if(buttonName.equals("K10")&& player==2){
            this.board[9][10] = 2;

            return true;
          }
          else if(buttonName.equals("L10")&& player==2){
            this.board[9][11] = 2;

            return true;
          }
          else if(buttonName.equals("M10")&& player==2){
            this.board[9][12] = 2;

            return true;
          }
          else if(buttonName.equals("N10")&& player==2){
            this.board[9][13] = 2;

            return true;
          }
          else if(buttonName.equals("O10")&& player==2){
            this.board[9][14] = 2;

            return true;
          }
          else if(buttonName.equals("A11")&& player==2){
            this.board[10][0] = 2;

            return true;
          }
          else if(buttonName.equals("B11")&& player==2){
            this.board[10][1] = 2;

            return true;
          }
          else if(buttonName.equals("C11")&& player==2){
            this.board[10][2] = 2;

            return true;
          }
          else if(buttonName.equals("D11")&& player==2){
            this.board[10][3] = 2;

            return true;
          }
          else if(buttonName.equals("E11")&& player==2){
            this.board[10][4] = 2;

            return true;
          }
          else if(buttonName.equals("F11")&& player==2){
            this.board[10][5] = 2;

            return true;
          }
          else if(buttonName.equals("G11")&& player==2){
            this.board[10][6] = 2;

            return true;
          }
          else if(buttonName.equals("H11")&& player==2){
            this.board[10][7] = 2;

            return true;
          }
          else if(buttonName.equals("I11")&& player==2){
            this.board[10][8] = 2;

            return true;
          }
          else if(buttonName.equals("J11")&& player==2){
            this.board[10][9] = 2;

            return true;
          }
          else if(buttonName.equals("K11")&& player==2){
            this.board[10][10] = 2;

            return true;
          }
          else if(buttonName.equals("L11")&& player==2){
            this.board[10][11] = 2;

            return true;
          }
          else if(buttonName.equals("M11")&& player==2){
            this.board[10][12] = 2;

            return true;
          }
          else if(buttonName.equals("N11")&& player==2){
            this.board[10][13] = 2;

            return true;
          }
          else if(buttonName.equals("O11")&& player==2){
            this.board[10][14] = 2;

            return true;
          }
          else if(buttonName.equals("A12")&& player==2){
            this.board[11][0] = 2;

            return true;
          }
          else if(buttonName.equals("B12")&& player==2){
            this.board[11][1] = 2;

            return true;
          }
          else if(buttonName.equals("C12")&& player==2){
            this.board[11][2] = 2;

            return true;
          }
          else if(buttonName.equals("D12")&& player==2){
            this.board[11][3] = 2;

            return true;
          }
          else if(buttonName.equals("E12")&& player==2){
            this.board[11][4] = 2;

            return true;
          }
          else if(buttonName.equals("F12")&& player==2){
            this.board[11][5] = 2;

            return true;
          }
          else if(buttonName.equals("G12")&& player==2){
            this.board[11][6] = 2;

            return true;
          }
          else if(buttonName.equals("H12")&& player==2){
            this.board[11][7] = 2;

            return true;
          }
          else if(buttonName.equals("I12")&& player==2){
            this.board[11][8] = 2;

            return true;
          }
          else if(buttonName.equals("J12")&& player==2){
            this.board[11][9] = 2;

            return true;
          }
          else if(buttonName.equals("K12")&& player==2){
            this.board[11][10] = 2;

            return true;
          }
          else if(buttonName.equals("L12")&& player==2){
            this.board[11][11] = 2;

            return true;
          }
          else if(buttonName.equals("M12")&& player==2){
            this.board[11][12] = 2;

            return true;
          }
          else if(buttonName.equals("N12")&& player==2){
            this.board[11][13] = 2;

            return true;
          }
          else if(buttonName.equals("O12")&& player==2){
            this.board[11][14] = 2;

            return true;
          }
          else if(buttonName.equals("A13")&& player==2){
            this.board[12][0] = 2;

            return true;
          }
          else if(buttonName.equals("B13")&& player==2){
            this.board[12][1] = 2;

            return true;
          }
          else if(buttonName.equals("C13")&& player==2){
            this.board[12][2] = 2;

            return true;
          }
          else if(buttonName.equals("D13")&& player==2){
            this.board[12][3] = 2;

            return true;
          }
          else if(buttonName.equals("E13")&& player==2){
            this.board[12][4] = 2;

            return true;
          }
          else if(buttonName.equals("F13")&& player==2){
            this.board[12][5] = 2;

            return true;
          }
          else if(buttonName.equals("G13")&& player==2){
            this.board[12][6] = 2;

            return true;
          }
          else if(buttonName.equals("H13")&& player==2){
            this.board[12][7] = 2;

            return true;
          }
          else if(buttonName.equals("I13")&& player==2){
            this.board[12][8] = 2;

            return true;
          }
          else if(buttonName.equals("J13")&& player==2){
            this.board[12][9] = 2;

            return true;
          }
          else if(buttonName.equals("K13")&& player==2){
            this.board[12][10] = 2;

            return true;
          }
          else if(buttonName.equals("L13")&& player==2){
            this.board[12][11] = 2;

            return true;
          }
          else if(buttonName.equals("M13")&& player==2){
            this.board[12][12] = 2;

            return true;
          }
          else if(buttonName.equals("N13")&& player==2){
            this.board[12][13] = 2;

            return true;
          }
          else if(buttonName.equals("O13")&& player==2){
            this.board[12][14] = 2;

            return true;
          }
          else if(buttonName.equals("A14")&& player==2){
            this.board[13][0] = 2;

            return true;
          }
          else if(buttonName.equals("B14")&& player==2){
            this.board[13][1] = 2;

            return true;
          }
          else if(buttonName.equals("C14")&& player==2){
            this.board[13][2] = 2;

            return true;
          }
          else if(buttonName.equals("D14")&& player==2){
            this.board[13][3] = 2;

            return true;
          }
          else if(buttonName.equals("E14")&& player==2){
            this.board[13][4] = 2;

            return true;
          }
          else if(buttonName.equals("F14")&& player==2){
            this.board[13][5] = 2;

            return true;
          }
          else if(buttonName.equals("G14")&& player==2){
            this.board[13][6] = 2;

            return true;
          }
          else if(buttonName.equals("H14")&& player==2){
            this.board[13][7] = 2;

            return true;
          }
          else if(buttonName.equals("I14")&& player==2){
            this.board[13][8] = 2;

            return true;
          }
          else if(buttonName.equals("J14")&& player==2){
            this.board[13][9] = 2;

            return true;
          }
          else if(buttonName.equals("K14")&& player==2){
            this.board[13][10] = 2;

            return true;
          }
          else if(buttonName.equals("L14")&& player==2){
            this.board[13][11] = 2;

            return true;
          }
          else if(buttonName.equals("M14")&& player==2){
            this.board[13][12] = 2;

            return true;
          }
          else if(buttonName.equals("N14")&& player==2){
            this.board[13][13] = 2;

            return true;
          }
          else if(buttonName.equals("O14")&& player==2){
            this.board[13][14] = 2;

            return true;
          }
          else if(buttonName.equals("A15")&& player==2){
            this.board[14][0] = 2;

            return true;
          }
          else if(buttonName.equals("B15")&& player==2){
            this.board[14][1] = 2;

            return true;
          }
          else if(buttonName.equals("C15")&& player==2){
            this.board[14][2] = 2;

            return true;
          }
          else if(buttonName.equals("D15")&& player==2){
            this.board[14][3] = 2;

            return true;
          }
          else if(buttonName.equals("E15")&& player==2){
            this.board[14][4] = 2;

            return true;
          }
          else if(buttonName.equals("F15")&& player==2){
            this.board[14][5] = 2;

            return true;
          }
          else if(buttonName.equals("G15")&& player==2){
            this.board[14][6] = 2;

            return true;
          }
          else if(buttonName.equals("H15")&& player==2){
            this.board[14][7] = 2;

            return true;
          }
          else if(buttonName.equals("I15")&& player==2){
            this.board[14][8] = 2;

            return true;
          }
          else if(buttonName.equals("J15")&& player==2){
            this.board[14][9] = 2;

            return true;
          }
          else if(buttonName.equals("K15")&& player==2){
            this.board[14][10] = 2;

            return true;
          }
          else if(buttonName.equals("L15")&& player==2){
            this.board[14][11] = 2;

            return true;
          }
          else if(buttonName.equals("M15")&& player==2){
            this.board[14][12] = 2;

            return true;
          }
          else if(buttonName.equals("N15")&& player==2){
            this.board[14][13] = 2;

            return true;
          }
          else if(buttonName.equals("O15")&& player==2){
            this.board[14][14] = 2;

            return true;
          }
          else{
            return false;
          }
    }

    public int getBoardToken(int i, int j){
      return board[i][j];
    }

    public void setBoardToken(int i, int j, int p){
      this.board[i][j] = p;
    }

    public int getRDMNumber(){
      Random rand = new Random();
      int r = rand.nextInt(getBoardXDimensions());
      return r;
    }
  }
