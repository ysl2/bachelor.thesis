// Five.java - main program for Five-In-A-Row Program
import javax.swing.JFrame;

////////////////////////////////////////////////////// class Five
/** Five.java - Winner is able to put 5 pieces in a row.
   The Five program consists of four files:
   Five.java      - this file with main to create window.
   FiveGUI.java   - implements the GUI interface.
   FiveLogic.java - the logical functioning
   FiveOpponent.java - opponent prototype for auto-play (will be replaced later!)
   @author Fred Swartz
   @version 2004-05-02
   @modified Tanel Tammet
   @modification 2005-11-20
   */
class Five {
    //================================================ method main
    public static void main(String[] args) {
        JFrame window = new JFrame("Five In A Row");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(new FiveGUI(10,10,window));
        window.pack();  // finalize layout
        //window.setResizable(false);
        window.show();  // make window visible
    }//end main
}//endclass Five
