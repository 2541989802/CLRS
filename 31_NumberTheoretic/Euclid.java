package numbertheoretic;

public class Euclid{
    public class GCD{
        public int d;
        public int x;
        public int y;
        public GCD(int d, int x, int y){
            this.d = d;
            this.x = x;
            this.y = y;
        }
    }
    public int gcd(int a, int b){
        if(b==0)
            return a;
        return gcd(b, a%b);
    }
    
    public GCD extendGcd(int a, int b){
        if(b==0)
            return new GCD(a, 1, 0);
        GCD res = extendGcd(b, a%b);
        int x = res.x;
        res.x = res.y;
        res.y = x - a/b*res.y;
        return res;
    }

    public int[] modSolver(int a, int b, int n){
        GCD gcd = extendGcd(a, n);
        if(b % gcd.d != 0)
            return null;
        int[] res = new int[gcd.d];
        int x = (gcd.x*b/gcd.d)%n;
        while(x<0)
            x+=n/gcd.d;
        for(int i = 0; i < res.length; i++)
            res[i] = (x + i*n/gcd.d)%n;
        return res;
    }
}