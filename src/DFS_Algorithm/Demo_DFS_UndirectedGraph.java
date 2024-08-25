package DFS_Algorithm;

public class Demo_DFS_UndirectedGraph {
    public static void main(String[] args) {
        //Initialize the graph
        Graph g = new Graph("src/UndirectedGraph.txt");

        //Run DFS algorithm
        g.DFS();

        //Show state of nodes after running DFS algorithm
        System.out.println("The following is the state of NODES" +
                                   " after running DFS algorithm:");
        g.DFS_showNodeState();

        //Show state of nodes after running DFS algorithm
        System.out.println("\n\nThe following is the state of EDGES" +
                                   " after running DFS algorithm:");
        g.DFS_showEdges();
    }   
}
