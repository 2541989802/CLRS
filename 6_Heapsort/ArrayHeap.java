package heapsort;

public class ArrayHeap<T>{
    public T[] heap;

    /*public ArrayHeap(){
        this((T[])(new Object[5]));
    }*/

    public ArrayHeap(T[] heap){
        this.heap = heap;
    }

    public int parent(int i) {
        if(i >= 0 && (i+1)/2 < heap.length)
            return (i+1)/2;
        return -1;
    }

    public int left(int i) {
        if(i >= 0 && 2*i+1 < heap.length)
            return 2*i+1;
        return -1;
        //throw new IndexOutOfBoundsException("ArrayHeap.left(int)");
    }

    public int right(int i) {
        if(2*i+2 < heap.length)
            return 2*i+2;
        return -1;
        //throw new IndexOutOfBoundsException("ArrayHeap.left(int)");
    }
}