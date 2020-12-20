//MiniMax Algorithm
//伪代码
//让电脑是MAX方，人类是MIN方
int miniMax(Position p, int depth) {	//Position是局面，depth是深度
	
	int value;
	int bestvalue;
	
	if (isGameOver(p)) {	//如果游戏结束，返回对此局面的估值
		return evaluation(p);
	}
	if (depth <= 0) {	//如果是叶子节点（终端节点），返回估值
		return evaluation(p);
	}
	if (p.color == COMPUTER_COLOR) {	//如果该电脑下棋了
		bestvalue = -INFINITY;	//让初始的最大值为极小
	} else {	//如果是人类下棋
		bestvalue = INFINITY;	//让初始的最大值为极大
	}
	
	for (遍历棋盘，先拿出一个空着的点m) {
		makeMove(m);	//在p中产生一个局面，也就是向下遍历一步，或者说假装再下一步棋。所以p应该长度可变
		value = miniMax(p,depth - 1);	//递归地往下走
		unMakeMove(m);	//恢复原状，删掉自己向下模拟的节点，防止内存溢出
	
		if (p.color == COMPUTER_COLOR) {	//如果该电脑下了，取MAX值
			bestvalue = max(value, bestvalue);
		} else {	//如果该人类下了，取MIN值
			bestvalue = min(value, bestvalue);
		}
	}

	return bestvalue;	//返回处理完的结果
}