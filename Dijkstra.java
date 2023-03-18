/**
 * Input: directed graph G = (V,E) in adjacency-list representation, a vertex s in V, a length greater than zero for each e in E
 * Postcondition: for every vertex v, the value len(v) equals the true shortest-path distance dist(s,v)
 * V = {s,t,v,w} E={1,2,3,4,5} LenE={1,2,3,4,5,6}
 * FromToE= {1(s,v), 2(v,w), 3(w,t), 4(ts,w), 5(v,t)}
 */

import java.util.*;

public class Dijkstra {
    public static void main(String[] args){
        char FromToE[][]={{'s','v'},{'v','w'},{'w','t'},{'s','w'},{'v','t'}};
        int len [] = {1,2,3,4,5};
        char vertexes[]={'s', 't', 'v', 'w'};
        graphs dijkstra = new graphs(vertexes,FromToE,len);
        dijkstra.printVertexList();
        System.out.println();
        dijkstra.printAdjacencies();
        // for(int i=0; i<FromToE.length; i++){
        //     System.out.printf(" %d(%c,%c} \n", len[i], FromToE[i][0], FromToE[i][1]);
        }
    }



class graphs{
private int lengths[];
private char adjacencylist[][];
private char vertexlist[];

// constructor
public graphs(char vertexes[],  char adjacencylist[][],int lengths[]) {
    this.lengths=lengths;
    this.adjacencylist=adjacencylist;
    this.vertexlist=vertexes;
}
// get length
public int getLength(int index){
    return lengths[index];
}
//print adjacency list
public void printAdjacencies(){
    for(int i=0; i<adjacencylist.length; i++){
        System.out.printf(" %d(%c,%c} \n", lengths[i], adjacencylist[i][0], adjacencylist[i][1]); 
    }
}

// print vertex list
public void printVertexList(){
    for(char vertex:vertexlist){
        System.out.printf("%c ",vertex);
    }
}

// Dijkstras shortest path algorighm
public int[] shortestPath(char vertex) {
    // x:={s}
    char seenVertexes[] = new char[vertexlist.length];
    seenVertexes[0]=vertex;
    int seenVertexIndex = 0;
    seenVertexIndex++;
    // lens(s) = 0 all others = inifinity
    int shortestPaths[] = new int[vertexlist.length];   
    for (int i: shortestPaths){
        if (vertex==vertexlist[i]){
            shortestPaths[i]=0;
        }
        else{
            shortestPaths[i]=Integer.MAX_VALUE;
        }
    }
    

    for (int i=0; i<adjacencylist.length; i++) {
        if (adjacencylist[i][0]==vertex){

            seenVertexes[i]=adjacencylist[i][1];
            seenVertexIndex++;
        }
    }

return  shortestPaths;

}



}
