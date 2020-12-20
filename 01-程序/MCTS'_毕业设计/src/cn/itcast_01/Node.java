package cn.itcast_01;

import MCTS_001.Board;
import MCTS_001.BoardConfig;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * 定义了节点与节点相关的操作
 */
public class Node implements BoardConfig {
    public State state;
    public Node parent;
    public HashMap<Action, Node> children = new HashMap<Action, Node>();
    public LinkedList<Action> untriedActions;
    public double Q_FOR_COMPUTER = 0.0;   //这里的Q是针对电脑方来说的
    public int N = 0;

    public Node(State state, Node parent) {
        this.state = state;
        this.parent = parent;
        this.untriedActions = state.getUntriedActions();
    }

    /**
     * 通过当前节点下所有可走的动作，产生一个子节点
     * 因为只有当所有子节点的N值都不为0的时候，才算是完全扩展
     * 因此在判断的时候，如果存在子节点全部产生，但是有的子节点的N值为0的情况，就会导致bug
     * 因此先看一下untriedActions里面，是否所有子节点已经产生
     * 如果全部产生，再看一下子节点集合中是否存在还没访问过的节点。如果存在，就从子节点里面选
     *
     * @return 产生一个新的子节点
     */
    public Node expand() {
        Node newChild = null;
        if (this.untriedActions.size() != 0) {
            //选出一个动作，并将此动作从“可选择的行动集合”中删除
            //由于在建立“可选择的行动集合”的时候的顺序已经洗牌过，因此pop相当于随机选一个
            Action nextAction = this.untriedActions.pop();
            //获得下一步的局面
            State nextState = this.state.getNextState(nextAction);
            newChild = new Node(nextState, this);
            //将节点信息与对应动作在子节点列表中注册
            this.children.put(nextAction, newChild);
        }
        return newChild;
    }

    /**
     * 用于判断一个节点是否是终端节点
     * 如果棋局没赢，说明不是终端节点。如果棋局已定，说明是终端节点
     *
     * @return
     */
    public boolean isTerminalNode() {
        return this.state.isOver() != NOT_WIN;
    }

    /**
     * 判断节点是否完全展开。注意，完全展开指的是所有子节点至少被访问过一次
     *
     * @return
     */
    public boolean isFullyExpanded() {
        return untriedActions.size() == 0;
    }

    /**
     * 默认策略，随机落子，直到一方胜利或者平局
     *
     * @return 模拟结果
     */
    public int defaultPolicy() {
        //需要把当前局面隔离开:
        int thisResult = this.state.isOver();   //这里可以优化，返回is_over, winner就可以少判断一次
        if (thisResult != NOT_WIN) {
            return thisResult;
        }
        State currentState = this.state.stateDeepCopy();
        int result = NOT_WIN;
        while (result == NOT_WIN) {
            LinkedList<Action> availableActions = currentState.getUntriedActions();
            Action action = Node.rollout(availableActions);
            currentState.board[action.x][action.y] = currentState.player;
            int nextPlayer = State.getOpponent(currentState.player);
            currentState.player = nextPlayer;
            result = Board.checkAllStatus(currentState.board, action.x, action.y);
        }
        return result; //出来的时候，已经不可能是NOT_WIN了，只有赢，输和全下满
    }

    /**
     * 随机选择：在可选的动作组中随机挑选一个动作
     *
     * @param availableActons 可选择的动作集合
     * @return
     */
    public static Action rollout(LinkedList<Action> availableActons) {
        return availableActons.pop();
    }

    /**
     * 当前节点进行反向传播。反向传播是改变当前节点的属性，而不是改变父节点的属性
     *
     * @param winner rollout模拟后的结果
     */
    public void backUp(int winner) {
        //相对于电脑方来说，因此需要分情况讨论
        N++;
        int currentPlayer = state.player;
        if (currentPlayer == COMPUTER_COLOR) {  //当前回合是电脑回合
            if (winner == currentPlayer) {  //如果电脑赢了
                Q_FOR_COMPUTER += Q_WIN_REWARD;
            } else {
                Q_FOR_COMPUTER += Q_LOSE_REWARD;
            }
        } else {    //当前回合是人类回合
            if (winner == currentPlayer) {  //如果人类赢了
                Q_FOR_COMPUTER += Q_LOSE_REWARD;
            } else {
                Q_FOR_COMPUTER += Q_WIN_REWARD;
            }
        }
        if (winner == ALL_FILED) {
            Q_FOR_COMPUTER += Q_EQUAL_REWARD;
        }
        if (parent != null) {  //如果父节点存在，就继续向上传播
            parent.backUp(winner);
        }
    }

    /**
     * 返回UCB值最大的键值对对象而不是节点
     * 可以根据键值对对象获取键或者值
     * 键对应最佳动作，值就是最佳动作下的子节点
     *
     * @param c 在进行公式计算时设置的超参数
     * @return 一个最优的子节点的键值对对象（bestAction,bestChildNode）
     */
    public Map.Entry<Action, Node> bestChild(double c) {
        double result = Double.NEGATIVE_INFINITY;   //初始置为负无穷大
        double tempResult;
        double left;
        double right;
        Map.Entry<Action, Node> bestChildObject = null;
        int temp_N = Integer.MIN_VALUE;
        Set<Map.Entry<Action, Node>> set = children.entrySet();
        for (Map.Entry<Action, Node> childObject : set) {
            Node child = childObject.getValue();
            if (c == 0.0) {     //如果是最后一步，挑访问次数多的
                if (child.N >= temp_N) {
                    temp_N = child.N;
                    bestChildObject = childObject;
                }
            } else {    //如果不是最后一步，计算UCB1
                left = child.Q_FOR_COMPUTER / child.N;
                right = c * Math.sqrt((2 * Math.log(N)) / child.N);
                tempResult = left + right;
                if (tempResult > result) {
                    result = tempResult;
                    bestChildObject = childObject;
                }
            }
        }
        return bestChildObject;
    }
}
