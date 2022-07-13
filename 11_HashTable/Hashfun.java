package hashtable;

public interface Hashfun{
    public void setSlots(int nkey, int slot);
    public int getSlots();
    public int hash(int key);
}