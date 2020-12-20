package MCTS_001;

import cn.itcast_01.MCTS;
import cn.itcast_01.Action;

/**
 * 电脑线程类
 */
public class Computer implements BoardConfig, Runnable {
    //成员变量：
    public Board board;

    //构造器：
    public Computer(Board board) {
        this.board = board;
    }

    /**
     * 电脑线程
     */
    @Override
    public void run() {
        if (board.canPlay == false) {
            board.text.append("当前状态胜负已分或平局，请重置棋盘\n");
            return;
        }
        if (board.round != COMPUTER_COLOR) {
            return;
        }
        String s1 = String.format("搜索%d次\n", MCTS.iterativeDeepening(this.board));   //记录当前的搜索次数
        String s2 = searchMethod();
        board.text.append("电脑： " + "(" + board.p.x + "," + board.p.y + ")  " + s2 + s1);  //在文本域中输出用时和搜索次数
        board.chessCount++;
        board.text.append("当前棋子数：  " + board.chessCount + "\n");
        board.repaint();
        //先看看电脑下完之后赢没赢，如果赢了，就不让人再下了。如果没赢，看看棋盘满了没有。如果满了，也不让人下了。如果没满，才让人下
        if (board.checkWinner(this.board.chessArray, board.p.x, board.p.y) == COMPUTER_WIN) {   //判断电脑赢了没有
            board.text.append("电脑胜，请重置开始新的游戏\n");
            board.canPlay = false;
            return;
        }
        //如果没赢
        if (board.chessCount >= BOARD_SIZE * BOARD_SIZE) {
            board.canPlay = false;
            return;
        }
        board.round = HUMAN_COLOR;  //没赢，但是棋盘没满，才让继续下
    }

    /**
     * 用于启动搜索的方法
     */
    //成员方法：
    public String searchMethod() {
        long start = System.currentTimeMillis();
        Action action = testMethod3();
        long end = System.currentTimeMillis();
        board.p.x = action.x;
        board.p.y = action.y;
        board.chessArray[action.x][action.y] = COMPUTER_COLOR;
        double mills = end - start;
        double second = mills * 0.001;
        return String.format("用时%.2f秒 ", second);   //记录搜索时间
    }

    /**
     * 用于调用MCTS的方法
     *
     * @return
     */
    public Action testMethod3() {
        MCTS mcts = new MCTS(this.board);
        Action action = mcts.UCTSearch(board.chessArray, COMPUTER_COLOR);
        return action;
    }
}


