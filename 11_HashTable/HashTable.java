package hashtable;

import basicdatastructure.*;

public class HashTable<T extends Hashfun, E>{
    public class Data<E> implements Comparable<Data<E>>{
        public int key;
        public E data;
        public Data(E data, int key){
            this.data = data;
            this.key = key;
        }
        public int compareTo(Data o){
            return key-o.key;
        }
    }

    public LinkList<Data<E>>[] table;
    public T hash;

    public HashTable(T hash, int nkey, int slots){
        this.hash = hash;
        hash.setSlots(nkey, slots);
        table = new LinkList[getSlots()];
        for(int i=0; i < getSlots(); i++)
            table[i] = new LinkList<Data<E>>();
    }

    public int getSlots(){
        return hash.getSlots();
    }

    public void insert(E data, int key){
        if(search(key)!=null)
            throw new RuntimeException("HashTable.insert(): the key already exist");
        this.table[hash.hash(key)].insert(new Data<E>(data, key));
    }

    public E search(int key){
        Data<E> d = ((Data<E>)(table[hash.hash(key)].search(new Data<E>(null, key))));
        return d==null?null:d.data;
    }

    public int collision(int key){
        LinkList<Data<E>>.Node<Data<E>> head = table[hash.hash(key)].head;
        Data<E> k = new Data<E>(null, key);
        int i = 0;
        while(head!=null && head.data.compareTo(k)!=0)
            i++;
        return i;
    }

    public void delete(int key){
        table[hash.hash(key)].delete(new Data<E>(null, key));
    }

    public void print(){
        System.out.println("");
        for(LinkList<Data<E>> list : table){
            System.out.print("\nkeys:");
            LinkList<Data<E>>.Node<Data<E>> head = list.head;
            while(head != null){
                System.out.print(head.data.key+", ");
                head = head.next;
            }
            head = list.head;
            System.out.print("\ndata:");
            while(head != null){
                System.out.print(head.data.data+", ");
                head = head.next;
            }
        }
        System.out.println("");
    }
}