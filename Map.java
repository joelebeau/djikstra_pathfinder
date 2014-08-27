/**
 * Joseph LeBeau
 * CS253
 * May 2012
 */

import java.util.*;

public class Map implements Graph
{
   public List<Edge> edges;
   public List<Node> nodes;
   private int numEdges;
   private int numVertices;
   
   public Map()
   {
      edges = new ArrayList<Edge>();
      nodes = new ArrayList<Node>();
      numEdges = 0;
      numVertices = 0;
   }
   
   
   public void addEdge(Node v1, Node v2)
   {
      if(edge(v1, v2))
         return;
      if(edge(v2, v1))
         return;
      v1.adjNodes.add(v2);
      v2.adjNodes.add(v1);
      edges.add(new Edge(v1, v2, v2.getTerrain()));
      edges.add(new Edge(v2, v1, v1.getTerrain()));
      
      numEdges += 2;
      if(!nodes.contains(v1))
      {
         nodes.add(v1);
         numVertices++;
      }
      if(!nodes.contains(v2))
      {
         nodes.add(v2);
         numVertices++;
      }
   }
   
   public void removeEdge(Node v1, Node v2)
   {
      if(!edge(v1, v2))
      {
         return;
      }
      v1.adjNodes.remove(v2);
      v2.adjNodes.remove(v1);

      numEdges -= 2;
      for(int i = 0; i < numEdges;i++)
      {
         if(edges.get(i).getSource().equals(v1) && edges.get(i).getDest().equals(v2))
            edges.remove(i);
         if(edges.get(i).getSource().equals(v2) && edges.get(i).getDest().equals(v1))
            edges.remove(i);
      }
      
      if(v1.adjNodes.isEmpty())
      {
         numVertices--;
      }
      if(v2.adjNodes.isEmpty())
      {
         numVertices--;
      }
   }
   

   public boolean edge(Node v1, Node v2)
   {
      if(v1.adjNodes.contains(v2))
         return true;
      else
         return false;
   }


   public int numVertices()
   {
      return numVertices;
   }

   public int numEdges()
   {
      return numEdges;
   }

   public int degree(Node v)
   {
      return v.adjNodes.size();
   }
   
   public int edgeWeight(Node source, Node dest)
   {
      if(!edge(source, dest))
         return 100000000;
      else
         return dest.getTerrain();
   }
   
   
   public void updateEdges(Node v)
   {
      for(int i = 0; i < degree(v); i++)
      {
         Node curr = v.adjNodes.get(i);
         removeEdge(curr, v);
         addEdge(curr, v);
      }
   }
}
