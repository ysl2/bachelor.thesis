package ysl;

//���ð�ť��������ButttonLitener��
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//ʵ�ֶ�JPanel�ļ����ӿڴ���
public class ButtonListener implements GoBangconfig,ActionListener{
	public GoBangframe gf;
	
	public ButtonListener(GoBangframe gf) {
		this.gf=gf;//��ȡ��벿�ֵĻ���
	}
	
	//�����淢������ʱ���д���
	public void actionPerformed(ActionEvent e) {
		//��ȡ��ǰ�������ť�����ݣ��ж��ǲ���"��ʼ����Ϸ"�����ť
		if(e.getActionCommand().equals("��ʼ����Ϸ")) {
			//����ǿ�ʼ����Ϸ�İ�ť����Ϊ��벿�����ü�������
			frameListener fl=new frameListener();
			fl.setGraphics(gf);//��ȡ���ʶ���
			gf.addMouseListener(fl);
		}
	}
	
}