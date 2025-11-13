
package main;

import modell.JatekModell;
import nezet.JatekNezet;
import vezerlo.JatekVezerlo;


public class JatekIndito {

    
    public static void main(String[] args) {
       
            JatekNezet nezet = new JatekNezet();
            JatekModell modell = new JatekModell();
            new JatekVezerlo(nezet, modell);
            nezet.setVisible(true);
        
    }
    
}
