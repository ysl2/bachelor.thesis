//MiniMax Algorithm
//伪代码
//让电脑是MAX方，人类是MIN方
int fAlphaBeta(int depth, int alpha, int beta) {
	
	int value;
	int bestValue = -INFINITY;	//current = 负无穷
	
	if (game over or depth <= 0) {
		return eval();
	}
	if (depth <= 0) {	//这里不知道为啥非要再加一个if，感觉没必要
		return eval();
	}
	for (each possible move m) {
		make move m;	//产生子节点
		value = - fAlphaBeta(depth - 1, -beta, -alpha);
		unmake move m;	//恢复局面
		
		if (value > bestValue) {
			bestValue = value;	//	保留极大值
			if (value >= alpha) {
				alpha = value;	//修改alpha边界
			}
			if (value >= beta) {
				break;	//beta剪枝
			}
		}
		return bestValue;
	}
}