package hashtable.util;

import numbertheoretic.*;

public class DivisionMethod implements Hashfun{
    private int slots;

    public DivisionMethod(){}

    public DivisionMethod(int nkeys, int slots){
        setSlots(nkeys, slots);
    }

    public DivisionMethod(int nkeys){
        this(nkeys, 5);
    }

    public void setSlots(int nkeys, int slots){
        this.slots = findPrime(nkeys, nkeys/slots);
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