package DFS_Algorithm;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


public class Graph {
    LinkedList<Node>[] adjList;
    Node[] nodes;
    LinkedList<Edge> edges = new LinkedList<>();
    
    int time = 0;

//---------------------------------------------------------------------------
    //Constructor
    public Graph(String graphFile){
        loadGraph(graphFile);
    }

    //Read graph from file
    private void loadGraph(String graphFile){

        //Open input file and make sure the input file exists.
        File file = new File(graphFile);
        if (!file.exists()){
            System.out.println("The file " + graphFile + " is not found.");
            System.exit(0);
        }

        try {
            Scanner inputFile = new Scanner(file);
            String line;
            String[] adjNodes;

            //Initialize nodes and thier adjacents
            int totalNode = Integer.parseInt(inputFile.nextLine());
            nodes = new Node[totalNode];
            adjList = new LinkedList[totalNode];

            for(int i = 0; i < totalNode; i++){
                this.nodes[i] = new Node(i);
            }

            //Adjacents of each node
            int i = 0;
            while(inputFile.hasNext()){
                line = inputFile.nextLine();
                adjNodes = line.split(" ");

                adjList[i] = new LinkedList<>();
                for(int j = 0; j < adjNodes.length; j++){
                    adjList[i].add(nodes[Integer.parseInt(adjNodes[j])]);
                }   
                i++;
            }
        } catch (Exception e) {
            System.out.println("Exception!!!");
        }
    }

    //Build the DFS tree rooted at EVERY NODE in the graph
    public void DFS(){
        for(int i = 0; i < nodes.length; i++){
            if(nodes[i].color == "white"){
                DFS_Visit(i);
            }
        }
    }

    //Build the DFS tree rooted at a SELECTED NODE in the graph
    public void DFS_Visit(int u){
        nodes[u].color = "gray";
        time = time + 1;
        nodes[u].d = time;

        for (Node v : adjList[u]) {
            Edge discoveryEdge = new Edge(nodes[u], v);
            if(v.color == "white"){
                v.parent = nodes[u];
                if(check_Edge_AvailableOn_EdgeList(discoveryEdge) == false){
                    edges.add(discoveryEdge);
                    discoveryEdge.type = "Tree Edge";
                }
                DFS_Visit(v.ID);
            }
            else if(v.color == "gray"){
                if(check_Edge_AvailableOn_EdgeList(discoveryEdge) == false){
                    edges.add(discoveryEdge);
                    discoveryEdge.type = "Back Edge";
                }
            }
            else if(v.color == "black"){
                if(check_Edge_AvailableOn_EdgeList(discoveryEdge) == false){
                    edges.add(discoveryEdge);
                    if(nodes[u].isAncestorOf(v)){
                        discoveryEdge.type = "Forward Edge";
                    }
                    else{
                        discoveryEdge.type = "Cross Edge";
                    }
                }
            }
        }

        nodes[u].color = "black";
        time = time + 1;
        nodes[u].f = time;
    }

    //Check if an edge already existed in the edge list
    private boolean check_Edge_AvailableOn_EdgeList(Edge edge){
        for (Edge thisEdge : edges) {
            if(thisEdge.equal(edge)){
                return true;
            }
        }
        return false;
    }

    //Show current state of nodes
    public void DFS_showNodeState(){
        System.out.println("-------------------------------------------------------------------");
        System.out.printf("| %-7s | %-9s | %-14s | %-14s | %-7s |\n", "Node ID", 
                                                               "Parent ID",
                                                               "Discovery Time", 
                                                               "Finish Time", 
                                                               "Color");
        System.out.println("-------------------------------------------------------------------");
        for (Node u : nodes) {
            System.out.printf("| %-7d | %-9d | %-14d | %-14d | %-7s |\n", u.ID,
                                                                          u.parent.ID,
                                                                          u.d,
                                                                          u.f,
                                                                          u.color);
        }
        System.out.println("-------------------------------------------------------------------");
    }

    //Show current state of edges
    public void DFS_showEdges(){
        System.out.println("--------------------------------");
        System.out.printf("| %-10s | %-15s |\n", "Edge", 
                                                               "Type");
        System.out.println("--------------------------------");
        for (Edge edge : edges) {
            System.out.printf("| %-10s | %-15s |\n", edge, 
                                                            edge.type); 
        }
        System.out.println("--------------------------------");
    }
}



