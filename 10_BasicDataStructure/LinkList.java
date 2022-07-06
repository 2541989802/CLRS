package basicdatastructure;

public class LinkList<T extends Comparable>{
    private class Node<T>{
        public Node<T> prev;
        public Node<T> next;
        public T data;
        public Node(T d){
            data = d;
        }
    }
    public Node<T> head;

    public LinkList(){}
    public LinkList(T[] ta){
        for(T e : ta)
            insert(e);
    }

    private Node<T> search_h(T t){
        Node<T> next = head;
        boolean find = false;
        while(next != null && !find){
            if(t!=null && t.compareTo(next.data)==0)
                find = true;
            else if(next.data != null && next.data.compareTo(t)==0)
                find = true;
            else if(t == null && next.data == null)
                find = true;    //null
            else
                next = next.next;
        }
        return next;
    }

    public T search(T t){
        Node<T> ret = search_h(t);
        return (ret!=null)?ret.data:null;
    }

    public void insert(T e){
        Node<T> n = new Node(e);
        if(head != null){
            head.prev = n;
        }
        n.next = head;
        head = n;
    }

    public void delete(T t){
        Node<T> n = search_h(t);
        if(n==null)
            return;
        if(n.prev != null)
            n.prev.next = n.next;
        else
            head = n.next;
        if(n.next != null)
            n.next.prev = n.prev;
    }
}