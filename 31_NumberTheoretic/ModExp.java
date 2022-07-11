package numbertheoretic;

public class ModExp{
    public int exp(int b, int exp, int n){
        int c = -1;
        for(int i = 1; i <= exp && i > 0; i=i<<1){
            c++;
        }
        int mask = 1 << (c>=0?c:0);
        int res = 1;
        b = b % n;
        while(mask>0){
            res = (res*res)%n;
            if((mask&exp) != 0)
                res = (res*b)%n;
            mask = mask >>> 1;
        }
        return res;
    }
}