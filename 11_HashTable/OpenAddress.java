package hashtable;

import hashtable.util.*;

public class OpenAddress<T extends OpenAddressHash>{
    public int[] data;
    public int[] key;
    private T hash;

    public OpenAddress(T hash, int slots){
        this.hash = hash;
        hash.setSlots(slots);
        data = new int[getSlots()];
        key = new int[getSlots()];
        for(int i = 0; i < data.length; i++)
            key[i] = -1;
    }

    public int getSlots(){
        return hash.getSlots();
    }

    public void insert(int data, int key){
        int i;
        for(i = 0; i < getSlots(); i++){
            if(this.key[hash.hash(key, i)]==-1)
                break;
        }
        if(i!=getSlots()){
            this.data[hash.hash(key, i)]=data;
            this.key[hash.hash(key, i)]=key;
        }
    }

    //return index
    public int search(int key){
        int i = 0;
        while(this.key[hash.hash(key,i)]!=key && this.key[hash.hash(key,i)]!=-1 && i < getSlots())
            i++;
        if(this.key[hash.hash(key,i)]!=-1 && i!=getSlots())
            return hash.hash(key,i);
        return -1;
    }

    public int collision(int key){
        int i;
        boolean find = false;
        for(i = 0; i < getSlots(); i++){
            if(this.key[hash.hash(key, i)]==key){
                find=true;
                break;
            }
        }
        if(!find)
            return -1;
        if(i!=getSlots())
            return i;
        return -1;
    }

    public void print(){
        System.out.print("\nkey:\t");
        for(int i = 0; i < data.length;i++)
            System.out.print(key[i]+", ");
        System.out.print("\ndata:\t");
        for(int i = 0; i < data.length;i++)
            System.out.print(data[i]+", ");
        System.out.print("\n");
    }
}