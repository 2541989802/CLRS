package numbertheoretic;

public class Euclid{
    public class GCD{
        public int x;
        public int y;
        public int d;
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
}