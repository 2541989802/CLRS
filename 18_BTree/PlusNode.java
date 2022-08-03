package btree;

import btree.*;

public class PlusNode<K extends Comparable<K>, T> extends Node<K>{
    public SimpleGenericArray<T> data;

    public PlusNode(int t){
        super(t);
        data = new SimpleGenericArray<T>(2*t-1);
    }

    public boolean addKey(int i, K k, T d){
        data.add(i, d);
        return addKey(i, k);
    }

    @Override
    public void removeKey(int i){
        data.remove(i);
        super.removeKey(i);
    }
    
    public void print(){
        System.out.print("("+n+"|");
        for(int i=0; i<n; i++){
            System.out.print("("+key.at(i)+":");
            System.out.print(data.at(i)+"),");
        }
        System.out.print(")");
    }
}