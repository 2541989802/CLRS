package hashtable.util;

public class LinearProb implements OpenAddressHash{
    public UniversalHash hash;

    public void setSlots(int slot){
        hash = new UniversalHash(slot);
        hash.constAB = true;
    }

    public int getSlots(){
        return hash.getSlots();
    }

    public int hash(int key, int i){
        return (hash.hash(key)+i)%getSlots();
    }
}