import java.util.Random;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.awt.Point;
import java.util.*;
import java.lang.*;

public class MCTSNode{
  private int visitCount;
  private int winCount;
  private int currentPlayer;
  private int[][] gameState;
  private ArrayList<MCTSNode> childrenNode = new ArrayList<MCTSNode>();
  private MCTSNode parentNode = null;
  private ArrayList<Point> possibleMoves = new ArrayList<Point>();
  private int moveMadeRow;
  private int moveMadeCol;

  /*
  String representation of the state of each Node.

  PC's read left to right.
  so it will always be 00 - 02 left to right, then 10 - 12, then 20-22
  I have mapped the board differently so that A1

  |A1|B1|C1|
  |A2|B2|C2|
  |A3|B2|C3|

  00|01|02
  10|11|12
  20|21|22

  Also Important Board info.

  currentPlayerMark = 1; Node will always start as on player 2
  humanPlayer = 1;
  computerPlayer = 2;

  */

public MCTSNode(BoardGame game){
  visitCount = 0;
  winCount = 0;
  currentPlayer = 2; //human player is 1, while com player is 2. root node is always player 2, as com will always play after human
  gameState = new int[game.getBoardXDimensions()][game.getBoardYDimensions()];
}

public void getBoardState(BoardGame game){
  for(int i = 0;i<game.getBoardXDimensions();i++){
    for(int j = 0;j<game.getBoardYDimensions();j++){
      gameState[i][j] = game.getBoardToken(i, j);
      //System.out.println(gameState[i][j]);
    }
  }
}

public int getPossibleMovesList(){
  return this.possibleMoves.size();
}

public void setPossibleMoves(){
  for(int i = 0;i<gameState.length;i++){
    for(int j = 0;j<gameState.length;j++){
      if(gameState[i][j]==0){
        Point emptySpace = new Point(i,j);
        possibleMoves.add(emptySpace);
      }
    }
  }
}

public void getPossibleMovesString(){
  for(int k= 0;k<possibleMoves.size();k++){
    System.out.println(getPossibleMoveX(k) + "," + getPossibleMoveY(k));
  }
}

public int getPossibleMoveX(int i){
  return possibleMoves.get(i).x;
}

public int getPossibleMoveY(int i){
  return possibleMoves.get(i).y;
}

public void removePossibleMove(int i){
  this.possibleMoves.remove(i);
}

public Point getPossibleMove(int i){
  return possibleMoves.get(i);
}

public int getPossibleMovesSize(){
  return possibleMoves.size();
}

public MCTSNode Select(){
  double maxUCT = Integer.MIN_VALUE;
  MCTSNode Node = this;
  if(this.possibleMoves.size()>0){
    return Node;
      }
  else{
    for(int i = 0;i<childrenNode.size();i++){
      double UCTValue = getUCTValue(getChildren(i));
      if(UCTValue > maxUCT){
        Node = getChildren(i);
        maxUCT = UCTValue;
      }
    }
    return Node;
  }
}

private double getUCTValue(MCTSNode childNode) {
        double UCTValue;
        if (childNode.getVisitCount() >= 1) {
          UCTValue = (Math.sqrt(2)*
              (Math.sqrt(Math.log(childNode.getParent().getVisitCount()* 1.0)
			  / childNode.getVisitCount()))
              + (1.0 *childNode.getWinCount() / childNode.getVisitCount()* 1.0));
        } else {
            UCTValue = Double.MAX_VALUE;
        }
        return UCTValue;
  }

  public int getWinCount(){
    return winCount;
  }

public MCTSNode Expand(BoardGame game){
    MCTSNode child = new MCTSNode(game);
    for(int k = 0;k<this.gameState[0].length;k++){
      for(int l = 0;l<this.gameState[1].length;l++){
        child.gameState[k][l] = this.gameState[k][l];
      }
    }
    Random r = new Random();
    int possibleMoveSelected = r.nextInt(getPossibleMovesList());
    int row = getPossibleMoveX(possibleMoveSelected);
    int col = getPossibleMoveY(possibleMoveSelected);
    if(this.currentPlayer==2){
      child.gameState[row][col] = 2;
      child.moveMadeRow = row;
      child.moveMadeCol = col;
      child.currentPlayer = 1;
      child.setPossibleMoves();
      child.possibleMoves.trimToSize();
    }
    else{
      child.gameState[row][col] = 1;
      child.moveMadeRow = row;
      child.moveMadeCol = col;
      child.currentPlayer = 2;
      child.setPossibleMoves();
      child.possibleMoves.trimToSize();
    }
    childrenNode.add(child);
    childrenNode.trimToSize();
    child.parentNode = this;
    this.removePossibleMove(possibleMoveSelected);
    this.possibleMoves.trimToSize();
    return child;
}

public void setMoveMadeRow(int row){
    this.moveMadeRow = row;
  }

  public void setMoveMadeCol(int col){
    this.moveMadeCol = col;
  }

public void Simulate(){
  if(this.possibleMoves.size()==0){
    if(this.checkWinPlayer2()==true){
      this.Backpropogate(2);
    }
    else if(this.checkWinPlayer1()==true){
      this.Backpropogate(1);
    }
    else{
      this.Backpropogate(0);
    }
  }
  else{
    BoardGame tempBoard = new BoardGame(this.gameState[0].length);
    for(int k = 0;k<tempBoard.getBoardXDimensions();k++){
      for(int l = 0;l<tempBoard.getBoardYDimensions();l++){
        tempBoard.setBoardToken(k,l,this.gameState[k][l]);
      }
    }
    int currentTempPlayerMark = this.currentPlayer;
    for(int i=0;i<=5;i++){
        Point move = getSimulatedMove(tempBoard, currentTempPlayerMark);
        tempBoard.setBoardToken(move.x, move.y, currentTempPlayerMark);
        if(tempBoard.checkWinPlayer2()){
          this.Backpropogate(2);
          break;
        }
        else if(tempBoard.checkWinPlayer1()){
          this.Backpropogate(1);
          break;
        }
        else if(tempBoard.isBoardFull()&&!tempBoard.checkWinPlayer1()&&!tempBoard.checkWinPlayer2()){
          this.Backpropogate(0);
          break;
        }
        if(currentTempPlayerMark == 1){
          currentTempPlayerMark = 2;
        }
        else{
          currentTempPlayerMark = 1;
        }
      }
      if(!tempBoard.isBoardFull()&&!tempBoard.checkWinPlayer1()&&!tempBoard.checkWinPlayer2()){
        this.Backpropogate(0);
      }
    }
  }

public MCTSNode getParent(){
  return parentNode;
}

public void Backpropogate(int winningPlayer){
  MCTSNode tempNode = this;
    while (tempNode!= null){
        if (winningPlayer == 1) {
            tempNode.visitCount=tempNode.visitCount+1;
        }
        else if(winningPlayer == 2) {
            tempNode.visitCount=tempNode.visitCount+1;
            tempNode.winCount=tempNode.winCount+1;
        }
        else{
          tempNode.visitCount=tempNode.visitCount+1;
          tempNode.winCount=tempNode.winCount+2;
        }
          tempNode = tempNode.getParent();
    }
}

public MCTSNode getMostVisitNode(){
  int mostVisited = 0;
  MCTSNode mostVisitedNode = null;
  for(int i = 0;i<this.childrenNode.size();i++){
    if(this.getChildren(i).getVisitCount() > mostVisited){
      mostVisited = this.getChildren(i).getVisitCount();
      mostVisitedNode = this.getChildren(i);
    }
  }
  return mostVisitedNode;
}

public int getMoveMadeRow(){
  return moveMadeRow;
}

public int getMoveMadeCol(){
  return moveMadeCol;
}

public boolean childrenLeft(){
  return childrenNode.size()>0;
  }

public int childrenList(){
  return childrenNode.size();
}

public MCTSNode getChildren(int i){
  return childrenNode.get(i);
}

  public void printNode(){
    for(int i = 0;i<gameState.length;i++){
      if (i<10){
      System.out.print("  "+(i+1) + " ");
      }
      else{
        System.out.print(" "+(i+1) + " ");
      }
    }
    System.out.println("");
    if(gameState.length<=4){
      System.out.println("-------------");
    }
    else{
      for(int x = 0;x<(gameState.length/3);x++)
      System.out.print("-------------");
      System.out.println("");
    }
    for (int i = 0; i < gameState.length; i++) {
        System.out.print("| ");
        for (int j = 0; j < gameState.length; j++) {
            System.out.print(gameState[i][j] + " | ");
            if (j==gameState.length-1){
                System.out.print(i+1);
          }
        }
        System.out.println();
        if(gameState.length<=4){
          System.out.println("-------------");
        }
        else{
          for(int h = 0;h<(gameState.length/3);h++)
          System.out.print("-------------");
          System.out.println("");
        }
    }
    System.out.println();
  }

  public MCTSNode getRNDChild(){
    Random r = new Random();
    return this.getChildren(r.nextInt(this.childrenNode.size()));
  }

  public boolean checkForEmptySpace(){
    for(int i = 0;i<this.gameState.length;i++){
      for(int j = 0;j<this.gameState.length;j++){
        if(gameState[i][j] == 0){
          return true;
        }
      }
    }
    return false;
  }

  public int getCurrentPlayer() {
      return currentPlayer;
  }

  public void changePlayer() {
      if (currentPlayer == 2) {
          currentPlayer = 1;
      }
      else {
          currentPlayer = 2;
      }
  }

  public boolean checkWinPlayer1(){
    //checking Every direction to see if there is a win.
    int[][] directions = {{1,0}, {1,-1}, {1,1}, {0,1}};
    ArrayList<Integer> winningLine = new ArrayList<>();
    for (int[] d : directions) {
        int di = d[0];
        int dj = d[1];
        for (int i = 0; i < this.gameState[0].length; i++) {
            for (int j = 0; j < this.gameState[1].length; j++) {
                int maxXDimensions = i + 4*di;
                int maxYDimensions = j + 4*dj;
                if (0 <= maxXDimensions && maxXDimensions < this.gameState[0].length
                && 0 <= maxYDimensions && maxYDimensions < this.gameState[1].length) {
                    winningLine.add(this.gameState[i][j]);
                    winningLine.add(this.gameState[i+di][j+dj]);
                    winningLine.add(this.gameState[i+2*di][j+2*dj]);
                    winningLine.add(this.gameState[i+3*di][j+3*dj]);
                    winningLine.add(this.gameState[maxXDimensions][maxYDimensions]);
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
        for (int i = 0; i < this.gameState[0].length; i++) {
            for (int j = 0; j < this.gameState[1].length; j++) {
                int maxXDimensions = i + 4*di;
                int maxYDimensions = j + 4*dj;
                if (0 <= maxXDimensions && maxXDimensions < this.gameState[0].length
                && 0 <= maxYDimensions && maxYDimensions < this.gameState[1].length) {
                  winningLine.add(this.gameState[i][j]);
                  winningLine.add(this.gameState[i+di][j+dj]);
                  winningLine.add(this.gameState[i+2*di][j+2*dj]);
                  winningLine.add(this.gameState[i+3*di][j+3*dj]);
                  winningLine.add(this.gameState[maxXDimensions][maxYDimensions]);
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

  public int getVisitCount(){
    return visitCount;
  }

  public void printNodeInfo(){
    System.out.println("Win Count: " + this.getWinCount());
    System.out.println("Visit Count: "+ this.getVisitCount());
    System.out.println("Number of children: "+this.childrenNode.size());
  }


  public boolean emptyChildren(){
    if(this.childrenNode.size() > 0){
      return false;
    }
    return true;
  }

  public Point getSimulatedMove(BoardGame Board, int currentPlayerMark){
    int[][] tempBoard = new int[Board.getBoardXDimensions()][Board.getBoardYDimensions()];
    int[][] tempBoardTemp = new int[Board.getBoardXDimensions()][Board.getBoardYDimensions()];;
    int currentTempPlayerMark = currentPlayerMark;
    for(int i = 0;i<tempBoard[0].length;i++){
      for(int j = 0;j<tempBoard[0].length;j++){
        tempBoard[i][j] = Board.getBoardToken(i,j);
      }
    }
    for(int i = 0;i<tempBoard[0].length;i++){
      for(int j = 0;j<tempBoard[0].length;j++){
      }
    }
    int[][] directions = {{1,0}, {1,-1}, {1,1}, {0,1}};
    for (int[] d : directions) {
        int di = d[0];
        int dj = d[1];
        for (int i = 0; i < tempBoard[0].length; i++) {
            for (int j = 0; j < tempBoard[1].length; j++) {
              int maxXDimensions = i + 4*di;
              int maxYDimensions = j + 4*dj;
              if (0 <= maxXDimensions && maxXDimensions < tempBoard[0].length
              && 0 <= maxYDimensions && maxYDimensions < tempBoard[1].length) {
                if(currentTempPlayerMark==2){
                  if(tempBoard[i][j]==0 &&
                  tempBoard[i+di][j+dj]==1 &&
                  tempBoard[i+2*di][j+2*dj]==1 &&
                  tempBoard[i+3*di][j+3*dj]==1 &&
                  tempBoard[maxXDimensions][maxYDimensions]==1){
                    tempBoardTemp[i][j] += 10000;
                  }
                  if(tempBoard[i][j]==1 &&
                  tempBoard[i+di][j+dj]==0 &&
                  tempBoard[i+2*di][j+2*dj]==1 &&
                  tempBoard[i+3*di][j+3*dj]==1 &&
                  tempBoard[maxXDimensions][maxYDimensions]==1){
                    tempBoardTemp[i+di][j+dj] += 10000;
                  }
                  if(tempBoard[i][j]==1 &&
                  tempBoard[i+di][j+dj]==1 &&
                  tempBoard[i+2*di][j+2*dj]==0 &&
                  tempBoard[i+3*di][j+3*dj]==1 &&
                  tempBoard[maxXDimensions][maxYDimensions]==1){
                    tempBoardTemp[i+2*di][j+2*dj] += 10000;
                  }
                  if(tempBoard[i][j]==1 &&
                  tempBoard[i+di][j+dj]==1 &&
                  tempBoard[i+2*di][j+2*dj]==1 &&
                  tempBoard[i+3*di][j+3*dj]==0 &&
                  tempBoard[maxXDimensions][maxYDimensions]==1){
                    tempBoardTemp[+3*di][j+3*dj] += 10000;
                  }
                  if(tempBoard[i][j]==1 &&
                  tempBoard[i+di][j+dj]==1 &&
                  tempBoard[i+2*di][j+2*dj]==1 &&
                  tempBoard[i+3*di][j+3*dj]==1 &&
                  tempBoard[maxXDimensions][maxYDimensions]==0){
                    tempBoardTemp[maxXDimensions][maxYDimensions] += 10000;
                  }
                  if(tempBoard[i][j]==0 &&
                  tempBoard[i+di][j+dj]==1 &&
                  tempBoard[i+2*di][j+2*dj]==1 &&
                  tempBoard[i+3*di][j+3*dj]==1){
                    tempBoardTemp[i][j] += 500;
                  }
                  if(tempBoard[i][j]==1 &&
                  tempBoard[i+di][j+dj]==0 &&
                  tempBoard[i+2*di][j+2*dj]==1 &&
                  tempBoard[i+3*di][j+3*dj]==1){
                    tempBoardTemp[i+di][j+dj] += 500;
                  }
                  if(tempBoard[i][j]==1 &&
                  tempBoard[i+di][j+dj]==1 &&
                  tempBoard[i+2*di][j+2*dj]==0 &&
                  tempBoard[i+3*di][j+3*dj]==1){
                    tempBoardTemp[i+2*di][j+2*dj] += 500;
                  }
                  if(tempBoard[i][j]==1 &&
                  tempBoard[i+di][j+dj]==1 &&
                  tempBoard[i+2*di][j+2*dj]==1 &&
                  tempBoard[i+3*di][j+3*dj]==0){
                    tempBoardTemp[i+3*di][j+3*dj] += 500;
                  }
                  if(tempBoard[i][j]==0 &&
                  tempBoard[i+di][j+dj]==1 &&
                  tempBoard[i+2*di][j+2*dj]==1){
                    tempBoardTemp[i][j] += 250;
                  }
                  if(tempBoard[i][j]==1 &&
                  tempBoard[i+di][j+dj]==0 &&
                  tempBoard[i+2*di][j+2*dj]==1){
                    tempBoardTemp[i+di][j+dj] += 250;
                  }
                  if(tempBoard[i][j]==1 &&
                  tempBoard[i+di][j+dj]==1 &&
                  tempBoard[i+2*di][j+2*dj]==0){
                    tempBoardTemp[i+2*di][j+2*dj] += 250;
                  }

                  if(tempBoard[i][j]==0 &&
                  tempBoard[i+di][j+dj]==2 &&
                  tempBoard[i+2*di][j+2*dj]==2 &&
                  tempBoard[i+3*di][j+3*dj]==2 &&
                  tempBoard[maxXDimensions][maxYDimensions]==2){
                    tempBoardTemp[i][j] += 10000;
                  }
                  if(tempBoard[i][j]==2 &&
                  tempBoard[i+di][j+dj]==2 &&
                  tempBoard[i+2*di][j+2*dj]==2 &&
                  tempBoard[i+3*di][j+3*dj]==2 &&
                  tempBoard[maxXDimensions][maxYDimensions]==2){
                    tempBoardTemp[i+di][j+dj] += 10000;
                  }
                  if(tempBoard[i][j]==2 &&
                  tempBoard[i+di][j+dj]==2 &&
                  tempBoard[i+2*di][j+2*dj]==0 &&
                  tempBoard[i+3*di][j+3*dj]==2 &&
                  tempBoard[maxXDimensions][maxYDimensions]==2){
                    tempBoardTemp[i+2*di][j+2*dj] += 10000;
                  }
                  if(tempBoard[i][j]==2 &&
                  tempBoard[i+di][j+dj]==2 &&
                  tempBoard[i+2*di][j+2*dj]==2 &&
                  tempBoard[i+3*di][j+3*dj]==0 &&
                  tempBoard[maxXDimensions][maxYDimensions]==2){
                    tempBoardTemp[+2*di][j+2*dj] += 10000;
                  }
                  if(tempBoard[i][j]==2 &&
                  tempBoard[i+di][j+dj]==2 &&
                  tempBoard[i+2*di][j+2*dj]==2 &&
                  tempBoard[i+3*di][j+3*dj]==2 &&
                  tempBoard[maxXDimensions][maxYDimensions]==0){
                    tempBoardTemp[maxXDimensions][maxYDimensions] += 10000;
                  }
                  if(tempBoard[i][j]==0 &&
                  tempBoard[i+di][j+dj]==2 &&
                  tempBoard[i+2*di][j+2*dj]==2 &&
                  tempBoard[i+3*di][j+3*dj]==2){
                    tempBoardTemp[i][j] += 100;
                  }
                  if(tempBoard[i][j]==2 &&
                  tempBoard[i+di][j+dj]==0 &&
                  tempBoard[i+2*di][j+2*dj]==2 &&
                  tempBoard[i+3*di][j+3*dj]==2){
                    tempBoardTemp[i+di][j+dj] += 100;
                  }
                  if(tempBoard[i][j]==2 &&
                  tempBoard[i+di][j+dj]==2 &&
                  tempBoard[i+2*di][j+2*dj]==0 &&
                  tempBoard[i+3*di][j+3*dj]==2){
                    tempBoardTemp[i+2*di][j+2*dj] += 100;
                  }
                  if(tempBoard[i][j]==2 &&
                  tempBoard[i+di][j+dj]==2 &&
                  tempBoard[i+2*di][j+2*dj]==2 &&
                  tempBoard[i+3*di][j+3*dj]==0){
                    tempBoardTemp[i+3*di][j+3*dj] += 100;
                  }
                  if(tempBoard[i][j]==0 &&
                  tempBoard[i+di][j+dj]==2 &&
                  tempBoard[i+2*di][j+2*dj]==2){
                    tempBoardTemp[i][j] += 50;
                  }
                  if(tempBoard[i][j]==2 &&
                  tempBoard[i+di][j+dj]==0 &&
                  tempBoard[i+2*di][j+2*dj]==2){
                    tempBoardTemp[i+di][j+dj] += 50;
                  }
                  if(tempBoard[i][j]==2 &&
                  tempBoard[i+di][j+dj]==2 &&
                  tempBoard[i+2*di][j+2*dj]==0){
                    tempBoardTemp[i+2*di][j+2*dj] += 50;
                  }
                  if(tempBoard[i][j]==0 &&
                  tempBoard[i+di][j+dj]==2){
                    tempBoardTemp[i][j] += 25;
                  }
                  if(tempBoard[i][j]==2 &&
                  tempBoard[i+di][j+dj]==0){
                    tempBoardTemp[i+di][j+dj] += 25;
                  }
                }
                else{
                  if(tempBoard[i][j]==0 &&
                  tempBoard[i+di][j+dj]==1 &&
                  tempBoard[i+2*di][j+2*dj]==1 &&
                  tempBoard[i+3*di][j+3*dj]==1 &&
                  tempBoard[maxXDimensions][maxYDimensions]==1){
                    tempBoardTemp[i][j] += 10000;
                  }
                  if(tempBoard[i][j]==1 &&
                  tempBoard[i+di][j+dj]==0 &&
                  tempBoard[i+2*di][j+2*dj]==1 &&
                  tempBoard[i+3*di][j+3*dj]==1 &&
                  tempBoard[maxXDimensions][maxYDimensions]==1){
                    tempBoardTemp[i+di][j+dj] += 10000;
                  }
                  if(tempBoard[i][j]==1 &&
                  tempBoard[i+di][j+dj]==1 &&
                  tempBoard[i+2*di][j+2*dj]==0 &&
                  tempBoard[i+3*di][j+3*dj]==1 &&
                  tempBoard[maxXDimensions][maxYDimensions]==1){
                    tempBoardTemp[i+2*di][j+2*dj] += 10000;
                  }
                  if(tempBoard[i][j]==1 &&
                  tempBoard[i+di][j+dj]==1 &&
                  tempBoard[i+2*di][j+2*dj]==1 &&
                  tempBoard[i+3*di][j+3*dj]==0 &&
                  tempBoard[maxXDimensions][maxYDimensions]==1){
                    tempBoardTemp[+3*di][j+3*dj] += 10000;
                  }
                  if(tempBoard[i][j]==1 &&
                  tempBoard[i+di][j+dj]==1 &&
                  tempBoard[i+2*di][j+2*dj]==1 &&
                  tempBoard[i+3*di][j+3*dj]==1 &&
                  tempBoard[maxXDimensions][maxYDimensions]==0){
                    tempBoardTemp[maxXDimensions][maxYDimensions] += 10000;
                  }
                  if(tempBoard[i][j]==0 &&
                  tempBoard[i+di][j+dj]==1 &&
                  tempBoard[i+2*di][j+2*dj]==1 &&
                  tempBoard[i+3*di][j+3*dj]==1){
                    tempBoardTemp[i][j] += 500;
                  }
                  if(tempBoard[i][j]==1 &&
                  tempBoard[i+di][j+dj]==0 &&
                  tempBoard[i+2*di][j+2*dj]==1 &&
                  tempBoard[i+3*di][j+3*dj]==1){
                    tempBoardTemp[i+di][j+dj] += 500;
                  }
                  if(tempBoard[i][j]==1 &&
                  tempBoard[i+di][j+dj]==1 &&
                  tempBoard[i+2*di][j+2*dj]==0 &&
                  tempBoard[i+3*di][j+3*dj]==1){
                    tempBoardTemp[i+2*di][j+2*dj] += 500;
                  }
                  if(tempBoard[i][j]==1 &&
                  tempBoard[i+di][j+dj]==1 &&
                  tempBoard[i+2*di][j+2*dj]==1 &&
                  tempBoard[i+3*di][j+3*dj]==0){
                    tempBoardTemp[i+3*di][j+3*dj] += 500;
                  }
                  if(tempBoard[i][j]==0 &&
                  tempBoard[i+di][j+dj]==1 &&
                  tempBoard[i+2*di][j+2*dj]==1){
                    tempBoardTemp[i][j] += 250;
                  }
                  if(tempBoard[i][j]==1 &&
                  tempBoard[i+di][j+dj]==0 &&
                  tempBoard[i+2*di][j+2*dj]==1){
                    tempBoardTemp[i+di][j+dj] += 250;
                  }
                  if(tempBoard[i][j]==1 &&
                  tempBoard[i+di][j+dj]==1 &&
                  tempBoard[i+2*di][j+2*dj]==0){
                    tempBoardTemp[i+2*di][j+2*dj] += 250;
                  }

                  if(tempBoard[i][j]==0 &&
                  tempBoard[i+di][j+dj]==2 &&
                  tempBoard[i+2*di][j+2*dj]==2 &&
                  tempBoard[i+3*di][j+3*dj]==2 &&
                  tempBoard[maxXDimensions][maxYDimensions]==2){
                    tempBoardTemp[i][j] += 10000;
                  }
                  if(tempBoard[i][j]==2 &&
                  tempBoard[i+di][j+dj]==2 &&
                  tempBoard[i+2*di][j+2*dj]==2 &&
                  tempBoard[i+3*di][j+3*dj]==2 &&
                  tempBoard[maxXDimensions][maxYDimensions]==2){
                    tempBoardTemp[i+di][j+dj] += 10000;
                  }
                  if(tempBoard[i][j]==2 &&
                  tempBoard[i+di][j+dj]==2 &&
                  tempBoard[i+2*di][j+2*dj]==0 &&
                  tempBoard[i+3*di][j+3*dj]==2 &&
                  tempBoard[maxXDimensions][maxYDimensions]==2){
                    tempBoardTemp[i+2*di][j+2*dj] += 10000;
                  }
                  if(tempBoard[i][j]==2 &&
                  tempBoard[i+di][j+dj]==2 &&
                  tempBoard[i+2*di][j+2*dj]==2 &&
                  tempBoard[i+3*di][j+3*dj]==0 &&
                  tempBoard[maxXDimensions][maxYDimensions]==2){
                    tempBoardTemp[+2*di][j+2*dj] += 10000;
                  }
                  if(tempBoard[i][j]==2 &&
                  tempBoard[i+di][j+dj]==2 &&
                  tempBoard[i+2*di][j+2*dj]==2 &&
                  tempBoard[i+3*di][j+3*dj]==2 &&
                  tempBoard[maxXDimensions][maxYDimensions]==0){
                    tempBoardTemp[maxXDimensions][maxYDimensions] += 10000;
                  }
                  if(tempBoard[i][j]==0 &&
                  tempBoard[i+di][j+dj]==2 &&
                  tempBoard[i+2*di][j+2*dj]==2 &&
                  tempBoard[i+3*di][j+3*dj]==2){
                    tempBoardTemp[i][j] += 100;
                  }
                  if(tempBoard[i][j]==2 &&
                  tempBoard[i+di][j+dj]==0 &&
                  tempBoard[i+2*di][j+2*dj]==2 &&
                  tempBoard[i+3*di][j+3*dj]==2){
                    tempBoardTemp[i+di][j+dj] += 100;
                  }
                  if(tempBoard[i][j]==2 &&
                  tempBoard[i+di][j+dj]==2 &&
                  tempBoard[i+2*di][j+2*dj]==0 &&
                  tempBoard[i+3*di][j+3*dj]==2){
                    tempBoardTemp[i+2*di][j+2*dj] += 100;
                  }
                  if(tempBoard[i][j]==2 &&
                  tempBoard[i+di][j+dj]==2 &&
                  tempBoard[i+2*di][j+2*dj]==2 &&
                  tempBoard[i+3*di][j+3*dj]==0){
                    tempBoardTemp[i+3*di][j+3*dj] += 100;
                  }
                  if(tempBoard[i][j]==0 &&
                  tempBoard[i+di][j+dj]==2 &&
                  tempBoard[i+2*di][j+2*dj]==2){
                    tempBoardTemp[i][j] += 50;
                  }
                  if(tempBoard[i][j]==2 &&
                  tempBoard[i+di][j+dj]==0 &&
                  tempBoard[i+2*di][j+2*dj]==2){
                    tempBoardTemp[i+di][j+dj] += 50;
                  }
                  if(tempBoard[i][j]==2 &&
                  tempBoard[i+di][j+dj]==2 &&
                  tempBoard[i+2*di][j+2*dj]==0){
                    tempBoardTemp[i+2*di][j+2*dj] += 50;
                  }
                  if(tempBoard[i][j]==0 &&
                  tempBoard[i+di][j+dj]==2){
                    tempBoardTemp[i][j] += 25;
                  }
                  if(tempBoard[i][j]==2 &&
                  tempBoard[i+di][j+dj]==0){
                    tempBoardTemp[i+di][j+dj] += 25;
                  }
                }
              }
            }
          }
        }
        ArrayList<Integer> MoveScores = new ArrayList<Integer>();
        for(int i = 0;i<tempBoardTemp[0].length;i++){
          for(int j = 0;j<tempBoardTemp[1].length;j++){
            MoveScores.add(tempBoardTemp[i][j]);
          }
        }
        ArrayList<Point> possibleBestMoves = new ArrayList<Point>();
        for(int i = 0;i<tempBoardTemp[0].length;i++){
          for(int j = 0;j<tempBoardTemp[1].length;j++){
            if(Collections.max(MoveScores)==tempBoardTemp[i][j]){
              Point possMove = new Point(i,j);
              possibleBestMoves.add(possMove);
            }
          }
        }
      Random r = new Random();
      Point selectedMove = possibleBestMoves.get(r.nextInt(possibleBestMoves.size()));
      return selectedMove;
      }
}
