package numbertheoretic;

import numbertheoretic.*;

public class RhoFactor{
    public int rho(int a){
        Euclid eu = new Euclid();
        Prime pr = new Prime();
        int x = (int)(Math.random()*(a-2))+1;
        int next = x;
        int c = 1;
        int i = 0;
        int d = 1;
        if(pr.millerRabin(a))
            return a;
        System.out.println("");
        while(c>0){
            next = (next*next-1)%a;
            d = x - next;
            while(d < 0)
                d += a;
            d = eu.gcd(d, a);
            if(d!=1 && d!=a)
                return d;
            i++;
            if(c==i){
                x = next;
                c = c*2;
                System.out.print("*");
            }
        }
        System.out.println("");
        return -1;
    }
}