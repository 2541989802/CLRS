package basicdatastructure;

import java.util.Iterator;

@SuppressWarnings("unchecked")
public class LinkList<T extends Comparable> implements Iterable<T>{
    public class Node<T>{
        public Node<T> prev;
        public Node<T> next;
        public T data;
        public Node(T d){
            data = d;
        }
    }

    public class LinkListIterator<T> implements Iterator<T>{
        public Node<T> head;
        public LinkListIterator(Node<T> head){
            this.head = head;
        }

        public boolean hasNext(){
            return head!=null && head.next!=null;
        }

        public T next(){
            T res = head.data;
            head = head.next;
            return res;
        }
    }

    public Node<T> head;
    private int size = 0;

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
        size++;
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
        size--;
    }

    public int getSize(){
        return size;
    }

    public Iterator<T> iterator(){
        return new LinkListIterator<T>(head);
    }
}