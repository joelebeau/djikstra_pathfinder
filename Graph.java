/**
 * Joseph LeBeau
 * CS253
 * May 2012
 */

public interface Graph
{
   void addEdge(Node v1, Node v2);       // Returns G with new edge v1v2 added
   void removeEdge(Node v1, Node v2);    // Returns G with edge v1v2 removed
   boolean edge(Node v1, Node v2);       // Returns true if there is an edge between v1 and v2
   int numVertices();                    // Returns the number of vertices in G
   int numEdges();                       // Returns the number of edges in G
   int degree(Node v);                   // Returns the degree of v
}
