package vanemdeboas;

public class ProtovEB{
    public int ur;
    public int lr;
    public int n;
    public ProtovEB summary;
    public ProtovEB[] cluster;
    public ProtovEB(int u){
        n=0;
        if(u<=2){
            ur=2;
            lr=1;
            summary = this;
            cluster = new ProtovEB[2];
        } else {
            int k=1;
            lr=0;
            while(k<u){
                k*=2;
                lr++;
            }
            lr=(int)Math.pow(2, lr/2);
            ur=u/lr;
            cluster = new ProtovEB[ur];
            for(int i=0; i< ur; i++){
                cluster[i] = new ProtovEB(lr);
            }
            summary = new ProtovEB(ur);
        }
    }    
}