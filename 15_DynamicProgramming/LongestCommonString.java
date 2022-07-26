package dynamicprogramming;

public class LongestCommonString{
    public String lcs(String a, String b){
        String res = "";
        int[][] len = new int[a.length()][b.length()];
        for(int i=0; i<a.length(); i++){
            for(int j=0; j<b.length(); j++){
                if(i==0||j==0){
                    if(a.charAt(i)==b.charAt(j))
                        len[i][j]=1;
                    else
                        len[i][j]=0;
                } else if (a.charAt(i)==b.charAt(j)){
                    len[i][j] = len[i-1][j-1]+1;
                } else {
                    len[i][j] = len[i-1][j]>len[i][j-1]?len[i-1][j]:len[i][j-1];
                }
            }
        }
        for(int i=a.length()-1; i>=0;){
            for(int j=b.length()-1; j>=0;){
                if(i==0||j==0){
                    if(len[i][j]!=0){
                        res=a.charAt(i)+res;
                        i--;
                        j--;
                    } else {
                        i=-1;
                        j=-1;
                    }
                } else {
                        if(len[i][j]==len[i-1][j-1]+1){
                            res=a.charAt(i)+res;
                            i--;
                            j--;
                        } else if(len[i][j]==len[i-1][j]){
                            i--;
                        } else {
                            j--;
                        }
                    }
            }
        }
        return res;
    }

    public String palindrone(String a){
        int[][] len = new[a.length()][a.length()];
        for(int i=0; i<a.length; i++){
            for(int j=0; j<a.length-i; j++){
                if(i==0){
                    len[j][j]=1;
                }
                for(int k=0; k<i; k++){
                    ;
                }
            }
        }
    }
}