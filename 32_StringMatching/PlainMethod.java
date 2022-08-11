package stringmatching;

import dynamictable.DynamicTable;

public class PlainMethod{
    public static int[] match(String input, String p){
        DynamicTable<Integer> res = new DynamicTable<>();
        for(int i=0; i<input.length(); i++){
            int j=0;
            for(;j<p.length() && j+i<input.length(); j++){
                if(input.charAt(j+i)!=p.charAt(j))
                    break;
            }
            if(j==p.length())
                res.push(i);
        }
        int[] ret = new int[res.num()];
        for(int i=0; i<ret.length; i++)
            ret[i] = res.at(i);
        return ret;
    }
}