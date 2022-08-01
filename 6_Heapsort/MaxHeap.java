package heapsort;

public class MaxHeap<T extends Comparable<T>> extends ArrayHeap<T>{
    //public int size = 0;
    public boolean maxheap = true;
    public Object[] heap;

    public MaxHeap(T[] heap){
        /*super((T[])(new Oject[heap.length]));
        for(T t : heap){
            this.heap
        }*/
        super(heap);
        //size = heap.length;
    }

    public void maxHeapify(int i){
        int l = left(i);
        int r = right(i);
        int max = i;
        if(l < table.num() && l >= 0){
            if(table.at(l).compareTo(table.at(max)) > 0){
                if(maxheap)
                    max = l;
            }else{
                if(!maxheap)
                    max = l;
            }
        }
        if(r < table.num() && r >= 0){
            if(table.at(r).compareTo(table.at(max)) > 0){
                if(maxheap)
                    max = r;
            }else{
                if(!maxheap)
                    max = r;
            }
        }
        if(max != i){
            T t = table.at(i);
            table.set(i,table.at(max));
            table.set(max, t);
            maxHeapify(max);
        }
    }

    public void buildMaxHeap(){
        for(int i = table.num()/2; i >= 0; i--){
            maxHeapify(i);
        }
    }

    public void heapSort(){
        T t;
        buildMaxHeap();
        heap = (new Object[table.num()]);
        for(int i = table.num()-1; i >= 0; i--){
            t = table.at(0);
            table.set(0, table.at(i));
            heap[i] = (T)t;
            //size--;
            table.pop();
            maxHeapify(0);
        }
    }

    @SuppressWarnings("unchecked")
    public T getHeap(int i){
        return (T)(heap[i]);
    }

    /*public void resetSize(){
        size = heap.length;
    }*/
}