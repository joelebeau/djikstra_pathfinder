/**
 * Joseph LeBeau
 * CS253
 * May 2012
 */

import java.util.*;
import java.awt.Color;

public class Node
{
   private int terrain;
   private int degree;
   private Color currColor;
   public ArrayList<Node> adjNodes;
   private int x_coord, y_coord;
   
   public Node()
   {
       terrain = 1;
       currColor = Color.green;
       degree = 0;
       adjNodes = new ArrayList<Node>();
       x_coord = y_coord = 0;
   }
   
   public void setDegree(int newDegree)
   {
      degree = newDegree;
   }
   
   public int getDegree()
   {
      return degree;
   }
   
   public int getX()
   {
      return x_coord;
   }
   
   public void setX(int new_x)
   {
      x_coord = new_x;
   }
   
   public int getY()
   {
      return y_coord;
   }
   
   public void setY(int new_y)
   {
      y_coord = new_y;
   }   
   
   public int getTerrain()
   {
      return terrain;
   }   
   
   public void setTerrain(int newTerrain)
   {
      terrain = newTerrain;
   }
   
   public Color getColor()
   {
      return currColor;
   }
   
   public void setColor(Color newColor)
   {
      currColor = newColor;
   }
}


