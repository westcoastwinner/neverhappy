import java.util.Arrays;

public class KnapSackProblem {
    public static void main(String[] args) {
        int[] w={1,4,3};//保存物品重量
        int[] val={1500,3000,2000};//保存物品的价值
        int m=4;//背包容量
        int n=val.length;//物品个数

        int[][] v=new int[n+1][m+1];//v[i][j]表示在前i个商品中能够装入容量为w的背包的最大价值

        for(int i=0;i<v.length;i++){
            v[i][0]=0;//将第一列设置为0
        }

        for(int i=0;i<v[0].length;i++){
            v[0][i]=0;//将第一行设置为0
        }


        for(int i=1;i<v.length;i++){
            for(int j=1;j<v[0].length;j++){
                if(w[i-1]>j){   //因为我们程序i从1开始，所以w[i]变成i-1
                    v[i][j]=v[i-1][j];
                }else{
                    v[i][j]=Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                }
            }
        }
        for(int[] e:v){
            System.out.println(Arrays.toString(e));
        }

    }
}
