package fibonacciheap;

import fibonacciheap.*;
import btree.SimpleGenericArray;

public class FibonacciHeap<T extends Comparable<T>>{
    public Node<T> min;
    public int num;

    public FibonacciHeap(){
        num=0;
        min=null;
    }

    public Node<T> insert(T key){
        Node<T> n = new Node<T>(key);;
        if(min==null){
            min=n;
        } else {
            min.addNext(n);
            if(n.compareTo(min)<0)
                min=n;
        }
        num++;
        return n;
    }

    public T min(){
        return min.data;
    }

    public T extractMin(){
        Node<T> cur = min;
        if(min.next==min)
            min=null;
        else
            min=min.next;
        cur.selfRemove();
        Node<T> ch;
        while(cur.child!=null){
            ch=cur.child;
            cur.removeChild(ch);
            if(min==null){
                min=ch;
            }else{
                min.addNext(ch);
            }
        }
        consolidate();
        num--;
        return cur.data;
    }

    public void decreaseKey(Node<T> node, T key){
        if(key.compareTo(node.data)>0){
            return;
        }
        node.data=key;
        if(node.parent!=null&&node.compareTo(node.parent)<0){
            Node<T>cur = node.parent;
            Node<T>ch;
            node.parent.removeChild(node);
            min.addNext(node);
            if(node.compareTo(min)<0)
                min=node;
            while(cur!=null&&cur.mark&&cur.parent!=null){
                ch = cur;
                cur = cur.parent;
                cur.removeChild(ch);
                min.addNext(ch);
                if(min.compareTo(ch)>0)
                    min=ch;
            }
            if(cur!=null)
                cur.mark=true;
        }
    }

    public void consolidate(){
        SimpleGenericArray<Node<T>> nodes = new SimpleGenericArray<Node<T>>(dN());
        while(min!=null){
            Node<T> cur = min;
            Node<T> y;
            min=min.next;
            if(min.next==min){
                min=null;
            }
            cur.selfRemove();
            while(nodes.at(cur.degree)!=null){
                y=nodes.at(cur.degree);
                nodes.set(cur.degree, null);
                if(cur.compareTo(y)<=0){
                    cur.addChild(y);
                } else {
                    y.addChild(cur);
                    cur = y;
                }
            }
            nodes.set(cur.degree, cur);
        }

        for(int i=0; i<nodes.length(); i++){
            if(nodes.at(i)!=null){
                if(min==null){
                    min=nodes.at(i);
                } else {
                    min.addNext(nodes.at(i));
                    if(min.compareTo(nodes.at(i))>0)
                        min=nodes.at(i);
                }
            }
        }
    }

    public int dN(){
        if(num<=0)
            return 0;
        return (int)(Math.log10(num)/Math.log10((1+Math.pow(5,0.5))/2))+1;
    }
}