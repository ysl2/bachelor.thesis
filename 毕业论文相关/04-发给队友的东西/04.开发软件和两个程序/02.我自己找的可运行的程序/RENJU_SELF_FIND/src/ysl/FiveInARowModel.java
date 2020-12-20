package ysl;
/**game five in a row
 * */
public class FiveInARowModel {
	public static final int EMPTY=0;//no chessman
	public static final int BLACK=1;//black chessman
	public static final int WHITE=2;//white chessman
	private int[][]board;
	private int lastPutRow,lastPutCol;
	public FiveInARowModel(int dimension){
		board=new int[dimension][dimension];
		clear();
	}
	public int getDimension(){
		return board.length;
	}
	public int getChessman(int row,int col){
		return board[row][col];
	}
	public boolean isEmpty(int row,int col){
		return EMPTY==getChessman(row, col);
	}
	public boolean isFull(){
		for(int k=0;k<board.length;++k)
			for(int j=0;j<board.length;++j)
				if(isEmpty(k, j))
					return false;
		return true;
	}
	public void clear(){
		//board=new int[board.length][board.length];
		for(int k=0;k<board.length;++k)
			for(int j=0;j<board.length;++j)
				board[k][j]=EMPTY;
		lastPutRow=lastPutCol=-1;
	}
	/**@return last position of the chessman put on the board
	 * */
	public int[]getLastPut(){
		return new int[]{lastPutRow,lastPutCol};
	}
	public boolean trySetChessman(int row,int col,int chessman){
		if(!isEmpty(row, col))
			return false;
		if(chessman!=BLACK&&chessman!=WHITE)
			return false;
		board[row][col]=chessman;
		//lastPutRow=row;
		//lastPutCol=col;
		boolean win= checkWin(row, col);
		board[row][col]=EMPTY;
		return win;
	}
	/**put a chessman on the board
	 * @param row row of position to put chessman
	 * @param col column of position to put chessman
	 * @param chessman type of chessman to put
	 * @return true if the putter wins
	 * */
	public boolean setChessman(int row,int col,int chessman) throws Exception{
		if(!isEmpty(row, col))
			throw new Exception("Non-empty position!");
		if(chessman!=BLACK&&chessman!=WHITE)
			throw new Exception("Invalid chessman");
		board[row][col]=chessman;
		lastPutRow=row;
		lastPutCol=col;
		return checkWin(row, col);
	}
	//chech whether five chessmans in a line
	private boolean checkWin(int row,int col){
		//left->right
		int chessman=getChessman(row, col);
		int selfChessManNum=1;
		for(int left=col-1;
				left>=0&&getChessman(row, left)==chessman;
				--left,++selfChessManNum);
		for(int right=col+1;
				right<getDimension()&&getChessman(row, right)==chessman;
				++right,++selfChessManNum);
		if(selfChessManNum>=5)
			return true;
		//up->down
		selfChessManNum=1;
		for(int up=row-1;
				up>=0&&getChessman(up,col)==chessman;
				--up,++selfChessManNum);
		for(int down=row+1;
				down<getDimension()&&getChessman(down,col)==chessman;
				++down,++selfChessManNum);
		if(selfChessManNum>=5)
			return true;
		//left-up->right-down
		selfChessManNum=1;
		for(int left=col-1,up=row-1;
				left>=0&&up>=0&&getChessman(up, left)==chessman;
				--left,--up,++selfChessManNum);
		for(int right=col+1,down=row+1;
				right<getDimension()&&down<getDimension()&&getChessman(down, right)==chessman;
				++right,++down,++selfChessManNum);
		if(selfChessManNum>=5)
			return true;
		//right-up->left-down
		selfChessManNum=1;
		for(int left=col-1,down=row+1;
				left>=0&&down<getDimension()&&getChessman(down, left)==chessman;
				--left,++down,++selfChessManNum);
		for(int right=col+1,up=row-1;
				right<getDimension()&&up>=0&&getChessman(up, right)==chessman;
				++right,--up,++selfChessManNum);
		if(selfChessManNum>=5)
			return true;
		return false;
	}
}
