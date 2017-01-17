import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
/**
 * GraphitController is a controller for a graphing calc.
 */
public class NGraphitController extends JPanel implements ActionListener {

  public NGraphitController(){
    this.addMouseMotionListener(new MouseHandler());
    this.addMouseListener(new MouseHandler());

    graphBtn = new JButton("Graphit");
    resetBtn = new JButton("Resetit");

    this.add(graphBtn);
    this.add(resetBtn);

    graphBtn.addActionListener(this);
    resetBtn.addActionListener(this);
  }

  private static final int CANVAS_WIDTH = 600;
  private static final int CANVAS_HEIGHT = 600;
  private static final int W = 600;
  private static final int H = 600;
  private int newCanvasWidth = CANVAS_WIDTH;
  private int oldCanvasWidth = CANVAS_WIDTH;
  private static final Point pp = new Point();
  private static NGraphitController gc;
  private static int startX;
  private static int startY;
  private static int x=0, y=0;
  private static Point p;
  private Point origin = new Point(W / 2, H / 2);
  private Point mousePt;
  private static JViewport vp;
  private static boolean value;
  private static int counter_X = 0;
  private static int counter_Y = 0;
  private static int old;
  private static int neww;
  private static boolean isPolynomial = false;

  private static Graphics2D g2;

  private int j;
  private int i;
  private int oldW;
  private int oldH;
  private int hor, ver;

  private JButton graphBtn;
  private JButton resetBtn;
  private JButton enter;
  private JFrame functionFrame;
  private JPanel functionPanel;
  private JTextField[] coeffs; 

  private JButton polynomialBtn;
  private JTextField degreeField;
  private int degrees;
  private int[] coEffs;
  boolean doneCoeffs = false;
  boolean isCoeffs =false;
  private int[] test; 
  private int lineSpacing = 40; 
  private Plot[] plots;
  private Plotter plotter;
  private Plot[] functions;



  public static void main(String[] args){

    SwingUtilities.invokeLater(new Runnable() {
        public void run() {

        createAndShowGUI();


        }
        });

  }

  /**
   * To create the Gui components
   */
  public static void createAndShowGUI(){

    //the main graph frame
    JFrame mainFrame = new JFrame("Graphit");
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);

    gc = new NGraphitController();

    p = new Point(0,0);

    // Viewport is to man through the graph.
    vp = new JViewport();
    vp.setSize(100, 100);
    vp.setView(gc);
    mainFrame.add(vp);

    mainFrame.pack();
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setVisible(true);

  }

  public void actionPerformed(ActionEvent evt) {
    String testInput;
    boolean isReady = false;

    if(evt.getActionCommand() == "Graphit") {

      functionFrame = new JFrame("function frame");
      functionPanel = new JPanel();
      functionFrame.setSize(300, 150);
      functionFrame.add(functionPanel);
      checkFunctionTypeCases();
      functionFrame.setVisible(true);

    }

    else if(evt.getActionCommand() == "Polynomial") {

      isPolynomial = true;
      //System.out.println("calling polynomial btn");
      degreeField = new JTextField ("degrees(0-6)");
      enter = new JButton("Enter");

      functionPanel.remove(polynomialBtn);
      functionPanel.add(degreeField);
      functionPanel.add(enter);
      functionPanel.revalidate();
      functionPanel.repaint();
      enter.addActionListener(this);
      degreeField.addKeyListener(new KeyHandler());

      degreeField.addMouseListener(new MouseHandler() {

          public void mouseClicked(MouseEvent e) {
          degreeField.setText(null);
          }

          });

    }

    else if(evt.getActionCommand() == "Enter") {
      System.out.println("Enter");
      System.out.println("--ENTER COMMAND--");
      System.out.println(isPolynomial + " isPolynomial");
      System.out.println(doneCoeffs + " doneCoeffs");
      System.out.println(isCoeffs + " isCoeffs");
      System.out.println("---done---");

      if(isPolynomial == true) {

        boolean success = true;
        System.out.println("Before try");

        degreeField.revalidate();	
        testInput = degreeField.getText();


        int test = 0;
        try {

          test = Integer.parseInt(testInput);

        }
        catch(NumberFormatException ev){

                    success = false;
          degreeField.setText(null);
          System.out.println("catch block");

        }

        if(success==true) {

          isPolynomial = false;
          System.out.println("Entered");
          degrees = test;
          functionPanel.remove(degreeField);
          System.out.println(degrees);
          isCoeffs = true;
          setCoeffs(degrees+1);
        }
      }  

      System.out.println("Isaac and amri");
      System.out.println(doneCoeffs);
      if(doneCoeffs==true){
        System.out.println("CALLING DONECOEFFS"); 
        boolean success = true;
        test = new int[degrees+1];
        try {
          System.out.println(degrees + " though ");
          System.out.println(test.length);
          for(int i=0; i < test.length; i++) {

            test[i] = Integer.parseInt(coeffs[i].getText());
          }
        }
        catch(NumberFormatException ev) {
          success  = false;
          for(int i=0; i<=degrees; i++) {
            coeffs[i].setText(null);
          }
          System.out.println("blabla");
        }
        System.out.println("NOT INT ");
        if(success == true) {

          //Graphit!
          graphit(test);
          repaint();
          isCoeffs = false;
          System.out.println(test);
        }




      }
      if(isCoeffs == true){
        isCoeffs = false;
        doneCoeffs=true;

      }




      //    isCoeffs = false; 
      isReady = true;

      System.out.println("--AFTER END - ENTER COMMAND--");
      System.out.println(isPolynomial + " isPolynomial");
      System.out.println(doneCoeffs + " doneCoeffs");
      System.out.println(isCoeffs + " isCoeffs");
      System.out.println("---done---");
    }

  }
  /*
     if(isReady==true){
//get the input from the txt fields


}

}


}*/
private synchronized void graphit(int[] coeffs){
  

  if(plotter == null){
    plotter = new Plotter(coeffs, 200, "Polynomial", lineSpacing);
  }
  System.out.println(-hor + "-hor " + hor +  " :hor");
  functions = plotter.runPlots(-hor, hor, gc.getWidth());
  repaint();

}


private void setCoeffs(int degree) {

  System.out.println(degree);
  char[] coeff = new char[degree];
  coeff[0] = 'a';
  coeffs = new JTextField[degree];

  for(int i =1; i<degree; i++) {

    coeff[i] = (char)(coeff[i-1]+1);

  }
  for(int i=0; i<degree; i++){
    System.out.println("259");
    coeffs[i]=new JTextField("Enter " + coeff[i]);

    functionPanel.add(coeffs[i]);
    //
  }

  for(int i=0; i<degree; i++) {
    final int som = i;

    coeffs[i].addKeyListener(new KeyHandler());
    coeffs[i].addMouseListener(new MouseHandler() {

        public void mouseClicked(MouseEvent ev) {
        coeffs[som].setText("");
        }
        }); 
  } 


  functionPanel.revalidate();
  functionPanel.repaint();


} 




private void checkFunctionTypeCases(){
  polynomialBtn = new JButton("Polynomial");
  functionPanel.add(polynomialBtn);
  polynomialBtn.addActionListener(this);

}

public void paintComponent(Graphics g){


  super.paintComponent(g);
  g2 =(Graphics2D) g;

  g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
      RenderingHints.VALUE_ANTIALIAS_ON);
  //line thickness width
  g2.setStroke(new BasicStroke(2));

  g2.drawLine(0, origin.y, getWidth(), origin.y);
  g2.drawLine(origin.x, 0, origin.x, getHeight());

  g2.setStroke(new BasicStroke(1));

  oldW = gc.getWidth();
  oldH = gc.getHeight();

  drawGreyLines(g2);
  g.drawString("AMRI AND ISAAC \u00a9", 10, 20);
      
      if(functions != null){
       ////
       plots = plotter.convertToPanelCoordinate(functions, origin);
       for(int i = 0; i < plots.length-1; i++){
         g2.drawLine((int)plots[i].getX(), (int)plots[i].getY(),
        (int)plots[i+1].getX(), (int)plots[i+1].getY());
        System.out.println("plots[" + i + "].x = "  + plots[i].getX() +
            " && plots[" + i + "].y = " + plots[i].getY());
        
       }
       
       
       
       
       ////

      }
}


public synchronized void drawGreyLines(Graphics2D g2) {

  if(oldW == gc.getWidth() && oldH == gc.getHeight()) {
    
    //for initial positions of the grey line, shall the viewport be panned
    //j is the position of the first y coordinate for the first horizontal line
    //i is the postition of the frirst x coordinate for the first vertical line 
    j = (gc.getHeight() + origin.y)%lineSpacing;
    i = (gc.getWidth() + origin.x)%lineSpacing;
    System.out.println(j + " =j " + i + " = i" );
    for(; i < gc.getWidth() && j<gc.getHeight(); i+=lineSpacing, j+=lineSpacing){
      //the number labels on the axis       
      hor = (i - origin.x)/lineSpacing;
      ver = -(j - origin.y)/lineSpacing;

      g2.setColor(Color.LIGHT_GRAY);
      g2.drawLine(i, 0, i ,gc.getHeight());
      g2.drawLine(0, j, gc.getWidth(), j );

      g2.setColor(Color.BLUE);
      g2.setFont(new Font("TimesRoman", Font.PLAIN, 10));

      //counter to count for the pixel xhange after being dragged
      g2.drawString("" + hor, i + counter_X%lineSpacing, origin.y+lineSpacing);

      g2.drawString("" + ver, origin.x-lineSpacing,j + counter_Y%lineSpacing);

    }

    counter_Y = 0;
    counter_X = 0;
  }

  else {

    for(; i < gc.getWidth() && j<gc.getHeight(); i+=lineSpacing, j+=lineSpacing){


      g2.setColor(Color.LIGHT_GRAY);

      g2.drawLine(i, 0, i ,gc.getHeight());

      g2.drawLine(0, j, gc.getWidth(), j );
    }
  }


}

public Dimension getPreferredSize() {
  return new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT);
}

private class KeyHandler implements KeyListener {


  public void keyTyped(KeyEvent ke){


    //degrees = Integer.parseInt(degreeField.getText());
  }

  public void keyPressed(KeyEvent ke) {}
  public void keyReleased(KeyEvent ke) {}

}

private class MouseHandler implements MouseMotionListener, MouseListener{


  public void mouseDragged(MouseEvent me) {

    int dx = me.getX() - mousePt.x;
    int dy = me.getY() - mousePt.y;

    if(dx!=0) {

      counter_X++;

    }

    if(dy!=0)  {

      counter_Y++;

    }

    origin.setLocation(origin.x + dx, origin.y + dy);
    mousePt = me.getPoint();
    repaint();

  }

  @Override
    public void mouseMoved(MouseEvent evt){


    }
  public void mouseClicked(MouseEvent evt){


  }

  public void mouseEntered(MouseEvent evt){

  }

  public void mouseReleased(MouseEvent evt){

    counter_X = 0;
    counter_Y = 0;
  }

  public void mousePressed(MouseEvent me) {
    mousePt = me.getPoint();
    repaint();


  }

  public void mouseExited(MouseEvent evt){

  }

}

}
