package hashtable;

public class MultiplicationMethod implements Hashfun{
    private double A = Math.random();
    private int bitsize;
    private int bitslots;

    public MultiplicationMethod(int bk, int bs){
        bitsize = bk;
        bitslots = bs;
    }

    public int getSlots(){
        return (int)Math.pow(2,bitslots);
    }

    public int hash(int k){
        return (int)(k*A*Math.pow(2,bitslots));
    }
}