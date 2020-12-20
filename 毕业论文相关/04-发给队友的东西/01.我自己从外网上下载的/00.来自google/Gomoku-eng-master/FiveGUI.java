// FiveGUI.java
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/////////////////////////////////////////////////// class FiveGUI
/** A Graphical User Interface for a Five-In-A-Row game.
    This implements the user interface (view and controller),
    but the logic (model) is implemented in a separate class that
    knows nothing about the user interface.   
    @author Fred Swartz
    @version 2004-05-02 Rodenbach
    @modified Tanel Tammet
    @modification 2005-11-20
    */
class FiveGUI extends JPanel implements Runnable {
    //=============================================== instance variables
    JFrame WINDOW;
    GraphicsPanel boardDisplay_;
    private JTextField    statusField_ = new JTextField();
    private FiveLogic     gameLogic_;
    private boolean       gameOver_ = false;
    
    private static final Color[]  PLAYER_COLOR = {null, Color.BLACK, Color.WHITE};
    private static final String[] PLAYER_NAME  = {null, "BLACK", "WHITE"};
    
    Thread runner;    // A thread for the autoplay loop. 
    boolean running;  // This is set to true when the thread is running.

    //====================================================== constructor
    public FiveGUI(int ROWS,int COLS,JFrame window) {
        WINDOW=window;
        //--- Create some buttons
        JButton newGameButton = new JButton("New");        
        JButton autoplayButton = new JButton("Auto");
        JButton board20Button = new JButton("20x20");
        JButton board10Button = new JButton("10x10");

        //--- Create control panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.add(newGameButton);        
        controlPanel.add(autoplayButton); 
        controlPanel.add(board20Button); 
        controlPanel.add(board10Button); 
        
        //--- Create graphics panel
        boardDisplay_ = new GraphicsPanel(ROWS,COLS);
        
        //--- Set the layout and add the components
        this.setLayout(new BorderLayout());
        this.add(controlPanel , BorderLayout.NORTH);
        this.add(boardDisplay_, BorderLayout.CENTER);
        this.add(statusField_ , BorderLayout.SOUTH);
        
        //-- Add action listeners
        newGameButton.addActionListener(new NewGameAction());
        autoplayButton.addActionListener(new autoplayAction());
        board20Button.addActionListener(new board20Action());
        board10Button.addActionListener(new board10Action());
        
        //-- Create game logic
        gameLogic_ = new FiveLogic(ROWS, COLS);
        
    }//end constructor

    //////////////////////////////////////////////// class GraphicsPanel
    // This is defined inside the outer class so that
    // it can use the game logic variable.
    class GraphicsPanel extends JPanel implements MouseListener {
        int ROWS = 10;
        int COLS = 10;
        int CELL_SIZE = 30; // Pixels
        int WIDTH=0;
        int HEIGHT=0;               
       
        //================================================== constructor
        public GraphicsPanel(int rows, int cols) {
            ROWS=rows;
            COLS=cols;
            if (WIDTH==0) {
              WIDTH  = COLS * CELL_SIZE;
              HEIGHT = ROWS * CELL_SIZE;  
            }    
            this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
            this.setBackground(Color.GRAY);
            this.addMouseListener(this);  // Listen own mouse events.
        }//end constructor
        
        //============================================== paintComponent
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            //-- Paint grid (could be done once and saved).
            for (int r=1; r<ROWS; r++) {  // Horizontal lines
                g.drawLine(0, r*CELL_SIZE, WIDTH, r*CELL_SIZE);
            }
            for (int c=1; c<COLS; c++) {
                g.drawLine(c*CELL_SIZE, 0, c*CELL_SIZE, HEIGHT);
            }
            
            //-- Draw players pieces.
            for (int r=0; r<ROWS; r++) {
                for (int c=0; c<COLS; c++) {
                    int x = c * CELL_SIZE;
                    int y = r * CELL_SIZE;
                    int who = gameLogic_.getPlayerAt(r, c);
                    if (who != gameLogic_.EMPTY) {
                        g.setColor(PLAYER_COLOR[who]);
                        g.fillOval(x+2, y+2, CELL_SIZE-4, CELL_SIZE-4);
                    }
                }
            }
        }//end paintComponent
        
        //======================================== listener mousePressed
        public void mousePressed(MouseEvent e) {
            //int enr;
            
            //--- map x,y coordinates into a row and col.
            int col = e.getX()/CELL_SIZE;
            int row = e.getY()/CELL_SIZE;
            
            int currentOccupant = gameLogic_.getPlayerAt(row, col);
            if (!gameOver_ && currentOccupant == gameLogic_.EMPTY) {                
              gameLogic_.human_move(row, col);                                              
              checkStatus();                
            }         
        }//end mousePressed
        
        public void checkStatus() {                        
            if (!gameOver_) {               
                switch (gameLogic_.getGameStatus()) {
                    case 1: // Player one wins.  Game over.
                            gameOver_ = true;
                            statusField_.setText("BLACK WINS");
                            break;
                    case 2: // Player two wins.  Game over.
                            gameOver_ = true;
                            statusField_.setText("WHITE WINS");
                            break;
                            
                    case FiveLogic.TIE:  // Tie game.  Game over.
                            gameOver_ = true;
                            statusField_.setText("TIE GAME");
                            break;
                            
                    default: showNextPlayer();
                }        
            }            
            this.repaint();  // Show any updates to game.
        }//end mousePressed
        
        //========================================== ignore these events
        public void mouseClicked (MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered (MouseEvent e) {}
        public void mouseExited  (MouseEvent e) {}                   
      
    }//end inner class GraphicsPanel
    
    //======================================= untility method showNextPlayer
    private void showNextPlayer() {
       statusField_.setText(PLAYER_NAME[gameLogic_.getNextPlayer()] + " to play");
    }//end showNextPlayer
        
    
    ///////////////////////////////////////// inner class NewGameAction
    private class NewGameAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gameLogic_.reset();
            gameOver_ = false;
            showNextPlayer();
            boardDisplay_.repaint();
        }
    }//end inner class NewGameAction
    
    private class board20Action implements ActionListener {       
        
        public void actionPerformed(ActionEvent e) { 
           FiveGUI ngui;
            
           //System.out.println("board20");                                                 
           WINDOW.hide(); 
           JFrame window = new JFrame("Five In A Row");
           WINDOW=window;
           window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           ngui=new FiveGUI(20,20,window);             
           window.setContentPane(ngui);
           window.pack();  // finalize layout         
           window.show();  // make window visible
            
           gameLogic_ = new FiveLogic(20, 20);
           gameLogic_.reset();
           gameOver_ = false;            
           showNextPlayer();
           boardDisplay_.repaint();             
        }        
        
    }//end inner class
    
    private class board10Action implements ActionListener {       
        
        public void actionPerformed(ActionEvent e) { 
           FiveGUI ngui;
            
           //System.out.println("board10");                                                 
           WINDOW.hide(); 
           JFrame window = new JFrame("Five In A Row");
           WINDOW=window;
           window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           ngui=new FiveGUI(10,10,window);             
           window.setContentPane(ngui);
           window.pack();  // finalize layout         
           window.show();  // make window visible
            
           gameLogic_ = new FiveLogic(10, 10);
           gameLogic_.reset();
           gameOver_ = false;            
           showNextPlayer();
           boardDisplay_.repaint();               
        }        
        
    }//end inner class
    
    private class autoplayAction implements ActionListener {       
        
        public void actionPerformed(ActionEvent e) { 
           startRunning();                  
        }        
        
    }//end inner class 
    
    
    // startRunning creates the autoplay thread and starts the following run()
    
    void startRunning() {
      if (running)  return;
      runner = new Thread(this);
         // Creates a thread that will execute the run() method in this class
      running = true;
      runner.start(); // following run() is called automatically here
    }      
    
    // run implements the autoplay loop
    
    public void run() {
      int enr; 
        
      //System.out.println("Autoplay starts."); 
      while(!gameOver_) {                      
             
        // get opponent move first
           
        enr=FiveOpponent.best_opponent_move(gameLogic_.board_);                         
        gameLogic_.board_[gameLogic_.decode_move_x(enr)][gameLogic_.decode_move_y(enr)]
            =gameLogic_.nextPlayer_;                          
        gameLogic_.nextPlayer_ = 3-gameLogic_.nextPlayer_; // Flip players
        gameLogic_.moves_++;                    // Increment number of moves.
        showNextPlayer();
        boardDisplay_.checkStatus();                         
        if(gameOver_) {
         //System.out.println("Game over!");   
         break;   
        }    
           
        // now get your own move
             
        enr=gameLogic_.best_move(gameLogic_.nextPlayer_,gameLogic_.board_);            
        gameLogic_.board_[gameLogic_.decode_move_x(enr)][gameLogic_.decode_move_y(enr)]
           =gameLogic_.nextPlayer_;       
        gameLogic_.nextPlayer_ = 3-gameLogic_.nextPlayer_; // Flip players
        gameLogic_.moves_++;     // Increment number of moves.      
        showNextPlayer();
        boardDisplay_.checkStatus();                                                                                          
      }
      running=false;        
    }
      

}//end class FiveGUI
