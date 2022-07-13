package hashtable;

import basicdatastructure.*;

public class PerfectHash<E>{
    private HashTable<UniversalHash, E>[] table;
    private UniversalHash h1;

    public PerfectHash(E[] data, int[] key){
        if(data.length!=key.length)
            throw new RuntimeException("PerfectHash(): data, key length inconsistent");
        h1 = new UniversalHash(data.length);
        h1.constAB=true;
        setup(data, key);
    }

    private void setup(E[] data, int[] key){
        int[] count = new int[key.length];
        table = new HashTable[key.length];
        for(int i = 0; i<key.length; i++){
            count[h1.hash(key[i])] = count[h1.hash(key[i])] + 1;
        }
        for(int i = 0; i<key.length; i++){
            if(count[i]!=0){
                table[i] = new HashTable<UniversalHash, E>(new UniversalHash(true), 0, count[i]*count[i]);
            }
        }
        for(int i = 0; i < data.length; i++){
            table[h1.hash(key[i])].insert(data[i], key[i]);
        }
        /////////////////////
        for(int i = 0; i < key.length; i++){
            System.out.print(search(key[i])+", ");
        }
        /////////////////////
        for(int i = 0; i < key.length; i++){
            if(table[i]!=null){
                int maxsize=0;
                for(LinkList list: table[i].table){
                    if(list.getSize() > maxsize)
                        maxsize = list.getSize();
                }
                while(maxsize>1){
                    HashTable<UniversalHash, E> temp = table[i];
                    table[i] = new HashTable<UniversalHash, E>(new UniversalHash(true), 0, count[i]*count[i]);
                    for(int j = 0; j < temp.table.length; j++){
                        for(HashTable<UniversalHash, E>.Data<E> dataKey: temp.table[j]){
                            table[i].insert(dataKey.data, dataKey.key);
                        }
                    }
                    maxsize=0;
                    for(LinkList list: table[i].table){
                        if(list.getSize() > maxsize)
                            maxsize = list.getSize();
                    }
                }

            }
        }
    }

    public E search(int key){
        return table[h1.hash(key)].search(key);
    }

    public int collision(int key){
        return table[h1.hash(key)].collision(key);
    }
}