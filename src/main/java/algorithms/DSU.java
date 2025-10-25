package algorithms;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DSU {
    private Map<String,String> parent;
    private Map<String,Integer> rank;

    public DSU(Iterable<String> vertices){
        parent= new HashMap<>();
        rank=new HashMap<>();
        for(String vertex: vertices){
            //Pls thats me writing the comment not gpt ok
            //At first every vertexs parent and rank 0
            parent.put(vertex,vertex);
            rank.put(vertex,0);
        }
    }

    public String find(String vertex){
        //If parent of vertex isnt equal to itself
        if(!parent.get(vertex).equals(vertex)){
            //keep going till vertexs parent isnt equal to itself
            parent.put(vertex,find(parent.get(vertex)));
        }
        return parent.get(vertex);
    }
    public void union(String x,String y){
        String rootX= find(x);
        String rootY=find(y);

        if(rootX.equals(rootY)){
            return;
        }
        if(rank.get(rootX)>rank.get(rootY)){
            parent.put(rootY,rootX);
        }
        else if (rank.get(rootX)<rank.get(rootY)){
            parent.put(rootX,rootY);
        }
        else{
            parent.put(rootX,rootY);
        }
    }
}