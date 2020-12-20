//Negamax Algorithm
//伪代码
//让电脑是MAX方，人类是MIN方
int negaMax(Position p, int depth) {
	
	int value;
	int bestvalue = -INFINITY;
	
	if (isGameOver(p)) {	//如果游戏结束，返回估值
		return evaluation(p);	//注意，负极大值的估值函数必须对哪边下的敏感
	}
	if (depth == 0) {	//深度为0，说明是叶子节点，返回估值
		return evaluation(p);
	}
	for (遍历棋盘，先拿出一个空着的点m) {
		makeMove(m);	//在p中产生一个局面，也就是向下遍历一步，或者说假装再下一步棋。所以p应该长度可变
		score = -negaMax(p, depth - 1);	//递归地往下走。
		unMakeMove(m);	//恢复原状，删掉自己向下模拟的节点，防止内存溢出
		
		if (value >= bestvalue) {
			bestvalue = value;	//取最大值
		}
	}
	return bestvalue;	//返回处理完的结果
}