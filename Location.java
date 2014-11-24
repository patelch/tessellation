

public class Location {

    private double[] x;
    private double[] y;
    private boolean flag;
    
    public Location(double[] x, double[] y) {
	this.x = x;
	this.y = y;
	flag = false;
    }
    
    public double[] getXLoc() {
	return x;
    }
    
    public double[] getYLoc() {
	return y;
    }
    
    public void locationVisited() {
	flag = true;
    }
    
    public boolean getFlag() {
	return flag;
    }
    
}
