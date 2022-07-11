package hashtable;

import numbertheoretic.*;

public class UniversalHash implements Hashfun{
    private int prime;
    private int slots;
    public boolean reminder = true;
    public int a, b;
    public boolean constAB = false;
    public UniversalHash(int slots){
        this.slots = slots;
        findPrime(slots);
    }

    private int findPrime(int slots){
        Prime pr = new Prime();
        return pr.findup(slots);
    }

    public int hash(int k){
        if(!constAB){
            a = (int)(Math.random()*(prime-1)+1);
            b = (int)(Math.random()*prime);
        }
        return ((a*k+b)%prime)%slots;
    }

    public void setAB(int a, int b){
        this.a = a;
        this.b = b;
    }

}