package DFS_Algorithm;


public class Edge {
    Node endPoint1, endPoint2;
    String type;
    
//---------------------------------------------------------------------------
    //Constructor
    public Edge(Node node1, Node node2){
        this.endPoint1 = node1;
        this.endPoint2 = node2;
    }
    
    public boolean equal(Edge edge){
        if(this.endPoint1 == edge.endPoint1 && this.endPoint2 == edge.endPoint2 || 
           this.endPoint2 == edge.endPoint1 && this.endPoint1 == edge.endPoint2){
            return true;
           }
        return false;
    }

    public String toString(){
        String str = "(" + endPoint1.ID + ", " + endPoint2.ID + ")";
        return str;
    }
}
