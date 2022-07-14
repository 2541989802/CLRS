package hashtable.util;

public class DirectAddress implements Hashfun{
    public int slots;

    public void setSlots(int nkey, int slots){
        this.slots = nkey;
    }

    public int getSlots(){
        return slots;
    }

    public int hash(int k){
        if(k<0 || k >= slots)
            throw new RuntimeException("DirectAddress.hash(): key is out of range");
        return k;
    }
}