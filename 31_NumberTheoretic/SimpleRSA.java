package numbertheoretic;

import numbertheoretic.*;

public class SimpleRSA{
    private int p;
    private int q;
    private int n;
    public int e;
    private int d;
    public SimpleRSA(int lo, int le){
        p = findPrime(lo);
        q = findPrime(p+lo);
        n = p*q;
        e = findPrime(le);
        Euclid ex = new Euclid();
        d = ex.modSolver(e, 1, (q-1)*(p-1))[0];
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

    public int findPrime(int lo){
        System.out.println("Warning:: This is an unfinished method.");
        return lo;
    }
}