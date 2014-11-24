

import java.awt.Graphics;
import java.awt.Panel;

public class Tessellation extends Panel {

    public void paint(Graphics g)
    {
	int height = getHeight();
	int width = getWidth();
	
	Hexagon hex = new Hexagon(g, height, width);
	Diamond dia = new Diamond(g, height, width);

	Thread thread1 = new Thread(hex);
	Thread thread2 = new Thread(dia);

	thread1.start();
	thread2.start();

	try {
	    thread1.join();
	    thread2.join();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }
}
