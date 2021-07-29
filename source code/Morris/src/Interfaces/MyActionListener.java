package Interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Windows 10 Pro
 */
public class MyActionListener implements ActionListener {

    javax.swing.JLabel jlbOrigen;
    javax.swing.JLabel jlbMeta;
   
    int velocidad=10;
    Timer timer;

    int xo, yo, xm, ym;

    public MyActionListener(javax.swing.JLabel jlbOrigen, javax.swing.JLabel jlbMeta) {
        this.jlbOrigen = jlbOrigen;
        this.jlbMeta = jlbMeta;

    }

    public void actionPerformed(ActionEvent arg0) {
        xo = jlbOrigen.getX();
        yo = jlbOrigen.getY();
        xm = jlbMeta.getX();
        ym = jlbMeta.getY();

        if (!(xo == xm && yo == ym)) {
            if (Math.abs(xm - xo) >= velocidad && Math.abs(ym - yo)  >= velocidad) {
                jlbOrigen.setLocation(xo = xo + velocidad * (int) Math.signum((double) (xm - xo)), yo = yo + velocidad* (int) Math.signum((double) (ym - yo)));
                //System.out.println("----cond 1");
            } else if (Math.abs(xm - xo)  < velocidad && Math.abs(ym - yo) >= velocidad) {
                jlbOrigen.setLocation(xo = xo +  (int) Math.signum((double) (xm - xo)), yo = yo +velocidad*(int) Math.signum((double) (ym - yo)));
                //System.out.println("----cond 2");
            } else if (Math.abs(ym - yo) < velocidad && Math.abs(xm - xo)  >= velocidad) {
                jlbOrigen.setLocation(xo = xo + velocidad*(int) Math.signum((double) (xm - xo)), yo = yo + (int) Math.signum((double) (ym - yo)));
                //System.out.println("----cond 3");
            }else if (Math.abs(xm - xo)  < velocidad && Math.abs(ym - yo) < velocidad) {
                jlbOrigen.setLocation(xo = xo +  (int) Math.signum((double) (xm - xo)), yo = yo +(int) Math.signum((double) (ym - yo)));
                //System.out.println("----cond 4");
            }
            //System.out.print("event--->(x,y)=" + xo + "," + yo);
        } else {
            timer.stop();
            //System.out.println("*****Debug*****");
        }

    }
}
