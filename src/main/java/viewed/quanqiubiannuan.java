package viewed;

import java.util.Scanner;

/**
 * @description:
 * @author：Favor
 * @date: 2024/4/9
 */
public class quanqiubiannuan {
    static Scanner sc = new Scanner(System.in);
    static int N = sc.nextInt();

    static char[][] arr = new char[N][N];
    static int[][] flag = new int[N][N];
    static int[][] dir = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };

    //                                                                      #
    //依题意，如果岛屿不被水淹，则岛屿中《存在》一个‘#’四周都有邻接的‘#’，类似这样  ### 环形的结构，
    //所以题目要求找出《不存在》这个环形结构的岛屿。                              #

    //记录岛屿中有多少个环形结构
    static int circle_count = 0;
    //记录符合题目要求的岛屿数
    static int count = 0;

    public static void main(String[] args) {

        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.next().toCharArray();
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {

                //从‘#’开始进行DFS搜索
                if (arr[i][j] == '#' && flag[i][j] == 0) {

                    //重置参数
                    circle_count = 0;

                    //开始遍历岛屿中所有的‘#’
                    DFS(i, j);

                    //如果不存在环形结构
                    if (circle_count == 0) {
                        count++;
                    }

                }
            }
        }
        System.out.println(count);
    }

    public static void DFS(int i, int j) {

        flag[i][j] = 1;

        //该‘#’周围一圈是否是‘#’
        if (fun(i, j)) {
            circle_count++;
        }

        for (int k = 0; k < dir.length; k++) {

            int dx = i + dir[k][0];
            int dy = j + dir[k][1];

            //！！！！因为周围边界全是海洋，所有不需要判断边界，否则这里要写判断dx,dy是否越界的语句

            //搜索周围的‘#’
            if (arr[dx][dy] == '#' && flag[dx][dy] == 0) {
                DFS(dx, dy);
            }

        }
    }

    //             #
    //岛屿中是否存在### 的结构
    //             #
    public static boolean fun(int i, int j) {

        //遍历四个方向，一旦存在一个‘.’，则返回false，只有四个方向都有‘#’才返回ture
        for (int k = 0; k < dir.length; k++) {

            int dx = i + dir[k][0];
            int dy = j + dir[k][1];

            //同样，这里无需考虑dx,dy是否越界

            if (arr[dx][dy] == '.') {
                return false;
            }
        }
        return true;
    }
}
