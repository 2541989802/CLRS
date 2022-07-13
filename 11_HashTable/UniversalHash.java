package hashtable;

import numbertheoretic.*;

public class UniversalHash implements Hashfun{
    private int prime;
    private int slots;
    public int a, b;
    public boolean constAB = false;
    private boolean init = false;

    public UniversalHash(){}

    public UniversalHash(int slots){
        setSlots(0, slots);
    }
    public void setSlots(int nkey, int slots){
        this.slots = slots;
        findPrime(slots+1);
        hash(0);
    }

    private void findPrime(int slots){
        Prime pr = new Prime();
        prime = pr.findup(slots);
    }

    public int hash(int k){
        if(!constAB || !init){
            a = (int)(Math.random()*(prime-1)+1);
            b = (int)(Math.random()*prime);
            init = true;
        }
        return ((a*k+b)%prime)%slots;
    }

    public void setAB(int a, int b){
        this.a = a;
        this.b = b;
    }

    public int getSlots(){
        return slots;
    }

}