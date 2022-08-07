package vanemdeboas;

public class VanEmdeBoas{
    public VEB root;
    public int u;

    public VanEmdeBoas(int u){
        int i = 2;
        while(i<u){
            i*=2;
        }
        this.u=i;
        root = new VEB(i);
    }

    public int max(){
        return root.max;
    }

    public int min(){
        return root.min;
    }

    public void insert(int x){
        if(x<0||x>=u)
            return;
        insert(root, x);
    }

    public void insert(VEB v, int x){
        if(v.min==x){
            return;
        }
        if(v.min==-1){
            v.min = x;
            v.max = v.min;
        } else {
            if(v.min>x){
                int t = v.min;
                v.min = x;
                x = t;
            }
            if(v.ur*v.lr>2){
                if(v.cluster[x/v.lr].min==-1){
                    insert(v.summary, x/v.lr);
                }
                insert(v.cluster[x/v.lr],x%v.lr);
            }
            if(v.max<x)
                v.max = x;
        }
    }

    public void delete(int x){
        if(x<0||x>=u)
            return;
        delete(root, x);
    }

    public void delete(VEB v, int x){
        if(v.min>x||v.max<x)
            return;
        if(v.min==v.max){
            v.min=-1;
            v.max=v.min;
        } else if(v.ur*v.lr==2){
            if(x==0)
                v.min=1;
            else
                v.min=0;
            v.max = v.min;
        } else {
            if(x==v.min){
                x = v.summary.min*v.lr+v.cluster[v.summary.min].min;
                v.min = x;
            }
            delete(v.cluster[x/v.lr], x%v.lr);
            if(v.cluster[x/v.lr].min==-1){
                delete(v.summary, x/v.lr);
                if(v.max==x){
                    if(v.summary.max!=-1)
                        v.max = v.summary.max*v.lr+v.cluster[v.summary.max].max;
                    else
                        v.max = v.min;
                }
            } else if(v.max==x){
                v.max = (x/v.lr)*v.lr+v.cluster[x/v.lr].max;
            }
        }
    }

    public boolean member(int x){
        return member(root, x);
    }

    public boolean member(VEB v, int x){
        if(x==v.max||x==v.min)
            return true;
        else if(v.ur*v.lr==2)
            return false;
        else
            return member(v.cluster[x/v.lr], x%v.lr);
    }

    public int successor(int x){
        return successor(root, x);
    }

    public int successor(VEB v, int x){
        if(v.ur*v.lr==2){
            if(x==0&&v.max==1)
                return 1;
            else
                return -1;
        } else if(v.min!=-1 && x<v.min){
            return v.min;
        } else {
            int m = v.cluster[x/v.lr].max;
            if (m!=-1&&m>x%v.lr)
                return (x/v.lr)*v.lr+successor(v.cluster[x/v.lr], x%v.lr);
            else {
                int next = successor(v.summary, x/v.lr);
                if(next==-1)
                    return -1;
                else
                    return next*v.lr+v.cluster[next].min;
            }
        }
    }

    public int predecessor(int x){
        return predecessor(root, x);
    }

    public int predecessor(VEB v, int x){
        if(v.ur*v.lr==2){
            if(x==1&&v.min==0)
                return 0;
            else
                return -1;
        } else if(v.max!=-1 && x>v.max){
            return v.max;
        } else {
            int m = v.cluster[x/v.lr].min;
            if(m!=-1&&m<x%v.lr){
                int pre = predecessor(v.cluster[x/v.lr], x%v.lr);
                int ret = (x/v.lr)*v.lr+pre;
                return ret;
            }
            else {
                int next = predecessor(v.summary, x/v.lr);
                if(next==-1){
                    if(v.min!=-1&&v.min<x)
                        return v.min;
                    else
                        return -1;
                } else{
                    int ret = next*v.lr+v.cluster[next].max;
                    if(!member(v,ret))
                        throw new RuntimeException("");
                    return ret;
                }
            }
        }
    }

    public boolean check(){
        return check(root);
    }

    public boolean check(VEB v){
        if(v.ur*v.lr==2)
            return true;
        if(v.ur*v.lr!=2&&v.min==v.max&&v.summary.min!=-1){
            System.out.println(v.min+"\t"+v.max+"\t"+v.summary.min+"\t"+v.lr*v.ur);
            return false;
        }
        if(v.ur*v.lr!=2&&v.min!=v.max&&v.summary.min==-1){
            System.out.println(v.min+"\t"+v.max+"\t"+v.summary.min+"\t"+v.lr*v.ur);
            return false;
        }
        boolean ret = check(v.summary);
        for(int i=0; i<v.cluster.length && ret; i++)
            ret = ret&&check(v.cluster[i]);
        return ret;
    }
}