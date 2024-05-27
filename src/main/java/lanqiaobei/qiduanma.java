package lanqiaobei;

import java.util.ArrayList;

/**
 * @description:
 * @author：Favor
 * @date: 2024/4/9
 */
public class qiduanma {
    static ArrayList<int[]> graph=new ArrayList<>();
    public static void main(String[] args) {
        int ans=0;
        graph.add(new int[]{1,5});
        graph.add(new int[]{0,2,6});
        graph.add(new int[]{1,3,6});
        graph.add(new int[]{2,4});
        graph.add(new int[]{3,5,6});
        graph.add(new int[]{0,4,6});
        graph.add(new int[]{1,2,4,5});//用邻接表来表示七段管图
        int[]bright=new int[8];//表示当前发光状态，bright[7]用于辅助存放当前图的不连通发光数
        for (int i = 1; i <= 127; i++) {//二进制表示127种发光状态
            boolean[]flag=new boolean[7];
            int tmp=i;
            for (int j = 0; j < 7; j++) {
                bright[j]=tmp&1;
                tmp>>=1;
            }
            bright[7]=0;
            for (int k = 0; k < 7; k++) {
                if(bright[k]==1&&!flag[k]) {
                    dfs(k, flag,bright);
                    bright[7]++;
                }
            }
            if(bright[7]==1)ans++;
        }
        System.out.println(ans);
    }
    public static void dfs(int k,boolean[]flag,int[]bright){
        flag[k]=true;
        for (int i = 0; i < graph.get(k).length; i++) {
            if(!flag[graph.get(k)[i]]&&bright[k]==1)dfs(graph.get(k)[i],flag,bright);
        }
    }
}
