/**
 * @author Charmi Patel
 *  2D Tessellation
 *
 */

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Screensaver {
    public static void main (String [] args)
    {
	Frame myFrame= new Frame();
	Tessellation panel= new Tessellation();
	myFrame.setSize(1400, 772);
	panel.setBackground(Color.BLACK);
	myFrame.add(panel);
	myFrame.setVisible(true);

	WindowAdapter myAdapter= new WindowAdapter()
	{
	    public void windowClosing(WindowEvent e)
	    {
		System.exit(0);
	    }
	};
	myFrame.addWindowListener(myAdapter);
    }
}
