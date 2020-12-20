//MiniMax Algorithm
//伪代码
//让电脑是MAX方，人类是MIN方
int alphaBeta(int nPly, int alpha, int beta) {	//nPly是此走法所在的层次
	
	int score;
	
	if (isGameOver) {
		return Evaluation;
	}
	if (nPly == 0) {
		return Evaluation;
	}
	if (is Human node) {	//人类节点，说明是取MIN值
		for (each possible move m) {	//	对每一可能的走法m
			makeMove m;	//生成新节点
			score = alphaBeta(nPly - 1, alpha, beta);	//递归搜索子节点
			unMakeMove m;
		
			if (score < beta) {
				beta = score;	//取极小值
				if (alpha >= beta) {
					return alpha;	//alpha剪枝，抛弃后继结点
				}
			}
			return beta;
		}
	} else {	//电脑节点，说明是取MAX值
		for (each possible move m) {
			makeMove m;
			score = alphaBeta(nPly - 1, alpha, beta);
			unMakeMove m;
			
			if (score > alpha) {
				alpha = score;
				if (alpha >= beta) {
					return beta;	//beta剪枝，抛弃后继结点
				}
			}
			return alpha;
		}
	}
}