package bwjiang;

import bwjiang.DrawChessBoard.Chessman;

public class MCTS_01 {
	public static final int BUDGET = 1000000;
	public static final int BLACKWIN = 1;
	public static final int WHITEWIN = 2;
	public static final int NOTWIN = 0;
	public static final int ALLFILLED = -1;
	public static final int BLACK = 1;
	public static final int NOCHESS = 0;
	public static final int WHITE = -1;
	public static final int ERROR = -999;
	public static final int WINLENGTH = 5;
	public static final int UCTCP = 2;
	public int rows;
	Node rootNode;
	public MCTS_01(int[][] chessmans) {
		rows = chessmans.length;
		
		rootNode = new Node(null, chessmans);
//		System.out.println();
//		for(int col_i=0; col_i<row; col_i++) {
//			for(int row_j=0; row_j<row; row_j++) {
//				System.out.print("  "+probabilityMap[col_i][row_j]+"-"+accessNumberMap[col_i][row_j]);
//			}
//			System.out.println();
//		}
		while (rootNode.accessNumber<BUDGET) {
			Node node = treePolicy(rootNode);
			int reward = defaultPolicy(node);
			backup(node, reward);
		}
		//return bestChild(rootNode);
	}
	
	class Node {
		public int[][] chessmans;//当前结点棋盘状态
		public int[][] rewardMap;//当前棋盘状态结点，落子的概率图
		public int[][] accessNumberMap;//当前棋盘状态结点，访问数量
		public int accessNumber;//当前节点总访问数
		public int reward;
		//下一节点集合
		public Node[][] nextNode;
		public Node lastNode;
		//本节点的最后一个棋子
		public int[] chessYX;
		//下一节点颜色
		int nextColor;
		public Node(Node lastNode, int[][] chessmans) {
			this.chessmans = chessmans;
			this.lastNode = lastNode;
			this.nextColor = nextChessColor(chessmans);
			nextNode = new Node[rows][rows];
			accessNumber = 0;
			reward = 0;
			rewardMap = new int[rows][rows];
			accessNumberMap = new int[rows][rows];
			chessYX = new int[2];
		}
		public void setChessYX(int Y, int X) {
			this.chessYX[0] = Y;
			this.chessYX[1] = X;
		}
	}
	public int[][] getProbabilityMap() {
		return rootNode.rewardMap;
	}
	public Node treePolicy(Node node) {
		while(node != null) {
			for(int col_i=0; col_i<rows; col_i++) {
				for(int row_j=0; row_j<rows; row_j++) {
					//System.out.println("["+col_i+","+row_j+"]");
					if(null == node.nextNode[col_i][row_j] && NOCHESS == node.chessmans[col_i][row_j]) {
						//System.out.println("hi");
						return expand(node, col_i, row_j);
					}
				}
			}
			node = bestChild(node);
			
		}
		return node;
	}
	//扩张下一个为扩展的动作
	public Node expand(Node node, int Y, int X) {
		int[][] tempChessmans = new int[rows][rows];
		for(int col_i=0; col_i<rows; col_i++) {
			for(int row_j=0; row_j<rows; row_j++) {
				tempChessmans[col_i][row_j] = node.chessmans[col_i][row_j];
				//System.out.print(tempChessmans[col_i][row_j]);
			}
			//System.out.println();
		}
		int tempNextColor = node.nextColor;
		tempChessmans[Y][X] = tempNextColor;
		node.nextNode[Y][X] = new Node(node, tempChessmans);
		node.nextNode[Y][X].setChessYX(Y, X);
		return node.nextNode[Y][X];
	}
	//UCT部分精髓
	public Node bestChild(Node node) {
		double maxUCT = 0;
		int maxUCTcol_i = -1;
		int maxUCTrow_j = -1;
		for(int col_i=0; col_i<rows; col_i++) {
			for(int row_j=0; row_j<rows; row_j++) {
				if(NOCHESS == node.chessmans[col_i][row_j]) {
					double tempMaxUCT = node.rewardMap[col_i][row_j]/node.accessNumberMap[col_i][row_j] 
							+ UCTCP * Math.sqrt(2*Math.log(node.accessNumber)/node.accessNumberMap[col_i][row_j]);
					if(tempMaxUCT > maxUCT) {
						maxUCT = tempMaxUCT;
						maxUCTcol_i = col_i;
						maxUCTrow_j = row_j;
					}
				}
			}
		}
		if(maxUCTcol_i == -1 || maxUCTrow_j == -1) {
			System.out.println("error：没找到极大值UCT");
			for(int col_i=0; col_i<rows; col_i++) {
				for(int row_j=0; row_j<rows; row_j++) {
					if(NOCHESS == node.chessmans[col_i][row_j]) {
						double tempMaxUCT = node.rewardMap[col_i][row_j]/node.accessNumberMap[col_i][row_j] 
								+ UCTCP * Math.sqrt(2*Math.log(node.accessNumber)/node.accessNumberMap[col_i][row_j]);
						System.out.println("error:"+tempMaxUCT+"="+node.rewardMap[col_i][row_j]+"/"+node.accessNumberMap[col_i][row_j] +"+"+ UCTCP +"*" + "Math.sqrt(2*"+Math.log(node.accessNumber)+"/"+node.accessNumberMap[col_i][row_j]+")");
					}
				}
			}
		}
		return node.nextNode[maxUCTcol_i][maxUCTrow_j];
	}
	//默认策略
	public int defaultPolicy(Node node) {
		//System.out.println("默认");
		///这里不知道是否需要把chessmans复制一下
		int nextColor = node.nextColor;
		int[][] tempChessmans = new int[rows][rows];
		for(int col_i=0; col_i<rows; col_i++) {
			for(int row_j=0; row_j<rows; row_j++) {
				tempChessmans[col_i][row_j] = node.chessmans[col_i][row_j];
			}
		}
		while(NOTWIN == checkTerminal(tempChessmans)) {
			int[] nextYX = new int[2];
			nextYX = randomNext(tempChessmans);
			tempChessmans[nextYX[0]][nextYX[1]] = nextColor;
			if(nextColor == BLACK) {
				nextColor = WHITE;
			}else{
				nextColor = BLACK;
			}
		}
		return stateReward(tempChessmans, node.nextColor);
	}
	//对于该棋盘状态的奖励
	public int stateReward(int[][] chessmans, int nextColor) {
		final int win = 2;
		final int loss = 0;
		final int flat = 1;
		if(checkTerminal(chessmans) == BLACKWIN) {
			if(nextColor == BLACK) {
				return win;
			}else {
				return loss;
			}
		}else if(checkTerminal(chessmans) == WHITEWIN){
			if(nextColor == WHITE) {
				return win;
			}else {
				return loss;
			}
		}else {
			return flat;
		}
	}
	//奖励回传
	public void backup(Node lastNode, int reward) {
		//me为1，him为-1
		int meOrHim = 1;
		//最后一个结点不需要记录其子节点的访问
		lastNode.accessNumber++;
		lastNode.reward +=meOrHim*reward;
		int[] chessYX = lastNode.chessYX;
		lastNode = lastNode.lastNode;
		while(lastNode != null) {
			//最后一个结点之前的所有节点都要记录其子节点的访问
			
			lastNode.accessNumberMap[chessYX[0]][chessYX[1]]++;
			lastNode.accessNumber++;
			
			lastNode.rewardMap[chessYX[0]][chessYX[1]] += meOrHim*reward;
			if(1 == meOrHim) {
				meOrHim = -1;
			}else {
				meOrHim = 1;
			}
			lastNode.reward += meOrHim*reward;
			
			//System.out.println(meOrHim);
			
			chessYX = lastNode.chessYX;
			lastNode = lastNode.lastNode;
			
		}
	}
	//返回当前棋盘的下一个棋子的颜色
	public int nextChessColor(int[][] chessmans) {
		int whiteNumber = 0;
		int blackNumber = 0;
//		for(int col_i=0; col_i<rows; col_i++) {
//			for(int row_j=0; row_j<rows; row_j++) {
//
//				System.out.print(chessmans[col_i][row_j]);
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		for(int col_i=0; col_i<rows; col_i++) {
			for(int row_j=0; row_j<rows; row_j++) {
				if(chessmans[col_i][row_j] == BLACK) {
					//System.out.print("@");
					blackNumber++;
				}
				if(chessmans[col_i][row_j] == WHITE) {
					//System.out.print("o");
					whiteNumber++;
				}
			}
		}
		if(blackNumber-whiteNumber == 1) {
			//System.out.print("@");
			return WHITE;
		}else if(blackNumber-whiteNumber == 0) {
			//System.out.print("o");
			//System.out.println("白黑一样，下一个黑");
			return BLACK;
		}else {
			System.out.println("error：棋盘棋子不同颜色数量差值不为1或0，为 黑"+blackNumber+" 白"+whiteNumber);
			return ERROR;
		}
	}
	//按照均等概率随机返回一个当前棋盘的落子位置的xy坐标
	public int[] randomNext(int[][] chessmans) {
		int[] nextYX = new int[2];
		int totalNumber = 0;
		for(int col_i=0; col_i<rows; col_i++) {
			for(int row_j=0; row_j<rows; row_j++) {
				if(chessmans[col_i][row_j] != NOCHESS) {
					totalNumber++;
				}
			}
		}
		int remainNumber = rows*rows - totalNumber;
		//System.out.print("随机生成新棋子："+remainNumber+" = "+rows+"*"+rows+"-"+totalNumber);
		int randomNumber = (int)(Math.random()*remainNumber);
		//System.out.print(randomNumber);
		int nowNumber = 0;
		for(int col_i=0; col_i<rows; col_i++) {
			for(int row_j=0; row_j<rows; row_j++) {
				if(chessmans[col_i][row_j] == NOCHESS) {
					if(nowNumber == randomNumber) {
						//System.out.println("插入新棋子"+"("+col_i+","+row_j+")");
						nextYX[0] = col_i;
						nextYX[1] = row_j;
						return nextYX;
					}
					nowNumber++;
				}
			}
		}
		System.out.println("error：随机数超出总剩余棋子数");
		return nextYX;
	}
	//检查当前结点是否为终端结点
	public int checkTerminal(int[][] chessmans) {
		int totalExistChessman = 0;
		for(int col_i=0; col_i<rows; col_i++) {
			for(int row_j=0; row_j<rows; row_j++) {
				if(chessmans[col_i][row_j] != NOCHESS) {
					//System.out.print("("+row_j+","+col_i+") ");
					int firstColor = chessmans[col_i][row_j];
					int[] distance = new int[4];
					//向右判断（包含向左）
					if(row_j+WINLENGTH-1<rows) {
						while(distance[0]<5 && firstColor == chessmans[col_i][row_j+distance[0]]) {
							distance[0]++;
						}
					}
					//向下判断（包含向上）
					if(col_i+WINLENGTH-1 < rows) {
						while(distance[1]<5 && firstColor == chessmans[col_i+distance[1]][row_j]) {
							distance[1]++;
						}
					}
					//向右上判断（包含左下）
					if(col_i-WINLENGTH+1 >= 0 && row_j+WINLENGTH-1 < rows) {
						while(distance[2]<5 && firstColor == chessmans[col_i-distance[2]][row_j+distance[2]]) {
							distance[2]++;
						}
					}
					//向右下判断（包含左上）
					if(col_i+WINLENGTH-1 < rows && row_j+WINLENGTH-1 < rows) {
						while(distance[3]<5 && firstColor == chessmans[col_i+distance[3]][row_j+distance[3]]) {
							distance[3]++;
						}
					}
					for(int distance_num=0; distance_num<4;distance_num++) {
						if(WINLENGTH == distance[distance_num]) {
							if(firstColor == BLACK) {
								return BLACKWIN;
							}else {
								return WHITEWIN;
							}
						}
					}
					totalExistChessman++;
				}	
			}
		}
		if(rows*rows == totalExistChessman) {
			return ALLFILLED;
		}
		return NOTWIN;
	}
}
