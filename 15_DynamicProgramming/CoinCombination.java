package dynamicprogramming;

public class CoinCombination{
    public int[][] comb(int[] coins, int sum){
        int[][] comb = new int[coins.length+1][sum+1];
        for(int i=0; i<=coins.length; i++)
            comb[i][0] = 0;
        for(int i=0; i<=sum; i++)
            comb[0][i] = 0;
        for(int i=1; i<=coins.length; i++){
            for(int s=1; s<=sum; s++){
                if(s%coins[i-1]==0)
                    comb[i][s]=1;
                for(int k=s; k>=0; k=k-coins[i-1]){
                    comb[i][s]+=comb[i-1][k];
                }
            }
        }
        return comb;
    }
}