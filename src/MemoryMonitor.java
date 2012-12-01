// $Id: MemoryMonitor.java,v 1.6 2003/02/26 21:08:47 jeffmc Exp $
/*
 * Copyright 1997-2000 Unidata Program Center/University Corporation for
 * Atmospheric Research, P.O. Box 3000, Boulder, CO 80307,
 * support@unidata.ucar.edu.
 * 
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or (at
 * your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation,
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA 
 */


//package ucar.unidata.ui;
//import  ucar.unidata.util.Misc;
//import  ucar.unidata.util.GuiUtils;

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;
import java.text.DecimalFormat;


public class MemoryMonitor  extends javax.swing.JPanel implements Runnable
{


    private boolean running = false;
    private long sleepInterval  = 2000;
    private Thread thread;
    private int percentThreshold;
    private int timesAboveThreshold = 0;
    private static DecimalFormat fmt =  new DecimalFormat("#0.00");
    private JLabel label1 = new JLabel ("");
    private JLabel label2 = new JLabel ("");
    private Color labelForeground;
    private boolean inTheRed = false;

    public MemoryMonitor () {
	this (80);
    }

    public MemoryMonitor (int percentThreshold) {
	this.percentThreshold = percentThreshold;
 //       GuiUtils.hbox (this, Misc.newList (label1, label2));
        this.add(label1);
        this.add(label2);
        MouseListener ml = new MouseAdapter () {
                public void mouseClicked (MouseEvent e) {
                    if (running) stop ();
                    else start ();
                }
            };
        labelForeground = label2.getForeground ();
        label1.addMouseListener (ml);
        label2.addMouseListener (ml);
        start ();
    }

    public void setLabelFont (Font f) {
	label1.setFont (f);
	label2.setFont (f);
    }

    private synchronized void  stop () {
        running = false;
        label1.setEnabled (false);
        label2.setEnabled (false);
    }



    private synchronized void  start () {
        if (running) return;
        label1.setEnabled (true);
        label2.setEnabled (true);
        running = true;
        thread = new Thread ( this, "Memory monitor");
        thread.start ();
    }


    private void showStats () {
        double totalMemory = (double) Runtime.getRuntime ().totalMemory ();
        double freeMemory = (double) Runtime.getRuntime ().freeMemory ();
        double usedMemory = (totalMemory-freeMemory);
        int percent = (int)(100.0*(usedMemory/totalMemory));
        totalMemory = totalMemory/1000000.0;
        usedMemory = usedMemory/1000000.0;
        label1.setText (" Memory: " + fmt.format (usedMemory)+"/"+fmt.format (totalMemory)+" MB");
        label2.setText (" (" + percent +"%)  ");

        // doing this continually slows things down
        //Runtime.getRuntime ().gc (); 
        if (percent > percentThreshold) {
            timesAboveThreshold ++;
            if (timesAboveThreshold > 5) {
		if (!inTheRed) {
		    setInTheRed (true);
		}
	    }
            Runtime.getRuntime ().gc ();

        } else {
	    if (inTheRed) {
		setInTheRed (false);		
	    }
            timesAboveThreshold = 0;
        }
    }

    protected void setInTheRed (boolean red) {
	inTheRed = red;
	if (inTheRed)
	    label2.setForeground (Color.red);
	else
            label2.setForeground (labelForeground);
    }

    public void run () {
        while (running) {
            showStats ();
            try {thread.sleep (sleepInterval);} catch (Exception exc) {}
        }
    }

    public void setRunning  (boolean r) {
        running = r;
    }

    public boolean getRunning  () {
        return running;
    }

    public static void main (String[]args) {
        JFrame f = new JFrame ();
        MemoryMonitor mm = new MemoryMonitor ();
        f.getContentPane ().add (mm);
        f.pack ();
        f.show ();
    }


}
