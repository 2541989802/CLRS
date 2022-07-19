package augmentingdatastructure;

import redblacktree.RedBlackTree;
import augmentingdatastructure.Node;

public class OSIntervalTree<T extends Comparable<T>> extends RedBlackTree<T>{
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

    public void leftRotate(Node<T> ro){
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

    public void rightRotate(Node<T> ro){
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

    public void delete(T key){
        delete((Node<T>)search2_h(root, key));
    }

    public Node<T> delete(Node<T> n){
        System.out.println("Atree");
        if(n==null)
            return null;
        Node<T> oldp = (Node<T>)(n.parent);
        Node<T> node = (Node<T>)(super.delete(n));
        System.out.println(node);
        while(oldp!=null && oldp!=node.parent){
            node.setSize();
            oldp.setMax();
            oldp=(Node<T>)(oldp.parent);
        }
        while(node!=null&&node!=oldp){
            node.setSize();
            node.setMax();
            node=(Node<T>)(node.parent);
        }
        return (Node<T>)n;
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