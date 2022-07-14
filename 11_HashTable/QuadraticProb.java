package hashtable.util;

public class QuadraticProb implements OpenAddressHash{
    public UniversalHash hash;
    public int a, b;

    public void setSlots(int slot){
        hash = new UniversalHash(slot);
        hash.constAB = true;
        a = (int)(Math.random()*(getSlots()-1))+1;
        b = (int)(Math.random()*(getSlots()-1))+1;
    }

    public int getSlots(){
        return hash.getSlots();
    }

    public int hash(int key, int i){
        return (hash.hash(key)+i*a+i*i*b)%getSlots();
    }
}