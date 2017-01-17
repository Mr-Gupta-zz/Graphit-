import java.awt.Point;
import java.text.DecimalFormat;
public class Plotter {

  private Plot[] plots;
  private int[] coeffs;
  private String name;
  private int pixelsBetweenNumbers;
  private Plot origin;
  private int plotSize;
  private Plot[] xAndYs;
  Plotter(int[] coeffs, int plotSize, String functionName,
      int pixelsBetweenNumbers){

    plots = new Plot[plotSize];
    name = functionName;
    this.plotSize = plotSize;
    this.coeffs = coeffs;
    this.pixelsBetweenNumbers = pixelsBetweenNumbers;
    
    xAndYs = new Plot[plotSize];

  }
  //example runPlots(-15, 15);
  //runs a plotter that stores 200 plots from -15 to 15.
  //plot1: (-15, f(-15));
  //plot 100: (0, f(0);
  //plot 200: (15, f(15));
  //
  // runPlots(-15, 15, gc.getWidth(), -15, 20)
  public Plot[] runPlots(int leftMostValue, int rightMostValue, int width){
    int x;
    int y;
    double deltaX = width/pixelsBetweenNumbers;
    double increment = deltaX/plotSize; //0.16
    int j = 0;

    for(double i = leftMostValue; j < plotSize; i+= increment, j++){

      xAndYs[j] = new Plot(i * pixelsBetweenNumbers, evaluate(i)*pixelsBetweenNumbers);
      
    }
    return xAndYs;
  }


  public  Plot[] convertToPanelCoordinate(Plot[] relations, Point origin){
    for(int i = 0; i < relations.length; i++){


    int xPlot = (int)( relations[i].getX() + origin.x);
    int yPlot = (int)( origin.y - relations[i].getY());
    if(plots[i] ==null){
      plots[i] = new Plot(xPlot, yPlot);
    }
    else {
      plots[i] = plots[i].modify(xPlot,yPlot);
    }
 

    }
    return plots;
  }


  Plot convertToPanelCoordinate(double x, double y, int j)  {
     
    
    int xPlot = (int)( x*pixelsBetweenNumbers + origin.getX());
    //int xPlot = (int) (x * 20  + 300);

    
 //   int yPlot = (int) ((-y * 20) + 300);
    int yPlot = (int)( origin.getY() - evaluate(x) * pixelsBetweenNumbers);


    if(plots[j] ==null){
      plots[j] = new Plot(xPlot, yPlot);
    }
    else {
      plots[j] = plots[j].modify(xPlot,yPlot);
    } 
    System.out.println(    new DecimalFormat("#.##").format(x) + ", "
        +  new DecimalFormat("#.##").format(y) + " --> "
        + xPlot + ", " + yPlot );  
    return plots[j];
   
  } 

  

  

    


  public static Plot[] shiftPlots(Point origin, Plot[] plots){
    for(int i = 0; i < plots.length; i++){
      plots[i].modify(plots[i].getX() + 
          origin.x, plots[i].getY() + origin.y);
    }
    return plots;

  }


  //coeffs[2, 3, 1]; // 2x^2 + 3x + 1
  //evaluate(-15, 0);
  //return (2(-15)^2 + 3(-15) + 1)

  public double evaluate(double x) {


    double y = 0;
    for (int i = 0; i < coeffs.length; i++){
      y = coeffs[i] + (x * y);

    }

    return y;
  } 



  public Plot getPlot(int index){
    return plots[index];

  }


}
