package hashtable;

import numbertheoretic.*;

public class DoubleHash implements OpenAddressHash{
    public UniversalHash h1;
    public UniversalHash h2;
    public void setSlots(int slot){
        Prime pr = new Prime();
        h1 = new UniversalHash(pr.findup(slot));
        h2 = new UniversalHash(getSlots()-1);
        h1.constAB=true;
        h2.constAB=true;
    }

    public int getSlots(){
        return h1.getSlots();
    }

    public int hash(int key, int i){
        return (h1.hash(key) + i*(1+h2.hash(key)))%getSlots();
    }
}