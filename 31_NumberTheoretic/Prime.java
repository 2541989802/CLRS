package numbertheoretic;

import numbertheoretic.*;

public class Prime{
    public int find(int low){
        return -1;
    }

    public boolean sudoPrime(int n){
        ModExp ex = new ModExp();
        return ex.exp(2, n-1, n) == 1;
    }

    //guess prime return true.
    //a is evident, n is number
    public boolean witness(int a, int n){
        if(n%2==0){
            return false;
        }
        int mask = 1;
        int c = 0;
        while(((n-1)&mask) == 0){
            c++;
            mask = mask<<1;
        }
        ModExp ex = new ModExp();
        int u = (n-1)>>>c;
        u = ex.exp(a, u, n);
        int last;
        for(int i = 0; i < c; i++){
            last = u;
            u = (u*u)%n;
            if(u==1 && last!=(n-1) && last!=1){
                return false;
            }
        }
        if(u != 1){
            return false;
        }
        return true;
    }

    //true if prime
    public boolean millerRabin(int a, int s){
        if(a == 2)
            return true;
        for(int i = 0; i < s; i++){
            if(!witness((int)(Math.random()*(a-2))+1, a))
                return false;
        }
        return true;
    }

    public boolean millerRabin(int a){
        return millerRabin(a, 50);
    }
}