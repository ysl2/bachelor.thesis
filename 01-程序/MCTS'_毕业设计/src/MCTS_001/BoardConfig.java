package MCTS_001;

import java.awt.Color;
import java.awt.Toolkit;

/**
 * 定义五子棋相关常量值
 */
public interface BoardConfig {
    //棋子相关：
    //这些都可以改：
    public static final int BLACK = 1;
    public static final int WHITE = -1;
    public static final int NOCHESS = 0;
    public static final double CHESS_RATIO = 0.3;   //棋子相对于棋盘格子的大小，此值为（棋子半径 / 棋盘格子），范围是0 ~ 0.5的小数，不能超过0.5，否则棋子会重叠
    public static final int WIN_LENGTH = 5;     //几子相连胜利。五子棋的话，这里就是5
    //局势相关：（可更改先手后手，但是人类与电脑的颜色必须相反。）
    //可更改的项目有：电脑执棋颜色，人类执棋颜色，先手颜色
    public static final int FIRST_HAND = BLACK;         //先手的颜色
    public static final int HUMAN_COLOR = WHITE;        //人类执棋颜色
    public static final int COMPUTER_COLOR = BLACK;     //电脑执棋颜色
    //下面这些不建议修改：
    public static final int HUMAN_WIN = HUMAN_COLOR;    //人类胜利标记
    public static final int COMPUTER_WIN = COMPUTER_COLOR;  //电脑胜利标记
    public static final int NOT_WIN = NOCHESS;
    public static final int ALL_FILED = Integer.MAX_VALUE;
    //棋盘相关：（可更改棋盘维度与标尺尺寸。推荐设置：BOARD_SIZE = 15, BOARD_SCALE = 45。SCALE过小可能导致窗体格式移位）
    public static final int BOARD_SIZE = 11;                                                                //定义棋盘格数
    public static final int BOARD_SCALE = 45;                                                               //定义棋盘里一个格子的单位长度，建议此值为奇数
    public static final int BOARD_LENGTH = (BOARD_SIZE + 0) * BOARD_SCALE;                                        //棋盘的长和宽（单位：像素）
    //窗体相关：
    public static final int WINDOW_WIDTH = (int) ((BOARD_LENGTH + BOARD_SCALE) / 0.6);                       //窗体的宽度
    public static final int WINDOW_HEIGHT = BOARD_SCALE + BOARD_LENGTH + BOARD_SCALE;                       //窗体的高度
    //按钮相关：
    public static final int BUTTON_WIDTH = 2 * BOARD_SCALE;     //按钮的宽度
    public static final int BUTTON_HEIGHT = BOARD_SCALE;        //按钮的高度
    public static final int BUTTON_CENTER_WIDTH = (int) (WINDOW_WIDTH - (BOARD_LENGTH + BOARD_SCALE) - BUTTON_WIDTH) / 2 + (BOARD_LENGTH + BOARD_SCALE);     //定义按钮居中，横坐标
    public static final int BUTTON_CENTER_HEIGHT = BOARD_SCALE;    //按钮的位置（高度）
    //  文本域相关
    public static final int TEXTAREA_WIDTH = WINDOW_WIDTH - (BOARD_LENGTH + BOARD_SCALE);
    public static final int TEXTAREA_HEIGHT = 3 * BOARD_SCALE;
    public static final int TEXTAREA_CENTER_HEIGHT = BUTTON_CENTER_HEIGHT + BUTTON_HEIGHT + BOARD_SCALE;
    public static final int TEXTAREA_CENTER_WIDTH = BOARD_LENGTH + BOARD_SCALE;
    //进度条相关：
    public static final int PROGRESSBAR_WIDTH = TEXTAREA_WIDTH;
    public static final int PROGRESSBAR_HEIGHT = (int) (0.25 * BOARD_SCALE);
    public static final int PROGRESSBAR_CENTER_HEIGHT = (int) (TEXTAREA_CENTER_HEIGHT + TEXTAREA_HEIGHT + 0.88 * BOARD_SCALE);
    public static final int PROGRESSBAR_CENTER_WIDTH = TEXTAREA_CENTER_WIDTH;
    //屏幕相关：
    public static final int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();    //获取屏幕宽度
    public static final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();  //获取屏幕高度
    public static final int SCREEN_CENTER_WIDTH = (int) (SCREEN_WIDTH - WINDOW_WIDTH) / 2;                  //屏幕中心横坐标，用于窗体居中
    public static final int SCREEN_CENTER_HEIGHT = (int) (SCREEN_HEIGHT - WINDOW_HEIGHT) / 2;               //屏幕中心纵坐标，用于窗体居中
    //颜色相关：
    public static final Color BOARD_COLOR = new Color(158, 198, 255);                               //棋盘的颜色
    //蒙特卡洛算法相关：
        //迭代深化搜索相关：
    public static final int INIT_CACULATE_TIMES = 38000;  //每次调用算法搜索的基础节点数
    public static final int ITERATIVE_DEEPENING_COUNT = 500;   //迭代加深常量，每迭代一次加这些搜索次数。0表示不启用迭代加深。不启用的时候默认按照最大搜索次数
    public static final int MAX_CACULATE_TIMES = 55000;     //最大搜索数。到了此值之后不会再增加搜索次数。
        //UCT相关：
    public static final double C_Param = 1 / Math.sqrt(2);   //这是UCT函数里面那个常量值
    public static final double Q_WIN_REWARD = 1;    //电脑模拟赢了的奖励
    public static final double Q_EQUAL_REWARD = 0;  //电脑模拟平局的奖励
    public static final double Q_LOSE_REWARD = -1;   //电脑模拟输了的奖励
}
