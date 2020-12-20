public class MctsPlayer {

  private static int maxIterations;

  public MctsPlayer(int i){
    maxIterations = i;
  }


  public static String getBestMove(BoardGame game){
    long startTime = System.nanoTime();
    MCTSNode root = new MCTSNode(game);
    root.getBoardState(game);
    root.setPossibleMoves();
    for(int iteration = 0; iteration < maxIterations; iteration++){
      MCTSNode initialNode = selectInitialNode(root);
      if(initialNode.getPossibleMovesList()>0){
        initialNode.Expand(game);
      }
      MCTSNode nodeSelected = initialNode;
      if(nodeSelected.childrenLeft() == true){
        nodeSelected = initialNode.getRNDChild();
      }
      nodeSelected.Simulate();
    }
    MCTSNode best = root.getMostVisitNode();
    long endTime = System.nanoTime();
    long totalTime = endTime - startTime;
    double timeSeconds = (double)totalTime / 1_000_000_000.0;
    double timeMinutes = timeSeconds/60;
    //best.printNode();
    //best.printNodeInfo();
    //System.out.println("The best move according to me is this: "+best.getMoveMadeRow()+","+best.getMoveMadeCol());
    //System.out.println("End of the process to search for best move");
    if(best.getMoveMadeRow() == 0 && best.getMoveMadeCol() == 0){
      //game.placeMark("A1",2);
      return "A1";
    }
    else if(best.getMoveMadeRow() == 0 && best.getMoveMadeCol() == 1){
      //game.placeMark("B1",2);
      return "B1";
    }
    else if(best.getMoveMadeRow() == 0 && best.getMoveMadeCol() == 2){
      //game.placeMark("C1",2);
      return "C1";
    }
    else if(best.getMoveMadeRow() == 0 && best.getMoveMadeCol() == 3){
      //game.placeMark("C1",2);
      return "D1";
    }
    else if(best.getMoveMadeRow() == 0 && best.getMoveMadeCol() == 4){
      //game.placeMark("C1",2);
      return "E1";
    }
    else if(best.getMoveMadeRow() == 0 && best.getMoveMadeCol() == 5){
      //game.placeMark("C1",2);
      return "F1";
    }
    else if(best.getMoveMadeRow() == 0 && best.getMoveMadeCol() == 6){
      //game.placeMark("C1",2);
      return "G1";
    }
    else if(best.getMoveMadeRow() == 0 && best.getMoveMadeCol() == 7){
      //game.placeMark("C1",2);
      return "H1";
    }
    else if(best.getMoveMadeRow() == 0 && best.getMoveMadeCol() == 8){
      //game.placeMark("C1",2);
      return "I1";
    }
    else if(best.getMoveMadeRow() == 0 && best.getMoveMadeCol() == 9){
      //game.placeMark("C1",2);
      return "J1";
    }
    else if(best.getMoveMadeRow() == 0 && best.getMoveMadeCol() == 10){
      //game.placeMark("C1",2);
      return "K1";
    }
    else if(best.getMoveMadeRow() == 0 && best.getMoveMadeCol() == 11){
      //game.placeMark("C1",2);
      return "L1";
    }
    else if(best.getMoveMadeRow() == 0 && best.getMoveMadeCol() == 12){
      //game.placeMark("C1",2);
      return "M1";
    }
    else if(best.getMoveMadeRow() == 0 && best.getMoveMadeCol() == 13){
      //game.placeMark("C1",2);
      return "N1";
    }
    else if(best.getMoveMadeRow() == 0 && best.getMoveMadeCol() == 14){
      //game.placeMark("C1",2);
      return "O1";
    }


    else if(best.getMoveMadeRow() == 1 && best.getMoveMadeCol() == 0){
      //game.placeMark("A2",2);
      return "A2";
    }
    else if(best.getMoveMadeRow() == 1 && best.getMoveMadeCol() == 1){
      //game.placeMark("B2",2);
      return "B2";
    }
    else if(best.getMoveMadeRow() == 1 && best.getMoveMadeCol() == 2){
      //game.placeMark("C2",2);
      return "C2";
    }
    else if(best.getMoveMadeRow() == 1 && best.getMoveMadeCol() == 3){
      //game.placeMark("C2",2);
      return "D2";
    }
    else if(best.getMoveMadeRow() == 1 && best.getMoveMadeCol() == 4){
      //game.placeMark("C2",2);
      return "E2";
    }
    else if(best.getMoveMadeRow() == 1 && best.getMoveMadeCol() == 5){
      //game.placeMark("C2",2);
      return "F2";
    }
    else if(best.getMoveMadeRow() == 1 && best.getMoveMadeCol() == 6){
      //game.placeMark("C2",2);
      return "G2";
    }
    else if(best.getMoveMadeRow() == 1 && best.getMoveMadeCol() == 7){
      //game.placeMark("C2",2);
      return "H2";
    }
    else if(best.getMoveMadeRow() == 1 && best.getMoveMadeCol() == 8){
      //game.placeMark("C2",2);
      return "I2";
    }
    else if(best.getMoveMadeRow() == 1 && best.getMoveMadeCol() == 9){
      //game.placeMark("C2",2);
      return "J2";
    }
    else if(best.getMoveMadeRow() == 1 && best.getMoveMadeCol() == 10){
      //game.placeMark("C2",2);
      return "K2";
    }
    else if(best.getMoveMadeRow() == 1 && best.getMoveMadeCol() == 11){
      //game.placeMark("C2",2);
      return "L2";
    }
    else if(best.getMoveMadeRow() == 1 && best.getMoveMadeCol() == 12){
      //game.placeMark("C2",2);
      return "M2";
    }
    else if(best.getMoveMadeRow() == 1 && best.getMoveMadeCol() == 13){
      //game.placeMark("C2",2);
      return "N2";
    }
    else if(best.getMoveMadeRow() == 1 && best.getMoveMadeCol() == 14){
      //game.placeMark("C2",2);
      return "O2";
    }

    else if(best.getMoveMadeRow() == 2 && best.getMoveMadeCol() == 0){
      //game.placeMark("A3",2);
      return "A3";
    }
    else if(best.getMoveMadeRow() == 2 && best.getMoveMadeCol() == 1){
      //game.placeMark("B3",2);
      return "B3";
    }
    else if(best.getMoveMadeRow() == 2 && best.getMoveMadeCol() == 2){
      //game.placeMark("C3",2);
      return "C3";
    }
    else if(best.getMoveMadeRow() == 2 && best.getMoveMadeCol() == 3){
      //game.placeMark("C3",2);
      return "D3";
    }
    else if(best.getMoveMadeRow() == 2 && best.getMoveMadeCol() == 4){
      //game.placeMark("C3",2);
      return "E3";
    }
    else if(best.getMoveMadeRow() == 2 && best.getMoveMadeCol() == 5){
      //game.placeMark("C3",2);
      return "F3";
    }
    else if(best.getMoveMadeRow() == 2 && best.getMoveMadeCol() == 6){
      //game.placeMark("C3",2);
      return "G3";
    }
    else if(best.getMoveMadeRow() == 2 && best.getMoveMadeCol() == 7){
      //game.placeMark("C3",2);
      return "H3";
    }
    else if(best.getMoveMadeRow() == 2 && best.getMoveMadeCol() == 8){
      //game.placeMark("C3",2);
      return "I3";
    }
    else if(best.getMoveMadeRow() == 2 && best.getMoveMadeCol() == 9){
      //game.placeMark("C3",2);
      return "J3";
    }
    else if(best.getMoveMadeRow() == 2 && best.getMoveMadeCol() == 10){
      //game.placeMark("C3",2);
      return "K3";
    }
    else if(best.getMoveMadeRow() == 2 && best.getMoveMadeCol() == 11){
      //game.placeMark("C3",2);
      return "L3";
    }
    else if(best.getMoveMadeRow() == 2 && best.getMoveMadeCol() == 12){
      //game.placeMark("C3",2);
      return "M3";
    }
    else if(best.getMoveMadeRow() == 2 && best.getMoveMadeCol() == 13){
      //game.placeMark("C3",2);
      return "N3";
    }
    else if(best.getMoveMadeRow() == 2 && best.getMoveMadeCol() == 14){
      //game.placeMark("C3",2);
      return "O3";
    }

    else if(best.getMoveMadeRow() == 3 && best.getMoveMadeCol() == 0){
      //game.placeMark("C3",2);
      return "A4";
    }
    else if(best.getMoveMadeRow() == 3 && best.getMoveMadeCol() == 1){
      //game.placeMark("C3",2);
      return "B4";
    }
    else if(best.getMoveMadeRow() == 3 && best.getMoveMadeCol() == 2){
      //game.placeMark("C3",2);
      return "C4";
    }
    else if(best.getMoveMadeRow() == 3 && best.getMoveMadeCol() == 3){
      //game.placeMark("C3",2);
      return "D4";
    }
    else if(best.getMoveMadeRow() == 3 && best.getMoveMadeCol() == 4){
      //game.placeMark("C3",2);
      return "E4";
    }
    else if(best.getMoveMadeRow() == 3 && best.getMoveMadeCol() == 5){
      //game.placeMark("C3",2);
      return "F4";
    }
    else if(best.getMoveMadeRow() == 3 && best.getMoveMadeCol() == 6){
      //game.placeMark("C3",2);
      return "G4";
    }
    else if(best.getMoveMadeRow() == 3 && best.getMoveMadeCol() == 7){
      //game.placeMark("C3",2);
      return "H4";
    }
    else if(best.getMoveMadeRow() == 3 && best.getMoveMadeCol() == 8){
      //game.placeMark("C3",2);
      return "I4";
    }
    else if(best.getMoveMadeRow() == 3 && best.getMoveMadeCol() == 9){
      //game.placeMark("C3",2);
      return "J4";
    }
    else if(best.getMoveMadeRow() == 3 && best.getMoveMadeCol() == 10){
      //game.placeMark("C3",2);
      return "K4";
    }
    else if(best.getMoveMadeRow() == 3 && best.getMoveMadeCol() == 11){
      //game.placeMark("C3",2);
      return "L4";
    }
    else if(best.getMoveMadeRow() == 3 && best.getMoveMadeCol() == 12){
      //game.placeMark("C3",2);
      return "M4";
    }
    else if(best.getMoveMadeRow() == 3 && best.getMoveMadeCol() == 13){
      //game.placeMark("C3",2);
      return "N4";
    }
    else if(best.getMoveMadeRow() == 3 && best.getMoveMadeCol() == 14){
      //game.placeMark("C3",2);
      return "O4";
    }

    else if(best.getMoveMadeRow() == 4 && best.getMoveMadeCol() == 0){
      //game.placeMark("C3",2);
      return "A5";
    }
    else if(best.getMoveMadeRow() == 4 && best.getMoveMadeCol() == 1){
      //game.placeMark("C3",2);
      return "B5";
    }
    else if(best.getMoveMadeRow() == 4 && best.getMoveMadeCol() == 2){
      //game.placeMark("C3",2);
      return "C5";
    }
    else if(best.getMoveMadeRow() == 4 && best.getMoveMadeCol() == 3){
      //game.placeMark("C3",2);
      return "D5";
    }
    else if(best.getMoveMadeRow() == 4 && best.getMoveMadeCol() == 4){
      //game.placeMark("C3",2);
      return "E5";
    }
    else if(best.getMoveMadeRow() == 4 && best.getMoveMadeCol() == 5){
      //game.placeMark("C3",2);
      return "F5";
    }
    else if(best.getMoveMadeRow() == 4 && best.getMoveMadeCol() == 6){
      //game.placeMark("C3",2);
      return "G5";
    }
    else if(best.getMoveMadeRow() == 4 && best.getMoveMadeCol() == 7){
      //game.placeMark("C3",2);
      return "H5";
    }
    else if(best.getMoveMadeRow() == 4 && best.getMoveMadeCol() == 8){
      //game.placeMark("C3",2);
      return "I5";
    }
    else if(best.getMoveMadeRow() == 4 && best.getMoveMadeCol() == 9){
      //game.placeMark("C3",2);
      return "J5";
    }
    else if(best.getMoveMadeRow() == 4 && best.getMoveMadeCol() == 10){
      //game.placeMark("C3",2);
      return "K5";
    }
    else if(best.getMoveMadeRow() == 4 && best.getMoveMadeCol() == 11){
      //game.placeMark("C3",2);
      return "L5";
    }
    else if(best.getMoveMadeRow() == 4 && best.getMoveMadeCol() == 12){
      //game.placeMark("C3",2);
      return "M5";
    }
    else if(best.getMoveMadeRow() == 4 && best.getMoveMadeCol() == 13){
      //game.placeMark("C3",2);
      return "N5";
    }
    else if(best.getMoveMadeRow() == 4 && best.getMoveMadeCol() == 13){
      //game.placeMark("C3",2);
      return "O5";
    }

    else if(best.getMoveMadeRow() == 5 && best.getMoveMadeCol() == 0){
      //game.placeMark("C3",2);
      return "A6";
    }
    else if(best.getMoveMadeRow() == 5 && best.getMoveMadeCol() == 1){
      //game.placeMark("C3",2);
      return "B6";
    }
    else if(best.getMoveMadeRow() == 5 && best.getMoveMadeCol() == 2){
      //game.placeMark("C3",2);
      return "C6";
    }
    else if(best.getMoveMadeRow() == 5 && best.getMoveMadeCol() == 3){
      //game.placeMark("C3",2);
      return "D6";
    }
    else if(best.getMoveMadeRow() == 5 && best.getMoveMadeCol() == 4){
      //game.placeMark("C3",2);
      return "E6";
    }
    else if(best.getMoveMadeRow() == 5 && best.getMoveMadeCol() == 5){
      //game.placeMark("C3",2);
      return "F6";
    }
    else if(best.getMoveMadeRow() == 5 && best.getMoveMadeCol() == 6){
      //game.placeMark("C3",2);
      return "G6";
    }
    else if(best.getMoveMadeRow() == 5 && best.getMoveMadeCol() == 7){
      //game.placeMark("C3",2);
      return "H6";
    }
    else if(best.getMoveMadeRow() == 5 && best.getMoveMadeCol() == 8){
      //game.placeMark("C3",2);
      return "I6";
    }
    else if(best.getMoveMadeRow() == 5 && best.getMoveMadeCol() == 9){
      //game.placeMark("C3",2);
      return "J6";
    }
    else if(best.getMoveMadeRow() == 5 && best.getMoveMadeCol() == 10){
      //game.placeMark("C3",2);
      return "K6";
    }
    else if(best.getMoveMadeRow() == 5 && best.getMoveMadeCol() == 11){
      //game.placeMark("C3",2);
      return "L6";
    }
    else if(best.getMoveMadeRow() == 5 && best.getMoveMadeCol() == 12){
      //game.placeMark("C3",2);
      return "M6";
    }
    else if(best.getMoveMadeRow() == 5 && best.getMoveMadeCol() == 13){
      //game.placeMark("C3",2);
      return "N6";
    }
    else if(best.getMoveMadeRow() == 5 && best.getMoveMadeCol() == 14){
      //game.placeMark("C3",2);
      return "O6";
    }

    else if(best.getMoveMadeRow() == 6 && best.getMoveMadeCol() == 0){
      //game.placeMark("C3",2);
      return "A7";
    }
    else if(best.getMoveMadeRow() == 6 && best.getMoveMadeCol() == 1){
      //game.placeMark("C3",2);
      return "B7";
    }
    else if(best.getMoveMadeRow() == 6 && best.getMoveMadeCol() == 2){
      //game.placeMark("C3",2);
      return "C7";
    }
    else if(best.getMoveMadeRow() == 6 && best.getMoveMadeCol() == 3){
      //game.placeMark("C3",2);
      return "D7";
    }
    else if(best.getMoveMadeRow() == 6 && best.getMoveMadeCol() == 4){
      //game.placeMark("C3",2);
      return "E7";
    }
    else if(best.getMoveMadeRow() == 6 && best.getMoveMadeCol() == 5){
      //game.placeMark("C3",2);
      return "F7";
    }
    else if(best.getMoveMadeRow() == 6 && best.getMoveMadeCol() == 6){
      //game.placeMark("C3",2);
      return "G7";
    }
    else if(best.getMoveMadeRow() == 6 && best.getMoveMadeCol() == 7){
      //game.placeMark("C3",2);
      return "H7";
    }
    else if(best.getMoveMadeRow() == 6 && best.getMoveMadeCol() == 8){
      //game.placeMark("C3",2);
      return "I7";
    }
    else if(best.getMoveMadeRow() == 6 && best.getMoveMadeCol() == 9){
      //game.placeMark("C3",2);
      return "J7";
    }
    else if(best.getMoveMadeRow() == 6 && best.getMoveMadeCol() == 10){
      //game.placeMark("C3",2);
      return "K7";
    }
    else if(best.getMoveMadeRow() == 6 && best.getMoveMadeCol() == 11){
      //game.placeMark("C3",2);
      return "L7";
    }
    else if(best.getMoveMadeRow() == 6 && best.getMoveMadeCol() == 12){
      //game.placeMark("C3",2);
      return "M7";
    }
    else if(best.getMoveMadeRow() == 6 && best.getMoveMadeCol() == 13){
      //game.placeMark("C3",2);
      return "N7";
    }
    else if(best.getMoveMadeRow() == 6 && best.getMoveMadeCol() == 14){
      //game.placeMark("C3",2);
      return "O7";
    }

    else if(best.getMoveMadeRow() == 7 && best.getMoveMadeCol() == 0){
      //game.placeMark("C3",2);
      return "A8";
    }
    else if(best.getMoveMadeRow() == 7 && best.getMoveMadeCol() == 1){
      //game.placeMark("C3",2);
      return "B8";
    }
    else if(best.getMoveMadeRow() == 7 && best.getMoveMadeCol() == 2){
      //game.placeMark("C3",2);
      return "C8";
    }
    else if(best.getMoveMadeRow() == 7 && best.getMoveMadeCol() == 3){
      //game.placeMark("C3",2);
      return "D8";
    }
    else if(best.getMoveMadeRow() == 7 && best.getMoveMadeCol() == 4){
      //game.placeMark("C3",2);
      return "E8";
    }
    else if(best.getMoveMadeRow() == 7 && best.getMoveMadeCol() == 5){
      //game.placeMark("C3",2);
      return "F8";
    }
    else if(best.getMoveMadeRow() == 7 && best.getMoveMadeCol() == 6){
      //game.placeMark("C3",2);
      return "G8";
    }
    else if(best.getMoveMadeRow() == 7 && best.getMoveMadeCol() == 7){
      //game.placeMark("C3",2);
      return "H8";
    }
    else if(best.getMoveMadeRow() == 7 && best.getMoveMadeCol() == 8){
      //game.placeMark("C3",2);
      return "I8";
    }
    else if(best.getMoveMadeRow() == 7 && best.getMoveMadeCol() == 9){
      //game.placeMark("C3",2);
      return "J8";
    }
    else if(best.getMoveMadeRow() == 7 && best.getMoveMadeCol() == 10){
      //game.placeMark("C3",2);
      return "K8";
    }
    else if(best.getMoveMadeRow() == 7 && best.getMoveMadeCol() == 11){
      //game.placeMark("C3",2);
      return "L8";
    }
    else if(best.getMoveMadeRow() == 7 && best.getMoveMadeCol() == 12){
      //game.placeMark("C3",2);
      return "M8";
    }
    else if(best.getMoveMadeRow() == 7 && best.getMoveMadeCol() == 13){
      //game.placeMark("C3",2);
      return "N8";
    }
    else if(best.getMoveMadeRow() == 7 && best.getMoveMadeCol() == 14){
      //game.placeMark("C3",2);
      return "O8";
    }

    else if(best.getMoveMadeRow() == 8 && best.getMoveMadeCol() == 0){
      //game.placeMark("C3",2);
      return "A9";
    }
    else if(best.getMoveMadeRow() == 8 && best.getMoveMadeCol() == 1){
      //game.placeMark("C3",2);
      return "B9";
    }
    else if(best.getMoveMadeRow() == 8 && best.getMoveMadeCol() == 2){
      //game.placeMark("C3",2);
      return "C9";
    }
    else if(best.getMoveMadeRow() == 8 && best.getMoveMadeCol() == 3){
      //game.placeMark("C3",2);
      return "D9";
    }
    else if(best.getMoveMadeRow() == 8 && best.getMoveMadeCol() == 4){
      //game.placeMark("C3",2);
      return "E9";
    }
    else if(best.getMoveMadeRow() == 8 && best.getMoveMadeCol() == 5){
      //game.placeMark("C3",2);
      return "F9";
    }
    else if(best.getMoveMadeRow() == 8 && best.getMoveMadeCol() == 6){
      //game.placeMark("C3",2);
      return "G9";
    }
    else if(best.getMoveMadeRow() == 8 && best.getMoveMadeCol() == 7){
      //game.placeMark("C3",2);
      return "H9";
    }
    else if(best.getMoveMadeRow() == 8 && best.getMoveMadeCol() == 8){
      //game.placeMark("C3",2);
      return "I9";
    }
    else if(best.getMoveMadeRow() == 8 && best.getMoveMadeCol() == 9){
      //game.placeMark("C3",2);
      return "J9";
    }
    else if(best.getMoveMadeRow() == 8 && best.getMoveMadeCol() == 10){
      //game.placeMark("C3",2);
      return "K9";
    }
    else if(best.getMoveMadeRow() == 8 && best.getMoveMadeCol() == 11){
      //game.placeMark("C3",2);
      return "L9";
    }
    else if(best.getMoveMadeRow() == 8 && best.getMoveMadeCol() == 12){
      //game.placeMark("C3",2);
      return "M9";
    }
    else if(best.getMoveMadeRow() == 8 && best.getMoveMadeCol() == 13){
      //game.placeMark("C3",2);
      return "N9";
    }
    else if(best.getMoveMadeRow() == 8 && best.getMoveMadeCol() == 14){
      //game.placeMark("C3",2);
      return "O9";
    }

    else if(best.getMoveMadeRow() == 9 && best.getMoveMadeCol() == 0){
      //game.placeMark("C3",2);
      return "A10";
    }
    else if(best.getMoveMadeRow() == 9 && best.getMoveMadeCol() == 1){
      //game.placeMark("C3",2);
      return "B10";
    }
    else if(best.getMoveMadeRow() == 9 && best.getMoveMadeCol() == 2){
      //game.placeMark("C3",2);
      return "C10";
    }
    else if(best.getMoveMadeRow() == 9 && best.getMoveMadeCol() == 3){
      //game.placeMark("C3",2);
      return "D10";
    }
    else if(best.getMoveMadeRow() == 9 && best.getMoveMadeCol() == 4){
      //game.placeMark("C3",2);
      return "E10";
    }
    else if(best.getMoveMadeRow() == 9 && best.getMoveMadeCol() == 5){
      //game.placeMark("C3",2);
      return "F10";
    }
    else if(best.getMoveMadeRow() == 9 && best.getMoveMadeCol() == 6){
      //game.placeMark("C3",2);
      return "G10";
    }
    else if(best.getMoveMadeRow() == 9 && best.getMoveMadeCol() == 7){
      //game.placeMark("C3",2);
      return "H10";
    }
    else if(best.getMoveMadeRow() == 9 && best.getMoveMadeCol() == 8){
      //game.placeMark("C3",2);
      return "I10";
    }
    else if(best.getMoveMadeRow() == 9 && best.getMoveMadeCol() == 9){
      //game.placeMark("C3",2);
      return "J10";
    }
    else if(best.getMoveMadeRow() == 9 && best.getMoveMadeCol() == 10){
      //game.placeMark("C3",2);
      return "K10";
    }
    else if(best.getMoveMadeRow() == 9 && best.getMoveMadeCol() == 11){
      //game.placeMark("C3",2);
      return "L10";
    }
    else if(best.getMoveMadeRow() == 9 && best.getMoveMadeCol() == 12){
      //game.placeMark("C3",2);
      return "M10";
    }
    else if(best.getMoveMadeRow() == 9 && best.getMoveMadeCol() == 13){
      //game.placeMark("C3",2);
      return "N10";
    }
    else if(best.getMoveMadeRow() == 9 && best.getMoveMadeCol() == 14){
      //game.placeMark("C3",2);
      return "O10";
    }

    else if(best.getMoveMadeRow() == 10 && best.getMoveMadeCol() == 0){
      //game.placeMark("C3",2);
      return "A11";
    }
    else if(best.getMoveMadeRow() == 10 && best.getMoveMadeCol() == 1){
      //game.placeMark("C3",2);
      return "B11";
    }
    else if(best.getMoveMadeRow() == 10 && best.getMoveMadeCol() == 2){
      //game.placeMark("C3",2);
      return "C11";
    }
    else if(best.getMoveMadeRow() == 10 && best.getMoveMadeCol() == 3){
      //game.placeMark("C3",2);
      return "D11";
    }
    else if(best.getMoveMadeRow() == 10 && best.getMoveMadeCol() == 4){
      //game.placeMark("C3",2);
      return "E11";
    }
    else if(best.getMoveMadeRow() == 10 && best.getMoveMadeCol() == 5){
      //game.placeMark("C3",2);
      return "F11";
    }
    else if(best.getMoveMadeRow() == 10 && best.getMoveMadeCol() == 6){
      //game.placeMark("C3",2);
      return "G11";
    }
    else if(best.getMoveMadeRow() == 10 && best.getMoveMadeCol() == 7){
      //game.placeMark("C3",2);
      return "H11";
    }
    else if(best.getMoveMadeRow() == 10 && best.getMoveMadeCol() == 8){
      //game.placeMark("C3",2);
      return "I11";
    }
    else if(best.getMoveMadeRow() == 10 && best.getMoveMadeCol() == 9){
      //game.placeMark("C3",2);
      return "J11";
    }
    else if(best.getMoveMadeRow() == 10 && best.getMoveMadeCol() == 10){
      //game.placeMark("C3",2);
      return "K11";
    }
    else if(best.getMoveMadeRow() == 10 && best.getMoveMadeCol() == 11){
      //game.placeMark("C3",2);
      return "L11";
    }
    else if(best.getMoveMadeRow() == 10 && best.getMoveMadeCol() == 12){
      //game.placeMark("C3",2);
      return "M11";
    }
    else if(best.getMoveMadeRow() == 10 && best.getMoveMadeCol() == 13){
      //game.placeMark("C3",2);
      return "N11";
    }
    else if(best.getMoveMadeRow() == 10 && best.getMoveMadeCol() == 14){
      //game.placeMark("C3",2);
      return "O11";
    }

    else if(best.getMoveMadeRow() == 11 && best.getMoveMadeCol() == 0){
      //game.placeMark("C3",2);
      return "A12";
    }
    else if(best.getMoveMadeRow() == 11 && best.getMoveMadeCol() == 1){
      //game.placeMark("C3",2);
      return "B12";
    }
    else if(best.getMoveMadeRow() == 11 && best.getMoveMadeCol() == 2){
      //game.placeMark("C3",2);
      return "C12";
    }
    else if(best.getMoveMadeRow() == 11 && best.getMoveMadeCol() == 3){
      //game.placeMark("C3",2);
      return "D12";
    }
    else if(best.getMoveMadeRow() == 11 && best.getMoveMadeCol() == 4){
      //game.placeMark("C3",2);
      return "E12";
    }
    else if(best.getMoveMadeRow() == 11 && best.getMoveMadeCol() == 5){
      //game.placeMark("C3",2);
      return "F12";
    }
    else if(best.getMoveMadeRow() == 11 && best.getMoveMadeCol() == 6){
      //game.placeMark("C3",2);
      return "G12";
    }
    else if(best.getMoveMadeRow() == 11 && best.getMoveMadeCol() == 7){
      //game.placeMark("C3",2);
      return "H12";
    }
    else if(best.getMoveMadeRow() == 11 && best.getMoveMadeCol() == 8){
      //game.placeMark("C3",2);
      return "I12";
    }
    else if(best.getMoveMadeRow() == 11 && best.getMoveMadeCol() == 9){
      //game.placeMark("C3",2);
      return "J12";
    }
    else if(best.getMoveMadeRow() == 11 && best.getMoveMadeCol() == 10){
      //game.placeMark("C3",2);
      return "K12";
    }
    else if(best.getMoveMadeRow() == 11 && best.getMoveMadeCol() == 11){
      //game.placeMark("C3",2);
      return "L12";
    }
    else if(best.getMoveMadeRow() == 11 && best.getMoveMadeCol() == 12){
      //game.placeMark("C3",2);
      return "M12";
    }
    else if(best.getMoveMadeRow() == 11 && best.getMoveMadeCol() == 13){
      //game.placeMark("C3",2);
      return "N12";
    }
    else if(best.getMoveMadeRow() == 11 && best.getMoveMadeCol() == 14){
      //game.placeMark("C3",2);
      return "O12";
    }

    else if(best.getMoveMadeRow() == 12 && best.getMoveMadeCol() == 0){
      //game.placeMark("C3",2);
      return "A13";
    }
    else if(best.getMoveMadeRow() == 12 && best.getMoveMadeCol() == 1){
      //game.placeMark("C3",2);
      return "B13";
    }
    else if(best.getMoveMadeRow() == 12 && best.getMoveMadeCol() == 2){
      //game.placeMark("C3",2);
      return "C13";
    }
    else if(best.getMoveMadeRow() == 12 && best.getMoveMadeCol() == 3){
      //game.placeMark("C3",2);
      return "D13";
    }
    else if(best.getMoveMadeRow() == 12 && best.getMoveMadeCol() == 4){
      //game.placeMark("C3",2);
      return "E13";
    }
    else if(best.getMoveMadeRow() == 12 && best.getMoveMadeCol() == 5){
      //game.placeMark("C3",2);
      return "F13";
    }
    else if(best.getMoveMadeRow() == 12 && best.getMoveMadeCol() == 6){
      //game.placeMark("C3",2);
      return "G13";
    }
    else if(best.getMoveMadeRow() == 12 && best.getMoveMadeCol() == 7){
      //game.placeMark("C3",2);
      return "H13";
    }
    else if(best.getMoveMadeRow() == 12 && best.getMoveMadeCol() == 8){
      //game.placeMark("C3",2);
      return "I13";
    }
    else if(best.getMoveMadeRow() == 12 && best.getMoveMadeCol() == 9){
      //game.placeMark("C3",2);
      return "J13";
    }
    else if(best.getMoveMadeRow() == 12 && best.getMoveMadeCol() == 10){
      //game.placeMark("C3",2);
      return "K13";
    }
    else if(best.getMoveMadeRow() == 12 && best.getMoveMadeCol() == 11){
      //game.placeMark("C3",2);
      return "L13";
    }
    else if(best.getMoveMadeRow() == 12 && best.getMoveMadeCol() == 12){
      //game.placeMark("C3",2);
      return "M13";
    }
    else if(best.getMoveMadeRow() == 12 && best.getMoveMadeCol() == 13){
      //game.placeMark("C3",2);
      return "N13";
    }
    else if(best.getMoveMadeRow() == 12 && best.getMoveMadeCol() == 14){
      //game.placeMark("C3",2);
      return "O13";
    }

    else if(best.getMoveMadeRow() == 13 && best.getMoveMadeCol() == 0){
      //game.placeMark("C3",2);
      return "A14";
    }
    else if(best.getMoveMadeRow() == 13 && best.getMoveMadeCol() == 1){
      //game.placeMark("C3",2);
      return "B14";
    }
    else if(best.getMoveMadeRow() == 13 && best.getMoveMadeCol() == 2){
      //game.placeMark("C3",2);
      return "C14";
    }
    else if(best.getMoveMadeRow() == 13 && best.getMoveMadeCol() == 3){
      //game.placeMark("C3",2);
      return "D14";
    }
    else if(best.getMoveMadeRow() == 13 && best.getMoveMadeCol() == 4){
      //game.placeMark("C3",2);
      return "E14";
    }
    else if(best.getMoveMadeRow() == 13 && best.getMoveMadeCol() == 5){
      //game.placeMark("C3",2);
      return "F14";
    }
    else if(best.getMoveMadeRow() == 13 && best.getMoveMadeCol() == 6){
      //game.placeMark("C3",2);
      return "G14";
    }
    else if(best.getMoveMadeRow() == 13 && best.getMoveMadeCol() == 7){
      //game.placeMark("C3",2);
      return "H14";
    }
    else if(best.getMoveMadeRow() == 13 && best.getMoveMadeCol() == 8){
      //game.placeMark("C3",2);
      return "I14";
    }
    else if(best.getMoveMadeRow() == 13 && best.getMoveMadeCol() == 9){
      //game.placeMark("C3",2);
      return "J14";
    }
    else if(best.getMoveMadeRow() == 13 && best.getMoveMadeCol() == 10){
      //game.placeMark("C3",2);
      return "K14";
    }
    else if(best.getMoveMadeRow() == 13 && best.getMoveMadeCol() == 11){
      //game.placeMark("C3",2);
      return "L14";
    }
    else if(best.getMoveMadeRow() == 13 && best.getMoveMadeCol() == 12){
      //game.placeMark("C3",2);
      return "M14";
    }
    else if(best.getMoveMadeRow() == 13 && best.getMoveMadeCol() == 13){
      //game.placeMark("C3",2);
      return "N14";
    }
    else if(best.getMoveMadeRow() == 13 && best.getMoveMadeCol() == 14){
      //game.placeMark("C3",2);
      return "O14";
    }

    else if(best.getMoveMadeRow() == 14 && best.getMoveMadeCol() == 0){
      //game.placeMark("C3",2);
      return "A15";
    }
    else if(best.getMoveMadeRow() == 14 && best.getMoveMadeCol() == 1){
      //game.placeMark("C3",2);
      return "B15";
    }
    else if(best.getMoveMadeRow() == 14 && best.getMoveMadeCol() == 2){
      //game.placeMark("C3",2);
      return "C15";
    }
    else if(best.getMoveMadeRow() == 14 && best.getMoveMadeCol() == 3){
      //game.placeMark("C3",2);
      return "D15";
    }
    else if(best.getMoveMadeRow() == 14 && best.getMoveMadeCol() == 4){
      //game.placeMark("C3",2);
      return "E15";
    }
    else if(best.getMoveMadeRow() == 14 && best.getMoveMadeCol() == 5){
      //game.placeMark("C3",2);
      return "F15";
    }
    else if(best.getMoveMadeRow() == 14 && best.getMoveMadeCol() == 6){
      //game.placeMark("C3",2);
      return "G15";
    }
    else if(best.getMoveMadeRow() == 14 && best.getMoveMadeCol() == 7){
      //game.placeMark("H15",2);
      return "H15";
    }
    else if(best.getMoveMadeRow() == 14 && best.getMoveMadeCol() == 8){
      //game.placeMark("I15",2);
      return "I15";
    }
    else if(best.getMoveMadeRow() == 14 && best.getMoveMadeCol() == 9){
      //game.placeMark("J15",2);
      return "J15";
    }
    else if(best.getMoveMadeRow() == 14 && best.getMoveMadeCol() == 10){
      //game.placeMark("K15",2);
      return "K15";
    }
    else if(best.getMoveMadeRow() == 14 && best.getMoveMadeCol() == 11){
      //game.placeMark("L15",2);
      return "L15";
    }
    else if(best.getMoveMadeRow() == 14 && best.getMoveMadeCol() == 12){
      //game.placeMark("M15",2);
      return "M15";
    }
    else if(best.getMoveMadeRow() == 14 && best.getMoveMadeCol() == 13){
      //game.placeMark("N15",2);
      return "N15";
    }
    else if(best.getMoveMadeRow() == 14 && best.getMoveMadeCol() == 14){
      //game.placeMark("O15",2);
      return "O15";
    }

    else{
      return "0";
    }
  }

  public static MCTSNode selectInitialNode(MCTSNode node){
    MCTSNode initialNode = node;
    while (initialNode.getPossibleMovesSize()==0&&initialNode.checkForEmptySpace()==true){
      initialNode = initialNode.Select();
    }
    return initialNode;
  }

}
