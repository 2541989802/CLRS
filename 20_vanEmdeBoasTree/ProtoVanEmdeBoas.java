package vanemdeboas;

public class ProtoVanEmdeBoas{
    public ProtovEB root;
    public int u;
    
    public ProtoVanEmdeBoas(int u){
        int i = 2;
        while(i<u){
            i*=2;
        }
        this.u=i;
        root = new ProtovEB(i);
    }

    public boolean member(int x){
        if(x<0 || x>=root.ur*root.lr)
            return false;
        return member(root, x);
    }

    public boolean member(ProtovEB v, int x){
        if(v.ur*v.lr==2)
            return v.cluster[x]!=null;
        else
            return member(v.cluster[x/v.lr], x%v.lr);
    }

    public void insert(int x){
        if(x<0 || x>=root.ur*root.lr)
            return;
        else
            insert(root, x);
    }

    public void insert(ProtovEB v, int x){
        if(v.ur*v.lr==2){
            if(v.cluster[x]==null){
                v.cluster[x]=v;
                v.n++;
            }
        } else {
            v.n-=v.cluster[x/v.lr].n;
            insert(v.cluster[x/v.lr], x%v.lr);
            insert(v.summary, x/v.lr);
            v.n+=v.cluster[x/v.lr].n;
        }
    }

    public void delete(int x){
        if(x<0 || x>=root.ur*root.lr)
            return;
        else
            delete(root, x);
    }

    public void delete(ProtovEB v, int x){
        if(v.ur*v.lr==2){
            if(v.cluster[x]!=null){
                v.cluster[x]=null;
                v.n--;
            }
        } else {
            v.n-=v.cluster[x/v.lr].n;
            delete(v.cluster[x/v.lr], x%v.lr);
            v.n+=v.cluster[x/v.lr].n;
            if(v.cluster[x/v.lr].n==0){
                delete(v.summary, x/v.lr);
            }
        }
    }

    public int min(){
        return min(root);
    }

    public int min(ProtovEB v){
        if(v.ur*v.lr==2){
            if(v.cluster[0]!=null)
                return 0;
            else if(v.cluster[1]!=null)
                return 1;
            else
                return -1;
        } else {
            int m = min(v.summary);
            if(m==-1)
                return -1;
            else
                return m*v.lr+min(v.cluster[m]);
        }
    }

    public int max(){
        return max(root);
    }

    public int max(ProtovEB v){
        if(v.ur*v.lr==2){
            if(v.cluster[1]!=null)
                return 1;
            else if(v.cluster[0]!=null)
                return 0;
            else
                return -1;
        } else {
            int m = max(v.summary);
            if(m==-1)
                return -1;
            else
                return m*v.lr+max(v.cluster[m]);
        }
    }

    public int successor(int x){
        return successor(root, x);
    }

    public int successor(ProtovEB v,int x){
        if(v.ur*v.lr==2){
            if(x==0 && v.cluster[1]!=null)
                return 1;
            else return -1;
        } else {
            int off = successor(v.cluster[x/v.lr], x%v.lr);
            if(off!=-1){
                return (x/v.lr)*v.lr+off;
            } else {
                int next = successor(v.summary, x/v.lr);
                if(next==-1)
                    return -1;
                else
                    return next*v.lr+min(v.cluster[next]);
            }
        }
    }

    public int predecessor(int x){
        return predecessor(root, x);
    }

    public int predecessor(ProtovEB v,int x){
        if(v.ur*v.lr==2){
            if(x==1 && v.cluster[0]!=null)
                return 0;
            else return -1;
        } else {
            int off = predecessor(v.cluster[x/v.lr], x%v.lr);
            if(off!=-1){
                return (x/v.lr)*v.lr+off;
            } else {
                int prev = predecessor(v.summary, x/v.lr);
                if(prev==-1)
                    return -1;
                else
                    return prev*v.lr+max(v.cluster[prev]);
            }
        }
    }
}