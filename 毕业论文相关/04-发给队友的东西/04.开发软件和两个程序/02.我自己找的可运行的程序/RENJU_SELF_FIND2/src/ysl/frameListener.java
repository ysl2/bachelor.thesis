package ysl;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Color;
 
//实现对GoBangframe下棋界面的监听接口处理
public class frameListener implements GoBangconfig,MouseListener{
	public GoBangframe gf;
	public int turn=1;//判断当前轮到谁了，1表示黑方，2表示白方
	
	public void setGraphics(GoBangframe gf) {
		this.gf=gf;
	}
	
	
	  public void mouseClicked(java.awt.event.MouseEvent e) {
		  int x=e.getX();
		  int y=e.getY();
		  //计算棋子要落在棋盘的哪个交叉点上
		  int countx=(x/40)*40+20;
		  int county=(y/40)*40+20;
		  Graphics g=gf.getGraphics();
		  
		  if(gf.isAvail[(countx-20)/40][(county-20)/40]!=0) {
			  System.out.println("此处已经有棋子了，请下在其它地方");
		  }
		  else {
			  //当前位置可以落子，先计算棋盘上棋子在数组中相应的位置
			  int colu=(countx-20)/40;
			  int ro=(county-20)/40;
			  
			  if(turn==1) {
				  //先设置颜色
				  g.setColor(Color.black);
				  //落子
				  g.fillOval(countx-size/2, county-size/2, size, size);
				  //设置当前位置已经有棋子了,棋子为黑子
				  gf.isAvail[colu][ro]=1;
				  turn++;
			  }
			  else {
				  g.setColor(Color.white);
				  g.fillOval(countx-size/2, county-size/2, size, size);
				  //设置当前位置已经有棋子了，棋子为白子
 
				  gf.isAvail[colu][ro]=2;
				  turn--;
			  }
		  }
	  }
	  
	  
	  // Method descriptor #8 (Ljava/awt/event/MouseEvent;)V
	  public void mousePressed(java.awt.event.MouseEvent e) {
		  
	  }
	  
	  // Method descriptor #8 (Ljava/awt/event/MouseEvent;)V
	  public void mouseReleased(java.awt.event.MouseEvent e) {
		  
	  }
	  
	  // Method descriptor #8 (Ljava/awt/event/MouseEvent;)V
	  public void mouseEntered(java.awt.event.MouseEvent e) {
		  
	  }
	  
	  // Method descriptor #8 (Ljava/awt/event/MouseEvent;)V
	  public void mouseExited(java.awt.event.MouseEvent e) {
		  
	  }
}
