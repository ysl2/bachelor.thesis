//���ð�ť��������ButttonLitener��
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JComboBox;
//ʵ�ֶ�JPanel�ļ����ӿڴ���
public class ButtonListener implements GoBangconfig,ActionListener{
	public GoBangframe gf;
	public JComboBox box;
	
	public ButtonListener(GoBangframe gf,JComboBox box) {
		this.gf=gf;//��ȡ��벿�ֵĻ���
		this.box=box;//��ȡ��ѡ�����
	}
	//�����淢������ʱ���д���
	public void actionPerformed(ActionEvent e) {
		
		//�������else if����Ϊ���û��else if��ÿ�����ұߵĽ�����ʱ�������ȡ���˶�ս�����˻���ս����Ϣ��ÿ�ζ���������������
		//��ȡ��ǰ�������ť�����ݣ��ж��ǲ���"��ʼ����Ϸ"�����ť
		if(e.getActionCommand().equals("��ʼ����Ϸ")) {
			//�ػ�����
		    for(int i=0;i<gf.isAvail.length;i++)
			   for(int j=0;j<gf.isAvail[i].length;j++)
			    	 gf.isAvail[i][j]=0;
		    gf.repaint();
			//����ǿ�ʼ����Ϸ�İ�ť����Ϊ��벿�����ü�������
			gf.turn=1;
		}
		//�жϵ�ǰ����İ�ť�ǲ��ǻ���
		else if(e.getActionCommand().equals("����")) {
			if(gf.ChessPositonList.size()>1) {
				//������������Ӧ��λ����Ϊ0��
				ChessPosition l=new ChessPosition();
				//��ȡ���һ�����ӵĶ�����Ϣ
				l=gf.ChessPositonList.remove(gf.ChessPositonList.size()-1);
				//����Ӧ������λ����Ϊ0
				gf.isAvail[l.Listi][l.Listj]=0;
				//����һ�ԭΪ��һ�������
				if(gf.turn==1) gf.turn++;
				else gf.turn--;
				
				//ֱ�ӵ���gf���ػ淽�����ػ淽���Ļ���Ӧ����������ҳ�滹û���ɵ�ʱ���Ҫ��ȡ
				//����repaint���Զ�����paint���������Ҳ��ø�����
				gf.repaint();
				//gf.paint(gf.getGraphics());

			}
			else {
				System.out.println("���ܻ���!");
			}
		}
		else if(e.getActionCommand().equals("����")) {
			if(gf.turn==1) System.out.println("�׷�Ӯ");
			else System.out.println("�ڷ�Ӯ");
		    //���°���������Ϊ���ɲ���
		    gf.turn=0;
		}
		else if(box.getSelectedItem().equals("�˻���ս")) {
			 gf.ChooseType=1;
			 gf.turn=0;
		}
		else if(box.getSelectedItem().equals("���˶�ս")){
			 gf.ChooseType=0;
			 gf.turn=0;
		}
	}
	
}
