package DFS_Algorithm;
import java.util.ArrayList;


public class Node{
    int ID;
    Node parent;
    String color = "white";
    int d;
    int f;

//---------------------------------------------------------------------------
    //Constructor
    public Node(int ID){
        this.ID = ID;
        this.parent = this;
        this.d = 0;
        this. f = 0;
    }

    //Check if a concerned node is an ancestor of somewhat node
    public boolean isAncestorOf(Node node){
        if(node != this){
            Node currentNode = node;
            Node parent = currentNode.parent;
            while(parent != currentNode){
                if(parent == this){
                    return true;
                }
                currentNode = parent;
                parent = currentNode.parent;
            }
            if(parent != this){
                return false;
            }
        }
        return true;
    }
}