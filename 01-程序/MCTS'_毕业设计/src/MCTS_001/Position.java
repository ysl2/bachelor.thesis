package MCTS_001;

/**
 * 描述棋盘上最后一个落子的坐标
 */
public class Position implements BoardConfig{
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
