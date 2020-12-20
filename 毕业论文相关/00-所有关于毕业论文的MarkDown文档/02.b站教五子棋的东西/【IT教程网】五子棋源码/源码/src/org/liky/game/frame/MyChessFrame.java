package org.liky.game.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class MyChessFrame extends JFrame implements MouseListener {
	public MyChessFrame() {
		this.setTitle("五子");
		this.setSize(1000, 700);
		this.setResizable(false) ;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		/*System.out.println("宽度为: "+width);
		System.out.println("高度为: "+height);*/
		this.setLocation((width - 200)/2, (height-100)/2);
		
		this.addMouseListener(this);
		
		this.setVisible(true);
	}
	
	public void paint(Graphics g) {
		/*g.drawString("五子棋游戏", 20, 40);*/
		/*g.drawOval(20, 40, 40, 40);*/
		/*g.fillOval(20, 40, 40, 40);*/
		/*g.drawLine(20, 40, 80, 40);*/
	/*	g.drawRect(20, 40, 40, 20);
		g.fillRect(80, 40, 40, 20);*/
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("E:/image/MLDN文字01.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(image,0,0,this);
		g.drawOval(20, 40, 40, 40);
		g.setColor(Color.BLUE);
		g.fillRect(80, 40, 40, 20);
		g.setFont(new Font("宋体",40,40));		
		g.drawString("五子棋游戏", 20, 100);
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
