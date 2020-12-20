//历史启发增强的alpha-beta
int alphaBeta(Position p, int a, int b, int depth) {
	int Count, m, score, bestmove, result;
	if (depth <= 0) {
		return evaluate();	//叶子节点返回估值
	}
	Count = generateMoves(moves);
	for (each possibly move m) {	//对每一合法走法
		make move m;	//根据当前走法产生子节点
		//递归调用alpha-beta搜索子节点
		result = - alphaBeta(-b, -a, depth - 1);	//	负极大值
		unmake move m;	//撤销搜索过的子节点
		if (result > score) {
			score = result;	//保留最大值
		}
		if (score >= b) {
			//beta剪枝，剩余节点不必再展开
			bestmove = moves[m];	//记录最佳走法
			goto done;
		}
		if (score > a) {
			a = score;
		}
		
	}
	done:
		HistoryTable[bestmove] = HistoryTable[bestmove] + 2^(depth);
		return score;
}