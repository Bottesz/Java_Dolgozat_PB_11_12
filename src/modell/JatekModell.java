
package modell;


public class JatekModell {

private boolean[][] tabla;

public JatekModell(){
    tabla = new boolean[3][3];
    

}
    public void kapcsol(int sor, int oszlop){
    valtas(sor,oszlop);
    if (sor > 0) valtas (sor - 1, oszlop);
    if (sor < 2) valtas (sor + 1, oszlop);
    if (oszlop > 0) valtas (sor,oszlop -1);
    if (oszlop < 2) valtas (sor,oszlop +1);
    
    }

    private void valtas(int sor, int oszlop) {
        tabla[sor][oszlop] = !tabla[sor][oszlop];
    }
    
    public boolean getErtek(int sor, int oszlop){
    return tabla[sor][oszlop];
    
    }
    
    
    public void reset(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabla[i][j] = false;
            }
        }
    
    }
    
    public boolean nyert(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!tabla[i][j]) return false;
                    
                
            }
            
        }
        return true;
    }
    
}
