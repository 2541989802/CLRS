package stringmatching;

import dynamictable.DynamicTable;

public class KMP{
    public static int[] prefix(String p){
        int[] res = new int[p.length()];
        res[0] = 0;
        int k = 0;
        for(int i=1; i<p.length(); i++){
            while(k>0 && p.charAt(k)!=p.charAt(i)){
                k=res[k-1];
            }
            if(p.charAt(k)==p.charAt(i))
                k++;
            res[i]=k;
        }
        return res;
    }

    public static int[] match(String input, String p){
        DynamicTable<Integer> res = new DynamicTable<>();
        int[] table = prefix(p);
        int k = 0;
        for(int i=0; i<input.length(); i++){
            while(k>0 && input.charAt(i)!=p.charAt(k)){
                k = table[k-1];
            }
            if(input.charAt(i)==p.charAt(k)){
                k++;
            }
            if(k==p.length()){
                res.push(i+1-k);
                k = table[k-1];
            }
        }
        int[] ret = new int[res.num()];
        for(int i=0; i<ret.length; i++)
            ret[i] = res.at(i);
        return ret;
    }
}