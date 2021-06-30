package cn.itcast_01;

import MCTS_001.Board;
import MCTS_001.BoardConfig;

import java.util.Map;

/**
 * 定义了MCTS的基本步骤与树策略
 */
public class MCTS implements BoardConfig {
    public Board board = null;

    // 构造器
    public MCTS(Board board) {
        this.board = board;
    }

    private MCTS() {
    }
    // 成员方法：

    /**
     * 这是UCT搜索的主方法
     *
     * @param chessArray 一个棋盘状态
     * @param player     当前的下棋方
     * @return 一个最优动作
     */
    public Action UCTSearch(int[][] chessArray, int player) {
        // 先判断是否产生bug情况，如果产生了bug，就直接返回修复结果
        Action tempAction = fixBug(chessArray);
        if (tempAction.x != -1) {
            return tempAction;
        }
        State state = new State(chessArray, player);
        Node root = new Node(state, null);
        int sumSarchTimes = MCTS.iterativeDeepening(this.board); // 当前搜索次数
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
     *
     * @param board
     * @return
     */
    public static int iterativeDeepening(Board board) {
        if (MAX_CACULATE_TIMES <= INIT_CACULATE_TIMES) {
            return INIT_CACULATE_TIMES; // 若初始更大，返回初始
        }
        if (ITERATIVE_DEEPENING_COUNT == 0) {
            return MAX_CACULATE_TIMES; // 若步进0，表示不启用，则直接返回最大
        }
        int depth = board.chessCount; // 以场上的棋子数作为步进
        int addTimes = depth * (int) (ITERATIVE_DEEPENING_COUNT / 2); // 扩大到预先设定的倍数
        int currentTimes = INIT_CACULATE_TIMES + addTimes;
        if (currentTimes >= MAX_CACULATE_TIMES) {
            return MAX_CACULATE_TIMES; // 当到达最大搜索次数时，不再增加次数
        }
        return currentTimes;
    }

    // quick fix
    public static Action fixBug(int[][] chessArray) {
        // 电脑 四
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[i].length; j++) {
                // 如果当前位置是玩家的，或者没有棋，就直接略过。
                if (chessArray[i][j] == HUMAN_COLOR || chessArray[i][j] == NOCHESS) {
                    continue;
                }
                // 判断上斜向四
                if (i - 3 > 0 && j + 3 < chessArray.length &&
                        chessArray[i][j] == COMPUTER_COLOR &&
                        chessArray[i - 1][j + 1] == COMPUTER_COLOR &&
                        chessArray[i - 2][j + 2] == COMPUTER_COLOR &&
                        chessArray[i - 3][j + 3] == COMPUTER_COLOR) {
                    if (i + 1 < chessArray.length && j - 1 >= 0 && chessArray[i + 1][j - 1] == NOCHESS) {
                        Action tempResult = new Action(i + 1, j - 1);
                        return tempResult;
                    }
                    if (i - 4 >= 0 && j + 4 < chessArray.length && chessArray[i - 4][j + 4] == NOCHESS) {
                        Action tempResult = new Action(i - 4, j + 4);
                        return tempResult;
                    }
                }
                // 判断下斜向四
                if (i + 3 < chessArray.length && j + 3 < chessArray.length &&
                        chessArray[i][j] == COMPUTER_COLOR &&
                        chessArray[i + 1][j + 1] == COMPUTER_COLOR &&
                        chessArray[i + 2][j + 2] == COMPUTER_COLOR &&
                        chessArray[i + 3][j + 3] == COMPUTER_COLOR) {
                    if (i - 1 >= 0 && j - 1 >= 0 && chessArray[i - 1][j - 1] == NOCHESS) {
                        Action tempResult = new Action(i - 1, j - 1);
                        return tempResult;
                    }
                    if (i + 4 < chessArray.length && j + 4 < chessArray.length && chessArray[i + 4][j + 4] == NOCHESS) {
                        Action tempResult = new Action(i + 4, j + 4);
                        return tempResult;
                    }
                }
                // 判断横向四
                if (j - 1 >= 0 && j + 3 < chessArray.length &&
                        chessArray[i][j] == COMPUTER_COLOR &&
                        chessArray[i][j + 1] == COMPUTER_COLOR &&
                        chessArray[i][j + 2] == COMPUTER_COLOR &&
                        chessArray[i][j + 3] == COMPUTER_COLOR) {
                    if (chessArray[i][j - 1] == NOCHESS) {
                        Action tempResult = new Action(i, j - 1);
                        return tempResult;
                    }
                    if (j + 4 < chessArray.length && chessArray[i][j + 4] == NOCHESS) {
                        Action tempResult = new Action(i, j + 4);
                        return tempResult;
                    }
                }
                // 判断纵向四
                if (i - 1 >= 0 && i + 3 < chessArray.length &&
                        chessArray[i][j] == COMPUTER_COLOR &&
                        chessArray[i + 1][j] == COMPUTER_COLOR &&
                        chessArray[i + 2][j] == COMPUTER_COLOR &&
                        chessArray[i + 3][j] == COMPUTER_COLOR) {
                    if (chessArray[i - 1][j] == NOCHESS) {
                        Action tempResult = new Action(i - 1, j);
                        return tempResult;
                    }
                    if (i + 4 < chessArray.length && chessArray[i + 4][j] == NOCHESS) {
                        Action tempResult = new Action(i + 4, j);
                        return tempResult;
                    }
                }
            }
        }

        // 玩家 四
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[i].length; j++) {
                // 如果当前位置是电脑的，或者没有棋，就直接略过。
                if (chessArray[i][j] == COMPUTER_COLOR || chessArray[i][j] == NOCHESS) {
                    continue;
                }
                // 判断上斜向四
                if (i - 3 > 0 && j + 3 < chessArray.length &&
                        chessArray[i][j] == HUMAN_COLOR &&
                        chessArray[i - 1][j + 1] == HUMAN_COLOR &&
                        chessArray[i - 2][j + 2] == HUMAN_COLOR &&
                        chessArray[i - 3][j + 3] == HUMAN_COLOR) {
                    if (i + 1 < chessArray.length && j - 1 >= 0 && chessArray[i + 1][j - 1] == NOCHESS) {
                        Action tempResult = new Action(i + 1, j - 1);
                        return tempResult;
                    }
                    if (i - 4 >= 0 && j + 4 < chessArray.length && chessArray[i - 4][j + 4] == NOCHESS) {
                        Action tempResult = new Action(i - 4, j + 4);
                        return tempResult;
                    }
                }
                // 判断下斜向四
                if (i + 3 < chessArray.length && j + 3 < chessArray.length &&
                        chessArray[i][j] == HUMAN_COLOR &&
                        chessArray[i + 1][j + 1] == HUMAN_COLOR &&
                        chessArray[i + 2][j + 2] == HUMAN_COLOR &&
                        chessArray[i + 3][j + 3] == HUMAN_COLOR) {
                    if (i - 1 >= 0 && j - 1 >= 0 && chessArray[i - 1][j - 1] == NOCHESS) {
                        Action tempResult = new Action(i - 1, j - 1);
                        return tempResult;
                    }
                    if (i + 4 < chessArray.length && j + 4 < chessArray.length && chessArray[i + 4][j + 4] == NOCHESS) {
                        Action tempResult = new Action(i + 4, j + 4);
                        return tempResult;
                    }
                }
                // 判断横向四
                if (j - 1 >= 0 && j + 3 < chessArray.length &&
                        chessArray[i][j] == HUMAN_COLOR &&
                        chessArray[i][j + 1] == HUMAN_COLOR &&
                        chessArray[i][j + 2] == HUMAN_COLOR &&
                        chessArray[i][j + 3] == HUMAN_COLOR) {
                    if (chessArray[i][j - 1] == NOCHESS) {
                        Action tempResult = new Action(i, j - 1);
                        return tempResult;
                    }
                    if (j + 4 < chessArray.length && chessArray[i][j + 4] == NOCHESS) {
                        Action tempResult = new Action(i, j + 4);
                        return tempResult;
                    }
                }
                // 判断纵向四
                if (i - 1 >= 0 && i + 3 < chessArray.length &&
                        chessArray[i][j] == HUMAN_COLOR &&
                        chessArray[i + 1][j] == HUMAN_COLOR &&
                        chessArray[i + 2][j] == HUMAN_COLOR &&
                        chessArray[i + 3][j] == HUMAN_COLOR) {
                    if (chessArray[i - 1][j] == NOCHESS) {
                        Action tempResult = new Action(i - 1, j);
                        return tempResult;
                    }
                    if (i + 4 < chessArray.length && chessArray[i + 4][j] == NOCHESS) {
                        Action tempResult = new Action(i + 4, j);
                        return tempResult;
                    }
                }
            }
        }

        // 电脑 活三
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[i].length; j++) {
                // 如果当前位置是玩家的，或者没有棋，就直接略过。
                if (chessArray[i][j] == HUMAN_COLOR || chessArray[i][j] == NOCHESS) {
                    continue;
                }
                // 判断上斜向活三
                if (i + 1 < chessArray.length && i - 3 >= 0 && j - 1 >= 0 && j + 3 < chessArray.length &&
                        chessArray[i + 1][j - 1] == NOCHESS &&
                        chessArray[i][j] == COMPUTER_COLOR &&
                        chessArray[i - 1][j + 1] == COMPUTER_COLOR &&
                        chessArray[i - 2][j + 2] == COMPUTER_COLOR &&
                        chessArray[i - 3][j + 3] == NOCHESS) {
                    Action tempResult = new Action(i + 1, j - 1);
                    return tempResult;
                }
                // 判断下斜向活三
                if (i - 1 >= 0 && j - 1 >= 0 && i + 3 < chessArray.length && j + 3 < chessArray.length &&
                        chessArray[i - 1][j - 1] == NOCHESS &&
                        chessArray[i][j] == COMPUTER_COLOR &&
                        chessArray[i + 1][j + 1] == COMPUTER_COLOR &&
                        chessArray[i + 2][j + 2] == COMPUTER_COLOR &&
                        chessArray[i + 3][j + 3] == NOCHESS) {
                    Action tempResult = new Action(i - 1, j - 1);
                    return tempResult;
                }
                // 判断横向活三
                if (j - 1 >= 0 && j + 3 < chessArray.length &&
                        chessArray[i][j - 1] == NOCHESS &&
                        chessArray[i][j] == COMPUTER_COLOR &&
                        chessArray[i][j + 1] == COMPUTER_COLOR &&
                        chessArray[i][j + 2] == COMPUTER_COLOR &&
                        chessArray[i][j + 3] == NOCHESS) {
                    Action tempResult = new Action(i, j - 1);
                    return tempResult;
                }
                // 判断纵向活三
                if (i - 1 >= 0 && i + 3 < chessArray.length &&
                        chessArray[i - 1][j] == NOCHESS &&
                        chessArray[i][j] == COMPUTER_COLOR &&
                        chessArray[i + 1][j] == COMPUTER_COLOR &&
                        chessArray[i + 2][j] == COMPUTER_COLOR &&
                        chessArray[i + 3][j] == NOCHESS) {
                    Action tempResult = new Action(i - 1, j);
                    return tempResult;
                }
            }
        }

        // 玩家 活三
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[i].length; j++) {
                // 如果当前位置是电脑的，或者没有棋，就直接略过。
                if (chessArray[i][j] == COMPUTER_COLOR || chessArray[i][j] == NOCHESS) {
                    continue;
                }
                // 判断上斜向活三
                if (i + 1 < chessArray.length && i - 3 >= 0 && j - 1 >= 0 && j + 3 < chessArray.length &&
                        chessArray[i + 1][j - 1] == NOCHESS &&
                        chessArray[i][j] == HUMAN_COLOR &&
                        chessArray[i - 1][j + 1] == HUMAN_COLOR &&
                        chessArray[i - 2][j + 2] == HUMAN_COLOR &&
                        chessArray[i - 3][j + 3] == NOCHESS) {
                    Action tempResult = new Action(i + 1, j - 1);
                    return tempResult;
                }
                // 判断下斜向活三
                if (i - 1 >= 0 && j - 1 >= 0 && i + 3 < chessArray.length && j + 3 < chessArray.length &&
                        chessArray[i - 1][j - 1] == NOCHESS &&
                        chessArray[i][j] == HUMAN_COLOR &&
                        chessArray[i + 1][j + 1] == HUMAN_COLOR &&
                        chessArray[i + 2][j + 2] == HUMAN_COLOR &&
                        chessArray[i + 3][j + 3] == NOCHESS) {
                    Action tempResult = new Action(i - 1, j - 1);
                    return tempResult;
                }
                // 判断横向活三
                if (j - 1 >= 0 && j + 3 < chessArray.length &&
                        chessArray[i][j - 1] == NOCHESS &&
                        chessArray[i][j] == HUMAN_COLOR &&
                        chessArray[i][j + 1] == HUMAN_COLOR &&
                        chessArray[i][j + 2] == HUMAN_COLOR &&
                        chessArray[i][j + 3] == NOCHESS) {
                    Action tempResult = new Action(i, j - 1);
                    return tempResult;
                }
                // 判断纵向活三
                if (i - 1 >= 0 && i + 3 < chessArray.length &&
                        chessArray[i - 1][j] == NOCHESS &&
                        chessArray[i][j] == HUMAN_COLOR &&
                        chessArray[i + 1][j] == HUMAN_COLOR &&
                        chessArray[i + 2][j] == HUMAN_COLOR &&
                        chessArray[i + 3][j] == NOCHESS) {
                    Action tempResult = new Action(i - 1, j);
                    return tempResult;
                }
            }
        }
        return new Action(-1, -1);
    }
}
