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
		public int[][] chessmans;//��ǰ�������״̬
		public int[][] rewardMap;//��ǰ����״̬��㣬���ӵĸ���ͼ
		public int[][] accessNumberMap;//��ǰ����״̬��㣬��������
		public int accessNumber;//��ǰ�ڵ��ܷ�����
		public int reward;
		//��һ�ڵ㼯��
		public Node[][] nextNode;
		public Node lastNode;
		//���ڵ�����һ������
		public int[] chessYX;
		//��һ�ڵ���ɫ
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
	//������һ��Ϊ��չ�Ķ���
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
	//UCT���־���
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
			System.out.println("error��û�ҵ�����ֵUCT");
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
	//Ĭ�ϲ���
	public int defaultPolicy(Node node) {
		//System.out.println("Ĭ��");
		///���ﲻ֪���Ƿ���Ҫ��chessmans����һ��
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
	//���ڸ�����״̬�Ľ���
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
	//�����ش�
	public void backup(Node lastNode, int reward) {
		//meΪ1��himΪ-1
		int meOrHim = 1;
		//���һ����㲻��Ҫ��¼���ӽڵ�ķ���
		lastNode.accessNumber++;
		lastNode.reward +=meOrHim*reward;
		int[] chessYX = lastNode.chessYX;
		lastNode = lastNode.lastNode;
		while(lastNode != null) {
			//���һ�����֮ǰ�����нڵ㶼Ҫ��¼���ӽڵ�ķ���
			
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
	//���ص�ǰ���̵���һ�����ӵ���ɫ
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
			//System.out.println("�׺�һ������һ����");
			return BLACK;
		}else {
			System.out.println("error���������Ӳ�ͬ��ɫ������ֵ��Ϊ1��0��Ϊ ��"+blackNumber+" ��"+whiteNumber);
			return ERROR;
		}
	}
	//���վ��ȸ����������һ����ǰ���̵�����λ�õ�xy����
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
		//System.out.print("������������ӣ�"+remainNumber+" = "+rows+"*"+rows+"-"+totalNumber);
		int randomNumber = (int)(Math.random()*remainNumber);
		//System.out.print(randomNumber);
		int nowNumber = 0;
		for(int col_i=0; col_i<rows; col_i++) {
			for(int row_j=0; row_j<rows; row_j++) {
				if(chessmans[col_i][row_j] == NOCHESS) {
					if(nowNumber == randomNumber) {
						//System.out.println("����������"+"("+col_i+","+row_j+")");
						nextYX[0] = col_i;
						nextYX[1] = row_j;
						return nextYX;
					}
					nowNumber++;
				}
			}
		}
		System.out.println("error�������������ʣ��������");
		return nextYX;
	}
	//��鵱ǰ����Ƿ�Ϊ�ն˽��
	public int checkTerminal(int[][] chessmans) {
		int totalExistChessman = 0;
		for(int col_i=0; col_i<rows; col_i++) {
			for(int row_j=0; row_j<rows; row_j++) {
				if(chessmans[col_i][row_j] != NOCHESS) {
					//System.out.print("("+row_j+","+col_i+") ");
					int firstColor = chessmans[col_i][row_j];
					int[] distance = new int[4];
					//�����жϣ���������
					if(row_j+WINLENGTH-1<rows) {
						while(distance[0]<5 && firstColor == chessmans[col_i][row_j+distance[0]]) {
							distance[0]++;
						}
					}
					//�����жϣ��������ϣ�
					if(col_i+WINLENGTH-1 < rows) {
						while(distance[1]<5 && firstColor == chessmans[col_i+distance[1]][row_j]) {
							distance[1]++;
						}
					}
					//�������жϣ��������£�
					if(col_i-WINLENGTH+1 >= 0 && row_j+WINLENGTH-1 < rows) {
						while(distance[2]<5 && firstColor == chessmans[col_i-distance[2]][row_j+distance[2]]) {
							distance[2]++;
						}
					}
					//�������жϣ��������ϣ�
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
