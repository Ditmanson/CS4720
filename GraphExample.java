
//package graphexample;

/**
 * @author abrouill
 */
public class GraphExample {

    public static void main(String[] args) {
        int index;
        // Graph with 4 Verticesw, 5 Edges created
        GraphClass myGraph = new GraphClass(4, 5);
        //Add Vertex 'A' and set it as Visited, length = 0
		index = myGraph.AddVertex('A');
        myGraph.GetVertex('A').setLength(0);
        myGraph.GetVertex('A').markVisited();
        //Add more Vertices      
        index = myGraph.AddVertex('B');
        index = myGraph.AddVertex('C');
        index = myGraph.AddVertex('D');
		//Add some edges
        index = myGraph.AddEdge('A', 'B', 5);
        index = myGraph.AddEdge('A', 'C', 1);
        index = myGraph.AddEdge('C', 'B', 2);
        index = myGraph.AddEdge('D', 'C', 4);
        index = myGraph.AddEdge('A', 'D', 2);
        //Print graph representation contents    
        myGraph.printVertices();
        myGraph.printEdges();
    }
}
