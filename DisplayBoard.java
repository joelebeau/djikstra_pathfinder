/**
 * Joseph LeBeau
 * CS253
 * May 2012
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

@SuppressWarnings("serial")
public class DisplayBoard extends JPanel implements MouseListener
{
   JPanel buttonPanel;
   JLabel authorLabel;
   ButtonListener buttonPress;
   Node selectedNode;
   Node targetNode;
   JButton fullRun, quickRun, clear, instaMap, genMap;
   JRadioButton makeAccess, makeBlock, makeTerrain, makeTarget, selectNode;
   ButtonGroup drawToggle;
   int x_click = 0, y_click = 0;
   
   Map gameMap;
   Node mapArray[][];
   final int ROWS = 20;
   final int COLS = 20;
   
   public DisplayBoard() 
   {  
      addMouseListener(this);
      gameMap = new Map();
      mapArray = new Node[COLS][ROWS];
      
      for(int i = 0; i < COLS; i++)
      {
         for(int j = 0; j < ROWS; j++)
         {
            mapArray[i][j] = new Node();
            mapArray[i][j].setX(i);
            mapArray[i][j].setY(j);
         }
      }
      
      for(int i = 0; i < COLS; i++)
      {
         for(int j = 0; j < ROWS; j++)
         {
            if(i-1 >= 0)
               gameMap.addEdge(mapArray[i][j], mapArray[i-1][j]);
            if(i+1 <= COLS-1)
               gameMap.addEdge(mapArray[i][j], mapArray[i+1][j]);
            if(j-1 >= 0)
               gameMap.addEdge(mapArray[i][j], mapArray[i][j-1]);
            if(j+1 <= ROWS-1)
               gameMap.addEdge(mapArray[i][j], mapArray[i][j+1]);
         }
      }
      genRandomMap();
      selectedNode = mapArray[0][0];
      targetNode = mapArray[COLS-1][ROWS-1];
      drawToggle = new ButtonGroup();
      authorLabel = new JLabel("<html>Developed by<br>Joseph LeBeau</html>");
      buttonPress = new ButtonListener();
      fullRun = new JButton("Full Run");
      fullRun.addActionListener(buttonPress);
      quickRun = new JButton("Quick Run");
      quickRun.addActionListener(buttonPress);
      genMap = new JButton("Gen. Map");
      genMap.addActionListener(buttonPress);
      clear = new JButton("Clear");
      clear.addActionListener(buttonPress);
      buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
      drawToggle = new ButtonGroup();
      selectNode = new JRadioButton("Set Source");
      makeTarget = new JRadioButton("Set Target");
      makeAccess = new JRadioButton("Set Grass");
      makeTerrain = new JRadioButton("Set Terrain");
      makeBlock = new JRadioButton("Set Wall");
      
      setLayout(new BorderLayout());
      setBorder(BorderFactory.createLineBorder(Color.black));
      
      drawToggle.add(selectNode);
      drawToggle.add(makeTarget);
      drawToggle.add(makeAccess);
      drawToggle.add(makeBlock);
      drawToggle.add(makeTerrain);  
      buttonPanel.add(selectNode);
      buttonPanel.add(makeTarget);
      buttonPanel.add(makeAccess);
      buttonPanel.add(makeBlock);
      buttonPanel.add(makeTerrain);
      buttonPanel.add(fullRun);
      buttonPanel.add(quickRun);
      buttonPanel.add(genMap);
      buttonPanel.add(clear);
      buttonPanel.add(authorLabel);
      buttonPanel.setPreferredSize(new Dimension(100,300));
      buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black));
      selectNode.setSelected(true);
      add(buttonPanel, BorderLayout.WEST);       
   }
   public void paintComponent(Graphics g)
   {
      int row, col;
      int x_start = 100;;
      
      for(col = 0; col < COLS; col++)
      {
         for(row = 0; row < ROWS; row++)
         {
            g.setColor(mapArray[col][row].getColor());
            g.fillRect(col*30+x_start, row*30, 30, 30);
            if(mapArray[col][row].equals(selectedNode))
            {
               g.setColor(Color.cyan);
               g.fillOval(col*30+x_start+7, row*30+7, 15, 15);
               g.setColor(Color.black);
               g.drawOval(col*30+x_start+7, row*30+7, 15, 15);
            }
            if(mapArray[col][row].equals(targetNode))
            {
               g.setColor(Color.red);
               g.fillOval(col*30+x_start+7, row*30+7, 15, 15);
               g.setColor(Color.black);
               g.drawOval(col*30+x_start+7, row*30+7, 15, 15);
            }
            g.setColor(Color.black);
            g.drawRect(col*30+x_start, row*30, 30,30);
            
         }
      }
   }   
   
   public void mouseClicked(MouseEvent mouse)
   {      
      int row, col;
      
      col = (mouse.getX()-100)/30;
      row = mouse.getY()/30;
      
      if(col > COLS || col < 0)
         return;
      if(row > ROWS || row < 0)
         return;
      
      if(selectNode.isSelected())
      {
         selectedNode = mapArray[col][row];
      }
      else if(makeTarget.isSelected())
      {
         targetNode = mapArray[col][row];
      }
      else if(makeAccess.isSelected())
      {
         if(!gameMap.nodes.contains(mapArray[col][row]))
         {
            if(col-1 >= 0 && gameMap.nodes.contains(mapArray[col-1][row]))
            {
               gameMap.addEdge(mapArray[col][row], mapArray[col-1][row]);
            }
            if(col+1 <= COLS-1 && gameMap.nodes.contains(mapArray[col+1][row]))
            {
               gameMap.addEdge(mapArray[col][row], mapArray[col+1][row]);
            }
            if(row-1 >= 0 && gameMap.nodes.contains(mapArray[col][row-1]))
            {
               gameMap.addEdge(mapArray[col][row], mapArray[col][row-1]);
            }
            if(row+1 <= ROWS-1 && gameMap.nodes.contains(mapArray[col][row+1]))
            {
               gameMap.addEdge(mapArray[col][row], mapArray[col][row+1]);
            }
         }      
         mapArray[col][row].setColor(Color.green);
         mapArray[col][row].setTerrain(1);
         gameMap.updateEdges(mapArray[col][row]);
      }
      else if(makeTerrain.isSelected())
      {
         if(!gameMap.nodes.contains(mapArray[col][row]))
         {
            if(col-1 >= 0 && gameMap.nodes.contains(mapArray[col-1][row]))
            {
               gameMap.addEdge(mapArray[col][row], mapArray[col-1][row]);
            }
            if(col+1 <= COLS-1 && gameMap.nodes.contains(mapArray[col+1][row]))
            {
               gameMap.addEdge(mapArray[col][row], mapArray[col+1][row]);
            }
            if(row-1 >= 0 && gameMap.nodes.contains(mapArray[col][row-1]))
            {
               gameMap.addEdge(mapArray[col][row], mapArray[col][row-1]);
            }
            if(row+1 <= ROWS-1 && gameMap.nodes.contains(mapArray[col][row+1]))
            {
               gameMap.addEdge(mapArray[col][row], mapArray[col][row+1]);
            }
         }
         mapArray[col][row].setColor(new Color(0xA0522D));
         mapArray[col][row].setTerrain(5);                     
         gameMap.updateEdges(mapArray[col][row]);
      }
      else if(makeBlock.isSelected())
      {
         if(gameMap.nodes.contains(mapArray[col][row]))
         {
            while(!mapArray[col][row].adjNodes.isEmpty())
            {
               gameMap.removeEdge(mapArray[col][row].adjNodes.get(0), mapArray[col][row]);
               gameMap.nodes.remove(mapArray[col][row]);
            }
            mapArray[col][row].setTerrain(10);
         }
         mapArray[col][row].setColor(Color.DARK_GRAY);
      }
      repaint();
   }
   
   public void genRandomMap()
   {
      Random rand = new Random();
      int newNode;
      
      for(int i = 0; i < COLS; i++)
      {
         for(int j = 0; j < ROWS; j++)
         {
            newNode = rand.nextInt(4);
            if(newNode < 3)
            {
               mapArray[i][j].setColor(Color.green);
               mapArray[i][j].setTerrain(1);
               gameMap.updateEdges(mapArray[i][j]);                     
            }
            if(newNode == 3)
            {
               mapArray[i][j].setColor(new Color(0xA0522D));
               mapArray[i][j].setTerrain(5);
               gameMap.updateEdges(mapArray[i][j]);                     
            }
         }
      }
   }
   
   public void clearMap()
   {
      for(int i = 0; i < COLS;i++)
      {
         for(int j = 0; j < ROWS; j++)
         {
            if(mapArray[i][j].getTerrain() == 1)
               mapArray[i][j].setColor(Color.green);
            else if(mapArray[i][j].getTerrain() > 1 && mapArray[i][j].getTerrain() < 10)
               mapArray[i][j].setColor(new Color(0xA0522D));
            else
               mapArray[i][j].setColor(Color.DARK_GRAY);
         }
      }
      repaint();
   }
   public void mouseEntered(MouseEvent mouse)
   {      
   }
   public void mouseExited(MouseEvent mouse)
   {
   }
   public void mousePressed(MouseEvent mouse)
   {      
   }
   public void mouseReleased(MouseEvent mouse)
   {      
   }
   private class DijkstraThread extends Thread
   {
      Map gameMap;
      Node[][] mapArray;
      Node start;
      Node target;
      boolean drawDelay;
      boolean finished;
      
      public DijkstraThread(Map gameMap, Node[][] mapArray, Node start, Node target, boolean drawDelay)
      {
         finished = false;
         this.gameMap = gameMap;
         this.mapArray = mapArray;
         this.start = start;
         this.target = target;
         this.drawDelay = drawDelay;
      }
      
      public void run()
      {
         int distArray[][] = new int[COLS][ROWS];
         boolean included[][] = new boolean[COLS][ROWS];
         Node[][] path = new Node[COLS][ROWS];
         PriorityQueue edgeQueue = new PriorityQueue();

         Node currFront;
         Edge currEdge;
         while(!finished)
         {
            for(int i = 0; i < COLS; i++)
            {
               for(int j = 0; j < ROWS; j++)
               {
                  if(gameMap.edge(start, mapArray[i][j]))
                  {
                     distArray[i][j] = gameMap.edgeWeight(start, mapArray[i][j]);
                     path[i][j] = start;
                     edgeQueue.enqueue(new Edge(start, mapArray[i][j], mapArray[i][j].getTerrain()));
                  }
                  
                  else
                  {
                     distArray[i][j] = 100000;
                     included[i][j] = false;
                  }
               }
            }
            
            distArray[start.getX()][start.getY()] = 0;
            included[start.getX()][start.getY()] = true;
            
            while(included[target.getX()][target.getY()] == false)
            {
               currEdge = edgeQueue.dequeue(); 
               currFront = currEdge.getDest();
               
               included[currFront.getX()][currFront.getY()] = true;

               if(drawDelay)
               {
                  currFront.setColor(Color.yellow);
                  repaint();
                  try
                  {
                     sleep(10);
                  }
                  catch(InterruptedException e)
                  {
                     
                  }
               }
               for(int i = 0; i < currFront.adjNodes.size(); i++)
               {
                  Node check = currFront.adjNodes.get(i);
                  if((distArray[currFront.getX()][currFront.getY()] + gameMap.edgeWeight(currFront, mapArray[check.getX()][check.getY()])) < distArray[check.getX()][check.getY()])
                  {
                     distArray[check.getX()][check.getY()] = (distArray[currFront.getX()][currFront.getY()] + gameMap.edgeWeight(currFront, check));
                     path[check.getX()][check.getY()] = currFront;
                  }
                  if(included[check.getX()][check.getY()] == false)
                  {
                     edgeQueue.enqueue(new Edge(start, check, distArray[check.getX()][check.getY()]));
                  }
                     
               }
            }
            Node checkNode = path[target.getX()][target.getY()];
            mapArray[target.getX()][target.getY()].setColor(Color.blue);
            while(checkNode != null)
            {
               checkNode.setColor(Color.blue);
               checkNode = path[checkNode.getX()][checkNode.getY()];
               repaint();
            }
            finished = true;
         }
      }
   }
   
   private class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent evt)
      {
         if(evt.getSource().equals(fullRun))
         {
            clearMap();
            DijkstraThread dThread = new DijkstraThread(gameMap, mapArray, selectedNode, targetNode, true);
            
            dThread.start();
         }
         else if(evt.getSource().equals(quickRun))
         {
            clearMap();
            DijkstraThread dThread = new DijkstraThread(gameMap, mapArray, selectedNode, targetNode, false);
            dThread.start();
         }
         else if(evt.getSource().equals(clear))
         {
            clearMap();
         }
         else if(evt.getSource().equals(genMap))
         {
            genRandomMap();
            repaint();
         }
      }
   }
}

