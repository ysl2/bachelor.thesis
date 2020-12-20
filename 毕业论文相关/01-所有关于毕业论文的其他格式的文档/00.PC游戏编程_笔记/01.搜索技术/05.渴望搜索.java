//MiniMax Algorithm
//伪代码
//让电脑是MAX方，人类是MIN方
int aspiritionSearch(int position[][] p) {
	int X;
	int WINDOW = 50;	//这个值是可以人为规定的
	X = fAlphaBeta(depth - 1, -INFINITY, INFINITY);	//先测试搜索一下，确定X的值
	int alpha = X - WINDOW;	//	X是我们猜测搜索的结果在X附近
	int beta = X + WINDOW;
	int score;
	while(true) {	//这里我不知道到底用不用加循环
		score = fAlphaBeta(depth, alpha, beta);
		if (score <= alpha) {
			alpha = -INFINITY;
		} else if (score >= beta) {
			beta = INFINITY;
		} else {
			break;
		}
	}
}
int fAlphaBeta(int depth, int alpha, int beta) {	//从fail-soft复制过来的
	
	int current = -INFINITY;	//current = 负无穷
	
	if (game over or depth <= 0) {
		return eval();
	}
	if (depth <= 0) {	//这里不知道为啥非要再加一个if，感觉没必要
		return eval();
	}
	for (each possible move m) {
		make move m;	//产生子节点
		score = - fAlphaBeta(depth - 1, -beta, -alpha);
		unmake move m;	//恢复局面
		
		if (score > current) {
			current = score;	//	保留极大值
			if (score >= alpha) {
				alpha = score;	//修改alpha边界
			}
			if (score >= beta) {
				break;	//beta剪枝
			}
		}
		return current;
	}
}
