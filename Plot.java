public class Plot{


  private double x;
  private double y;
  Plot(double x ,double y){
    this.x = x;
    this.y = y;

  }

  public Plot modify(double x, double y){
    this.x = x;
    this.y = y;
    return this;
  }

  public double getX() {
    return x;
  }
  public double getY() {
    return y;
  }

  
  
}
