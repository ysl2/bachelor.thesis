//采用空窗搜索
int mTDF(int firstGuess, int depth) {
	
	int g, upperBound， lowerBound;
	g = firstGuess;	//初始猜测值
	upperBound = INFINITY;	//无穷大，在范例中是20000
	lowerBound = -INFINITY;	//负无穷，在范例中是-20000
	
	while (lowerBound < upperBound) {	//下界小于上界
		if (g == lowerBound) {
			beta = g + 1;
		} else {
			beta = g;
		}
		//空窗探测
		g = alphabeta(depth, beta - 1, beta);	//这里使用的alpha-beta搜索是经过置换表增强的fail-soft alphabeta搜索
		if (g < beta) {
			upperBound = g; //fail high
		} else {
			lowerBound = g;	//fail low
		}
		return g;	//返回结果
	}
}