package heapsort;

public class PriorityQueues<T extends Comparable<T>> extends MaxHeap<T>{
    public PriorityQueues(T[] heap){
        super(heap);
        buildMaxHeap();
    }

    public T heapMax(){
        return table.at(0);
    }

    public T extractMax(){
        T res = table.at(0);
        if(table.num()<=0)
            throw new RuntimeException("PriorityQueues.extractMax(): Heap Underflow");
        table.table[0] = table.table[table.num()-1];
        table.pop();
        maxHeapify(0);
        return res;
    }

    public void increaseKey(int i, T key){
        if(i < 0 || i >= table.size()){
            throw new IndexOutOfBoundsException("PriorityQueues.increaseKey()");
        }
        if(key == null && table.table[i] == null){
            return;
        }
        if(maxheap &&(table.at(i) != null && table.at(i).compareTo(key) > 0) || (key != null && key.compareTo(table.at(i)) < 0)){
            throw new RuntimeException("PriorityQueues.increaseKey(): new key is smaller than old key when maxheap is true");
        }
        if(!maxheap &&(table.at(i) != null && table.at(i).compareTo(key) < 0) || (key != null && key.compareTo(table.at(i)) > 0)){
            throw new RuntimeException("PriorityQueues.increaseKey(): new key is greater than old key when maxheap is false");
        }
        table.table[i] = key;
        T t = table.at(i);
        if(maxheap){
            while(i > 0 && table.at(i).compareTo(table.at(parent(i))) > 0){
                table.table[i] = table.table[parent(i)];
                i = parent(i);
                table.table[i] = t;
            }
        } else {
            while(i > 0 && table.at(i).compareTo(table.at(parent(i))) < 0){
                table.table[i] = table.table[parent(i)];
                i = parent(i);
                table.table[i] = t;
            }
        }
    }

    public void insertKey(T key){
        table.push(key);
        increaseKey(table.num()-1, key);
    }

    public void simplePrint(){
        int l = 0;
        int r = 0;
        int t = 1;
        while(t < table.num()){
            t = t*2 + 1;
        }
        int t2 = t;
        System.out.println("");
        for(int i = 0; i < t2/2; i++)
            System.out.print("========");
        System.out.println("");
        for(int i = 0; i < table.num(); i++){
            if(l == i){
                t = t/2;
                l = left(l);
            }
            for(int j = 0; j < (t+1)/2; j++){
                System.out.print("\t");
            }
            System.out.print(table.table[i] + "\t");
            if(r == i){
                System.out.println("");
                r = right(r);
            } else {
                for(int j = 0; j < (t+1)/2-1; j++){
                    System.out.print("\t");
                }
            }
        }
        System.out.println("");
        for(int i = 0; i < t2/2; i++)
            System.out.print("========");
        System.out.println("");

    }

    public int size(){
        return table.num();
    }

}