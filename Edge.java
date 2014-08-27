/**
 * Joseph LeBeau
 * CS253
 * May 2012
 */

public class Edge
{
   private Node source, dest;
   private int distance;
   private Edge next;
   
   public Edge(Node newSrc, Node newDest, int newDistance)
   {
      source = newSrc;
      dest = newDest;
      distance = newDistance;
      next = null;
   }
   public Edge getNext()
   {
      return next;
   }
   public void setNext(Edge newNext)
   {
      next = newNext;
   }
   public Node getSource()
   {
      return source;
   }
   public void setSource(Node newSource)
   {
      source = newSource;
   }
   public Node getDest()
   {
      return dest;
   }
   public int getDistance()
   {
      return distance;
   }
   public void setDistance(int newDistance)
   {
      distance = newDistance;
   }
}
