// FiveLogic.java - Game to get 5 pieces in a row.
///////////////////////////////////////////////// class FiveLogic
/** This class implements the logic (model) for the game of 
    Five-In-A-Row.   
    @author Fred Swartz
    @version 2004-05-02
    @modified Tanel Tammet
    @modification 2005-11-20
    */
class FiveLogic {
    /** Number of board rows. */
    private int maxRows_;
    /** Number of board columns. */
    private int maxCols_;
    /** The board. */
    int[][] board_; 
    /** The player who moves next. */
    int     nextPlayer_;
    /** Number of moves in the game. */
    int     moves_ = 0;
    
    //-- Constants
    public  static final int EMPTY   = 0;  // The cell is empty.
    private static final int PLAYER1 = 1;
    public  static final int TIE     = -1; // Game is a tie (draw).
    
    //================================================== constructor
    public FiveLogic(int rows, int cols) {
        maxRows_ = rows;
        maxCols_ = cols;
        board_ = new int[maxRows_][maxCols_];
        reset();
    }//end constructor
    
    //================================================= getNextPlayer
    /** Returns the next player. */
    public int getNextPlayer() {
        return nextPlayer_;
    }//end getFace
    
    //=================================================== getPlayerAt
    /** Returns player who has played at particular row and column. */
    public int getPlayerAt(int r, int c) {
        return board_[r][c];
    }//end getPlayerAt
    
    //========================================================== reset
    /** Clears board to initial state. Makes first move in center. */
    public void reset() {
        for (int r=0; r<maxRows_; r++) {
            for (int c=0; c<maxCols_; c++) {
                board_[r][c] = EMPTY;
            }
        }
        moves_ = 0;  // No moves so far.
        nextPlayer_ = PLAYER1;  
        //-- Make first move in center.
        move(maxCols_/2, maxRows_/2);  // First player moves to center
    }//end reset
    
    //=========================================================== move
    /** Play a marker on the board, record it, flip players. */
    
    public void move(int r, int c) {
        //assert board_[r][c] == EMPTY;
        board_[r][c] = nextPlayer_;  // Record this move.
        nextPlayer_ = 3-nextPlayer_; // Flip players
        moves_++;                    // Increment number of moves.
    }//end move
        
    //=========================================================== move
    /** Play a marker on the board, record it, flip players. */
    public void human_move(int r, int c) {      
        int enr;      
        move(r,c);  // actually perform human move             
        // compute machine move and perform it
        enr=best_move(nextPlayer_,board_);
        board_[decode_move_x(enr)][decode_move_y(enr)]=nextPlayer_;       
        nextPlayer_ = 3-nextPlayer_; // Flip players
        moves_++;                    
    }//end move
    
   
    //========================================== utility method count5_
    /** The count5_ utility function returns true if there are five in
        a row starting at the specified r,c position and 
        continuing in the dr direcection (+1, -1) and
        similarly for the column c.
        */
    private boolean count5_(int r, int dr, int c, int dc) {
        int player = board_[r][c];  // remember the player.
        for (int i=1; i<5; i++) {
            if (board_[r+dr*i][c+dc*i] != player) return false;
        }
        return true;  // There were 5 in a row!
    } // count5_

    //=================================================== getGameStatus
    /** -1 = game is tie, 0 = more to play, 
         1 = player1 wins, 2 = player2 wins */
    public int getGameStatus() {
        int row;
        int col;
        int n_up, n_right, n_up_right, n_up_left;

        boolean at_least_one_move;   // true if game isn't a tie

        for (row = 0; row < maxRows_; row++) {
            for (col = 0; col < maxCols_; col++) {
                int p = board_[row][col];
                if (p != EMPTY) {
                    // look at 4 kinds of rows of 5
                    //  1. a column going up
                    //  2. a row going to the right
                    //  3. a diagonal up and to the right
                    //  4. a diagonal up and to the left
    
                    if (row < maxRows_-4) // Look up
                        if (count5_(row, 1, col, 0)) return p;
    
                    if (col < maxCols_-4) { // row to right
                        if (count5_(row, 0, col, 1))  return p;
    
                        if (row < maxRows_-4) { // diagonal up to right
                            if (count5_(row, 1, col, 1)) return p;
                        }
                    }
    
                    if (col > 3 && row < maxRows_-4) { // diagonal up left
                        if (count5_(row, 1, col, -1)) return p;
                    }
                }//endif position wasn't empty
            }//endfor row
        }//endfor col

        // Neither player has won, it's tie if there are empty positions.
        // Game is finished if total moves equals number of positions.
        if (moves_ == maxRows_*maxCols_) {
            return TIE; // Game tied.  No more possible moves.
        } else {
            return 0;  // More to play.
        }
    }//end getGameStatus

    
    //=========================================================== 
    //
    //  new functions by T.Tammet follow 
    //
    //===========================================================
    
    
    // encode and decode a move number
    
    public int encode_move(int x, int y)  {
      return x*1000+y;      
    } 
    
    public int decode_move_x(int n) {
      return (int)(n/1000);      
    }  
    
    public int decode_move_y(int n) {
      return (n%1000);      
    }  
    
    //  Find the best move 
    
    /* ==================================
    
       Finding the best move for the computer - essentially, the following function -
       is exactly what you have to improve !!!    

       ==================================
    */    
    public int best_move(int player, int[][] board) {        
      int maxrows;
      int maxcols;        
      int i;
      int j;
        
      //System.out.println("start finding best move for player: "+player);          
      
      // what size does the board have?
        
      maxrows=board.length;
      maxcols=board[0].length;  
      
      // find the first available cell        
        
      for(i=0;i<maxrows;i++) {
        for(j=0;j<maxcols;j++) {
          if (board[i][j]==0) {
             //System.out.println("best move found: i: "+i+" j: "+j);
             return encode_move(i,j);
          }           
        }     
      }
      System.out.println("error in best_move: no move found");
      System.exit(1);
      return 0;
    }
    
    
}//end class FiveLogic
