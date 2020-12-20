//�������������GoBangframe��

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
public class GoBangframe extends JPanel implements GoBangconfig{
	public Graphics g;//����һ֧����
	public int[][] isAvail=new int [column][row];//����һ����ά�������������̵��������
	public ArrayList<ChessPosition>ChessPositonList=new ArrayList<ChessPosition>();//����ÿһ�����������
	public int turn=0;
	public int ChooseType=0;//0��ʾ���˶�ս��1��ʾ�˻���ս��Ĭ�����˶�ս
	
	public static HashMap<String,Integer> map = new HashMap<String,Integer>();//���ò�ͬ�����������ӦȨֵ������
	static {
		
		//����ס
		map.put("01", 17);//��1��
		map.put("02", 12);//��1��
		map.put("001", 17);//��1��
		map.put("002", 12);//��1��
		map.put("0001", 17);//��1��
		map.put("0002", 12);//��1��
		
		map.put("0102",17);//��1����15
		map.put("0201",12);//��1����10
		map.put("0012",15);//��1����15
		map.put("0021",10);//��1����10
		map.put("01002",19);//��1����15
		map.put("02001",14);//��1����10
		map.put("00102",17);//��1����15
		map.put("00201",12);//��1����10
		map.put("00012",15);//��1����15
		map.put("00021",10);//��1����10

		map.put("01000",21);//��1����15
		map.put("02000",16);//��1����10
		map.put("00100",19);//��1����15
		map.put("00200",14);//��1����10
		map.put("00010",17);//��1����15
		map.put("00020",12);//��1����10
		map.put("00001",15);//��1����15
		map.put("00002",10);//��1����10

		//����ס
		map.put("0101",65);//��2����40
		map.put("0202",60);//��2����30
		map.put("0110",65);//��2����40
		map.put("0220",60);//��2����30
		map.put("011",65);//��2����40
		map.put("022",60);//��2����30
		map.put("0011",65);//��2����40
		map.put("0022",60);//��2����30
		
		map.put("01012",65);//��2����40
		map.put("02021",60);//��2����30
		map.put("01102",65);//��2����40
		map.put("02201",60);//��2����30
		map.put("00112",65);//��2����40
		map.put("00221",60);//��2����30

		map.put("01010",75);//��2����40
		map.put("02020",70);//��2����30
		map.put("01100",75);//��2����40
		map.put("02200",70);//��2����30
		map.put("00110",75);//��2����40
		map.put("00220",70);//��2����30
		map.put("00011",75);//��2����40
		map.put("00022",70);//��2����30
		
		//����ס
		map.put("0111",150);//��3����100
		map.put("0222",140);//��3����80
		
		map.put("01112",150);//��3����100
		map.put("02221",140);//��3����80
		
		map.put("01101",1000);//��3����130
		map.put("02202",800);//��3����110
		map.put("01011",1000);//��3����130
		map.put("02022",800);//��3����110
		map.put("01110", 1000);//��3��
		map.put("02220", 800);//��3��
		
		map.put("01111",3000);//4����300
		map.put("02222",3500);//4����280
	}
	public int[][] weightArray=new int[column][row];//����һ����ά���飬����������Ȩֵ
	
	//���������
	public static void main(String args[]) {
		GoBangframe gf=new GoBangframe();//��ʼ��һ�����������Ķ���
		gf.initUI();//���÷������н���ĳ�ʼ��
	}
	
	public void initUI() {
		//��ʼ��һ������,�����ñ����С������
		JFrame jf=new JFrame();
		jf.setTitle("������");
		jf.setSize(800,650);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(3);
		
		jf.setLayout(new BorderLayout());//���ö�������JFrameΪ��ܲ���
		
		Dimension dim1=new Dimension(150,0);//�����Ұ벿�ֵĴ�С
		Dimension dim3=new Dimension(550,0);//������벿�ֵĴ�С
		Dimension dim2=new Dimension(140,40);//�����ұ߰�ť����Ĵ�С
		
		//ʵ����ߵĽ��棬��GoBangframe�Ķ�����ӵ���ܲ��ֵ��м䲿��
		//�Ѿ���һ��GoBangframe�����ˣ���ʾ��ǰ��Ķ�����this
		this.setPreferredSize(dim3);//�����������Ĵ�С
		this.setBackground(Color.LIGHT_GRAY);//��������������ɫ
		//����Ļ�ֱ�Ӱ���ߵĻ��������ȥ��ָ�����ڿ�ܲ��ֵ��м���
		//����������������һЩС����
		jf.add(this,BorderLayout.CENTER);//��ӵ���ܲ��ֵ��м䲿��
		
		//ʵ���ұߵ�JPanel��������
		JPanel jp=new JPanel();
		jp.setPreferredSize(dim1);//����JPanel�Ĵ�С
		jp.setBackground(Color.white);//�����ұߵĽ�����ɫΪ��ɫ
		jf.add(jp,BorderLayout.EAST);//��ӵ���ܲ��ֵĶ��߲���
		jp.setLayout(new FlowLayout());//����JPanelΪ��ʽ����
		
		//������������Ҫ�Ѱ�ť��������μӵ��Ǹ�JPanel����
		//���ð�ť����
		String[] butname= {"��ʼ����Ϸ","����","����"};
		JButton[] button=new JButton[3];
		
		//���ΰ�������ť�������ȥ
		for(int i=0;i<butname.length;i++) {
			button[i]=new JButton(butname[i]);
			button[i].setPreferredSize(dim2);
			jp.add(button[i]);
		}
		
		//����ѡ�ť
		String[] boxname= {"���˶�ս","�˻���ս"};
		JComboBox box=new JComboBox(boxname);
		jp.add(box);
		
		//��ť�����
		ButtonListener butListen=new ButtonListener(this,box);
		//��ÿһ����ť�����״̬�¼��ļ����������
		for(int i=0;i<butname.length;i++) {
			button[i].addActionListener(butListen);//��ӷ��������ļ�������
		}
		
		//�Կ�ѡ������¼���������
		box.addActionListener(butListen);
		
		frameListener fl=new frameListener();
		fl.setGraphics(this);//��ȡ���ʶ���
		this.addMouseListener(fl);
		
		jf.setVisible(true);
	}
	
	public void PopUp(String result) {
		JOptionPane jo=new JOptionPane();
		jo.showMessageDialog(null, result, "��Ϸ���", JOptionPane.PLAIN_MESSAGE);
	}
	
	//AI�����㷨����
	/*public Integer unionWeight(Integer a,Integer b ) {
		//һһ
		if((a>=10)&&(a<=25)&&(b>=10)&&(b<=25)) return 60;
		//һ������һ
		else if(((a>=10)&&(a<=25)&&(b>=60)&&(b<=80))||((a>=60)&&(a<=80)&&(b>=10)&&(b<=25))) return 800;
		//һ������һ������
		else if(((a>=10)&&(a<=25)&&(b>=140)&&(b<=1000))||((a>=140)&&(a<=1000)&&(b>=10)&&(b<=25))||((a>=60)&&(a<=80)&&(b>=60)&&(b<=80)))
			return 3000;
		//����������
		else if(((a>=60)&&(a<=80)&&(b>=140)&&(b<=1000))||((a>=140)&&(a<=1000)&&(b>=60)&&(b<=80))) return 3000;
		else return 0;
	}*/
	
	
	//��д�ػ淽��,������д���ǵ�һ�����JPanel�ķ���
	public void paint(Graphics g) {
		super.paint(g);//�����׿�
		
		//�ػ������
		g.setColor(Color.black);
		for(int i=0;i<row;i++) {
			g.drawLine(x, y+size*i, x+size*(column-1), y+size*i);
		}
		for(int j=0;j<column;j++) {
			g.drawLine(x+size*j, y, x+size*j, y+size*(row-1));
		}
		
		//�ػ������
		for(int i=0;i<row;i++) {
			for(int j=0;j<column;j++) {
				if(isAvail[i][j]==1) {
					int countx=size*j+20;
					int county=size*i+20;
					g.setColor(Color.black);
					g.fillOval(countx-size/2, county-size/2, size, size);
				}
				else if(isAvail[i][j]==2) {
					int countx=size*j+20;
					int county=size*i+20;
					g.setColor(Color.white);
					g.fillOval(countx-size/2, county-size/2, size, size);
				}
			}
		}
	}
	
}
