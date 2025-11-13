
package test;


import modell.JatekModell;

public class JatekModellTest {
    
    public static void main(String[] args) {
        JatekModell modell = new JatekModell();
        
        
        testReset(modell);
        testKapcsol(modell);
        testNyert(modell);
        testSarokKapcsol(modell);
    }

    private static void testReset(JatekModell modell) {
         modell.kapcsol(1, 1);
         modell.reset();
         boolean siker = true;
         for (int i = 0; i < 3; i++) {
             for (int j = 0; j < 3; j++) {
                 if (modell.getErtek(i, j)) siker = false;
                     
                 System.out.println("Reset Teszt: " + (siker ? "Sikeres" : "Sikertelen"));
             }
        }
    }

    private static void testKapcsol(JatekModell modell) {
       modell.reset();
       modell.kapcsol(1,1);
       
       boolean siker = modell.getErtek(1, 1) && modell.getErtek(0, 1) && modell.getErtek(2, 1) && modell.getErtek(1, 0) && modell.getErtek(1, 2);
       
       System.out.println("Kapcsol teszt: " + (siker ? "Sikeres" : "Sikertelen"));
    }

    private static void testNyert(JatekModell modell) {
        modell.reset();
        boolean nyert = modell.nyert();
        System.out.println("Üres Tábla: " + (nyert? "Hiba" : "Ok"));
        
        
    }

    private static void testSarokKapcsol(JatekModell modell) {
        modell.reset();
        modell.kapcsol(0, 0);

        boolean siker = modell.getErtek(0, 0) && modell.getErtek(0, 1) && modell.getErtek(1, 0)  && !modell.getErtek(1, 1); 
                        
                  
        System.out.println("Sarok kapcsolás teszt: " + (siker ? "Sikeres" : "Sikertelen"));
    }
    }
    

