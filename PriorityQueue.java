/**
 * Joseph LeBeau
 * CS253
 * May 2012
 */
public class PriorityQueue
{
   private int size;
   private Edge headEdge, tailEdge;
   
   public PriorityQueue()
   {
      size = 0;
      
      headEdge = new Edge(null, null, -1);
      tailEdge = new Edge(null, null, 100000);
      headEdge.setNext(tailEdge);
   }
   
   public void enqueue(Edge newEdge)
   {
      if(this.empty()) 
      {
         headEdge.setNext(newEdge);
         newEdge.setNext(tailEdge);
      }
      else
      {
         Edge currEdge = headEdge.getNext();
         Edge prevEdge = headEdge;
         
         while(newEdge.getDistance() >= currEdge.getDistance() && !(currEdge.equals(tailEdge)))
         {
            if(currEdge.getSource().equals(newEdge.getSource()) && currEdge.getDest().equals(newEdge.getDest()))
               return;
            prevEdge = currEdge;
            currEdge = currEdge.getNext();
         }
         prevEdge.setNext(newEdge);
         newEdge.setNext(currEdge);
      }  
      size++;
   }
   
   public Edge dequeue()
   {
      if(this.empty())
      {
         return null;
      }
      Edge dequeueEdge;
      dequeueEdge = headEdge.getNext();
      headEdge.setNext(dequeueEdge.getNext());
      size--;
      
      return dequeueEdge;
   }
   
   public int size()
   {
      return size;
   }
   
   public boolean empty()
   {
      if(headEdge.getNext().equals(tailEdge))
         return true;
      else
         return false;
   }
   
   public Edge front()
   {
      return headEdge.getNext();
   }
}