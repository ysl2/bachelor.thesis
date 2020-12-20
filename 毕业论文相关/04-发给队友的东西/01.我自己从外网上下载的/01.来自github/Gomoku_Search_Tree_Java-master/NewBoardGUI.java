import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NewBoardGUI extends JFrame
{

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

  private JButton btnA1, btnA2, btnA3, btnA4, btnA5, btnA6, btnA7, btnA8, btnA9, btnA10, btnA11, btnA12, btnA13, btnA14, btnA15;
  private JButton btnB1, btnB2, btnB3, btnB4, btnB5, btnB6, btnB7, btnB8, btnB9, btnB10, btnB11, btnB12, btnB13, btnB14, btnB15;
  private JButton btnC1, btnC2, btnC3, btnC4, btnC5, btnC6, btnC7, btnC8, btnC9, btnC10, btnC11, btnC12, btnC13, btnC14, btnC15;
  private JButton btnD1, btnD2, btnD3, btnD4, btnD5, btnD6, btnD7, btnD8, btnD9, btnD10, btnD11, btnD12, btnD13, btnD14, btnD15;
  private JButton btnE1, btnE2, btnE3, btnE4, btnE5, btnE6, btnE7, btnE8, btnE9, btnE10, btnE11, btnE12, btnE13, btnE14, btnE15;
  private JButton btnF1, btnF2, btnF3, btnF4, btnF5, btnF6, btnF7, btnF8, btnF9, btnF10, btnF11, btnF12, btnF13, btnF14, btnF15;
  private JButton btnG1, btnG2, btnG3, btnG4, btnG5, btnG6, btnG7, btnG8, btnG9, btnG10, btnG11, btnG12, btnG13, btnG14, btnG15;
  private JButton btnH1, btnH2, btnH3, btnH4, btnH5, btnH6, btnH7, btnH8, btnH9, btnH10, btnH11, btnH12, btnH13, btnH14, btnH15;
  private JButton btnI1, btnI2, btnI3, btnI4, btnI5, btnI6, btnI7, btnI8, btnI9, btnI10, btnI11, btnI12, btnI13, btnI14, btnI15;
  private JButton btnJ1, btnJ2, btnJ3, btnJ4, btnJ5, btnJ6, btnJ7, btnJ8, btnJ9, btnJ10, btnJ11, btnJ12, btnJ13, btnJ14, btnJ15;
  private JButton btnK1, btnK2, btnK3, btnK4, btnK5, btnK6, btnK7, btnK8, btnK9, btnK10, btnK11, btnK12, btnK13, btnK14, btnK15;
  private JButton btnL1, btnL2, btnL3, btnL4, btnL5, btnL6, btnL7, btnL8, btnL9, btnL10, btnL11, btnL12, btnL13, btnL14, btnL15;
  private JButton btnM1, btnM2, btnM3, btnM4, btnM5, btnM6, btnM7, btnM8, btnM9, btnM10, btnM11, btnM12, btnM13, btnM14, btnM15;
  private JButton btnN1, btnN2, btnN3, btnN4, btnN5, btnN6, btnN7, btnN8, btnN9, btnN10, btnN11, btnN12, btnN13, btnN14, btnN15;
  private JButton btnO1, btnO2, btnO3, btnO4, btnO5, btnO6, btnO7, btnO8, btnO9, btnO10, btnO11, btnO12, btnO13, btnO14, btnO15;
  private BoardGame board;
  private MctsPlayer comPlayer;

	public static void main(String [] args)
	{
		new NewBoardGUI();
	}

	public NewBoardGUI()
	{
		// Set up the grid
  		this.setSize(700,650);
      this.setResizable(true);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setTitle("Gomoku");
      JMenuBar menuBar = new JMenuBar();
      JMenu menu = new JMenu("Options");
      JMenuItem quit = new JMenuItem("Quit");
      quit.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e){
            System.exit(0);;
          }
      });
      JMenuItem newGame = new JMenuItem("New Game");
      newGame.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e){
            resetGame();
          }
      });
      menu.add(newGame);
      menu.add(quit);
      menuBar.add(menu);
      setJMenuBar(menuBar);
		  JPanel panel1 = new JPanel();
	    panel1.setSize(700,650);
	    panel1.setLayout(new GridLayout(15,15));
      this.setVisible(true);
      btnA1 = createButton("A1");
      btnB1 = createButton("B1");
      btnC1 = createButton("C1");
      btnD1 = createButton("D1");
      btnE1 = createButton("E1");
      btnF1 = createButton("F1");
      btnG1 = createButton("G1");
      btnH1 = createButton("H1");  //15th letter in alphabet is O
      btnI1 = createButton("I1");
      btnJ1 = createButton("J1");
      btnK1 = createButton("K1");
      btnL1 = createButton("L1");
      btnM1 = createButton("M1");
      btnN1 = createButton("N1");
      btnO1 = createButton("O1");

	    btnA2 = createButton("A2");
      btnB2 = createButton("B2");
      btnC2 = createButton("C2");
      btnD2 = createButton("D2");
      btnE2 = createButton("E2");
      btnF2 = createButton("F2");
      btnG2 = createButton("G2");
      btnH2 = createButton("H2");  //15th letter in alphabet is O
      btnI2 = createButton("I2");
      btnJ2 = createButton("J2");
      btnK2 = createButton("K2");
      btnL2 = createButton("L2");
      btnM2 = createButton("M2");
      btnN2 = createButton("N2");
      btnO2 = createButton("O2");

	    btnA3 = createButton("A3");
	    btnB3 = createButton("B3");
	    btnC3 = createButton("C3");
      btnD3 = createButton("D3");
      btnE3 = createButton("E3");
      btnF3 = createButton("F3");
      btnG3 = createButton("G3");
      btnH3 = createButton("H3");  //15th letter in alphabet is O
      btnI3 = createButton("I3");
      btnJ3 = createButton("J3");
      btnK3 = createButton("K3");
      btnL3 = createButton("L3");
      btnM3 = createButton("M3");
      btnN3 = createButton("N3");
      btnO3 = createButton("O3");

      btnA4 = createButton("A4");
	    btnB4 = createButton("B4");
	    btnC4 = createButton("C4");
      btnD4 = createButton("D4");
      btnE4 = createButton("E4");
      btnF4 = createButton("F4");
      btnG4 = createButton("G4");
      btnH4 = createButton("H4");
      btnI4 = createButton("I4");
      btnJ4 = createButton("J4");
      btnK4 = createButton("K4");
      btnL4 = createButton("L4");
      btnM4 = createButton("M4");
      btnN4 = createButton("N4");
      btnO4 = createButton("O4");

      btnA5 = createButton("A5");
	    btnB5 = createButton("B5");
	    btnC5 = createButton("C5");
      btnD5 = createButton("D5");
      btnE5 = createButton("E5");
      btnF5 = createButton("F5");
      btnG5 = createButton("G5");
      btnH5 = createButton("H5");
      btnI5 = createButton("I5");
      btnJ5 = createButton("J5");
      btnK5 = createButton("K5");
      btnL5 = createButton("L5");
      btnM5 = createButton("M5");
      btnN5 = createButton("N5");
      btnO5 = createButton("O5");

      btnA6 = createButton("A6");
	    btnB6 = createButton("B6");
	    btnC6 = createButton("C6");
      btnD6 = createButton("D6");
      btnE6 = createButton("E6");
      btnF6 = createButton("F6");
      btnG6 = createButton("G6");
      btnH6 = createButton("H6");
      btnI6 = createButton("I6");
      btnJ6 = createButton("J6");
      btnK6 = createButton("K6");
      btnL6 = createButton("L6");
      btnM6 = createButton("M6");
      btnN6 = createButton("N6");
      btnO6 = createButton("O6");

      btnA7 = createButton("A7");
	    btnB7 = createButton("B7");
	    btnC7 = createButton("C7");
      btnD7 = createButton("D7");
      btnE7 = createButton("E7");
      btnF7 = createButton("F7");
      btnG7 = createButton("G7");
      btnH7 = createButton("H7");  //15th letter in alphabet is O
      btnI7 = createButton("I7");
      btnJ7 = createButton("J7");
      btnK7 = createButton("K7");
      btnL7 = createButton("L7");
      btnM7 = createButton("M7");
      btnN7 = createButton("N7");
      btnO7 = createButton("O7");

      btnA8 = createButton("A8");
	    btnB8 = createButton("B8");
	    btnC8 = createButton("C8");
      btnD8 = createButton("D8");
      btnE8 = createButton("E8");
      btnF8 = createButton("F8");
      btnG8 = createButton("G8");
      btnH8 = createButton("H8");  //15th letter in alphabet is O
      btnI8 = createButton("I8");
      btnJ8 = createButton("J8");
      btnK8 = createButton("K8");
      btnL8 = createButton("L8");
      btnM8 = createButton("M8");
      btnN8 = createButton("N8");
      btnO8 = createButton("O8");

      btnA9 = createButton("A9");
	    btnB9 = createButton("B9");
	    btnC9 = createButton("C9");
      btnD9 = createButton("D9");
      btnE9 = createButton("E9");
      btnF9 = createButton("F9");
      btnG9 = createButton("G9");
      btnH9 = createButton("H9");  //15th letter in alphabet is O
      btnI9 = createButton("I9");
      btnJ9 = createButton("J9");
      btnK9 = createButton("K9");
      btnL9 = createButton("L9");
      btnM9 = createButton("M9");
      btnN9 = createButton("N9");
      btnO9 = createButton("O9");

      btnA10 = createButton("A10");
	    btnB10 = createButton("B10");
	    btnC10 = createButton("C10");
      btnD10 = createButton("D10");
      btnE10 = createButton("E10");
      btnF10 = createButton("F10");
      btnG10 = createButton("G10");
      btnH10 = createButton("H10");  //15th letter in alphabet is O
      btnI10 = createButton("I10");
      btnJ10 = createButton("J10");
      btnK10 = createButton("K10");
      btnL10 = createButton("L10");
      btnM10 = createButton("M10");
      btnN10 = createButton("N10");
      btnO10 = createButton("O10");

      btnA11 = createButton("A11");
	    btnB11 = createButton("B11");
	    btnC11 = createButton("C11");
      btnD11 = createButton("D11");
      btnE11 = createButton("E11");
      btnF11 = createButton("F11");
      btnG11 = createButton("G11");
      btnH11 = createButton("H11");  //15th letter in alphabet is O
      btnI11 = createButton("I11");
      btnJ11 = createButton("J11");
      btnK11 = createButton("K11");
      btnL11 = createButton("L11");
      btnM11 = createButton("M11");
      btnN11 = createButton("N11");
      btnO11 = createButton("O11");

      btnA12 = createButton("A12");
	    btnB12 = createButton("B12");
	    btnC12 = createButton("C12");
      btnD12 = createButton("D12");
      btnE12 = createButton("E12");
      btnF12 = createButton("F12");
      btnG12 = createButton("G12");
      btnH12 = createButton("H12");  //15th letter in alphabet is O
      btnI12 = createButton("I12");
      btnJ12 = createButton("J12");
      btnK12 = createButton("K12");
      btnL12 = createButton("L12");
      btnM12 = createButton("M12");
      btnN12 = createButton("N12");
      btnO12 = createButton("O12");

      btnA13 = createButton("A13");
	    btnB13 = createButton("B13");
	    btnC13 = createButton("C13");
      btnD13 = createButton("D13");
      btnE13 = createButton("E13");
      btnF13 = createButton("F13");
      btnG13 = createButton("G13");
      btnH13 = createButton("H13");
      btnI13 = createButton("I13");
      btnJ13 = createButton("J13");
      btnK13 = createButton("K13");
      btnL13 = createButton("L13");
      btnM13 = createButton("M13");
      btnN13 = createButton("N13");
      btnO13 = createButton("O13");

      btnA14 = createButton("A14");
	    btnB14 = createButton("B14");
	    btnC14 = createButton("C14");
      btnD14 = createButton("D14");
      btnE14 = createButton("E14");
      btnF14 = createButton("F14");
      btnG14 = createButton("G14");
      btnH14 = createButton("H14");
      btnI14 = createButton("I14");
      btnJ14 = createButton("J14");
      btnK14 = createButton("K14");
      btnL14 = createButton("L14");
      btnM14 = createButton("M14");
      btnN14 = createButton("N14");
      btnO14 = createButton("O14");

      btnA15 = createButton("A15");
	    btnB15 = createButton("B15");
	    btnC15 = createButton("C15");
      btnD15 = createButton("D15");
      btnE15 = createButton("E15");
      btnF15 = createButton("F15");
      btnG15 = createButton("G15");
      btnH15 = createButton("H15");
      btnI15 = createButton("I15");
      btnJ15 = createButton("J15");
      btnK15 = createButton("K15");
      btnL15 = createButton("L15");
      btnM15 = createButton("M15");
      btnN15 = createButton("N15");
      btnO15 = createButton("O15");

      panel1.add(btnA1);
  		panel1.add(btnB1);
  		panel1.add(btnC1);
      panel1.add(btnD1);
      panel1.add(btnE1);
      panel1.add(btnF1);
      panel1.add(btnG1);
      panel1.add(btnH1);
      panel1.add(btnI1);
      panel1.add(btnJ1);
      panel1.add(btnK1);
      panel1.add(btnL1);
      panel1.add(btnM1);
      panel1.add(btnN1);
      panel1.add(btnO1);

  		panel1.add(btnA2);
  		panel1.add(btnB2);
      panel1.add(btnC2);
      panel1.add(btnD2);
      panel1.add(btnE2);
      panel1.add(btnF2);
      panel1.add(btnG2);
      panel1.add(btnH2);
      panel1.add(btnI2);
      panel1.add(btnJ2);
      panel1.add(btnK2);
      panel1.add(btnL2);
      panel1.add(btnM2);
      panel1.add(btnN2);
      panel1.add(btnO2);

      panel1.add(btnA3);
  		panel1.add(btnB3);
  		panel1.add(btnC3);
      panel1.add(btnD3);
      panel1.add(btnE3);
      panel1.add(btnF3);
      panel1.add(btnG3);
      panel1.add(btnH3);
      panel1.add(btnI3);
      panel1.add(btnJ3);
      panel1.add(btnK3);
      panel1.add(btnL3);
      panel1.add(btnM3);
      panel1.add(btnN3);
      panel1.add(btnO3);

      panel1.add(btnA4);
  		panel1.add(btnB4);
  		panel1.add(btnC4);
      panel1.add(btnD4);
      panel1.add(btnE4);
      panel1.add(btnF4);
      panel1.add(btnG4);
      panel1.add(btnH4);
      panel1.add(btnI4);
      panel1.add(btnJ4);
      panel1.add(btnK4);
      panel1.add(btnL4);
      panel1.add(btnM4);
      panel1.add(btnN4);
      panel1.add(btnO4);

      panel1.add(btnA5);
  		panel1.add(btnB5);
  		panel1.add(btnC5);
      panel1.add(btnD5);
      panel1.add(btnE5);
      panel1.add(btnF5);
      panel1.add(btnG5);
      panel1.add(btnH5);
      panel1.add(btnI5);
      panel1.add(btnJ5);
      panel1.add(btnK5);
      panel1.add(btnL5);
      panel1.add(btnM5);
      panel1.add(btnN5);
      panel1.add(btnO5);

      panel1.add(btnA6);
  		panel1.add(btnB6);
  		panel1.add(btnC6);
      panel1.add(btnD6);
      panel1.add(btnE6);
      panel1.add(btnF6);
      panel1.add(btnG6);
      panel1.add(btnH6);
      panel1.add(btnI6);
      panel1.add(btnJ6);
      panel1.add(btnK6);
      panel1.add(btnL6);
      panel1.add(btnM6);
      panel1.add(btnN6);
      panel1.add(btnO6);

      panel1.add(btnA7);
  		panel1.add(btnB7);
  		panel1.add(btnC7);
      panel1.add(btnD7);
      panel1.add(btnE7);
      panel1.add(btnF7);
      panel1.add(btnG7);
      panel1.add(btnH7);
      panel1.add(btnI7);
      panel1.add(btnJ7);
      panel1.add(btnK7);
      panel1.add(btnL7);
      panel1.add(btnM7);
      panel1.add(btnN7);
      panel1.add(btnO7);

      panel1.add(btnA8);
  		panel1.add(btnB8);
  		panel1.add(btnC8);
      panel1.add(btnD8);
      panel1.add(btnE8);
      panel1.add(btnF8);
      panel1.add(btnG8);
      panel1.add(btnH8);
      panel1.add(btnI8);
      panel1.add(btnJ8);
      panel1.add(btnK8);
      panel1.add(btnL8);
      panel1.add(btnM8);
      panel1.add(btnN8);
      panel1.add(btnO8);

      panel1.add(btnA9);
  		panel1.add(btnB9);
  		panel1.add(btnC9);
      panel1.add(btnD9);
      panel1.add(btnE9);
      panel1.add(btnF9);
      panel1.add(btnG9);
      panel1.add(btnH9);
      panel1.add(btnI9);
      panel1.add(btnJ9);
      panel1.add(btnK9);
      panel1.add(btnL9);
      panel1.add(btnM9);
      panel1.add(btnN9);
      panel1.add(btnO9);

      panel1.add(btnA10);
  		panel1.add(btnB10);
  		panel1.add(btnC10);
      panel1.add(btnD10);
      panel1.add(btnE10);
      panel1.add(btnF10);
      panel1.add(btnG10);
      panel1.add(btnH10);
      panel1.add(btnI10);
      panel1.add(btnJ10);
      panel1.add(btnK10);
      panel1.add(btnL10);
      panel1.add(btnM10);
      panel1.add(btnN10);
      panel1.add(btnO10);

      panel1.add(btnA11);
  		panel1.add(btnB11);
  		panel1.add(btnC11);
      panel1.add(btnD11);
      panel1.add(btnE11);
      panel1.add(btnF11);
      panel1.add(btnG11);
      panel1.add(btnH11);
      panel1.add(btnI11);
      panel1.add(btnJ11);
      panel1.add(btnK11);
      panel1.add(btnL11);
      panel1.add(btnM11);
      panel1.add(btnN11);
      panel1.add(btnO11);

      panel1.add(btnA12);
  		panel1.add(btnB12);
  		panel1.add(btnC12);
      panel1.add(btnD12);
      panel1.add(btnE12);
      panel1.add(btnF12);
      panel1.add(btnG12);
      panel1.add(btnH12);
      panel1.add(btnI12);
      panel1.add(btnJ12);
      panel1.add(btnK12);
      panel1.add(btnL12);
      panel1.add(btnM12);
      panel1.add(btnN12);
      panel1.add(btnO12);

      panel1.add(btnA13);
  		panel1.add(btnB13);
  		panel1.add(btnC13);
      panel1.add(btnD13);
      panel1.add(btnE13);
      panel1.add(btnF13);
      panel1.add(btnG13);
      panel1.add(btnH13);
      panel1.add(btnI13);
      panel1.add(btnJ13);
      panel1.add(btnK13);
      panel1.add(btnL13);
      panel1.add(btnM13);
      panel1.add(btnN13);
      panel1.add(btnO13);

      panel1.add(btnA14);
  		panel1.add(btnB14);
  		panel1.add(btnC14);
      panel1.add(btnD14);
      panel1.add(btnE14);
      panel1.add(btnF14);
      panel1.add(btnG14);
      panel1.add(btnH14);
      panel1.add(btnI14);
      panel1.add(btnJ14);
      panel1.add(btnK14);
      panel1.add(btnL14);
      panel1.add(btnM14);
      panel1.add(btnN14);
      panel1.add(btnO14);

      panel1.add(btnA15);
  		panel1.add(btnB15);
  		panel1.add(btnC15);
      panel1.add(btnD15);
      panel1.add(btnE15);
      panel1.add(btnF15);
      panel1.add(btnG15);
      panel1.add(btnH15);
      panel1.add(btnI15);
      panel1.add(btnJ15);
      panel1.add(btnK15);
      panel1.add(btnL15);
      panel1.add(btnM15);
      panel1.add(btnN15);
      panel1.add(btnO15);

	    this.add(panel1);
	    this.setVisible(true);

		// Start the game
		board = new BoardGame(15);
    comPlayer = new MctsPlayer(50000);

	}

  /*private void resetBoard(){
    for(int i = 0;i<9;i++){
      panel1.removeAll();
    }
    winner = false;
    currentPlayerMark = "X";
  }*/

	private JButton createButton(String buttonName)
	{
		JButton btn = new JButton();
		btn.setPreferredSize(new Dimension(25, 25));
		//Font f = new Font("Dialog", Font.PLAIN, 12); --used for normal game
    Font f = new Font(Font.SANS_SERIF,  Font.BOLD, 13);
    //Font f = new Font("Dialog", Font.PLAIN, 8);
		btn.setFont(f);
		btn.addActionListener(e -> btnClick(e, buttonName));
		return btn;
	}

	private void btnClick(ActionEvent e, String buttonName)
	{
		JButton btn = (JButton)e.getSource();
    //System.out.println(buttonName);
    if(btn.getText().equals("")){
      //System.out.println(btn.getText().equals(""));
  		btn.setText("X");
      //System.out.println(btn.getText());
      board.placeMark(buttonName,1);
      //System.out.println(buttonName);
      //board.printBoard();

      if (board.checkWinPlayer1() == true)
  		{
  			JOptionPane.showMessageDialog(null,
  				"You win!", "Game Over.",
  				JOptionPane.INFORMATION_MESSAGE);
  			resetGame();
  			return;
  		}

  		if (board.isBoardFull() == true)
  		{
  			JOptionPane.showMessageDialog(null,
  				"It's a draw!", "Game Over.",
  				JOptionPane.INFORMATION_MESSAGE);
  			resetGame();
  			return;
  		}
      String comMove = MctsPlayer.getBestMove(board);
      //System.out.print(comMove);
  		switch (comMove)
  		{
  			case "A1":
  				btnA1.setText("O");
          board.setBoardToken(0,0,2);
  				break;
  			case "A2":
  				btnA2.setText("O");
          board.setBoardToken(1,0,2);
  				break;
  			case "A3":
  				btnA3.setText("O");
          board.setBoardToken(2,0,2);
  				break;
        case "A4":
  				btnA4.setText("O");
          board.setBoardToken(3,0,2);
  				break;
        case "A5":
  				btnA5.setText("O");
          board.setBoardToken(4,0,2);
  				break;
        case "A6":
  				btnA6.setText("O");
          board.setBoardToken(5,0,2);
  				break;
        case "A7":
  				btnA7.setText("O");
          board.setBoardToken(6,0,2);
  				break;
        case "A8":
  				btnA8.setText("O");
          board.setBoardToken(7,0,2);
  				break;
        case "A9":
  				btnA9.setText("O");
          board.setBoardToken(8,0,2);
  				break;
        case "A10":
  				btnA10.setText("O");
          board.setBoardToken(9,0,2);
  				break;
        case "A11":
  				btnA11.setText("O");
          board.setBoardToken(10,0,2);
  				break;
        case "A12":
  				btnA12.setText("O");
          board.setBoardToken(11,0,2);
  				break;
        case "A13":
  				btnA13.setText("O");
          board.setBoardToken(12,0,2);
  				break;
        case "A14":
  				btnA14.setText("O");
          board.setBoardToken(13,0,2);
  				break;
        case "A15":
  				btnA15.setText("O");
          board.setBoardToken(14,0,2);
  				break;

  			case "B1":
  				btnB1.setText("O");
          board.setBoardToken(0,1,2);
  				break;
  			case "B2":
  				btnB2.setText("O");
          board.setBoardToken(1,1,2);
  				break;
  			case "B3":
  				btnB3.setText("O");
          board.setBoardToken(2,1,2);
  				break;
        case "B4":
  				btnB4.setText("O");
          board.setBoardToken(3,1,2);
  				break;
        case "B5":
  				btnB5.setText("O");
          board.setBoardToken(4,1,2);
  				break;
        case "B6":
  				btnB6.setText("O");
          board.setBoardToken(5,1,2);
  				break;
        case "B7":
  				btnB7.setText("O");
          board.setBoardToken(6,1,2);
  				break;
        case "B8":
  				btnB8.setText("O");
          board.setBoardToken(7,1,2);
  				break;
        case "B9":
  				btnB9.setText("O");
          board.setBoardToken(8,1,2);
  				break;
        case "B10":
  				btnB10.setText("O");
          board.setBoardToken(9,1,2);
  				break;
        case "B11":
  				btnB11.setText("O");
          board.setBoardToken(10,1,2);
  				break;
        case "B12":
  				btnB12.setText("O");
          board.setBoardToken(11,1,2);
  				break;
        case "B13":
  				btnB13.setText("O");
          board.setBoardToken(12,1,2);
  				break;
        case "B14":
          btnB14.setText("O");
          board.setBoardToken(13,1,2);
          break;
        case "B15":
          btnB15.setText("O");
          board.setBoardToken(14,1,2);
          break;

  			case "C1":
  				btnC1.setText("O");
          board.setBoardToken(0,2,2);
  				break;
  			case "C2":
  				btnC2.setText("O");
          board.setBoardToken(1,2,2);
  				break;
  			case "C3":
  				btnC3.setText("O");
          board.setBoardToken(2,2,2);
  				break;
        case "C4":
  				btnC4.setText("O");
          board.setBoardToken(3,2,2);
  				break;
        case "C5":
  				btnC5.setText("O");
          board.setBoardToken(4,2,2);
  				break;
        case "C6":
          btnC6.setText("O");
          board.setBoardToken(5,2,2);
          break;
        case "C7":
          btnC7.setText("O");
          board.setBoardToken(6,2,2);
          break;
        case "C8":
          btnC8.setText("O");
          board.setBoardToken(7,2,2);
          break;
        case "C9":
          btnC9.setText("O");
          board.setBoardToken(8,2,2);
          break;
        case "C10":
          btnC10.setText("O");
          board.setBoardToken(9,2,2);
          break;
        case "C11":
          btnC11.setText("O");
          board.setBoardToken(10,2,2);
          break;
        case "C12":
          btnC12.setText("O");
          board.setBoardToken(11,2,2);
          break;
        case "C13":
          btnC13.setText("O");
          board.setBoardToken(12,2,2);
          break;
        case "C14":
          btnC14.setText("O");
          board.setBoardToken(13,2,2);
          break;
        case "C15":
          btnC15.setText("O");
          board.setBoardToken(14,2,2);
          break;

        case "D1":
          btnD1.setText("O");
          board.setBoardToken(0,3,2);
          break;
        case "D2":
          btnD2.setText("O");
          board.setBoardToken(1,3,2);
          break;
        case "D3":
          btnD3.setText("O");
          board.setBoardToken(2,3,2);
          break;
        case "D4":
          btnD4.setText("O");
          board.setBoardToken(3,3,2);
          break;
        case "D5":
          btnD5.setText("O");
          board.setBoardToken(4,3,2);
          break;
        case "D6":
          btnD6.setText("O");
          board.setBoardToken(5,3,2);
          break;
        case "D7":
          btnD7.setText("O");
          board.setBoardToken(6,3,2);
          break;
        case "D8":
          btnD8.setText("O");
          board.setBoardToken(7,3,2);
          break;
        case "D9":
          btnD9.setText("O");
          board.setBoardToken(8,3,2);
          break;
        case "D10":
          btnD10.setText("O");
          board.setBoardToken(9,3,2);
          break;
        case "D11":
          btnD11.setText("O");
          board.setBoardToken(10,3,2);
          break;
        case "D12":
          btnD12.setText("O");
          board.setBoardToken(11,3,2);
          break;
        case "D13":
          btnD13.setText("O");
          board.setBoardToken(12,3,2);
          break;
        case "D14":
          btnD14.setText("O");
          board.setBoardToken(13,3,2);
          break;
        case "D15":
          btnD15.setText("O");
          board.setBoardToken(14,3,2);
          break;

        case "E1":
          btnE1.setText("O");
          board.setBoardToken(0,4,2);
          break;
        case "E2":
          btnE2.setText("O");
          board.setBoardToken(1,4,2);
          break;
        case "E3":
          btnE3.setText("O");
          board.setBoardToken(2,4,2);
          break;
        case "E4":
          btnE4.setText("O");
          board.setBoardToken(3,4,2);
          break;
        case "E5":
          btnE5.setText("O");
          board.setBoardToken(4,4,2);
          break;
        case "E6":
          btnE6.setText("O");
          board.setBoardToken(5,4,2);
          break;
        case "E7":
          btnE7.setText("O");
          board.setBoardToken(6,4,2);
          break;
        case "E8":
          btnE8.setText("O");
          board.setBoardToken(7,4,2);
          break;
        case "E9":
          btnE9.setText("O");
          board.setBoardToken(8,4,2);
          break;
        case "E10":
          btnE10.setText("O");
          board.setBoardToken(9,4,2);
          break;
        case "E11":
          btnE11.setText("O");
          board.setBoardToken(10,4,2);
          break;
        case "E12":
          btnE12.setText("O");
          board.setBoardToken(11,4,2);
          break;
        case "E13":
          btnE13.setText("O");
          board.setBoardToken(12,4,2);
          break;
        case "E14":
          btnE14.setText("O");
          board.setBoardToken(13,4,2);
          break;
        case "E15":
          btnE15.setText("O");
          board.setBoardToken(14,4,2);
          break;

        case "F1":
          btnF1.setText("O");
          board.setBoardToken(0,5,2);
          break;
        case "F2":
          btnF2.setText("O");
          board.setBoardToken(1,5,2);
          break;
        case "F3":
          btnF3.setText("O");
          board.setBoardToken(2,5,2);
          break;
        case "F4":
          btnF4.setText("O");
          board.setBoardToken(3,5,2);
          break;
        case "F5":
          btnF5.setText("O");
          board.setBoardToken(4,5,2);
          break;
        case "F6":
          btnF6.setText("O");
          board.setBoardToken(5,5,2);
          break;
        case "F7":
          btnF7.setText("O");
          board.setBoardToken(6,5,2);
          break;
        case "F8":
          btnF8.setText("O");
          board.setBoardToken(7,5,2);
          break;
        case "F9":
          btnF9.setText("O");
          board.setBoardToken(8,5,2);
          break;
        case "F10":
          btnF10.setText("O");
          board.setBoardToken(9,5,2);
          break;
        case "F11":
          btnF11.setText("O");
          board.setBoardToken(10,5,2);
          break;
        case "F12":
          btnF12.setText("O");
          board.setBoardToken(11,5,2);
          break;
        case "F13":
          btnF13.setText("O");
          board.setBoardToken(12,5,2);
          break;
        case "F14":
          btnF14.setText("O");
          board.setBoardToken(13,5,2);
          break;
        case "F15":
          btnF15.setText("O");
          board.setBoardToken(14,5,2);
          break;

        case "G1":
          btnG1.setText("O");
          board.setBoardToken(0,6,2);
          break;
        case "G2":
          btnG2.setText("O");
          board.setBoardToken(1,6,2);
          break;
        case "G3":
          btnG3.setText("O");
          board.setBoardToken(2,6,2);
          break;
        case "G4":
          btnG4.setText("O");
          board.setBoardToken(3,6,2);
          break;
        case "G5":
          btnG5.setText("O");
          board.setBoardToken(4,6,2);
          break;
        case "G6":
          btnG6.setText("O");
          board.setBoardToken(5,6,2);
          break;
        case "G7":
          btnG7.setText("O");
          board.setBoardToken(6,6,2);
          break;
        case "G8":
          btnG8.setText("O");
          board.setBoardToken(7,6,2);
          break;
        case "G9":
          btnG9.setText("O");
          board.setBoardToken(8,6,2);
          break;
        case "G10":
          btnG10.setText("O");
          board.setBoardToken(9,6,2);
          break;
        case "G11":
          btnG11.setText("O");
          board.setBoardToken(10,6,2);
          break;
        case "G12":
          btnG12.setText("O");
          board.setBoardToken(11,6,2);
          break;
        case "G13":
          btnG13.setText("O");
          board.setBoardToken(12,6,2);
          break;
        case "G14":
          btnG14.setText("O");
          board.setBoardToken(13,6,2);
          break;
        case "G15":
          btnG15.setText("O");
          board.setBoardToken(14,6,2);
          break;

        case "H1":
          btnH1.setText("O");
          board.setBoardToken(0,7,2);
          break;
        case "H2":
          btnH2.setText("O");
          board.setBoardToken(1,7,2);
          break;
        case "H3":
          btnH3.setText("O");
          board.setBoardToken(2,7,2);
          break;
        case "H4":
          btnH4.setText("O");
          board.setBoardToken(3,7,2);
          break;
        case "H5":
          btnH5.setText("O");
          board.setBoardToken(4,7,2);
          break;
        case "H6":
          btnH6.setText("O");
          board.setBoardToken(5,7,2);
          break;
        case "H7":
          btnH7.setText("O");
          board.setBoardToken(6,7,2);
          break;
        case "H8":
          btnH8.setText("O");
          board.setBoardToken(7,7,2);
          break;
        case "H9":
          btnH9.setText("O");
          board.setBoardToken(8,7,2);
          break;
        case "H10":
          btnH10.setText("O");
          board.setBoardToken(9,7,2);
          break;
        case "H11":
          btnH11.setText("O");
          board.setBoardToken(10,7,2);
          break;
        case "H12":
          btnH12.setText("O");
          board.setBoardToken(11,7,2);
          break;
        case "H13":
          btnH13.setText("O");
          board.setBoardToken(12,7,2);
          break;
        case "H14":
          btnH14.setText("O");
          board.setBoardToken(13,7,2);
          break;
        case "H15":
          btnH15.setText("O");
          board.setBoardToken(14,7,2);
          break;

        case "I1":
          btnI1.setText("O");
          board.setBoardToken(0,8,2);
          break;
        case "I2":
          btnI2.setText("O");
          board.setBoardToken(1,8,2);
          break;
        case "I3":
          btnI3.setText("O");
          board.setBoardToken(2,8,2);
          break;
        case "I4":
          btnI4.setText("O");
          board.setBoardToken(3,8,2);
          break;
        case "I5":
          btnI5.setText("O");
          board.setBoardToken(4,8,2);
          break;
        case "I6":
          btnI6.setText("O");
          board.setBoardToken(5,8,2);
          break;
        case "I7":
          btnI7.setText("O");
          board.setBoardToken(6,8,2);
          break;
        case "I8":
          btnI8.setText("O");
          board.setBoardToken(7,8,2);
          break;
        case "I9":
          btnI9.setText("O");
          board.setBoardToken(8,8,2);
          break;
        case "I10":
          btnI10.setText("O");
          board.setBoardToken(9,8,2);
          break;
        case "I11":
          btnI11.setText("O");
          board.setBoardToken(10,8,2);
          break;
        case "I12":
          btnI12.setText("O");
          board.setBoardToken(11,8,2);
          break;
        case "I13":
          btnI13.setText("O");
          board.setBoardToken(12,8,2);
          break;
        case "I14":
          btnI14.setText("O");
          board.setBoardToken(13,8,2);
          break;
        case "I15":
          btnI15.setText("O");
          board.setBoardToken(14,8,2);
          break;

        case "J1":
          btnJ1.setText("O");
          board.setBoardToken(0,9,2);
          break;
        case "J2":
          btnJ2.setText("O");
          board.setBoardToken(1,9,2);
          break;
        case "J3":
          btnJ3.setText("O");
          board.setBoardToken(2,9,2);
          break;
        case "J4":
          btnJ4.setText("O");
          board.setBoardToken(3,9,2);
          break;
        case "J5":
          btnJ5.setText("O");
          board.setBoardToken(4,9,2);
          break;
        case "J6":
          btnJ6.setText("O");
          board.setBoardToken(5,9,2);
          break;
        case "J7":
          btnJ7.setText("O");
          board.setBoardToken(6,9,2);
          break;
        case "J8":
          btnJ8.setText("O");
          board.setBoardToken(7,9,2);
          break;
        case "J9":
          btnJ9.setText("O");
          board.setBoardToken(8,9,2);
          break;
        case "J10":
          btnJ10.setText("O");
          board.setBoardToken(9,9,2);
          break;
        case "J11":
          btnJ11.setText("O");
          board.setBoardToken(10,9,2);
          break;
        case "J12":
          btnJ12.setText("O");
          board.setBoardToken(11,9,2);
          break;
        case "J13":
          btnJ13.setText("O");
          board.setBoardToken(12,9,2);
          break;
        case "J14":
          btnJ14.setText("O");
          board.setBoardToken(13,9,2);
          break;
        case "J15":
          btnJ15.setText("O");
          board.setBoardToken(14,9,2);
          break;

        case "K1":
          btnK1.setText("O");
          board.setBoardToken(0,10,2);
          break;
        case "K2":
          btnK2.setText("O");
          board.setBoardToken(1,10,2);
          break;
        case "K3":
          btnK3.setText("O");
          board.setBoardToken(2,10,2);
          break;
        case "K4":
          btnK4.setText("O");
          board.setBoardToken(3,10,2);
          break;
        case "K5":
          btnK5.setText("O");
          board.setBoardToken(4,10,2);
          break;
        case "K6":
          btnK6.setText("O");
          board.setBoardToken(5,10,2);
          break;
        case "K7":
          btnK7.setText("O");
          board.setBoardToken(6,10,2);
          break;
        case "K8":
          btnK8.setText("O");
          board.setBoardToken(7,10,2);
          break;
        case "K9":
          btnK9.setText("O");
          board.setBoardToken(8,10,2);
          break;
        case "K10":
          btnK10.setText("O");
          board.setBoardToken(9,10,2);
          break;
        case "K11":
          btnK11.setText("O");
          board.setBoardToken(10,10,2);
          break;
        case "K12":
          btnK12.setText("O");
          board.setBoardToken(11,10,2);
          break;
        case "K13":
          btnK13.setText("O");
          board.setBoardToken(12,10,2);
          break;
        case "K14":
          btnK14.setText("O");
          board.setBoardToken(13,10,2);
          break;
        case "K15":
          btnK15.setText("O");
          board.setBoardToken(14,10,2);
          break;

        case "L1":
          btnL1.setText("O");
          board.setBoardToken(0,11,2);
          break;
        case "L2":
          btnL2.setText("O");
          board.setBoardToken(1,11,2);
          break;
        case "L3":
          btnL3.setText("O");
          board.setBoardToken(2,11,2);
          break;
        case "L4":
          btnL4.setText("O");
          board.setBoardToken(3,11,2);
          break;
        case "L5":
          btnL5.setText("O");
          board.setBoardToken(4,11,2);
          break;
        case "L6":
          btnL6.setText("O");
          board.setBoardToken(5,11,2);
          break;
        case "L7":
          btnL7.setText("O");
          board.setBoardToken(6,11,2);
          break;
        case "L8":
          btnL8.setText("O");
          board.setBoardToken(7,11,2);
          break;
        case "L9":
          btnL9.setText("O");
          board.setBoardToken(8,11,2);
          break;
        case "L10":
          btnL10.setText("O");
          board.setBoardToken(9,11,2);
          break;
        case "L11":
          btnL11.setText("O");
          board.setBoardToken(10,11,2);
          break;
        case "L12":
          btnL12.setText("O");
          board.setBoardToken(11,11,2);
          break;
        case "L13":
          btnL13.setText("O");
          board.setBoardToken(12,11,2);
          break;
        case "L14":
          btnL14.setText("O");
          board.setBoardToken(13,11,2);
          break;
        case "L15":
          btnL15.setText("O");
          board.setBoardToken(14,11,2);
          break;

        case "M1":
          btnM1.setText("O");
          board.setBoardToken(0,12,2);
          break;
        case "M2":
          btnM2.setText("O");
          board.setBoardToken(1,12,2);
          break;
        case "M3":
          btnM3.setText("O");
          board.setBoardToken(2,12,2);
          break;
        case "M4":
          btnM4.setText("O");
          board.setBoardToken(3,12,2);
          break;
        case "M5":
          btnM5.setText("O");
          board.setBoardToken(4,12,2);
          break;
        case "M6":
          btnM6.setText("O");
          board.setBoardToken(5,12,2);
          break;
        case "M7":
          btnM7.setText("O");
          board.setBoardToken(6,12,2);
          break;
        case "M8":
          btnM8.setText("O");
          board.setBoardToken(7,12,2);
          break;
        case "M9":
          btnM9.setText("O");
          board.setBoardToken(8,12,2);
          break;
        case "M10":
          btnM10.setText("O");
          board.setBoardToken(9,12,2);
          break;
        case "M11":
          btnM11.setText("O");
          board.setBoardToken(10,12,2);
          break;
        case "M12":
          btnM12.setText("O");
          board.setBoardToken(11,12,2);
          break;
        case "M13":
          btnM13.setText("O");
          board.setBoardToken(12,12,2);
          break;
        case "M14":
          btnM14.setText("O");
          board.setBoardToken(13,12,2);
          break;
        case "M15":
          btnM15.setText("O");
          board.setBoardToken(14,12,2);
          break;

        case "N1":
          btnN1.setText("O");
          board.setBoardToken(0,13,2);
          break;
        case "N2":
          btnN2.setText("O");
          board.setBoardToken(1,13,2);
          break;
        case "N3":
          btnN3.setText("O");
          board.setBoardToken(2,13,2);
          break;
        case "N4":
          btnN4.setText("O");
          board.setBoardToken(3,13,2);
          break;
        case "N5":
          btnN5.setText("O");
          board.setBoardToken(4,13,2);
          break;
        case "N6":
          btnN6.setText("O");
          board.setBoardToken(5,13,2);
          break;
        case "N7":
          btnN7.setText("O");
          board.setBoardToken(6,13,2);
          break;
        case "N8":
          btnN8.setText("O");
          board.setBoardToken(7,13,2);
          break;
        case "N9":
          btnN9.setText("O");
          board.setBoardToken(8,13,2);
          break;
        case "N10":
          btnN10.setText("O");
          board.setBoardToken(9,13,2);
          break;
        case "N11":
          btnN11.setText("O");
          board.setBoardToken(10,13,2);
          break;
        case "N12":
          btnN12.setText("O");
          board.setBoardToken(11,13,2);
          break;
        case "N13":
          btnN13.setText("O");
          board.setBoardToken(12,13,2);
          break;
        case "N14":
          btnN14.setText("O");
          board.setBoardToken(13,13,2);
          break;
        case "N15":
          btnN15.setText("O");
          board.setBoardToken(14,13,2);
          break;

        case "O1":
          btnO1.setText("O");
          board.setBoardToken(0,14,2);
          break;
        case "O2":
          btnO2.setText("O");
          board.setBoardToken(1,14,2);
          break;
        case "O3":
          btnO3.setText("O");
          board.setBoardToken(2,14,2);
          break;
        case "O4":
          btnO4.setText("O");
          board.setBoardToken(3,14,2);
          break;
        case "O5":
          btnO5.setText("O");
          board.setBoardToken(4,14,2);
          break;
        case "O6":
          btnO6.setText("O");
          board.setBoardToken(5,14,2);
          break;
        case "O7":
          btnO7.setText("O");
          board.setBoardToken(6,14,2);
          break;
        case "O8":
          btnO8.setText("O");
          board.setBoardToken(7,14,2);
          break;
        case "O9":
          btnO9.setText("O");
          board.setBoardToken(8,14,2);
          break;
        case "O10":
          btnO10.setText("O");
          board.setBoardToken(9,14,2);
          break;
        case "O11":
          btnO11.setText("O");
          board.setBoardToken(10,14,2);
          break;
        case "O12":
          btnO12.setText("O");
          board.setBoardToken(11,14,2);
          break;
        case "O13":
          btnO13.setText("O");
          board.setBoardToken(12,14,2);
          break;
        case "O14":
          btnO14.setText("O");
          board.setBoardToken(13,14,2);
          break;
        case "O15":
          btnO15.setText("O");
          board.setBoardToken(14,14,2);
          break;


        case "0":
  				break;
  		}
  		if (board.checkWinPlayer2() == true)
  		{
  			JOptionPane.showMessageDialog(null,
  				"Computer Wins!", "Game Over.",
  				JOptionPane.INFORMATION_MESSAGE);
  			resetGame();
  			return;
  		}
      if (board.isBoardFull() == true)
  		{
  			JOptionPane.showMessageDialog(null,
  				"It's a draw!", "Game Over.",
  				JOptionPane.INFORMATION_MESSAGE);
  			resetGame();
  			return;
  		}
    }
    else{
      JOptionPane.showMessageDialog(null,
        "That position is already occupied.", "Please pick another position.",
        JOptionPane.INFORMATION_MESSAGE);
      return;
    }
}

	private void resetGame()
	{
		board.initializeBoard();
		btnA1.setText("");
		btnA2.setText("");
		btnA3.setText("");
    btnA4.setText("");
    btnA5.setText("");
    btnA6.setText("");
    btnA7.setText("");
    btnA8.setText("");
    btnA9.setText("");
    btnA10.setText("");
    btnA11.setText("");
    btnA12.setText("");
    btnA13.setText("");
    btnA14.setText("");
    btnA15.setText("");

		btnB1.setText("");
		btnB2.setText("");
		btnB3.setText("");
    btnB4.setText("");
    btnB5.setText("");
    btnB6.setText("");
    btnB7.setText("");
    btnB8.setText("");
    btnB9.setText("");
    btnB10.setText("");
    btnB11.setText("");
    btnB12.setText("");
    btnB13.setText("");
    btnB14.setText("");
    btnB15.setText("");

		btnC1.setText("");
		btnC2.setText("");
		btnC3.setText("");
    btnC4.setText("");
    btnC5.setText("");
    btnC6.setText("");
    btnC7.setText("");
    btnC8.setText("");
    btnC9.setText("");
    btnC10.setText("");
    btnC11.setText("");
    btnC12.setText("");
    btnC13.setText("");
    btnC14.setText("");
    btnC15.setText("");

    btnD1.setText("");
    btnD2.setText("");
    btnD3.setText("");
    btnD4.setText("");
    btnD5.setText("");
    btnD6.setText("");
    btnD7.setText("");
    btnD8.setText("");
    btnD9.setText("");
    btnD10.setText("");
    btnD11.setText("");
    btnD12.setText("");
    btnD13.setText("");
    btnD14.setText("");
    btnD15.setText("");

    btnE1.setText("");
    btnE2.setText("");
    btnE3.setText("");
    btnE4.setText("");
    btnE5.setText("");
    btnE6.setText("");
    btnE7.setText("");
    btnE8.setText("");
    btnE9.setText("");
    btnE10.setText("");
    btnE11.setText("");
    btnE12.setText("");
    btnE13.setText("");
    btnE14.setText("");
    btnE15.setText("");

    btnF1.setText("");
    btnF2.setText("");
    btnF3.setText("");
    btnF4.setText("");
    btnF5.setText("");
    btnF6.setText("");
    btnF7.setText("");
    btnF8.setText("");
    btnF9.setText("");
    btnF10.setText("");
    btnF11.setText("");
    btnF12.setText("");
    btnF13.setText("");
    btnF14.setText("");
    btnF15.setText("");

    btnG1.setText("");
    btnG2.setText("");
    btnG3.setText("");
    btnG4.setText("");
    btnG5.setText("");
    btnG6.setText("");
    btnG7.setText("");
    btnG8.setText("");
    btnG9.setText("");
    btnG10.setText("");
    btnG11.setText("");
    btnG12.setText("");
    btnG13.setText("");
    btnG14.setText("");
    btnG15.setText("");

    btnH1.setText("");
    btnH2.setText("");
    btnH3.setText("");
    btnH4.setText("");
    btnH5.setText("");
    btnH6.setText("");
    btnH7.setText("");
    btnH8.setText("");
    btnH9.setText("");
    btnH10.setText("");
    btnH11.setText("");
    btnH12.setText("");
    btnH13.setText("");
    btnH14.setText("");
    btnH15.setText("");

    btnI1.setText("");
    btnI2.setText("");
    btnI3.setText("");
    btnI4.setText("");
    btnI5.setText("");
    btnI6.setText("");
    btnI7.setText("");
    btnI8.setText("");
    btnI9.setText("");
    btnI10.setText("");
    btnI11.setText("");
    btnI12.setText("");
    btnI13.setText("");
    btnI14.setText("");
    btnI15.setText("");

    btnJ1.setText("");
    btnJ2.setText("");
    btnJ3.setText("");
    btnJ4.setText("");
    btnJ5.setText("");
    btnJ6.setText("");
    btnJ7.setText("");
    btnJ8.setText("");
    btnJ9.setText("");
    btnJ10.setText("");
    btnJ11.setText("");
    btnJ12.setText("");
    btnJ13.setText("");
    btnJ14.setText("");
    btnJ15.setText("");

    btnK1.setText("");
    btnK2.setText("");
    btnK3.setText("");
    btnK4.setText("");
    btnK5.setText("");
    btnK6.setText("");
    btnK7.setText("");
    btnK8.setText("");
    btnK9.setText("");
    btnK10.setText("");
    btnK11.setText("");
    btnK12.setText("");
    btnK13.setText("");
    btnK14.setText("");
    btnK15.setText("");

    btnL1.setText("");
    btnL2.setText("");
    btnL3.setText("");
    btnL4.setText("");
    btnL5.setText("");
    btnL6.setText("");
    btnL7.setText("");
    btnL8.setText("");
    btnL9.setText("");
    btnL10.setText("");
    btnL11.setText("");
    btnL12.setText("");
    btnL13.setText("");
    btnL14.setText("");
    btnL15.setText("");

    btnM1.setText("");
    btnM2.setText("");
    btnM3.setText("");
    btnM4.setText("");
    btnM5.setText("");
    btnM6.setText("");
    btnM7.setText("");
    btnM8.setText("");
    btnM9.setText("");
    btnM10.setText("");
    btnM11.setText("");
    btnM12.setText("");
    btnM13.setText("");
    btnM14.setText("");
    btnM15.setText("");

    btnN1.setText("");
    btnN2.setText("");
    btnN3.setText("");
    btnN4.setText("");
    btnN5.setText("");
    btnN6.setText("");
    btnN7.setText("");
    btnN8.setText("");
    btnN9.setText("");
    btnN10.setText("");
    btnN11.setText("");
    btnN12.setText("");
    btnN13.setText("");
    btnN14.setText("");
    btnN15.setText("");

    btnO1.setText("");
    btnO2.setText("");
    btnO3.setText("");
    btnO4.setText("");
    btnO5.setText("");
    btnO6.setText("");
    btnO7.setText("");
    btnO8.setText("");
    btnO9.setText("");
    btnO10.setText("");
    btnO11.setText("");
    btnO12.setText("");
    btnO13.setText("");
    btnO14.setText("");
    btnO15.setText("");


	}
}
