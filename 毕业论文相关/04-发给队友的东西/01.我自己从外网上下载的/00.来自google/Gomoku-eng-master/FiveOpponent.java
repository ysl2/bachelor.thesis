// FiveOpponent.java - Game to get 5 pieces in a row.
///////////////////////////////////////////////// class FiveOpponent
/** This class implements the prototype opponent for the autoplay.
    There is no need to improve this code here.
    Instead, you will later get a new version as a pre-compiled
    class, and your program should be able to beat this
    pre-compiled class in ca 90% of the matches.
    Once you have the pre-compiled class, you can simply
    throw away this file here.
    @author Tanel Tammet
    @version 2005-11-20
    */
    
class FiveOpponent {
   
    //  Find the best move 
        
    public static int best_opponent_move(int[][] board) {
      int maxrows;
      int maxcols;        
      int blacknr;
      int whitenr;        
      int i;
      int j;
      int mycolor;  
      int whitecell=2;
      int blackcell=1;      
         
      // first just wait half a second
        
      try {
         Thread.currentThread().sleep(500);
      }  catch (InterruptedException e) {
          e.printStackTrace();
      } 
                  
      // what size does the board have?
        
      maxrows=board.length;
      maxcols=board[0].length;  
        
      // who is about to move (i.e. what is my color?)
      // this info is not actually used by the move finder later  
      
      whitenr=0;
      blacknr=0;        
      for(i=0;i<maxrows;i++) {
       for(j=0;j<maxcols;j++) {
          if (board[i][j]==whitecell) whitenr++;
          else if (board[i][j]==blackcell) blacknr++;                                    
        }     
      }  

      if (whitenr>=blacknr) mycolor=blackcell;
      else mycolor=whitecell;    
          
      // find the first empty cell, from rightmost lower area        
        
      for(i=maxrows-1;i>=0;i--) {
        for(j=maxcols-1;j>=0;j--) {
          if (board[i][j]==0) {
             //System.out.println("opponent move i: "+i+" j: "+j);
             return encode_move(i,j);
          }           
        }     
      }
      System.out.println("error in best_opponent_move: no move found");
      System.exit(1);
      return 0;
    }
    
    // encode_move must be the same as for FiveLogic!!
    
    private static int encode_move(int x, int y)  {
      return x*1000+y;      
    } 
    
}//end class FiveLogic
