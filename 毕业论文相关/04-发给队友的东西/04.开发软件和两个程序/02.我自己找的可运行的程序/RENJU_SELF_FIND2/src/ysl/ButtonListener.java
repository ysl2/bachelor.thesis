package ysl;

//设置按钮监听方法ButttonLitener类
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//实现对JPanel的监听接口处理
public class ButtonListener implements GoBangconfig,ActionListener{
	public GoBangframe gf;
	
	public ButtonListener(GoBangframe gf) {
		this.gf=gf;//获取左半部分的画板
	}
	
	//当界面发生操作时进行处理
	public void actionPerformed(ActionEvent e) {
		//获取当前被点击按钮的内容，判断是不是"开始新游戏"这个按钮
		if(e.getActionCommand().equals("开始新游戏")) {
			//如果是开始新游戏的按钮，再为左半部分设置监听方法
			frameListener fl=new frameListener();
			fl.setGraphics(gf);//获取画笔对象
			gf.addMouseListener(fl);
		}
	}
	
}