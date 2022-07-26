package dynamicprogramming;

public class MatrixChain{
    public int[][] order(int[] mn){
        int[][] res = new int[mn.length-1][mn.length-1];
        int[][] cost = new int[mn.length-1][mn.length-1];
        int t = 0;
        for(int i=0; i<mn.length-1; i++){
            for(int j=0; j<mn.length-1-i; j++){
                cost[j][j+i]=-1;
                if(i==0){
                    cost[j][j+i]=0;
                    res[j][j+i]=1;
                }
                for(int k=0; k<i; k++){
                    t=mn[j]*mn[k+j+1]*mn[i+j+1]+cost[j][j+k]+cost[j+k+1][i+j];
                    if(t<cost[j][j+i]||cost[j][j+i]==-1){
                        cost[j][j+i]=t;
                        res[j][j+i]=k+1;
                    }
                }
            }
        }
        return res;
    }
}