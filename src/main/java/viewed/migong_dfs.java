package viewed;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @description:
 * @author：Favor
 * @date: 2024/4/8
 */
public class migong_dfs {
    static int row=15,column=36;
    static int[][]m=new int[row+1][column+1];
    static boolean[][]flag=new boolean[row+1][column+1];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //在此输入您的代码...
        flag[0][0]=true;
        for (int i = 0; i <= row; i++) {
            String str=scan.nextLine();
            for (int j = 0; j <= column; j++) {
                m[i][j]= str.charAt(j)-48;
            }
        }
        ArrayList<Character> ans=new ArrayList<>();
        dfs(0,0,new ArrayList<>(),ans);
        for (Character an : ans) {
            System.out.print(an);
        }
        scan.close();
    }
    //DFS走迷宫但过于耗时
    public static void dfs(int i,int j,ArrayList<Character>tmp,ArrayList<Character>ans){
        if(m[i][j]==1)return;
        if(i==row&&j==column) {
            if(!ans.isEmpty()){
                if(ans.size()>tmp.size()){
                    ans.clear();
                    ans.addAll(tmp);
                }
            }else ans.addAll(tmp);
            return;
        }

        if(i+1<=row&&!flag[i+1][j]){
            flag[i+1][j]=true;
            tmp.add('D');
            dfs(i+1,j,tmp,ans);
            tmp.remove(tmp.size()-1);
            flag[i+1][j]=false;
        }

        if(j-1>=0&&!flag[i][j-1]){
            flag[i][j-1]=true;
            tmp.add('L');
            dfs(i,j-1,tmp,ans);
            tmp.remove(tmp.size()-1);
            flag[i][j-1]=false;
        }

        if(j+1<=column&& !flag[i][j + 1]){
            flag[i][j+1]=true;
            tmp.add('R');
            dfs(i,j+1,tmp,ans);
            tmp.remove(tmp.size()-1);
            flag[i][j+1]=false;
        }

        if(i-1>=0&&!flag[i-1][j]){
            flag[i-1][j]=true;
            tmp.add('U');
            dfs(i-1,j,tmp,ans);
            tmp.remove(tmp.size()-1);
            flag[i-1][j]=false;
        }
    }
}
