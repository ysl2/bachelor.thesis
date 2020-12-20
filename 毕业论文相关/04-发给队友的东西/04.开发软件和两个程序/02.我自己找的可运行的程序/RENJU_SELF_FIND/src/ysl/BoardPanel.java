package ysl;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
 
import javax.swing.JOptionPane;
import javax.swing.JPanel;
 
 
public class BoardPanel extends JPanel {
	public static final int WIDTH=600;
	public static final int HEIGHT=600;
	private static int MARGIN=20;
	private FiveInARowModel board;//chessman board
	private ComputerPlayer computerPlayer;
	public BoardPanel(FiveInARowModel board){
		this.board=board;
		computerPlayer=new ComputerPlayer(board, FiveInARowModel.BLACK);
		this.setBackground(Color.WHITE);
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Point p=e.getPoint();
				int row,col;
				int dim=BoardPanel.this.board.getDimension();
				int cellWidth=(getWidth()-2*MARGIN)/dim;
				if(p.y>=MARGIN&&p.y<MARGIN+dim*cellWidth&&
						p.x>=MARGIN&&p.x<MARGIN+dim*cellWidth){
					row=(p.y-MARGIN)/cellWidth;
					col=(p.x-MARGIN)/cellWidth;
					if(!BoardPanel.this.board.isEmpty(row, col))
						return;
					try {
						//user put a chessman
						boolean win=BoardPanel.this.board.setChessman(row, col, FiveInARowModel.WHITE);
						BoardPanel.this.drawChessman(row, col);
						if(win){//user wins
							JOptionPane.showMessageDialog(BoardPanel.this, "You win!");
							BoardPanel.this.board.clear();
							BoardPanel.this.repaint();
						}else if(BoardPanel.this.board.isFull()){//full
							JOptionPane.showMessageDialog(BoardPanel.this, "No empty cell!");
							BoardPanel.this.board.clear();
							BoardPanel.this.repaint();
						}else{
							//computer put a chessman
							win=computerPlayer.play();
							int[]last=BoardPanel.this.board.getLastPut();
							row=last[0];
							col=last[1];
							BoardPanel.this.drawChessman(row, col);
							if(win){//computer wins
								JOptionPane.showMessageDialog(BoardPanel.this, "Computer win!");
								BoardPanel.this.board.clear();
								BoardPanel.this.repaint();
							}else if(BoardPanel.this.board.isFull()){
								JOptionPane.showMessageDialog(BoardPanel.this, "No empty cell!");
								BoardPanel.this.board.clear();
								BoardPanel.this.repaint();
							}
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}
	public int getWidth() {
		return WIDTH;
	}
	public int getHeight() {
		return HEIGHT;
	}
	public void paint(Graphics g) {
		Graphics2D g2d=(Graphics2D) g;
		//fill white back ground
		g2d.setColor(Color.CYAN);
		g2d.fillRect(0, 0, WIDTH, HEIGHT);
		//write tip
		g2d.setColor(Color.BLACK);
		g2d.drawString("Computer", MARGIN, MARGIN*2/3);
		FontMetrics fm=g2d.getFontMetrics();
		Rectangle2D rect=fm.getStringBounds("Computer", g2d);
		int r=MARGIN-2;
		g2d.drawOval(MARGIN+(int)rect.getWidth()+r, 0, r, r);
		g2d.fillOval(MARGIN+(int)rect.getWidth()+r, 0, r, r);
		
		g2d.drawString("User", WIDTH/2, MARGIN*2/3);
		rect=fm.getStringBounds("User", g2d);
		g2d.drawOval(WIDTH/2+(int)rect.getWidth()+r, 0, r, r);
		g2d.setColor(Color.WHITE);
		g2d.fillOval(WIDTH/2+(int)rect.getWidth()+r, 0, r, r);
		
		//draw background of board
		int cellWidth=(getWidth()-2*MARGIN)/board.getDimension();
		g2d.setColor(Color.GRAY);
		g2d.fillRect(MARGIN, MARGIN, 
				cellWidth*board.getDimension(), cellWidth*board.getDimension());
		//draw lines
		g2d.setColor(Color.BLACK);
		for(int k=0;k<board.getDimension()+1;++k){
			g2d.drawLine(MARGIN+k*cellWidth, MARGIN, 
					MARGIN+k*cellWidth, MARGIN+cellWidth*board.getDimension());
			g2d.drawLine(MARGIN, MARGIN+k*cellWidth, 
					MARGIN+cellWidth*board.getDimension(), MARGIN+k*cellWidth);
		}
		for(int k=0;k<board.getDimension();++k){
			for(int j=0;j<board.getDimension();++j)
				drawChessman(k, j);
		}
	}
	private void drawChessman(int row,int col){
		int chessman=board.getChessman(row, col);
		if(chessman!=FiveInARowModel.BLACK&&chessman!=FiveInARowModel.WHITE)
			return;
		int x,y;
		int cellWidth=(getWidth()-2*MARGIN)/board.getDimension();
		x=MARGIN+col*cellWidth;
		y=MARGIN+row*cellWidth;
		Graphics2D g2d=(Graphics2D) this.getGraphics();
		int pad=2;
		Color color=chessman==FiveInARowModel.BLACK?Color.BLACK:Color.WHITE;
		g2d.setColor(color);
		g2d.fillOval(x+pad, y+pad, cellWidth-2*pad, cellWidth-2*pad);
	}
}