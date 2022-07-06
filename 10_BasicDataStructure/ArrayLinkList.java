package basicdatastructure;

public class ArrayLinkList<T extends Comparable<T>>{
    private int[] next;
    public Object[] oa;
    private int[] prev;
    private int collecter;
    private int[] head;

    public ArrayLinkList(int i){
        setup(i);
    }

    public ArrayLinkList(T[][] init){
        setup(len_h(init));
        for(int i = 0; i < init.length; i++){
            for(T t : init[i])
                insert(t, i);
        }
    }

    private int len_h(Object[][] oaa){
        int len = 0;
        for(Object[] oa : oaa){
            len += oa.length;
        }
        return len;
    }

    private void setup(int i){ 
        oa = new Object[i];
        next = new int[i+1];
        next[i] = i;
        prev = new int[i+1];
        prev[i] = i;
        head = new int[i];
        for(int j = 0; j < i; j++){
            head[j] = i;
            next[j] = j+1;
            prev[j] = i;
        }
        collecter = 0;
    }

    private int search_h(T t, int li){
        int ne = head[li];
        boolean find = false;
        while(ne !=oa.length && !find){
            if(t!=null && t.compareTo((T)(oa[ne]))==0)
                find = true;
            else if(oa[ne]!=null && ((T)oa[ne]).compareTo(t)==0)
                find = true;
            else if(t==null && oa[ne]==null)
                find = true;
            else
                ne = next[ne];
        }
        return ne;
    }

    public T search(T t, int li){
        return (T)(oa[search_h(t, li)]);
    }

    public void insert(T e, int li){
        if(collecter==oa.length)
            throw new RuntimeException("ArrayLinkList.insert(): memory full");
        int nc = next[collecter];
        next[collecter] = head[li];
        if(head[li]!=oa.length)
            prev[head[li]] = collecter;
        head[li] = collecter;
        oa[head[li]] = e;
        collecter = nc;
    }

    public void delete(T t, int li){
        if(head[li]==oa.length)
            throw new RuntimeException("ArrayLinkList.delete(): list: " +li+ " underflow");
        int cur = search_h(t, li);
        if(cur != oa.length){
            if(next[cur]!=oa.length)
                prev[next[cur]] = prev[cur];
            if(prev[cur]!=oa.length)
                next[prev[cur]] = next[cur];
            else
                head[li] = next[cur];
            next[cur] = collecter;
            prev[cur] = oa.length;
            collecter = cur;
        }
    }

    public void print(){
        int cur = oa.length;
        int n = 0;
        for(int i : head){
            cur = i;
            if(i!=oa.length)
                System.out.printf("%dst array: ", n);
            while(cur != oa.length){
                System.out.print(oa[cur]+", ");
                cur = next[cur];
            }
            if(i!=oa.length)
                System.out.println("");
            n++;
        }
    }
    public void print_h(){
        for(int i =0; i < oa.length; i++){
            System.out.printf("%3d,",i);
        }
        System.out.println("");
        for(int i =0; i < oa.length; i++){
            System.out.printf("%3d,",head[i]);
        }
        System.out.println("");
        for(int i =0; i < oa.length; i++){
            System.out.printf("%3d,",next[i]);
        }
        System.out.print("\n ");
        for(int i =0; i < oa.length; i++){
            System.out.print(oa[i]+", ");
        }
        System.out.println("");
        for(int i =0; i < oa.length; i++){
            System.out.printf("%3d,",prev[i]);
        }
        System.out.println("");
    }
}