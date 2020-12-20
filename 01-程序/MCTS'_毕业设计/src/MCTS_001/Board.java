package MCTS_001;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 面板类
 */
public class Board extends JPanel implements BoardConfig {
    //成员变量：
    public int[][] chessArray = new int[BOARD_SIZE][BOARD_SIZE];    //存储棋子，NOCHESS表示没棋，BLACK表示黑棋，WHITE表示白棋
    public int round = FIRST_HAND;   //用于回合的标记以及先手的判断。在BoardConfig的FIRST_HAND中可以设置先后手
    public Position p = new Position(-1, -1);   //棋子的横纵坐标
    public int chessCount = 0;
    public Computer computer = new Computer(this);

    public boolean canPlay = true;  //  是否能继续玩的标记。初始是可以玩的
    public JTextArea text = new JTextArea();    //用于显示消息
    public JScrollPane scroll = new JScrollPane(text);  //滑动条，用于滚动文本域
    public JProgressBar jpb = new JProgressBar();
    ;

    public Thread th1;  //用来存放电脑棋手的线程

    //构造器：
    public Board() {
        init();     //用于初始化窗体
    }

    /**
     * 用于初始化窗体与组件的方法
     */
    public void init() {
        if (COMPUTER_COLOR == round) {   //如果电脑是先手的话
            chessArray[(int) (BOARD_SIZE / 2)][(int) (BOARD_SIZE / 2)] = COMPUTER_COLOR;   //测试用的，黑棋
            chessCount = 1;
            round = HUMAN_COLOR;
        }
        //创建组件
        JFrame jf = new JFrame();
        JButton button1 = new JButton("重置棋盘");
        //窗体初始化
        jf.setTitle("基于Monte Carlo方法的五子棋算法设计与实现");
        jf.setBounds(SCREEN_CENTER_WIDTH, SCREEN_CENTER_HEIGHT, WINDOW_WIDTH, WINDOW_HEIGHT);
        jf.setLayout(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setResizable(true);
        //主面板初始化
        this.setBounds(0, 0, BOARD_LENGTH + BOARD_SCALE, BOARD_LENGTH + BOARD_SCALE);
        this.setBackground(BOARD_COLOR);
        this.addMouseListener(new PanelMouseAdapter());
        //按钮初始化
        button1.setBounds(BUTTON_CENTER_WIDTH, BUTTON_CENTER_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        button1.addActionListener(new ButtonActionListener());
        //文本域初始化
        scroll.setBounds(TEXTAREA_CENTER_WIDTH, TEXTAREA_CENTER_HEIGHT, TEXTAREA_WIDTH, TEXTAREA_HEIGHT);
        Font font = new Font("微软雅黑", Font.PLAIN, 16);
        text.setFont(font);
        text.setLineWrap(true);
        DefaultCaret caret = (DefaultCaret) text.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        //进度条初始化
        jpb.setBounds(PROGRESSBAR_CENTER_WIDTH, PROGRESSBAR_CENTER_HEIGHT, PROGRESSBAR_WIDTH, PROGRESSBAR_HEIGHT);
        jpb.setBorderPainted(true);
        jpb.setMaximum(INIT_CACULATE_TIMES - 1);
        jpb.setMinimum(0);
        //添加组件并显示
        jf.add(button1);
        jf.add(this);
        jf.add(scroll);
        jf.add(jpb);
        jf.setVisible(true);
    }

    /**
     * 用于画出棋盘的方法
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        //画出棋盘
        for (int i = 1; i <= BOARD_SIZE; i++) {
            for (int j = 1; j <= BOARD_SIZE; j++) {
                g.drawLine(BOARD_SCALE, BOARD_SCALE * j, BOARD_LENGTH, BOARD_SCALE * j);
                g.drawLine(BOARD_SCALE * i, BOARD_SCALE, BOARD_SCALE * i, BOARD_LENGTH);
            }
        }

        //画出棋子
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                //画黑棋
                if (chessArray[i][j] == BLACK) {
                    int locationx = i * BOARD_SCALE + BOARD_SCALE;      //加一个scale是因为棋盘左边空了一溜
                    int locationy = j * BOARD_SCALE + BOARD_SCALE;      //加一个scale是因为棋盘上边空了一溜
                    g.setColor(Color.BLACK);
                    g.fillOval((int) (locationx - (BOARD_SCALE * CHESS_RATIO)), (int) (locationy - (BOARD_SCALE * CHESS_RATIO)), (int) (2 * (BOARD_SCALE * CHESS_RATIO)), (int) (2 * (BOARD_SCALE * CHESS_RATIO)));
                }
                //画白棋
                else if (chessArray[i][j] == WHITE) {
                    int locationx = i * BOARD_SCALE + BOARD_SCALE;      //加一个scale是因为棋盘左边空了一溜
                    int locationy = j * BOARD_SCALE + BOARD_SCALE;      //加一个scale是因为棋盘上边空了一溜
                    g.setColor(Color.BLACK);
                    g.drawOval((int) (locationx - (BOARD_SCALE * CHESS_RATIO)), (int) (locationy - (BOARD_SCALE * CHESS_RATIO)), (int) (2 * (BOARD_SCALE * CHESS_RATIO)), (int) (2 * (BOARD_SCALE * CHESS_RATIO)));
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillOval((int) (locationx - (BOARD_SCALE * CHESS_RATIO)), (int) (locationy - (BOARD_SCALE * CHESS_RATIO)), (int) (2 * (BOARD_SCALE * CHESS_RATIO)), (int) (2 * (BOARD_SCALE * CHESS_RATIO)));
                }
            }
        }
        jpb.setValue(0);
        System.out.println("当前棋子数：" + chessCount + "\n");
        if (chessCount >= BOARD_SIZE * BOARD_SIZE) {
            canPlay = false;
            text.append("平局，请重置棋盘\n");
        }
    }

    /**
     * 判断棋盘状态的主方法，包括是否输赢或者是否平局
     *
     * @param board 一个棋盘
     * @param x     棋盘x坐标
     * @param y     棋盘y坐标
     * @return 返回四种状态：HUMAN_WIN     COMPUTER_WIN    ALL_FILED   NOT_WIN
     */
    public static int checkAllStatus(int[][] board, int x, int y) {
        int isWinner = checkWinner(board, x, y);
        if (isWinner != NOT_WIN) {
            return isWinner;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == NOCHESS) {
                    return NOT_WIN;
                }
            }
        }
        return ALL_FILED;
    }

    /**
     * 用于判断输赢的主方法
     *
     * @param x 最后一个落子位置的x索引
     * @param y 最后一个落子位置的y索引
     * @return 赢了就返回胜利方。没产生输赢就返回NOT_WIN，但是还要继续判断是否真的是NOT_WIN
     */
    public static int checkWinner(int[][] board, int x, int y) { //用于判断输赢
        int count;
        count = checkCount(0, 1, board, x, y);     //纵判断
        if (count >= WIN_LENGTH) {
            return board[x][y];
        }
        for (int i = -1; i <= 1; i++) {     //其他方向的判断
            count = checkCount(1, i, board, x, y);
            if (count >= WIN_LENGTH) {
                return board[x][y];
            }
        }
        return NOT_WIN;
    }

    /**
     * 判断输赢的内部方法
     *
     * @param a 由主函数调用
     * @param b 由主函数调用
     * @param x 主函数的形参x
     * @param y 主函数的形参y
     * @return 赢了返回true，没赢返回false
     */
    public static int checkCount(int a, int b, int[][] board, int x, int y) {    //判断输赢的内部方法
        int count = 1;
        int tempA = a;
        int tempB = b;

        for (int i = 1; i >= -1; i -= 2) {
            while (x + i * a >= 0 && x + i * a <= BOARD_SIZE - 1 && y + i * b >= 0 && y + i * b <= BOARD_SIZE - 1 && board[x][y] == board[x + i * a][y + i * b]) {
                count++;
                if (a != 0) {
                    a++;
                }
                if (b > 0) {
                    b++;
                    continue;
                }
                if (b < 0) {
                    b--;
                }
            }
            a = tempA;
            b = tempB;
        }
        return count;
    }

    //成员内部类：

    /**
     * 用于鼠标监听的类
     */
    class PanelMouseAdapter extends MouseAdapter {  //用于给面板加入鼠标监听事件
        @Override
        public void mousePressed(MouseEvent e) {
            if (canPlay == false) { //如果压根就没法下棋的话，
                text.append("胜负已定或平局，无法继续下棋。请重置棋盘\n");
                return;
            }
            if (chessCount >= BOARD_SIZE * BOARD_SIZE) {
                text.append("平局，无法继续下棋，请重置棋盘\n");
                return;
            }
            if (round != HUMAN_COLOR) { //如果能下棋，但是不是人的回合
                text.append("电脑正在考虑，请等一会儿\n");
                return;
            }
            int locationx = e.getX();
            int locationy = e.getY();
            System.out.println("locationx:" + locationx + "  " + "locationy:" + locationy + "\n");
            //判断鼠标点击位置是否落在棋盘内：（注意：向棋盘外面扩展了半个单位长度）
            if (!(locationx >= BOARD_SCALE * 0.5 && locationx <= BOARD_LENGTH + (BOARD_SCALE * 0.5) && locationy >= BOARD_SCALE * 0.5 && locationy <= BOARD_LENGTH + (BOARD_SCALE * 0.5))) {
                return;     //如果不在范围内，就返回。否则继续执行下面的代码
            }
            System.out.println("落在范围内" + "\n");
            p.x = (locationx - (locationx / BOARD_SCALE)) / BOARD_SCALE;
            p.y = (locationy - (locationy / BOARD_SCALE)) / BOARD_SCALE;
            System.out.println("转换地址成功");   //测试语句
            try {
                int tempPosition = chessArray[p.x][p.y];
            } catch (ArrayIndexOutOfBoundsException e2) {
                System.out.println("ArraysIndexOutOfBoundsException");
                return;
            }
            if (chessArray[p.x][p.y] != NOCHESS) {  //判断此处是否已经有棋
                text.append("此处已经有棋了，请在别的地方下\n");
                return;
            }
            chessArray[p.x][p.y] = HUMAN_COLOR;
            chessCount++;
            System.out.println("写入地址成功");   //测试语句
            text.append("棋手： " + "(" + p.x + "," + p.y + ")" + "\n");
            text.append("当前棋子数：  " + chessCount + "\n");
            round = COMPUTER_COLOR;
            repaint();
            //先看赢没赢，赢了直接不让下。没赢，看看满了没有。满了就平局不让下，没满就让电脑下
            if (checkWinner(chessArray, p.x, p.y) == HUMAN_WIN) {    //赢了，直接不让下了
                text.append("棋手胜。请重置开始新的游戏\n");
                canPlay = false;
                return;
            }
            //这个if不知道是什么原因，只有在电脑先手的时候才起作用
            if (chessCount >= BOARD_SCALE * BOARD_SCALE) { //没赢还满了，输出平局的信息，不让继续下了
                //text.append("平局，请重置开始新的游戏\n");
                canPlay = false;    //其他变量先不用初始化，等按下按钮的时候再给初始化
                return;
            }
            //没赢，但是没满
            if (canPlay == true && chessCount < BOARD_SIZE * BOARD_SIZE) {
                th1 = new Thread(computer);
                th1.start();   //启动电脑线程
            }
        }
    }

    /**
     * 用于按钮监听的类
     */
    class ButtonActionListener implements ActionListener {  //用于给按钮添加动作监听事件
        @Override
        public void actionPerformed(ActionEvent e) {
            if (th1 != null && th1.isAlive()) {    //如果线程存在并且还开着的话才用关。否则不用关
                th1.stop();
                jpb.setValue(0);
            }
            chessArray = new int[BOARD_SIZE][BOARD_SIZE];   //清空棋盘
            round = FIRST_HAND;     //把回合恢复成先手方
            if (COMPUTER_COLOR == FIRST_HAND) {   //如果电脑是先手的话，先让电脑下一个棋，然后再让人下
                chessArray[(int) (BOARD_SIZE / 2)][(int) (BOARD_SIZE / 2)] = COMPUTER_COLOR;   //测试用的，黑棋
                chessCount = 1;
            } else {    //如果电脑是后手，就不下了，直接让人下
                chessCount = 0;
            }
            round = HUMAN_COLOR;    //都安排完了之后，再让回合给人类
            canPlay = true;
            text.setText("棋盘已经重置\n");
            repaint();
        }
    }
}