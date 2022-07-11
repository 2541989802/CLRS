package numbertheoretic;

import numbertheoretic.*;

public class SimpleRSA{
    private int p;
    private int q;
    public int n;
    public int e;
    private int d;
    public SimpleRSA(int lo, int le){
        p = findPrime(lo);
        q = findPrime(p+lo);
        n = p*q;
        le = le % n;
        e = (le+(int)(100*Math.random()))%((q-1)*(p-1));
        Euclid ex = new Euclid();
        int[] t = ex.modSolver(e, 1, (q-1)*(p-1));
        while(t==null){
            e++;
            t = ex.modSolver(e, 1, (q-1)*(p-1));
        }
        d = t[0];
    }

    public int encrypt(int m){
        if(m >= n || m < 0)
            throw new RuntimeException("SimpleRSA.encrypt(): message out of range");
        ModExp ex = new ModExp();
        return ex.exp(m, e, n);
    }

    public int decrypt(int m){
        if(m >= n || m < 0)
            throw new RuntimeException("SimpleRSA.decrypt(): message out of range");
        ModExp ex = new ModExp();
        return ex.exp(m, d, n);
    }

    public int crypt(int m, int p, int n){
        ModExp ex = new ModExp();
        return ex.exp(m, p, n);
    }

    public int findPrime(int lo){
        Prime pr = new Prime();
        return pr.findup(lo);
    }
}