//�������������GoBangframe��

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
public class GoBangframe extends JPanel implements GoBangconfig{
	public Graphics g;//����һ֧����
	public int[][] isAvail=new int [COLUMN][ROW];//����һ����ά�������������̵��������
	public ArrayList<ChessPosition>ChessPositonList=new ArrayList<ChessPosition>();//����ÿһ�����������
	public int turn=0;//����0ʱ�޷�����
	public int ChooseType=0;//0��ʾ���˶�ս��1��ʾ�˻���ս��Ĭ�����˶�ս
	
	public static HashMap<String,Integer> map = new HashMap<String,Integer>();//���ò�ͬ�����������ӦȨֵ������
	static {
		
		//����ס
		map.put("01", 25);//��1��
		map.put("02", 22);//��1��
		map.put("001", 17);//��1��
		map.put("002", 12);//��1��
		map.put("0001", 17);//��1��
		map.put("0002", 12);//��1��
		
		map.put("0102",25);//��1����15
		map.put("0201",22);//��1����10
		map.put("0012",15);//��1����15
		map.put("0021",10);//��1����10
		map.put("01002",25);//��1����15
		map.put("02001",22);//��1����10
		map.put("00102",17);//��1����15
		map.put("00201",12);//��1����10
		map.put("00012",15);//��1����15
		map.put("00021",10);//��1����10

		map.put("01000",25);//��1����15
		map.put("02000",22);//��1����10
		map.put("00100",19);//��1����15
		map.put("00200",14);//��1����10
		map.put("00010",17);//��1����15
		map.put("00020",12);//��1����10
		map.put("00001",15);//��1����15
		map.put("00002",10);//��1����10

		//��ǽ��ס
		map.put("0101",65);//��2����40
		map.put("0202",60);//��2����30
		map.put("0110",80);//��2����40
		map.put("0220",76);//��2����30
		map.put("011",80);//��2����40
		map.put("022",76);//��2����30
		map.put("0011",65);//��2����40
		map.put("0022",60);//��2����30
		
		map.put("01012",65);//��2����40
		map.put("02021",60);//��2����30
		map.put("01102",80);//��2����40
		map.put("02201",76);//��2����30
		map.put("01120",80);//��2����40
		map.put("02210",76);//��2����30
		map.put("00112",65);//��2����40
		map.put("00221",60);//��2����30

		map.put("01100",80);//��2����40
		map.put("02200",76);//��2����30
		map.put("01010",75);//��2����40
		map.put("02020",70);//��2����30
		map.put("00110",75);//��2����40
		map.put("00220",70);//��2����30
		map.put("00011",75);//��2����40
		map.put("00022",70);//��2����30
		
		//����ס
		map.put("0111",150);//��3����100
		map.put("0222",140);//��3����80
		
		map.put("01112",150);//��3����100
		map.put("02221",140);//��3����80
	
		map.put("01110", 1100);//��3��
		map.put("02220", 1050);//��3��
		map.put("01101",1000);//��3����130
		map.put("02202",800);//��3����110
		map.put("01011",1000);//��3����130
		map.put("02022",800);//��3����110
		
		map.put("01111",3000);//4����300
		map.put("02222",3500);//4����280
	}
	public int[][] weightArray=new int[COLUMN][ROW];//����һ����ά���飬����������Ȩֵ
	
	public void initUI() {
		//��ʼ��һ������,�����ñ����С������
		JFrame jf=new JFrame();
		jf.setTitle("������");
		jf.setSize(UIWIDTH,UIHIGHTH);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(3);		
		jf.setLayout(new BorderLayout());//���ö�������JFrameΪ��ܲ���
		
		//ʵ����ߵĽ��棬��GoBangframe�Ķ�����ӵ���ܲ��ֵ��м䲿��
		//�Ѿ���һ��GoBangframe�����ˣ���ʾ��ǰ��Ķ�����this
		this.setBackground(Color.WHITE);//��������������ɫ
		//����Ļ�ֱ�Ӱ���ߵĻ��������ȥ��ָ�����ڿ�ܲ��ֵ��м���
		//����������������һЩС����
		jf.add(this,BorderLayout.CENTER);//��ӵ���ܲ��ֵ��м䲿��
		
		//ʵ���ұߵ�JPanel��������
		JPanel jp=new JPanel();
		jp.setPreferredSize(dim1);//����JPanel�Ĵ�С
		jp.setBackground(Color.LIGHT_GRAY);//�����ұߵĽ�����ɫΪ��ɫ
		jf.add(jp,BorderLayout.EAST);//��ӵ���ܲ��ֵĶ��߲���
		jp.setLayout(new FlowLayout());//����JPanelΪ��ʽ����
		
		//�༭�û���Ϣ��������ͷ���ǳƣ��Ա𣬵ȼ�
		//ImageIcon[] userPicture={USERPICTURE,USERNAME,USERSEX,USERLEVEL};
		String[] userMessage={"pic","�ǳƣ�Alexwym","�Ա���","�ȼ�������"};
		JLabel[] user =new JLabel[4];
		//���ñ���ͼƬ�Ĵ�С
		USERPICTURE.setImage(USERPICTURE.getImage().getScaledInstance(dim3.width, dim3.height,Image.SCALE_DEFAULT ));
		user[0]=new JLabel(USERPICTURE);
		user[0].setPreferredSize(dim3);
		jp.add(user[0]);
		
		for(int i=1;i<4;i++){
			user[i]=new JLabel(userMessage[i]);
			user[i].setPreferredSize(dim2);
			/*
			 *����setFont������TextField�ı���������Ϣ�������С
			 *textx1.setFont(new Font(Font.DIALOG,Font.PLAIN,30));
			 */	
			user[i].setFont(new Font(Font.MONOSPACED,Font.ITALIC,20));
			jp.add(user[i]);
		}
		
		
		//������������Ҫ�Ѱ�ť��������μӵ��Ǹ�JPanel����
		//���ð�ť����
		//String[] butname= {"","",""};
		String[] butname= {"��ʼ����Ϸ","����","����"};
		ImageIcon[] BackgroundPicture={STARTBUTTON,BACKBUTTON,LOSEBUTTON}; 
		JButton[] button=new JButton[3];
		
		//���ΰ�������ť�������ȥ
		for(int i=0;i<butname.length;i++) {
			BackgroundPicture[i].setImage(BackgroundPicture[i].getImage().getScaledInstance(dim4.width+20, dim4.height,Image.SCALE_DEFAULT ));
			button[i]=new JButton(butname[i],BackgroundPicture[i]);
			button[i].setPreferredSize(dim4);
			jp.add(button[i]);
		}
		
		//����ѡ�ť
		
		ImageIcon[] pic={BATTLEBUTTON1,BATTLEBUTTON2}; 
		//String[] boxname= {"���˶�ս","�˻���ս"};
		JComboBox box=new JComboBox(pic);
		box.setPreferredSize(dim4);
		pic[0].setImage(pic[0].getImage().getScaledInstance(dim4.width, dim4.height,Image.SCALE_DEFAULT ));
		pic[1].setImage(pic[1].getImage().getScaledInstance(dim4.width, dim4.height,Image.SCALE_DEFAULT ));
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
	
	public void PopUp(String top,String result) {
		JOptionPane jo=new JOptionPane();
		jo.showMessageDialog(null, result, top, JOptionPane.PLAIN_MESSAGE);
	}
	
	//��д�ػ淽��,������д���ǵ�һ�����JPanel�ķ���
	public void paint(Graphics g) {
		super.paint(g);//�����׿�
		//��ӱ���ͼƬ
		g.drawImage(CHESSBOARD, 0, 0,this.getWidth(), this.getHeight(), this);
		
		//�ػ������
		g.setColor(Color.black);
		for(int i=0;i<ROW;i++) {
			g.drawLine(X, Y+SIZE*i, X+SIZE*(COLUMN-1), Y+SIZE*i);
		}
		for(int j=0;j<COLUMN;j++) {
			g.drawLine(X+SIZE*j, Y, X+SIZE*j, Y+SIZE*(ROW-1));
		}
		
		//�ػ������
		for(int i=0;i<ROW;i++) {
			for(int j=0;j<COLUMN;j++) {
				if(isAvail[i][j]==1) {
					int countx=SIZE*j+SIZE/2;
					int county=SIZE*i+SIZE/2;
					g.drawImage(BLACKCHESS,countx-SIZE+X, county-SIZE/2, SIZE, SIZE,null);
				}
				else if(isAvail[i][j]==2) {
					int countx=SIZE*j+SIZE/2;
					int county=SIZE*i+SIZE/2;
					g.drawImage(WHITECHESS,countx-SIZE+X, county-SIZE/2, SIZE, SIZE,null);
				}
			}
		}
	}
	
}
