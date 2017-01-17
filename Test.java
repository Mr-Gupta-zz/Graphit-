import java.awt.Point;
public class Test {

  public Test(){

  init();
  }

  public static void main(String[] args){
    Test test = new Test();
  }

  public void init(){
    Point origin = new Point(300, 300);
    int[] coeffs = new int[]{2,0,0};
   Plotter plotter = new Plotter(coeffs, 50, "Polynomial", 20, origin);

    Plot[] plots =  plotter.runPlots(-5, 5, 600);



  }


}
