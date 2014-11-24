

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Hexagon implements Runnable {

    Graphics panel;
    private int panelWidth, panelHeight;
    private static double TRI_HYP = 75;
    private static final double TRI_ANG = Math.PI / 6;
    private static final double TRI_WIDTH = TRI_HYP * Math.sin(TRI_ANG);
    private static final double TRI_HEIGHT = TRI_HYP * Math.cos(TRI_ANG);
    private TreeMap<Integer, Location> locations;
    private static int counter = 0;

    public Hexagon(Graphics g, int height, int width) {
	panel = g;
	panelWidth = width;
	panelHeight = height;
	locations = new TreeMap<Integer, Location>();
    }

    public void run() {
	getLocations();
	drawHexagon();
    }

    public void getLocations() {
	for(int h = 0; panelHeight - h > 0; h = (int) (h + TRI_HEIGHT * 2))
	{
	    for(int w = 0; panelWidth - w > 0; w = (int) (w + TRI_HYP * 2))
	    {
		double[] x={w, w + TRI_WIDTH, w + TRI_WIDTH + TRI_HYP, w + TRI_HYP * 2,
			w + TRI_WIDTH + TRI_HYP, w + TRI_WIDTH};
		double[] y={h + TRI_HEIGHT, h, h, h + TRI_HEIGHT, h + TRI_HEIGHT * 2,
			h + TRI_HEIGHT * 2};

		Location loc = new Location(x, y);
		locations.put(counter, loc);
		counter++;
	    }	
	}
    }

    public void drawHexagon() {
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

	    for(int u = 0; u < x.length; u++) {
		tempx[u]=(int) x[u];
		tempy[u]=(int) y[u];
	    }

	    for(int i = 0; i < 85; i++)
	    {
		if(i < 21)
		    g.setColor(new Color(141, (int) (Math.random() * 255), 134, 12*i));
		else
		    g.setColor(new Color((int) (Math.random() * 255), 136, 166, 3*i));
		g.drawPolygon(tempx, tempy, x.length);
		Thread.sleep(30);

		for(int r = 0; r < x.length; r++) //scales each of the x coordinates
		{
		    if(r == x.length - 1)
			x[r]=(((300-i)/300.0)*x[r]+(i/300.0)*x[0]);
		    else
			x[r]=(((300-i)/300.0)*x[r]+(i/300.0)*x[r+1]);
		    tempx[r]=(int) x[r];
		}

		for(int c=0; c<y.length; c++) //scales each of the y coordinates
		{
		    if(c==x.length-1)
			y[c]=(((300-i)/300.0)*y[c]+(i/300.0)*y[0]);
		    else
			y[c]=(((300-i)/300.0)*y[c]+(i/300.0)*y[c+1]);
		    tempy[c]=(int) y[c];
		}
	    }
	}
    }
}
