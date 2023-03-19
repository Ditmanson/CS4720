/**
 * Input: directed graph G = (V,E) in adjacency-list representation, a vertex s in V, a length greater than zero for each e in E
 * Postcondition: for every vertex v, the value len(v) equals the true shortest-path distance dist(s,v)
 * V = {s,t,v,w} E={1,2,3,4,5} LenE={1,2,3,4,5,6}
 * FromToE= {1(s,v), 2(v,w), 3(w,t), 4(s,w), 5(v,t)}
 */

import java.util.*;
public class dikstra {
    public static void main(String[] args){
        int numVerts =4; //needed for constructor
        int numEdges=5; // needed for constructor
        Graph dikstraGraph = new Graph(numEdges,numVerts); //declare and initialize dikstra
        char vertexNames[]= {'s','t','v','w'}; // array of vertex names
        for (char name:vertexNames){ // declare and initialize vertecies 
            Vertex vertex = new Vertex(name);
            dikstraGraph.addVertex(vertex); // add vertex to graph
        }
        int edgeWeight[]= {1,2,3,4,5}; // array of edge weights
        char fromV[]={'s','v','w','s','v'}; // array of from destiantion
        char toV[]={'v','w','t','w','t'};
        for (int i=0; i<numEdges; i++){ // declare and initalize edges
            Edge edge = new Edge(fromV[i], toV[i],edgeWeight[i]);
            dikstraGraph.addEdge(edge); // add edges to graph
        }
        System.out.println(dikstraGraph); // print graph prior to algorithm
        dikstraGraph.dijkstrasPath('s'); // apply the algorithm
        System.out.println(dikstraGraph); // print out graph after algorithm

    }

}
class Graph{
    public static final String noMoreSpace="No more space, create new graph";
    Vertex[] verticies; // array of vert
    Edge[] edges; 
    //Vertex[] verticesSeen; // removed, used booleans instead. Change came from al's data structure showing that was ok.
    int maxVerts;
    int maxEdges;
    int edgeCount=0; // used instead of edges.length so the graph can be made to grow without slowing down speed
    int vertexCount=0; // used instead of verticies.length so the graph can be made to grow without slowing down speed

    //graph constructor
    public Graph(int maxEdges, int maxVerts){
        verticies = new Vertex[maxVerts];
        edges = new Edge[maxEdges];
        //verticesSeen= new Vertex [maxVerts]; // removed and replaced with booleans
        this.maxVerts=maxVerts;
        this.maxEdges=maxEdges;
    }
    // getters and setters
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
// adders. is that a word...
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
    verticies[i].setLength(Integer.MAX_VALUE); // Integer.MAX_VALUE used for inifinity
    verticies[i].setVisited(false); // set all booleans to false
}
        int distanceFromSource=0; // initialize source distance to zero
        int index = getVertexIndex(vertexName); // getvertex index is needed for modifying arrays inside the object
        verticies[index].setVisited(true); // set soure vertex to visited
        verticies[index].setLength(distanceFromSource); // set source vertex length to zero
        boolean allSeen=false; // while loop exit boolean.
        Arrays.sort(edges); // ensure the edges are in ascending order
        char name; // declare a variable name as a char
        //aglorithm time!!
        while (!allSeen){ //when all from verticies possible to travel to from the source have been visited. then exit while loop
            for (int i=0; i<edges.length; i++){ 
                Vertex fromVertex=getVertex(edges[i].getFromV()); // get the from vertex in a variable
                Vertex toVertex=getVertex(edges[i].getToV()); // get the to vertex in a variable
                if(fromVertex.isVisited()==true && toVertex.isVisited()==false){  // only execute next line if we are at the from vertex and need to travel to the to vertex
                    distanceFromSource=fromVertex.getLength()+edges[i].getWeight(); // calculate distance
                    name = edges[i].getToV(); // get the name of the to edge
                    index = getVertexIndex(name); // get the index so we can update the graph
                    verticies[index].setLength(distanceFromSource); // update graph distance
                    verticies[index].setVisited(true); // update graph visited booleans
                }
            }
            allSeen=true; // set boolean to true and then check if we are ready to exit
            for(int i=0; i<edges.length; i++){ 
                if(getVertex(edges[i].getFromV()).isVisited() && !getVertex(edges[i].getToV()).isVisited()){
                allSeen=false; // if there's more graph to traverse then set boolean to false and repeat
                }
            }
        }
    }


    @Override
    public String toString() { // used string builder for toString to get the entire graph
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
        public String toString(){// create toString for the a vertex
            return String.format("Vertex %c: Path length %d: ",name,length);
        }


    }
    //edge class
    class Edge implements Comparable<Edge>{ // edges must use comparable interface so they can be sorted prior to entering.
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
        @Override // override to string to something pretty
        public String toString(){
            return String.format("From %c to %c -- Cost:%d", fromV, toV,weight);
        }
        @Override
        public int compareTo(Edge edge){ // used to put edges in ascending order based weights
            if(this.weight>edge.weight){
                return 1;
            }
            else if(this.weight<edge.weight){
                return -1;
            }
            else return 0;

        }


    }



