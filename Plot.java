public class Plot{


  int x;
  int y;
  Plot(int x ,int y){
    this.x = x;
    this.y = y;

  }

  public Plot modify(int x, int y){
    this.x = x;
    this.y = y;
    return this;
  }

  public int getX() {
    return x;
  }
  public int getY() {
    return y;
  }

  
  
}
