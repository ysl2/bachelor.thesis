package cn.itcast_01;

import MCTS_001.Board;
import MCTS_001.BoardConfig;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * 用于描述游戏状态，定义了与游戏状态相关的操作
 */
public class State implements BoardConfig {
    public int[][] board;
    public int player;

    public State(int[][] board, int player) {
        this.board = State.boardDeepCopy(board);
        this.player = player;
    }

    /**
     * 用于深层复制一个二维数组。复制后产生的新二维数组具有完全不同的地址，不会与原数组发生冲突。
     *
     * @param board
     * @return
     */
    public static int[][] boardDeepCopy(int[][] board) {
        int[][] duplicateBoard = new int[board.length][];
        for (int i = 0; i < duplicateBoard.length; i++) {
            duplicateBoard[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return duplicateBoard;
    }

    /**
     * 返回传入的玩家的对手
     *
     * @param player
     * @return
     */
    public static int getOpponent(int player) {
        return (player == COMPUTER_COLOR) ? HUMAN_COLOR : COMPUTER_COLOR;
    }


    /**
     * 获得“可选择的行动组”。注意，此行动组的顺序被故意打乱，以保证随机性。
     *
     * @return 可选择的行动组
     */
    public LinkedList<Action> getUntriedActions() {
        LinkedList<Action> untriedActons = new LinkedList<Action>();
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (this.board[i][j] == NOCHESS) {
                    Action action = new Action(i, j);
                    untriedActons.add(action);
                }
            }
        }
        Collections.shuffle(untriedActons);     //打乱顺序
        return untriedActons;
    }

    /**
     * 判断当前局面并返回判断结果
     *
     * @return
     */
    public int isOver() {
        int counter = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == NOCHESS) {
                    continue;
                }
                int result = Board.checkWinner(board, i, j);
                if (result != NOT_WIN) {
                    return result;
                }
                counter++;
            }
        }
        if (counter >= BOARD_SIZE * BOARD_SIZE) {
            return ALL_FILED;
        }
        return NOT_WIN;
    }

    /**
     * 用于深层复制当前局面，返回的新局面与当前局面地址不同，完全隔离
     *
     * @return
     */
    public State stateDeepCopy() {
        return new State(this.board, this.player);
    }

    /**
     * 在当前局面的基础上，根据一个动作产生一个新的局面
     *
     * @param action
     * @return
     */
    public State getNextState(Action action) {
        //获得下一步的玩家
        int nextPlayer = State.getOpponent(this.player);
        //产生下一个局面
        State nextState = new State(this.board, nextPlayer);
        //在新局面中，注册动作
        nextState.board[action.x][action.y] = this.player;
        return nextState;
    }
}
