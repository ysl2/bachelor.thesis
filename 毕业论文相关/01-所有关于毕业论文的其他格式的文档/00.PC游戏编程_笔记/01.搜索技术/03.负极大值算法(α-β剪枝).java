//Negamax Algorithm
//伪代码
//让电脑是MAX方，人类是MIN方
int negaMaxAlphaBeta(int nPly, int alpha, int beta) {
	
	int score;
	
	if (GameOver) {
		return eval();
	}
	if (nPly <= 0) {
		return eval();
	}
	for (each possible move m) {
		make move m;
		score = -negaMaxAlphaBeta(nPly - 1, -beta, -alpha);
		unmake move m;
		if (score >= alpha) {
			alpha = score;	//修改alpha边界
		}
		if (alpha >= beta) {
			break;	//beta剪枝
		}
	}
	return alpha;
}