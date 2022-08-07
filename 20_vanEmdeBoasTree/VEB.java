package vanemdeboas;

public class VEB{
    public int ur;
    public int lr;
    public int n;
    public VEB summary;
    public VEB[] cluster;
    public int min;
    public int max;
    public VEB(int u){
        min=-1;
        max=-1;
        n=0;
        if(u<=2){
            ur=2;
            lr=1;
            summary = this;
            cluster = new VEB[2];
        } else {
            int k=1;
            lr=0;
            while(k<u){
                k*=2;
                lr++;
            }
            lr=(int)Math.pow(2, lr/2);
            ur=u/lr;
            cluster = new VEB[ur];
            for(int i=0; i< ur; i++){
                cluster[i] = new VEB(lr);
            }
            summary = new VEB(ur);
        }
    }
}