

import java.awt.Color;
import java.awt.Graphics;
import java.util.TreeMap;

public class Diamond implements Runnable {

    Graphics panel;
    int panelWidth, panelHeight;
    private static final double TRI_HYP = 75;
    private static final double TRI_ANG = Math.PI / 6;
    private static final double TRI_WIDTH = TRI_HYP * Math.sin(TRI_ANG);
    private static final double TRI_HEIGHT = TRI_HYP * Math.cos(TRI_ANG);
    private TreeMap<Integer, Location> locations;
    private static int counter = 0;

    public Diamond(Graphics g, int height, int width) {
	panel = g;
	panelWidth = width;
	panelHeight = height;
	locations = new TreeMap<Integer, Location>();
    }

    public void run() {
	getLocations();
	drawDiamond();
    }

    public void getLocations()
    {
	for(int h = 0; panelHeight - h > 0; h = (int) (h + TRI_HEIGHT * 2))
	{
	    for(int w = 0; panelWidth - w > 0; w = (int) (w + TRI_HYP * 2))
	    {
		double[] x= {w, (int) (w - TRI_HYP / 2.0), w, (int) (w + TRI_HYP / 2.0)};
		double[] y= {h - TRI_HEIGHT, h, h + TRI_HEIGHT, h};

		Location loc = new Location(x, y);
		locations.put(counter, loc);
		counter++;
	    }	
	}
    }

    public void drawDiamond() {
	int i = 0;
	while(i < locations.size()) {
	    Integer randInt = (int) (Math.random() * locations.size());
	    try {
		Location loc = locations.get(randInt);
		if(loc.getFlag() == false) {
		    loc.locationVisited();
		    pursuit(panel, loc.getXLoc(), loc.getYLoc());
		    i++;
		}
	    } catch(InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }

    public void pursuit(Graphics g, double[] x, double[] y) throws InterruptedException {

	synchronized(panel) {
	    int[] tempx= new int[x.length];
	    int[] tempy=new int[y.length];
	    for(int u=0; u<x.length; u++) {
		tempx[u] = (int) x[u];
		tempy[u] = (int) y[u];
	    }

	    for(int i = 1; i < 25; i++)
	    {
		g.setColor(new Color(134, 141, (int) (Math.random() * 255), 10*i));
		g.drawPolygon(tempx, tempy, x.length);
		Thread.sleep(60);

		for(int r=0; r<x.length; r++) //scales each of the x coordinates
		{
		    if(r==0)
		    {
			x[r]=(((300-i)/300.0)*x[r]+(i/300.0)*x[x.length-1]);
			tempx[r]=(int) x[r];
		    }
		    else
		    {
			x[r]=(((300-i)/300.0)*x[r]+(i/300.0)*x[r-1]);
			tempx[r]=(int) x[r];
		    }
		}
		for(int c=0; c<y.length; c++) //scales each of the y coordinates
		{
		    if(c==0)
		    {
			y[c]=(((300-i)/300.0)*y[c]+(i/300.0)*y[y.length-1]);
			tempy[c]=(int) y[c];
		    }
		    else
		    {
			y[c]=(((300-i)/300.0)*y[c]+(i/300.0)*y[c-1]);
			tempy[c]=(int) y[c];
		    }
		}
	    }
	}
    }
}
