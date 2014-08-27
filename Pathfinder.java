/**
 * Joseph LeBeau
 * CS253
 * May 2012
 */

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Pathfinder
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame("Pathfinder");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      DisplayBoard screen = new DisplayBoard();
      
      frame.setLayout(new BorderLayout());      
      frame.getContentPane().add(screen);
      
      frame.pack();
      frame.setSize(718,640);
      frame.setVisible(true);
   }
}