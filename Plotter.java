import java.awt.Point;
import java.text.DecimalFormat;
public class Plotter {

  private Plot[] plots;
  private int[] coeffs;
  private String name;
  private int pixelsBetweenNumbers;
  private Plot origin;
  private int plotSize;

  Plotter(int[] coeffs, int plotSize, String functionName,
      int pixelsBetweenNumbers, Point origin){

    plots = new Plot[plotSize];
    name = functionName;
    this.plotSize = plotSize;
    this.coeffs = coeffs;
    this.pixelsBetweenNumbers = pixelsBetweenNumbers;
    this.origin = new Plot(origin.x, origin.y);

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

      plots[j] = convertToPanelCoordinate(i, evaluate(i), j); 
     

      
    }
    return plots;
  }





  private Plot convertToPanelCoordinate(double x, double y, int j)  {
     
    
    int xPlot = (int)( x*pixelsBetweenNumbers + origin.getX());

    int yPlot = (int)(origin.getY()+evaluate(x)* pixelsBetweenNumbers);
    if(plots[j] ==null){
      plots[j] = new Plot(xPlot, yPlot);
    }
    else {
      plots[j] = plots[j].modify(xPlot,yPlot);
    }
    System.out.println(new DecimalFormat("#.##").format(x) + ", "
        +  new DecimalFormat("#.##").format(y) + " --> "
        + xPlot + ", " + yPlot ); 
    return plots[j];
   
  }


  //coeffs[2, 3, 1]; // 2x^2 + 3x + 1
  //evaluate(-15, 0);
  //return (2(-15)^2 + 3(-15) + 1)

  public double evaluate(double x) {


    double y = 0;
    for (int i = 0; i < coeffs.length; i++){
      y = coeffs[i];

    }

    return y;
  } 



  public Plot getPlot(int index){
    return plots[index];

  }


}
