package bwjiang;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;

public class DrawChessBoard {
	public static final int BLACKWIN = 1;
	public static final int WHITEWIN = 2;
	public static final int NOTWIN = 0;
	public static final int ALLFILLED = -1;
	public static final int BLACK = 1;
	public static final int NOCHESS = 0;
	public static final int WHITE = -1;
	public static final int WINLENGTH = 5;

	public BoardFrame boardFrame;
	public int rows = 9;
	JTextField rowsText;
	JLabel nowChessColor;

	public DrawChessBoard() {
		// ���̴���
		this.boardFrame = new BoardFrame();

		boardFrame.setVisible(true);
	}

	// ��������
	class BoardPanel extends JPanel implements MouseListener {
		public Image boardImage;// ���̱߿�

		public int lastChessColor = WHITE;
		// ��¼ȫ�����ӵ������࣬��һ��Ϊ�������ڶ���Ϊ����
		public int[][] chessmans = new int[rows][rows];
		public int[][] predictChessmans = new int[rows][rows];
		public int maxReward = 0;
		public int nextChessColor = BLACK;// ���ƽ�����Ȩ

		int FrameWidth;// ����
		int FrameHeight;
		int chessBoardX;// ���̱߿����Ͻ�
		int chessBoardY;
		int realChessBoardX;// �������ӵ����Ͻ�
		int realChessBoardY;
		int deltaX;// ���
		int deltaY;

		public int[][] getChessmans() {
			return this.chessmans;
		}

		// ���̱߿�
		public BoardPanel() {
			try {
				boardImage = ImageIO.read(new File("boardFrame.png"));
			} catch (IOException e) {
				System.out.print("error: boardImage not exist");
				return;
			}
			addMouseListener(this);
		}

		@Override
		// �����ڲ��Ļ���������
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int imageWidth = boardImage.getWidth(this);
			int imageHeight = boardImage.getHeight(this);
			FrameWidth = getWidth();
			FrameHeight = getHeight();
			// ��ͼƬ�����ڻ����м�
			chessBoardX = (FrameWidth - imageWidth) / 2;
			chessBoardY = (FrameHeight - imageHeight) / 2;
			g.drawImage(boardImage, chessBoardX, chessBoardY, null);
			// ����������
			int margin = 40;
			deltaX = (imageWidth - 2 * margin) / (rows - 1);
			deltaY = (imageHeight - 2 * margin) / (rows - 1);
			realChessBoardX = chessBoardX + (imageWidth - deltaX * (rows - 1)) / 2;
			realChessBoardY = chessBoardY + (imageHeight - deltaY * (rows - 1)) / 2;
			for (int i = 0; i < rows; i++) {// ����
				g.drawLine(realChessBoardX, realChessBoardY + deltaY * i, realChessBoardX + deltaX * (rows - 1),
						realChessBoardY + deltaY * i);
			}
			for (int i = 0; i < rows; i++) {// ����
				g.drawLine(realChessBoardX + deltaX * i, realChessBoardY, realChessBoardX + deltaX * i,
						realChessBoardY + deltaY * (rows - 1));
			}

			// ������
			int radius = (int) (deltaX * 0.3);
			for (int col_i = 0; col_i < rows; col_i++) {
				for (int row_j = 0; row_j < rows; row_j++) {
					if (chessmans[col_i][row_j] != NOCHESS) {
						if (chessmans[col_i][row_j] == WHITE) {
							g.setColor(Color.white);
							g.fillOval(realChessBoardX - radius + deltaX * col_i,
									realChessBoardY - radius + deltaY * row_j, 2 * radius, 2 * radius);
						} else if (chessmans[col_i][row_j] == BLACK) {
							g.setColor(Color.black);
							g.fillOval(realChessBoardX - radius + deltaX * col_i,
									realChessBoardY - radius + deltaY * row_j, 2 * radius, 2 * radius);
						}
					}
					if (predictChessmans[col_i][row_j] != 0) {
						if (predictChessmans[col_i][row_j] < 0) {
							predictChessmans[col_i][row_j] = 0;
						}
						System.out.println(predictChessmans[col_i][row_j] + "--" + maxReward);
						float pingfangcof = (predictChessmans[col_i][row_j] / (float) maxReward)
								* (predictChessmans[col_i][row_j] / (float) maxReward);
						System.out.println(pingfangcof);
						g.setColor(new Color(255, 0, 0, (int) (255 * pingfangcof)));
						g.fillOval(realChessBoardX - radius + deltaX * col_i, realChessBoardY - radius + deltaY * row_j,
								2 * radius, 2 * radius);
					}
				}
			}
			System.out.println("��������״̬");
		}

		@Override
		// ��������
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getButton() == MouseEvent.BUTTON1) {
				int x = e.getX(); // �õ����x����
				int y = e.getY(); // �õ����y����
				// �����Ƿ���������
				if (x > chessBoardX && x < FrameWidth - chessBoardX && y > chessBoardY
						&& y < FrameHeight - chessBoardY) {
					int col_i = (x - realChessBoardX + deltaX / 2) / deltaX;
					int row_j = (y - realChessBoardY + deltaY / 2) / deltaY;
					// ���鵱ǰλ���Ƿ�����
					if (chessmans[col_i][row_j] == NOCHESS) {
						if (nextChessColor == BLACK) {
							System.out.print("���� ");
							nowChessColor.setText("����");
							chessmans[col_i][row_j] = BLACK;
							nextChessColor = WHITE;
						} else if (nextChessColor == WHITE) {
							System.out.print("���� ");
							nowChessColor.setText("����");
							chessmans[col_i][row_j] = WHITE;
							nextChessColor = BLACK;
						}
						String banner = "��굱ǰ���λ�õ�������" + x + "," + y + "  ��Ӧ��������Ϊ" + col_i + "," + row_j + " ";
						System.out.print(banner);
						int isWin = checkTerminal(chessmans);
						System.out.print("isWin:" + isWin);
						repaint();

						//////////////////////////////////
						// ����MCTS�㷨
						// ������һ����¼ÿ��λ�����Ӹ��ʵľ���
						MCTS_01 MCTS = new MCTS_01(chessmans);
						System.out.println("���߲���" + MCTS.getProbabilityMap());
						int[][] tempMap = new int[rows][rows];
						tempMap = MCTS.getProbabilityMap();
						maxReward = 0;
						for (int i = 0; i < rows; i++) {
							for (int j = 0; j < rows; j++) {
								System.out.print("  " + -tempMap[i][j]);
								predictChessmans[i][j] = -tempMap[i][j];
								if (-tempMap[i][j] > maxReward) {
									maxReward = -tempMap[i][j];
								}

							}
							System.out.println();
						}
						//////////////////////////////////
					} else {
						System.out.println("��ǰλ������");
					}
				} else {
					System.out.println("���λ�÷Ƿ�");
				}
			}

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void refreshChessBoard() {
			if (Integer.parseInt(rowsText.getText()) > 30 || Integer.parseInt(rowsText.getText()) < 5) {
				System.out.println("����ά�ȴ���");
				return;
			}
			for (int row_i = 0; row_i < rows; row_i++) {
				for (int col_j = 0; col_j < rows; col_j++) {
					chessmans[row_i][col_j] = NOCHESS;
				}
			}
			nowChessColor.setText("����");
			nextChessColor = BLACK;
			rows = Integer.parseInt(rowsText.getText());
			chessmans = new int[rows][rows];
			predictChessmans = new int[rows][rows];
			repaint();
		}

		public void restart() {
			for (int row_i = 0; row_i < rows; row_i++) {
				for (int col_j = 0; col_j < rows; col_j++) {
					chessmans[row_i][col_j] = NOCHESS;
					predictChessmans[rows][rows] = NOCHESS;
				}
			}
			nowChessColor.setText("����");
			nextChessColor = BLACK;
			repaint();
		}
	}

	class BoardFrame extends JFrame {
		private BoardPanel boardPanel;
		JButton refreshBoard;
		JButton restartBoard;

		public int[][] getChessmans() {
			return boardPanel.chessmans;
		}

		public BoardFrame() {
			System.out.println("BoardFrame init");
			setTitle("MCTS������");
			setSize(800, 800);
			Container containerPane = getContentPane();
			JPanel BoardInfo = new JPanel();
			BoardInfo.setLayout(new FlowLayout(FlowLayout.CENTER));
			BoardInfo.setSize(800, 100);
			JLabel rowsLabel = new JLabel("����ά��");
			rowsText = new JTextField(5);
			rowsText.setText("19");
			refreshBoard = new JButton("�޸�����ά��");
			restartBoard = new JButton("���¿�ʼ");
			JLabel nowChessColorLabel = new JLabel("��ǰ�ֵ���");
			nowChessColor = new JLabel("����");

			BoardInfo.add(rowsLabel);
			BoardInfo.add(rowsText);
			BoardInfo.add(refreshBoard);
			BoardInfo.add(restartBoard);
			BoardInfo.add(nowChessColorLabel);
			BoardInfo.add(nowChessColor);

			BoardInfo.setBorder(new TitledBorder("������Ϣ��"));
			containerPane.add(BoardInfo, BorderLayout.NORTH);
			boardPanel = new BoardPanel();
			containerPane.add(boardPanel);
			AddActionListener();
		}

		private void AddActionListener() {
			refreshBoard.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boardPanel.refreshChessBoard();
				}
			});
			restartBoard.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boardPanel.restart();
				}
			});
		}
	}

	class Chessman {
		private int color; // WHITE=1;BLACK=0
		private boolean placed;

		public Chessman(int color, boolean placed) {
			this.color = color;
			this.placed = placed;
		}

		public boolean getPlaced() {
			return placed;
		}

		public void setPlaced(boolean placed) {
			this.placed = placed;
		}

		public int getColor() {
			return color;
		}
	}

	// ��鵱ǰ����Ƿ�Ϊ�ն˽��
	public int checkTerminal(int[][] chessmans) {
		int totalExistChessman = 0;
		for (int row_i = 0; row_i < rows; row_i++) {
			for (int col_j = 0; col_j < rows; col_j++) {
				if (chessmans[row_i][col_j] != NOCHESS) {
					System.out.print("(" + col_j + "," + row_i + ") ");
					int firstColor = chessmans[row_i][col_j];
					int[] distance = new int[4];
					// �����жϣ���������
					if (col_j + WINLENGTH - 1 < rows) {
						while (firstColor == chessmans[row_i][col_j + distance[0]]) {
							distance[0]++;
						}
					}
					// �����жϣ��������ϣ�
					if (row_i + WINLENGTH - 1 < rows) {
						while (firstColor == chessmans[row_i + distance[1]][col_j]) {
							distance[1]++;
						}
					}
					// �������жϣ��������£�
					if (row_i - WINLENGTH + 1 >= 0 && col_j + WINLENGTH - 1 < rows) {
						while (firstColor == chessmans[row_i - distance[2]][col_j + distance[2]]) {
							distance[2]++;
						}
					}
					// �������жϣ��������ϣ�
					if (row_i + WINLENGTH - 1 < rows && col_j + WINLENGTH - 1 < rows) {
						while (firstColor == chessmans[row_i + distance[3]][col_j + distance[3]]) {
							distance[3]++;
						}
					}
					for (int distance_num = 0; distance_num < 4; distance_num++) {
						if (WINLENGTH == distance[distance_num]) {
							if (firstColor == BLACK) {
								return BLACKWIN;
							} else {
								return WHITEWIN;
							}
						}
					}
					totalExistChessman++;
				}
			}
		}
		if (rows * rows == totalExistChessman) {
			return ALLFILLED;
		}
		return NOTWIN;
	}
}
