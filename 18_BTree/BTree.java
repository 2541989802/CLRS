package btree;

import btree.*;
import basicdatastructure.*;

public class BTree<T extends Comparable<T>>{
    public Node<T> root;
    public int t;
    public int height;

    public BTree(int i){
        t = i;
    }

    public void insertKey(T key){
        if(root==null){
            root=new Node<T>(t);
            root.leaf = true;
            height=1;
        }
        if(root.n==t*2-1){
            splitChild(null, 0);
        }
        insertKeyNFull(root, key);
    }

    public void insertKeyNFull(Node<T> node, T key){
        if(!node.leaf){
            int i = node.n;
            while(i>0 && key.compareTo(node.key.at(i-1))<0){
                i--;
            }
            if(node.child.at(i).n==2*t-1){
                //System.out.println("split");
                //node.child.at(i).print();System.out.println("");
                splitChild(node, i);
                //node.child.at(i).print();System.out.println("");
                if(key.compareTo(node.key.at(i))>=0)
                    i++;
            }
            insertKeyNFull(node.child.at(i), key);
        } else {
            int i = node.n;
            //System.out.println(i);
            while(i>0 && key.compareTo(node.key.at(i-1))<0){
                i--;
            }
            node.addKey(i, key);
        }
    }

    public void splitChild(Node<T> node, int i){
        if(node==null&&i==0){
            node = new Node<T>(t);
            node.child.set(0, root);
            root = node;
            height++;
        }
        if(node.child.at(i)==null)
            return;
        Node<T> ch = node.child.at(i);
        int num = ch.n;
        T k = ch.key.at(num/2);
        Node<T> n = new Node<T>(t);
        n.leaf = ch.leaf;
        {
            int j=0;
            int x;
            for(x=num/2+1; x<num; x++){
                n.key.set(j, ch.key.at(x));
                ch.key.set(x, null);
                if(!ch.leaf){
                    n.child.set(j, ch.child.at(x));
                    ch.child.set(x, null);
                }
                j++;
            }
            if(!ch.leaf){
                n.child.set(j, ch.child.at(x));
                ch.child.set(x, null);
            }
            n.n=num-num/2-1;
            ch.n=num/2;
        }
        node.addKey(i, k);
        node.addChild(i+1, n);
    }

    public T findKey(T key){
        int index = -1;
        Node<T> cur = root;
        while(index==-1&&cur!=null){
            int i=cur.n;
            while(i>0 && key.compareTo(cur.key.at(i-1))<=0)
                i--;

            if(i<cur.n && key.compareTo(cur.key.at(i))==0){
                index = i;
            } else {
                if(cur.leaf)
                    cur=null;
                else{
                    cur=cur.child.at(i);
                }
            }
        }
        if(index!=-1){
            return cur.key.at(index);
        }
        return null;
    }

    public void deleteKey(T key){
        int index = -1;
        Node<T> cur = root;
        while(index==-1&&cur!=null){
            int i=cur.n;
            while(i>0 && key.compareTo(cur.key.at(i-1))<=0)
                i--;

            if(i<cur.n && key.compareTo(cur.key.at(i))==0){
                index = i;
            } else {
                if(!cur.leaf){
                    if(i<cur.n){
                        Node<T> ch1 = cur.child.at(i);
                        Node<T> ch2 = cur.child.at(i+1);
                        if(ch1.n>t-1);
                        else if(ch2.n>t-1){
                            ch1.addChild(ch1.n+1, ch2.child.at(0));
                            ch1.addKey(ch1.n,cur.key.at(i));
                            cur.key.set(i, ch2.key.at(0));
                            ch2.removeChild(0);
                            ch2.removeKey(0);
                        } else {
                            mergeNode(cur, i);
                        }
                    } else {
                        Node<T> ch1 = cur.child.at(i-1);
                        Node<T> ch2 = cur.child.at(i);
                        if(ch2.n>t-1);
                        else if(ch1.n>t-1){
                            ch2.addChild(0, ch1.child.at(ch1.n));
                            ch2.addKey(0,cur.key.at(i-1));
                            cur.key.set(i-1, ch1.key.at(ch1.n-1));
                            ch1.removeChild(ch1.n);
                            ch1.removeKey(ch1.n-1);
                        } else {
                            mergeNode(cur, i-1);
                            i--;
                        }
                    }
                }
                if(cur.leaf)
                    cur=null;
                else{
                    cur=cur.child.at(i);
                }
            }
        }
        if(index!=-1){
            deleteKey(cur, index);
        }
    }

    public void deleteKey(Node<T> node, int i){
        if(!node.leaf){
            if(node.child.at(i).n>t-1){
                node.key.set(i, deleteLast(node.child.at(i)));
            } else if(node.child.at(i+1).n>t-1) {
                node.key.set(i, deleteFirst(node.child.at(i+1)));
            } else {
                int index = node.child.at(i).n;
                mergeNode(node, i);
                deleteKey(node.child.at(i), index);
            }
        } else {
            node.removeKey(i);
        }
    }

    public T deleteLast(Node<T> node){
        if(!node.leaf){
            Node<T> ch1 = node.child.at(node.n-1);
            Node<T> ch2 = node.child.at(node.n);
            if(ch2.n>t-1){
                return deleteLast(ch2);
            } else if(ch1.n>t-1){
                ch2.addChild(0, ch1.child.at(ch1.n));
                ch2.addKey(0,node.key.at(node.n-1));
                node.key.set(node.n-1, ch1.key.at(ch1.n-1));
                ch1.removeChild(ch1.n);
                ch1.removeKey(ch1.n-1);
                return deleteLast(ch2);
            } else {
                mergeNode(node, node.n-1);
                return deleteLast(node.child.at(node.n));
            }
        } else {
            T ret = node.key.at(node.n-1);
            node.removeKey(node.n-1);
            return ret;
        }
    }

    public T deleteFirst(Node<T> node){
        if(!node.leaf){
            Node<T> ch1 = node.child.at(0);
            Node<T> ch2 = node.child.at(1);
            if(ch1.n>t-1){
                return deleteFirst(ch1);
            } else if(ch2.n>t-1){
                ch1.addChild(ch1.n+1, ch2.child.at(0));
                ch1.addKey(ch1.n,node.key.at(0));
                node.key.set(0, ch2.key.at(0));
                ch2.removeChild(0);
                ch2.removeKey(0);
                return deleteFirst(ch1);
            } else {
                mergeNode(node, 0);
                return deleteFirst(node.child.at(0));
            }
        } else {
            T ret = node.key.at(0);
            node.removeKey(0);
            return ret;
        }
    }

    public void mergeNode(Node<T> node, int i){
        Node<T> ch1 = node.child.at(i);
        Node<T> ch2 = node.child.at(i+1);
        ch1.key.set(ch1.n, node.key.at(i));
        int num = ch1.n+1+ch2.n;
        int j=0;
        int x;
        for(x=ch1.n+1; x<num; x++){
            ch1.key.set(x, ch2.key.at(j));
            if(!ch1.leaf)
                ch1.child.set(x, ch2.child.at(j));
            j++;
        }
        if(!ch1.leaf)
            ch1.child.set(x, ch2.child.at(j));
        ch1.n=num;
        node.removeKey(i);
        node.removeChild(i+1);
        if(node==root&&node.n==0){
            root = root.child.at(0);
            height--;
        }
    }

    public void print(){
        Queue<Node<T>> q1 = (Queue<Node<T>>)new Queue((int)Math.pow(2*t,height));
        Queue<Node<T>> q2 = (Queue<Node<T>>)new Queue((int)Math.pow(2*t,height));
        Node<T> t;
        boolean isq1 = true;
        System.out.println(height+":");
        q1.enqueue(root);
        while(!(q1.isEmpty()&&q2.isEmpty())){
            if(isq1){
                while(!q1.isEmpty()){
                    t = q1.dequeue();
                    t.print();
                    if(!t.leaf){
                        for(int i=0; i<t.n+1; i++)
                            q2.enqueue(t.child.at(i));
                    }
                }
            } else {
                while(!q2.isEmpty()){
                    t = q2.dequeue();
                    t.print();
                    if(!t.leaf){
                        for(int i=0; i<t.n+1; i++)
                            q1.enqueue(t.child.at(i));
                    }
                }
            }
            System.out.println("");
            isq1 = !isq1;
        }
    }
}