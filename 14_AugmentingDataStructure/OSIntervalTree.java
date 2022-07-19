package augmentingdatastructure;

import redblacktree.*;

public class OSIntervalTree<T extends Comparable<T>> extends RedBlackTree<T>{
    public class Node<T extends Comparable<T>> extends RedBlackTree<T>.Node<T>{
        int size;
        T high;
        T max;
        public Node(T low, T high, Node<T> parent, boolean black){
            super(low, parent, black);
            this.high = high;
            if(high!=null&&high.compareTo(low)<0)
                this.high = low;
            else if(low!=null&&low.compareTo(high)>0)
                this.high = low;
            setMax();
            setSize();
        }

        @SuppressWarnings("unchecked")
        public void setMax(){
            T l = left!=null?((Node<T>)left).max:high;
            T r = right!=null?((Node<T>)right).max:high;
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
            size = (left!=null?((Node<T>)left).size:0)+(right!=null?((Node<T>)right).size:0)+1;
        }

        public int insect(T i){
            if(i!=null){
                if(i.compareTo(data)>=0&&i.compareTo(max)<=0)
                    return 0;
                else if(i.compareTo(max)>0)
                    return 1;
                else
                    return -1;
            }
            else if(data!=null&&max!=null){
                if(data.compareTo(i)<=0&&max.compareTo(i)>=0)
                    return 0;
                else if(max.compareTo(i)<0)
                    return 1;
                else
                    return -1;
            }
            else if(max!=null){
                if(max.compareTo(i)>=0)
                    return 0;
                else
                    return 1;
            }
            else if(data!=null){
                if(data.compareTo(i)<=0)
                    return 0;
                else
                    return -1;
            }
            return 0;
        }
    }

    public void insert(T data){
        insert(new Node<T>(data, data, null, false));
    }

    public void insert(T low, T high){
        insert(new Node<T>(low, high, null, false));
    }

    public void insert(Node<T> node){
        super.insert(node);
        while(node!=null){
            node.setSize();
            node=(Node<T>)(node.parent);
        }
    }

    @Override
    public void leftRotate(RedBlackTree<T>.Node<T> ro){
        Node<T> root = (Node<T>) ro;
        Node<T> old = (Node<T>)(root.right);
        root.setSize();
        root.setMax();
        super.leftRotate(root);
        if(old!=null){
            old.size = root.size;
            root.setSize();
        }
        root.setMax();
        old.setMax();
    }

    @Override
    public void rightRotate(RedBlackTree<T>.Node<T> ro){
        Node<T> root = (Node<T>)ro;
        Node<T> old = (Node<T>)(root.left);
        root.setSize();
        root.setMax();
        super.rightRotate(root);
        if(old!=null){
            old.size = root.size;
            root.setSize();
        }
        root.setMax();
        old.setMax();
    }

    public void delete(Node<T> u){
        Node<T> oldp = (Node<T>)(u.parent);
        Node<T> node = (Node<T>)(super.delete(u));
        while(oldp!=null){
            oldp.setSize();
            oldp.setMax();
            oldp=(Node<T>)(oldp.parent);
        }
        while(node!=null&&node!=oldp){
            node.setSize();
            node.setMax();
            node=(Node<T>)(node.parent);
        }
    }

    public Node<T> osSelect1(Node<T> node, int i){
        if(node == null)
            return null;
        int r = (node.left!=null?((Node<T>)(node.left)).size:0)+1;
        if(i == r)
            return node;
        else if(i < r)
            return osSelect1((Node<T>)(node.left), i);
        else
            return osSelect1((Node<T>)(node.right), i-r);
    }

    public Node<T> osSelect2(Node<T> node, int i){
        int r = (node.left!=null?((Node<T>)(node.left)).size:0)+1;;
        while(node!=null&&r!=i){
            if(i < r){
                node = (Node<T>)(node.left);
                if(node!=null)
                    r -= (node.right!=null?((Node<T>)(node.right)).size:0)+1;
            }
            else{
                node = (Node<T>)(node.right);
                if(node!=null)
                    r += (node.left!=null?((Node<T>)(node.left)).size:0)+1;
            }
        }
        return node;
    }

    public T osSelect(int i){
        Node<T> res = osSelect2((Node<T>)root, i);
        return res!=null?res.data:null;
    }

    public int osRank(Node<T> node){
        int r = (node.left!=null?((Node<T>)(node.left)).size:0)+1;
        while(node!=root){
            if(node==node.parent.right)
                r += ((Node<T>)(node.parent)).size+1;
            node = (Node<T>)(node.parent);
        }
        return r;
    }

    public Node<T> intervalSearch(T i){
        Node<T> node = (Node<T>)root;
        while(node.insect(i)!=0){
            if(node.insect(i)>0)
                node = (Node<T>)(node.right);
            else
                node = (Node<T>)(node.left);
        }
        return node;
    }
}