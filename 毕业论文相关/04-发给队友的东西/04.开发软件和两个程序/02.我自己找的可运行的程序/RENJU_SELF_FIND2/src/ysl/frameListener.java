package ysl;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Color;
 
//ʵ�ֶ�GoBangframe�������ļ����ӿڴ���
public class frameListener implements GoBangconfig,MouseListener{
	public GoBangframe gf;
	public int turn=1;//�жϵ�ǰ�ֵ�˭�ˣ�1��ʾ�ڷ���2��ʾ�׷�
	
	public void setGraphics(GoBangframe gf) {
		this.gf=gf;
	}
	
	
	  public void mouseClicked(java.awt.event.MouseEvent e) {
		  int x=e.getX();
		  int y=e.getY();
		  //��������Ҫ�������̵��ĸ��������
		  int countx=(x/40)*40+20;
		  int county=(y/40)*40+20;
		  Graphics g=gf.getGraphics();
		  
		  if(gf.isAvail[(countx-20)/40][(county-20)/40]!=0) {
			  System.out.println("�˴��Ѿ��������ˣ������������ط�");
		  }
		  else {
			  //��ǰλ�ÿ������ӣ��ȼ�����������������������Ӧ��λ��
			  int colu=(countx-20)/40;
			  int ro=(county-20)/40;
			  
			  if(turn==1) {
				  //��������ɫ
				  g.setColor(Color.black);
				  //����
				  g.fillOval(countx-size/2, county-size/2, size, size);
				  //���õ�ǰλ���Ѿ���������,����Ϊ����
				  gf.isAvail[colu][ro]=1;
				  turn++;
			  }
			  else {
				  g.setColor(Color.white);
				  g.fillOval(countx-size/2, county-size/2, size, size);
				  //���õ�ǰλ���Ѿ��������ˣ�����Ϊ����
 
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
