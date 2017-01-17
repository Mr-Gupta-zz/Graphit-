import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
/**
 * GraphitController is a controller for a graphing calc.
 */
public class GraphitController extends JPanel  {

  public GraphitController(){
    this.addMouseMotionListener(new MouseHandler());
    this.addMouseListener(new MouseHandler());
  }
  
  private static final int CANVAS_WIDTH = 600;
  private static final int CANVAS_HEIGHT = 600;
  private int newCanvasWidth = CANVAS_WIDTH;
  private int oldCanvasWidth = CANVAS_WIDTH;

  private static GraphitController gc;

  public static void main(String[] args){

    SwingUtilities.invokeLater(new Runnable() {
        public void run() {

        createAndShowGUI();
   

System.out.println("4");
        }
        });

  }

  public static void createAndShowGUI(){
  System.out.println("1");
    JFrame mainFrame = new JFrame("Graphit");
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
    mainFrame.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
    
  System.out.println("2");
    //setBorder(BorderFactory.createLineBorder(Color.black));
  
    gc = new GraphitController();
     mainFrame.add(gc);

     mainFrame.pack();
    mainFrame.setVisible(true);
System.out.println("3");
 


  }
  public void paintComponent(Graphics g){
    


    super.paintComponent(g);

    newCanvasWidth = gc.getWidth();

    Graphics2D g2 =(Graphics2D) g;

    //thick width
    g2.setStroke(new BasicStroke(2));

    //draw the y-coordinate axis
    g2.drawLine(CANVAS_WIDTH/2, 0, CANVAS_WIDTH/2, gc.getHeight());

    //draw the x-coordinate axis
    g2.drawLine(0, CANVAS_HEIGHT/2, gc.getWidth(), CANVAS_HEIGHT/2);
    
    g2.setStroke(new BasicStroke(1));
    int s = 20;
    for(int i = 0; i < CANVAS_WIDTH; i++, s+=20){

      g2.setColor(Color.LIGHT_GRAY);
      g2.drawLine(s, 0, s,gc.getHeight());
      //g2.setForeground(Color.GREEN);      
       g2.drawLine(0, s, gc.getWidth(), s); 
    }

    g.drawString("AMRI AND ISAAC", 10, 20); 

  }

  public Dimension getPreferredSize(){
    return new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT);
  }


  private static class MouseHandler implements MouseMotionListener, MouseListener{

    public void mouseDragged(MouseEvent evt){

     System.out.println("DRAGGED");

    }

    public void mouseMoved(MouseEvent evt){


    }
    public void mouseClicked(MouseEvent evt){


    }

    public void mouseEntered(MouseEvent evt){


    }

    public void mouseReleased(MouseEvent evt){
      

    }

    public void mousePressed(MouseEvent evt){
    System.out.println("PRESSED");

    }

    public void mouseExited(MouseEvent evt){
       System.out.println(evt.getX() <= 0);
        
       }



      //oldCanvasWidth = 
    
    
    
     
  
  }

 
}

