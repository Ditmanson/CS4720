/*
Author BROUILLETTE 3/15/2023
Example of vertex and adjacency (edge) list implementation,
not the only possible way to do this in Java,
but an effective way.
ASSUMPTIONS:
1. Vertices have CAPITAL letter names which we use to find the correct
    index via their ASCII value.  A vertex is referenced using its name.
2. Vertices are named with sequential letters staring with 'A', and the number
    of vertices and edges must be known and unchangeable when the graph is 
    created.
3. All the vertices allocated are initialized via AddVertex before using the 
    graph
4. Any graph searches are NOT contained here, but implemented outside this
    class.
*/
//package graphexample;

/**
 * @author abrouill
 */
public class GraphClass {
// Constant simulating INFINITY
    public static final int MaxDistance = 32000;

    Vertex[] vertices;          //List of vertices
    Edge[] edges;               //List of edges (adjacency list)
    int edgeCount;              //Number of edges filled so far
    int maxEdge, maxVert;       //Number of allocated vertices and edges  

    /* Vertex and Edge are Java inner classes. defined here for convenience

//  Vertices  and Edges are created  and stored in the lists using AddVertex
//    and AddEdge
     */
    public class Vertex {

        private char name;         // assume just a letter is needed
        private boolean visited;   // true if has been seen
        private int length;        // SSSP distance from source node

        public Vertex(char nameLetter) {
            name = nameLetter;
            visited = false;  // assume not yet visited
            length = MaxDistance;   // assume LARGE, simulate infinity
        }
// Setters/getters

        public void markVisited() {
            visited = true;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setLength(int newLength) {
            length = newLength;
        }

        public int getLength() {
            return length;
        }
        // name is set by constructor, never channged
        public char getName() {
            return name;
        }
//Printable formatted string of Vertex data
        public String print() {
            String result = "" + name;
            if (visited) {
                result = result + "* ";
            } else {
                result = result + "  ";
            }
            result = result + "Path length = " + length;
            return result;
        }
    }


//Stores the endpoints and weight for each edge
    public class Edge {

        char fromV, toV;  //Stored as Vertex name letters
        int weight;

        public Edge(char fromname, char toname, int edgeweight) {
            fromV = fromname;
            toV = toname;
            weight = edgeweight;
        }

//Printable formatted string of Edge data
        public String print() {
            String result = "" + fromV + " -> " + toV + " Cost = " + Integer.toString(weight);
            return result;
        }
    }

    public GraphClass(int numVerts, int numEdges) {
        vertices = new Vertex[numVerts];
        edges = new Edge[numEdges];
        maxVert = numVerts;  // valid array indices are 0..numverts -1
        maxEdge = numEdges;
        edgeCount = 0;

    }
// Converts the capital letter name to an integer index for direct access

    public int AddVertex(char name) {
        int result = -1;  //default
        int index = (int) name - (int) 'A';
        if ((index < (maxVert)) && (index >= 0)) {    // if room available
            vertices[index] = new Vertex(name);
            result = index;
        }
        return result;
    }

//Returns vertex by name if present, else null
    public Vertex GetVertex(char vertexname) {
        Vertex result = null; //default
        int index = (int) vertexname - (int) 'A';
        if ((index < (maxVert)) && (index >= 0)) {    // if valid
            result = vertices[index];
        }
        return result;
    }

//Returns vertex by index if present, else null
    public Vertex GetVertex(int vertexindex) {
        Vertex result = null; //default
        if ((vertexindex < (maxVert)) && (vertexindex >= 0)) {    // if valid
            result = vertices[vertexindex];
        }
        return result;
    }


// Adds an edge using vertex names and edge weight
    public int AddEdge(char fromname, char toname, int edgeweight) {
        int result = -1;  //default
        //int index = ;
        if (edgeCount < (maxEdge)) {    // if room available
            edges[edgeCount] = new Edge(fromname, toname, edgeweight);
            result = edgeCount;
            edgeCount++;
        }
        return result;
    }
// Number of defined Edges in the list now for loop limits    
    public int EdgeCount() {
        return edgeCount;
    }
// Get an Edge by index
    public Edge GetEdge(int index) {
        Edge result = null; //default
        if ((index < (maxEdge)) && (index >= 0)) {    // if valid
            result = edges[index];
        }
        return result;
    }

    public void printVertices() {
        int i; //assumes all slots allocated have been used
        for (i = 0; i < maxVert; i++) {
            System.out.print(Integer.toString(i) + ": ");
            if (vertices[i] != null) {
                System.out.println(vertices[i].print());
            } else {
                System.out.println("Vertex not initialized.");
            }
        }

    }

    public void printEdges() {
        int i;
        for (i = 0; i < edgeCount; i++) {
            System.out.print(Integer.toString(i) + ": ");
            System.out.println(edges[i].print());
        }
    }
}
