package ysl;

import java.awt.BorderLayout;
import javax.swing.JFrame;
 
 
public class MainFrame extends JFrame {
	public MainFrame() {
		FiveInARowModel board=new FiveInARowModel(19);
		BoardPanel panel=new BoardPanel(board);
		getContentPane().add(panel,BorderLayout.CENTER);
		setSize(panel.getWidth(),10+panel.getHeight());
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
 
}