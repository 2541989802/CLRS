package binarysearchtree;

public class BinarySearchTree<T extends Comparable<T>>{
    public class Node<T extends Comparable<T>> implements Comparable<Node<T>>{
        public T data;
        public Node<T> parent;
        public Node<T> left;
        public Node<T> right;

        public Node(T data, Node<T> parent){
            this.data = data;
            this.parent = parent;
        }

        public int compareTo(Node<T> o){
            if(o==null)
                throw new RuntimeException("BinarySearchTree<T>.Node<T>.compareTo(): recieve a null object, shouldn't happen in code");
            if(data!=null)
                return data.compareTo(o.data);
            if(o.data!=null)
                return -1*o.data.compareTo(data);
            else
                return 0;
        }
    }

    public Node<T> root;

    public void treeWalk(Node<T> root){
        if(root!=null){
            treeWalk(root.left);
            System.out.print(root.data+", ");
            treeWalk(root.right);
        }
    }

    public void treeWalk(){
        treeWalk(root);
    }

    public Node<T> search1_h(Node<T> node, T key){
        Node<T> other = new Node<T>(key,null);
        if(node==null)
            return null;
        else if(node.compareTo(other)==0)
            return node;
        else if(node.compareTo(other)>0)
            return search1_h(node.left, key);
        else
            return search1_h(node.right, key);
    }

    public Node<T> search2_h(Node<T> node, T key){
        Node<T> other = new Node<T>(key, null);
        while(node!=null&&node.compareTo(other)!=0){
            if(node.compareTo(other)>0)
                node = node.left;
            else
                node = node.right;
        }
        return node;
    }

    public T search(T key){
        Node<T> res = search2_h(root, key);
        return res!=null?res.data:null;
    }

    public Node<T> min_h(Node<T> node){
        while(node!=null&&node.left!=null){
            node = node.left;
        }
        return node;
    }

    public T min(){
        Node<T> res = min_h(root);
        return res!=null?res.data:null;
    }

    public Node<T> max_h(Node<T> node){
        while(node!=null&&node.right!=null){
            node = node.right;
        }
        return node;
    }

    public T max(){
        Node<T> res = max_h(root);
        return res!=null?res.data:null;
    }

    public Node<T> successor_h(Node<T> node){
        if(node!=null){
            if(node.right!=null)
                return min_h(node.right);
            else {
                while(node.parent!=null && node.parent.right==node)
                    node = node.parent;
                return node.parent;
                }
        }
        return null;
    }

    public T successor(T key){
        Node<T> res = successor_h(search2_h(root, key));
        return res!=null?res.data:null;
    }

    public void insert(Node<T> z){
        Node<T> p = null;
        Node<T> x = root;
        while(x!=null){
            p = x;
            if(x.compareTo(z)>=0)
                x = x.left;
            else
                x = x.right;
        }
        z.parent = p;
        if(p==null)
            root = z;
        else if(p.compareTo(z)>=0)
            p.left = z;
        else
            p.right = z;
    }

    public void insert(T key){
        insert(new Node<T>(key, null));
    }

    //v replace u
    public void transplant(Node<T> u, Node<T> v){
        if(u.parent==null)
            root = v;
        else if(u==u.parent.left)
            u.parent.left = v;
        else
            u.parent.right = v;
        if(v!=null)
            v.parent = u.parent;
    }

    public void delete(Node<T> u){
        if(u==null)
            return;
        else if(u.left==null)
            transplant(u, u.right);
        else if(u.right==null){
            transplant(u, u.left);
        } else {
            Node<T> y = min_h(u.right);
            if(y.parent!=u){
                transplant(y, y.right);
                y.right = u.right;
                y.right.parent = y;
            }
            transplant(u, y);
            y.left = u.left;
            y.left.parent = y;
        }
    }

    public void delete(T key){
        delete(search2_h(root, key));
    }
}