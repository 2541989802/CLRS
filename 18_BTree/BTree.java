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