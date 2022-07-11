package hashtable;

import numbertheoretic.*;

public class DivisionMethod implements Hashfun{
    private int slots;
    public boolean reminder = true;

    public DivisionMethod(int nkeys, int loadfactor){
        slots = findPrime(nkeys, loadfactor);
    }

    public DivisionMethod(int nkeys){
        this(nkeys, 5);
    }

    private int findPrime(int nkeys, int loadfactor){
        Prime pr = new Prime();
        int m = nkeys%loadfactor;
        m = nkeys/loadfactor+(m==0?0:1);
        return pr.findup(m);
    }

    public int getSlots(){
        return slots;
    }

    public int hash(int k){
        return k % slots;
    }
}