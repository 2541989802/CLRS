package selection;

public class ExpectLinearSelection<T extends Comparable<T>>{
    public T[] array;
    public ExpectLinearSelection(T[] array){
        this.array = array;
    }

    private T selection(int l, int r, int i){
        if(l == r)
            return array[l];
        int m = partition(l,r);
        int rank = m-l+1;
        if(rank == i)
            return array[m];
        if(rank > i)
            return selection(l, m-1, i);
        else
            return selection(m+1, r, i-rank);
    }

    public T select(int i){
        return selection(0,array.length-1, i);
    }

    private int partition(int l, int r){
        random_h(l, r);
        int m = l;
        while(array[m] == null && m <= r){
            m++;
        }
        if(m > r){
            return -1;
        }
        T p = array[m];
        array[m] = array[l];
        array[l] = p;
        m = l;
        T t;
        for(int i = l+1; i <= r; i++){
            if(p.compareTo(array[i]) > 0){
                m++;
                t = array[i];
                array[i] = array[m];
                array[m] = t;
            }
        }
        array[l] = array[m];
        array[m] = p;
        return m;
    }

    private void random_h(int l, int r){
        int len = r+1-l;
        T t;
        int index;
        for(int i = 0; i < len; i++){
            index = (int)(Math.random()*(len-i))+i;
            t = array[i+l];
            array[i+l] = array[index+l];
            array[index+l] = t;
        }
    }
}