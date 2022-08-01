package heapsort;

import dynamictable.*;

public class ArrayHeap<T>{
    
    public DynamicTable<T> table;

    @SuppressWarnings("unchecked")
    public ArrayHeap(T[] heap){
        table = (DynamicTable<T>)new DynamicTable<Object>();
        for(int i=0; i<heap.length; i++)
            table.push(heap[i]);
    }

    public int parent(int i) {
        if(i >=0 && (i+1)/2-1 < table.num())
            return (i+1)/2-1;
        return -1;
    }

    public int left(int i) {
        if(i >= 0 && 2*i+1 < table.num())
            return 2*i+1;
        return -1;
    }

    public int right(int i) {
        if(i >= 0 && 2*i+2 < table.num())
            return 2*i+2;
        return -1;
    }
}