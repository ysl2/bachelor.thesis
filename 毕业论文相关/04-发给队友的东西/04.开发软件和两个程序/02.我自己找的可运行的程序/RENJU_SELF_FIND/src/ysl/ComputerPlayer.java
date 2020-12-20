package ysl;

import java.util.Random;

//
public class ComputerPlayer {
	private FiveInARowModel board;
	private int chessman;
	public ComputerPlayer(FiveInARowModel board,int chessman){
		this.board=board;
		this.chessman=chessman;
	}
	/**
	 * put a chessman on board
	 * @return true if computer wins
	 * */
	public boolean play(){
		int[]anotherPlayerPut=board.getLastPut();
		if(anotherPlayerPut[0]<0){
			try {
				board.setChessman(board.getDimension()/2, board.getDimension()/2, chessman);
			} catch (Exception e) {
			}
			return false;
		}
		int row=anotherPlayerPut[0],col=anotherPlayerPut[1];
		int nearRow=-1,nearCol=-1,dis=2*board.getDimension();
		//search a position if player can win
		for(int k=0;k<board.getDimension();++k)
			for(int j=0;j<board.getDimension();++j)
				if(board.isEmpty(k, j)){
					if(Math.abs(k-row)+Math.abs(j-col)<dis){
						nearCol=j;
						nearRow=k;
						dis=Math.abs(k-row)+Math.abs(j-col);
					}
					if(board.trySetChessman(k, j, chessman)){
						try {
							return board.setChessman(k, j, chessman);
						} catch (Exception e) {
							//impossible to get here
							return false;
						}
					}
				}
		//
		//left->right
		//check whether another player has more than 3 chessman in a row
		if(checkLeftToRight(row, col))
			return false;
		if(checkUpToDown(row, col))
			return false;
		if(checkLeftUpToRightDown(row, col))
			return false;
		if(checkLeftDownToRightUp(row, col))
			return false;
		//choose a position randomly
		try {
			board.setChessman(nearRow, nearCol, chessman);
		} catch (Exception e) {
		}
		return false;
	}
	private boolean checkLeftToRight(int row,int col){
		return check(row,col,0,1);
	}
	private boolean checkUpToDown(int row,int col){
		return check(row,col,1,0);
	}
	private boolean checkLeftUpToRightDown(int row,int col){
		return check(row,col,1,1);
	}
	private boolean checkLeftDownToRightUp(int row,int col){
		return check(row,col,-1,1);
	}
	//check chessmans in a line. If more than 3 chessmans in a row, then blocked it
	private boolean check(int row,int col,int drow,int dcol){
		int startRow=row-drow,startCol=col-dcol,
				endRow=row+drow,endCol=col+dcol;
		int chessman=board.getChessman(row, col);
		int sameChessNum=1;
		while(startRow>=0&&startCol>=0&&board.getChessman(startRow, startCol)==chessman){
			startRow-=drow;
			startCol-=dcol;
			++sameChessNum;
		}
		while(endRow<board.getDimension()
				&&endCol<board.getDimension()
				&&board.getChessman(endRow, endCol)==chessman){
			endRow+=drow;
			endCol+=dcol;
			++sameChessNum;
		}
		if(sameChessNum>=3){//more than 3  chessman in a row
			if(startRow>=0&&startCol>=0&&board.isEmpty(startRow, startCol)){
				try {
					board.setChessman(startRow, startCol, this.chessman);
					return true;
				} catch (Exception e) {
				}
			}else if(endRow<board.getDimension()
					&&endCol<board.getDimension()&&board.isEmpty(endRow, endCol)){
				try {
					board.setChessman(endRow, endCol, this.chessman);
					return true;
				} catch (Exception e) {
				}
			}
		}
		return false;
	}
}