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
		// 棋盘窗体
		this.boardFrame = new BoardFrame();

		boardFrame.setVisible(true);
	}

	// 棋盘内容
	class BoardPanel extends JPanel implements MouseListener {
		public Image boardImage;// 棋盘边框

		public int lastChessColor = WHITE;
		// 记录全部落子的棋子类，第一项为行数，第二项为列数
		public int[][] chessmans = new int[rows][rows];
		public int[][] predictChessmans = new int[rows][rows];
		public int maxReward = 0;
		public int nextChessColor = BLACK;// 控制交换棋权

		int FrameWidth;// 窗体
		int FrameHeight;
		int chessBoardX;// 棋盘边框左上角
		int chessBoardY;
		int realChessBoardX;// 真正落子的左上角
		int realChessBoardY;
		int deltaX;// 间距
		int deltaY;

		public int[][] getChessmans() {
			return this.chessmans;
		}

		// 棋盘边框
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
		// 棋盘内部的画线与棋子
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int imageWidth = boardImage.getWidth(this);
			int imageHeight = boardImage.getHeight(this);
			FrameWidth = getWidth();
			FrameHeight = getHeight();
			// 将图片呈现在画布中间
			chessBoardX = (FrameWidth - imageWidth) / 2;
			chessBoardY = (FrameHeight - imageHeight) / 2;
			g.drawImage(boardImage, chessBoardX, chessBoardY, null);
			// 画出棋盘线
			int margin = 40;
			deltaX = (imageWidth - 2 * margin) / (rows - 1);
			deltaY = (imageHeight - 2 * margin) / (rows - 1);
			realChessBoardX = chessBoardX + (imageWidth - deltaX * (rows - 1)) / 2;
			realChessBoardY = chessBoardY + (imageHeight - deltaY * (rows - 1)) / 2;
			for (int i = 0; i < rows; i++) {// 横线
				g.drawLine(realChessBoardX, realChessBoardY + deltaY * i, realChessBoardX + deltaX * (rows - 1),
						realChessBoardY + deltaY * i);
			}
			for (int i = 0; i < rows; i++) {// 竖线
				g.drawLine(realChessBoardX + deltaX * i, realChessBoardY, realChessBoardX + deltaX * i,
						realChessBoardY + deltaY * (rows - 1));
			}

			// 画棋子
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
			System.out.println("更新棋盘状态");
		}

		@Override
		// 控制落子
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getButton() == MouseEvent.BUTTON1) {
				int x = e.getX(); // 得到鼠标x坐标
				int y = e.getY(); // 得到鼠标y坐标
				// 检验是否在棋盘内
				if (x > chessBoardX && x < FrameWidth - chessBoardX && y > chessBoardY
						&& y < FrameHeight - chessBoardY) {
					int col_i = (x - realChessBoardX + deltaX / 2) / deltaX;
					int row_j = (y - realChessBoardY + deltaY / 2) / deltaY;
					// 检验当前位置是否有棋
					if (chessmans[col_i][row_j] == NOCHESS) {
						if (nextChessColor == BLACK) {
							System.out.print("黑棋 ");
							nowChessColor.setText("白棋");
							chessmans[col_i][row_j] = BLACK;
							nextChessColor = WHITE;
						} else if (nextChessColor == WHITE) {
							System.out.print("白棋 ");
							nowChessColor.setText("黑棋");
							chessmans[col_i][row_j] = WHITE;
							nextChessColor = BLACK;
						}
						String banner = "鼠标当前点击位置的坐标是" + x + "," + y + "  对应棋盘坐标为" + col_i + "," + row_j + " ";
						System.out.print(banner);
						int isWin = checkTerminal(chessmans);
						System.out.print("isWin:" + isWin);
						repaint();

						//////////////////////////////////
						// 调用MCTS算法
						// 传回来一个记录每个位置落子概率的矩阵
						MCTS_01 MCTS = new MCTS_01(chessmans);
						System.out.println("行走策略" + MCTS.getProbabilityMap());
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
						System.out.println("当前位置有棋");
					}
				} else {
					System.out.println("鼠标位置非法");
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
				System.out.println("棋盘维度错误");
				return;
			}
			for (int row_i = 0; row_i < rows; row_i++) {
				for (int col_j = 0; col_j < rows; col_j++) {
					chessmans[row_i][col_j] = NOCHESS;
				}
			}
			nowChessColor.setText("黑棋");
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
			nowChessColor.setText("黑棋");
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
			setTitle("MCTS五子棋");
			setSize(800, 800);
			Container containerPane = getContentPane();
			JPanel BoardInfo = new JPanel();
			BoardInfo.setLayout(new FlowLayout(FlowLayout.CENTER));
			BoardInfo.setSize(800, 100);
			JLabel rowsLabel = new JLabel("棋盘维度");
			rowsText = new JTextField(5);
			rowsText.setText("19");
			refreshBoard = new JButton("修改棋盘维度");
			restartBoard = new JButton("重新开始");
			JLabel nowChessColorLabel = new JLabel("当前轮到：");
			nowChessColor = new JLabel("黑棋");

			BoardInfo.add(rowsLabel);
			BoardInfo.add(rowsText);
			BoardInfo.add(refreshBoard);
			BoardInfo.add(restartBoard);
			BoardInfo.add(nowChessColorLabel);
			BoardInfo.add(nowChessColor);

			BoardInfo.setBorder(new TitledBorder("棋盘信息栏"));
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

	// 检查当前结点是否为终端结点
	public int checkTerminal(int[][] chessmans) {
		int totalExistChessman = 0;
		for (int row_i = 0; row_i < rows; row_i++) {
			for (int col_j = 0; col_j < rows; col_j++) {
				if (chessmans[row_i][col_j] != NOCHESS) {
					System.out.print("(" + col_j + "," + row_i + ") ");
					int firstColor = chessmans[row_i][col_j];
					int[] distance = new int[4];
					// 向右判断（包含向左）
					if (col_j + WINLENGTH - 1 < rows) {
						while (firstColor == chessmans[row_i][col_j + distance[0]]) {
							distance[0]++;
						}
					}
					// 向下判断（包含向上）
					if (row_i + WINLENGTH - 1 < rows) {
						while (firstColor == chessmans[row_i + distance[1]][col_j]) {
							distance[1]++;
						}
					}
					// 向右上判断（包含左下）
					if (row_i - WINLENGTH + 1 >= 0 && col_j + WINLENGTH - 1 < rows) {
						while (firstColor == chessmans[row_i - distance[2]][col_j + distance[2]]) {
							distance[2]++;
						}
					}
					// 向右下判断（包含左上）
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
