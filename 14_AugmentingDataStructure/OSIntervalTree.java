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
            node.setMax();
            node=(Node<T>)(node.parent);
        }
    }

    @Override
    public void leftRotate(redblacktree.Node<T> node){
        if(node instanceof Node)
            leftRotate((Node<T>)node);
        else
            super.leftRotate(node);
    }

    public void leftRotate(Node<T> root){
        Node<T> old = (Node<T>)(root.right);
        root.setSize();
        root.setMax();
        super.leftRotate(root);
        if(old!=null){
            old.setSize();
        }
        root.setSize();
        root.setMax();
        old.setMax();
    }

    @Override
    public void rightRotate(redblacktree.Node<T> node){
        if(node instanceof Node)
            rightRotate((Node<T>)node);
        else
            super.rightRotate(node);
    }

    public void rightRotate(Node<T> root){
        Node<T> old = (Node<T>)(root.left);
        root.setSize();
        root.setMax();
        super.rightRotate(root);
        if(old!=null){
            old.setSize();
        }
        root.setSize();
        root.setMax();
        old.setMax();
    }

    public void delete(T key){
        delete((Node<T>)search2_h(root, key));
    }

    public Node<T> delete(Node<T> n){
        if(n==null)
            return null;
        Node<T> oldp = (Node<T>)(n.parent);
        redblacktree.Node<T> t = super.delete(n);
        Node<T> node = (t!=null&&!(t instanceof Node))?(Node<T>)(t.parent):(Node<T>)t;
        while(node!=null&&node!=oldp){
            node.setSize();
            node.setMax();
            node=(Node<T>)(node.parent);
        }
        while(oldp!=null){
            oldp.setSize();
            oldp.setMax();
            oldp=(Node<T>)(oldp.parent);
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
        while(node!=null&&node.insect(i)!=0){
            if(node.left!=null&&((Node<T>)(node.left)).insect(i)<=0)
                node = (Node<T>)(node.left);
            else
                node = (Node<T>)(node.right);
        }
        return node;
    }

    public void nodeSize_h(Node<T> node, int i){
        if(node==null)
            return;
        nodeSize_h((Node<T>)(node.left),i+1);
        System.out.print("("+node.data+":"+((Node<T>)node).size+":"+i+"), ");
        nodeSize_h((Node<T>)(node.right),i+1);
    }

    public void nodeSize(){
        System.out.println("");
        nodeSize_h((Node<T>)root, 1);
        System.out.println("");
    }

    public void nodeMax_h(Node<T> node, int i){
        if(node==null)
            return;
        nodeMax_h((Node<T>)(node.left),i+1);
        System.out.print("("+node.data+":"+node.high+":"+((Node<T>)node).max+":"+i+"), ");
        nodeMax_h((Node<T>)(node.right),i+1);
    }

    public void nodeMax(){
        System.out.println("");
        nodeMax_h((Node<T>)root, 1);
        System.out.println("");
    }

    public boolean check(){
        boolean res1 = super.check();
        if(!res1)
            System.out.print("RED BLACK FAILURE, ");
        boolean res2 = (this.check_h1((Node<T>)root)==1);
        if(!res2)
            System.out.print("SIZE FAILURE, ");
        boolean res3 = (this.check_h2((Node<T>)root)==1);
        if(!res3)
            System.out.print("MAX FAILURE, ");
        boolean res = res1&&res2&&res3;
        if(!res)
            System.out.println("");
        return res;
    }

    public int check_h1(Node<T> node){
        if(node==null)
            return 1;
        int l = node.left==null?0:((Node<T>)(node.left)).size;
        int r = node.right==null?0:((Node<T>)(node.right)).size;
        return (((l+r+1)==node.size)&&check_h1((Node<T>)(node.left))==1&&check_h1((Node<T>)(node.right))==1)?1:0;
    }

    public int check_h2(Node<T> node){
        if(node==null)
            return 1;
        T l = (node.left!=null)?((Node<T>)(node.left)).max:node.high;
        T r = (node.right!=null)?((Node<T>)(node.right)).max:node.high;
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
        if(node.high!=null){
            if(node.high.compareTo(m)>=0)
                m = node.high;
        } else if(m!=null){
            if(m.compareTo(node.high)<0)
                m = node.high;
        }
        if((m==null&&node.max==null)||(m!=null&&m.compareTo(node.max)==0)||(node.max!=null&&node.max.compareTo(m)==0))
            return (check_h2((Node<T>)(node.left))+check_h2((Node<T>)(node.right))==2)?1:0;
        return -1;
    }
}