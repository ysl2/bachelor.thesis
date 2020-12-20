package cn.itcast_01;

import MCTS_001.Board;
import MCTS_001.BoardConfig;

import java.util.Map;

/**
 * 定义了MCTS的基本步骤与树策略
 */
public class MCTS implements BoardConfig {
    public Board board = null;

    //构造器
    public MCTS(Board board) {
        this.board = board;
    }

    private MCTS() {
    }
    //成员方法：

    /**
     * 这是UCT搜索的主方法
     *
     * @param chessArray 一个棋盘状态
     * @param player     当前的下棋方
     * @return 一个最优动作
     */
    public Action UCTSearch(int[][] chessArray, int player) {
        State state = new State(chessArray, player);
        Node root = new Node(state, null);
        int sumSarchTimes = MCTS.iterativeDeepening(this.board);  //当前搜索次数
        board.jpb.setMaximum(sumSarchTimes);
        for (int i = 0; i < sumSarchTimes; i++) {
            Node currentNode = treePolicy(root);
            int winner = currentNode.defaultPolicy();
            currentNode.backUp(winner);
            if (this.board != null) {
                this.board.jpb.setValue(i);
            }
        }
        Map.Entry<Action, Node> bestChildOject = root.bestChild(0.0);
        Action bestNextAction = bestChildOject.getKey();
        return bestNextAction;
    }

    /**
     * 选择与扩展
     *
     * @param node 当前节点
     * @return 返回一个值得继续模拟的子节点
     */
    public Node treePolicy(Node node) {
        while (!node.isTerminalNode()) {
            if (!node.isFullyExpanded()) {
                return node.expand();
            } else {
                Map.Entry<Action, Node> bestChildObject = node.bestChild(C_Param);
                node = bestChildObject.getValue();
            }
        }
        return node;
    }

    /**
     * 迭代深化搜索。随着场上棋子数量增加，搜索次数增多。当超过设定的最大搜索次数时，不会继续增加次数。
     * 如果迭代常量为0，则表示不启用迭代加深，将按照最大搜索次数进行搜索
     * @param board
     * @return
     */
    public static int iterativeDeepening(Board board) {
            if (MAX_CACULATE_TIMES <= INIT_CACULATE_TIMES) {
                return INIT_CACULATE_TIMES; //若初始更大，返回初始
            }
            if (ITERATIVE_DEEPENING_COUNT == 0) {
                return MAX_CACULATE_TIMES;  //若步进0，表示不启用，则直接返回最大
            }
            int depth = board.chessCount;   //以场上的棋子数作为步进
            int addTimes = depth * (int)(ITERATIVE_DEEPENING_COUNT / 2);   //扩大到预先设定的倍数
            int currentTimes = INIT_CACULATE_TIMES + addTimes;
            if (currentTimes >= MAX_CACULATE_TIMES) {
                return MAX_CACULATE_TIMES;  //当到达最大搜索次数时，不再增加次数
            }
            return currentTimes;
    }
}
