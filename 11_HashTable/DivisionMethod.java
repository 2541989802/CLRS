package hashtable;

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
        if(reminder)
            System.out.println("This is a unfinished function. Delete this reminder after finished.");
        return nkeys;
    }

    public int getSlots(){
        return slots;
    }

    public int hash(int k){
        return k % slots;
    }
}