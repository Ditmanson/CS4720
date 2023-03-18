/**
 * Input: directed graph G = (V,E) in adjacency-list representation, a vertex s in V, a length greater than zero for each e in E
 * Postcondition: for every vertex v, the value len(v) equals the true shortest-path distance dist(s,v)
 * V = {s,t,v,w} E={1,2,3,4,5} LenE={1,2,3,4,5,6}
 * FromToE= {1(s,v), 2(v,w), 3(w,t), 4(s,w), 5(v,t)}
 */

import java.util.*;
public class dikstra {
    public static void main(String[] args){
        int numVerts =4;
        int numEdges=5;
        Graph dikstraGraph = new Graph(numEdges,numVerts);
        //public Vertex(char name){
            //dikstraGraph.addVertex('s');
        char vertexNames[]= {'s','t','v','w'};
        for (char name:vertexNames){
            Vertex vertex = new Vertex(name);
            dikstraGraph.addVertex(vertex);
        }
        int edgeWeight[]= {1,9,3,4,5};
        char fromV[]={'s','v','w','s','v'};
        char toV[]={'v','w','t','w','t'};
        for (int i=0; i<numEdges; i++){
            Edge edge = new Edge(fromV[i], toV[i],edgeWeight[i]);
            dikstraGraph.addEdge(edge);
        }
        System.out.println(dikstraGraph);
        dikstraGraph.dijkstrasPath('s');
        System.out.println(dikstraGraph);

    }

}
class Graph{
    public static final String noMoreSpace="No more space, create new graph";
    Vertex[] verticies;
    Edge[] edges; 
    Vertex[] verticesSeen;
    int maxVerts;
    int maxEdges;
    int edgeCount=0;
    int vertexCount=0;

    //graph constructor
    public Graph(int maxEdges, int maxVerts){
        verticies = new Vertex[maxVerts];
        edges = new Edge[maxEdges];
        verticesSeen= new Vertex [maxVerts];
        this.maxVerts=maxVerts;
        this.maxEdges=maxEdges;
    }
    
    public Vertex getVertex(char vertexName){
        for (int i=0; i<verticies.length;i++){
            if (verticies[i].getName() == vertexName){
                return verticies[i];
            }
        }        
        return null;
    }
    public int getVertexIndex(char vertexName){
        for (int i=0; i<verticies.length;i++){
            if (verticies[i].getName() == vertexName){
                return i;
            }
        }        
        return -1;
    }

    public void addVertex(Vertex newVertex){
        if (vertexCount<maxVerts){
            verticies[vertexCount++]=newVertex;
        }
        else{
            System.out.println(noMoreSpace);
        }
    }
    public void addEdge(Edge newEdge){
        if(edgeCount<maxEdges){
            edges[edgeCount++]=newEdge;
        }
        else{
            System.out.println(noMoreSpace);
        }
    }
    public void dijkstrasPath(char vertexName){
//set the stage
for(int i=0; i<verticies.length;i++){
    verticies[i].setLength(Integer.MAX_VALUE);
    verticies[i].setVisited(false);
}
        int distanceFromSource=0;
        int index = getVertexIndex(vertexName);
        verticies[index].setVisited(true);
        verticies[index].setLength(0);
        boolean allSeen=false;
        Arrays.sort(edges); // ensure the edges are in ascending order
        char name;
        //aglorithm time!!
        while (!allSeen){ //currently set as a boolean to escape because
            for (int i=0; i<edges.length; i++){ 
                Vertex fromVertex=getVertex(edges[i].getFromV());
                Vertex toVertex=getVertex(edges[i].getToV());
                if(fromVertex.isVisited()==true && toVertex.isVisited()==false){
                    distanceFromSource=fromVertex.getLength()+edges[i].getWeight();
                    name = edges[i].getToV();
                    index = getVertexIndex(name);
                    verticies[index].setLength(distanceFromSource);
                    verticies[index].setVisited(true);
                }
            }
            allSeen=true;
            for(int i=0; i<edges.length; i++){
                if(getVertex(edges[i].getFromV()).isVisited() && !getVertex(edges[i].getToV()).isVisited()){
                allSeen=false;
                }
            }
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices:\n");
        for (int i = 0; i < vertexCount; i++) {
            sb.append(verticies[i].toString()).append("\n");
        }
        sb.append("Edges:\n");
        for (int i = 0; i < edgeCount; i++) {
            sb.append(edges[i].toString()).append("\n");
        }
        return sb.toString();
    }
}
    class Vertex{
        public static final int infinity= Integer.MAX_VALUE;

        private char name;
        private boolean visited;
        private int length; //distance from source found with dijkstars method

        //constructor
        public Vertex(char name){
            this.name=name;
            visited=false;
            length=infinity;
        }

        //setters and getters
        public void setVisited(boolean setVisit){
            visited=setVisit;
        }

        public boolean isVisited(){
            return visited;
        }

        public void setLength(int Length){
            this.length=Length;
        }
        public int getLength(){
            return length;
        }
        public char getName(){
            return name;
        }

        //override toString
        @Override
        public String toString(){
            return String.format("Vertex %c: Path length %d: ",name,length);
        }


    }
    //edge class
    class Edge implements Comparable<Edge>{
        private char fromV;
        private char toV;
        private int weight;

        public Edge(char fromV, char toV, int weight){
            this.fromV=fromV;
            this.toV=toV;
            this.weight=weight;
        }
        //getters and setters
        public void setfromV(char from){
            fromV=from;
        }
        public char getFromV(){
            return fromV;
        }
        public void setToV(char to){
            toV=to;
        }
        public char getToV(){
            return toV;
        }
        public void setWeight(int weight){
            this.weight=weight;
        }
        public int getWeight(){
            return weight;
        }
        @Override
        public String toString(){
            return String.format("From %c to %c -- Cost:%d", fromV, toV,weight);
        }
        @Override
        public int compareTo(Edge edge){
            if(this.weight>edge.weight){
                return 1;
            }
            else if(this.weight<edge.weight){
                return -1;
            }
            else return 0;

        }


    }



