package hashtable;

public interface OpenAddressHash{
    public void setSlots(int slot);
    public int getSlots();
    public int hash(int key, int i);
}