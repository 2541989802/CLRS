package hashtable;

public class MultiplicationMethod implements Hashfun{
    private double A = Math.random();
    private int bitsize = 0;
    private int bitslots = 0;

    public void setSlots(int nkey, int slots){
        while(nkey > 0){
            nkey=nkey/2;
            bitsize++;
        }
        while(slots > 0){
            slots=slots/2;
            bitslots++;
        }
    }

    public int getSlots(){
        return (int)(Math.pow(2,bitslots));
    }

    public int hash(int k){
        return (int)(((k*A)%1)*getSlots());
    }
}