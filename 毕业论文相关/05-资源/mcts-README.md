# 程序组件列表：

---

## 界面部分：

### Board类：

- [x] 界面初始化：init()

- [x] 重绘：paint()

- [x] 按钮监听:ButtonActionListener类

- [x] 鼠标监听：PanelMouseAdapter类

- [ ] 判断胜负相关：checkAllStatus()、checkWinner()与checkCount()

### Computer类：

- [ ] 电脑下棋相关：run()、serachMethod()、testMethod3()

---

## 算法部分：

### MCTS类：

- [x] 主算法：UCTSearch()
- [ ] 树策略：treePolicy()
- [ ] 迭代深化：iterativeDeepening()

### Node类：

- [ ] 扩展相关：expand()、isTerminalNode()、isFullyExpand()

- [x] 模拟：defaultPolicy()和rollout()

- [x] 反向传播：backUp()

### State类：

- [x] 工具相关：棋盘深拷贝boardDeepCopt()、state深拷贝stateDeepCopy()、获取对手getOpponent()、产生下一步状态getNextState()

- [ ] 产生行动组：getUntriedActions()

- [x] 判断胜负：isOver() -> 此方法内部调用了Board类的“判断胜负”方法

---

## 其他部分（公共）：

- [ ] BoardConfig接口：公共部分
- [ ] Action类：公共部分，用于算法的坐标表示
- [ ] Position类：公共部分，用于界面的坐标表示。与Action不同的地方在于成员变量中，Position类中没有final修饰符
- [ ] Main类：启动类