def monte_carlo_tree_search(root)
	while resources_left(time,computational power):	#思考时间有限，计算能力有限。在资源允许的情况下运行MCTS
		leaf = traverse(root)	#leaf = unvisited node	单个模拟会从未访问的节点开始，结果会被反向传播到根节点，然后根节点就会被认为完全展开。
		simulation_result = rollout(leaf)	#rollout策略函数，用于对博弈树节点评估的近似估算。这一过程会从该节点处开始以某种随机博弈方式进行
		backpropagate(leaf,simulation_result)	#一旦完成一次新访问节点（有时称为叶节点）的模拟，其结果就将反向传播回当前的博弈树根节点。模拟开始的节点就会被标记为已访问
	return best_child(root)	#最优的一步通常是总访问次数N(v)最高的节点，因为它的值是被估计的最好的（节点的自身估计值一定是很高的，并且同是也是被探索次数最多的节点）

def traverse(node)
	while fully_expanded(node):
		node = best_uct(node)	# UCT：树的上限置信区间，分为两部分。一部分是exploitation分量（开拓分量。可看作是胜率，用总模拟奖励Q(v)除以总访问次数N(v)），另一部分是exploration分量（探索分量）。探索分量偏向于那些尚未探索过的节点，即那些相对访问次数较少的节点（N(v)较低的节点）。探索分量会随着节点访问次数的增加而减小，并且将为访问次数较少的节点提供更高的选择可能性，从而引导搜索进行探索。在竞争博弈中，UCT函数的expolitation分量Q_i的计算总是与在节点i处移动的玩家有关。这意味着在遍历博弈树时，这个值会根据正在遍历的节点变化：对于任何两个连续的节点，这个值是相反的。
	return pick_unvisited(node.children) or node	# in case no children are present / node is terminal
def rollout(node)	#rollout策略函数。
	while non_terminal(node):
		node = rollout_policy(node)	#采用某种rollout策略
	return result(node)	#模拟结果包括：模拟奖励Q(v)和访问次数N(v)
def rollout_policy(node)	#默认的rollout策略函数会使用服从均匀分布的随机采样
	return pick_random(node.children)
def backpropagate(node,result)	#反向传播路径上的每个节点的统计数据都会被计算 / 更新。反向传播保证了每个节点的统计数据将会反映在其所有后代节点所开始的模拟结果中（因为模拟结果会被传送到博弈树根节点）
	if is_root(node) return	#反向传播模拟结果的目的是更新反向传播路径上的所有结点v（包括模拟开始的节点）的总模拟奖励Q(v)和总访问次数N(v)。Q(v)最简单的形式是通过考核的节点的模拟结果的总和。N(v)表示一个节点在反向传播路径上的次数（同时是它对总模拟奖励贡献的次数）。总模拟奖励较高的节点会是很好的候选节点，但访问量较低的节点也可能很值得访问（因为它们还没有被很好地展开）
	node.stats = update_stats(node,result)
	backpropagate(node.parent)
def bset_child(node)
	pick child with highest number of visits

		  
		
