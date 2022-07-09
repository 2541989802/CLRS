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
<<<<<<< HEAD
        return (int)Math.pow(2,bitslots);
=======
        return (int)(Math.pow(2,bitslots));
>>>>>>> 4dd9b95af42bfcf8b6b81991a587426433298c9c
    }

    public int hash(int k){
        return (int)(k*A*Math.pow(2,bitslots));
    }
}