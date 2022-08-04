package btree;

import btree.*;
import basicdatastructure.*;

public class BPlusTree<K extends Comparable<K>, T>{
    public Node<K> root;
    public int t;
    public int height;

    public BPlusTree(int i){
        t = i;
    }

    public void insertKey(K key, T data){
        if(root==null){
            root=new PlusNode<K, T>(t);
            root.leaf = true;
            height=1;
            root.child=null;
        }
        if(root.n==t*2-1){
            splitChild(null, 0);
        }
        insertKeyNFull(root, key, data);
    }

    @SuppressWarnings("unchecked")
    public void insertKeyNFull(Node<K> node, K key, T data){
        if(!node.leaf){
            int i = node.n;
            while(i>0 && key.compareTo(node.key.at(i-1))<0){
                i--;
            }
            if(node.child.at(i).n==2*t-1){
                splitChild(node, i);
                if(key.compareTo(node.key.at(i))>=0)
                    i++;
            }
            insertKeyNFull(node.child.at(i), key, data);
        } else {
            int i = node.n;
            while(i>0 && key.compareTo(node.key.at(i-1))<0){
                i--;
            }
            ((PlusNode<K, T>)node).addKey(i, key, data);
        }
    }

    @SuppressWarnings("unchecked")
    public void splitChild(Node<K> node, int i){
        if(node==null&&i==0){
            node = new Node<K>(t);
            node.child.set(0, root);
            root = node;
            height++;
        }
        if(node.child.at(i)==null)
            return;
        Node<K> ch = node.child.at(i);
        int num = ch.n;
        K k = ch.key.at(num/2);
        Node<K> n;
        if(ch.leaf){
            n = new PlusNode<K, T>(t);
            n.child=null;
        }
        else
            n = new Node<K>(t);
        n.leaf = ch.leaf;
        {
            int j=0;
            int x;
            for(x=num/2+1; x<num; x++){
                n.key.set(j, ch.key.at(x));
                ch.key.set(x, null);
                if(ch.leaf){
                    ((PlusNode<K, T>)n).data.set(j, ((PlusNode<K, T>)ch).data.at(x));
                    ((PlusNode<K, T>)ch).data.set(x, null);
                } else {
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
            if(ch.leaf)
                ch.n=num/2+1;
            else
                ch.n=num/2;
        }
        node.addKey(i, k);
        node.addChild(i+1, n);
    }

    public K findKey(K key){
        int index = -1;
        Node<K> cur = root;
        while(index==-1&&cur!=null){
            int i=cur.n;
            while(i>0 && key.compareTo(cur.key.at(i-1))<=0)
                i--;

            if(i<cur.n && key.compareTo(cur.key.at(i))==0 && cur.leaf){
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

    @SuppressWarnings("unchecked")
    public T findData(K key){
        int index = -1;
        Node<K> cur = root;
        while(index==-1&&cur!=null){
            int i=cur.n;
            while(i>0 && key.compareTo(cur.key.at(i-1))<=0)
                i--;

            if(i<cur.n && key.compareTo(cur.key.at(i))==0 && cur.leaf){
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
            return ((PlusNode<K,T>)cur).data.at(index);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public void deleteKey(K key){
        int index = -1;
        Node<K> cur = root;
        while(index==-1&&cur!=null){
            int i=cur.n;
            while(i>0 && key.compareTo(cur.key.at(i-1))<=0){
                i=i-1;
            }
            if(i<cur.n && key.compareTo(cur.key.at(i))==0){
                index = i;
            } else {
                if(!cur.leaf){
                    if(i<cur.n){
                        Node<K> ch1 = cur.child.at(i);
                        Node<K> ch2 = cur.child.at(i+1);
                        if(ch1.n>t-1);
                        else if(ch2.n>t-1){
                            if(!ch1.leaf){
                                ch1.addChild(ch1.n+1, ch2.child.at(0));
                                ch1.addKey(ch1.n,cur.key.at(i));
                                cur.key.set(i, ch2.key.at(0));
                                ch2.removeChild(0);
                                ch2.removeKey(0);
                            } else {
                                cur.key.set(i, ch2.key.at(0));
                                ((PlusNode<K,T>)ch1).addKey(ch1.n,ch2.key.at(0),((PlusNode<K,T>)ch2).data.at(0));
                                ch2.removeKey(0);
                            }
                        } else {
                            mergeNode(cur, i);
                        }
                    } else {
                        Node<K> ch1 = cur.child.at(i-1);
                        Node<K> ch2 = cur.child.at(i);
                        if(ch2.n>t-1);
                        else if(ch1.n>t-1){
                            if(!ch1.leaf){
                                ch2.addChild(0, ch1.child.at(ch1.n));
                                ch2.addKey(0,cur.key.at(i-1));
                                cur.key.set(i-1, ch1.key.at(ch1.n-1));
                                ch1.removeChild(ch1.n);
                                ch1.removeKey(ch1.n-1);
                            } else {
                                cur.key.set(i-1, ch1.key.at(ch1.n-2));
                                ((PlusNode<K,T>)ch2).addKey(0,ch1.key.at(ch1.n-1),((PlusNode<K,T>)ch1).data.at(ch1.n-1));
                                ch1.removeKey(ch1.n-1);
                            }
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

    @SuppressWarnings("unchecked")
    public void deleteKey(Node<K> node, int i){
        if(!node.leaf){
            if(node.child.at(i).n>t-1){
                node.key.set(i, deleteLast(node.child.at(i)));
            } else if(node.child.at(i+1).n>t-1) {
                Node<K> ch1 = node.child.at(i);
                Node<K> ch2 = node.child.at(i+1);
                int index = node.child.at(i).n;
                if(!ch1.leaf){
                    ch1.addChild(ch1.n+1, ch2.child.at(0));
                    ch1.addKey(ch1.n,node.key.at(0));
                    node.key.set(i, ch2.key.at(0));
                    ch2.removeChild(0);
                    ch2.removeKey(0);
                } else {
                    node.key.set(i, ch2.key.at(0));
                    ((PlusNode<K,T>)ch1).addKey(ch1.n,ch2.key.at(0),((PlusNode<K,T>)ch2).data.at(0));
                    ch2.removeKey(0);
                    index=index-1;
                }
                deleteKey(ch1, index);
            } else {
                int index = node.child.at(i).n;
                mergeNode(node, i);
                if(node.child.at(i).leaf)
                    index=index-1;
                deleteKey(node.child.at(i), index);
            }
        } else {
            ((PlusNode<K,T>)node).removeKey(i);
            if(root==node&&node.n==0)
                root=null;
        }
    }

    @SuppressWarnings("unchecked")
    public K deleteLast(Node<K> node){
        if(!node.leaf){
            Node<K> ch1 = node.child.at(node.n-1);
            Node<K> ch2 = node.child.at(node.n);
            if(ch2.n>t-1){
                return deleteLast(ch2);
            } else if(ch1.n>t-1){
                if(!ch1.leaf){
                    ch2.addChild(0, ch1.child.at(ch1.n));
                    ch2.addKey(0,node.key.at(node.n-1));
                    node.key.set(node.n-1, ch1.key.at(ch1.n-1));
                    ch1.removeChild(ch1.n);
                    ch1.removeKey(ch1.n-1);
                } else {
                    node.key.set(node.n-1, ch1.key.at(ch1.n-2));
                    ((PlusNode<K,T>)ch2).addKey(0,ch1.key.at(ch1.n-1),((PlusNode<K,T>)ch1).data.at(ch1.n-1));
                    ch1.removeKey(ch1.n-1);
                }
                return deleteLast(ch2);
            } else {
                mergeNode(node, node.n-1);
                return deleteLast(node.child.at(node.n));
            }
        } else {
            K ret = node.key.at(node.n-2);
            node.removeKey(node.n-1);
            return ret;
        }
    }

    @SuppressWarnings("unchecked")
    public void mergeNode(Node<K> node, int i){
        Node<K> ch1 = node.child.at(i);
        Node<K> ch2 = node.child.at(i+1);
        int num = ch1.n+1+ch2.n;
        int j=0;
        int x=ch1.n+1;
        if(!ch1.leaf)
            ch1.key.set(ch1.n, node.key.at(i));
        else{
            num--;
            x--;
        }
        for(; x<num; x++){
            ch1.key.set(x, ch2.key.at(j));
            if(!ch1.leaf)
                ch1.child.set(x, ch2.child.at(j));
            else
                ((PlusNode<K,T>)ch1).data.set(x, ((PlusNode<K,T>)ch2).data.at(j));
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

    @SuppressWarnings("unchecked")
    public void print(){
        if(root==null){
            System.out.println(""+null);
            return;
        }
        Queue<Node<K>> q1 = (Queue<Node<K>>)new Queue((int)Math.pow(2*t,height));
        Queue<Node<K>> q2 = (Queue<Node<K>>)new Queue((int)Math.pow(2*t,height));
        Node<K> t;
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