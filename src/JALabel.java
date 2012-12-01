

/*
 * JALabel.java
 * Author: Jean-Luc PONS
 * Java anti-aliased label
 * http://cvs.sourceforge.net/viewcvs.py/tango-cs/tango/atk/src/fr/esrf/tangoatk/widget/util/chart/JALabel.java?rev=1.2&view=auto
 */


import java.awt.event.*;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 * A Class for Anti-Aliased label.
 * @author JL Pons
 */

public class JALabel extends JLabel {
    public JALabel(String s) {
        super(s);
    }
    
    public JALabel() {
        super();
    }
    
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        super.paint(g);
    }
};
