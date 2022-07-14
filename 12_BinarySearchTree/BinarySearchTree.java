package binarysearchtree;

public class BinarySearchTree<T extends Compareble<T>>{
    public class Node<T>{
        public T data;
        public Node<T> head;
        public Node<T> left;
        public Node<T> right;

        public Node(T data, Node<T> head){
            this.data = data;
            this.head = head;
        }
    }

    public Node<T> head;

    public void treeWalk(Node<T> head){
        if(head!=null){
            treeWalk(head.left);
            System.out.print(head.data+", ");
            treeWalk(head.right);
        }
    }

    private Node<T> search1_h(Node<T> node, T key){
        if(node==null||(key==null&&node.data==null))
            return null;
        else if((key!=null&&key.compareTo(node.data)==0)||(node.data!=null && node.data.compareTo(key)==0))
            return node;
        else if((key!=null&&key.compareTo(node.data)<0)||(node.data!=null && node.data.compareTo(key)>0))
            return search(node.left, key);
        else
            return search(node.right, key);
    }

    private Node<T> search2_h(Node<T> node, T key){
        while(node!=null&&((key!=null&&key.compareTo(node.data)!=0)||(node.data!=null && node.data.compareTo(key)!=0))){
            if((key!=null&&key.compareTo(node.data)<0)||(node.data!=null && node.data.compareTo(key)>0))
                node = node.left;
            else
                node = node.right;
        }
        return node;
    }

    private Node<T> min_h(){
        Node<T> node = head;
        while(node!=null&&node.left!=null){
            node = node.left;
        }
        return node;
    }

    private Node<T> max_h(){
        Node<T> node = head;
        while(node!=null&&node.right!=null){
            node = node.right;
        }
        return node;
    }
}