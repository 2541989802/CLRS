package augmentingdatastructure;

public class Node<T extends Comparable<T>> extends redblacktree.Node<T>{
    public int size;
    public T high;
    public T max;
    public Node(T low, T high, Node<T> parent, boolean black){
        super(low, parent, black);
        this.high = high;
        if(high!=null&&high.compareTo(low)<0)
            this.high = low;
        else if(low!=null&&low.compareTo(high)>0)
            this.high = low;
        setMax();
        size = 1;
    }

    @SuppressWarnings("unchecked")
    public void setMax(){
        T l = (left!=null&&left instanceof Node)?((Node<T>)left).max:high;
        T r = (right!=null&&right instanceof Node)?((Node<T>)right).max:high;
        T m = null;
        if(l!=null){
            if(l.compareTo(r)>=0)
                m = l;
            else
                m = r;
        } else if(r!=null){
            if(r.compareTo(l)>=0)
                m = r;
            else
                m = l;
        } else {
            m = null;
        }
        if(high!=null){
            if(high.compareTo(m)>=0)
                m = high;
        } else if(m!=null){
            if(m.compareTo(high)<0)
                m = high;
        }
        max = m;
    }


    @SuppressWarnings("unchecked")
    public void setSize(){
        size = ((left!=null&&left instanceof Node)?((Node<T>)left).size:0)+((right!=null&&right instanceof Node)?((Node<T>)right).size:0)+1;
    }

    public int insect(T i){
        if(i!=null){
            if(i.compareTo(data)>=0&&i.compareTo(high)<=0)
                return 0;
            else if(i.compareTo(max)>0)
                return 1;
            else
                return -1;
        }
        else if(data!=null&&high!=null){
            if(data.compareTo(i)<=0&&high.compareTo(i)>=0)
                return 0;
            else if(max!=null&&max.compareTo(i)<0)
                return 1;
            else
                return -1;
        }
        else
            return 0;
    }
}